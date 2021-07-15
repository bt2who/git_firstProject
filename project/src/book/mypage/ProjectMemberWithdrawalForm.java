package book.mypage;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import book.DAODTO.ProjectMemberDTO;
import book.DAODTO.ProjectWithdrawalDAO;

public class ProjectMemberWithdrawalForm extends JFrame implements ActionListener {
	private String loginId, loginPwd;
	private JLabel titleLabel, questionLabel, inquiryLabel, callLabel, significantLabel, securityLabel, idLabel, pwdLabel;
	private JCheckBox agreeCheckBox;
	private JTextField idTextField;
	private JPasswordField pwdTextField;
	private JButton backButton, checkButton;
	private DefaultTableModel model;
	private JTable table;
	private List<ProjectMemberDTO> list;
	private int num;
	
	public ProjectMemberWithdrawalForm(int num, String loginId, String loginPwd) {
		this.num = num;
		this.loginId = loginId;
		this.loginPwd = loginPwd;
		
		setLayout(null);
		
		//JLabel 생성
		titleLabel = new JLabel("회원 탈퇴");
		titleLabel.setFont(new Font("중나좋체 Light",Font.BOLD,35));
		//titleLabel.setForeground(Color.BLUE);
		titleLabel.setBounds(300, 40, 460, 80);
		
		questionLabel = new JLabel("회원님 bit199 서비스를 이용하시는데 불편함이 있으셨나요?");
		questionLabel.setFont(new Font("중나좋체 Light",Font.BOLD,14));
		//questionLabel.setForeground(Color.BLUE);
		questionLabel.setBounds(120, 110, 460, 125);
		
		inquiryLabel = new JLabel("이용 불편 및 각종 문의 사항은 고객센터로 문의 주시면 성심 성의껏 답변 드리겠습니다.");
		inquiryLabel.setFont(new Font("중나좋체 Light",Font.PLAIN,14));
		//inquiryLabel.setForeground(Color.BLUE);
		inquiryLabel.setBounds(120, 130, 760, 145);
		
		callLabel = new JLabel("- 자주 묻는 질문 / 1:1 온라인 문의 / 전화 문의: 010-3678-6435 (365일 오전 9시~오후6시)");
		callLabel.setFont(new Font("중나좋체 Light",Font.PLAIN,14));
		//callLabel.setForeground(Color.BLUE);
		callLabel.setBounds(120, 145, 600, 160);
		
		significantLabel = new JLabel("회원탈퇴 전, 유의사항을 확인해 주시기 바랍니다");
		significantLabel.setFont(new Font("중나좋체 Light",Font.BOLD,14));
		significantLabel.setForeground(Color.DARK_GRAY);
		significantLabel.setBounds(135, 190, 600, 215);
		
		securityLabel = new JLabel("보안을 위해 회원님의 계정 아이디와 비밀번호를 확인합니다");
		securityLabel.setFont(new Font("중나좋체 Light",Font.BOLD,14));
		securityLabel.setForeground(Color.DARK_GRAY);
		securityLabel.setBounds(120, 480, 600, 200);
		
		idLabel = new JLabel("아이디 :");
		idLabel.setFont(new Font("중나좋체 Light",Font.PLAIN,14));
		idLabel.setForeground(Color.DARK_GRAY);
		idLabel.setBounds(120, 520, 600, 200);
		
		pwdLabel = new JLabel("비밀번호 :");
		pwdLabel.setFont(new Font("중나좋체 Light",Font.PLAIN,14));
		pwdLabel.setForeground(Color.DARK_GRAY);
		pwdLabel.setBounds(300, 520, 600, 200);
		
		
		//JCheckBox 생성
		agreeCheckBox = new JCheckBox("상기 bit199 회원탈퇴 시 처리사항 안내를 확인하였음에 동의합니다.");
		agreeCheckBox.setForeground(Color.BLUE);
		agreeCheckBox.setBounds(130, 515, 600, 25);
		agreeCheckBox.setBackground(new Color(231, 200, 226));
		
		
		//JTextField 생성
		idTextField = new JTextField();
		idTextField.setBounds(163, 607, 120, 30);
		
		pwdTextField = new JPasswordField();
		pwdTextField.setBounds(355, 607, 120, 30);
		
		
		//JButton 생성
		backButton = new JButton(new ImageIcon("beforebtn.png"));
		backButton.setBounds(750,90,90,30);
		backButton.setBorderPainted(false);
		
		checkButton = new JButton("본인확인");
		checkButton.setBounds(490, 607, 100, 30);
		
		
		//DB의 모든 레코드를 꺼내서 JList에 뿌리기
//		MemberDAO memberDAO = MemberDAO.getInstance();
//		List<BookOrderDTO> allList = memberDAO.getMemberList();
//		
//		//모델생성
//		model = new DefaultTableModel();
//		
//		//목록생성
//		Vector<Object> vector = new Vector<Object>();
//		vector.add(MemberDTO.getPosition());
		
		
		
		
		
		//Container 생성
		Container con = this.getContentPane();
		con.add(titleLabel);
		con.add(backButton);
		con.add(questionLabel);
		con.add(inquiryLabel);
		con.add(callLabel);
		con.add(significantLabel);
		con.add(securityLabel);
		con.add(agreeCheckBox);
		con.add(idLabel);
		con.add(pwdLabel);
		con.add(idTextField);
		con.add(pwdTextField);
		con.add(checkButton);
		
		
		
		con.setBackground(Color.WHITE);
		setBounds(200, 150, 1200, 800);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		// event 처리
		backButton.addActionListener(this);
		checkButton.addActionListener(this);
		
	} //MemberWithdrawalForm()

//-----------------------------------------------------------------
	public void paint(Graphics g){
		super.paint(g);
		g.setColor(Color.LIGHT_GRAY);
		g.drawLine(90, 160, 900, 160);
		g.drawLine(90, 705, 900, 705);
		g.drawLine(110, 300, 880, 300);
		g.drawLine(110, 580, 880, 580);
		g.drawLine(110, 300, 110, 580);
		g.drawLine(880, 300, 880, 580);
		g.drawLine(110, 530, 880, 530);
		
		
		Toolkit t = Toolkit.getDefaultToolkit();
		Image img = t.getImage("logo.png");
	    g.drawImage(img,
					90, 70, 265, 140,       //화면 위치 - 고정되면 안됨
					0, 0, 1650, 768,        //이미지 위치
	                this);
	    //Toolkit t2 = Toolkit.getDefaultToolkit();
	    Image img2 = t.getImage("significant.png");
	    g.drawImage(img2,
					120, 342, 870, 692,       //화면 위치 - 고정되면 안됨
					0, 0, 1680, 768,        //이미지 위치
	                this);
	    
		
	} //paint(Graphics g)
	
	
//-----------------------------------------------------------------	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backButton) {
			new ProjectMemberUpdateForm(num, loginId, loginPwd);
			setVisible(false);
		} else if(e.getSource() == checkButton) {
			deleteMember();
			new ProjectMemberLogin();
			setVisible(false);
			
		}
		
	} //actionPerformed(ActionEvent e)
	

	//-----------------------------------------------------------------		
	private void deleteMember() {
		String id = idTextField.getText();
		String pwd = pwdTextField.getText();
		ProjectMemberDTO memberDTO = new ProjectMemberDTO();
		if(agreeCheckBox.isSelected()) {
			if(id.equals(loginId) && pwd.equals(loginPwd)) {
				
				memberDTO.setId(loginId);
				memberDTO.setPwd(loginPwd);
				
				
				//DB - delete
				ProjectWithdrawalDAO withdrawalDAO = ProjectWithdrawalDAO.getInstance();
				withdrawalDAO.deleteMember(memberDTO);
				
				JOptionPane.showMessageDialog(null, "탈퇴완료. 그동안 이용해주셔서 감사합니다.");
			
			} else {
				JOptionPane.showMessageDialog(null, "아이디와 비밀번호가 일치하지 않습니다.");
			}
		} else {
			JOptionPane.showMessageDialog(null, "동의하여 주시기 바랍니다");
		}
		
		idTextField.setText("");
		pwdTextField.setText("");
		
	} //deleteMember()
	
	
//	public static void main(String[] args) {
//		new MemberWithdrawalForm();
//	}// main



}