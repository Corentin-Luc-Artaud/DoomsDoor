package Vanargand.DoomsDoor.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database implements Comparable<Database>{
	private String id;
	private String path;
	private Connection connection;
	private Table [] tables;
	
	public Database(String id, String path, Table [] tables) throws SQLException {
		this.id = id;
		this.path = path;
		this.tables = tables;
		connect();
		integrity();
		notifyTables();
	}
	private void connect() throws SQLException {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		connection = DriverManager.getConnection("jdbc:sqlite:"+path);
	}
	
	private void integrity() {
		String request = "SELECT * FROM sqlite_master where type='table'";
		boolean areValids [] = new boolean[tables.length];
		for (int i = 0; i < areValids.length; ++i)
			areValids[i] = false;
		
		try (PreparedStatement stmt = connection.prepareStatement(request)){
			stmt.execute();
			ResultSet result = stmt.getResultSet();
			while (result.next()) {
				String name = result.getString("name");
				for(int i  = 0; i < tables.length; ++i) {
					if (tables[i].getTableName().equals(name) && 
							result.getString("sql").equals(tables[i].getSql())) 
						areValids[i] = true;
					
					else {
						
						try(PreparedStatement stmtdel = connection.prepareStatement("Drop Table "+name)) {
							stmtdel.execute();
						}catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			}
			for (int i = 0; i < tables.length; ++i) {
				if (! areValids[i]) {
					try(PreparedStatement stmt2 = connection.prepareStatement(tables[i].getSql())){
						stmt2.execute();
					}catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		};
		
	}
	
	public void notifyTables() {
		for (int i = 0; i < tables.length; ++i){
			tables[i].atatch(this);
		}
	}
	
	@Override
	public int compareTo(Database o) {
		return this.id.compareTo(o.id);
	}
	
	public String getId() {
		return id;
	}
	
	public Connection getConnection() {
		return connection;
	}
}
