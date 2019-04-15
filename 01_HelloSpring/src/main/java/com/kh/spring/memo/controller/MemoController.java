package com.kh.spring.memo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.memo.model.service.MemoService;

@Controller
public class MemoController {
	
	private Logger logger=LoggerFactory.getLogger(MemoController.class);

	@Autowired
	MemoService service;
	
	@RequestMapping("/memo/memo.do")
	public ModelAndView memo()
	{
		logger.debug("메모를 찾아줘~");
		List<Map<String,String>> list=service.selectMemo();
		logger.debug("content : "+list);
		ModelAndView mv=new ModelAndView();
		mv.addObject("memoList",list);
		mv.setViewName("memo/memo");
		return mv;
	}
	
	
	@RequestMapping("/memo/insertMemo.do")
	public String insertMemo(String memo, String password, Model model)
	{
		Map<String,String> map=new HashMap();
		map.put("memo", memo);
		map.put("password",password);
		int result=service.insertMemo(map);
		String msg="";
		String loc="/memo/memo.do";
		if(result>0)
		{
			msg="메모등록성공";
		}
		else {msg="메모등록실패";}
		model.addAttribute("msg", msg);
		model.addAttribute("loc", loc);
		return "common/msg";		
		
	}
	
	
	
	
}
