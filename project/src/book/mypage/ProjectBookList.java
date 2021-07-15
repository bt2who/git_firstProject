package book.mypage;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import book.manager.ProjectManagerMain;
import book.type.ProjectArt;
import book.type.ProjectEassay;
import book.type.ProjectHumanities;
import book.type.ProjectNaturalScience;
import book.type.ProjectNovel;
import book.type.ProjectPoem;
import book.type.ProjectTotal;

public class ProjectBookList extends JFrame implements ActionListener {// 도서목록
	private JLabel logoPic;
	private JComboBox<String> subjectComboBox; // 말머리
	private JTextField searchT; // 검색창
	private JButton searchBtn; // 검색버튼
	private JButton mypageBtn, cartBtn, orderBtn, backButton,memberInfoButton;
	private JTabbedPane menu;// 탭페인
	private JPanel totalP, nsP, poemP, humanP, novelP, essayP, artP;// 메뉴탭
	private ProjectTotal total;
	private ProjectNaturalScience naturalScience;
	private ProjectPoem poem;
	private ProjectHumanities humanities;
	private ProjectNovel novel;
	private ProjectEassay eassay;
	private ProjectArt art;
	private int num;
	private String loginId;
	private String loginPwd;

	public ProjectBookList(int num, String loginId, String loginPwd) {// 생성자
		this.num = num;
		this.loginId = loginId;
		this.loginPwd = loginPwd;
		setLayout(null);
//-------------------------------------------------------------------------
		//메뉴생성
		mypageBtn = new JButton(new ImageIcon("updatebtn.png"));
		mypageBtn.setBorderPainted(false);
		mypageBtn.setBounds(690,40,100,30);
		cartBtn = new JButton(new ImageIcon("cartbtn.png"));
		cartBtn.setBorderPainted(false);
		cartBtn.setBounds(800,40,100,30);
		orderBtn = new JButton(new ImageIcon("orderlist.png"));
		orderBtn.setBounds(910,40,100,30);
		orderBtn.setBorderPainted(false);
		backButton = new JButton(new ImageIcon("beforebtn.png"));
		backButton.setBounds(1020,40,90,30);
		backButton.setBorderPainted(false);
	
		
		memberInfoButton = new JButton(new ImageIcon("infoBtn.png"));
		memberInfoButton.setBounds(580,40,100,30);
		memberInfoButton.setBorderPainted(false);
		
		//로고사진
		logoPic = new JLabel(new ImageIcon("logo2.png"));
		logoPic.setBounds(50,20,310,150);
		
	
		//검색창
		String [] subject = {"제목","저자명","출판사"};
		subjectComboBox = new JComboBox<String>(subject);
		subjectComboBox.setFont(new Font("중나좋체 Light",Font.PLAIN,20));
		subjectComboBox.setBounds(390,100,85,30);
		searchT = new JTextField();
		searchT.setBounds(480,100,255,30);
		searchBtn = new JButton(new ImageIcon("search.png"));
		searchBtn.setBorderPainted(false);
		searchBtn.setBounds(740,100,85,30);
		searchBtn.setFont(new Font("중나좋체 Light",Font.PLAIN,20));
				
		
		
		//클래스 불러오기! 생성해줌!
		total = new ProjectTotal(num,loginId); //new를 해줘서 새로운클래스를 불러옴
		naturalScience =new ProjectNaturalScience(num,loginId);
		poem = new ProjectPoem(num,loginId);
		humanities = new ProjectHumanities(num,loginId);
		novel = new ProjectNovel(num,loginId);
		eassay = new ProjectEassay(num,loginId);
		art = new ProjectArt(num,loginId);
		
		
		
		
		//메뉴탭 생성
		menu = new JTabbedPane(SwingConstants.LEFT);
		menu.setBounds(80,180,1000,550);
		menu.setFont(new Font("중나좋체 Light",Font.PLAIN,20));
		menu.addTab("전체목록", total);
		menu.addTab("자연과학", naturalScience);
		menu.addTab("시", poem);
		menu.addTab("인문학", humanities);
		menu.addTab("소설", novel);
		menu.addTab("에세이", eassay);
		menu.addTab("예술", art);
				
		
		//컨테이너
		Container c = this.getContentPane();
		if (num == 0) {
			c.add(mypageBtn);
			c.add(cartBtn);
			c.add(memberInfoButton);
		}
		c.add(orderBtn);
		c.add(backButton);
		
		c.add(subjectComboBox);
		c.add(searchT);
		c.add(searchBtn);
		
		c.add(logoPic);
		
		c.setBackground(Color.WHITE);
		c.add("LEFT",menu);
		
		setVisible(true);
		setTitle("도서목록");
		setBounds(50,50,1200,800);
		//setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		event();
		
	}//BookList() 
	// 이벤트
	private void event() {
		searchBtn.addActionListener(this); // 검색버튼이벤트
		mypageBtn.addActionListener(this);
		orderBtn.addActionListener(this);
		cartBtn.addActionListener(this);
		backButton.addActionListener(this);
		memberInfoButton.addActionListener(this);
		menu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getSource()==menu) {
					total.reset();
					art.reset();
					eassay.reset();
					humanities.reset();
					naturalScience.reset();
					novel.reset();
					poem.reset();
					
					searchT.setText("");
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == searchBtn) {// 검색메소드
			if (menu.getSelectedComponent() == total) { // 토탈탭에서 검색버튼을 눌렀을때
				total.search(subjectComboBox.getSelectedIndex(), searchT.getText());
			} else if (menu.getSelectedComponent() == naturalScience) {// 자연과학탭에서 검색버튼을 눌렀을때
				naturalScience.search(subjectComboBox.getSelectedIndex(), searchT.getText());
			} else if (menu.getSelectedComponent() == poem) {// 시탭에서 검색버튼을 눌렀을때
				poem.search(subjectComboBox.getSelectedIndex(), searchT.getText());
			} else if (menu.getSelectedComponent() == humanities) {// 인문학탭에서 검색버튼을 눌렀을때
				humanities.search(subjectComboBox.getSelectedIndex(), searchT.getText());
			} else if (menu.getSelectedComponent() == novel) {// 소설탭에서 검색버튼을 눌렀을때
				novel.search(subjectComboBox.getSelectedIndex(), searchT.getText());
			} else if (menu.getSelectedComponent() == eassay) {// 에세이탭에서 검색버튼을 눌렀을때
				eassay.search(subjectComboBox.getSelectedIndex(), searchT.getText());
			} else if (menu.getSelectedComponent() == art) {// 예술탭에서 검색버튼을 눌렀을때
				art.search(subjectComboBox.getSelectedIndex(), searchT.getText());
			}
		} else if (e.getSource() == memberInfoButton) {
			new ProjectMemberInfoForm(num, loginId, loginPwd);
			setVisible(false);
		} else if (e.getSource() == mypageBtn) {
			new ProjectMemberUpdateForm(num, loginId, loginPwd);
			setVisible(false);
		} else if (e.getSource() == orderBtn) {
			new ProjectOrderListForm(num, loginId, loginPwd);
			setVisible(false);
		} else if (e.getSource() == cartBtn) {
			new ProjectCartForm(num, loginId, loginPwd);
			setVisible(false);
		}else if (e.getSource() == backButton) { 
			if (num== 0) {
				new ProjectMemberLogin();
				setVisible(false);   
			}else{
				new ProjectManagerMain(); 
				setVisible(false);}
		} //if
		

	}

}
