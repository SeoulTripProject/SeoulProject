package com.sist.vo;
/*
 *  CNO    NOT NULL NUMBER        
	NO              NUMBER        
	TITLE  NOT NULL VARCHAR2(100) 
	ADDR            VARCHAR2(100) 
	POSTER NOT NULL VARCHAR2(350) 
	PERIOD          VARCHAR2(100) 
	STORY           CLOB          
	TEL             VARCHAR2(50)  
	SEAT            VARCHAR2(30) 
 */
public class CultureVO {
	private int cno,no;
	private String title,addr,poster,period,story,tel,seat;
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getStory() {
		return story;
	}
	public void setStory(String story) {
		this.story = story;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	
}
