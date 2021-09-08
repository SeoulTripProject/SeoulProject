package com.sist.dao;
import java.util.*;
import java.sql.*;
public class FoodDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@211.238.142.211:1521:XE";
	private static FoodDAO dao;
	
	public FoodDAO()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception ex) {}
	}
	
	public void getConnection()
	{
		try
		{
			conn=DriverManager.getConnection(URL,"hr","happy");
		}catch(Exception ex) {}
	}
	public void disConnection()
	{
		try
		{
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception ex) {}
	}
	
	public static FoodDAO newInstance()
	{
		if(dao==null)
			dao=new FoodDAO();
		return dao;
	}

	public void foodInsert(FoodVO vo)
	{
		try
		{
			getConnection();
			String sql="INSERT INTO trip_R VALUES("
					+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getNo());
			ps.setInt(2, vo.getRno());
			ps.setString(3, vo.getPoster());
			ps.setString(4, vo.getRname());
			ps.setDouble(5, vo.getScore());
			ps.setString(6, vo.getAddress());
			ps.setString(7, vo.getTel());
			ps.setString(8, vo.getRtype());
			ps.setString(9, vo.getPrice());
			ps.setString(10, vo.getParking());
			ps.setString(11, vo.getOpenHour());
			ps.setString(12, vo.getMenu());
			ps.setInt(13, vo.getGood());
			ps.setInt(14, vo.getSoso());
			ps.setInt(15, vo.getBad());
			ps.setString(16, vo.getRtag());
			ps.executeUpdate();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
	}
}
