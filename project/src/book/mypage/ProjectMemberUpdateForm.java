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

import book.DAODTO.ProjectMemberDTO;
import book.DAODTO.ProjectMemberUpdateDAO;



public class ProjectMemberUpdateForm extends JFrame implements ActionListener {
	private int num;
	private String loginId, loginPwd;
	private JPanel  namePanel,birthPanel, pwdPanel, phonePanel,newPwdPanel, againPwdPanel, addressPanel;
	private JLabel titleLabel, birthLabel, nameLabel, pwdLabel,  newPwdLabel, againPwdLabel,  phoneLabel, addressLabel, withdrawalLabel;
	private JLabel birthLabel2,addressLabel2,phoneLabel2, nowPwdLabel2; 
	private JTextField birthTextField, nameTextField, nowPwdTextField, newPwdTextField, againPwdTextField, phoneTextField, addressTextField;
	private JButton backButton, birthButton, nameButton, pwdButton, phoneButton, addressButton, withdrawalButton;
	//TODO 새로운 변수값
	private JTextArea idJTA,pwdJTA, pwdCheckJTA; 
	
	public ProjectMemberUpdateForm(int num, String loginId, String loginPwd) {
		this.num = num;
		this.loginId = loginId;
		this.loginPwd = loginPwd;   //TODO 다시 활성화 시킬것
	
	
	//public MemberUpdateForm() {//TODO 비활성화 시킬것
		
		setLayout(null);
		
		//JPaenl 생성
		namePanel = new JPanel();
		namePanel.setBackground(new Color(247, 247, 247));
		namePanel.setBounds(100, 155, 150, 40);
		
		birthPanel = new JPanel();
		birthPanel.setBackground(new Color(247, 247, 247));
		birthPanel.setBounds(100, 220, 150, 40);
		
		pwdPanel = new JPanel();
		pwdPanel.setBackground(new Color(247, 247, 247));
		pwdPanel.setBounds(100, 320, 150, 40);
		
		newPwdPanel = new JPanel();
		newPwdPanel.setBackground(new Color(247,247,247));
		newPwdPanel.setBounds(100,382,150,40);
		
		againPwdPanel = new JPanel();
		againPwdPanel.setBackground(new Color(247,247,247));
		againPwdPanel.setBounds(100,442,150,40);
		
		phonePanel = new JPanel();
		phonePanel.setBackground(new Color(247, 247, 247));
		phonePanel.setBounds(100, 520, 150, 40);
		
		addressPanel = new JPanel();
		addressPanel.setBackground(new Color(247, 247, 247));
		addressPanel.setBounds(100, 610, 150, 40);
		
		
		//JLabel 생성
		titleLabel = new JLabel("회원정보 수정");
		titleLabel.setFont(new Font("중나좋체 Light",Font.BOLD,35));
		titleLabel.setBounds(300, 40, 460, 80);
			
		nameLabel = new JLabel("이름");
		nameLabel.setFont(new Font("중나좋체 Light",Font.BOLD,25));
		namePanel.add(nameLabel);
		
		birthLabel = new JLabel("생년월일");
		birthLabel.setFont(new Font("중나좋체 Light",Font.BOLD,25));
		birthPanel.add(birthLabel);
		
		birthLabel2 =new JLabel("[입력예시]2002년3월5일 → 020305");
		birthLabel2.setBounds(340,240,200,40); 
		
		addressLabel2 =new JLabel("[입력예시]010-9876-5432 → 010-9876-5432");
		addressLabel2.setBounds(340,545,250,40);
		
		phoneLabel2 =new JLabel("[입력예시]?도 ?시 ?구 ?대로 123번길 장소?동?호");
		phoneLabel2.setBounds(340,630,300,40);
		
		pwdLabel = new JLabel("비밀번호");
		pwdLabel.setFont(new Font("중나좋체 Light",Font.BOLD,25));
		pwdPanel.add(pwdLabel);
		
		newPwdLabel = new JLabel("새 비밀번호");
		newPwdLabel.setFont(new Font("중나좋체 Light",Font.BOLD,25));
		newPwdPanel.add(newPwdLabel);
		
		againPwdLabel = new JLabel("새 비밀번호 확인");
		againPwdLabel.setFont(new Font("중나좋체 Light",Font.BOLD,22));
		againPwdPanel.add(againPwdLabel);
		
		
		nowPwdLabel2 = new JLabel("현재 비밀번호 입력");
		nowPwdLabel2.setFont(new Font("중나좋체 Light",Font.PLAIN,13));
		nowPwdLabel2.setBounds(346, 344, 100, 40);
//		
//		newPwdLabel2 = new JLabel("새 비밀번호 입력 8자리 이상");
//		newPwdLabel2.setFont(new Font("중나좋체 Light",Font.PLAIN,13));
//		newPwdLabel2.setBounds(346, 396, 150, 50);
//		
//		againPwdLabel2 = new JLabel("비밀번호 다시 입력");
//		againPwdLabel2.setFont(new Font("중나좋체 Light",Font.PLAIN,13));
//		againPwdLabel2.setBounds(346, 459, 100, 50);
		
//		idJTA = new JTextArea("현재 비밀번호 입력");
//		idJTA.setForeground(Color.gray);
//		idJTA.setFont(new Font("중나좋체 Light",Font.PLAIN,13));
//		idJTA.setBounds(346, 344, 100, 40); 
		
		pwdJTA = new JTextArea("새 비밀번호 입력 8자리 이상");
		pwdJTA.setForeground(Color.gray);
		pwdJTA.setFont(new Font("중나좋체 Light",Font.PLAIN,13));
		pwdJTA.setBounds(346, 417, 150, 20); 
		
		pwdCheckJTA = new JTextArea("비밀번호 다시 입력");
		pwdCheckJTA.setForeground(Color.gray);
		pwdCheckJTA.setFont(new Font("중나좋체 Light",Font.PLAIN,13));
		pwdCheckJTA.setBounds(346, 477, 100, 50); 
		
		
		
		phoneLabel = new JLabel("휴대폰 번호");
		phoneLabel.setFont(new Font("중나좋체 Light",Font.BOLD,25));
		phonePanel.add(phoneLabel);
		
		addressLabel = new JLabel("주소");
		addressLabel.setFont(new Font("중나좋체 Light",Font.BOLD,25));
		addressPanel.add(addressLabel);
		
		withdrawalLabel = new JLabel("탈퇴를 원하시면 우측의 회원탈퇴 버튼을 눌러주세요");
		withdrawalLabel.setFont(new Font("중나좋체 Light",Font.PLAIN,13));
		withdrawalLabel.setBounds(480, 395, 500, 595);
		
		
		
		//JTextField 생성
		nameTextField = new JTextField();
		nameTextField.setBounds(340,159,255,30);
		
		
		birthTextField = new JTextField();
		birthTextField.setBounds(340,221,255,30);
		
		nowPwdTextField = new JTextField();
		nowPwdTextField.setBounds(340,324,255,30);
		
		newPwdTextField = new JTextField();
		newPwdTextField.setBounds(340,383,255,30);
		
		againPwdTextField = new JTextField();
		againPwdTextField.setBounds(340,445,255,30);
		
		phoneTextField = new JTextField();
		phoneTextField.setBounds(340,527,255,30);
		
		addressTextField = new JTextField();
		addressTextField.setBounds(340,612,255,30);
		
		
		//JButton 생성
		backButton = new JButton(new ImageIcon("beforebtn.png"));//이전으로
		backButton.setBounds(750,90,90,30);
		backButton.setBorderPainted(false);
	
		nameButton = new JButton(new ImageIcon("namebtn.png"));//이름변경
		nameButton.setBounds(640,159,100,30);
		nameButton.setBorderPainted(false);
		
		birthButton = new JButton(new ImageIcon("birthbtn.png"));//생일변경
		birthButton.setBounds(640,221,130,30);
		birthButton.setBorderPainted(false);
		
		pwdButton = new JButton(new ImageIcon("pwdbtn.png"));//비번변경
		pwdButton.setBounds(640,445,130,30);
		pwdButton.setBorderPainted(false);
		
		phoneButton = new JButton(new ImageIcon("phonebtn.png"));//번호변경
		phoneButton.setBounds(640,527,150,30);
		phoneButton.setBorderPainted(false);
		
		addressButton = new JButton(new ImageIcon("addressbtn.png"));//주소변경
		addressButton.setBounds(640,612,100,30);
		addressButton.setBorderPainted(false);
		
		withdrawalButton = new JButton(new ImageIcon("talbtn.png"));//탈퇴
		withdrawalButton.setBounds(750,678,90,30);
		withdrawalButton.setBorderPainted(false);
		
		
		Container con = this.getContentPane();
		con.add(titleLabel);
		con.add(backButton);
		//생년월일
		con.add(birthPanel);
		con.add(birthTextField);
		con.add(birthButton);
		con.add(birthLabel2);
		//name
		con.add(namePanel);
		con.add(nameTextField);
		con.add(nameButton);
		//pwd
		con.add(pwdPanel);
		con.add(pwdJTA);
		con.add(pwdCheckJTA);
		con.add(nowPwdLabel2);
//		con.add(newPwdLabel2);
//		con.add(againPwdLabel2);
		con.add(pwdCheckJTA);
		
		con.add(nowPwdTextField);

		con.add(newPwdPanel);
		
		
		con.add(newPwdTextField);
		con.add(againPwdPanel);
	
		
		con.add(againPwdTextField);
		con.add(pwdButton);
		//phone
		con.add(phonePanel);
		con.add(phoneTextField);
		con.add(phoneButton);
		con.add(phoneLabel2);
		//address0
		con.add(addressPanel);
		con.add(addressLabel2);
		con.add(addressTextField);
		con.add(addressButton);
		//withdrawalLabel
		con.add(withdrawalLabel);
		con.add(withdrawalButton);
		

		con.setBackground(Color.WHITE);
		setBounds(200, 150, 1200, 800);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		//event	처리
		backButton.addActionListener(this);
		withdrawalButton.addActionListener(this);
		birthButton.addActionListener(this);
		nameButton.addActionListener(this);
		pwdButton.addActionListener(this);
		phoneButton.addActionListener(this);
		addressButton.addActionListener(this);
		
		
		
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
		
		
		} else if(e.getSource() == withdrawalButton) {
			new ProjectMemberWithdrawalForm(num, loginId, loginPwd);
			setVisible(false);
		} else if(e.getSource() == birthButton) {
			updateBirth(); 
		} else if(e.getSource() == nameButton) {
			updateName();
			
		} else if(e.getSource() == pwdButton) {
			updatePwd();
			
		} else if(e.getSource() == phoneButton) {
			updatePhone();
			
		} else if(e.getSource() == addressButton) {
			
			updateAddress();
			
		}
		
		
	} //actionPerformed(ActionEvent e)
	
	
