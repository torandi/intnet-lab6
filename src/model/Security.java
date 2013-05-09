package model;

//Model for Security Table
public class Security extends DatabaseObject<Security> {

	private static final Security query_obj = new Security();

	/* the query object */
	public static Security q() {
		return query_obj;
	}
	
	@Override
	public void validate() throws ValidationException {
		validateMinLength("name", 3);
		validateUniqueness("name");
	}

	@Override
	protected String table_name() {
		return "securities";
	}

	public String getName() {
		return (String) get("name");
	}

	public void setName(String name) {
		set("name", name);
	}

	@Override
	protected Class<Security> cls() {
		return Security.class;
	}
}
