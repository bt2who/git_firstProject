package book.mypage;


import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ProjectMemberEmailSend {
	
	
	
	public void emailSend() {
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

	
		String fromName = "발신자 닉네임";
        String charSet = "UTF-8";



		
		
		
		try {
			msg.setSentDate(new Date());
			//보내는 날짜 지정
			
			
			
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			
			
			
//			InternetAddress from = new InternetAddress() ;
//			from = new InternetAddress(new String(fromName.getBytes(charSet), "8859_1") + "<bit199store@gmail.com>");
//           msg.setFrom(from);



			
			
			
			

			
		msg.setFrom(new InternetAddress("bit199store@gmail.com","VISITOR")); //관리자(보내는 이) 이메일
			//- Message 클래스의 setFrom() 메소드를 사용하여 발송자를 지정한다. 발송자의 메일, 발송자명\
			//InternetAddress 클래스는 이메일 주소를 나타날 때 사용하는 클래스이다.




			InternetAddress to = new InternetAddress("canon8906@gmail.com"); //수신자 이메일 생성
			 msg.setRecipient(Message.RecipientType.TO, to);        
			 //Message 클래스의 setRecipient() 메소드를 사용하여 수신자를 설정한다. setRecipient() 메소드로 수신자, 참조, 숨은 참조 설정이 가능하다.

			 //Message.RecipientType.TO : 받는 사람

			 //Message.RecipientType.CC : 참조

			// Message.RecipientType.BCC : 숨은 참조
			
			 msg.setSubject("제목", "UTF-8");  //메일제목           
	         msg.setText("안녕하세요 테스트 메일입니다.", "UTF-8"); //메일 내용
	       
	         Transport.send(msg);
	         //Transport는 메일을 최종적으로 보내는 클래스로 메일을 보내는 부분이다.


		 }catch (AddressException addr_e) {

			 addr_e.printStackTrace();
		} catch (UnsupportedEncodingException e) { // 지원되지 않는 인코딩을 사용할 경우 예외 처리
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {  //메일 계정인증 관련 예외 처리
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
	}
}

		

