package com.csse.common;


//import javax.xml.transform.TransformerFactoryConfigurationError;
//import javax.xml.transform.TransformerException;
import java.io.File;
//import org.xml.sax.SAXException;
//import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.NodeList;
//import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
//import javax.xml.transform.TransformerConfigurationException;

public class CommonUtil extends CommonProperties {
	
	public static String getEmployeeQueries(String id) throws Exception {
		NodeList node; Element element = null;
		node = DocumentBuilderFactory.newInstance().newDocumentBuilder()
				.parse(new File("src/com/csse/config/EmployeeQuery.xml"))
				.getElementsByTagName("query");
		for (int x = 0; x < node.getLength(); x++) {
			element = (Element) node.item(x);
			if (element.getAttribute("id").equals(id))
				break;
		}
		return element.getTextContent().trim();
	}
}
