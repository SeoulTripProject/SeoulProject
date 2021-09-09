package com.sist.main;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sist.dao.*;
import com.sist.vo.*;
import java.net.URLEncoder;
import java.util.*;
public class PracticeClass4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PracticeClass4 mc=new PracticeClass4();
	       mc.PracticeData();
	}
	public void PracticeData()
	   {
	      ExbitDAO dao=ExbitDAO.newInstance(); // 싱글턴
	      try
	      {
	    	  int k=1; 
	    	  int c=1;
	    	 for(int q=0; q<=4; q++)
	    	 {
	    		 String url="https://korean.visitseoul.net/culture?curPage="+q;
				 Document doc=Jsoup.connect(url).get();
	    		 Elements link=doc.select("li.item a");
	    		 
	    		 for(int w=0; w<link.size(); w++)
	    		 {
	    			 Document doc2=
								Jsoup.connect("https://korean.visitseoul.net"+link.get(w).attr("href")).get();
	    			 Element title=doc2.selectFirst("div.sub-contents-inner section.infor-element h3.h3");
	    			 Elements detail=doc2.select("div.detial-cont-infor p");
	    			 Elements period=doc2.select("div.detial-reserve p.reserve-day-text");
	    			 System.out.println("제목:"+title.text());
					 System.out.println("줄거리:"+detail.text());
					 System.out.println(period.text());
	    			 
	    			 
	    				 CultureVO vo=new CultureVO();
	    				 try
	    				 {

	    					 vo.setCno(k);
	    					 vo.setNo(c);
	    					 vo.setTitle(vo.getTitle());
	    					 vo.setStory(vo.getStory());

	    					 Elements poster=doc2.select("div.detial-cont-thumb img");
	    					 String image="";
	    					 for(int j=0;j<poster.size();j++)
	    					 {
	    						 image+=poster.get(j).attr("src")+"^"; // , 
	    					 }
	    					 image=image.substring(0,image.lastIndexOf("^"));
	    					 
	    					 int h=1;
	    					 StringTokenizer st=new StringTokenizer(image,"^");
	    					 while(st.hasMoreTokens())
	                         {
	                            System.out.println(h+"."+st.nextToken());
	                            h++;
	                         }
	                         vo.setPoster(image);
	    					 
	    					 String per="no",te="no",ti="no",da="no",pr="no",ad="no",tr="no";
	    					 Elements etc=doc2.select("div.detail-map-infor dl dt");
	    					 
	    					 int m=1;
	    					 for(int a=0;a<etc.size();a++)
	    					 {
	    						 String ss=etc.get(a).text();
	    						 
	    						 if(ss.equals("전화번호"))
	    						 {
	    							 Element e=doc2.select("div.detail-map-infor dl dd").get(a);
	    							 te=e.text();
	    						 }
	    						 else if(ss.equals("주소"))
	    						 {
	    							 Element e=doc2.select("div.detail-map-infor dl dd").get(a);
	    							 ad=e.text();
	    						 }
	    					 }
	    					 System.out.println("전화번호:"+te);
	    					 System.out.println("주소:"+ad);
	    					 System.out.println("===========================");
	    					 
	    					 ExbitVO evo=new ExbitVO();
	    					 evo.setNo(m);
	    					 evo.setEno(vo.getCno());
	    					 evo.setPeriod(per);
	    					 evo.setTel(te);
	    					 evo.setTime(ti);
	    					 evo.setDay(da);
	    					 evo.setPrice(pr);
	    					 evo.setAddr(ad);
	    					 evo.setTrans(tr);
	    					 evo.setPoster(image);
	    					
	    				
	    				 }catch(Exception ex) {}
	    		 }
	    	 }
	    		 
	    	 
	      }catch(Exception ex)
	      {
	    	  ex.printStackTrace();
	      }
	  
	   



}
}
