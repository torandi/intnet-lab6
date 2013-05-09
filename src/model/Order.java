package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

// Model for Order Table
public class Order extends DatabaseObject<Order> {

	private static final Order query_obj = new Order();

	/* the query object */
	public static Order q() {
		return query_obj;
	}

	@Override
	protected String table_name() {
		return "orders";
	}

	@Override
	protected Class<Order> cls() {
		return Order.class;
	}

	public Security getSecurity() {
		return Security.q().from_id(getSecurityId());
	}

	public int getSecurityId() {
		return get_int("security_id");
	}

	public void setSecurityId(int id) {
		set("security_id", new Integer(id));
	}

	public Boolean getType() {
		return (Boolean) get("type");
	}

	public void setType(Boolean type) {
		set("type", type);
	}
	
	public boolean isBuyOrder() {
		return getType();
	}
	
	public boolean isSellOrder() {
		return !getType();
	}

	public float getPrice() {
		return ((Float) get("price")).floatValue();
	}

	public void setPrice(float price) {
		set("price", new Float(price));
	}

	public int getAmount() {
		return get_int("amount");
	}

	public void setAmount(int amount) {
		set("amount", new Integer(amount));
	}

	// Buyer Seller
	public String getUid() {
		return (String) get("uid");
	}

	public void setUid(String uid) {
		set("uid", uid);
	}

	/* Tries to find a matching sell order for this buy order
	 * If this is not a buy order nothing is done
	 * @return True if a match was found
	 */
	public boolean match() throws SQLException, ValidationException {
		if(isSellOrder()) return false;
	
		PreparedStatement stmt = statement("security_id = ? AND amount = ? AND price <= ? AND type = 0 LIMIT 1");
		stmt.setInt(1, getSecurityId());
		stmt.setInt(2, getAmount());
		stmt.setFloat(3, getPrice());
		ArrayList<Order> sell_order = Order.q().where(stmt);
		if(sell_order.size() == 1) {
			/* Create trade */
			Trade trade = Trade.from_orders(this, sell_order.get(0));
			trade.commit();
			/* Delete the orders */
			sell_order.get(0).delete();
			this.delete();
			return true;
		} else {
			return false;
		}
		
	}
}
