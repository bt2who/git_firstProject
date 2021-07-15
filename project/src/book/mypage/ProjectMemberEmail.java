package book.mypage;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ProjectMemberEmail extends Authenticator {
	//TODO 구글 SMTP를 이용한 이메일 인증,이메일 보내기 
	
	
	
	

	
	
	//메일을 보내기 위해 추가한 mail-1.4.7 jar에는 javax.mail 패키지가 들어있다.

	//javax.mail 패키지의 주요 클래스는 Session, Message, Address, Authenticator, Tranport 등이 있다. SMTP 서버에 연결해 사용자 인증을 하기 위해서 Authenticator 클래스 사용이 필요하다.
	//TODO  SMTP(보내는 메일의 로그인)
	private String mail_id = "bit199store";  //구글 아이디
	private String mail_pw = "atsalam1004";  //구글 비밀번호
	private String fromName = "온라인서점bit199"; //발신자 닉네임
	private String charSet = "UTF-8"; // 텍스트 파일 인코딩
	
	
	
	PasswordAuthentication pa;
	

	public ProjectMemberEmail() {
		

		pa = new PasswordAuthentication(mail_id, mail_pw);

	}


	public PasswordAuthentication getPasswordAuthentication() {
		return pa;
	}
	//Authenticator를 사용하기 위해서는 PasswordAuthenticator 클래스로부터 인스턴스를 생성하고 getPasswordAuthentication 메소드로 리턴받아야 한다.

	//PasswordAuthentication 클래스는 사용자의 아이디와 패스워드를 입력받아 반환하도록 재정의한다.
	//TODO  인증번호 보내기 메소드
	public String emailSend(String insertEmail,String insertEmail2) {
		
		String returnCode = emailCode();  //코드메소드를 변수로 초기화
		
		Properties prop = System.getProperties();
		//- Properties 클래스는 시스템의 속성을 객체로 생성하는 클래스이다. Hashtable을 상속받은 클래스로 속성과 값은 1:1로 저장이 된다.
		prop.put("mail.smtp.starttls.enable","true"); //로그인시 TLS를 사용할 것인지 설정
		prop.put("mail.smtp.host", "smtp.gmail.com"); //이메일 발송을 처리해줄 SMTP 서버
		prop.put("mail.smtp.auth", "true"); //SMTP 서버의 인증을 사용한다는 의미
		prop.put("mail.smtp.port", "587"); //TLS의 포트번호는 587이며 SSL의 포트번호는 465이다
		//이 부분은 TLS와 SSL의 사용에 따라 설정값이 다른데 위 코드는 TLS의 경우이다. 
		//https://stackoverflow.com/questions/20290625/javamail-javax-mail-authenticationfailedexception 참조



		
		
		
		
		Authenticator auth = new ProjectMemberEmail();
	  
		Session session = Session.getDefaultInstance(prop,auth);
		//MemberEmail.java 에서 Authenticator를 상속받아 생성한 MailAuth 클래스를 받아 세션을 생성한다.
		//getDefaultInstance의 첫 번째 파라미터는 앞서 설정한 메일 처리 환경이다.




		MimeMessage msg = new MimeMessage(session);
		//MimeMessage는 Message (추상)클래스를 상속받은 인터넷 메일을 위한 클래스이다. 위에서 생성한 세션을 담아 msg 객체를 생성한다.

	
//		private String fromName = "bit199관리자"; //발신자 닉네임
//        private String charSet = "UTF-8"; // 사용언어



		
		
		
		try {
			msg.setSentDate(new Date());
			//보내는 날짜 지정
			
			
			
			
		} catch (MessagingException e) {
			
			e.printStackTrace();
		}
		
		try {
			
			
			
//			InternetAddress from = new InternetAddress() ;
//			from = new InternetAddress(new String(fromName.getBytes(charSet), "8859_1") + "<bit199store@gmail.com>");
//           msg.setFrom(from);



			
			
			
			

			
		msg.setFrom(new InternetAddress("bit199store@gmail.com",fromName)); //관리자(보내는 이) 이메일
			//- Message 클래스의 setFrom() 메소드를 사용하여 발송자를 지정한다. 발송자의 메일, 발송자명\
			//InternetAddress 클래스는 이메일 주소를 나타날 때 사용하는 클래스이다.




			InternetAddress to = new InternetAddress(insertEmail+insertEmail2); //수신자 이메일 생성
			 msg.setRecipient(Message.RecipientType.TO, to);        
			 //Message 클래스의 setRecipient() 메소드를 사용하여 수신자를 설정한다. setRecipient() 메소드로 수신자, 참조, 숨은 참조 설정이 가능하다.
			 //System.out.println(insertEmail+insertEmail2);
			 //Message.RecipientType.TO : 받는 사람

			 //Message.RecipientType.CC : 참조

			// Message.RecipientType.BCC : 숨은 참조
			//TODO  이메일 제목,내용 입력창
			 msg.setSubject("비트199 e메일 인증메일 안내", charSet);  //메일제목           
	         msg.setText( //메일내용
	        		
	        	"온라인 서점 비트199입니다."+"\n"
	        	+ "아래의 이메일 인증코드를 입력해주세요."+"\n"
	        	+"인증코드는 대문자,소문자,숫자를 구분하여 정확하게 입력해주세요."
	        	+"\n"+"\n"
	        	+returnCode
	        	+"\n"+"\n"+"감사합니다."
	        	
	        		 , charSet); //메일 내용
	       
	         Transport.send(msg);
	         //Transport는 메일을 최종적으로 보내는 클래스로 메일을 보내는 부분이다.


		 }catch (AddressException e) {

			 e.printStackTrace();
		} catch (UnsupportedEncodingException e) { // 지원되지 않는 인코딩을 사용할 경우 예외 처리
			
			e.printStackTrace();
		} catch (MessagingException e) {  //메일 계정인증 관련 예외 처리
			
			e.printStackTrace();
		} /*finally {
            System.out.println("I am finally."+"\n"+"오류 : javax.mail.SendFailedException : 잘못된 주소"+"\n"
            		+ "해결 방안\r\n"
            		+ "이메일 구성의 경우 다음을 확인하십시오.\r\n"
            		+ "\r\n"
            		+ "이메일 구성을 사용하여 올바르게 작동하는 경우 이메일 알림을 테스트합니다 (\r\n"
            		+ "예 : 관리 설정 및 지원 이메일 구성).\r\n"
            		+ "오류 메시지에 따라 :\r\n"
            		+ "범위가 지정된 이메일 주소는 \"잘못된 주소\"입니다.\r\n"
            		+ "\"550 5.1.1\": SMTP 호스트에서 예외 응답이 있습니다. 릴레이에 대해서는 시스템 관리자에게 문의해야하며 이는 전송하지 않은 SMTP 서버에 있습니다.\r\n"
            		+ "\"501 5.5.4\": 명령이 정확하고 인식되었지만 매개 변수 (예 : 이메일 주소)가 유효하지 않습니다. \r\n"
            		+ "대부분의 경우 SMTP 오류 501은 잘못된 이메일 주소, 잘못된 도메인 이름 수신자 또는 설정된 표준을 따르지 않는 Unix / Linux SEND MAIL 명령으로 인해 발생합니다."
            		);
          
		}*/

		
		
		
			
		return returnCode;	//리턴된 코드번호
	}

	//TODO  이메일 인증코드 메소드
	public String emailCode(){ //이메일 인증 코드
		String code="";
		
//		for(int i=0; i<6; i++) {
//			code += (int)(Math.random()*10); 
//			}
		//System.out.println(code);	
		for(int i=0; i<6; i++) { 

	         int random = (int)((Math.random()*(3-1+1))+1);
	         switch(random) {
	         case 1:
	            code += (int)((Math.random()*9)+1);
	            break;
	         case 2:
	            int test1 = (int)((Math.random()*(90-65+1))+65);         
	            code += (char)test1;
	            break;
	         case 3:
	            int test2 = (int)((Math.random()*(122-97+1))+97);
	            code += (char)test2;
	            break;
	          
	         }
	         System.out.println(code);
	      // TODO code 보이는 설정 부분
		}

		
		
		
		
		return code;
		
		
	}
	

	//TODO  아이디찾기 이메일 관련 메소드
public void emailSendId(String findId, String insertEmail,String insertEmail2) {
		
		
		
		Properties prop = System.getProperties();
		//- Properties 클래스는 시스템의 속성을 객체로 생성하는 클래스이다. Hashtable을 상속받은 클래스로 속성과 값은 1:1로 저장이 된다.
		prop.put("mail.smtp.starttls.enable","true"); //로그인시 TLS를 사용할 것인지 설정
		prop.put("mail.smtp.host", "smtp.gmail.com"); //이메일 발송을 처리해줄 SMTP 서버
		prop.put("mail.smtp.auth", "true"); //SMTP 서버의 인증을 사용한다는 의미
		prop.put("mail.smtp.port", "587"); //TLS의 포트번호는 587이며 SSL의 포트번호는 465이다
		//이 부분은 TLS와 SSL의 사용에 따라 설정값이 다른데 위 코드는 TLS의 경우이다. 
		//https://stackoverflow.com/questions/20290625/javamail-javax-mail-authenticationfailedexception 참조



		
		
		
		
		Authenticator auth = new ProjectMemberEmail();
	  
		Session session = Session.getDefaultInstance(prop,auth);
		//MemberEmail.java 에서 Authenticator를 상속받아 생성한 MailAuth 클래스를 받아 세션을 생성한다.
		//getDefaultInstance의 첫 번째 파라미터는 앞서 설정한 메일 처리 환경이다.




		MimeMessage msg = new MimeMessage(session);
		//MimeMessage는 Message (추상)클래스를 상속받은 인터넷 메일을 위한 클래스이다. 위에서 생성한 세션을 담아 msg 객체를 생성한다.

	
//		private String fromName = "bit199관리자"; //발신자 닉네임
//        private String charSet = "UTF-8"; // 사용언어



		
		
		
		try {
			msg.setSentDate(new Date());
			//보내는 날짜 지정
			
			
			
			
		} catch (MessagingException e) {
			
			e.printStackTrace();
		}
		
		try {
			
			
			
//			InternetAddress from = new InternetAddress() ;
//			from = new InternetAddress(new String(fromName.getBytes(charSet), "8859_1") + "<bit199store@gmail.com>");
//           msg.setFrom(from);



			
			
			
			

			
		msg.setFrom(new InternetAddress("bit199store@gmail.com",fromName)); //관리자(보내는 이) 이메일
			//- Message 클래스의 setFrom() 메소드를 사용하여 발송자를 지정한다. 발송자의 메일, 발송자명\
			//InternetAddress 클래스는 이메일 주소를 나타날 때 사용하는 클래스이다.




			InternetAddress to = new InternetAddress(insertEmail+insertEmail2); //수신자 이메일 생성
			 msg.setRecipient(Message.RecipientType.TO, to);        
			 //Message 클래스의 setRecipient() 메소드를 사용하여 수신자를 설정한다. setRecipient() 메소드로 수신자, 참조, 숨은 참조 설정이 가능하다.
			 //System.out.println(insertEmail+insertEmail2);
			 //Message.RecipientType.TO : 받는 사람

			 //Message.RecipientType.CC : 참조

			// Message.RecipientType.BCC : 숨은 참조
			//TODO  아이디찾기 메일 제목,내용
			 msg.setSubject("비트199 아이디찾기 안내", charSet);  //메일제목           
	         msg.setText(
	        		
	        	"온라인 서점 비트199입니다."+"\n"+ "아래의 가입된 아이디를 확인해주세요."+"\n"+"\n"
	        	+findId+"\n"+"\n"+"감사합니다."
	        	
	        		 , charSet); //메일 내용
	       
	         Transport.send(msg);
	         //Transport는 메일을 최종적으로 보내는 클래스로 메일을 보내는 부분이다.


		 }catch (AddressException e) {

			 e.printStackTrace();
		} catch (UnsupportedEncodingException e) { // 지원되지 않는 인코딩을 사용할 경우 예외 처리
			
			e.printStackTrace();
		} catch (MessagingException e) {  //메일 계정인증 관련 예외 처리
			
			e.printStackTrace();
		}	
}		
			
			
//TODO  비밀번호찾기 관련메소드
			public void emailSendPwd(String findPwd, String insertEmail,String insertEmail2) {
				
				
				
				Properties prop = System.getProperties();
				//- Properties 클래스는 시스템의 속성을 객체로 생성하는 클래스이다. Hashtable을 상속받은 클래스로 속성과 값은 1:1로 저장이 된다.
				prop.put("mail.smtp.starttls.enable","true"); //로그인시 TLS를 사용할 것인지 설정
				prop.put("mail.smtp.host", "smtp.gmail.com"); //이메일 발송을 처리해줄 SMTP 서버
				prop.put("mail.smtp.auth", "true"); //SMTP 서버의 인증을 사용한다는 의미
				prop.put("mail.smtp.port", "587"); //TLS의 포트번호는 587이며 SSL의 포트번호는 465이다
				//이 부분은 TLS와 SSL의 사용에 따라 설정값이 다른데 위 코드는 TLS의 경우이다. 
				//https://stackoverflow.com/questions/20290625/javamail-javax-mail-authenticationfailedexception 참조



				
				
				
				
				Authenticator auth = new ProjectMemberEmail();
			  
				Session session = Session.getDefaultInstance(prop,auth);
				//MemberEmail.java 에서 Authenticator를 상속받아 생성한 MailAuth 클래스를 받아 세션을 생성한다.
				//getDefaultInstance의 첫 번째 파라미터는 앞서 설정한 메일 처리 환경이다.




				MimeMessage msg = new MimeMessage(session);
				//MimeMessage는 Message (추상)클래스를 상속받은 인터넷 메일을 위한 클래스이다. 위에서 생성한 세션을 담아 msg 객체를 생성한다.

			
//				private String fromName = "bit199관리자"; //발신자 닉네임
//		        private String charSet = "UTF-8"; // 사용언어



				
				
				
				try {
					msg.setSentDate(new Date());
					//보내는 날짜 지정
					
					
					
					
				} catch (MessagingException e) {
					
					e.printStackTrace();
				}
				
				try {
					
					
					
//					InternetAddress from = new InternetAddress() ;
//					from = new InternetAddress(new String(fromName.getBytes(charSet), "8859_1") + "<bit199store@gmail.com>");
//		           msg.setFrom(from);



					
					
					
					

					
				msg.setFrom(new InternetAddress("bit199store@gmail.com",fromName)); //관리자(보내는 이) 이메일
					//- Message 클래스의 setFrom() 메소드를 사용하여 발송자를 지정한다. 발송자의 메일, 발송자명\
					//InternetAddress 클래스는 이메일 주소를 나타날 때 사용하는 클래스이다.




					InternetAddress to = new InternetAddress(insertEmail+insertEmail2); //수신자 이메일 생성
					 msg.setRecipient(Message.RecipientType.TO, to);        
					 //Message 클래스의 setRecipient() 메소드를 사용하여 수신자를 설정한다. setRecipient() 메소드로 수신자, 참조, 숨은 참조 설정이 가능하다.
					 //System.out.println(insertEmail+insertEmail2);
					 //Message.RecipientType.TO : 받는 사람

					 //Message.RecipientType.CC : 참조

					// Message.RecipientType.BCC : 숨은 참조
					//TODO  비밀번호찾기 제목,내용 입력 부분
					 msg.setSubject("비트199 비밀번호찾기 안내", charSet);  //메일제목           
			         msg.setText(
			        		
			        	"온라인 서점 비트199입니다."+"\n"+ "아래의 가입된 비밀번호를 확인해주세요."+"\n"+"\n"
			        	+findPwd+"\n"+"\n"+"감사합니다."
			        	
			        		 , charSet); //메일 내용
			       
			         Transport.send(msg);
			         //Transport는 메일을 최종적으로 보내는 클래스로 메일을 보내는 부분이다.


				 }catch (AddressException e) {

					 e.printStackTrace();
				} catch (UnsupportedEncodingException e) { // 지원되지 않는 인코딩을 사용할 경우 예외 처리
					
					e.printStackTrace();
				} catch (MessagingException e) {  //메일 계정인증 관련 예외 처리
					
					e.printStackTrace();	
				} /*finally {
	            System.out.println("I am finally."+"\n"+"오류 : javax.mail.SendFailedException : 잘못된 주소"+"\n"
	            		+ "해결 방안\r\n"
	            		+ "이메일 구성의 경우 다음을 확인하십시오.\r\n"
	            		+ "\r\n"
	            		+ "이메일 구성을 사용하여 올바르게 작동하는 경우 이메일 알림을 테스트합니다 (\r\n"
	            		+ "예 : 관리 설정 및 지원 이메일 구성).\r\n"
	            		+ "오류 메시지에 따라 :\r\n"
	            		+ "범위가 지정된 이메일 주소는 \"잘못된 주소\"입니다.\r\n"
	            		+ "\"550 5.1.1\": SMTP 호스트에서 예외 응답이 있습니다. 릴레이에 대해서는 시스템 관리자에게 문의해야하며 이는 전송하지 않은 SMTP 서버에 있습니다.\r\n"
	            		+ "\"501 5.5.4\": 명령이 정확하고 인식되었지만 매개 변수 (예 : 이메일 주소)가 유효하지 않습니다. \r\n"
	            		+ "대부분의 경우 SMTP 오류 501은 잘못된 이메일 주소, 잘못된 도메인 이름 수신자 또는 설정된 표준을 따르지 않는 Unix / Linux SEND MAIL 명령으로 인해 발생합니다."
	            		);
	          finally 사용중지한 이유: smtp 이메일 오입력시 에러메시지를 사용자입장에서 설명해주기 적절하다고 생각하여 추가하였으나,
	          email 컬럼 값을 2개로 나뉜뒤부터 정상적인 상황에도 finally메시지만 뜨는 현상이 생겨서 중지하였다. 
	          즉, email 컬럼값을 1개로 전송해야 다시 쓸 수 있다.
			}*/

			
			}
			
			// TODO 마지막부분
			
		}		
			
			
			
			
			
			
			
			
			
			
			
		
		
		
		
			
		







	





