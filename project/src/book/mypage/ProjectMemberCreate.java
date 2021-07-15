package book.mypage;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.mail.internet.InternetAddress;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import book.DAODTO.ProjectMemberDAO;
import book.DAODTO.ProjectMemberDTO;



public class ProjectMemberCreate extends JFrame implements ActionListener {
	private JLabel idL, pwdL, nameL, ageL, phoneL , addressL, mentL,emailL,pwdCheckL,codeL,logoSL; // 라벨
	private JLabel nameLT, ageLT, phoneLT , addressLT, emailLT,codeLT; //라벨입력양식 안내 LT=LabelText 줄임
	private JTextField idT,  nameT, ageT, phoneT,addressT,emailT,codeT; // 입력창
	private JButton idCheckBtn, inputBtn,cancelBtn, emailCheckBtn,codeCheckBtn; //버튼
	private JPasswordField pwdT,pwdCheckT; //비밀번호 입력창
	private String retrunCode2,code; //메일클래스에서 받은 리턴값을 사용하기 위해 위에서 초기화,code는 여러곳에서 사용되어 초기화
	private JComboBox emailComboBox; //메일2 도메인 선택버튼창
	private Boolean check;
	private JTextArea idJTA,pwdJTA, pwdCheckJTA;
	
	
	//private JTextArea addressT;
	//JScrollPane scroll = new JScrollPane(addressT); //스크롤

	private JList<ProjectMemberDTO> list;
	//private DefaultListModel<MemberDTO> model;

