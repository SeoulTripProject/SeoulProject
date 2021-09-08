package com.sist.vo;
/*
이름       널?       유형             
-------- -------- -------------- 
TNO      NOT NULL NUMBER         
NO                NUMBER         
TITLE    NOT NULL VARCHAR2(1000) 
POSTER            VARCHAR2(4000) 
INTRO             CLOB           
NTAG              VARCHAR2(1000) 
LOC               VARCHAR2(500)  
CONTENT           CLOB           
TEL               VARCHAR2(50)   
WEBSITE           VARCHAR2(1000) 
TIME              VARCHAR2(1000) 
HOLIDAY           VARCHAR2(500)  
OPEN              VARCHAR2(1000) 
PRICE             VARCHAR2(500)  
HANDI             VARCHAR2(2000) 
CAUTION           VARCHAR2(2000) 
SITE              VARCHAR2(1000) 
TRAFFIC           VARCHAR2(1000) 
CATEGORY          VARCHAR2(1000) 
      
*/    
public class NatureVO {
	private int tno,no;
	private String title,poster,intro,ntag,loc,content,tel,website,time,holiday,open,price,handi,caution,site,
					traffic,category,link;
	
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public int getTno() {
		return tno;
	}
	public void setTno(int tno) {
		this.tno = tno;
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
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getNtag() {
		return ntag;
	}
	public void setNtag(String ntag) {
		this.ntag = ntag;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getHoliday() {
		return holiday;
	}
	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getHandi() {
		return handi;
	}
	public void setHandi(String handi) {
		this.handi = handi;
	}
	public String getCaution() {
		return caution;
	}
	public void setCaution(String caution) {
		this.caution = caution;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getTraffic() {
		return traffic;
	}
	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	
	
	
}