package book.manager;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import book.DAODTO.ProjectBookDAO;
import book.DAODTO.ProjectBookDTO;
import book.mypage.ProjectBookList;
import book.mypage.ProjectMemberLogin;
import book.mypage.ProjectOrderListForm;

public class ProjectManagerMain extends JFrame implements ActionListener{
	private JLabel bookNameL, writerL, bookTypeL, publisherL, bookPriceL, bookVolumeL,backL;
	private JTextField bookNameT, writerT, bookTypeT, publisherT, bookPriceT, bookVolumeT;
	private JButton insertBtn, cancelBtn, bookListBtn, sellListBtn;
	

	private Font font = new Font("중나좋체 MEDIUM", Font.BOLD, 20);

	
	public ProjectManagerMain() {
		setLayout(null);
		//제목
		bookNameL = new JLabel("제 목 : ");
		bookNameL.setBounds(100,100,110,30);
		bookNameL.setFont(font);
		bookNameT = new JTextField("",15);
		bookNameT.setBounds(180, 100, 130, 30);
		bookNameT.setFont(font);
		//저자
		writerL = new JLabel("저 자 : ");
		writerL.setBounds(100, 160, 110, 30);
		writerL.setFont(font);
		writerT = new JTextField("",15);
		writerT.setBounds(180, 160, 130, 30);
		writerT.setFont(font);
		//장르
		bookTypeL = new JLabel("장 르 : ");
		bookTypeL.setBounds(100, 220, 110, 30);
		bookTypeL.setFont(font);
		bookTypeT = new JTextField("",15);
		bookTypeT.setBounds(180, 220, 130, 30);
		bookTypeT.setFont(font);
		//출판사
		publisherL = new JLabel("출판사 : ");
		publisherL.setBounds(100, 280, 130, 30);
		publisherL.setFont(font);
		publisherT = new JTextField("",15);
		publisherT.setBounds(180, 280, 130, 30);
		publisherT.setFont(font);
		//가격
		bookPriceL = new JLabel("가 격 : ");
		bookPriceL.setBounds(100, 340, 130, 30);
		bookPriceL.setFont(font);
		bookPriceT = new JTextField("",15);
		bookPriceT.setBounds(180, 340, 130, 30);
		bookPriceT.setFont(font);
		//권수
		bookVolumeL = new JLabel("권 수 : ");
		bookVolumeL.setBounds(100, 400, 130, 30);
		bookVolumeL.setFont(font);
		bookVolumeT = new JTextField("", 15);
		bookVolumeT.setBounds(180, 400, 130, 30);
		bookVolumeT.setFont(font);
		
		//back
				ImageIcon icon = new ImageIcon("book.jpg");
				Image img = icon.getImage();
				Image changeImg = img.getScaledInstance(900, 720, Image.SCALE_SMOOTH);
				ImageIcon changeIcon = new ImageIcon(changeImg);
				backL = new JLabel(new ImageIcon(changeImg));
				backL.setBounds(0, 0, 1500, 720);
								
				//btn 생성
				insertBtn = new JButton(new ImageIcon("insertBtn.png"));//등록
				insertBtn.setBounds(110,480, 80, 40);
				insertBtn.setFont(font);
				insertBtn.setBorderPainted(false);

				
				cancelBtn = new JButton(new ImageIcon("cancelBtn2.png"));//취소
				cancelBtn.setBounds(210,480, 80, 40);
				cancelBtn.setFont(font);
				cancelBtn.setBorderPainted(false);

				
				bookListBtn = new JButton(new ImageIcon("jaegoBtn.png"));//도서재고현황
				bookListBtn.setBounds(110, 550, 180, 40);
				bookListBtn.setFont(font);
				bookListBtn.setBorderPainted(false);

				
				sellListBtn = new JButton(new ImageIcon("salesBtn.png"));//도서매출현황
				sellListBtn.setBounds(110, 610, 180, 40);
				sellListBtn.setFont(font);
				sellListBtn.setBorderPainted(false);

				
				Container c = this.getContentPane();
			    c.add(backL);
				c.add(bookNameL);
				c.add(bookNameT);
				c.add(writerL);
				c.add(writerT);
				c.add(bookTypeL);
				c.add(bookTypeT);
				c.add(publisherL);
				c.add(publisherT);
				c.add(bookPriceL);
				c.add(bookPriceT);
				c.add(bookVolumeL);
				c.add(bookVolumeT);
				c.add(insertBtn);
				c.add(cancelBtn);
				c.add(bookListBtn);
				c.add(sellListBtn);
				
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
		
		//이벤트
		insertBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		bookListBtn.addActionListener(this);
		sellListBtn.addActionListener(this);
		
	}//생성자
	//bookdata input
	private void inputBook() {
		Scanner scan = new Scanner(System.in);
		//data
		String bookName = bookNameT.getText();
		String writer = writerT.getText();
		String bookType = bookTypeT.getText();
		String publisher = publisherT.getText();
		int bookPrice = Integer.parseInt(bookPriceT.getText());
		int bookVolume = Integer.parseInt(bookVolumeT.getText());
		
		ProjectBookDAO bookDAO = ProjectBookDAO.getInstance();
		
		int seq = bookDAO.getSeq();
		
		ProjectBookDTO bookDTO = new ProjectBookDTO();
		bookDTO.setSeq(seq);
		bookDTO.setBookName(bookName);
		bookDTO.setWriter(writer);
		bookDTO.setBookType(bookType);
		bookDTO.setPublisher(publisher);
		bookDTO.setBookPrice(bookPrice);
		bookDTO.setBookVolume(bookVolume);
		
		System.out.println("매니저");
		
		bookDAO.inputBook(bookDTO);
		System.out.println("매니저끝");
		remove();
	}//insert
	//remove
	public void remove() {
		bookNameT.setText("");
		writerT.setText("");
		bookTypeT.setText("");
		publisherT.setText("");
		bookPriceT.setText("");
		bookVolumeT.setText("");
		
	}//remove
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==insertBtn) {
			inputBook();
		}else if(e.getSource()== cancelBtn) {
			remove();
			setVisible(false);
			new ProjectMemberLogin();
		}else if(e.getSource()==bookListBtn) {
			new ProjectBookList(1, "admin", "789456");
			setVisible(false);
		}else if(e.getSource() == sellListBtn) {
			new ProjectBookSaleList();
			setVisible(false);
		}
		
	}
	

}
