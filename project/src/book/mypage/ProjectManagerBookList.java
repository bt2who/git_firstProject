package book.mypage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import book.DAODTO.ProjectBookDAO;
import book.DAODTO.ProjectBookDTO;

public class ProjectManagerBookList extends JPanel{
	private DefaultTableModel model;
	private JTable table;
	private List<ProjectBookDTO> list;
	private JPanel artP;
	
	
	public ProjectManagerBookList() {
		setLayout(new BorderLayout());
		
		artP = new JPanel();
		artP.setLayout(null);
		
		//목록생성
		Vector<String> artV = new Vector<String>();
		artV.addElement("번호");
		artV.addElement("제목");
		artV.addElement("저자");
		artV.addElement("출판사");
		artV.addElement("가격");
		
		//모델생성
		model = new DefaultTableModel(artV,0) {
			public boolean isCellEditable(int i, int c) {
				return false; //수정 못하게
			} 
		};
		
		
		//DB레코드 JList에 넣기
		ProjectBookDAO bookDAO = ProjectBookDAO.getInstance();
		List<ProjectBookDTO> allList = bookDAO.genreList("예술");
		
		for(ProjectBookDTO bookDTO : allList) {
			Vector<Object> v = new Vector<Object>();
			v.add(bookDTO.getSeq());
			v.add(bookDTO.getBookName());
			v.add(bookDTO.getWriter());
			v.add(bookDTO.getBookType());
			v.add(bookDTO.getPublisher());
			v.add(bookDTO.getBookPrice());
			
			model.addRow(v);
			
		}
		
		
		
		
		//테이블생성
		table = new JTable (model);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBackground(Color.WHITE);//스크롤색- 안먹음
		scroll.setBounds(10,10,880,525);//테이블사이즈
		
		//데이터
		
		//리스트생성
		
		artP.add(scroll);
		artP.setBackground(Color.WHITE);//바탕색
		add(artP);
		
		
		//AbstractTableModel(abstract)
		
		
		
	}//art()
	
	
	
	public void search(int subjectCombobox,String searchT) {//검색
		ProjectBookDAO bookDAO = ProjectBookDAO.getInstance();
		if(subjectCombobox == 0 ){//제목별을 선택했을때	
			List<ProjectBookDTO> list = new ArrayList<ProjectBookDTO>();
			list = bookDAO.searchTypeBookName("예술", searchT);	//searchT.getText();에 입력된 값을 제목에서 찾아라
			 
			model.setRowCount(0);
			 for(ProjectBookDTO bookDTO : list) {
				 
				 Vector<Object> v = new Vector<Object>();
				 v.add(bookDTO.getSeq());
				 v.add(bookDTO.getBookName());
				 v.add(bookDTO.getWriter());
				 v.add(bookDTO.getBookType());
				 v.add(bookDTO.getPublisher());
				 v.add(bookDTO.getBookPrice());
				 
				model.addRow(v);
			 }
		}else if(subjectCombobox == 1) {
			List<ProjectBookDTO> list = new ArrayList<ProjectBookDTO>();
			list = bookDAO.searchTypeWriter("예술",searchT);	//searchT.getText();에 입력된 값을 제목에서 찾아라

			model.setRowCount(0);
			 for(ProjectBookDTO bookDTO : list) {
				 
				 Vector<Object> v = new Vector<Object>();
				 v.add(bookDTO.getSeq());
				 v.add(bookDTO.getBookName());
				 v.add(bookDTO.getWriter());
				 v.add(bookDTO.getBookType());
				 v.add(bookDTO.getPublisher());
				 v.add(bookDTO.getBookPrice());
				 
				model.addRow(v);
			 }
		}else if(subjectCombobox == 2) {
			List<ProjectBookDTO> list = new ArrayList<ProjectBookDTO>();
			list = bookDAO.searchTypePublisher("예술",searchT);	//searchT.getText();에 입력된 값을 제목에서 찾아라

			model.setRowCount(0);
			 for(ProjectBookDTO bookDTO : list) {
				 
				 Vector<Object> v = new Vector<Object>();
				 v.add(bookDTO.getSeq());
				 v.add(bookDTO.getBookName());
				 v.add(bookDTO.getWriter());
				 v.add(bookDTO.getBookType());
				 v.add(bookDTO.getPublisher());
				 v.add(bookDTO.getBookPrice());
				 
				model.addRow(v);
			 }
		}
	
	}//search(int subjectCombobox,String searchT)
	

	
}



