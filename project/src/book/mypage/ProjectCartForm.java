package book.mypage;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import book.DAODTO.ProjectCartDAO;
import book.DAODTO.ProjectCartDTO;


public class ProjectCartForm extends JFrame implements ActionListener {
	private JLabel titleLabel, totalLabel, priceLabel, wonLabel;
	private JTextField totalPriceField;
	// private JComboBox combo;
	private JButton totalPriceLabel, deleteButton, backButton, checkButton, allButton, imgButton;
	private DefaultTableModel model;
	private JTable table;
	private List<ProjectCartDTO> list;
	private static int num;
	private String loginId, loginPwd;
	private static final int BOOLEAN_COLUMN = 0;
	private ProjectCartDTO cartDTO;

	public ProjectCartForm(int num, String loginId, String loginPwd) {
		this.num = num;
		this.loginId = loginId;
		this.loginPwd = loginPwd;

		setLayout(null);

		// JLabel 생성
		titleLabel = new JLabel("장바구니");
		titleLabel.setFont(new Font("중나좋체 Light", Font.BOLD, 35));
		// titleLabel.setForeground(Color.BLUE);
		titleLabel.setBounds(450, 40, 460, 80);

		totalLabel = new JLabel("총 주문 금액");
		totalLabel.setFont(new Font("중나좋체 Light", Font.PLAIN, 32));
		// totalLabel.setForeground(Color.BLUE);
		totalLabel.setBounds(120, 420, 460, 80);

		priceLabel = new JLabel("결제 예정 금액");
		priceLabel.setFont(new Font("중나좋체 Light", Font.BOLD, 18));
		// priceLabel.setForeground(Color.BLUE);
		priceLabel.setBounds(580, 480, 460, 80);

		wonLabel = new JLabel("원");
		wonLabel.setFont(new Font("중나좋체 Light", Font.BOLD, 18));
		// wonLabel.setForeground(Color.BLUE);
		wonLabel.setBounds(840, 480, 460, 80);

		// JTextField 생성 - 결제 금액 띄우는 곳
		totalPriceField = new JTextField();
		// totalPriceField.setText(Integer.toString(cartDTO.setPrice(getInt("Price")));
		totalPriceField.setBounds(700, 505, 140, 30);
		totalPriceField.setEnabled(false);

		// JButton 생성
		
		imgButton = new JButton(new ImageIcon("buyad.png")); //new JButton(new ImageIcon("logo2.png"));
		imgButton.setBounds(920,100,240,570);
		imgButton.setBorderPainted(false);
		
		deleteButton = new JButton(new ImageIcon("seldelbtn.png"));//"선택삭제"
		deleteButton.setBounds(100, 630, 100, 30);
		deleteButton.setBorderPainted(false);

		backButton = new JButton(new ImageIcon("shopping.png"));//"계속 쇼핑하기"
		backButton.setBounds(460, 630, 130, 30);
		backButton.setBorderPainted(false);

		checkButton = new JButton(new ImageIcon("selorbtn.png"));//선택상품 주문하기
		checkButton.setBounds(600, 630, 130, 30);
		checkButton.setBorderPainted(false);

		allButton = new JButton(new ImageIcon("allorderbtn.png"));//전체상품 주문하기
		allButton.setBounds(740, 630, 130, 30);
		allButton.setBorderPainted(false);

		// 목록생성
		Vector<String> totalVector = new Vector<String>();
		totalVector.addElement("선택");
		totalVector.addElement("상품정보");
		totalVector.addElement("지은이");
		totalVector.addElement("장르");
		totalVector.addElement("출판사");
		totalVector.addElement("상품금액");
		totalVector.addElement("수량");

		// 모델생성
		model = new DefaultTableModel(totalVector, 0);

		// 테이블생성
		table = new JTable(model) {

			private static final long serialVersionUID = 1L;

			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return Boolean.class;
				case 1:
					return String.class;
				case 2:
					return String.class;
				default:
					return String.class;
				}
			}
		};

		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);

		table.getModel().addTableModelListener(new CheckBoxModelListener());

		DefaultTableCellRenderer dcr = new DefaultTableCellRenderer() {
			public Component getTableCellRendererComponent // 셀렌더러
			(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				JCheckBox box = new JCheckBox();
				box.setSelected(((Boolean) value).booleanValue());
				box.setHorizontalAlignment(JLabel.CENTER);
				return box;
			}
		};

		JScrollPane scroll = new JScrollPane(table);
		scroll.setBackground(Color.WHITE);// 스크롤색- 안먹음
		scroll.setBounds(100, 150, 780, 250);// 테이블사이즈

		// DB의 모든 레코드를 꺼내서 JList에 뿌리기
		ProjectCartDAO cartDAO = ProjectCartDAO.getInstance();
		List<ProjectCartDTO> allList = cartDAO.getCartList(loginId);

		for (ProjectCartDTO cartDTO : allList) {
			Vector<Object> vector = new Vector<Object>();
			vector.add(false);
			vector.add(cartDTO.getBookName());
			vector.add(cartDTO.getWriter());
			vector.add(cartDTO.getBookType());
			vector.add(cartDTO.getPublisher());
			vector.add(cartDTO.getBookPrice());
			vector.add(cartDTO.getBookVolume());

			model.addRow(vector);
		} //for


		// list생성
		list = new LinkedList<ProjectCartDTO>();

		// Container 생성
		Container con = this.getContentPane();
		con.add(titleLabel);
		con.add(totalLabel);
		con.add(priceLabel);
		con.add(wonLabel);
		con.add(deleteButton);
		con.add(backButton);
		con.add(checkButton);
		con.add(allButton);
		con.add(totalPriceField);
		con.add(scroll);
		con.add(imgButton);

		con.setBackground(Color.WHITE);
		setBounds(200, 150, 1200, 800);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// event 처리
		backButton.addActionListener(this);
		allButton.addActionListener(this);
		checkButton.addActionListener(this);
		
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == deleteButton) {

					int size = list.size();
					//System.out.println("size: " + size + "model=" + model.getRowCount());

					for (int i = 0; i < size; i++) {
//							if(size==0) {
//								return;
//							}
						ProjectCartDTO cartDTO = list.get(i);
						cartDAO.deleteCart(cartDTO, loginId);

						list.remove(i);
						//model.removeRow(i);
						
						model.setNumRows(0);
						
						List<ProjectCartDTO> allList = cartDAO.getCartList(loginId);
						
						for (ProjectCartDTO cartDTO2 : allList) {
							Vector<Object> vector2 = new Vector<Object>();
							vector2.add(false);
							vector2.add(cartDTO2.getBookName());
							vector2.add(cartDTO2.getWriter());
							vector2.add(cartDTO2.getBookType());
							vector2.add(cartDTO2.getPublisher());
							vector2.add(cartDTO2.getBookPrice());
							vector2.add(cartDTO2.getBookVolume());

							model.addRow(vector2);
						} // for

						size--;
						i--;

					} // for i
						
						
				} //if
			} //actionPerformed(ActionEvent e)

		});

	} // CartForm()
	
	
