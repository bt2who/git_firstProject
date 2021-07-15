create table orderlist(
memberid varchar2(50),--아이디
orderdate varchar2(50),—--주문날짜
bookname varchar2(150),--상품정보
writer varchar2(50),--저자
booktype varchar2(50),--장르
publisher varchar2(50),--출판사
bookprice number,--금액
bookvolume number);--수량

create table memberInfo(
position number ,  -- 회원 0 , 관리자 1
id varchar2(30),   
pwd varchar2(20) , 
name varchar2(30), 
age number,   -- 생년월일 [예]210423 추후  birth로 변경건의(변경하지않고 age로)
address varchar2(100),
email varchar2(100),
email2 varchar2(30),
phone varchar2(50) 
);

create table cart(
memberid varchar2(50),--아이디
orderdate varchar2(50),--주문날짜
bookname varchar2(150),--상품정보
writer varchar2(50),--저자
booktype varchar2(50)--장르
publisher varchar2(50)--출판사
bookprice number,--금액
bookvolume number);--수량

create table booktable (seq number,
                        bookName varchar2(100),
                        writer varchar2(50),
                        bookType varchar2(50),
                        publisher varchar2(50),
                        bookPrice number,
                        bookVolume number
                        );
