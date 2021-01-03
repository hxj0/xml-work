package com.hxj.book.util;

import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;



public class XmlUtil {
	
	public static Document getDocument(String fileName) {
		SAXReader reader = new SAXReader(); 
		Document document = null;
		try {
			document = reader.read("src/data/"+fileName+".xml");
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document;
	}
	
    public static <T> void getBeanToXml(Document document, T obj) {
    //long lasting = System.currentTimeMillis();//效率检测

      String rootname = obj.getClass().getSimpleName().toLowerCase();//获得类名
      Element root = document.getRootElement();//获取根节点
      Field[] properties = obj.getClass().getDeclaredFields();//获得实体类的所有属性

      //递归实体
      Element secondRoot = root.addElement(rootname);            //二级节点

      for (int i = 0; i < properties.length; i++) {
          //反射get方法
          Method getmeth = null;
          try {
              //为二级节点添加属性，属性值为对应属性的值
              getmeth = obj.getClass().getMethod(
                      "get"
                              + properties[i].getName().substring(0, 1)
                              .toUpperCase()
                              + properties[i].getName().substring(1));
              if (i == 0) {
                  secondRoot.addAttribute(properties[i].getName().toUpperCase(), getmeth.invoke(obj).toString());
              } else {
                  secondRoot.addElement(properties[i].getName()).setText(
                          getmeth.invoke(obj).toString());
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
      }
    }
    
    public static void writeXML(Document document,String fileName) {
    	OutputFormat format = new OutputFormat("\t", true);
        format.setTrimText(true); //去掉空白
        // 在创建Writer时，指定格式化器
        XMLWriter writer = null;
        try {
            writer = new XMLWriter(new FileOutputStream("src/data/"+fileName+".xml"), format);
            writer.write(document);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
