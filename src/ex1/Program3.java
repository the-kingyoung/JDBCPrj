package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Program3 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		String title = "TEST3";
		String content = "hahaha3";
		String files = "";
		int id = 9;
		
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String sql = "UPDATE NOTICE "
				+ "SET "
				+ "	TITLE =?,"
				+ "	CONTENT =?,"
				+ "	FILES =?"
				+ "WHERE ID=?";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url,"scott","tiger");
//		Statement st = con.createStatement();
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, title);
		st.setString(2, content);
		st.setString(3, files);
		st.setInt(4, id);
		
		int result = st.executeUpdate();
		
		System.out.println(result);
		
		
//		ResultSet rs = st.executeQuery(sql);
//		INSERT UPDATE DELETE를 할 경우에는 
//		ResultSet rs = st.executeUpdate(sql); 를 사용한다.
	
		
		
		st.close();
		con.close();

	}

}
