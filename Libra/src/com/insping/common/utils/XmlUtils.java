package com.insping.common.utils;

import java.io.CharArrayReader;
import java.io.File;
import java.io.FileFilter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XmlUtils {
	public static final String BR = System.getProperty("line.separator");
	static final FileFilter xmlFileExtFilter = new FileFilter() {
		public boolean accept(File paramFile) {
			return (paramFile.getName().endsWith(".xml"));
		}
	};

	public static Document load(String paramString) throws Exception {
		DocumentBuilderFactory localDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
		localDocumentBuilderFactory.setIgnoringComments(false);
		localDocumentBuilderFactory.setIgnoringElementContentWhitespace(false);
		localDocumentBuilderFactory.setValidating(false);
		localDocumentBuilderFactory.setCoalescing(true);
		DocumentBuilder localDocumentBuilder = localDocumentBuilderFactory.newDocumentBuilder();
		return localDocumentBuilder.parse(paramString);
	}

	public static Document load(File paramFile) throws Exception {
		DocumentBuilderFactory localDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
		localDocumentBuilderFactory.setIgnoringComments(false);
		localDocumentBuilderFactory.setIgnoringElementContentWhitespace(false);
		localDocumentBuilderFactory.setValidating(false);
		localDocumentBuilderFactory.setCoalescing(true);
		DocumentBuilder localDocumentBuilder = localDocumentBuilderFactory.newDocumentBuilder();
		return localDocumentBuilder.parse(paramFile);
	}

	public static String getFileName(String paramString) {
		Pattern localPattern = Pattern.compile("[^\\" + File.separator + "]+.xml");
		Matcher localMatcher = localPattern.matcher(paramString);
		if (localMatcher.find())
			return localMatcher.group().substring(0, localMatcher.group().length() - 4);
		return "";
	}

	public static boolean checkValidity(String paramString) {
		String[] arrayOfString = paramString.split(".");
		return (arrayOfString[(arrayOfString.length - 1)].equals("xml"));
	}

	public static boolean isXml(String paramString) {
		return (paramString.toLowerCase().endsWith("xml"));
	}

	public static Document loadStringWithoutTitle(String paramString) throws Exception {
		paramString = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + BR + paramString;
		return loadString(paramString);
	}

	public static Document loadString(String paramString) throws Exception {
		DocumentBuilderFactory localDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
		localDocumentBuilderFactory.setIgnoringComments(false);
		localDocumentBuilderFactory.setIgnoringElementContentWhitespace(false);
		localDocumentBuilderFactory.setValidating(false);
		localDocumentBuilderFactory.setCoalescing(false);
		DocumentBuilder localDocumentBuilder = localDocumentBuilderFactory.newDocumentBuilder();
		char[] arrayOfChar = new char[paramString.length()];
		paramString.getChars(0, paramString.length(), arrayOfChar, 0);
		InputSource localInputSource = new InputSource(new CharArrayReader(arrayOfChar));
		return localDocumentBuilder.parse(localInputSource);
	}

	public static String getTextByFullName(Document paramDocument, String paramString) {
		String[] arrayOfString = StringUtils.toStringArray(paramString, ".");
		Element localElement = paramDocument.getDocumentElement();
		for (int i = 1; i < arrayOfString.length; ++i)
			localElement = getChildByName(localElement, arrayOfString[i]);
		return getText(localElement);
	}

	public static String getTextByFullName(Element paramElement, String paramString) {
		String[] arrayOfString = StringUtils.toStringArray(paramString, ".");
		Element localElement = paramElement;
		for (int i = 0; i < arrayOfString.length; ++i)
			localElement = getChildByName(localElement, arrayOfString[i]);
		return getText(localElement);
	}

	public static String getChildText(Element paramElement, String paramString) {
		Element localElement = getChildByName(paramElement, paramString);
		if (localElement == null)
			return "";
		return getText(localElement);
	}

	public static Element[] getChildrenByName(Element paramElement, String paramString) {
		NodeList localNodeList = paramElement.getChildNodes();
		int i = localNodeList.getLength();
		LinkedList<Node> localLinkedList = new LinkedList<Node>();
		for (int j = 0; j < i; ++j) {
			Node localNode = localNodeList.item(j);
			if ((localNode.getNodeType() != 1) || (!(localNode.getNodeName().equals(paramString))))
				continue;
			localLinkedList.add(localNode);
		}
		return ((Element[]) localLinkedList.toArray(new Element[localLinkedList.size()]));
	}

	public static Element getChildByName(Element paramElement, String paramString) {
		Element[] arrayOfElement = getChildrenByName(paramElement, paramString);
		if (arrayOfElement.length == 0)
			return null;
		if (arrayOfElement.length > 1)
			throw new IllegalStateException(
					"Too many (" + arrayOfElement.length + ") '" + paramString + "' elements found!");
		return arrayOfElement[0];
	}

	public static String getText(Element paramElement) {
		NodeList localNodeList = paramElement.getChildNodes();
		int i = localNodeList.getLength();
		for (int j = 0; j < i; ++j) {
			Node localNode = localNodeList.item(j);
			if (localNode.getNodeType() == 3)
				return localNode.getNodeValue();
		}
		return "";
	}

	public static String getAttribute(Element paramElement, String paramString) {
		return paramElement.getAttribute(paramString);
	}

	public static int getIntValue(Element paramElement) {
		return Integer.valueOf(getText(paramElement)).intValue();
	}

	public static Long getLongValue(Element paramElement) {
		return Long.valueOf(getText(paramElement));
	}

	public static byte getByteValue(Element paramElement) {
		return Byte.valueOf(getText(paramElement)).byteValue();
	}

	public static Map<String, Object> getProperties(Element paramElement) {
		HashMap<String, Object> localHashMap = new HashMap<String, Object>();
		Element[] arrayOfElement = getChildrenByName(paramElement, "property");
		for (int i = 0; i < arrayOfElement.length; ++i) {
			String str1 = arrayOfElement[i].getAttribute("name");
			String str2 = arrayOfElement[i].getAttribute("type");
			String str3 = getText(arrayOfElement[i]);
			try {
				Class<?> localClass = Class.forName(str2);
				Constructor<?> localConstructor = localClass.getConstructor(new Class[] { String.class });
				Object localObject = localConstructor.newInstance(new Object[] { str3 });
				localHashMap.put(str1, localObject);
			} catch (Exception localException) {
				System.err.println(
						"Unable to parse property '" + str1 + "'='" + str3 + "': " + localException.toString());
			}
		}
		return localHashMap;
	}

	public static void save(String paramString, Document paramDocument) throws Exception {
		DOMSource localDOMSource = new DOMSource(paramDocument);
		File localFile1 = new File(paramString);
		File localFile2 = localFile1.getParentFile();
		localFile2.mkdirs();
		StreamResult localStreamResult = new StreamResult(localFile1);
		try {
			TransformerFactory localTransformerFactory = TransformerFactory.newInstance();
			Transformer localTransformer = localTransformerFactory.newTransformer();
			Properties localProperties = localTransformer.getOutputProperties();
			localProperties.setProperty("encoding", "UTF-8");
			localProperties.setProperty("indent", "yes");
			localTransformer.setOutputProperties(localProperties);
			localTransformer.transform(localDOMSource, localStreamResult);
		} catch (TransformerConfigurationException localTransformerConfigurationException) {
			localTransformerConfigurationException.printStackTrace();
		} catch (TransformerException localTransformerException) {
			localTransformerException.printStackTrace();
		}
	}

	public static Document blankDocument(String paramString) throws Exception {
		DocumentBuilderFactory localDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
		localDocumentBuilderFactory.setIgnoringComments(false);
		localDocumentBuilderFactory.setIgnoringElementContentWhitespace(false);
		localDocumentBuilderFactory.setValidating(false);
		localDocumentBuilderFactory.setCoalescing(false);
		DocumentBuilder localDocumentBuilder = localDocumentBuilderFactory.newDocumentBuilder();
		Document localDocument = localDocumentBuilder.newDocument();
		Element localElement = localDocument.createElement(paramString);
		localDocument.appendChild(localElement);
		return localDocument;
	}

	public static Element createChild(Document paramDocument, Element paramElement, String paramString) {
		Element localElement = paramDocument.createElement(paramString);
		paramElement.appendChild(localElement);
		return localElement;
	}

	public static void createChildText(Document paramDocument, Element paramElement, String paramString1,
			String paramString2) {
		Element localElement = paramDocument.createElement(paramString1);
		localElement.appendChild(paramDocument.createTextNode((paramString2 == null) ? "" : paramString2));
		paramElement.appendChild(localElement);
	}

	public static void createChildTextWithComment(Document paramDocument, Element paramElement, String paramString1,
			String paramString2, String paramString3) {
		Element localElement = paramDocument.createElement(paramString1);
		localElement.appendChild(paramDocument.createTextNode((paramString2 == null) ? "" : paramString2));
		Comment localComment = paramDocument.createComment(paramString3);
		paramElement.appendChild(localComment);
		paramElement.appendChild(localElement);
	}

	public static void createComment(Document paramDocument, String paramString) {
		Comment localComment = paramDocument.createComment(paramString);
		paramDocument.getDocumentElement().appendChild(localComment);
	}

	public static void createOptionalChildText(Document paramDocument, Element paramElement, String paramString1,
			String paramString2) {
		if ((paramString2 == null) || (paramString2.length() == 0))
			return;
		Element localElement = paramDocument.createElement(paramString1);
		localElement.appendChild(paramDocument.createTextNode(paramString2));
		paramElement.appendChild(localElement);
	}

	public static String Doc2String(Document paramDocument) {
		try {
			DOMSource localDOMSource = new DOMSource(paramDocument);
			StringWriter localStringWriter = new StringWriter();
			StreamResult localStreamResult = new StreamResult(localStringWriter);
			TransformerFactory localTransformerFactory = TransformerFactory.newInstance();
			Transformer localTransformer = localTransformerFactory.newTransformer();
			localTransformer.transform(localDOMSource, localStreamResult);
			return localStringWriter.toString();
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return null;
	}

	public static void applyProperties(Object paramObject, Element paramElement) {
		Map<?, ?> localMap = getProperties(paramElement);
		Iterator<?> localIterator = localMap.keySet().iterator();
		Field[] arrayOfField = paramObject.getClass().getFields();
		Method[] arrayOfMethod = paramObject.getClass().getMethods();
		while (localIterator.hasNext()) {
			String str = (String) localIterator.next();
			Object localObject = localMap.get(str);
			try {
				for (int i = 0; i < arrayOfField.length; ++i) {
					if ((!(arrayOfField[i].getName().equalsIgnoreCase(str)))
							|| (!(isTypeMatch(arrayOfField[i].getType(), localObject.getClass()))))
						continue;
					arrayOfField[i].set(paramObject, localObject);
					System.err.println("Set field " + arrayOfField[i].getName() + "=" + localObject);
					break;
				}
				for (int i = 0; i < arrayOfMethod.length; ++i) {
					if ((!(arrayOfMethod[i].getName().equalsIgnoreCase("set" + str)))
							|| (arrayOfMethod[i].getParameterTypes().length != 1)
							|| (!(isTypeMatch(arrayOfMethod[i].getParameterTypes()[0], localObject.getClass()))))
						continue;
					arrayOfMethod[i].invoke(paramObject, new Object[] { localObject });
					System.err.println("Set method " + arrayOfMethod[i].getName() + "=" + localObject);
					break;
				}
			} catch (Exception localException) {
				System.err.println("Unable to apply property '" + str + "': " + localException.toString());
			}
		}
	}

	private static boolean isTypeMatch(Class<?> paramClass, Class<? extends Object> paramClass1) {
		if (paramClass.equals(paramClass1))
			return true;
		if (paramClass.isPrimitive()) {
			if ((paramClass.getName().equals("int")) && (paramClass1.getName().equals("java.lang.Integer")))
				return true;
			if ((paramClass.getName().equals("long")) && (paramClass1.getName().equals("java.lang.Long")))
				return true;
			if ((paramClass.getName().equals("float")) && (paramClass1.getName().equals("java.lang.Float")))
				return true;
			if ((paramClass.getName().equals("double")) && (paramClass1.getName().equals("java.lang.Double")))
				return true;
			if ((paramClass.getName().equals("char")) && (paramClass1.getName().equals("java.lang.Character")))
				return true;
			if ((paramClass.getName().equals("byte")) && (paramClass1.getName().equals("java.lang.Byte")))
				return true;
			if ((paramClass.getName().equals("short")) && (paramClass1.getName().equals("java.lang.Short")))
				return true;
			if ((paramClass.getName().equals("boolean")) && (paramClass1.getName().equals("java.lang.Boolean")))
				return true;
		}
		return false;
	}

	public static final FileFilter getXmlFileExtFilter() {
		return xmlFileExtFilter;
	}
}
