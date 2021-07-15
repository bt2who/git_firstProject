package book.DAODTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.TableModel;


public class ProjectBookOrderDAO {
	private static ProjectBookOrderDAO instance;
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "c##java";
	private String password = "bit";
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	
	public ProjectBookOrderDAO() {
		try {
			//driver loading
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	} //BookOrderDAO()
	
	
	public static ProjectBookOrderDAO getInstance() {
		if(instance == null) {
			instance = new ProjectBookOrderDAO();
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
	

	public int getSeq() {
		int seq = 0;
		getConnection();
		String sql = "select seq.nextval from dual";
		
		try {
			pstmt=conn.prepareStatement(sql);
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
		
	} //getSeq
		
	//-----------------------------------------------------
	public void inputOrderBook(ProjectBookDTO bookDTO) {
		//int su = 0;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
		getConnection();
		String sql = "insert into orderlist values(?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bookDTO.getMemberId());
			pstmt.setString(2, sdf.format(date));
			pstmt.setString(3, bookDTO.getBookName());
			pstmt.setString(4, bookDTO.getWriter());
			pstmt.setString(5, bookDTO.getBookType());
			pstmt.setString(6, bookDTO.getPublisher());
			pstmt.setInt(7, bookDTO.getBookPrice());
			pstmt.setInt(8, bookDTO.getBookVolume());
			
			pstmt.executeUpdate();//실행
			
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
		
		
	}//inputOrderBook(BookDTO bookDTO)
	
	
	

	
	public List<ProjectBookOrderDTO> getBookOrderList(String loginId) {
		List<ProjectBookOrderDTO> list = new ArrayList<ProjectBookOrderDTO>();
		String sql;
		//접속
		getConnection();
		try {
			//생성
			if(loginId.equals("admin")) {
				sql = "select * from orderlist order by orderDate";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
			}else {
				sql = "select * from orderlist where memberId = ? order by orderDate";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, loginId);
				//실행
				rs = pstmt.executeQuery();
				
			}
			while(rs.next()) {
				ProjectBookOrderDTO bookOrderDTO = new ProjectBookOrderDTO();
				
				bookOrderDTO.setOrderDate(rs.getString("orderdate"));
				bookOrderDTO.setBookName(rs.getString("bookname"));
				bookOrderDTO.setWriter(rs.getString("writer"));
				bookOrderDTO.setBookType(rs.getString("bookType"));
				bookOrderDTO.setPublisher(rs.getString("bookName"));
				bookOrderDTO.setBookPrice(rs.getInt("bookPrice"));
				bookOrderDTO.setBookVolume(rs.getInt("bookVolume"));			
				list.add(bookOrderDTO);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			list = null;
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} //finally		
		
		return list;
		
	} //getBookOrderList()


//---------------------------------------------------------------------------
	public void qntInput(ProjectCartDTO cartDTO,String loginId) { //장바구니로 값을 보냄!
		String sql;
	    String bookName = null;
		String memberId = null;
		getConnection();
		
		sql = "select * from cart";
	      try {
	         pstmt = conn.prepareStatement(sql);
	         rs = pstmt.executeQuery();

	         while (rs.next()) {
	            if (cartDTO.getBookName().equals(rs.getString("bookname"))) {
	               bookName = cartDTO.getBookName();
	               
	            }
	            if(cartDTO.getMemberID().equals(rs.getString("memberId")) && !cartDTO.getBookName().equals(rs.getString("bookname"))) {
	                  memberId = cartDTO.getMemberID();
	            }
	         }
	      } catch (SQLException e1) {
	         e1.printStackTrace();
	      } finally {
	         try {
	            if (rs != null)
	               rs.close();
	            if (pstmt != null)
	               pstmt.close();
	            if (conn != null)
	               conn.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
	    Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
		
		getConnection();//접속
		if(bookName == null || memberId != null) {
			
			sql = "insert into cart values(?,?,?,?,?,?,?,?) ";
		try {
			pstmt = conn.prepareStatement(sql);//번역
			pstmt.setString(1, cartDTO.getMemberID());//아이디
			pstmt.setString(2, sdf.format(date));
			pstmt.setString(3, cartDTO.getBookName());//제목
			pstmt.setString(4, cartDTO.getWriter());//저자
			pstmt.setString(5, cartDTO.getBookType());//장르
			pstmt.setString(6, cartDTO.getPublisher());
			pstmt.setInt(7, cartDTO.getBookPrice());
			pstmt.setInt(8, cartDTO.getBookVolume());
			//출판사
			//가격
			//수량
			
			pstmt.executeUpdate();//실행
							
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}//finally	
		}	else {
			  sql = "update cart set bookVolume = bookVolume + ? where bookName = ?";
		         try {
		            pstmt = conn.prepareStatement(sql);
		            pstmt.setInt(1,cartDTO.getBookVolume());
		            pstmt.setString(2,bookName);
		            
		            pstmt.executeUpdate();
		            
		         } catch (SQLException e) {
		            e.printStackTrace();
		         }finally {
		            try {
		               if (pstmt != null)
		                  pstmt.close();
		               if (conn != null)
		                  conn.close();
		            } catch (SQLException e) {
		               e.printStackTrace();
		            }
		         } 
		         
		      }		
	}//qntInput()

//---------------------------------------------------------------------------------
	public List<ProjectBookOrderDTO> dailySale(){
		List<ProjectBookOrderDTO> list = new ArrayList<ProjectBookOrderDTO>();
		getConnection();
		String sql = "select * from orderlist where orderDate = ?  order by bookVolume desc, bookPrice desc";
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sdf.format(date));
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProjectBookOrderDTO bookOrderDTO = new ProjectBookOrderDTO();
				bookOrderDTO.setOrderDate(rs.getString("orderdate"));
				bookOrderDTO.setBookName(rs.getString("bookname"));
				bookOrderDTO.setWriter(rs.getString("writer"));
				bookOrderDTO.setBookType(rs.getString("bookType"));
				bookOrderDTO.setPublisher(rs.getString("publisher"));
				bookOrderDTO.setBookPrice(rs.getInt("bookPrice"));
				bookOrderDTO.setBookVolume(rs.getInt("bookVolume"));
				bookOrderDTO.setMemberId(rs.getString("memberId"));
				
				list.add(bookOrderDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
				try {
					if(rs!=null)	rs.close();
					if(pstmt!=null)	pstmt.close();
					if(conn!=null)	conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return list;
	}
	public List<ProjectBookOrderDTO> monthlySale(String year, String month){
		List<ProjectBookOrderDTO> list = new ArrayList<ProjectBookOrderDTO>();
		getConnection();
		String sql = "select * from orderlist where orderDate like ? order by bookVolume desc, bookPrice desc";
	
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, year+"/"+month+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProjectBookOrderDTO bookOrderDTO = new ProjectBookOrderDTO();
				bookOrderDTO.setOrderDate(rs.getString("orderdate"));
				bookOrderDTO.setBookName(rs.getString("bookname"));
				bookOrderDTO.setWriter(rs.getString("writer"));
				bookOrderDTO.setBookType(rs.getString("bookType"));
				bookOrderDTO.setPublisher(rs.getString("publisher"));
				bookOrderDTO.setBookPrice(rs.getInt("bookPrice"));
				bookOrderDTO.setBookVolume(rs.getInt("bookVolume"));
				bookOrderDTO.setMemberId(rs.getString("memberId"));
				
				list.add(bookOrderDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			list = null;
		}finally {
				try {
					if(rs!=null)	rs.close();
					if(pstmt!=null)	pstmt.close();
					if(conn!=null)	conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return list;
	}
		
}
