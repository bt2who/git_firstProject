package book.DAODTO;


public class ProjectCartDTO {
	 private String memberID; //아이디
	   private String orderDate;
	   private String bookName; // 책제목
	   private String writer;//저자
	   private String bookType;//장르
	   private String publisher; //출판사
	   private int bookPrice; // 가격
	   private int bookVolume;//수량
	   
	   
	   public String getOrderDate() {
	      return orderDate;
	   }
	   public int getBookVolume() {
	      return bookVolume;
	   }
	   public void setBookVolume(int bookVolume) {
	      this.bookVolume = bookVolume;
	   }
	   public void setOrderDate(String orderDate) {
	      this.orderDate = orderDate;
	   }
	   public String getMemberID() {
	      return memberID;
	   }
	   public void setMemberID(String memberID) {
	      this.memberID = memberID;
	   }
	   public String getBookName() {
	      return bookName;
	   }
	   public void setBookName(String bookName) {
	      this.bookName = bookName;
	   }
	   public String getWriter() {
	      return writer;
	   }
	   public void setWriter(String writer) {
	      this.writer = writer;
	   }
	   public String getBookType() {
	      return bookType;
	   }
	   public void setBookType(String bookType) {
	      this.bookType = bookType;
	   }
	   public String getPublisher() {
	      return publisher;
	   }
	   public void setPublisher(String publisher) {
	      this.publisher = publisher;
	   }
	   public int getBookPrice() {
	      return bookPrice;
	   }
	   public void setBookPrice(int bookPrice) {
	      this.bookPrice = bookPrice;
	   }
	

}
