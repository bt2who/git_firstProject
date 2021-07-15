package book.manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import book.DAODTO.ProjectBookDTO;
import book.DAODTO.ProjectBookOrderDAO;
import book.DAODTO.ProjectBookOrderDTO;

public class ProjectMonthlySale extends JPanel implements ActionListener {
	private DefaultTableModel model;
	private JTable table;
	private List<ProjectBookDTO> list;
	private JPanel saleP;
	private JLabel totalVolumeL, totalPriceL, totalVolume, totalPrice;
	private int volume, price;
	private JLabel yearL, monthL, yearEx, monthEx;
	private JTextField yearT, monthT;
	private JButton searchBtn;
	private Date date = new Date();
	private SimpleDateFormat year = new SimpleDateFormat("yy");
	private SimpleDateFormat month = new SimpleDateFormat("MM");
	private NumberFormat nf = new DecimalFormat();

	public ProjectMonthlySale() {
		setLayout(new BorderLayout());
		saleP = new JPanel();
		saleP.setLayout(null);
		// 목록생성
		Vector<String> totalVector = new Vector<String>();
		totalVector.addElement("주문날짜");
		totalVector.addElement("제목");
		totalVector.addElement("저자");
		totalVector.addElement("장르");
		totalVector.addElement("출판사");
		totalVector.addElement("가격");
		totalVector.addElement("수량");
		totalVector.addElement("판매금액");

		// 모델생성
		model = new DefaultTableModel(totalVector, 0) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};

		// DB의 모든 레코드를 꺼내서 JList에 뿌리기
		ProjectBookOrderDAO bookOrderDAO = ProjectBookOrderDAO.getInstance();
		List<ProjectBookOrderDTO> allList = bookOrderDAO.monthlySale(year.format(date), month.format(date));

		for (ProjectBookOrderDTO bookOrderDTO : allList) {
			Vector<Object> vector = new Vector<Object>();
			vector.add(bookOrderDTO.getOrderDate());
			vector.add(bookOrderDTO.getBookName());
			vector.add(bookOrderDTO.getWriter());
			vector.add(bookOrderDTO.getBookType());
			vector.add(bookOrderDTO.getPublisher());
			vector.add(nf.format(bookOrderDTO.getBookPrice()) + "원");
			vector.add(bookOrderDTO.getBookVolume());
			vector.add(nf.format(bookOrderDTO.getBookPrice() * bookOrderDTO.getBookVolume()) + "원");

			model.addRow(vector);
			volume += bookOrderDTO.getBookVolume();
			price += (bookOrderDTO.getBookPrice() * bookOrderDTO.getBookVolume());
		}
		totalVolumeL = new JLabel("총 판매량");
		totalVolumeL.setBounds(600, 460, 100, 50);
		totalVolumeL.setFont(new Font("중나좋체 Light", Font.PLAIN, 20));
		totalVolume = new JLabel(nf.format(volume) + "권");
		totalVolume.setBounds(700, 460, 50, 50);
		totalVolume.setFont(new Font("중나좋체 Light", Font.PLAIN, 20));
		totalPriceL = new JLabel("총 판매금액");
		totalPriceL.setBounds(770, 460, 100, 50);
		totalPriceL.setFont(new Font("중나좋체 Light", Font.PLAIN, 20));
		totalPrice = new JLabel(nf.format(price) + "원");
		totalPrice.setBounds(900, 460, 100, 50);
		totalPrice.setFont(new Font("중나좋체 Light", Font.PLAIN, 20));
		yearL = new JLabel("연도 입력");
		yearL.setBounds(30, 460, 100, 50);
		yearL.setFont(new Font("중나좋체 Light", Font.PLAIN, 20));
		yearT = new JTextField("", 10);
		yearT.setBounds(120, 473, 50, 25);
		yearT.setFont(new Font("중나좋체 Light", Font.PLAIN, 20));
		yearEx = new JLabel("ex)21");
		yearEx.setBounds(120, 495, 50, 25);
		yearEx.setFont(new Font("중나좋체 Light", Font.PLAIN, 15));
		yearEx.setForeground(Color.LIGHT_GRAY);
		monthL = new JLabel("월 입력");
		monthL.setBounds(200, 460, 100, 50);
		monthL.setFont(new Font("중나좋체 Light", Font.PLAIN, 20));
		monthT = new JTextField("", 10);
		monthT.setBounds(280, 473, 50, 25);
		monthT.setFont(new Font("중나좋체 Light", Font.PLAIN, 20));
		monthEx = new JLabel("ex)04");
		monthEx.setBounds(280, 495, 50, 25);
		monthEx.setFont(new Font("중나좋체 Light", Font.PLAIN, 15));
		monthEx.setForeground(Color.LIGHT_GRAY);
		searchBtn = new JButton("검색");
		searchBtn.setBounds(360, 472, 70, 30);
		searchBtn.setFont(new Font("중나좋체 Light", Font.PLAIN, 15));
		// 테이블생성
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBackground(Color.WHITE);// 스크롤색- 안먹음
		scroll.setBounds(10, 10, 995, 450);// 테이블사이즈

		saleP.add(scroll);
		saleP.add(totalVolumeL);
		saleP.add(totalVolume);
		saleP.add(totalPriceL);
		saleP.add(totalPrice);
		saleP.add(yearL);
		saleP.add(yearT);
		saleP.add(monthL);
		saleP.add(monthT);
		saleP.add(searchBtn);
		saleP.add(yearEx);
		saleP.add(monthEx);
		saleP.setBackground(Color.WHITE);
		add(saleP);
		searchBtn.addActionListener(this);
		yearT.addActionListener(this);
		monthT.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == searchBtn) {
			if (yearT.getText().length()==2 && monthT.getText().length()==2 && Integer.parseInt(monthT.getText())<13 && Integer.parseInt(monthT.getText())>0) {
				volume = 0;
				price = 0;
				model.setRowCount(0);

				ProjectBookOrderDAO bookOrderDAO = ProjectBookOrderDAO.getInstance();
				List<ProjectBookOrderDTO> allList = bookOrderDAO.monthlySale(yearT.getText(), monthT.getText());

				for (ProjectBookOrderDTO bookOrderDTO : allList) {
					Vector<Object> vector = new Vector<Object>();
					vector.add(bookOrderDTO.getOrderDate());
					vector.add(bookOrderDTO.getBookName());
					vector.add(bookOrderDTO.getWriter());
					vector.add(bookOrderDTO.getBookType());
					vector.add(bookOrderDTO.getPublisher());
					vector.add(nf.format(bookOrderDTO.getBookPrice()) + "원");
					vector.add(bookOrderDTO.getBookVolume());
					vector.add(nf.format(bookOrderDTO.getBookPrice() * bookOrderDTO.getBookVolume()) + "원");

					model.addRow(vector);
					volume += bookOrderDTO.getBookVolume();
					price += (bookOrderDTO.getBookPrice() * bookOrderDTO.getBookVolume());
				}

				totalVolume.setText(nf.format(volume) + "권");
				totalVolume.setBounds(700, 460, 50, 50);
				totalVolume.setFont(new Font("중나좋체 Light", Font.PLAIN, 20));
				totalPrice.setText(nf.format(price) + "원");
				totalPrice.setBounds(900, 460, 100, 50);
				totalPrice.setFont(new Font("중나좋체 Light", Font.PLAIN, 20));
			}//if
			else {
				JOptionPane.showMessageDialog(null, "날짜를 형식에 맞게 입력바랍니다");
			}
			yearT.setText("");
			monthT.setText("");
		}

	}

}
