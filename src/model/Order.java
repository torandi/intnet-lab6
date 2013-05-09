package model;

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

	public Security getSecurity() {
		return Security.q().from_id(getSecurityId());
	}

	public int getSecurityId() {
		return get_int("security_id");
	}

	public void setSecurityId(int id) {
		set("security_id", new Integer(id));
	}

	public String getType() {
		return (String) get("type");
	}

	public void setType(Boolean type) {
		set("type", type);
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

	@Override
	protected Class<Order> cls() {
		return Order.class;
	}
}
