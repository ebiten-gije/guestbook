package himedia.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GuestbookDaoOI implements GuestbookDao {

	private String dbuser;
	private String dbpass;
	
	public GuestbookDaoOI (String dbuser, String dbpass) {
		this.dbuser = dbuser;
		this.dbpass = dbpass;
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(dburl, dbuser, dbpass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	@Override
	public List <GuestbookVo> getList (){
		Connection conn =null;
		Statement stmt = null;
		ResultSet rs = null;
		
		List<GuestbookVo> list = new ArrayList<>();
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			String sql = "Select * from guestbook order by reg_date desc";
			
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Long no = rs.getLong("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String content = rs.getString("content");
				Date regDate = rs.getDate("reg_date");
				
				GuestbookVo vo = new GuestbookVo(no, name, password, content, regDate);
				
				list.add(vo);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	@Override
	public boolean add(GuestbookVo vo) {
		Connection conn =null;
		PreparedStatement pstmt = null;
		int addedCount = 0;
		
		try {
			//	connection 획득
			conn = getConnection();
			//	실행계획
			String sql = "insert into guestbook (no, name, password, content, reg_date) values" + 
			" (seq_guestbook_no.nextval, ?, ?, ?, sysdate)";
			
			pstmt = conn.prepareStatement(sql);
			
			//	데이터 바인딩
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getContent());
			
			addedCount = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
				
		}
		return 1 == addedCount;
	}
	
	@Override
	public boolean del(Long no, String password) {
		Connection conn =null;
		PreparedStatement pstmt = null;
		int deletedCount = 0;
		
		try {
			conn = getConnection();
			String sql = "delete from guestbook where no = ? and password = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			//	데이터 바인딩
			pstmt.setLong(1, no);
			pstmt.setString(2, password);

			
			deletedCount = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
				
		}
		return 1 == deletedCount;
	}
	
	@Override
	public GuestbookVo search(Long no, String password) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		GuestbookVo vo = null;
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			String sql = "Select * from guestbook where no = " + no + " and password = " + password;
			
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Long no2 = rs.getLong("no");
				String name = rs.getString("name");
				String password2 = rs.getString("password");
				String content = rs.getString("content");
				Date regDate = rs.getDate("reg_date");
				
				vo = new GuestbookVo(no2, name, password2, content, regDate);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
				
		}
		return vo;
	}
	
	@Override
	public boolean update(Long no, String content) {
		Connection conn =null;
		PreparedStatement pstmt = null;
		int updatedCount = 0;
		
		try {
			conn = getConnection();
			String sql = "update guestbook set content = ? where no = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			//	데이터 바인딩
			pstmt.setString(1, content);
			pstmt.setLong(2, no);
			
			updatedCount = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
				
		}
		return 1 == updatedCount;
	}
}
