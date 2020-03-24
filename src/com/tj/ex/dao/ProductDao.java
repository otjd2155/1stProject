package com.tj.ex.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tj.ex.dto.ProductDto;

public class ProductDao {
	public static final int FAIL    =0;
	public static final int SUCCESS = 1;
	
	private static ProductDao instance = new ProductDao();
	public static ProductDao getInstance() {
		return instance;
	}
	private ProductDao() {}
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
	
	// 전체상품 불러오기
	public ArrayList<ProductDto> productlistFileBoard(int startRow, int endRow){
		ArrayList<ProductDto> PDto = new ArrayList<ProductDto>();
		Connection        conn = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM" + 
				"    (SELECT * FROM PRODUCT ORDER BY PRDATE DESC)A) WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String pCode 	 = rs.getString("pCode");
				String pName 	 = rs.getString("pName");
				String pType	 = rs.getString("pType");
				String pFilename = rs.getString("pFilename");
				String pOrign 	 = rs.getString("pOrign");
				String pWeight   = rs.getString("pWeight");
				String pPart 	 = rs.getString("pPart");
				String pUse		 = rs.getString("pUse");
				int    pPrice    = rs.getInt("pPrice");
				int    pStock    = rs.getInt("pStock");
				Date   pRdate    = rs.getDate("pRdate");
				
				PDto.add(new ProductDto(pCode, pName, pType, pFilename, pOrign, pWeight, pPart, pUse, pPrice, pStock, pRdate));
				
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage()+PDto.toString());
		}finally {
			try {
				if(rs   != null) rs.close();
				if(pstmt!= null) pstmt.close();
				if(conn != null) conn.close();
			}catch (SQLException e) {
				System.out.println(e.getMessage()+2);
			}
		}
		return PDto;
	}
	// type에 따른 상품 리스트
	public ArrayList<ProductDto> productTypelistFileBoard(String pType, int startRow, int endRow){
		ArrayList<ProductDto> PDto = new ArrayList<ProductDto>();
		Connection        conn = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM" + 
				"    (SELECT * FROM PRODUCT WHERE PTYPE=? ORDER BY PRDATE DESC)A) WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, pType);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String pCode 	 = rs.getString("pCode");
				String pName 	 = rs.getString("pName");
				String pFilename = rs.getString("pFilename");
				String pOrign 	 = rs.getString("pOrign");
				String pWeight   = rs.getString("pWeight");
				String pPart 	 = rs.getString("pPart");
				String pUse		 = rs.getString("pUse");
				int    pPrice    = rs.getInt("pPrice");
				int    pStock    = rs.getInt("pStock");
				Date   pRdate    = rs.getDate("pRdate");
				
				PDto.add(new ProductDto(pCode, pName, pType, pFilename, pOrign, pWeight, pPart, pUse, pPrice, pStock, pRdate));
				
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage()+PDto.toString());
		}finally {
			try {
				if(rs   != null) rs.close();
				if(pstmt!= null) pstmt.close();
				if(conn != null) conn.close();
			}catch (SQLException e) {
				System.out.println(e.getMessage()+2);
			}
		}
		return PDto;
	}
	
	
	
	// 전체  상품 개수
	public int productAllTotCnt() {
		int totCnt = 0;
		Connection        conn = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT COUNT(*) FROM PRODUCT";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totCnt = rs.getInt(1);
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs   != null) rs.close();
				if(pstmt!= null) pstmt.close();
				if(conn != null) conn.close();
			}catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return totCnt;
	}
	
	// 상품 타입별 개수
	public int productTypeTotCnt(String pType) {
		int totCnt = 0;
		Connection        conn = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT COUNT(*) FROM PRODUCT WHERE PTYPE=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pType);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totCnt = rs.getInt(1);
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs   != null) rs.close();
				if(pstmt!= null) pstmt.close();
				if(conn != null) conn.close();
			}catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return totCnt;
	}
	
	
	// 상품 등록
	
	public int productInsert(String pName, String pType, String pFilename, String pOrign, String pWeight, String pPart, String pUse, int pPrice, int pStock)  {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO PRODUCT (PCODE,PNAME,PTYPE,PFILENAME,PORIGN,PWEIGHT,PPART,PUSE,PPRICE,PSTOCK) " + 
				"    VALUES ('GOGI'||TRIM(TO_CHAR(PRODUCT_SEQ.nextval,'000')), ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		System.out.println("뭐야");
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,pName );
			pstmt.setString(2,pType );
			pstmt.setString(3,pFilename );
			pstmt.setString(4,pOrign );
			pstmt.setString(5,pWeight );
			pstmt.setString(6,pPart );
			pstmt.setString(7,pUse );
			pstmt.setInt(8, pPrice);
			pstmt.setInt(9, pStock);
			
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "상품등록성공":"상품등록실패");
		}catch (SQLException e) {
			System.out.println(e.getMessage()+"1");
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
	
	
	// Dto 정보 불러오기 
	public ProductDto productContentView(String pCode) {
		
		ProductDto pDto = null;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM PRODUCT WHERE PCODE=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pCode);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				String pName 	 = rs.getString("pName");
				String pType 	 = rs.getString("pType");
				String pFilename = rs.getString("pFilename");
				String pOrign 	 = rs.getString("pOrign");
				String pWeight   = rs.getString("pWeight");
				String pPart 	 = rs.getString("pPart");
				String pUse		 = rs.getString("pUse");
				int    pPrice    = rs.getInt("pPrice");
				int    pStock    = rs.getInt("pStock");
				Date   pRdate    = rs.getDate("pRdate");
				
				pDto = new ProductDto(pCode, pName, pType, pFilename, pOrign, pWeight, pPart, pUse, pPrice, pStock, pRdate);
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage()+"뭐가 문제야?");
			System.out.println(pDto.toString());
		}finally {
			try {
				if(rs   != null) rs.close();
				if(pstmt!= null) pstmt.close();
				if(conn != null) conn.close();
			}catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return pDto;
	}
	//상품 수정
	public int productUpdate(String pName, String pType, String pFilename, String pOrign, String pWeight, String pPart, String pUse, int pPrice, int pStock, String pCode)  {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE PRODUCT SET" + 
				"    PNAME=?, PTYPE=?,PFILENAME=?,PORIGN=?,PWEIGHT=?,PPART=?,PUSE=?,PPRICE=?,PSTOCK=(PSTOCK+?) WHERE PCODE=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,pName);
			pstmt.setString(2,pType);
			pstmt.setString(3,pFilename);
			pstmt.setString(4,pOrign);
			pstmt.setString(5,pWeight);
			pstmt.setString(6,pPart);
			pstmt.setString(7,pUse);
			pstmt.setInt(8, pPrice);
			pstmt.setInt(9, pStock);
			pstmt.setString(10,pCode );
			
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "상품수정성공":"상품수정실패");
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
	
	//상품 삭제
	
	public int productDelte(String pCode) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM PRODUCT WHERE PCODE=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pCode);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "상품 삭제 성공":"상품 삭제 실패");
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
	
	//상품 검색
	public ArrayList<ProductDto> productSearch(String pName, int startRow, int endRow){
		ArrayList<ProductDto> PDto = new ArrayList<ProductDto>();
		Connection        conn = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM" + 
				"    (SELECT * FROM PRODUCT WHERE PNAME LIKE '%' ||?||'%' ORDER BY PRDATE DESC)A) WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, pName);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String pCode 	 = rs.getString("pCode");
				pName = rs.getString("pName");
				String pType	 = rs.getString("pType");
				String pFilename = rs.getString("pFilename");
				String pOrign 	 = rs.getString("pOrign");
				String pWeight   = rs.getString("pWeight");
				String pPart 	 = rs.getString("pPart");
				String pUse		 = rs.getString("pUse");
				int    pPrice    = rs.getInt("pPrice");
				int    pStock    = rs.getInt("pStock");
				Date   pRdate    = rs.getDate("pRdate");
				
				PDto.add(new ProductDto(pCode, pName, pType, pFilename, pOrign, pWeight, pPart, pUse, pPrice, pStock, pRdate));
				
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage()+PDto.toString());
		}finally {
			try {
				if(rs   != null) rs.close();
				if(pstmt!= null) pstmt.close();
				if(conn != null) conn.close();
			}catch (SQLException e) {
				System.out.println(e.getMessage()+2);
			}
		}
		return PDto;
	}
	
	// 상품 이름별 개수
	public int productNameTotCnt(String pName) {
		int totCnt = 0;
		Connection        conn = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT COUNT(*) FROM PRODUCT where PNAME LIKE '%' ||?||'%'";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totCnt = rs.getInt(1);
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs   != null) rs.close();
				if(pstmt!= null) pstmt.close();
				if(conn != null) conn.close();
			}catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return totCnt;
	}
	
	//주문시 상품 재고 변경
	
	public int productStockUpdate(int pStock, String pCode)  {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE PRODUCT SET PSTOCK=PSTOCK-? WHERE PCODE=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pStock);
			pstmt.setString(2, pCode);
			
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "재고수량변경":"재고수량실패");
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
