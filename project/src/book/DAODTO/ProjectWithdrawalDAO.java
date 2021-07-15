package book.DAODTO;

import java.sql.SQLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ProjectWithdrawalDAO {
	private static ProjectWithdrawalDAO instance;
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "c##java";
	private String password = "bit";
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public ProjectWithdrawalDAO() {
		try {
			//driver loading
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	} //WithdrawalDAO()
	
	
	public static ProjectWithdrawalDAO getInstance() {
		if(instance == null) {
			instance = new ProjectWithdrawalDAO();
		}
		return instance;
		
	}
	
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	} //getConnection()
	
	
	public void deleteMember(ProjectMemberDTO memberDTO) {
		getConnection();
		String sql = "delete memberInfo where id=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberDTO.getId());
			
			//실행
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // 에러가 있건 없건 수행
			try {
				if (pstmt != null)
					pstmt.close(); // 끊어야 함
				if (conn != null)
					conn.close(); // 거꾸로 소멸
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // finally
		
	} //deleteMember(string id)
	

}
