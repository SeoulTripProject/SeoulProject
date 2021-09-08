package com.sist.dao;
import java.util.*;
import java.sql.*;
import javax.sql.*;

import com.sist.vo.FoodVO;

import javax.naming.*;
public class FoodDAO {
	private Connection conn;
	private PreparedStatement ps;
	private static FoodDAO dao;
	
	public void getConnection()
	{
		try
		{
			Context init=new InitialContext(); // 저장된 위치에 접근
			Context c=(Context)init.lookup("java://comp//env");
			DataSource ds=(DataSource)c.lookup("jdbc/oracle");
			conn=ds.getConnection();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void disConnection()
	{
		try
		{
			if(ps==null) ps.close();
			if(conn==null) conn.close();
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
