package com.sist.main;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sist.dao.*;

import java.net.URLEncoder;
import java.util.*;
public class MainClass {
	public static void main(String[] args) {
		MainClass mc=new MainClass();
//		mc.foodSGetData();
		mc.foodGuGetData();
	}
	
	public void foodSGetData()
	{
		FoodDAO dao=FoodDAO.newInstance();
		try
		{
			String gus = "서울";
			int k=1;
			int m=1;
			
				System.out.println("구:"+gus);
				
				for(int i=1; i<=10; i++)
				{
					String url="https://www.mangoplate.com/search/"
							+URLEncoder.encode(gus, "UTF-8")+"?keyword="
							+URLEncoder.encode(gus, "UTF-8")+"&page="+i;
					Document doc=Jsoup.connect(url).get();
					Elements link=doc.select("figure.restaurant-item a.only-desktop_not");
					for(int j=0; j<link.size(); j++)
					{
						System.out.println(link.get(j).attr("href"));
						Document doc2=
								Jsoup.connect("https://www.mangoplate.com"+link.get(j).attr("href")).get();
						   Element title=doc2.selectFirst("span.title h1.restaurant_name");
						   String sss="";
						   try
						   {
							   Element score=doc2.selectFirst("strong.rate-point span");
							   sss=score.text();
						   }catch(Exception ex) 
						   {
							   sss="0.0";
						   }
					       Elements poster=doc2.select("figure.restaurant-photos-item img.center-croping");
					       String image="";
					       for(int o=0;o<poster.size();o++)
					       {
					    	   image+=poster.get(o).attr("src")+"^"; // , 
					       }
					       image=image.substring(0,image.lastIndexOf("^"));// 마지막 ^를 제거  => 구분문자
					       image=image.replace("&", "#"); // 오라클에는 &를 #으로 바꾸지만 다시 출력할때는 반대로 한다
					       StringTokenizer st=new StringTokenizer(image,"^");
					       int p=1;
					       
					       while(st.hasMoreTokens())
					       {
					    	   System.out.println(p+"."+st.nextToken());
					    	   p++;
					       }
//					       String s2="";
//					       Elements etc2=doc2.select("table.info tr th");
//					       for(int b=0; b<etc2.size(); b++)
//					       {
//					    	   String s1=etc2.get(b).text();
//					    	   try
//					    	   {
//					    		   if(s1.equals("웹 사이트"))
//					    		   {
//					    			   Element info=doc2.selectFirst("table.info tr th a");
//					    			   System.out.println("웹 사이트:"+info.attr("href"));
//					    		   }
//					    	   }catch(Exception ex)
//					    	   {
//					    		   s2="no";
//					    		   System.out.println("웹사이트:"+s2);
//					    	   }
//					       }
					       
					       
					       String addr="no",phone="no",tp="no",pr="no",pa="no",ti="no", mu="no";
					       Elements etc=doc2.select("table.info tr th");
					       //System.out.println(etc);
					       for(int a=0;a<etc.size();a++)
					       {
					    	   String ss=etc.get(a).text();
					    	   if(ss.equals("주소"))
					    	   {
					    		   Element e=doc2.select("table.info td").get(a);
					    		   addr=e.text();
					    	   }
					    	   else if(ss.equals("전화번호"))
					    	   {
					    		   Element e=doc2.select("table.info td").get(a);
					    		   phone=e.text();
					    	   }
					    	   else if(ss.equals("음식 종류"))
					    	   {
					    		   Element e=doc2.select("table.info td").get(a);
					    		   tp=e.text();
					    	   }
					    	   else if(ss.equals("가격대"))
					    	   {
					    		   Element e=doc2.select("table.info td").get(a);
					    		   pr=e.text();
					    	   }
					    	   else if(ss.equals("주차"))
					    	   {
					    		   Element e=doc2.select("table.info td").get(a);
					    		   pa=e.text();
					    	   }
					    	   else if(ss.equals("영업시간"))
					    	   {
					    		   Element e=doc2.select("table.info td").get(a);
					    		   ti=e.text();
					    	   }
//					    	   else if(ss.equals("웹 사이트"))
//					    	   {
//					    		   try 
//					    		   {
//						    		   Element e=doc2.select("table.info td a").get(a);
//						    		   we=e.text();
//					    		   }catch(Exception ex) 
//					    		   {
//					    			   we="no";
//					    		   }
//					    	   }
					    	   else if(ss.equals("메뉴"))
					    	   {
					    		   Element e=doc2.select("table.info td").get(a);
					    		   mu=e.text();
					    	   }
					       }
					       System.out.println("번호:"+m);
					       System.out.println("업체명:"+title.text());
					       System.out.println("점수:"+sss);
					       System.out.println("주소:"+addr);
					       System.out.println("전화:"+phone);
					       System.out.println("음식종류:"+tp);
					       System.out.println("가격대:"+pr);
					       System.out.println("주차:"+pa);
					       System.out.println("영업시간:"+ti);
//					       System.out.println("웹사이트:"+info.attr("href"));
					       System.out.println("메뉴:"+mu);
					       System.out.println("===========================");
					       // 데이터베이스 저장 
					       FoodVO fvo=new FoodVO();
					       fvo.setNo(m);
					       fvo.setRno(k);
					       fvo.setRname(title.text());
					       fvo.setScore(Double.parseDouble(sss));
					       fvo.setAddress(addr);
					       fvo.setTel(phone);
					       fvo.setRtype(tp);
					       fvo.setPrice(pr);
					       fvo.setParking(pa);
					       fvo.setOpenHour(ti);
					       fvo.setMenu(mu);
//					       fvo.setInfo(we);
					       fvo.setPoster(image);
					       // 번호는 자동 증가 
					       // <script id="reviewCountInfo" type="application/json">[{"action_value":1,"count":0},{"action_value":2,"count":3},{"action_value":3,"count":23}]</script>
					       Element review=doc2.selectFirst("script#reviewCountInfo");
					       // class(중복) => . , id(중복이 없다) => # => 태그 구분
					       //System.out.println(review.data()); // script => data()
					       String json=review.data(); // javascript
					       // text() => HTML / attr => HTML / html => HTML
					       JSONParser jp=new JSONParser();
					       JSONArray arr=(JSONArray)jp.parse(json); // [{}, {}, {}]
					       String good="",soso="",bad="";
					       for(int b=0;b<arr.size();b++)
					       {
					    	   JSONObject obj=(JSONObject)arr.get(b); // {}
					    	   if(b==0)
					    	   {
					    		   bad=String.valueOf(obj.get("count"));
					    		   // 						키{"count":10}
					    	   }
					    	   else if(b==1)
					    	   {
					    		   soso=String.valueOf(obj.get("count"));
					    	   }
					    	   else if(b==2)
					    	   {
					    		   good=String.valueOf(obj.get("count"));
					    	   }
					       }
					       System.out.println("GOOD:"+good);
					       System.out.println("SOSO:"+soso);
					       System.out.println("BAD:"+bad);
					       fvo.setGood(Integer.parseInt(good));
					       fvo.setSoso(Integer.parseInt(soso));
					       fvo.setBad(Integer.parseInt(bad));
						/*
						 * <ul class="Restaurant__TagList only-mobile"> <li class="Restaurant__TagItem">
						 * <a class="Restaurant__TagLink" href="/search/이자카야"
						 * onclick="trackEvent('CLICK_RELATED_TAG', {&quot;restaurant_key&quot;:&quot;iMRRP69qtkeO&quot;,&quot;keyword&quot;:&quot;이자카야&quot;})"
						 * >#이자카야</a> </li> <li class="Restaurant__TagItem"> <a
						 * class="Restaurant__TagLink" href="/search/고등어"
						 * onclick="trackEvent('CLICK_RELATED_TAG', {&quot;restaurant_key&quot;:&quot;iMRRP69qtkeO&quot;,&quot;keyword&quot;:&quot;고등어&quot;})"
						 * >#고등어</a> </li>
						 */
					       // Elements link=doc.select("div.top_list_slide ul.list-toplist-slider a");
					       // System.out.println(link.get(i).attr("href"));
					       /*
					        *  Elements poster=doc2.select("figure.restaurant-photos-item img.center-croping");
					       String image="";
					       for(int o=0;o<poster.size();o++)
					       {
					    	   image+=poster.get(o).attr("src")+"^"; // , 
					       }
					       image=image.substring(0,image.lastIndexOf("^"));// 마지막 ^를 제거  => 구분문자
					       image=image.replace("&", "#"); // 오라클에는 &를 #으로 바꾸지만 다시 출력할때는 반대로 한다
					       StringTokenizer st=new StringTokenizer(image,"^");
					       int p=1;
					       
					       while(st.hasMoreTokens())
					       {
					    	   System.out.println(p+"."+st.nextToken());
					    	   p++;
					       }
					        */
//					       String ssss="";
//					       try 
//					       {
						       Elements rtag=doc2.select("ul.Restaurant__TagList a.Restaurant__TagLink");
						       String tag="";
						       for(int l=0; l<rtag.size(); l++)
						       {
						    	   tag+=rtag.get(l).text()+"^";
						       }
						       tag=tag.substring(0,tag.lastIndexOf("^"));
						       StringTokenizer stt=new StringTokenizer(tag,"^");
						       int q=1;
						       while(stt.hasMoreTokens())
						       {
						    	   System.out.println(q+"."+stt.nextToken());
						    	   q++;
						       }
//						   }catch(Exception ex)
//					       {
//					    	   ssss="no";
//					       }
//					       fvo.setRtag(tag);
					       
					       
					       // 데이터베이스에 저장 
					       dao.foodInsert(fvo);
					       m++;
					       System.out.println("=============================================================");
					   }
					}
				
				k++;
			
		}catch(Exception ex) {}
	}
	
	
	public void foodGuGetData()
	{
		FoodDAO dao=FoodDAO.newInstance();
		try
		{
			String[] gus = { "전체", "강서구", "양천구", "구로구", "마포구", "영등포구", "금천구",
				    "은평구", "서대문구", "동작구", "관악구", "종로구", "중구", "용산구", "서초구", "강북구",
				    "성북구", "도봉구", "동대문구", "성동구", "강남구", "노원구", "중랑구", "광진구", "송파구",
				    "강동구" };
			int k=2;
			int m=201;
			for(String gu:gus)
			{
				System.out.println("구:"+gu);
				
				for(int i=1; i<=10; i++)
				{
					String url="https://www.mangoplate.com/search/"
							+URLEncoder.encode(gu, "UTF-8")+"?keyword="
							+URLEncoder.encode(gu, "UTF-8")+"&page="+i;
					Document doc=Jsoup.connect(url).get();
					Elements link=doc.select("figure.restaurant-item a.only-desktop_not");
					for(int j=0; j<link.size(); j++)
					{
						System.out.println(link.get(j).attr("href"));
						Document doc2=
								Jsoup.connect("https://www.mangoplate.com"+link.get(j).attr("href")).get();
						   Element title=doc2.selectFirst("span.title h1.restaurant_name");
						   String sss="";
						   try
						   {
							   Element score=doc2.selectFirst("strong.rate-point span");
							   sss=score.text();
						   }catch(Exception ex) 
						   {
							   sss="0.0";
						   }
					       Elements poster=doc2.select("figure.restaurant-photos-item img.center-croping");
					       String image="";
					       for(int o=0;o<poster.size();o++)
					       {
					    	   image+=poster.get(o).attr("src")+"^"; // , 
					       }
					       image=image.substring(0,image.lastIndexOf("^"));// 마지막 ^를 제거  => 구분문자
					       image=image.replace("&", "#"); // 오라클에는 &를 #으로 바꾸지만 다시 출력할때는 반대로 한다
					       StringTokenizer st=new StringTokenizer(image,"^");
					       int p=1;
					       
					       while(st.hasMoreTokens())
					       {
					    	   System.out.println(p+"."+st.nextToken());
					    	   p++;
					       }
//					       String s2="";
//					       Elements etc2=doc2.select("table.info tr th");
//					       for(int b=0; b<etc2.size(); b++)
//					       {
//					    	   String s1=etc2.get(b).text();
//					    	   try
//					    	   {
//					    		   if(s1.equals("웹 사이트"))
//					    		   {
//					    			   Element info=doc2.selectFirst("table.info tr th a");
//					    			   System.out.println("웹 사이트:"+info.attr("href"));
//					    		   }
//					    	   }catch(Exception ex)
//					    	   {
//					    		   s2="no";
//					    		   System.out.println("웹사이트:"+s2);
//					    	   }
//					       }
					       
					       
					       String addr="no",phone="no",tp="no",pr="no",pa="no",ti="no", mu="no";
					       Elements etc=doc2.select("table.info tr th");
					       //System.out.println(etc);
					       for(int a=0;a<etc.size();a++)
					       {
					    	   String ss=etc.get(a).text();
					    	   if(ss.equals("주소"))
					    	   {
					    		   Element e=doc2.select("table.info td").get(a);
					    		   addr=e.text();
					    	   }
					    	   else if(ss.equals("전화번호"))
					    	   {
					    		   Element e=doc2.select("table.info td").get(a);
					    		   phone=e.text();
					    	   }
					    	   else if(ss.equals("음식 종류"))
					    	   {
					    		   Element e=doc2.select("table.info td").get(a);
					    		   tp=e.text();
					    	   }
					    	   else if(ss.equals("가격대"))
					    	   {
					    		   Element e=doc2.select("table.info td").get(a);
					    		   pr=e.text();
					    	   }
					    	   else if(ss.equals("주차"))
					    	   {
					    		   Element e=doc2.select("table.info td").get(a);
					    		   pa=e.text();
					    	   }
					    	   else if(ss.equals("영업시간"))
					    	   {
					    		   Element e=doc2.select("table.info td").get(a);
					    		   ti=e.text();
					    	   }
//					    	   else if(ss.equals("웹 사이트"))
//					    	   {
//					    		   try 
//					    		   {
//						    		   Element e=doc2.select("table.info td a").get(a);
//						    		   we=e.text();
//					    		   }catch(Exception ex) 
//					    		   {
//					    			   we="no";
//					    		   }
//					    	   }
					    	   else if(ss.equals("메뉴"))
					    	   {
					    		   Element e=doc2.select("table.info td").get(a);
					    		   mu=e.text();
					    	   }
					       }
					       System.out.println("번호:"+m);
					       System.out.println("업체명:"+title.text());
					       System.out.println("점수:"+sss);
					       System.out.println("주소:"+addr);
					       System.out.println("전화:"+phone);
					       System.out.println("음식종류:"+tp);
					       System.out.println("가격대:"+pr);
					       System.out.println("주차:"+pa);
					       System.out.println("영업시간:"+ti);
//					       System.out.println("웹사이트:"+info.attr("href"));
					       System.out.println("메뉴:"+mu);
					       System.out.println("===========================");
					       // 데이터베이스 저장 
					       FoodVO fvo=new FoodVO();
					       fvo.setNo(m);
					       fvo.setRno(k);
					       fvo.setRname(title.text());
					       fvo.setScore(Double.parseDouble(sss));
					       fvo.setAddress(addr);
					       fvo.setTel(phone);
					       fvo.setRtype(tp);
					       fvo.setPrice(pr);
					       fvo.setParking(pa);
					       fvo.setOpenHour(ti);
					       fvo.setMenu(mu);
//					       fvo.setInfo(we);
					       fvo.setPoster(image);
					       // 번호는 자동 증가 
					       // <script id="reviewCountInfo" type="application/json">[{"action_value":1,"count":0},{"action_value":2,"count":3},{"action_value":3,"count":23}]</script>
					       Element review=doc2.selectFirst("script#reviewCountInfo");
					       // class(중복) => . , id(중복이 없다) => # => 태그 구분
					       //System.out.println(review.data()); // script => data()
					       String json=review.data(); // javascript
					       // text() => HTML / attr => HTML / html => HTML
					       JSONParser jp=new JSONParser();
					       JSONArray arr=(JSONArray)jp.parse(json); // [{}, {}, {}]
					       String good="",soso="",bad="";
					       for(int b=0;b<arr.size();b++)
					       {
					    	   JSONObject obj=(JSONObject)arr.get(b); // {}
					    	   if(b==0)
					    	   {
					    		   bad=String.valueOf(obj.get("count"));
					    		   // 						키{"count":10}
					    	   }
					    	   else if(b==1)
					    	   {
					    		   soso=String.valueOf(obj.get("count"));
					    	   }
					    	   else if(b==2)
					    	   {
					    		   good=String.valueOf(obj.get("count"));
					    	   }
					       }
					       System.out.println("GOOD:"+good);
					       System.out.println("SOSO:"+soso);
					       System.out.println("BAD:"+bad);
					       fvo.setGood(Integer.parseInt(good));
					       fvo.setSoso(Integer.parseInt(soso));
					       fvo.setBad(Integer.parseInt(bad));
						/*
						 * <ul class="Restaurant__TagList only-mobile"> <li class="Restaurant__TagItem">
						 * <a class="Restaurant__TagLink" href="/search/이자카야"
						 * onclick="trackEvent('CLICK_RELATED_TAG', {&quot;restaurant_key&quot;:&quot;iMRRP69qtkeO&quot;,&quot;keyword&quot;:&quot;이자카야&quot;})"
						 * >#이자카야</a> </li> <li class="Restaurant__TagItem"> <a
						 * class="Restaurant__TagLink" href="/search/고등어"
						 * onclick="trackEvent('CLICK_RELATED_TAG', {&quot;restaurant_key&quot;:&quot;iMRRP69qtkeO&quot;,&quot;keyword&quot;:&quot;고등어&quot;})"
						 * >#고등어</a> </li>
						 */
					       // Elements link=doc.select("div.top_list_slide ul.list-toplist-slider a");
					       // System.out.println(link.get(i).attr("href"));
					       /*
					        *  Elements poster=doc2.select("figure.restaurant-photos-item img.center-croping");
					       String image="";
					       for(int o=0;o<poster.size();o++)
					       {
					    	   image+=poster.get(o).attr("src")+"^"; // , 
					       }
					       image=image.substring(0,image.lastIndexOf("^"));// 마지막 ^를 제거  => 구분문자
					       image=image.replace("&", "#"); // 오라클에는 &를 #으로 바꾸지만 다시 출력할때는 반대로 한다
					       StringTokenizer st=new StringTokenizer(image,"^");
					       int p=1;
					       
					       while(st.hasMoreTokens())
					       {
					    	   System.out.println(p+"."+st.nextToken());
					    	   p++;
					       }
					        */
//					       String ssss="";
//					       try 
//					       {
						       Elements rtag=doc2.select("ul.Restaurant__TagList a.Restaurant__TagLink");
						       String tag="";
						       for(int l=0; l<rtag.size(); l++)
						       {
						    	   tag+=rtag.get(l).text()+"^";
						       }
						       tag=tag.substring(0,tag.lastIndexOf("^"));
						       StringTokenizer stt=new StringTokenizer(tag,"^");
						       int q=1;
						       while(stt.hasMoreTokens())
						       {
						    	   System.out.println(q+"."+stt.nextToken());
						    	   q++;
						       }
//						   }catch(Exception ex)
//					       {
//					    	   ssss="no";
//					       }
					       fvo.setRtag(tag);
					       
					       // 데이터베이스에 저장 
					       dao.foodInsert(fvo);
					       m++;
					       System.out.println("=============================================================");
					   }
					}
				}
				k++;
			
		}catch(Exception ex) {}
	}
}
