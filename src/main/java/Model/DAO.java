package Model;

import java.sql.Connection;

public abstract class DAO<T> {

	protected Connection cnx = null;
	
	public DAO(Connection cnx) {
		this.cnx = cnx;
	}
	
	public abstract T find(int id);
	public abstract boolean create(T obj);
	public abstract boolean update(T obj);
	public abstract boolean delete(T obj);
	
}
