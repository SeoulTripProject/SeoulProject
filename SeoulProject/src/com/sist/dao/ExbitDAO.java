package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.vo.*;
public class ExbitDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@211.238.142.211:1521:XE";
	private static ExbitDAO dao;
	// 드라이버 등록
	public ExbitDAO()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception ex) {}
	}
	// 연결 시작
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
	// 싱글턴 => DAO 객체 하나만사용 (메모리공간 1개만 생성) = 재사용
	// 스프링에서는 기본(싱글턴) => 필요시에는 여러개 객체 생성 => prototype
	public static ExbitDAO newInstance()
	{
		if(dao==null)
			dao=new ExbitDAO();
		return dao;
	}
	// 데이터 수집 => insert
	public void exbitInsert(ExbitVO vo)
	{
		try
		{
			getConnection();
			String sql="INSERT INTO trip_E VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getEno());
			ps.setInt(2, vo.getNo());
			ps.setString(3, vo.getTitle());
			ps.setString(4, vo.getPoster());
			ps.setString(5, vo.getDetail());
			ps.setString(6, vo.getPeriod());
			ps.setString(7, vo.getTel());
			ps.setString(8, vo.getTime());
			ps.setString(9, vo.getDay());
			ps.setString(10, vo.getPrice());
			ps.setString(11, vo.getAddr());
			ps.setString(12, vo.getTrans());
			ps.setString(13, vo.getTag());
			ps.setInt(14, vo.getType());
			// 실행 요청
			ps.executeUpdate(); // Commit()
			/* 
			 *  => 크롤릴 / 오라클 열기(닫기)
			 *     ===== 약간 속도를 줄인다 (Thread,sleep())
			 */
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

