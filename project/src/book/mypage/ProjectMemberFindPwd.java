package book.mypage;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import book.DAODTO.ProjectMemberDAO;

public class ProjectMemberFindPwd extends JFrame implements ActionListener{
	
	private JLabel idL,  mentL,emailL,codeL,logoSL;// 라벨
	private JLabel idLT, emailLT,codeLT; //라벨입력양식 안내 LT=LabelText 줄임
	private JTextField idT, emailT,codeT; // 입력창
	private JButton findPwdBtn,cancelBtn, emailCheckBtn,codeCheckBtn; //버튼
	private JComboBox emailComboBox;
	private String returnCode2,returnFindPwd;  //지역을 벗어난 함수의 리턴값을 받기위해 이곳에 변수초기화
	private String email,email2,code; //code:여러지역에 변수가 중복으로 쓰여서 선언.
	//emial1,2:다른 지역에서 쓰인 값을 그대로 사용하기위해 선언
	
	public ProjectMemberFindPwd() {
	
		// TODO pass word찾기 화면 창-----------------
				setLayout(null);
				//버튼,라벨
				String[] email2 = {"@gmail.com","@naver.com","@nate.com","@hanmail.net","@daum.net"};
				emailComboBox = new JComboBox<String>(email2);
				
				Font font = new Font("중나좋체 MEDIUM",Font.BOLD,42);
				Font font2 = new Font("중나좋체 LIGHT",Font.BOLD,22);
				Font font3 = new Font("굴림",Font.BOLD,12);
				Font font4 = new Font("굴림",Font.BOLD,10);
				
				mentL=new JLabel("비밀번호 찾기");
				mentL.setFont(font);
				mentL.setBounds(30,10,500,100);
				
				logoSL = new JLabel(new ImageIcon("small_logo.jpg"));
				logoSL.setBounds(280,32,171,66);
				
				idL = new JLabel("아 이 디");
				idL.setFont(font2);
				idL.setBounds(50,120,130,25);
				idT = new JTextField();
				idT.setBounds(170,120,272,25);   
				idLT = new JLabel("가입된 아이디 입력");
				idLT.setFont(font3);
				idLT.setBounds(175,143,150,25);
				idLT.setForeground(Color.gray);
				
				emailL = new JLabel("이 메 일");
				emailL.setFont(font2);
				emailL.setBounds(50,180,130,25);
				emailT = new JTextField(); //new JTextArea();
				emailT.setBounds(170,180,62,25);
				emailLT = new JLabel("가입된 이메일 입력");
				emailLT.setFont(font3);
				emailLT.setBounds(175,203,300,25);
				emailLT.setForeground(Color.gray);
				
				emailCheckBtn = new JButton(new ImageIcon("insend.png"));//이메일
				emailCheckBtn.setFont(font4);
				emailCheckBtn.setBounds(335,180,106,25);
				emailCheckBtn.setBorderPainted(false);
				
				emailComboBox.setBounds(232,180,102,25); //이메일주소2
				
				codeL = new JLabel("인증확인");//인증번호
				codeL.setFont(font2);
				codeL.setBounds(50,240,130,25);
				codeT = new JTextField(); //new JTextArea();
				codeT.setBounds(170,240,165,25);
				codeLT = new JLabel("이메일로 받은 인증번호를 입력해주세요.");
				codeLT.setFont(font3);
				codeLT.setBounds(175,263,300,25);
				codeLT.setForeground(Color.gray);
				
				codeCheckBtn = new JButton(new ImageIcon("inincheck.png"));
				codeCheckBtn.setBounds(335,240,106,25);
				codeCheckBtn.setBorderPainted(false);
				
				findPwdBtn = new JButton(new ImageIcon("find_pw.jpg"));
				findPwdBtn.setBounds(90,320,152,40);
				findPwdBtn.setBorderPainted(false);
				
				cancelBtn = new JButton(new ImageIcon("cancelbtn.jpg"));
				cancelBtn.setBounds(245,320,152,40);
				cancelBtn.setBorderPainted(false);
				
				//컨테이너
				Container con = getContentPane();
				
				
				con.add(mentL);
				con.add(logoSL);
				con.add(idL);
				con.add(idT);
				con.add(idLT);
					
				con.add(emailL);
				con.add(emailT);
				con.add(emailLT);
				con.add(emailComboBox);
				con.add(emailCheckBtn);
				
				con.add(codeL);
				con.add(codeT);
				con.add(codeCheckBtn);
				con.add(codeLT);
				
				con.add(findPwdBtn);
				con.add(cancelBtn);
				
				//세팅
				setTitle("https://www.bit199.com/Templates/FTForgetPW.aspx");
				setBounds(700, 150, 500, 440);
				setVisible(true);
				//setDefaultCloseOperation(EXIT_ON_CLOSE);
				//con.setBackground(new Color(255, 240, 240));
				con.setBackground(Color.white);
				setResizable(false);	
			
			//	logInBtn.addActionListener(this);
				this.eventFindPwd();
				findPwdBtn.setEnabled(false);
			}
			// TODO 이벤트 창
			private void eventFindPwd() {
				findPwdBtn.addActionListener(this);
				cancelBtn.addActionListener(this);
				emailCheckBtn.addActionListener(this);
				codeCheckBtn.addActionListener(this);
				
			}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == cancelBtn) {
			setVisible(false);
		}
		// TODO 버튼 기능
			if(e.getSource() == emailCheckBtn) { //메일+아이디 중복체크+코드보내기
				ProjectMemberEmail memberEmail = new ProjectMemberEmail(); //멤버메일 클래스 객체생성
				ProjectMemberDAO memberDAO = ProjectMemberDAO.getInstance();
				
				String id = idT.getText();
				email = emailT.getText();
				email2 = (String)emailComboBox.getSelectedItem();
				returnFindPwd=memberDAO.findPwd(idT.getText(),emailT.getText(),(String)emailComboBox.getSelectedItem());
				
				
				if(!returnFindPwd.equals("")) {
					
				
					email = emailT.getText();
					email2 = (String)emailComboBox.getSelectedItem();
					
					returnCode2 = memberEmail.emailSend(emailT.getText(),(String)emailComboBox.getSelectedItem()); //멤버메일 클래스의 메일보내기함수 (getText값)넣고 호출
					//new MemberEmail().emailSend(); 
					JOptionPane.showMessageDialog(this, "인증번호가 전송되었습니다. 인증번호를 입력해주세요.");
					
					
						
				}else{
					JOptionPane.showMessageDialog(this, "아이디 혹은 이메일이 잘못 입력되었습니다.");
					findPwdBtn.setEnabled(false);
					//System.out.println(check+id);
				}
			
			}
			
			
	if(e.getSource() == codeCheckBtn) {
				
				
				
				
				code = codeT.getText();
				
				if(code.equals(returnCode2)){
					JOptionPane.showMessageDialog(this, "메일 인증 완료. 비밀번호찾기 버튼을 누르세요.");
					
					findPwdBtn.setEnabled(true); 
					}else{JOptionPane.showMessageDialog(this, "인증번호가 일치하지 않습니다.");
					findPwdBtn.setEnabled(false); 
					}
			}
			
	if(e.getSource() == findPwdBtn) {
		code = codeT.getText(); //codeCheck성공후 고의적 오입력 방지를 위해 변수초기화를 위에서 설정
		if(code.equals(returnCode2)){
			
			email = emailT.getText();
			email2 = (String)emailComboBox.getSelectedItem();
			
			new ProjectMemberEmail().emailSendPwd(returnFindPwd,email,email2);
			JOptionPane.showMessageDialog(this, "입력하신 메일주소로 가입된 비밀번호가 전송되었습니다.");
			
			
			}else{JOptionPane.showMessageDialog(this, "인증번호가 일치하지 않습니다.");
			findPwdBtn.setEnabled(false); 
			}
		
		}
	
	//TODO 끝부분
	}//actionPerformed(ActionEvent e)

}//class MemberFindPwd extends JFrame implements ActionListener


