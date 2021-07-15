package book.manager;

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
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import book.DAODTO.ProjectBookOrderDAO;
import book.DAODTO.ProjectBookOrderDTO;
import book.mypage.ProjectBookList;

public class ProjectBookSaleList extends JFrame implements ActionListener {
	private JLabel titleLabel;
	private JButton buyButton;
	private DefaultTableModel model;
	private JTable table;
	private List<ProjectBookOrderDTO> list;
	private JTabbedPane menu;
	private ProjectMonthlySale monthlySale;
	private ProjectDaySale daySale;

	

	public ProjectBookSaleList() {		
		
		setLayout(null);
		
		//JLabel 생성
		titleLabel = new JLabel("매출 현황");
		titleLabel.setFont(new Font("중나좋체 Light",Font.BOLD,35));
		//titleLabel.setForeground(Color.BLUE);
		titleLabel.setBounds(300, 40, 460, 80);
		
		
		//JButton 생성
		buyButton = new JButton(new ImageIcon("beforebtn.png"));
		buyButton.setBounds(1000,90,90,30);
		buyButton.setBorderPainted(false);
		
		//클래스
		monthlySale = new ProjectMonthlySale();
		daySale = new ProjectDaySale();
		
		//탭
		menu = new JTabbedPane(SwingConstants.TOP);
		menu.setBounds(80, 180, 1020, 550);
		menu.setFont(new Font("중나좋체 Light", Font.PLAIN, 20));
		menu.addTab("당일매출", daySale);
		menu.addTab("월별매출", monthlySale);
			
		//Container 생성
		Container c = this.getContentPane();
		c.add(titleLabel);
		c.add(buyButton);
		c.add("LEFT",menu);
		
		
		c.setBackground(Color.WHITE);
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
		
		
		
	} // OrderListForm()

//-----------------------------------------------------------------	
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.LIGHT_GRAY);
		g.drawLine(90, 160, 1100, 160);
//		g.drawLine(90, 705, 900, 705);

		Toolkit t = Toolkit.getDefaultToolkit();
		Image img = t.getImage("logo.png");
		g.drawImage(img, 90, 70, 265, 140, // 화면 위치 - 고정되면 안됨
				0, 0, 1650, 768, // 이미지 위치
				this);

	} // paint(Graphics g)

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==buyButton) {
			new ProjectManagerMain();
			setVisible(false);
		}
		
	}
}
//-----------------------------------------------------------------
	