package com.newlecture.app.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.newlecture.app.entuty.Notice;

public class NoticeService {
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String uid = "scott";
	private String pwd = "tiger";
	private String driver = "oracle.jdbc.driver.OracleDriver";

	public List<Notice> getList() throws ClassNotFoundException, SQLException{
		
		String sql = "SELECT * FROM NOTICE";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url,uid,pwd);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		List<Notice> list = new ArrayList<Notice>();
		
		while(rs.next()) {
			int id = rs.getInt("ID");
			String title = rs.getString("TITLE");
			String writer = rs.getString("WRITER_ID");
			String content = rs.getString("CONTENT");
			Date regdate =  rs.getDate("REGDATE");
			int hit = rs.getInt("HIT");
			String files = rs.getString("FILES");
			
			Notice notice = new Notice(
					id,
					title,
					writer,
					content,
					regdate,
					hit,
					files
					
					);
			
			list.add(notice);
		}
		rs.close();
		st.close();
		con.close();
		
		return list;
	}

	public int insert(Notice notice) throws SQLException, ClassNotFoundException {
		
		int ID = notice.getId();
		String title = notice.getTitle();
		String writerId = notice.getWriter();
		String content = notice.getContent();
		String files = notice.getFiles();
		
		
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String sql = "INSERT INTO notice (ID, title, WRITER_ID, CONTENT, FILES) VALUES (?,?,?,?,?)";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url,uid,pwd);
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, ID);
		st.setString(2, title);
		st.setString(3, writerId);
		st.setString(4, content);
		st.setString(5, files);
		
		int result = st.executeUpdate();
		
		System.out.println(result);

		st.close();
		con.close();
		return result;
	}
	
	public int update(Notice notice) throws ClassNotFoundException, SQLException {
		
		String title = notice.getTitle();
		String content = notice.getContent();
		String files = notice.getFiles();
		int id = notice.getId();
		
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String sql = "UPDATE NOTICE "
				+ "SET "
				+ "	TITLE =?,"
				+ "	CONTENT =?,"
				+ "	FILES =?"
				+ "WHERE ID=?";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url,uid,pwd);
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, title);
		st.setString(2, content);
		st.setString(3, files);
		st.setInt(4, id);
		
		int result = st.executeUpdate();
		
		System.out.println(result);
		
		st.close();
		con.close();
		
		return result;
	}
	
	public int delete(int id) throws SQLException, ClassNotFoundException {
		
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String sql = "DELETE NOTICE WHERE ID=?";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url,uid,pwd);
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, id);
		
		int result = st.executeUpdate();

		st.close();
		con.close();
		
		return result;
	}
}