	public ProjectMemberCreate() {

		// TODO //회원가입 창 ----------------------------------------------
		setLayout(null);

		
		//회원가입창 구현
				String[] email2 = {"@gmail.com","@naver.com","@nate.com","@hanmail.net","@daum.net"};
				emailComboBox = new JComboBox<String>(email2);
				
				Font font = new Font("중나좋체 MEDIUM",Font.BOLD,42);
				mentL=new JLabel("회원정보 입력");
				mentL.setFont(font);
				mentL.setBounds(30,10,500,100);

				logoSL = new JLabel(new ImageIcon("small_logo.jpg"));
				logoSL.setBounds(280,36,171,66);
				
				idCheckBtn = new JButton(new ImageIcon("idjungbok.png"));
				idCheckBtn.setBounds(310,120,121,25);
				idCheckBtn.setBorderPainted(false);

				inputBtn = new JButton(new ImageIcon("joinBtn2.png"));
				inputBtn.setBounds(90,680,152,40);
				inputBtn.setBorderPainted(false);

				cancelBtn = new JButton(new ImageIcon("cancelBtn.png"));
				cancelBtn.setBounds(245,680,152,40);
				cancelBtn.setBorderPainted(false);

				


				Font font2 = new Font("중나좋체 LIGHT",Font.BOLD,22);
			
				Font font3 = new Font("굴림",Font.BOLD,12);
				idL = new JLabel("아 이 디");
				idL.setFont(font2);
				idL.setBounds(50,120,130,25);
				idT = new JTextField();
				idT.setBounds(170,120,140,25);   
				
				 
				
				pwdL = new JLabel("비밀번호");
				pwdL.setFont(font2);
				pwdL.setBounds(50,180,130,25);
				pwdT = new JPasswordField();
				pwdT.setBounds(170,180,262,25);
				
				
				pwdCheckL = new JLabel("비밀번호확인");
				pwdCheckL.setFont(font2);
				pwdCheckL.setBounds(50,240,130,25);
				pwdCheckT = new JPasswordField();
				pwdCheckT.setBounds(170,240,262,25);
				

				nameL = new JLabel("이     름");
				nameL.setFont(font2);
				nameL.setBounds(50,300,130,25);
				nameT = new JTextField();
				nameT.setBounds(170,300,262,25);
				nameLT = new JLabel("실명 입력");
				nameLT.setFont(font3);
				nameLT.setBounds(175,323,130,25);
				nameLT.setForeground(Color.gray);

				ageL = new JLabel("생년월일");
				ageL.setFont(font2);
				ageL.setBounds(50,360,130,25);
				ageT = new JTextField();
				ageT.setBounds(170,360,262,25);
				ageLT = new JLabel("[입력예시]2002년3월5일 → 020305");
				ageLT.setFont(font3);
				ageLT.setBounds(175,383,262,25);
				ageLT.setForeground(Color.gray);

				phoneL = new JLabel("휴 대 폰");
				phoneL.setFont(font2);
				phoneL.setBounds(50,420,130,25);
				phoneT = new JTextField();
				phoneT.setBounds(170,420,270,25);
				phoneLT = new JLabel("[입력예시]010-9876-5432 → 01098765432");
				phoneLT.setFont(font3);
				phoneLT.setBounds(175,443,262,25);
				phoneLT.setForeground(Color.gray);
				
				
				
				addressL = new JLabel("주     소");
				addressL.setFont(font2);
				addressL.setBounds(50,480,130,25);
				addressT = new JTextField(""); //new JTextArea();
				addressT.setBounds(170,480,262,50);
				addressLT = new JLabel("[입력예시] ?도 ?시 ?구 ?대로 123번길 장소?동?호");
				addressLT.setFont(font3);
				addressLT.setBounds(175,528,290,25);
				addressLT.setForeground(Color.gray);
				
				
				emailL = new JLabel("이 메 일");
				emailL.setFont(font2);
				emailL.setBounds(50,565,130,25);
				emailT = new JTextField(); //new JTextArea();
				emailT.setBounds(170,565,62,25);
				emailLT = new JLabel("이메일관련 관리자문의메일:bit199store@gmail.com");
				emailLT.setFont(font3);
				emailLT.setBounds(175,588,300,25);
				emailLT.setForeground(Color.gray);
				

				emailCheckBtn = new JButton(new ImageIcon("emailin.png"));
				emailCheckBtn.setBounds(335,565,96,25);
				emailCheckBtn.setBorderPainted(false);
				
				emailComboBox.setBounds(232,565,102,25); //이메일주소2
				
				codeL = new JLabel("인증번호");
				codeL.setFont(font2);
				codeL.setBounds(50,625,130,25);
				codeT = new JTextField(); //new JTextArea();
				codeT.setBounds(170,625,165,25);
				codeLT = new JLabel("이메일로 받은 인증번호를 입력해주세요.");
				codeLT.setFont(font3);
				codeLT.setBounds(175,648,300,25);
				codeLT.setForeground(Color.gray);
				
				codeCheckBtn = new JButton(new ImageIcon("incheck.png"));
				codeCheckBtn.setBounds(335,625,96,25);
				codeCheckBtn.setBorderPainted(false);
				
				idJTA = new JTextArea("4자리 이상 입력");
				idJTA.setForeground(Color.gray);
				idJTA.setFont(font3);
				idJTA.setBounds(175,146,200,25); //143+3
				
				pwdJTA = new JTextArea("8자리 이상 입력");
				pwdJTA.setForeground(Color.gray);
				pwdJTA.setFont(font3);
				pwdJTA.setBounds(175,206,200,25); //143+3
				
				pwdCheckJTA = new JTextArea("비밀번호 똑같이 입력");
				pwdCheckJTA.setForeground(Color.gray);
				pwdCheckJTA.setFont(font3);
				pwdCheckJTA.setBounds(175,266,200,25); //143+3
				
				
			
				// TODO setBound 위치설정 관련 끝부분

				
				//컨테이너
				Container con = getContentPane();

				con.add(idCheckBtn);
				con.add(mentL);
				con.add(logoSL);
				con.add(idL);
				con.add(idT);
				con.add(idJTA);
				con.add(pwdL);
				con.add(pwdT);
				con.add(pwdJTA);
				con.add(pwdCheckL);
				con.add(pwdCheckT);
				con.add(pwdCheckJTA);
				con.add(nameL);
				con.add(nameT);
				con.add(nameLT);
				con.add(ageL);
				con.add(ageT);
				con.add(ageLT);
				con.add(phoneL);
				con.add(phoneT);
				con.add(phoneLT);
				con.add(addressL);
				con.add(addressT);
				con.add(addressLT);
				con.add(inputBtn);
				con.add(cancelBtn);
				con.add(emailL);
				con.add(emailT);
				con.add(emailLT);
				con.add(emailComboBox);
				con.add(emailCheckBtn);
				con.add(codeL);
				con.add(codeT);
				con.add(codeCheckBtn);
				con.add(codeLT);
				
				// TODO 화면창 수정 부분

				
				//세팅
				setTitle("https://www.bit199.com/Member/FTMemAcc.aspx");
				setBounds(200, 150, 500, 790);
				setVisible(true);
				//setDefaultCloseOperation(EXIT_ON_CLOSE);
				//con.setBackground(new Color(255, 240, 240));
				con.setBackground(Color.white);
				setResizable(false);

			

				this.eventCreate();


			}//MemberCreate()









