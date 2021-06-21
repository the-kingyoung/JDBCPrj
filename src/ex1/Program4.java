package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Program4 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		int id = 9;
		
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String sql = "DELETE NOTICE WHERE ID=?";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url,"scott","tiger");
//		Statement st = con.createStatement();
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, id);
		
		int result = st.executeUpdate();
		
		System.out.println(result);
		
		
//		ResultSet rs = st.executeQuery(sql);
//		INSERT UPDATE DELETE를 할 경우에는 
//		ResultSet rs = st.executeUpdate(sql); 를 사용한다.
	
		
		
		st.close();
		con.close();

	}

}
