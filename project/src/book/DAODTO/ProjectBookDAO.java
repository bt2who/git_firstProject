package book.DAODTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class ProjectBookDAO {
	private static ProjectBookDAO instance;
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "c##java";
	private String password = "bit";
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

//접속--------------------------------------------------------------------------------------
	public ProjectBookDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void getConnection() {
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//싱글톤 instance생성--------------------------------------------------------------------------
	public static ProjectBookDAO getInstance() {
		if (instance == null) {
			instance = new ProjectBookDAO();
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
		} finally {
			try {
				if (rs != null)	rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return seq;
	}

//데이터입력---------------------------------------------------------------------------------------------	
	public void inputBook(ProjectBookDTO bookDTO) {

		String sql;
		String bookName = null;
		getConnection();
		
		sql = "select * from bookTable";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				if (bookDTO.getBookName().equals(rs.getString("bookName"))) {
					bookName = bookDTO.getBookName();
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null)	rs.close();
				if (pstmt != null)	pstmt.close();
				if (conn != null)	conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		getConnection();// 접속
		if (bookName == null) {
			sql = "insert into bookTable values(?,?,?,?,?,?,?)";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bookDTO.getSeq());// 책번호
				pstmt.setString(2, bookDTO.getBookName());// 제목
				pstmt.setString(3, bookDTO.getWriter());// 저자
				pstmt.setString(4, bookDTO.getBookType());// 장르
				pstmt.setString(5, bookDTO.getPublisher());// 출판사
				pstmt.setInt(6, bookDTO.getBookPrice());// 가격
				pstmt.setInt(7, bookDTO.getBookVolume());
				
				pstmt.executeUpdate();// 실행

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (pstmt != null)	pstmt.close();
					if (conn != null)	conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} // finally
		}else {
			sql = "update bookTable set bookVolume = bookVolume + ? where bookName = ?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bookDTO.getBookVolume());
				pstmt.setString(2, bookName);
				
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if (pstmt != null)	pstmt.close();
					if (conn != null)	conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} 
			
		}
	}// inputBook

//장르별검색?출력!------------------------------------------------------------------
	public List<ProjectBookDTO> selectTotal() { // 전체출력<ㅡ
		List<ProjectBookDTO> list = new ArrayList<ProjectBookDTO>();
		getConnection();
		String sql = "select * from bookTable order by seq"; // 다가져옴

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ProjectBookDTO bookDTO = new ProjectBookDTO();
				bookDTO.setSeq(rs.getInt("seq"));
				bookDTO.setBookName(rs.getString("BookName"));
				bookDTO.setWriter(rs.getString("Writer"));
				bookDTO.setBookType(rs.getString("BookType"));
				bookDTO.setPublisher(rs.getString("Publisher"));
				bookDTO.setBookPrice(rs.getInt("BookPrice"));
				bookDTO.setBookVolume(rs.getInt("bookVolume"));

				list.add(bookDTO);
			} // while

		} catch (SQLException e) {
			e.printStackTrace();
			list = null;
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return list;

	}// selectTotle()