//-----------------------------------------------------------------	

	
	public class CheckBoxModelListener implements TableModelListener{

		public void tableChanged(TableModelEvent e) {
			
			int column = e.getColumn();
			if (column == BOOLEAN_COLUMN) {
				TableModel model = (TableModel) e.getSource();
				
				for (int row = 0; row < table.getRowCount(); row++) {
					Boolean checked = (Boolean) model.getValueAt(row, 0);
					if (checked) { //체크가 됐을 떄
						
						ProjectCartDTO cartDTO = new ProjectCartDTO();
						cartDTO.setBookName((String) model.getValueAt(row, 1));
						cartDTO.setWriter((String) model.getValueAt(row, 2));
						cartDTO.setBookType((String) model.getValueAt(row, 3));
						cartDTO.setPublisher((String) model.getValueAt(row, 4));
						cartDTO.setBookPrice((int) model.getValueAt(row, 5));
						cartDTO.setBookVolume((int) model.getValueAt(row, 6));
						
						boolean result = true;
						for(int i = 0; i < list.size(); i++) {
							if(list.get(i).getBookName().equals(cartDTO.getBookName())) {
								result = false;
							}
						}
						
						if(result) {
							list.add(cartDTO);
						}
						
						
						
						if (totalPriceField.getText().equals("")) {
							int totalPrice = (int)table.getValueAt(row, 5)*(int)table.getValueAt(row, 6);
							totalPriceField.setText(Integer.toString(totalPrice));
						} else {

							int total = 0;

							//System.out.println((table.getValueAt(row, 5)));

							for (int i = 0; i < table.getRowCount(); i++) {

								if ((Boolean) model.getValueAt(i, 0)) {
									int totalPrice = (int)table.getValueAt(i, 5)*(int)table.getValueAt(i, 6);
									total = total + totalPrice;
								}

							}

							totalPriceField.setText(String.valueOf(total));
						}


					} else {
						//System.out.println("check false");
						
						ProjectCartDTO cartDTO = new ProjectCartDTO();
						cartDTO.setBookName((String) model.getValueAt(row, 1));
						cartDTO.setWriter((String) model.getValueAt(row, 2));
						cartDTO.setBookType((String) model.getValueAt(row, 3));
						cartDTO.setPublisher((String) model.getValueAt(row, 4));
						cartDTO.setBookPrice((int) model.getValueAt(row, 5));
						cartDTO.setBookVolume((int) model.getValueAt(row, 6));
						
						int unCheckedIndex = 0;
						
						boolean result = false;
						for(int i = 0; i < list.size(); i++) {
							if(list.get(i).getBookName().equals(cartDTO.getBookName())) {
								result = true;
								unCheckedIndex = i;
							}
						}
						
						if(result==true) {
							//list.remove(cartDTO);
							list.remove(unCheckedIndex);
						}int i;
						int choice=0;
		                  for(i=0; i<table.getRowCount(); i++) {
		                      if((Boolean)model.getValueAt(i, 0)) {
		                         choice++;
		                      }
		                   }
		                   if(i==table.getRowCount()&&choice==0) {
		                      totalPriceField.setText("");
		                   }
						
						//list.clear();
					} //if
					
				} //for row
			} //if
		}

//list.clear();

	} //CheckBoxModelListener()

