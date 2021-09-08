package com.sist.dao;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import com.sist.dao.*;
public class StayDAO {
	private Connection conn;
	private PreparedStatement ps;
	private static StayDAO dao; 

	public void getConnection()
	{
		try
		{
			Context init=new InitialContext();
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
	
	//�̱���
	public static StayDAO newInstance()
	{
		if(dao==null) // �̻����ÿ���
			dao=new StayDAO();
		return dao; // �̹� ������� dao��ü�� ����Ѵ�
	}

}
