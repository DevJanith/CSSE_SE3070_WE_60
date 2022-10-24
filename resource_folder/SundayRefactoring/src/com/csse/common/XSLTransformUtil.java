package com.csse.common;

//import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathFactory;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
//import java.io.IOException;
//import java.text.MessageFormat;
import java.util.ArrayList;
//import javax.xml.xpath.XPathExpressionException;
import javax.xml.parsers.DocumentBuilderFactory;
//import org.xml.sax.SAXException;
//import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import org.w3c.dom.Document;
//import javax.xml.transform.TransformerConfigurationException;
//import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.TransformerFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
//import javax.xml.xpath.XPathExpression;

/**
 * The class XSL transform util extends common properties
 */
public class XSLTransformUtil extends CommonProperties {

	private static final ArrayList<Map<String, String>> l = new ArrayList<Map<String, String>>();

	private static Map<String, String> m = null;

	public static final String EMPLOYEE_REQUEST_FILE_PATH = properties.getProperty("employeeRequest");
	public static final String EMPLOYEE_RESPONCE_FILE_PATH = properties.getProperty("employeeResponce");
	public static final String EMPLOYEE_MODIFIED_FILE_PATH = properties.getProperty("employeeModified");

	/**
	 *
	 * Request transform
	 *
	 * @param Exception
	 *            the exception
	 * @throws Exception
	 */
	public static void requestTransform() throws Exception {

		Source request = new StreamSource(new File(EMPLOYEE_REQUEST_FILE_PATH));
		Source modify = new StreamSource(new File(EMPLOYEE_MODIFIED_FILE_PATH));
		Result response = new StreamResult(new File(EMPLOYEE_RESPONCE_FILE_PATH));
		TransformerFactory.newInstance().newTransformer(modify).transform(request, response);
	}

	/**
	 *
	 * XMLXP ATHS
	 *
	 * @param Exception
	 *            the exception
	 * @return String>>
	 * @throws Exception
	 */
	public static ArrayList<Map<String, String>> readXMLPathValues() throws Exception {

		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder()
				.parse(EMPLOYEE_RESPONCE_FILE_PATH);
		XPath request = XPathFactory.newInstance().newXPath();

		int employeeCount = Integer.parseInt(
				(String) request.compile("count(//Employees/Employee)").evaluate(document, XPathConstants.STRING));

		for (int i = 1; i <= employeeCount; i++) {
			m = new HashMap<String, String>();
			m.put("XpathEmployeeIDKey", (String) request.compile("//Employees/Employee[" + i + "]/EmployeeID/text()")
					.evaluate(document, XPathConstants.STRING));
			m.put("XpathEmployeeNameKey",
					(String) request.compile("//Employees/Employee[" + i + "]/EmployeeFullName/text()")
							.evaluate(document, XPathConstants.STRING));
			m.put("XpathEmployeeAddressKey",
					(String) request.compile("//Employees/Employee[" + i + "]/EmployeeFullAddress/text()")
							.evaluate(document, XPathConstants.STRING));
			m.put("XpathFacultyNameKey", (String) request.compile("//Employees/Employee[" + i + "]/FacultyName/text()")
					.evaluate(document, XPathConstants.STRING));
			m.put("XpathDepartmentKey", (String) request.compile("//Employees/Employee[" + i + "]/Department/text()")
					.evaluate(document, XPathConstants.STRING));
			m.put("XpathDesignationKey", (String) request.compile("//Employees/Employee[" + i + "]/Designation/text()")
					.evaluate(document, XPathConstants.STRING));
			l.add(m);
		}
		return l;
	}
}
