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

import com.tj.ex.dto.QnaDto;

public class QnaDao {
	public static final int FAIL    =0;
	public static final int SUCCESS = 1;
	
	private static QnaDao instance = new QnaDao();
	public static QnaDao getInstance() {
		return instance;
	}
	private QnaDao() {}
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
	
	//글 목록 뿌리기 
	public ArrayList<QnaDto> qnaList(int startRow, int endRow){
		ArrayList<QnaDto> qDto = new ArrayList<QnaDto>();
		Connection        conn = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM " + 
				"    (SELECT QNO, (SELECT MNAME FROM QNA Q, MEMBER M " + 
				"        WHERE Q.MID=M.MID AND QNA.QNO=QNO) ||" + 
				"    (SELECT ANAME FROM QNA Q, ADMIN M " + 
				"        WHERE Q.AID=M.AID AND QNA.QNO=QNO) WRITER, MID, AID, " + 
				"        QTITLE, QCONTENT, QFILENAME1, QFILENAME2, QGROUP, QSTEP, QINDENT, QHIT, QRDATE" + 
				"        FROM QNA ORDER BY QGROUP DESC, QSTEP)A )" + 
				"        WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int    qNo		  = rs.getInt("qNo");
				String mId		  = rs.getString("mId");
				String aId		  = rs.getString("aId");
				String writer	  = rs.getString("writer");
				
				String qtitle 	  = rs.getString("qtitle");
				String qContent   = rs.getString("qContent");
				String qFilename1 = rs.getString("qFilename1");
				String qFilename2 = rs.getString("qFilename2");
				int    qGroup	  = rs.getInt("qGroup");
				int    qStep	  = rs.getInt("qStep");
				int    qIndent	  = rs.getInt("qIndent");
				int    qHit		  = rs.getInt("qHit");
				Date   qRdate	  = rs.getDate("qRdate");
				qDto.add(new QnaDto(qNo, mId, aId, writer, qtitle, qContent, qFilename1, qFilename2, qGroup, qStep, qIndent, qHit, qRdate));
				
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
		
		return qDto;		
	}	
	
	// 글 개수
	public int qnaTotCnt() {
		int totCnt = 0;
		Connection        conn = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT COUNT(*) FROM QNA";
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
	
	// 글 조회수 올리기
	public void qnaHitUp(int qNo) {
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE QNA SET QHIT = QHIT+1 WHERE QNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qNo);
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
	
	
	// 글 상세보기
	public QnaDto qNaContentView(int qNo) {
		qnaHitUp(qNo);
		QnaDto 		  	  qDto  = null;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT QNO, (SELECT MNAME FROM QNA Q, MEMBER M WHERE Q.MID=M.MID AND QNA.QNO=QNO) ||" + 
				"    (SELECT ANAME FROM QNA Q, ADMIN M WHERE Q.AID=M.AID AND QNA.QNO=QNO) WRITER, MID, AID," + 
				"   QTITLE, QCONTENT, QFILENAME1, QFILENAME2, QGROUP, QSTEP, QINDENT, QHIT, QRDATE" + 
				"   FROM QNA WHERE QNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String mId		  = rs.getString("mId");
				String aId		  = rs.getString("aId");
				String writer	  = rs.getString("writer");
				
				String qtitle 	  = rs.getString("qtitle");
				String qContent   = rs.getString("qContent");
				String qFilename1 = rs.getString("qFilename1");
				String qFilename2 = rs.getString("qFilename2");
				int    qGroup	  = rs.getInt("qGroup");
				int    qStep	  = rs.getInt("qStep");
				int    qIndent	  = rs.getInt("qIndent");
				int    qHit		  = rs.getInt("qHit");
				Date   qRdate	  = rs.getDate("qRdate");
				
				qDto = new QnaDto(qNo, mId, aId, writer, qtitle, qContent, qFilename1, qFilename2, qGroup, qStep, qIndent, qHit, qRdate);   
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
		return qDto;
	}
	
	// 글쓰기 (원글)
	
	public int qNaWrite(String mId, String aId, String qtitle, String qContent, String qFilename1, String qFilename2)  {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO QNA (QNO, MID, AID, QTITLE, QCONTENT, QFILENAME1, QFILENAME2, QGROUP, QSTEP, QINDENT)" + 
				"    VALUES (QNA_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, QNA_SEQ.CURRVAL, 0, 0 )";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, aId);
			pstmt.setString(3, qtitle);
			pstmt.setString(4, qContent);
			pstmt.setString(5, qFilename1);
			pstmt.setString(6, qFilename2);
			
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
	
	// 글 수정
	public int qnaModify(String qtitle, String qContent, String qFilename1, String qFilename2, int qNo) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE QNA SET QTITLE=?, QCONTENT=?, QFILENAME1=?, QFILENAME2=?, QRDATE=SYSDATE" + 
				"    WHERE QNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, qtitle);
			pstmt.setString(2, qContent);
			pstmt.setString(3, qFilename1);
			pstmt.setString(4, qFilename2);
			pstmt.setInt(5, qNo);
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
	
	// 글 삭제
	public int qnaDelete(int qNo) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM QNA WHERE QNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qNo);
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
	// 답변글 쓰기 전 step a
	private void qnaReplyStepA(int qGroup, int qStep) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE QNA SET QSTEP = QSTEP+1 WHERE QGROUP=? AND QSTEP>?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qGroup);
			pstmt.setInt(2, qStep);
			int result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "기존답변글들 밑으로":"답변 처음이네");
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
	
	// 답글쓰기
	public int qnaReply(String mId, String aId, String qtitle, String qContent, 
				String qFilename1, String qFilename2, int qGroup, int qStep, int qIndent)  {
		int result = FAIL;
		qnaReplyStepA(qGroup, qStep);
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO QNA " + 
				"    (QNO, MID, AID, QTITLE, QCONTENT, QFILENAME1, QFILENAME2, QGROUP, QSTEP, QINDENT)" + 
				"    VALUES (QNA_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, aId);
			pstmt.setString(3, qtitle);
			pstmt.setString(4, qContent);
			pstmt.setString(5, qFilename1);
			pstmt.setString(6, qFilename2);
			pstmt.setInt(7, qGroup);
			pstmt.setInt(8, qStep+1);
			pstmt.setInt(9, qIndent+1);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "답글쓰기성공":"답글쓰기실패");
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




















