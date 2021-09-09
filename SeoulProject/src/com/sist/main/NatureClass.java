package com.sist.main;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sist.dao.NatureDAO;
import com.sist.dao.NatureVO;

//내부사이트로 이동해서 세부데이터 긁는게 안되는데... 도움좀 부탁드려요 ㅠㅠ

public class NatureClass {
	public static void main(String[] args) {
		NatureClass mc = new NatureClass();
		mc.natureData();
	}

	public void natureData() {
		NatureDAO dao = NatureDAO.newInstance();
		try {

			int page = 2; //14
			for (int j = 1; j <= page; j++) {
				String url = "https://korean.visitseoul.net/nature"+"?curPage=" + j;
				Document doc = Jsoup.connect(url).get(); //페이지 소스
				Elements elements = doc.select("li.item"); // 한 덩어리, size() : 8

				
				// elements 크기는 8, 8개 데이터 추출하니까 반복문
				for (int i = 0; i < elements.size(); i++) {
					try {
						String title = elements.get(i).select("span.title").text(); 
						String intro = elements.get(i).getElementsByAttributeValue("class", "small-text text-dot-d")
								.text();
						String link = elements.get(i).select("a").attr("href");

						String fullLink = "https://korean.visitseoul.net" + link;
						// System.out.println(strLink);
						// NatureVO vo = new NatureVO();
						/*
						 * vo.setTitle(title); //넣음 vo.setIntro(intro); //넣음 vo.setTno(k++); //넣음
						 * vo.setLink(fullLink); //넣음
						 * 
						 * // dao.NatureInsert(vo);
						 */
						// 내부 웹사이트
						Document doc2 = Jsoup.connect(fullLink).get();
						// content
						String content = doc2.getElementsByAttributeValue("class", "text-area").text(); // content
						// vo.setContent(content);

						Elements etc = doc2.select("div.detail-map-infor dl dt");
						
						// etc.text() 가 전화번호면
						String te = "no", web = "no", ti = "no", op = "no", holi = "no", pri = "no", hand = "no",
								cau = "no", cate = "no", si="no", tra="no";
						for (int a = 0; a < etc.size(); a++)
						{
							String ss = etc.get(a).text();
							if (ss.equals("전화번호")) {
								Element e = doc2.select("div.detail-map-infor dl dd").get(a);
								te = e.text();
							} else if (ss.equals("웹사이트")) {
								Element e = doc2.select("div.detail-map-infor dl dd a").get(a);
								web = e.attr("href");
							} else if (ss.equals("이용시간")) {
								Element e = doc2.select("div.detail-map-infor dl dd").get(a);
								ti = e.text();
							} else if (ss.equals("휴무일")) {
								Element e = doc2.select("div.detail-map-infor dl dd").get(a);
								holi = e.text();
							} else if (ss.equals("운영 요일")) {
								Element e = doc2.select("div.detail-map-infor dl dd").get(a);
								op = e.text();
							} else if (ss.equals("장애인 편의시설")) {
								Element e = doc2.select("div.detail-map-infor dl>img").get(a);
								hand = e.text();
							} else if (ss.equals("이것만은 꼭!")) {
								Element e = doc2.select("div.detail-map-infor dl dd").get(a);
								cau = e.text();
							} else if (ss.equals("이용요금")) {
								Element e = doc2.select("div.detail-map-infor dl dd").get(a);
								pri = e.text();
							}else if (ss.equals("주소")) {
								Element e = doc2.select("div.detail-map-infor dl dd").get(a);
								si = e.text();
							}else if (ss.equals("교통 정보")) {
								Element e = doc2.select("div.detail-map-infor dl dd").get(a);
								tra = e.text();
							}
	
						}
						System.out.println(te);
						System.out.println(web);
						System.out.println(ti);
						System.out.println(op);
						System.out.println(holi);
						System.out.println(pri);
						System.out.println(hand);
						System.out.println(cau);
						System.out.println(si);
						System.out.println(tra);
						System.out.println("===================="); 
						
						
					} catch (Exception ex) {}

				}
			}
		} catch (IOException e) {
		}

	}

}