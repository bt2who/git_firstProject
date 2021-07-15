package book.mypage;




	import java.awt.Color;
	import java.awt.Container;
	import java.awt.Font;
	import java.awt.Graphics;
	import java.awt.Image;
	import java.awt.Toolkit;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	import javax.swing.ImageIcon;
	import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JPanel;
	import javax.swing.JPasswordField;
	import javax.swing.JTextArea;
	import javax.swing.JTextField;

import book.DAODTO.ProjectMemberDAO;
import book.DAODTO.ProjectMemberDTO;
	import book.DAODTO.ProjectMemberUpdateDAO;



	public class ProjectMemberInfoForm  extends JFrame implements ActionListener {
		private int num;
		private String loginId, loginPwd;
		private JPanel  namePanel,birthPanel, pwdPanel, phonePanel,newPwdPanel, againPwdPanel, addressPanel,emailPanel;
		private JLabel titleLabel, birthLabel, nameLabel, pwdLabel,  newPwdLabel, againPwdLabel,emailLabel,  phoneLabel, addressLabel, withdrawalLabel;
		private JLabel birthLabel2,addressLabel2,phoneLabel2, nowPwdLabel2; 
		private JTextField birthTextField, nameTextField, phoneTextField, addressTextField,emailTextField;
		private JPasswordField nowPwdTextField, newPwdTextField, againPwdTextField;
		private JButton backButton;
		//TODO 새로운 변수값
		//private JTextArea idJTA,pwdJTA, pwdCheckJTA; 
		
		public ProjectMemberInfoForm(int num, String loginId, String loginPwd) {
			this.num = num;
			this.loginId = loginId;
			this.loginPwd = loginPwd;   //TODO 다시 활성화 시킬것
		
		
	//	public ProjectMemberInfoForm() {//TODO 비활성화 시킬것
			
			setLayout(null);
			
			//JPaenl 생성
			namePanel = new JPanel();
			namePanel.setBackground(new Color(247, 247, 247));
			namePanel.setBounds(100, 155, 150, 40);
			
			birthPanel = new JPanel();
			birthPanel.setBackground(new Color(247, 247, 247));
			birthPanel.setBounds(100, 220, 150, 40);
			
			emailPanel = new JPanel();
			emailPanel.setBackground(new Color(247, 247, 247));
			emailPanel.setBounds(100, 320, 150, 40);
			
//			newPwdPanel = new JPanel();
//			newPwdPanel.setBackground(new Color(247,247,247));
//			newPwdPanel.setBounds(100,382,150,40);
//			
//			againPwdPanel = new JPanel();
//			againPwdPanel.setBackground(new Color(247,247,247));
//			againPwdPanel.setBounds(100,442,150,40);
//			
			phonePanel = new JPanel();
			phonePanel.setBackground(new Color(247, 247, 247));
			phonePanel.setBounds(100, 520, 150, 40);
			
			addressPanel = new JPanel();
			addressPanel.setBackground(new Color(247, 247, 247));
			addressPanel.setBounds(100, 610, 150, 40);
			
			
			//JLabel 생성
			titleLabel = new JLabel("회원정보 확인");
			titleLabel.setFont(new Font("중나좋체 Light",Font.BOLD,35));
			titleLabel.setBounds(300, 40, 460, 80);
				
			nameLabel = new JLabel("이름");
			nameLabel.setFont(new Font("중나좋체 Light",Font.BOLD,25));
			namePanel.add(nameLabel);
			
			birthLabel = new JLabel("생년월일");
			birthLabel.setFont(new Font("중나좋체 Light",Font.BOLD,25));
			birthPanel.add(birthLabel);
			
			
			emailLabel = new JLabel("이메일");
			emailLabel.setFont(new Font("중나좋체 Light",Font.BOLD,25));
			emailPanel.add(emailLabel);
			
//			birthLabel2 =new JLabel("[입력예시]2002년3월5일 → 020305");
//			birthLabel2.setBounds(340,240,200,40); 
			
//			addressLabel2 =new JLabel("[입력예시]010-9876-5432 → 010-9876-5432");
//			addressLabel2.setBounds(340,545,250,40);
//			
//			phoneLabel2 =new JLabel("[입력예시]?도 ?시 ?구 ?대로 123번길 장소?동?호");
//			phoneLabel2.setBounds(340,630,300,40);
			
			
			
//			newPwdLabel = new JLabel("새 비밀번호");
//			newPwdLabel.setFont(new Font("중나좋체 Light",Font.BOLD,25));
//			newPwdPanel.add(newPwdLabel);
//			
//			againPwdLabel = new JLabel("새 비밀번호 확인");
//			againPwdLabel.setFont(new Font("중나좋체 Light",Font.BOLD,22));
//			againPwdPanel.add(againPwdLabel);
//			
//			
//			nowPwdLabel2 = new JLabel("현재 비밀번호 입력");
//			nowPwdLabel2.setFont(new Font("중나좋체 Light",Font.PLAIN,13));
//			nowPwdLabel2.setBounds(346, 344, 100, 40);
//			
//			newPwdLabel2 = new JLabel("새 비밀번호 입력 8자리 이상");
//			newPwdLabel2.setFont(new Font("중나좋체 Light",Font.PLAIN,13));
//			newPwdLabel2.setBounds(346, 396, 150, 50);
//			
//			againPwdLabel2 = new JLabel("비밀번호 다시 입력");
//			againPwdLabel2.setFont(new Font("중나좋체 Light",Font.PLAIN,13));
//			againPwdLabel2.setBounds(346, 459, 100, 50);
			
//			idJTA = new JTextArea("현재 비밀번호 입력");
//			idJTA.setForeground(Color.gray);
//			idJTA.setFont(new Font("중나좋체 Light",Font.PLAIN,13));
//			idJTA.setBounds(346, 344, 100, 40); 
			
//			pwdJTA = new JTextArea("새 비밀번호 입력 8자리 이상");
//			pwdJTA.setForeground(Color.gray);
//			pwdJTA.setFont(new Font("중나좋체 Light",Font.PLAIN,13));
//			pwdJTA.setBounds(346, 417, 150, 20); 
//			
//			pwdCheckJTA = new JTextArea("비밀번호 다시 입력");
//			pwdCheckJTA.setForeground(Color.gray);
//			pwdCheckJTA.setFont(new Font("중나좋체 Light",Font.PLAIN,13));
//			pwdCheckJTA.setBounds(346, 477, 100, 50); 
			
			
			
			phoneLabel = new JLabel("휴대폰 번호");
			phoneLabel.setFont(new Font("중나좋체 Light",Font.BOLD,25));
			phonePanel.add(phoneLabel);
			
			addressLabel = new JLabel("주소");
			addressLabel.setFont(new Font("중나좋체 Light",Font.BOLD,25));
			addressPanel.add(addressLabel);
			
//			withdrawalLabel = new JLabel("탈퇴를 원하시면 우측의 회원탈퇴 버튼을 눌러주세요");
//			withdrawalLabel.setFont(new Font("중나좋체 Light",Font.PLAIN,13));
//			withdrawalLabel.setBounds(480, 395, 500, 595);
			
			
			
			//JTextField 생성
			nameTextField = new JTextField();
			nameTextField.setBounds(340,159,255,30);
			
			
			birthTextField = new JTextField();
			birthTextField.setBounds(340,221,255,30);
			
			emailTextField = new JTextField();
			emailTextField.setBounds(340,324,255,30);
			
			newPwdTextField = new JPasswordField();
			newPwdTextField.setBounds(340,383,255,30);
			
			againPwdTextField = new JPasswordField();
			againPwdTextField.setBounds(340,445,255,30);
			
			phoneTextField = new JTextField();
			phoneTextField.setBounds(340,527,255,30);
			
			addressTextField = new JTextField();
			addressTextField.setBounds(340,612,255,30);
			
			
			//JButton 생성
			backButton = new JButton(new ImageIcon("beforebtn.png"));//이전으로
			backButton.setBounds(750,90,90,30);
			backButton.setBorderPainted(false);
		
//			nameButton = new JButton(new ImageIcon("image/namebtn.png"));//이름변경
//			nameButton.setBounds(640,159,100,30);
//			nameButton.setBorderPainted(false);
//			
//			birthButton = new JButton(new ImageIcon("image/birthbtn.png"));//생일변경
//			birthButton.setBounds(640,221,130,30);
//			birthButton.setBorderPainted(false);
//			
//			pwdButton = new JButton(new ImageIcon("image/pwdbtn.png"));//비번변경
//			pwdButton.setBounds(640,445,130,30);
//			pwdButton.setBorderPainted(false);
//			
//			phoneButton = new JButton(new ImageIcon("image/phonebtn.png"));//번호변경
//			phoneButton.setBounds(640,527,150,30);
//			phoneButton.setBorderPainted(false);
//			
//			addressButton = new JButton(new ImageIcon("image/addressbtn.png"));//주소변경
//			addressButton.setBounds(640,612,100,30);
//			addressButton.setBorderPainted(false);
//			
//			withdrawalButton = new JButton(new ImageIcon("image/talbtn.png"));//탈퇴
//			withdrawalButton.setBounds(750,678,90,30);
//			withdrawalButton.setBorderPainted(false);
			
			
			Container con = this.getContentPane();
		con.add(titleLabel);
		con.add(backButton);
//			//생년월일
		con.add(birthPanel);
		con.add(birthTextField);
//			//con.add(birthButton);
//			con.add(birthLabel2);
//			//name
		con.add(namePanel);
			con.add(nameTextField);
//			//con.add(nameButton);
//			//pwd
		con.add(emailPanel);
//			//con.add(pwdJTA);
//			//con.add(pwdCheckJTA);
//			con.add(nowPwdLabel2);
////			con.add(newPwdLabel2);
////			con.add(againPwdLabel2);
//			//con.add(pwdCheckJTA);
//			
//		con.add(nowPwdTextField);
//
//			con.add(newPwdPanel);
//			
//			
	con.add(emailTextField);
//			con.add(againPwdPanel);
//		
//			
//			con.add(againPwdTextField);
//			//con.add(pwdButton);
//			//phone
			con.add(phonePanel);
			con.add(phoneTextField);
//			//con.add(phoneButton);
//			con.add(phoneLabel2);
//			//address0
			con.add(addressPanel);
//			con.add(addressLabel2);
		con.add(addressTextField);
////			con.add(addressButton);
////			//withdrawalLabel
////			con.add(withdrawalLabel);
////			con.add(withdrawalButton);
			

			con.setBackground(Color.WHITE);
			setBounds(200, 150, 1200, 800);
			setResizable(false);
			setVisible(true);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			
			
			
			//event	처리
			backButton.addActionListener(this);
//			withdrawalButton.addActionListener(this);
//			birthButton.addActionListener(this);
//			nameButton.addActionListener(this);
//			pwdButton.addActionListener(this);
//			phoneButton.addActionListener(this);
//			addressButton.addActionListener(this);
			
			loadMemberInfo();
				
			
			
		} //MypageForm()
	
	//-----------------------------------------------------------------	
		public void paint(Graphics g){
			super.paint(g);
			g.setColor(Color.LIGHT_GRAY);
			g.drawLine(90, 160, 860, 160);
			g.drawLine(90, 705, 860, 705);
			g.drawLine(290, 160, 290, 704);
			
			Toolkit t = Toolkit.getDefaultToolkit();
			Image img = t.getImage("logo.png");
	        g.drawImage(img,
	    				90, 70, 265, 140,       //화면 위치 - 고정되면 안됨
	    				0, 0, 1650, 768,        //이미지 위치
	                    this);
			
		} //paint(Graphics g)


	//-----------------------------------------------------------------	
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == backButton) {
				new ProjectBookList(num, loginId, loginPwd);   //TODO 다시 활성화시킬것 
				setVisible(false);
			
			}

			
			
		} 
		
	//-----------------------------------------------------------------	
		
	
		
		
			
				
		

//			nowPwdTextField.setText("");
//			newPwdTextField.setText("");
//			againPwdTextField.setText("");
			
		
	
		

	//------------------------------------------------------
		
		
//		

		private void loadMemberInfo() {
			ProjectMemberDAO memberDAO = ProjectMemberDAO.getInstance();
			
			
			ProjectMemberDTO dto =memberDAO.memberLoad(loginId, loginPwd);
			
			nameTextField.setText(dto.getName());
			birthTextField.setText(dto.getAge());
			phoneTextField.setText(dto.getPhone());
			addressTextField.setText(dto.getAddress());
			emailTextField.setText(dto.getEmail()+dto.getEmail2());
			
			
		}	
			
//		public static void main(String[] args) {
//			new ProjectMemberInfoForm();
//}
	}	
	
