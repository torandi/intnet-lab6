package model;

import java.sql.Timestamp;

public class Trade extends DatabaseObject<Trade> {
	
	private static final Trade query_obj = new Trade();

	/* the query object */
	public static Trade q() {
		return query_obj;
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
		set("security_id",new Integer(id));
	}
	
	public float getPrice() {
		return ((Float)get("price")).floatValue();
	}
	
	public void setPrice(float price) {
		set("price",new Float(price));
	}
	
	public int getAmount() {
		return get_int("amount");
	}
	
	public void setAmount(int amount) {
		set("amount",new Integer(amount));
	}
	
	public String getBuyer() {
		return (String)get("buyer");
	}
	
	public void setBuyer(String buyer) {
		set("buyer",buyer);
	}
	
	public String getSeller() {
		return (String)get("seller");
	}
	
	public void setSeller(String seller) {
		set("seller",seller);
	}
	
	public Timestamp getTime() {
		return (Timestamp)get("dt");
	}
	
	public void setTime(Timestamp time) {
		set("dt",time);
	}

	@Override
	protected Class<Trade> cls() {
		return Trade.class;
	}
}
