package book.mypage;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import book.DAODTO.ProjectBookOrderDAO;
import book.DAODTO.ProjectBookOrderDTO;
import book.DAODTO.ProjectCartDTO;



public class ProjectOrderListForm extends JFrame implements ActionListener {
	private String loginId, loginPwd;
	private JLabel titleLabel;
	private JButton buyButton,imgButton;
	private DefaultTableModel model;
	private JTable table;
	private List<ProjectBookOrderDTO> list;
	private int num;
	
	public ProjectOrderListForm(int num, String loginId, String loginPwd) {
		this.num = num;
		this.loginId = loginId;
		this.loginPwd = loginPwd;
		
		setLayout(null);
		
		//JLabel 생성
		titleLabel = new JLabel("주문목록");
		titleLabel.setFont(new Font("중나좋체 Light",Font.BOLD,35));
		//titleLabel.setForeground(Color.BLUE);
		titleLabel.setBounds(450, 40, 460, 80);
		
		//광고이미지
		imgButton = new JButton(new ImageIcon("cartad.png")); //new JButton(new ImageIcon("logo2.png"));
		imgButton.setBounds(920,100,240,570);
		imgButton.setBorderPainted(false);
		
		//JButton 생성
		buyButton = new JButton(new ImageIcon("beforebtn.png"));
		buyButton.setBounds(750,90,90,30);
		buyButton.setBorderPainted(false);
		
		
		//목록생성
		Vector<String> totalVector = new Vector<String>();
		totalVector.addElement("주문날짜");
		totalVector.addElement("제목");
		totalVector.addElement("저자");
		totalVector.addElement("장르");
		totalVector.addElement("출판사");
		totalVector.addElement("금액");
		totalVector.addElement("수량");
		
		
		//모델생성
		model = new DefaultTableModel(totalVector, 0);

		
		//DB의 모든 레코드를 꺼내서 JList에 뿌리기
		
		ProjectBookOrderDAO bookOrderDAO = ProjectBookOrderDAO.getInstance();
		List<ProjectBookOrderDTO> allList = bookOrderDAO.getBookOrderList(loginId);
		
		for(ProjectBookOrderDTO cartDTO : allList) {//담음
			Vector<Object> vector = new Vector<Object>();
			vector.add(cartDTO.getOrderDate());
			vector.add(cartDTO.getBookName());
			vector.add(cartDTO.getWriter());
			vector.add(cartDTO.getBookType());
			vector.add(cartDTO.getPublisher());
			vector.add(cartDTO.getBookPrice());
			vector.add(cartDTO.getBookVolume());
		
			model.addRow(vector);
		}//카트디티오의 데이터를 백터에 담아서 북오더디에오로 감
			
		System.out.println("sssss");
		//테이블생성
		table = new JTable (model);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBackground(Color.WHITE);//스크롤색- 안먹음
		scroll.setBounds(100,150,780,480);//테이블사이즈
		
		
		
		//Container 생성
		Container con = this.getContentPane();
		con.add(titleLabel);
		con.add(buyButton);
		con.add(scroll);
		con.add(imgButton);
		
		con.setBackground(Color.WHITE);
		setBounds(200, 150, 1200, 800);
		setResizable(false);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
		
		
		//event 처리
		buyButton.addActionListener(this);
		
		
		
	} //OrderListForm()
	
//-----------------------------------------------------------------	
	public void paint(Graphics g){
		super.paint(g);
		g.setColor(Color.LIGHT_GRAY);
		g.drawLine(90, 160, 900, 160);
		g.drawLine(90, 705, 900, 705);
		
		
		Toolkit t = Toolkit.getDefaultToolkit();
		Image img = t.getImage("logo.png");
	    g.drawImage(img,
					90, 70, 265, 140,       //화면 위치 - 고정되면 안됨
					0, 0, 1650, 768,        //이미지 위치
	                this);
	    
		Image img2 = t.getImage("order.png");
		g.drawImage(img2, 
					400, 86, 445, 128, // 화면 위치 - 고정되면 안됨
					0, 0, 562, 487, // 이미지 위치
					this);
		
	} //paint(Graphics g)
//-----------------------------------------------------------------
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == buyButton) {
			ProjectBookOrderDAO dao = new ProjectBookOrderDAO();
			//dao.inputFriend();
			new ProjectBookList(num,loginId, loginPwd);
			setVisible(false);
		}
		
	} //actionPerformed(ActionEvent e)
	
//	public static void main(String[] args) {
//		new OrderListFormloginId, loginPwd);
//	}
	

}
