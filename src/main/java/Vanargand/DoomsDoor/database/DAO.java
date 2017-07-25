package Vanargand.DoomsDoor.database;

import java.util.List;

public interface DAO <T> extends Table{
	public List<T> getAll();
	public T getById(int id);
	public boolean insert(T object);
	public boolean delete(T object);
	public boolean update(T object);
}
