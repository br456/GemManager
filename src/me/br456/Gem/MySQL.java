package me.br456.Gem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL {
	
	private MySQLConnector mysql;
	
	private Connection c;
	private Statement s;
	private ResultSet res;
	
	private String host;
	private String port;
	private String database;
	private String username;
	private String password;
	
	public MySQL(String host, String port, String database, String username, String password) {
		this.host = host;
		this.port = port;
		this.database = database;
		this.username = username;
		this.password = password;
		
		mysql = new MySQLConnector(Gem.getPlugin(), this.host, this.port, this.database, this.username, this.password);
		c = mysql.openConnection();
		createStatement();
		update("CREATE TABLE IF NOT EXISTS GemManager(name VARCHAR(50), gems INT(100))");		
	}
	
	public void createStatement() {
		if(c == null) return;
		try {
			s = c.createStatement();
		} catch (SQLException e) {
			Utils.sendError(3);
			System.out.println("GemManager Error Report:");
			e.printStackTrace();
			System.out.println("------------------------");
		}
	}
	
	public void establishConnection() {
		mysql.closeConnection();
		c = mysql.openConnection();
		createStatement();
	}
	
	public boolean error() {
		return mysql.checkConnection();
	}
	
	public ResultSet query(String query) {
		try {
			res = s.executeQuery(query);
			res.next();
			return res;
		} catch (SQLException e) {
			Utils.sendError(4);
			System.out.println("GemManager Error Report:");
			e.printStackTrace();
			System.out.println("------------------------");
		}
		return res;
	}
	
	public void update(String update) {
		try {
			s.executeUpdate(update);
		} catch (SQLException e) {
			Utils.sendError(5);
			System.out.println("GemManager Error Report:");
			e.printStackTrace();
			System.out.println("------------------------");
		}
	}
	
	public boolean exists(String name) {
		
		try {
			PreparedStatement ps = c.prepareStatement("SELECT name FROM GemManager WHERE name = ?");
			ps.setString(1, name);
			res = ps.executeQuery();
			if(!(res.next())) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			Utils.sendError(6);
			System.out.println("GemManager Error Report:");
			e.printStackTrace();
			System.out.println("------------------------");
			return false;
		}
		
	}
	
	public int getGems(String name) {
		int gems;
		try {
			PreparedStatement ps = c.prepareStatement("SELECT * FROM GemManager WHERE name = ?");
			ps.setString(1, name);
			res = ps.executeQuery();
			res.next();
			gems = res.getInt("gems");
		} catch (SQLException e) {
			Utils.sendError(7);
			System.out.println("GemManager Error Report:");
			e.printStackTrace();
			System.out.println("------------------------");
			gems = 0;
		}
		return gems;
	}
	
	public void closeConnection() {
		mysql.closeConnection();
	}
	
	public void setGems(String name, int gems) {
		try {
			PreparedStatement ps = c.prepareStatement("UPDATE GemManager SET gems = ? WHERE name = ?");
			ps.setString(1, Integer.toString(gems));
			ps.setString(2, name);
			System.out.println("Setting gems="+gems+" where name="+name);
			ps.executeUpdate();
		} catch (SQLException e) {
			Utils.sendError(8);
			System.out.println("GemManager Error Report:");
			e.printStackTrace();
			System.out.println("------------------------");
		}
	}
	
	public void addPlayer(String name) {
		try {
			PreparedStatement ps = c.prepareStatement("INSERT INTO GemManager (`name`, `gems`) VALUES (?, '0');");
			ps.setString(1, name);
			ps.executeUpdate();
		} catch (SQLException e) {
			Utils.sendError(9);
			System.out.println("GemManager Error Report:");
			e.printStackTrace();
			System.out.println("------------------------");
		}
	}
	
	public Connection getConnection() {
		return this.c;
	}
	
	public Statement getStatement() {
		return this.s;
	}
	
	public ResultSet getResultSet() {
		return this.res;
	}
	
	public String getHost() {
		return this.host;
	}
	
	public String getPort() {
		return this.port;
	}
	
	public String getDatabase() {
		return this.database;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
}
