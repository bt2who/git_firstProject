package book.DAODTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProjectMemberDAO  {

	private String url = "jdbc:oracle:thin:@localhost:1521:xe"; //로컬호스트
	//private String url = "jdbc:oracle:thin:@211.184.119.183:1521:xe"; //아이피입력
	
	private String username = "c##java";
	private String password = "bit";
	private PreparedStatement pstmt = null;
	private Connection conn=null;
	private static ProjectMemberDAO instance;
	private ResultSet rs;
	private Statement st;
	

	// TODO jdbc 시작부분


	// JDBC 로딩
	public ProjectMemberDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	// 연결
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 싱글톤 instance 생성
	public static ProjectMemberDAO getInstance() {
		if(instance == null) {
			instance = new ProjectMemberDAO();
		}
		return instance;
	}




	
	// 회원 목록 출력하기(관리자 기능) _ 0: 관리자, 1: 일반회원
	// List를 전달받아 출력할 때, 비번은 나오지 않도록 한다.
	public List<ProjectMemberDTO> memberList(int num) {
		List<ProjectMemberDTO> memberList = null;
		try {
			this.getConnection();
			String sql = "select * from memberInfo where position ="+num;

			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			// TODO 멤버 정보 저장 관련 메소드
			memberList = new ArrayList<ProjectMemberDTO>();
			ProjectMemberDTO member;

			while(rs.next()) {
				member = new ProjectMemberDTO();
				
				member.setPosition(num);
				member.setId(rs.getString("id"));
				member.setPwd(rs.getString("pwd"));
				member.setName(rs.getString("name"));
				member.setAge(rs.getString("age"));
				member.setPhone(rs.getString("phone"));
				member.setAddress(rs.getString("address"));
				member.setEmail(rs.getString("email"));
				member.setEmail(rs.getString("email2"));
				
				memberList.add(member);	
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try{ 
				if( rs != null ) {	rs.close(); }
				if( st != null ) { st.close(); }
				if( conn != null ) { conn.close(); }

				return memberList;// 반환 받는 쪽에서 null 검사 시행 후 사용(NPE 피하기)
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return memberList;

	}





	// TODO 일반회원 로그인
	public boolean userLogin(String id, String pwd) {
		
		String sql = String.format("select pwd from memberInfo where id = '%s' and position = 0",id);
		this.getConnection();
		boolean result = false;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				if(rs.getString("pwd").equals(pwd)) {
					result = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try{
				if( rs != null ) {   rs.close(); }
				if( st != null ) { st.close(); }
				if( conn != null ) { conn.close(); }
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result;

	} // TRUE : 아이디, 비밀번호 일치 / FALSE : 아이디, 비밀번호 불일치
	// TODO 관리자 로그인
	public boolean managerLogin(String id, String pwd) {
	
		String sql = String.format("select pwd from memberInfo where id = '%s' and position = 1",id);
		this.getConnection();
		boolean result = false;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				if(rs.getString("pwd").equals(pwd)) {
					result = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try{
				if( rs != null ) {   rs.close(); }
				if( st != null ) { st.close(); }
				if( conn != null ) { conn.close(); }
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}	
	// TODO 아이디 중복확인 
	public boolean idCheck(String id) {//아이디 중복확인
		String sql = String.format("select * from memberInfo where id ='%s'", id); 
		this.getConnection();
		boolean result = false;
		try {
			// System.out.println("123");
			st = conn.createStatement();
			// System.out.println("456");
			rs = st.executeQuery(sql);
			// System.out.println("789");
			result = rs.next();
			// System.out.println("결과 : " + rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try{
				if( rs != null ) {   rs.close(); }
				if( st != null ) { st.close(); }
				if( conn != null ) { conn.close(); }
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
		
	} // TRUE : 중복값이 있다.  FALSE: 중복값이 없다 -> 가입진행 가능
	// TODO 이메일 중복확인
	public boolean emailCheck(String email,String email2) {//이메일 중복확인
		String sql = String.format("select email2 from memberInfo where email ='%s'", email); 
		this.getConnection();
		boolean result = false;
		try {
			
			st = conn.createStatement();
			
			rs = st.executeQuery(sql);
			
			//result = rs.next();
			
			while(rs.next()) {
				if(rs.getString("email2").equals(email2)) {
					result = true; }
			}
				
			// System.out.println("결과 : " + rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try{
				if( rs != null ) {   rs.close(); }
				if( st != null ) { st.close(); }
				if( conn != null ) { conn.close(); }
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
		
		
		

	} // TRUE : 중복값이 있다.  FALSE: 중복값이 없다 -> 가입진행 가능

	// TODO 회원가입
	public boolean add(ProjectMemberDTO arg) {
		try {		
			this.getConnection();
			String sql = "insert into memberInfo values"
					+ "(?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1,arg.getPosition());
			pstmt.setString(2,arg.getId());
			pstmt.setString(3,arg.getPwd());
			pstmt.setString(4,arg.getName());
			pstmt.setString(5,arg.getAge());
			pstmt.setString(6,arg.getAddress());
			pstmt.setString(7,arg.getEmail());
			pstmt.setString(8,arg.getEmail2());
			pstmt.setString(9,arg.getPhone());

			int result = pstmt.executeUpdate();

			if(result == 0) {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try{ 
				if( pstmt != null ) {	pstmt.close(); }
				if( conn != null ) {	conn.close(); }
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}

		return true;

	}

	// TODO 이름+ 이메일 중복확인. (아이디찾기 기능) 
	
	public String findId(String name,String email,String email2) {
		String sql = String.format("select id from memberInfo where name ='%s' and email = '%s'and email2='%s'", name,email ,email2); 
		this.getConnection();
		String id="";
	
		try {
			
			st = conn.createStatement();
			
			rs = st.executeQuery(sql);
			
			//result = rs.next();
			
			while(rs.next()) {
				
			id = rs.getString("id");
			
			}
			
			
			
			
			
				
			
				
			
			//id = ; 
			
				
			
			// System.out.println("결과 : " + rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try{
				if( rs != null ) {   rs.close(); }
				if( st != null ) { st.close(); }
				if( conn != null ) { conn.close(); }
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		//System.out.println("리턴값"+id);
		return id;
		

	} // 
	
	// TODO 닉네임+이메일로 중복찾기 (비밀번호 찾기기능)
	
	public String findPwd(String id,String email,String email2) {
		String sql = String.format("select pwd from memberInfo where id ='%s' and email = '%s'and email2='%s'", id,email ,email2); 
		this.getConnection();
		String pwd="";
	
		try {
			
			st = conn.createStatement();
			
			rs = st.executeQuery(sql);
			
			//result = rs.next();
			
			while(rs.next()) {
				
			pwd = rs.getString("pwd");
			
			}
			
			
			
			
			
				
			
				
			
			
			
				
			
			// System.out.println("결과 : " + rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try{
				if( rs != null ) {   rs.close(); }
				if( st != null ) { st.close(); }
				if( conn != null ) { conn.close(); }
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		//System.out.println("리턴값"+id);
		return pwd;
		// TODO 끝부분

	} // 
	public ProjectMemberDTO memberLoad(String loginId, String loginPwd) {
		
		ProjectMemberDTO loadMember=null;
		String sql = String.format("select * from memberInfo where id ='%s' and pwd = '%s' ", loginId ,loginPwd); 
		this.getConnection();
		
		
		try {
			
			st = conn.createStatement();
			
			rs = st.executeQuery(sql);
			
			
			
			
			while(rs.next()) {
				loadMember=	new ProjectMemberDTO();	
			loadMember.setName(rs.getString("name"));
			loadMember.setAge(rs.getString("age"));
			loadMember.setPhone(rs.getString("phone"));
			loadMember.setAddress(rs.getString("address"));
			loadMember.setEmail(rs.getString("email"));
			loadMember.setEmail2(rs.getString("email2"));
			
			
			}
			
			
			
			
			
				
			//result = rs.next();
				
			
			
			
				
			
			// System.out.println("결과 : " + rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try{
				if( rs != null ) {   rs.close(); }
				if( st != null ) { st.close(); }
				if( conn != null ) { conn.close(); }
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		//System.out.println("리턴값"+id);
		
		// TODO 끝부분
		return loadMember;
	} // 
		
	
	

	
}	//MemberDAO





