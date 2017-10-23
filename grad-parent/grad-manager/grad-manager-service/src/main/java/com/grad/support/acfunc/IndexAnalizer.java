package com.grad.support.acfunc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.grad.BaseUtil;
import com.grad.util.Validation;

public class IndexAnalizer extends BaseUtil{

	/**
	 * 分析页面头部获取链接列表
	 * @param header
	 * @return
	 */
	public List<String> analizeHeaderForUrls(Element header) {
		if (Validation.isNull(header) || Validation.isEmpty(header.getElementsByTag("nav"))) {
			log.error("the header content is empty");
			return null;
		}
		Element nav = header.getElementById("nav");
		Elements ahrefs = nav.select("a");
		if(Validation.isEmpty(ahrefs)){
			log.error("the header don`t have super link");
			return null;
		}
		List<String> urls = new ArrayList<String>();
		for (Element ahref : ahrefs) {
			String url = ahref.attr("abs:href");
			if (null != url && !"".equals(url)) {
				urls.add(url);
			}
		}
		return urls;
	}

	/**
	 * 分析页面中带有轮播的section节点
	 * @param section
	 * @return
	 */
	public Map<String, Object> analizeCarouselSection(Element section){
		
		return null;
	}
	
	/**
	 * 分析页面中普通的section节点
	 * @param section
	 * @return
	 */
	public Map<String, Object> analizeSection(Element section) {

		return null;
	}
}
