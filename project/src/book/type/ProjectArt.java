package book.type;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import book.DAODTO.ProjectBookDAO;
import book.DAODTO.ProjectBookDTO;
import book.DAODTO.ProjectBookOrderDAO;
import book.DAODTO.ProjectCartDTO;

//import project.BookDAO;
//import project.BookDTO;
//import project.BookOrderDAO;
//import project.CartDTO;

public class ProjectArt extends JPanel {
	private DefaultTableModel model;
	private JTable table;
	private List<ProjectBookDTO> list;
	private JPanel artP;
	private int num;

	public ProjectArt(int num, String loginId) {
		this.num = num;
		setLayout(new BorderLayout());

		artP = new JPanel();
		artP.setLayout(null);

		// 목록생성
		Vector<String> artV = new Vector<String>();
		artV.addElement("번호");
		artV.addElement("제목");
		artV.addElement("저자");
		artV.addElement("장르");
		artV.addElement("출판사");
		artV.addElement("가격");
		if (num == 1)
			artV.add("권수");

		// 모델생성 - 수정못하게
		model = new DefaultTableModel(artV, 0) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};

		// DB레코드 JList에 넣기
		ProjectBookDAO bookDAO = ProjectBookDAO.getInstance();
		List<ProjectBookDTO> allList = bookDAO.genreList("예술");

		for (ProjectBookDTO bookDTO : allList) {
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

		// 테이블생성
		table = new JTable(model);

		// 테이블선택
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scroll = new JScrollPane(table);
		scroll.setBackground(Color.WHITE);// 스크롤색- 안먹음
		scroll.setBounds(10, 10, 880, 525);// 테이블사이즈
		// table.addMouseListener(this);

		// 데이터

		// 리스트생성

		artP.add(scroll);
		artP.setBackground(Color.WHITE);// 바탕색
		add(artP);

		if (num == 0) {
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
						return;
					} else
						return;

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

	}// art()

	public void search(int subjectCombobox, String searchT) {// 검색
		ProjectBookDAO bookDAO = ProjectBookDAO.getInstance();
		if (subjectCombobox == 0) {// 제목별을 선택했을때
			List<ProjectBookDTO> list = new ArrayList<ProjectBookDTO>();
			list = bookDAO.searchTypeBookName("예술", searchT); // searchT.getText();에 입력된 값을 제목에서 찾아라

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
			list = bookDAO.searchTypeWriter("예술", searchT); // searchT.getText();에 입력된 값을 제목에서 찾아라

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
			list = bookDAO.searchTypePublisher("예술", searchT); // searchT.getText();에 입력된 값을 제목에서 찾아라

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

		ProjectBookDAO bookDAO = ProjectBookDAO.getInstance();
		List<ProjectBookDTO> allList = bookDAO.genreList("예술");

		for (ProjectBookDTO bookDTO : allList) {
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

}