//-----------------------------------------------------------------	
	
	private void updateAddress() {
		String address = addressTextField.getText();
		//TODO 유효성검사(배송주소) 1/5
		if(address.length()>=10) {// 회원주소최소글자열
		}else {   
			JOptionPane.showMessageDialog(this, "주소를 정확하게 입력해주세요. [입력예시] ?도 ?시 ?구 ?대로 123번길 장소?동?호");	 
			return;
		}//TODO 유효성검사(배송주소) 1/5
		
		
		ProjectMemberDTO memberDTO = new ProjectMemberDTO();
		memberDTO.setId(loginId);
		memberDTO.setAddress(address);
		
		
		//DB - update
		ProjectMemberUpdateDAO memberUpdateDAO = ProjectMemberUpdateDAO.getInstance();
		memberUpdateDAO.updateAddress(memberDTO);
		
		addressTextField.setText("");
		
		JOptionPane.showMessageDialog(null, "주소가 변경되었습니다");
		
	} //updateAddress()
	
	private void updatePhone() {
		String phone = phoneTextField.getText();
		//TODO 유효성검사 (폰번호) 2/5
		if(phone.length()>=10) // 회원핸드폰번호최소글자열
			if (phone.indexOf("-")==-1){   
			//성공 
		}else {JOptionPane.showMessageDialog(this, "-는 입력할 수 없습니다. [입력예시]010-9876-5432 → 01098765432");	 
		return;
				
			
		}else {   
			JOptionPane.showMessageDialog(this, "휴대폰번호를 정확하게 입력해주세요. [입력예시]010-9876-5432 → 01098765432");	 
			return;	
		}
		//TODO 유효성검사 (폰번호) 2/5
		ProjectMemberDTO memberDTO = new ProjectMemberDTO();
		memberDTO.setId(loginId);
		memberDTO.setPhone(phone);
		
		
		//DB - update
		ProjectMemberUpdateDAO memberUpdateDAO = ProjectMemberUpdateDAO.getInstance();
		memberUpdateDAO.updatePhone(memberDTO);
		
		
		
		phoneTextField.setText("");
		JOptionPane.showMessageDialog(null, "전화번호가 변경되었습니다");
		
	} //updatePhone()
	
	private void updatePwd() {

		ProjectMemberDTO memberDTO = new ProjectMemberDTO();
		// memberDTO.setPwd(loginPwd);

		// DB - update
		if (nowPwdTextField.getText().equals(loginPwd)) {
			if (newPwdTextField.getText().equals(againPwdTextField.getText())) {

				if (newPwdTextField.getText().length()>=8) {

					String pwd = againPwdTextField.getText();

					memberDTO.setId(loginId);
					memberDTO.setPwd(pwd);

					ProjectMemberUpdateDAO memberUpdateDAO = ProjectMemberUpdateDAO.getInstance();
					memberUpdateDAO.updatePwd(memberDTO);

					JOptionPane.showMessageDialog(null, "비밀번호가 변경되었습니다");

				} else {
					JOptionPane.showMessageDialog(null, "8자리 이상 입력해주세요.");

				}
			} else {
				JOptionPane.showMessageDialog(null, "입력한 비밀번호가 동일하지 않습니다.");

			}
		} else {
			JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다");

		}

		nowPwdTextField.setText("");
		newPwdTextField.setText("");
		againPwdTextField.setText("");

	} // updatePwd()
	
	//TODO 유효성검사 (이름) 3/5
	private void updateName() {
		String name = nameTextField.getText();
		if(name.length()>=2 && name.indexOf("1")==-1&& name.indexOf("2")==-1&& name.indexOf("3")==-1&& name.indexOf("4")==-1&& name.indexOf("5")==-1
				&& name.indexOf("6")==-1&& name.indexOf("7")==-1&& name.indexOf("8")==-1&& name.indexOf("9")==-1&& name.indexOf("0")==-1) {
			// 회원이름최소글자열 // 숫자 입력방지
		}else {
			JOptionPane.showMessageDialog(this, "실명을 정확하게 입력해주세요.");	 
			return;
		}
		//TODO 유효성검사 (이름) 3/5
		         
		ProjectMemberDTO memberDTO = new ProjectMemberDTO();
		memberDTO.setId(loginId);
		memberDTO.setName(name);
		
		
		//DB - update
		ProjectMemberUpdateDAO memberUpdateDAO = ProjectMemberUpdateDAO.getInstance();
		memberUpdateDAO.updateName(memberDTO);
		
		nameTextField.setText("");
		
		JOptionPane.showMessageDialog(null, "이름이 변경되었습니다");
		
	}//updateName()
	
	private void updateBirth() {
		
		String birth = birthTextField.getText();
		//TODO 유효성검사 (생년월일) 4/5
		if(birth.length()==6) {// 회원생년월일최소글자열
		}else {   
			JOptionPane.showMessageDialog(this, "생년월일을 정확하게 입력해주세요.[입력예시]2002년3월5일 → 020305");	 
			return;
		}
		//TODO 유효성검사 (생년월일) 4/5
		ProjectMemberDTO memberDTO = new ProjectMemberDTO();
		memberDTO.setId(loginId);
		memberDTO.setAge(birth);
		//TODO 변수값 변경해야됨 
		
		//DB - update
		ProjectMemberUpdateDAO memberUpdateDAO = ProjectMemberUpdateDAO.getInstance();
		memberUpdateDAO.updateEmail(memberDTO);
		
		birthTextField.setText("");
		
		JOptionPane.showMessageDialog(null, "생년월일이 변경되었습니다");
		
	}//updateEmail()
	

//------------------------------------------------------
	
	
//	public static void main(String[] args) {
//		new MemberUpdateForm();
//	} //main

		
		
}