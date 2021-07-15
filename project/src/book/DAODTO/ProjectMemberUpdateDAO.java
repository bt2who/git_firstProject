package book.DAODTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ProjectMemberUpdateDAO {
	private static ProjectMemberUpdateDAO instance;
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "c##java";
	private String password = "bit";
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	
	public ProjectMemberUpdateDAO() {
		try {
			//driver loading
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	} //MemberUpdateDAO()
	
	
	public static ProjectMemberUpdateDAO getInstance() {
		if(instance == null) {
			instance = new ProjectMemberUpdateDAO();
		}
		return instance;
	} //getInstance()
	
	
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	} //getConnection()
	
//------------------------------------------------------------	

	public void updateAddress(ProjectMemberDTO memberDTO) {
		
		getConnection();

		String sql = "update memberInfo set address = ? where id = ? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberDTO.getAddress());
			pstmt.setString(2, memberDTO.getId());
			
			
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	} //updateName(MemberDTO memberDTO)
	
////------------------------------------------------------------	
	
	public void updatePhone(ProjectMemberDTO memberDTO) {
		
		getConnection();

		String sql = "update memberInfo set phone = ? where id = ? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberDTO.getPhone());
			pstmt.setString(2, memberDTO.getId());
			
			
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	} //updateName(MemberDTO memberDTO)
	
//------------------------------------------------------------
	
	public void updatePwd(ProjectMemberDTO memberDTO) {
		
		getConnection();

		String sql = "update memberInfo set pwd = ? where id = ? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberDTO.getPwd());
			pstmt.setString(2, memberDTO.getId());
			
			
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	} //updatePwd(MemberDTO memberDTO)
	
//------------------------------------------------------------

	public void updateName(ProjectMemberDTO memberDTO) {
		
		getConnection();

		String sql = "update memberInfo set name = ? where id = ? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberDTO.getName());
			pstmt.setString(2, memberDTO.getId());
			
			
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	} //updateName(MemberDTO memberDTO)
	
//------------------------------------------------------------
	
	public void updateEmail(ProjectMemberDTO memberDTO) {
	
		getConnection();

		String sql = "update memberInfo set age = ? where id = ? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberDTO.getAge());
			pstmt.setString(2, memberDTO.getId());
			
			
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	} //updateEmail(MemberDTO memberDTO)
	

}
