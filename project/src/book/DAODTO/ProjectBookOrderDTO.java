package book.DAODTO;

public class ProjectBookOrderDTO {
	 private String memberId;
	   private String orderDate;
	   private String bookName;
	   private String writer;
	   private String bookType;
	   private String publisher;
	   private int bookPrice;
	   private int bookVolume;//수량

	   public String getMemberId() {
	      return memberId;
	   }

	   public void setMemberId(String memberId) {
	      this.memberId = memberId;
	   }

	   public String getOrderDate() {
	      return orderDate;
	   }

	   public void setOrderDate(String orderdate) {
	      this.orderDate = orderdate;
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

	   public int getBookVolume() {
	      return bookVolume;
	   }

	   public void setBookVolume(int bookVolume) {
	      this.bookVolume = bookVolume;
	   }

	
}