	// TODO 이벤트
	public void eventCreate() {
		inputBtn.addActionListener(this);
		//		editBtn.addActionListener(this);
		//		deleteBtn.addActionListener(this);
		//		removeBtn.addActionListener(this);
		

		idCheckBtn.addActionListener(this);
	
		emailCheckBtn.addActionListener(this);
		codeCheckBtn.addActionListener(this);
		
		cancelBtn.addActionListener(this);
		
		
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == inputBtn) {

			if( pwdT.getText().equals(pwdCheckT.getText()) ){// 비밀번호 일치 여부 확인
				pwdCheckJTA.setText("비밀번호가 일치합니다.");
				pwdCheckJTA.setForeground(Color.blue);
				pwdJTA.setText("사용가능한 비밀번호입니다.");
				pwdJTA.setForeground(Color.blue);
				if(pwdT.getText().length()>=8) {
					pwdJTA.setText("사용가능한 비밀번호입니다.");
					pwdJTA.setForeground(Color.blue);
					
					
				}else{
					pwdJTA.setText("8자리 이상 입력해주세요.");
					pwdJTA.setForeground(Color.red);
					return;
				}
			}else{
				if(pwdT.getText().length()>=8) {
					pwdJTA.setText("사용가능한 비밀번호입니다.");
					pwdJTA.setForeground(Color.blue);
					pwdCheckJTA.setText("비밀번호가 일치하지 않습니다.");
					pwdCheckJTA.setForeground(Color.red);
				}else {
					//JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다."); //정상적으로 입력후 다시 입력값을 고의적으로 오입력후 가입하는것을 방지
					pwdJTA.setText("8자리 이상 입력해주세요.");
					pwdJTA.setForeground(Color.red);
					pwdCheckJTA.setText("비밀번호가 일치하지 않습니다.");
					pwdCheckJTA.setForeground(Color.red);
				}
				
				
			
				return;
			}	
				
			
		
	
			// TODO 버튼 기능 부분  actionPerformed

			// idCheck();
			ProjectMemberDAO memberDAO = ProjectMemberDAO.getInstance();

			boolean check = memberDAO.idCheck(idT.getText());

			if(check == false) {
				// 아이디 중복 X


				inputBtn.setEnabled(true);

			}else {
				// 아이디 중복
				//JOptionPane.showMessageDialog(this, "아이디 중복검사를 다시 해주세요."); //정상적으로 입력후 다시 입력값을 고의적으로 오입력후 가입하는것을 방지
				idJTA.setText("아이디 중복검사를 해주세요.");
				idJTA.setForeground(Color.red);
				inputBtn.setEnabled(false);
				return;
			} 

			
			inputMember();
	}
		//else {}
		
	

	
	
