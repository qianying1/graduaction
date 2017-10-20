package com.grad.support.acfunc;

import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class IndexAnalizer {
	
	public List<String> analizeHeaderForUrls(Element header){
		Element nav = header.getElementById("nav");
		Elements ahrefs = nav.select("a");
		List<String> urls = new ArrayList<String>();
		for (Element ahref : ahrefs) {
			String url = ahref.attr("abs:href");
			if (null != url && !"".equals(url)) {
				urls.add(url);
			}
		}
		return urls;
	}
	
	public 
}
