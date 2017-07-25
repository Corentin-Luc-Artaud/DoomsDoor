package Vanargand.DoomsDoor.database;

import java.util.TreeSet;

public class DatabasesRepository {
	private static DatabasesRepository instance = new DatabasesRepository();
	
	private TreeSet<Database> databases;
	
	private DatabasesRepository() {
		databases = new TreeSet<>();
	}
	
	public static DatabasesRepository getInstance() {
		return instance;
	}
	
	public boolean add(Database database) {
		return databases.add(database);
	}
	
	public Database getById(String id) {
		for (Database database : databases) {
			if (database.getId().equals(id))  return database;
		}
		return null;
	}
}