		if(e.getSource() == cancelBtn) {
			
			//System.exit(0);//이거누르면 창이 다꺼진다
			setVisible(false);
			
					
		}



		
		if(e.getSource() == idCheckBtn) {
			//idCheck();


			ProjectMemberDAO memberDAO = ProjectMemberDAO.getInstance();

			boolean check = memberDAO.idCheck(idT.getText());

			if(check == false) {
				// 아이디 중복 X
				String id = idT.getText();
				if(id.length()>=4) {//JOptionPane.showMessageDialog(this, "사용 가능한 아이디입니다."); 
				idJTA.setText("사용가능한 아이디 입니다.");
				idJTA.setForeground(Color.blue);
				inputBtn.setEnabled(true);
				}else {
					//JOptionPane.showMessageDialog(this, "아이디는 최소 4자리를 입력해야 합니다.");
					idJTA.setText("4자리 이상 입력");
					idJTA.setForeground(Color.red);
				inputBtn.setEnabled(false);
				}
			}else {
				// 아이디 중복
				//JOptionPane.showMessageDialog(this, "중복된 아이디가 있습니다.");
				idJTA.setText("중복된 아이디가 있습니다.");
				idJTA.setForeground(Color.red);
				inputBtn.setEnabled(false);
				
			}

		}
		// TODO 이메일 중복버튼
		if(e.getSource() == emailCheckBtn) {
			ProjectMemberEmail memberEmail = new ProjectMemberEmail(); //멤버메일 클래스 객체생성
			ProjectMemberDAO memberDAO = ProjectMemberDAO.getInstance();
			boolean check = memberDAO.emailCheck(emailT.getText(),(String)emailComboBox.getSelectedItem());
			
			if(check == false) {
			String email = emailT.getText();
			String email2 = (String)emailComboBox.getSelectedItem();
			
			
			
			
				if(email.length()>=5 && email.indexOf("@")==-1 && email2.indexOf("@")!=-1 ) 
				{JOptionPane.showMessageDialog(this, "인증번호가 전송되었습니다. 인증번호를 입력해주세요.");
		
				retrunCode2 = memberEmail.emailSend(emailT.getText(),(String)emailComboBox.getSelectedItem()); //멤버메일 클래스의 메일보내기함수 (getText값)넣고 호출
				//메일클래스의 리턴값을 받기위한 변수명 설정 //두 입력값은 수신메일주소이다.
		
		
				// 회원이메일최소글자열
				inputBtn.setEnabled(true); 
		
				}else if(email.indexOf("@")!=-1 ) {
					JOptionPane.showMessageDialog(this, "@는 입력할 수 없습니다.");	 
					inputBtn.setEnabled(false);
				}else {   
					JOptionPane.showMessageDialog(this, "이메일 입력이 잘못되었습니다.");	 
					inputBtn.setEnabled(false);
				}
			
				
			}else {
				JOptionPane.showMessageDialog(this, "중복된 메일주소가 있습니다.");
			inputBtn.setEnabled(false);	
			}
		
		}
	
		if(e.getSource() == codeCheckBtn) {
			
			
			
			
			code = codeT.getText();
			
			if(code.equals(retrunCode2)){
				JOptionPane.showMessageDialog(this, "메일 인증이 완료되었습니다.");
				inputBtn.setEnabled(true); 
				}else{JOptionPane.showMessageDialog(this, "인증번호가 일치하지 않습니다.");
				inputBtn.setEnabled(false); 
				}
		}
	
		
		
		
		
