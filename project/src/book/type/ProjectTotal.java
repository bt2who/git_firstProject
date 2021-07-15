package book.type;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import book.DAODTO.ProjectBookDAO;
import book.DAODTO.ProjectBookDTO;
import book.DAODTO.ProjectBookOrderDAO;
import book.DAODTO.ProjectCartDTO;

public class ProjectTotal extends JPanel {// 누르면 데이터베이스 내 전체 목록 가져오기
	private DefaultTableModel model;
	private JTable table;
	private List<ProjectBookDTO> list;
	private JPanel totalP;
	private int num;

	public ProjectTotal(int num, String loginId) {
		this.num = num;
		setLayout(new BorderLayout());

		// 탭패널 생성
		totalP = new JPanel();
		totalP.setLayout(null);

		// 목록생성
		Vector<String> totalV = new Vector<String>();
		totalV.addElement("번호");
		totalV.addElement("제목");
		totalV.addElement("저자");
		totalV.addElement("장르");
		totalV.addElement("출판사");
		totalV.addElement("가격");
		if (num == 1)
			totalV.add("권수");

		// Vector을 관리할 모델생성
		model = new DefaultTableModel(totalV, 0) {

			public boolean isCellEditable(int i, int c) {
				return false; // 수정 못하게
			}

		};

		// DB의 모든 레코드를 꺼내서 JList에 뿌리기
		ProjectBookDAO bookDAO = ProjectBookDAO.getInstance(); // 싱글톤부름 - 디비는 DAO에서 처리하니까
		List<ProjectBookDTO> allList = bookDAO.selectTotal(); // BookDTO를 담을 LISt생성! 여러데이터를 담아야해서 LIST를 만듬

		for (ProjectBookDTO bookDTO : allList) {// DTO를 리스트에 하나하나 담는다

			// 데이터를 담을 벡터 생성 - 데이터가 문자열,숫자가 있어서 오브젝트로 잡음
			Vector<Object> v = new Vector<Object>();
			v.add(bookDTO.getSeq()); // DTo에 있는 seq를 벡터에 붙임
			v.add(bookDTO.getBookName());
			v.add(bookDTO.getWriter());
			v.add(bookDTO.getBookType());
			v.add(bookDTO.getPublisher());
			v.add(bookDTO.getBookPrice());
			if (num == 1)
				v.add(bookDTO.getBookVolume());

			model.addRow(v);// 모델에 한줄씩 붙임
		}

		// 테이블생성
		table = new JTable(model);

		// 테이블선택
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBackground(Color.WHITE);// 스크롤색- 안먹음
		scroll.setBounds(10, 10, 880, 525);// 테이블사이즈

		totalP.add(scroll);
		totalP.setBackground(Color.WHITE);// 바탕색
		add(totalP);

		if (num == 0) {
			// 얘를 장바구니에 연동해야함
			table.addMouseListener(new MouseAdapter() {// 익명이너

				@Override
				public void mouseClicked(MouseEvent e) {

					int row = table.getSelectedRow();
					String qnt = JOptionPane.showInputDialog(null, "수량을 입력하세요");
					if (qnt == null) {// 입력안하고 취소 눌렀을 때 에러 안뜨게
						return;
					}
					if (qnt.length() == 0 || qnt == null) {
						JOptionPane.showMessageDialog(null, "수량이 입력되지 않았습니다.");
						// System.exit(0);
						return;
					}

					int result = JOptionPane.showConfirmDialog(null, "장바구니에 추가하시겠습니까?", // 메세지
							"구매확인", // 타이틀
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (result == JOptionPane.YES_OPTION) {
						// 입력 값을 북 오더 디에오로 보내
						JOptionPane.showMessageDialog(null, "장바구니에 추가완료!");
					} else if (result == JOptionPane.NO_OPTION) {
						setVisible(false);
						return;
					}else {
						return;
					}

					// cartDTO에 담기
					ProjectCartDTO cartDTO = new ProjectCartDTO();
					cartDTO.setBookName((String) model.getValueAt(row, 1));// 제목
					cartDTO.setWriter((String) model.getValueAt(row, 2));
					cartDTO.setBookType((String) model.getValueAt(row, 3));
					cartDTO.setPublisher((String) model.getValueAt(row, 4));
					cartDTO.setBookPrice((int) model.getValueAt(row, 5));// 가격
					cartDTO.setBookVolume(Integer.parseInt(qnt));// 수량
					cartDTO.setMemberID(loginId);
					
					ProjectBookOrderDAO bookOrderDAO = ProjectBookOrderDAO.getInstance();
					bookOrderDAO.qntInput(cartDTO,loginId);

				}
			});
		}

	}

//검색-----------------------------------------------------------------------------------------------------------------
	public void search(int subjectCombobox, String searchT) { // (말머리,검색텍스트)
		ProjectBookDAO bookDAO = ProjectBookDAO.getInstance();

		if (subjectCombobox == 0) {// 제목별을 선택했을때
			List<ProjectBookDTO> list = new ArrayList<ProjectBookDTO>();
			list = bookDAO.searchBookName(searchT); // searchT.getText();에 입력된 값을 제목에서 찾아라 - DAO의 searchBookName로 감

			model.setRowCount(0);
			for (ProjectBookDTO bookDTO : list) {

				Vector<Object> v = new Vector<Object>();
				v.add(bookDTO.getSeq());
				v.add(bookDTO.getBookName());
				v.add(bookDTO.getWriter());
				v.add(bookDTO.getBookType());
				v.add(bookDTO.getPublisher());
				v.add(bookDTO.getBookPrice());
				if (num == 1)
					v.add(bookDTO.getBookVolume());

				model.addRow(v);
			}
		} else if (subjectCombobox == 1) {
			List<ProjectBookDTO> list = new ArrayList<ProjectBookDTO>();
			list = bookDAO.searchWriter(searchT); // searchT.getText();에 입력된 값을 저자에서 찾아라 - DAO의 searchWriter로 감

			model.setRowCount(0);
			for (ProjectBookDTO bookDTO : list) {

				Vector<Object> v = new Vector<Object>();
				v.add(bookDTO.getSeq());
				v.add(bookDTO.getBookName());
				v.add(bookDTO.getWriter());
				v.add(bookDTO.getBookType());
				v.add(bookDTO.getPublisher());
				v.add(bookDTO.getBookPrice());
				if (num == 1)
					v.add(bookDTO.getBookVolume());

				model.addRow(v);
			}
		} else if (subjectCombobox == 2) {
			List<ProjectBookDTO> list = new ArrayList<ProjectBookDTO>();
			list = bookDAO.searchPublisher(searchT); // searchT.getText();에 입력된 값을 출판사에서 찾아라 - DAO의 searchPublisher로 감

			model.setRowCount(0);
			for (ProjectBookDTO bookDTO : list) {

				Vector<Object> v = new Vector<Object>();
				v.add(bookDTO.getSeq());
				v.add(bookDTO.getBookName());
				v.add(bookDTO.getWriter());
				v.add(bookDTO.getBookType());
				v.add(bookDTO.getPublisher());
				v.add(bookDTO.getBookPrice());
				if (num == 1)
					v.add(bookDTO.getBookVolume());

				model.addRow(v);
			}
		}

	}// search(int subjectCombobox,String searchT)

	public void reset() {
		model.setRowCount(0);

		ProjectBookDAO bookDAO = ProjectBookDAO.getInstance(); // 싱글톤부름 - 디비는 DAO에서 처리하니까
		List<ProjectBookDTO> allList = bookDAO.selectTotal(); // BookDTO를 담을 LISt생성! 여러데이터를 담아야해서 LIST를 만듬

		for (ProjectBookDTO bookDTO : allList) {// DTO를 리스트에 하나하나 담는다

			// 데이터를 담을 벡터 생성 - 데이터가 문자열,숫자가 있어서 오브젝트로 잡음
			Vector<Object> v = new Vector<Object>();
			v.add(bookDTO.getSeq()); // DTo에 있는 seq를 벡터에 붙임
			v.add(bookDTO.getBookName());
			v.add(bookDTO.getWriter());
			v.add(bookDTO.getBookType());
			v.add(bookDTO.getPublisher());
			v.add(bookDTO.getBookPrice());
			if (num == 1)
				v.add(bookDTO.getBookVolume());

			model.addRow(v);// 모델에 한줄씩 붙임
		}
	}

}
