package model;

import java.sql.Timestamp;
import java.util.Date;

// Model for Trades tabel
public class Trade extends DatabaseObject<Trade> {

	private static final Trade query_obj = new Trade();

	/* the query object */
	public static Trade q() {
		return query_obj;
	}

	protected String default_order() {
		return "dt ASC";
	}

	@Override
	protected String table_name() {
		return "trades";
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

	public String getBuyer() {
		return (String) get("buyer");
	}

	public void setBuyer(String buyer) {
		set("buyer", buyer);
	}

	public String getSeller() {
		return (String) get("seller");
	}

	public void setSeller(String seller) {
		set("seller", seller);
	}

	public Timestamp getTime() {
		return (Timestamp) get("dt");
	}

	public void setTime(Timestamp time) {
		set("dt", time);
	}

	@Override
	protected Class<Trade> cls() {
		return Trade.class;
	}

	/*
	 * Populates a trade object with data from orders, does not commit
	 */
	public static Trade from_orders(Order buy_order, Order sell_order) {
		Trade trade = new Trade();

		trade.setAmount(buy_order.getAmount());
		trade.setBuyer(buy_order.getUid());
		trade.setSeller(sell_order.getUid());
		trade.setPrice(sell_order.getPrice());
		trade.setTime(new Timestamp(new Date().getTime()));
		trade.setSecurityId(buy_order.getSecurityId());
		return trade;
	}
}
