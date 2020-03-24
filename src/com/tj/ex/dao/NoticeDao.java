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

import com.tj.ex.dto.NoticeDto;

public class NoticeDao {
	public static final int FAIL    =0;
	public static final int SUCCESS = 1;
	
	private static NoticeDao instance = new NoticeDao();
	public static NoticeDao getInstance() {
		return instance;
	}
	private NoticeDao() {}
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
	
	// 글목록
	public ArrayList<NoticeDto> notice_list(int startRow, int endRow){
		ArrayList<NoticeDto> nDto = new ArrayList<NoticeDto>();
		Connection        conn = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM" + 
				"    (SELECT N.*, ANAME FROM NOTICE_BOARD N, ADMIN A WHERE A.AID=N.AID ORDER BY NNO DESC)A)" + 
				"    WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int nNo 		= rs.getInt("nNo");
				String aId 		= rs.getString("aId");
				String aName    = rs.getString("aName");
				String nTitle 	= rs.getString("nTitle");
				String nContent = rs.getString("nContent");
				Date   nRdate 	= rs.getDate("nRdate");
				int nHit 		= rs.getInt("nHit");				
				nDto.add(new NoticeDto(nNo, aId, aName, nTitle, nContent, nRdate, nHit));
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
		
		return nDto;		
	}
	// 글 개수 
	public int notice_TotCnt() {
		int totCnt = 0;
		Connection        conn = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT COUNT(*) FROM NOTICE_BOARD";
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
	//글 쓰기
	public int notice_write(String aId, String nTitle, String nContent)  {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO  NOTICE_BOARD (NNO, AID, NTITLE, NCONTENT) " + 
				"    VALUES (NOTICE_BOARD_SEQ.NEXTVAL, ?, ?, ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aId);
			pstmt.setString(2, nTitle);
			pstmt.setString(3, nContent);
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
	//조회수 올리기
	public void hitUp(int fId) {
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE NOTICE_BOARD SET NHIT = NHIT+1 WHERE NNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fId);
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
	// NNO로 글 상세정보 
		public NoticeDto notice_contentView(int nNo) {
			
			NoticeDto nDto = null;
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			ResultSet         rs    = null;
			String sql = "SELECT N.*, ANAME FROM NOTICE_BOARD N, ADMIN A WHERE A.AID=N.AID AND NNO=?";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, nNo);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					String aId 		= rs.getString("aId");
					String nTitle 	= rs.getString("nTitle");
					String aName    = rs.getString("aName");
					String nContent = rs.getString("nContent");
					Date   nRdate 	= rs.getDate("nRdate");
					int nHit 		= rs.getInt("nHit");		
					nDto = new NoticeDto(nNo, aId, aName, nTitle, nContent, nRdate, nHit);
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
			return nDto;
		}
	
	//글 수정을 위한 NNO로 글 dto정보 
	public NoticeDto notice_modifyView(int nNo) {
		
		NoticeDto nDto = null;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT N.*, ANAME FROM NOTICE_BOARD N, ADMIN A WHERE A.AID=N.AID AND NNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String aId 		= rs.getString("aId");
				String nTitle 	= rs.getString("nTitle");
				String aName    = rs.getString("aName");
				String nContent = rs.getString("nContent");
				Date   nRdate 	= rs.getDate("nRdate");
				int nHit 		= rs.getInt("nHit");		
				nDto = new NoticeDto(nNo, aId, aName, nTitle, nContent, nRdate, nHit);
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
		return nDto;
	}
	
	//글 수정하기 
	public int modify(int nNo, String nTitle, String nContent) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE NOTICE_BOARD SET NTITLE=?,NCONTENT=?, NRDATE=SYSDATE WHERE NNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, nTitle);
			pstmt.setString(2, nContent);
			pstmt.setInt(3, nNo);
			result = pstmt.executeUpdate();
			
			
			System.out.println(result==FAIL? "글수정 성공":"글수정 실패");
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
	public int notice_delete(int nNo) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM NOTICE_BOARD WHERE NNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nNo);
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














