package book.manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import book.DAODTO.ProjectBookDTO;
import book.DAODTO.ProjectBookOrderDAO;
import book.DAODTO.ProjectBookOrderDTO;

public class ProjectDaySale extends JPanel {
	private DefaultTableModel model;
	private JTable table;
	private List<ProjectBookDTO> list;
	private JPanel saleP;
	private JLabel totalVolumeL, totalVolume, totalPriceL, totalPrice;
	private int volume, price;
	
	public ProjectDaySale() {
		setLayout(new BorderLayout());
		NumberFormat nf = new DecimalFormat();
		
		saleP = new JPanel();
		saleP.setLayout(null);
		
		//목록생성
		Vector<String> totalVector = new Vector<String>();
		totalVector.addElement("주문날짜");
		totalVector.addElement("제목");
		totalVector.addElement("저자");
		totalVector.addElement("장르");
		totalVector.addElement("출판사");
		totalVector.addElement("가격");
		totalVector.addElement("수량");
		totalVector.addElement("판매금액");
		
		
		//모델생성
		model = new DefaultTableModel(totalVector, 0) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};

		
		//DB의 모든 레코드를 꺼내서 JList에 뿌리기
		ProjectBookOrderDAO bookOrderDAO = ProjectBookOrderDAO.getInstance();
		List<ProjectBookOrderDTO> allList = bookOrderDAO.dailySale();
		
		for(ProjectBookOrderDTO bookOrderDTO : allList) {
			Vector<Object> vector = new Vector<Object>();
			vector.add(bookOrderDTO.getOrderDate());
			vector.add(bookOrderDTO.getBookName());
			vector.add(bookOrderDTO.getWriter());
			vector.add(bookOrderDTO.getBookType());
			vector.add(bookOrderDTO.getPublisher());
			vector.add(nf.format(bookOrderDTO.getBookPrice())+"원");
			vector.add(bookOrderDTO.getBookVolume());
			vector.add(nf.format(bookOrderDTO.getBookPrice()*bookOrderDTO.getBookVolume())+"원");
			model.addRow(vector);
			volume += bookOrderDTO.getBookVolume();
			price += (bookOrderDTO.getBookPrice()*bookOrderDTO.getBookVolume());
		}
		totalVolumeL = new JLabel("총 판매량");
		totalVolumeL.setBounds(600, 460, 100, 50);
		totalVolumeL.setFont(new Font("중나좋체 Light", Font.PLAIN, 20));
		totalVolume = new JLabel(nf.format(volume)+"권");
		totalVolume.setBounds(700, 460, 50, 50);
		totalVolume.setFont(new Font("중나좋체 Light", Font.PLAIN, 20));
		totalPriceL = new JLabel("총 판매금액");
		totalPriceL.setBounds(770, 460, 100, 50);
		totalPriceL.setFont(new Font("중나좋체 Light", Font.PLAIN, 20));
		totalPrice = new JLabel(nf.format(price)+"원");
		totalPrice.setBounds(900, 460, 100, 50);
		totalPrice.setFont(new Font("중나좋체 Light", Font.PLAIN, 20));
		//테이블생성
		table = new JTable (model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBackground(Color.WHITE);//스크롤색- 안먹음
		scroll.setBounds(10,10,995,450);//테이블사이즈
		
		saleP.add(scroll);
		saleP.add(totalVolumeL);
		saleP.add(totalVolume);
		saleP.add(totalPriceL);
		saleP.add(totalPrice);
		saleP.setBackground(Color.WHITE);
		add(saleP);
	}


}
