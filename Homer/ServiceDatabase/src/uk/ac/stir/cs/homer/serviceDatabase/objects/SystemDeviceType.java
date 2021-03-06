package uk.ac.stir.cs.homer.serviceDatabase.objects;

public class SystemDeviceType extends DBObject {

	public static final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS SystemDeviceType (" +
						"id VARCHAR PRIMARY KEY, " +
						"name VARCHAR, " +
						"jsonConfigData VARCHAR " +					
					")";
		
	public static final String SQL_INSERT = "INSERT INTO SystemDeviceType VALUES (?,?,?)";
	public static final String SQL_UPDATE = "UPDATE SystemDeviceType SET name=?, jsonConfigData=? WHERE id=?;";
	public static final String SQL_UPDATE_NAME = "UPDATE SystemDeviceType SET name=? WHERE id=?;";
	public static final String SQL_DELETE = "DELETE FROM SystemDeviceType WHERE id = ?";
	
	
	private String id;
	private String name;
	private String jsonConfigData;

	public SystemDeviceType(String id, String name, String jsonConfigData)
	{
		init(new String[] { id, name, jsonConfigData });
	}
	
	public SystemDeviceType() {}
	
	public String getId() {
		return id;
	}
	
	@Override
	public void init(String[] params) {
		this.id = params[0];
		this.name = params[1];
		this.jsonConfigData = params[2];
	}
	
	public String getJsonConfigData() {
		return jsonConfigData;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public int getConstructorSize() {
		return 3;
	}

	public void setName(String newName) {
		this.name = newName;
	}
	
	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof SystemDeviceType)
		{
			return id.equals( ((SystemDeviceType)arg0).getId() ); 
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return id.hashCode();
	}
}
