package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Program2 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		String ID = "9";
		String title = "TEST2";
		String writerId = "newlecz";
		String content = "hahaha";
		String files = "";
		
		
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String sql = "INSERT INTO notice (ID, title, WRITER_ID, CONTENT, FILES) VALUES (?,?,?,?,?)";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url,"scott","tiger");
//		Statement st = con.createStatement();
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, ID);
		st.setString(2, title);
		st.setString(3, writerId);
		st.setString(4, content);
		st.setString(5, files);
		
		int result = st.executeUpdate();
		
		System.out.println(result);
		
		
//		ResultSet rs = st.executeQuery(sql);
//		INSERT UPDATE DELETE를 할 경우에는 
//		ResultSet rs = st.executeUpdate(sql); 를 사용한다.
	
		
		
		st.close();
		con.close();

	}

}
