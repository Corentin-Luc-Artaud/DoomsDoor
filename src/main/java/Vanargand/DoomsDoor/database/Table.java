package Vanargand.DoomsDoor.database;


public interface Table {
	/**
	 * 
	 * @return the name of the table
	 */
	public String getTableName();
	/**
	 * Attach the table to the database
	 * @param database
	 */
	public void atatch(Database database);
	/**
	 * @return the sqlQuery to create the table
	 */
	public String getSql();
	/**
	 * @brief insert the curent state of the table in the database
	 * @return true if the insertion succeed
	 */
}
