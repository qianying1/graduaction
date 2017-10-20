package com.grad.acfunc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/acfun/index",produces="application/json;charset=UTF-8")
public class IndexController {

	@ResponseBody
	@RequestMapping("index")
	public String index(){
		
		return "";
	}
}