//-----------------------------------------------------------------	
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.LIGHT_GRAY);
		g.drawLine(90, 160, 900, 160);
		g.drawLine(90, 705, 900, 705);
		g.drawLine(100, 450, 880, 450);
		g.drawLine(100, 650, 880, 650);
		g.drawLine(100, 450, 100, 650);
		g.drawLine(880, 450, 880, 650);

		Toolkit t = Toolkit.getDefaultToolkit();
		Image img = t.getImage("logo.png");
		g.drawImage(img, 90, 70, 265, 140, // 화면 위치 - 고정되면 안됨
				0, 0, 1650, 768, // 이미지 위치
				this);
		Image img2 = t.getImage("cart.png");
		g.drawImage(img2, 300, 86, 345, 128, // 화면 위치 - 고정되면 안됨
				0, 0, 532, 487, // 이미지 위치
				this);

	} // paint(Graphics g)

//-----------------------------------------------------------------
	@Override
	public void actionPerformed(ActionEvent e) {
		ProjectCartDAO cartDAO = ProjectCartDAO.getInstance();
		
		if(e.getSource() == backButton) {
			//BookOrderDAO dao = new BookOrderDAO();
			new ProjectBookList(num, loginId, loginPwd);
			setVisible(false);
		} else if (e.getSource() == checkButton) { // 선택주문
			int result = JOptionPane.showConfirmDialog(null, "주문하시겠습니까?");
			if (result == JOptionPane.CLOSED_OPTION) {
				return;

			} else if (result == JOptionPane.YES_OPTION) {

				int size = list.size();
				// System.out.println("size: " + size + "model=" + model.getRowCount());

				for (int i = 0; i < size; i++) {

					ProjectCartDTO cartDTO = list.get(i);
					cartDAO.moveCart(cartDTO, loginId);

					cartDAO.deleteCart(cartDTO, loginId);

					list.remove(i);
					// model.removeRow(i);

					model.setNumRows(0);

					List<ProjectCartDTO> allList = cartDAO.getCartList(loginId);

					for (ProjectCartDTO cartDTO2 : allList) {
						Vector<Object> vector2 = new Vector<Object>();
						vector2.add(false);
						vector2.add(cartDTO2.getBookName());
						vector2.add(cartDTO2.getWriter());
						vector2.add(cartDTO2.getBookType());
						vector2.add(cartDTO2.getPublisher());
						vector2.add(cartDTO2.getBookPrice());
						vector2.add(cartDTO2.getBookVolume());

						model.addRow(vector2);
					} // for

					size--;
					i--;

				} // for i

			
				new ProjectOrderListForm(num, loginId, loginPwd);
				setVisible(false);
			} //if
		}else if (e.getSource() == allButton) {//전체주문
			int result = JOptionPane.showConfirmDialog(null, "주문하시겠습니까?");
			if(result == JOptionPane.CLOSED_OPTION) {
				//사용자가 '예',"아니오" 의 선택없이 다이얼로그 창을 닫은 경우
				return;
			}else if(result == JOptionPane.YES_OPTION) {
				//사용자가 예를 선택한경우-CartDAO로 데이터를 가지고가라~디비에 넣어라~
				//장바구니의 띄워져있는 애들을 가지고 가야함★★★
				
//				BookOrderDTO = new BookOrderDTO();
//				//디티오에 담아
//				bookOrderDTO.setBookName("");
//				cartDAO.getCartList(bookOrderDTO);//디에오에 있는 카트리스트메소드로 디티오를 들고가
				//주문창으로 이동
				
				List<ProjectCartDTO> allList = cartDAO.getCartList(loginId);
				int size = allList.size();
				// System.out.println("size: " + size + "model=" + model.getRowCount());

				for (int i = 0; i < size; i++) {

					ProjectCartDTO cartDTO = allList.get(i);
					cartDAO.moveCart(cartDTO, loginId);

					cartDAO.deleteCart(cartDTO, loginId);

					
					// model.removeRow(i);
				
				
				} // for i
				model.setNumRows(0);
				JOptionPane.showMessageDialog(null, "주문완료");
				new ProjectOrderListForm(num, loginId, loginPwd);
				setVisible(false);
				
				
			}	
			
			
		}else {
	    	return; // 사용자가 "아니오"를 선택한 경우
	    }// else
		
		
	}// actionPerformed(ActionEvent e)

	
} // CartForm