//장르메소드생성----------------------------------------------------------------
	public List<ProjectBookDTO> genreList(String booktype) {
		List<ProjectBookDTO> list = new ArrayList<ProjectBookDTO>();
		getConnection();

		// 만약 자연과학을 누르면 자연과학만 출력해라
		String sql = "select * from bookTable where bookType = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, booktype);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ProjectBookDTO bookDTO = new ProjectBookDTO();
				bookDTO.setSeq(rs.getInt("seq"));
				bookDTO.setBookName(rs.getString("BookName"));
				bookDTO.setWriter(rs.getString("Writer"));
				bookDTO.setBookType(rs.getString("BookType"));
				bookDTO.setPublisher(rs.getString("Publisher"));
				bookDTO.setBookPrice(rs.getInt("BookPrice"));
				bookDTO.setBookVolume(rs.getInt("bookVolume"));

				list.add(bookDTO);
			} // while

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

	// 제목 검색
	public List<ProjectBookDTO> searchBookName(String bookname) {
		List<ProjectBookDTO> list = new ArrayList<ProjectBookDTO>();
		getConnection();
		String sql = "select * from BookTable where bookName like ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + bookname + "%");

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ProjectBookDTO bookDTO = new ProjectBookDTO();
				bookDTO.setSeq(rs.getInt("seq"));
				bookDTO.setBookName(rs.getString("bookName"));
				bookDTO.setWriter(rs.getString("Writer"));
				bookDTO.setBookType(rs.getString("BookType"));
				bookDTO.setPublisher(rs.getString("Publisher"));
				bookDTO.setBookPrice(rs.getInt("BookPrice"));
				bookDTO.setBookVolume(rs.getInt("bookVolume"));

				list.add(bookDTO);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

	// 저자 검색
	public List<ProjectBookDTO> searchWriter(String writer) {
		List<ProjectBookDTO> list = new ArrayList<ProjectBookDTO>();
		getConnection();
		String sql = "select * from BookTable where writer like ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + writer + "%");

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ProjectBookDTO bookDTO = new ProjectBookDTO();
				bookDTO.setSeq(rs.getInt("seq"));
				bookDTO.setBookName(rs.getString("bookName"));
				bookDTO.setWriter(rs.getString("Writer"));
				bookDTO.setBookType(rs.getString("BookType"));
				bookDTO.setPublisher(rs.getString("Publisher"));
				bookDTO.setBookPrice(rs.getInt("BookPrice"));
				bookDTO.setBookVolume(rs.getInt("bookVolume"));

				list.add(bookDTO);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

	// 출판사 검색
	public List<ProjectBookDTO> searchPublisher(String publisher) {
		List<ProjectBookDTO> list = new ArrayList<ProjectBookDTO>();
		getConnection();
		String sql = "select * from BookTable where publisher like ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + publisher + "%");

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ProjectBookDTO bookDTO = new ProjectBookDTO();
				bookDTO.setSeq(rs.getInt("seq"));
				bookDTO.setBookName(rs.getString("bookName"));
				bookDTO.setWriter(rs.getString("Writer"));
				bookDTO.setBookType(rs.getString("BookType"));
				bookDTO.setPublisher(rs.getString("Publisher"));
				bookDTO.setBookPrice(rs.getInt("BookPrice"));
				bookDTO.setBookVolume(rs.getInt("bookVolume"));

				list.add(bookDTO);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

	// 장르 제목 검색
	public List<ProjectBookDTO> searchTypeBookName(String bookType, String bookname) {
		List<ProjectBookDTO> list = new ArrayList<ProjectBookDTO>();
		getConnection();
		String sql = "select * from BookTable where bookType = ? and bookName like ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bookType);
			pstmt.setString(2, "%" + bookname + "%");

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ProjectBookDTO bookDTO = new ProjectBookDTO();
				bookDTO.setSeq(rs.getInt("seq"));
				bookDTO.setBookName(rs.getString("bookName"));
				bookDTO.setWriter(rs.getString("Writer"));
				bookDTO.setBookType(rs.getString("BookType"));
				bookDTO.setPublisher(rs.getString("Publisher"));
				bookDTO.setBookPrice(rs.getInt("BookPrice"));
				bookDTO.setBookVolume(rs.getInt("bookVolume"));

				list.add(bookDTO);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

	// 장르 저자 검색
	public List<ProjectBookDTO> searchTypeWriter(String bookType, String writer) {
		List<ProjectBookDTO> list = new ArrayList<ProjectBookDTO>();
		getConnection();
		String sql = "select * from BookTable where bookType =? and writer like ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bookType);
			pstmt.setString(2, "%" + writer + "%");

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ProjectBookDTO bookDTO = new ProjectBookDTO();
				bookDTO.setSeq(rs.getInt("seq"));
				bookDTO.setBookName(rs.getString("bookName"));
				bookDTO.setWriter(rs.getString("Writer"));
				bookDTO.setBookType(rs.getString("BookType"));
				bookDTO.setPublisher(rs.getString("Publisher"));
				bookDTO.setBookPrice(rs.getInt("BookPrice"));
				bookDTO.setBookVolume(rs.getInt("bookVolume"));

				list.add(bookDTO);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

	// 장르 출판사 검색
	public List<ProjectBookDTO> searchTypePublisher(String bookType, String publisher) {
		List<ProjectBookDTO> list = new ArrayList<ProjectBookDTO>();
		getConnection();
		String sql = "select * from BookTable where bookType =? and publisher like ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bookType);
			pstmt.setString(2, "%" + publisher + "%");

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ProjectBookDTO bookDTO = new ProjectBookDTO();
				bookDTO.setSeq(rs.getInt("seq"));
				bookDTO.setBookName(rs.getString("bookName"));
				bookDTO.setWriter(rs.getString("Writer"));
				bookDTO.setBookType(rs.getString("BookType"));
				bookDTO.setPublisher(rs.getString("Publisher"));
				bookDTO.setBookPrice(rs.getInt("BookPrice"));
				bookDTO.setBookVolume(rs.getInt("bookVolume"));

				list.add(bookDTO);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

	public void bookOrder(ProjectBookDTO bookDTO, int bookVolume) {
		getConnection();

		String sql = "update bookTable set bookVolume = bookVolume ? where bookName = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, -bookVolume);
			pstmt.setString(2, bookDTO.getBookName());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)	pstmt.close();
				if (conn != null)	conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		getConnection();
		sql = "select * from BookTable where bookName =?";
		ProjectBookDTO changeBookDTO = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bookDTO.getBookName());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				changeBookDTO = new ProjectBookDTO();
				changeBookDTO.setSeq(rs.getInt("seq"));
				changeBookDTO.setBookName(rs.getString("bookName"));
				changeBookDTO.setWriter(rs.getString("Writer"));
				changeBookDTO.setBookType(rs.getString("BookType"));
				changeBookDTO.setPublisher(rs.getString("Publisher"));
				changeBookDTO.setBookPrice(rs.getInt("BookPrice"));
				changeBookDTO.setBookVolume(rs.getInt("bookVolume"));
			}
			new ProjectBookOrderDAO().inputOrderBook(changeBookDTO);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)		rs.close();
				if (pstmt != null)	pstmt.close();
				if (conn != null)	conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}// bookorder

	public void keepOrder(ProjectBookDTO bookDTO, int bookVolume) {

		getConnection();
		String sql = "select * from BookTable where bookName =?";
		ProjectBookDTO changeBookDTO = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bookDTO.getBookName());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				bookDTO = new ProjectBookDTO();
				bookDTO.setSeq(rs.getInt("seq"));
				bookDTO.setBookName(rs.getString("bookName"));
				bookDTO.setWriter(rs.getString("Writer"));
				bookDTO.setBookType(rs.getString("BookType"));
				bookDTO.setPublisher(rs.getString("Publisher"));
				bookDTO.setBookPrice(rs.getInt("BookPrice"));
				bookDTO.setBookVolume(rs.getInt("bookVolume"));

			}
			new ProjectKeepOrderDAO().inputKeepOrder(bookDTO, bookVolume);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)		rs.close();
				if (pstmt != null)	pstmt.close();
				if (conn != null)	conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}// bookorder

}
