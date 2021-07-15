package book.DAODTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import book.DAODTO.ProjectBookDTO;
import book.DAODTO.ProjectBookOrderDTO;

public class ProjectKeepOrderDAO {
	private static ProjectKeepOrderDAO instance;
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "c##java";
	private String password = "bit";
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	
	public ProjectKeepOrderDAO() {
		try {
			//driver loading
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static ProjectKeepOrderDAO getInstance() {
		if(instance == null) {
			instance = new ProjectKeepOrderDAO();
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
	
	
	public void inputKeepOrder(ProjectBookDTO bookDTO, int volume) {
		getConnection();
		String sql = "insert into cart values(?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bookDTO.getBookName());
			pstmt.setInt(2, bookDTO.getBookPrice());
			pstmt.setInt(3, volume);
			pstmt.setString(4, bookDTO.getMemberId());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
				try {
					if(pstmt!=null)	pstmt.close();
					if(conn!=null)	conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
		}
		
	}//inputkeeporder()
	
	public List<ProjectBookOrderDTO> getKeepOrderList(String memberId){
		List<ProjectBookOrderDTO> list = new ArrayList<ProjectBookOrderDTO>();
		getConnection();
		String sql = "select * from cart where memberId = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProjectBookOrderDTO bookOrderDTO = new ProjectBookOrderDTO();
				bookOrderDTO.setBookName(rs.getString("bookname"));
				bookOrderDTO.setBookPrice(rs.getInt("bookPrice"));
				bookOrderDTO.setBookVolume(rs.getInt("bookVolume"));
				bookOrderDTO.setMemberId(rs.getString("memberId"));
				
				list.add(bookOrderDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

}
