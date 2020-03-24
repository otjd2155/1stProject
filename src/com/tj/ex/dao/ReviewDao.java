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

import com.tj.ex.dto.ReviewDto;

public class ReviewDao {
	public static final int FAIL    =0;
	public static final int SUCCESS = 1;
	
	private static ReviewDao instance = new ReviewDao();
	public static ReviewDao getInstance() {
		return instance;
	}
	private ReviewDao() {}
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
	
	// 글목록 불러오기 (페이징)
	public ArrayList<ReviewDto> reviewList(int startRow, int endRow){
		ArrayList<ReviewDto> rDto = new ArrayList<ReviewDto>();
		Connection        conn = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql ="SELECT * FROM (SELECT ROWNUM RN, A.* FROM" + 
				"    (SELECT R.*,M.MNAME,P.PFILENAME, P.PNAME, P.PPRICE " + 
				"    FROM REVIEW R, MEMBER M, PRODUCT P" + 
				"    WHERE R.MID=M.MID AND R.PCODE=P.PCODE" + 
				"    ORDER BY RRDATE DESC)A) " + 
				"    WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int rNo = rs.getInt("rNo");
				String mId = rs.getString("mId");
				String pCode = rs.getString("pCode");
				String rTitle = rs.getString("rTitle");
				String rContent = rs.getString("rContent");
				String rFilename = rs.getString("rFilename");
				int rHit = rs.getInt("rHit");
				Date rRdate = rs.getDate("rRdate");
				
				String mName = rs.getString("mName");
				String pFilename = rs.getString("pFilename");
				String pName = rs.getString("pName");
				int pPrice = rs.getInt("pPrice");
				rDto.add(new ReviewDto(rNo, mId, pCode, rTitle, rContent, rFilename, rHit, rRdate, mName, pFilename, pName, pPrice));
				
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
		
		return rDto;		
	}
	// 상품상세 페이지 밑에 리뷰 글 
	public ArrayList<ReviewDto> reviewProList(String pCode, int startRow, int endRow){
		ArrayList<ReviewDto> rDto = new ArrayList<ReviewDto>();
		Connection        conn = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM" + 
				"    (SELECT R.*,M.MNAME,P.PFILENAME, P.PNAME, P.PPRICE " + 
				"    FROM REVIEW R, MEMBER M, PRODUCT P " + 
				"    WHERE R.MID=M.MID AND R.PCODE=P.PCODE AND R.PCODE =?" + 
				"    ORDER BY RRDATE DESC)A) " + 
				"    WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pCode);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int rNo = rs.getInt("rNo");
				String mId = rs.getString("mId");
				String rTitle = rs.getString("rTitle");
				String rContent = rs.getString("rContent");
				String rFilename = rs.getString("rFilename");
				int rHit = rs.getInt("rHit");
				Date rRdate = rs.getDate("rRdate");
				
				String mName = rs.getString("mName");
				String pFilename = rs.getString("pFilename");
				String pName = rs.getString("pName");
				int pPrice = rs.getInt("pPrice");
				rDto.add(new ReviewDto(rNo, mId, pCode, rTitle, rContent, rFilename, rHit, rRdate, mName, pFilename, pName, pPrice));
				
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
		
		return rDto;		
	}
	//상품당 리뷰 글개수
	public int reviewProTotCnt(String pCode) {
		int totCnt = 0;
		Connection        conn = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT COUNT(*) FROM REVIEW R, PRODUCT P WHERE R.PCODE=P.PCODE AND R.PCODE =?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pCode);
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
	
	
	//글 개수
	public int reviewTotCnt() {
		int totCnt = 0;
		Connection        conn = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT COUNT(*) FROM REVIEW";
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
	// 조회수 올리기
	public void reviewHitUp(int rNo) {
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE REVIEW SET RHIT= RHIT+1 WHERE RNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rNo);
			pstmt.executeUpdate();
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
	}
	
	
	//상세 보기 
	
	public ReviewDto reviewContentView(int rNo) {
		reviewHitUp(rNo);
		ReviewDto 		  rDto  = null;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT R.*,M.MNAME,P.PFILENAME, P.PNAME, P.PPRICE " + 
				"    FROM REVIEW R, MEMBER M, PRODUCT P " + 
				"    WHERE R.MID=M.MID AND R.PCODE=P.PCODE AND RNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String mId = rs.getString("mId");
				String pCode = rs.getString("pCode");
				String rTitle = rs.getString("rTitle");
				String rContent = rs.getString("rContent");
				String rFilename = rs.getString("rFilename");
				int rHit = rs.getInt("rHit");
				Date rRdate = rs.getDate("rRdate");
				
				String mName = rs.getString("mName");
				String pFilename = rs.getString("pFilename");
				String pName = rs.getString("pName");
				int pPrice = rs.getInt("pPrice");
				rDto = new ReviewDto(rNo, mId, pCode, rTitle, rContent, rFilename, rHit, rRdate, mName, pFilename, pName, pPrice);
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
		return rDto;
	}
	
	// 글쓰기
	
	public int reviewWrite(String mId, String pCode, String rTitle, String rContent, String rFilename)  {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO REVIEW (RNO, MID, PCODE, RTITLE, RCONTENT, RFILENAME)" + 
				"    VALUES (REVIEW_SEQ.NEXTVAL, ?,?,?,?,?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, pCode);
			pstmt.setString(3, rTitle);
			pstmt.setString(4, rContent);
			pstmt.setString(5, rFilename);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "글쓰기성공":"글쓰기실패");
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
	
	
	//글수정하기 
	public int reviewModify(String rTitle, String rContent, String rFilename, int rNo) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE REVIEW SET RTITLE=?,RCONTENT= ?,RFILENAME=?, RRDATE=SYSDATE WHERE RNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, rTitle);
			pstmt.setString(2, rContent);
			pstmt.setString(3, rFilename);
			pstmt.setInt(4, rNo);
			
			result = pstmt.executeUpdate();
			
			System.out.println(result==SUCCESS? "글수정 성공":"글수정 실패");
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
	
	//글 삭제하기
	public int reviewDelete(int rNo) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM REVIEW WHERE RNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rNo);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "글삭제 성공":"글삭제 실패");
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
