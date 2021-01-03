package com.hxj.book.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;
import java.util.regex.Pattern;

import org.dom4j.Document;
import org.dom4j.Element;

import com.hxj.book.util.XmlUtil;

public class Test {
	public static void main(String[] args) throws ParseException {
//		Document document = XmlUtil.getDocument("user");
//		System.out.println(document.getRootElement().elements().get(0).elementText("username"));
//		JLabel jLabel = new JLabel();
//		System.out.println(new Date("2020-11-1".replace('-', '/')));
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		System.out.println(sdf.format(sdf.parse("2020-11-9"))); 
//		Document document = XmlUtil.getDocument("book");
//		document.clearContent();
		
//
//    	Document document = XmlUtil.getDocument("book");
//    	Element root = document.getRootElement();
//    	root.remove(root.elementByID("5")); 
////    	System.out.println(root.elementByID("5"));
//		XmlUtil.writeXML(document,"book");
//		System.out.println(Pattern.matches("^\\d{4}-\\d{2}-\\d\\d?$", "2000-22-"));
//		System.out.println("2001".compareTo("31")); 
		long cur = System.currentTimeMillis();
//		System.out.println(UUID.randomUUID());  //太慢？
		System.out.println(Math.random());
		System.out.println(System.currentTimeMillis()-cur); 
	}
}
