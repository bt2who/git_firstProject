package book.DAODTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class ProjectCartDAO {
	private static ProjectCartDAO instance;
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "c##java";
	private String password = "bit";

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	
//접속--------------------------------------------------------------------------------------	
	public ProjectCartDAO() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}//CartDAO()
	
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//싱글톤 instance생성--------------------------------------------------------------------------	
	public static ProjectCartDAO getInstance() {
		if(instance == null) {
			instance = new ProjectCartDAO();
		}
		return instance;
	}
	
//seq값 얻어오기-----------------------------------------------------------------------------------	
	public int getSeq() {
		int seq = 0;
		getConnection();
		String sql = "select seq.nextval from dual";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			rs.next();
			rs.getInt(1);
			seq = rs.getInt(1);
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
		return seq;	
	}
	
//데이터입력---------------------------------------------------------------------------------------------		
	public void getCartList(ProjectBookOrderDTO bookOrderDTO){
		List<ProjectBookOrderDTO> list = new ArrayList<ProjectBookOrderDTO>();
		
		getConnection();
		
		
		String sql = "insert into cart values(?,?,?,?,?,?,?,?)"; //오더리스트디비
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString (1, bookOrderDTO.getMemberId());//아이디
			pstmt.setString (2, bookOrderDTO.getOrderDate());//오더날짜
			pstmt.setString(3, bookOrderDTO.getBookName());//책제목
			pstmt.setString(4, bookOrderDTO.getWriter());//지은이
			pstmt.setString(5, bookOrderDTO.getBookType());//장르
			pstmt.setString(6, bookOrderDTO.getPublisher());//출판사
			pstmt.setInt(7, bookOrderDTO.getBookPrice());//가격
			pstmt.setInt(8, bookOrderDTO.getBookVolume());//재고
			
			rs = pstmt.executeQuery();//실행
			
		} catch (SQLException e) {
			e.printStackTrace();
			list = null;
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
		//return list;
	}


//-------------------------------------------------------------------------------------------------

	public List<ProjectCartDTO> getCartList(String loginId) {//담은 데이터를 장바구니목록창으로 보내줌
		List<ProjectCartDTO> list = new ArrayList<ProjectCartDTO>();

		// 접속
		getConnection();
		String sql = "select * from cart where memberId = ? order by bookname";

		try {
			// 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginId);
			// 실행
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ProjectCartDTO cartDTO = new ProjectCartDTO();

				cartDTO.setBookName(rs.getString("bookname"));
				cartDTO.setWriter(rs.getString("writer"));
				cartDTO.setBookType(rs.getString("booktype"));
				cartDTO.setPublisher(rs.getString("publisher"));
				cartDTO.setBookPrice(rs.getInt("bookprice"));
				cartDTO.setBookVolume(rs.getInt("bookvolume"));
				list.add(cartDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			list = null;
		} finally {
			try {
				if (rs != null)	rs.close();
				if (pstmt != null)	pstmt.close();
				if (conn != null)	conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // finally
		
		

		return list;

	} // getCartList()

	public void deleteCart(ProjectCartDTO cartDTO, String loginId) {
		
		getConnection();
		String sql = "delete cart where memberId = ? and bookname=?";
		System.out.println(cartDTO.getBookName());
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginId);
			pstmt.setString(2, cartDTO.getBookName());
			
			//실행
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // 에러가 있건 없건 수행
			try {
				if (pstmt != null)	pstmt.close(); // 끊어야 함
				if (conn != null)	conn.close(); // 거꾸로 소멸
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // finally
			
	}
	
//-----------------------------------------------------------------------------------------------
	//TODO
	public void moveCart(ProjectCartDTO cartDTO, String loginId) {//장바구니담은 데이터를 주문목록디비에 입력함
		List<ProjectBookOrderDTO> list = new ArrayList<ProjectBookOrderDTO>();
		List<ProjectBookOrderDTO> bookList = new ArrayList<ProjectBookOrderDTO>();
	   // 접속
			getConnection();
			String sql;
			try {
				//cart에서 데이터 받아오기----------------------------------------
				sql = "select * from cart where memberId = ? and bookName = ?";

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, loginId);
				pstmt.setString(2, cartDTO.getBookName());

				rs = pstmt.executeQuery();
				while (rs.next()) {
					ProjectBookOrderDTO orderDTO = new ProjectBookOrderDTO();

					orderDTO.setMemberId(rs.getString("memberId"));
					orderDTO.setOrderDate(rs.getString("orderdate"));
					orderDTO.setBookName(rs.getString("bookName"));
					orderDTO.setWriter(rs.getString("writer"));
					orderDTO.setBookType(rs.getString("bookType"));
					orderDTO.setPublisher(rs.getString("publisher"));
					orderDTO.setBookPrice(rs.getInt("bookPrice"));
					orderDTO.setBookVolume(rs.getInt("bookVolume"));

					list.add(orderDTO);
				} // while
				//bookTable check---------------------------------------
				sql = "select * from bookTable where bookName = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, cartDTO.getBookName());
				rs = pstmt.executeQuery();
				while(rs.next()) {
					ProjectBookOrderDTO orderDTO = new ProjectBookOrderDTO();
					
					orderDTO.setBookName(rs.getString("bookName"));
					orderDTO.setWriter(rs.getString("writer"));
					orderDTO.setBookType(rs.getString("bookType"));
					orderDTO.setPublisher(rs.getString("publisher"));
					orderDTO.setBookPrice(rs.getInt("bookPrice"));
					orderDTO.setBookVolume(rs.getInt("bookVolume"));

					bookList.add(orderDTO);
				}
				for(int i=0; i<list.size(); i++) {
					if((bookList.get(i).getBookVolume()-list.get(i).getBookVolume()) < 0) {
						JOptionPane.showMessageDialog(null, "재고가 부족합니다");
						return;
					}
				}
				
				//bookTable update------------------------------------------------------
				sql = "update bookTable set bookVolume = bookVolume - ? where bookName =?";

				pstmt = conn.prepareStatement(sql);
				for (int i = 0; i < list.size(); i++) {
					pstmt.setInt(1, list.get(i).getBookVolume());
					pstmt.setString(2, list.get(i).getBookName());
					
					pstmt.executeUpdate();
				}
				
				//orderlist insert--------------------------------------------------
				sql = "insert into orderlist select * from cart where memberId = ? and bookName = ?"; // 아이디로
				// System.out.println(bookName);
				// 생성
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, loginId);
				pstmt.setString(2, cartDTO.getBookName());

				// 실행
				pstmt.executeUpdate();

//		         while (rs.next()) {
//		            //CartDTO cartDTO = new CartDTO();
//		            //BookOrderDTO bookOrderDTO = new BookOrderDTO();
//		            //cartDTO.setOrderDate(rs.getString("orderDate"));
//		        	cartDTO.setMemberID(rs.getString("memberId"));
//		            cartDTO.setBookName(rs.getString("BookName"));
//		            cartDTO.setWriter(rs.getString("Writer"));
//		            cartDTO.setBookType(rs.getString("BookType"));
//		            cartDTO.setPublisher(rs.getString("Publisher"));
//		            cartDTO.setBookPrice(rs.getInt("BookPrice"));
//		            cartDTO.setBookVolume(rs.getInt("BookVolume"));
	//
//		            list.add(cartDTO);
//		         }
			} catch (SQLException e) {
				e.printStackTrace();
				list = null;
			} finally {
				try {
//		            if (rs != null)	rs.close();
					if (pstmt != null)
						pstmt.close();
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} // finally

		} // moveCart()
	
}


















