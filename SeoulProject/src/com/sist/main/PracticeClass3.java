package com.sist.main;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sist.dao.*;

import java.net.URLEncoder;
import java.util.*;
public class PracticeClass3 {

   public static void main(String[] args) {
      // TODO Auto-generated method stub
       PracticeClass3 mc=new PracticeClass3();
       mc.PracticeData();

   }
   // 영화

   public void PracticeData()
   {
      ExbitDAO dao=ExbitDAO.newInstance(); // 싱글턴
      try
      {
    	  int k=1; 
    	  int c=1;
    	 for(int q=0; q<=3; q++)
    	 {
    		 String url="https://korean.visitseoul.net/exhibition?curPage="+q;
			 Document doc=Jsoup.connect(url).get();
    		 Elements link=doc.select("li.item a");
    		 
    		 for(int w=0; w<link.size(); w++)
    		 {
    			 Document doc2=
							Jsoup.connect("https://korean.visitseoul.net"+link.get(w).attr("href")).get();
    			 Element title=doc2.selectFirst("div.sub-contents-inner section.infor-element h3.h3");
    			 Elements detail=doc2.select("div.text-area p span");
    			 System.out.println("제목:"+title.text());
				 System.out.println("디테일:"+detail.text());
    			 
    			 
    				 ExbitVO vo=new ExbitVO();
    				 try
    				 {

    					 vo.setEno(k);
    					 vo.setNo(c);
    					 vo.setTitle(vo.getTitle());
    					 vo.setDetail(vo.getDetail());

    					 Elements poster=doc2.select("div.wide-slide div.item");
    					 String image="";
    					 for(int j=0;j<poster.size();j++)
    					 {
    						 image+=poster.get(j).attr("style")+"^"; // , 
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
    						 if(ss.equals("행사 기간"))
    						 {
    							 Element e=doc2.select("div.detail-map-infor dl dd").get(a);
    							 per=e.text();
    						 }
    						 else if(ss.equals("전화번호"))
    						 {
    							 Element e=doc2.select("div.detail-map-infor dl dd").get(a);
    							 te=e.text();
    						 }
    						 else if(ss.equals("이용시간"))
    						 {
    							 Element e=doc2.select("div.detail-map-infor dl dd").get(a);
    							 ti=e.text();
    						 }
    						 else if(ss.equals("운영 요일"))
    						 {
    							 Element e=doc2.select("div.detail-map-infor dl dd").get(a);
    							 da=e.text();
    						 }
    						 else if(ss.equals("이용 요금"))
    						 {
    							 Element e=doc2.select("div.detail-map-infor dl dd").get(a);
    							 pr=e.text();
    						 }
    						 else if(ss.equals("주소"))
    						 {
    							 Element e=doc2.select("div.detail-map-infor dl dd").get(a);
    							 ad=e.text();
    						 }
    						 else if(ss.equals("교통 정보"))
    						 {
    							 Element e=doc2.select("div.detail-map-infor dl dd").get(a);
    							 tr=e.text();
    						 }
    					 }
    					 System.out.println("행사 기간:"+per);
    					 System.out.println("전화번호:"+te);
    					 System.out.println("이용시간:"+ti);
    					 System.out.println("운영 요일:"+da);
    					 System.out.println("이용 요금:"+pr);
    					 System.out.println("주소:"+ad);
    					 System.out.println("교통 정보:"+tr);
    					 System.out.println("===========================");
    					 
    					 ExbitVO evo=new ExbitVO();
    					 evo.setNo(m);
    					 evo.setEno(vo.getEno());
    					 evo.setPeriod(per);
    					 evo.setTel(te);
    					 evo.setTime(ti);
    					 evo.setDay(da);
    					 evo.setPrice(pr);
    					 evo.setAddr(ad);
    					 evo.setTrans(tr);
    					 evo.setPoster(image);
    					 
    					 Elements tag=doc2.select("section.tag-element p a");
					       String etag="";
					       for(int l=0; l<tag.size(); l++)
					       {
					    	   etag+=tag.get(l).text()+"^";
					       }
					       etag=etag.substring(0,etag.lastIndexOf("^"));
					       StringTokenizer stt=new StringTokenizer(etag,"^");
					       int z=1;
					       while(stt.hasMoreTokens())
					       {
					    	   System.out.println(z+"."+stt.nextToken());
					    	   z++;
					       }
    					 
    					 k++;
    				 }catch(Exception ex) {}
    				 
    				 System.out.println("=====================================================================");
    			 }
    		 }
    		 
    	 
      }catch(Exception ex)
      {
    	  ex.printStackTrace();
      }
  }


}
