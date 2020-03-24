package com.tj.ex.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tj.ex.dto.OrderDetailDto;
import com.tj.ex.dto.OrderDto;

public class OrdersDao {
	public static final int FAIL    =0;
	public static final int SUCCESS = 1;
	
	private static OrdersDao instance = new OrdersDao();
	public static OrdersDao getInstance() {
		return instance;
	}
	private OrdersDao() {}
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
			conn = ds.getConnection();
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	// 주문정보 입력
	public int orderInsert(String mId, String oName, String oAddress, String oTel)  {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO ORDERS (ONO,MID,ONAME,OADDRESS,OTEL) " + 
				"    VALUES ('ORDER'||TRIM(TO_CHAR(ORDERS_SEQ.NEXTVAL,'000')), ?,?,?,?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, oName);
			pstmt.setString(3, oAddress);
			pstmt.setString(4, oTel);
			
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "ORDER주문성공":"ORDER주문실패");
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!= null) pstmt.close();
				if(conn != null) conn.close();
			}catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	// 주문상세 페이지 배송자 정보 출력
	
	public OrderDto ordersMember (String oNo) {
		OrderDto oDto = new OrderDto();
		Connection        conn = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql ="SELECT * FROM ORDERS WHERE ONO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, oNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String mId = rs.getString("mId");
				String oName = rs.getString("oName");
				String oAddress = rs.getString("oAddress");
				String oTel = rs.getString("oTel");
				Date oRdate = rs.getDate("oRdate");
				String oState = rs.getString("oState");
				oDto = new OrderDto(oNo, mId, oName, oAddress, oTel, oRdate, oState);
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs   != null) rs.close();
				if(pstmt!= null) pstmt.close();
				if(conn != null) conn.close();
			}catch (SQLException e) {
				System.out.println(e.getMessage()+2);
			}
		}
		
		return oDto;
	}
	
	// 관리자 페이지에서 배송처리 시 주문 완료
	public int orderState(String oNo)  {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE ORDERS SET OSTATE = '배송완료' WHERE ONO=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, oNo);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "배송완료":"배송실패");
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!= null) pstmt.close();
				if(conn != null) conn.close();
			}catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	
	
	
	
}















