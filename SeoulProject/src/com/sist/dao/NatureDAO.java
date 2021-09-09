package com.sist.dao;
/*
 * Table TRIP_N
이름      널?       유형             
------- -------- -------------- 
TNO     NOT NULL NUMBER         
TITLE   NOT NULL VARCHAR2(1000) 
POSTER  NOT NULL VARCHAR2(260)  
INTRO            VARCHAR2(1000) 
REV_HIT          NUMBER         
TYPE    NOT NULL NUMBER 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class NatureDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL = "jdbc:oracle:thin:@211.238.142.211:1521/XE";
	private static NatureDAO dao;

	// 드라이버 등록
	public NatureDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception ex) {
		}
	}

	// 연결
	public void getConnection() {
		try
		{
			conn = DriverManager.getConnection(URL, "hr", "happy");
		} catch (Exception ex) {
		}
	}

	// 종료
	public void disConnection() {
		try {
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (Exception ex) {
		}
	}
	
	//
	public static NatureDAO newInstance() {
		if (dao == null) // 없으면 생성하고, 있으면 기존 꺼 사용
			dao = new NatureDAO();
		return dao;
	}
	
	// inser함수
	public void NatureInsert(NatureVO vo) {
		try {
			
			getConnection();
			String sql = "INSERT INTO trip_n VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; //19개
			ps = conn.prepareStatement(sql);
			ps.setInt(1, vo.getTno());
			ps.setInt(2, vo.getNo());
			ps.setString(3, vo.getTitle());
			ps.setString(4, vo.getPoster());
			ps.setString(5, vo.getIntro());
			ps.setString(6, vo.getNtag());
			ps.setString(7, vo.getLoc());
			ps.setString(8, vo.getContent());
			ps.setString(9, vo.getTel());
			ps.setString(10, vo.getWebsite());
			ps.setString(11, vo.getTime());
			ps.setString(12, vo.getHoliday());
			ps.setString(13, vo.getOpen());
			ps.setString(14, vo.getPrice());
			ps.setString(15, vo.getHandi());
			ps.setString(16, vo.getCaution());
			ps.setString(17, vo.getSite());
			ps.setString(18, vo.getTraffic());
			ps.setString(19, vo.getCategory());
			ps.setString(20, vo.getLink());
			// 실행 요청
			ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}

	}
}