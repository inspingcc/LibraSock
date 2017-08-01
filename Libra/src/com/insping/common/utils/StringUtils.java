package com.insping.common.utils;

import java.util.LinkedList;
import java.util.StringTokenizer;

public class StringUtils {
	public static final String[] toLowerCaseWordArray(String paramString) {
		if ((paramString == null) || (paramString.length() == 0))
			return new String[0];
		StringTokenizer localStringTokenizer = new StringTokenizer(paramString, " ,\r\n.:/\\+");
		String[] arrayOfString = new String[localStringTokenizer.countTokens()];
		for (int i = 0; i < arrayOfString.length; ++i)
			arrayOfString[i] = localStringTokenizer.nextToken().toLowerCase();
		return arrayOfString;
	}

	public static final String[] toStringArray(String paramString) {
		if ((paramString == null) || (paramString.length() == 0))
			return new String[0];
		StringTokenizer localStringTokenizer = new StringTokenizer(paramString, ",\r\n/\\");
		String[] arrayOfString = new String[localStringTokenizer.countTokens()];
		for (int i = 0; i < arrayOfString.length; ++i)
			arrayOfString[i] = localStringTokenizer.nextToken();
		return arrayOfString;
	}

	public static final String[] split(String paramString1, String paramString2) {
		StringTokenizer localStringTokenizer = new StringTokenizer(paramString1, paramString2);
		String[] arrayOfString = new String[localStringTokenizer.countTokens()];
		for (int i = 0; i < arrayOfString.length; ++i)
			arrayOfString[i] = localStringTokenizer.nextToken();
		return arrayOfString;
	}

	public static final String[] toStringArray(String paramString1, String paramString2) {
		if ((paramString1 == null) || (paramString1.length() == 0))
			return new String[0];
		StringTokenizer localStringTokenizer = new StringTokenizer(paramString1, paramString2);
		String[] arrayOfString = new String[localStringTokenizer.countTokens()];
		for (int i = 0; i < arrayOfString.length; ++i)
			arrayOfString[i] = localStringTokenizer.nextToken();
		return arrayOfString;
	}

	public static String[] splitOnWhitespace(String paramString) {
		int i = -1;
		LinkedList<String> localLinkedList = new LinkedList<String>();
		int j = paramString.length();
		for (int k = 0; k < j; ++k) {
			char c = paramString.charAt(k);
			if (!(Character.isWhitespace(c)))
				continue;
			if (k - i > 1)
				localLinkedList.add(paramString.substring(i + 1, k));
			i = k;
		}
		return ((String[]) localLinkedList.toArray(new String[localLinkedList.size()]));
	}

	public static final String replaceAll(String paramString1, String paramString2, String paramString3) {
		if ((paramString1 != null) && (paramString2 != null) && (paramString3 != null) && (!(paramString1.equals("")))
				&& (!(paramString2.equals("")))) {
			StringBuilder localStringBuilder = new StringBuilder();
			int i = 0;
			int j = paramString1.length();
			int k = 0;
			int l = paramString2.length();
			while ((i < j) && ((k = paramString1.indexOf(paramString2, i)) >= i)) {
				localStringBuilder.append(paramString1.substring(i, k)).append(paramString3);
				i = k + l;
			}
			if (i == 0)
				return paramString1;
			if (i < j)
				localStringBuilder.append(paramString1.substring(i));
			return localStringBuilder.toString();
		}
		return paramString1;
	}

	public static final String subString(String paramString, int paramInt) {
		if (paramString == null)
			return "";
		if (paramString.length() <= paramInt)
			return paramString;
		return new StringBuilder(paramString.substring(0, paramInt)).append("...").toString();
	}

	public static final String join(String paramString1, String paramString2, String[] paramArrayOfString) {
		StringBuilder localStringBuilder = new StringBuilder(paramString2);
		for (String str : paramArrayOfString)
			localStringBuilder.append(paramString1).append(str);
		return localStringBuilder.toString();
	}

	public static boolean containsForbidChars(String paramString) {
		String str = "/#@\\+|";
		for (int i = 0; i < str.length(); ++i)
			if (paramString.indexOf(str.charAt(i)) >= 0)
				return true;
		return false;
	}

	public static boolean isNull(String paramString1, String paramString2) {
		return ((paramString1 == null) || (paramString1.trim().equals("")));
	}
	
	public static boolean isNull(String str){
		if (str == null || str.equals("") || str.equals("null")) {
			return true;
		}
		return false;
	}
}