		// TODO 버튼 기능 끝부분
	}//actionPerformed(ActionEvent e)




	
	// private boolean inputMember()
	private void inputMember() {
		//데이터
		
		String id = idT.getText();
		String pwd = pwdT.getText();
		String pwdCheck = pwdCheckT.getText(); 
		String name = nameT.getText();
		String age = ageT.getText();
		String phone = phoneT.getText();
		String address = addressT.getText();
		String email = emailT.getText();
		String email2 = (String)emailComboBox.getSelectedItem();
		// TODO 회원정보 입력부분 메소드


		ProjectMemberDTO memberDTO = new ProjectMemberDTO();

		memberDTO.setPosition(0);
		memberDTO.setId(id);
		memberDTO.setPwd(pwd);
		memberDTO.setName(name);
		memberDTO.setAge(age);
		memberDTO.setPhone(phone);
		memberDTO.setAddress(address);
		memberDTO.setEmail(email);
		memberDTO.setEmail2(email2);
		// private boolean inputMember()	이었을시 	
		// if(true) return 
		ProjectMemberDAO memberDAO = ProjectMemberDAO.getInstance();

		

		//TODO 유효성 검사
		if(id.length()>=4) {// 회원아이디최소글자열
			idJTA.setText("사용 가능한 아이디입니다.");
			idJTA.setForeground(Color.blue);
		}else {
			//JOptionPane.showMessageDialog(this, "아이디는 최소 4자리를 입력해야 합니다.");	 
			idJTA.setText("4자리 이상 입력");
			idJTA.setForeground(Color.red);
			return;

		}
		if(pwd.length()>=8) {
		pwdJTA.setText("사용 가능한 비밀번호 입니다.");
		pwdJTA.setForeground(Color.blue);// 회원비밀번호최소글자열
		}else {   
			//JOptionPane.showMessageDialog(this, "비밀번호는 8자리 이상 입력해주세요.");	
			pwdJTA.setText("8자리 이상 입력해주세요.");
			pwdJTA.setForeground(Color.red);
			
			
			return;
		}//"2"+"3"+"4"+"5"+"6"+"7"+"8"+"9"+"0")==-1
		if(name.length()>=2 && name.indexOf("1")==-1&& name.indexOf("2")==-1&& name.indexOf("3")==-1&& name.indexOf("4")==-1&& name.indexOf("5")==-1
				&& name.indexOf("6")==-1&& name.indexOf("7")==-1&& name.indexOf("8")==-1&& name.indexOf("9")==-1&& name.indexOf("0")==-1) {
			// 회원이름최소글자열 // 숫자 입력방지
		}else {
			JOptionPane.showMessageDialog(this, "실명을 정확하게 입력해주세요.");	 
			return;
		}
		if(age.length()==6) {// 회원생년월일최소글자열
		}else {   
			JOptionPane.showMessageDialog(this, "생년월일을 정확하게 입력해주세요.[입력예시]2002년3월5일 → 020305");	 
			return;
		}
		if(phone.length()>=10) // 회원핸드폰번호최소글자열
			if (phone.indexOf("-")==-1){   
			//성공 
		}else {JOptionPane.showMessageDialog(this, "-는 입력할 수 없습니다. [입력예시]010-9876-5432 → 01098765432");	 
		return;
				
			
		}else {   
			JOptionPane.showMessageDialog(this, "휴대폰번호를 정확하게 입력해주세요. [입력예시]010-9876-5432 → 01098765432");	 
			return;	
		
		}
		if(address.length()>=10) {// 회원주소최소글자열
		}else {   
			JOptionPane.showMessageDialog(this, "주소를 정확하게 입력해주세요. [입력예시] ?도 ?시 ?구 ?대로 123번길 장소?동?호");	 
			return;
		}if(email.length()>=4) {// 회원이메일최소글자열
		}else {   
			JOptionPane.showMessageDialog(this, "이메일을 정확하게 입력해주세요. [입력예시]계정명@메일선택");	 
			return;
		
		
		}



		//dao에게 Beans 전달
		


		
		
		
		

		if(check = true) {
			code = codeT.getText();
			if(!code.equals(retrunCode2)){
				JOptionPane.showMessageDialog(this, "인증번호가 일치하지 않습니다.");
				inputBtn.setEnabled(false); 
			// 저장 성공 //TODO : 저장성공 멘트 뜨게하기
			//remove();
			}else{
			
				
			Boolean check = memberDAO.add(memberDTO);	
			JOptionPane.showMessageDialog(this, "환영합니다! 비트199회원가입이 완료되었습니다.");
			setVisible(false);
			}
		}

	
	}

}//inputMember()

