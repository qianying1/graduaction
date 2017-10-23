package com.grad.util;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Validation {

	public static boolean isNull(Element header) {
		if (header == null || header.getElementsByTag("nav") == null || header.getElementsByTag("nav").size() <= 0) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(Elements elementsByTag) {
		if (elementsByTag.size() <= 0) {
			return true;
		}
		return false;
	}

	public static boolean isStringEmpty(String str) {
		if (str.trim().equals("")) {
			return true;
		}
		return false;
	}

	public static boolean isStringNull(String str) {
		if (str == null) {
			return true;
		}
		return false;
	}

}
