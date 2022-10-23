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

/**
 * The class Common util extends common properties
 */
public class CommonUtil extends CommonProperties {

	public static final String EMPLOYEE_QUERY_FILE_PATH = properties.getProperty("employeeQuery");

	/**
	 *
	 * Gets the employee queries
	 *
	 * @param id
	 *            the id
	 * @return the employee queries
	 * @throws Exception
	 */
	public static String getEmployeeQueries(String id) throws Exception {

		NodeList node;
		Element element = null;
		node = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(EMPLOYEE_QUERY_FILE_PATH))
				.getElementsByTagName("query");
		for (int x = 0; x < node.getLength(); x++) {
			element = (Element) node.item(x);
			if (element.getAttribute("id").equals(id))
				break;
		}
		return element.getTextContent().trim();
	}
}
