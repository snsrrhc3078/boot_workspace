package com.edu.boot0307.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edu.boot0307.domain.Board;
import com.edu.boot0307.exception.BoardException;
import com.edu.boot0307.model.board.BoardService;

//스프링 부트는 기존의 Legacy 의 api를 그대로 가져오되, 단지
//설정에 대한 부담만 개발자에게 안준 것 뿐이다.. 따라서
//@Controller, @Service.. 사용가능
@Controller
public class BoardController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/board/list")
	public ModelAndView getList() {
		logger.info("list 하위 컨트롤러 작동함");
		
		List<Board> boardList = new ArrayList<>();
		//데이터 가공하기
		for(int i =0;i<109;i++) {
			Board board = new Board();
			board.setTitle(i+ "번째 제목");
			board.setWriter(i+"번째작성자");
			board.setContent(i+"번째 내용");
			board.setRegdate("2023-03-09");
			board.setHit(i);
			boardList.add(board);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/list2");
		mav.addObject("boardList", boardList);
		return mav;
	}
	
	@PostMapping("/board/regist")
	public ModelAndView regist(Board board) {
		
		logger.info("글쓰기 요청 받음 board : " + board);
		logger.info(board.getTitle());
		logger.info(board.getWriter());
		logger.info(board.getContent());
		
		//3단계
		boardService.insert(board);
		
		ModelAndView mav = new ModelAndView("redirect:/board/list");
		return mav;
	}
	
	@GetMapping("/board/registform")
	public ModelAndView getForm() {
		return new ModelAndView("/board/write");
	}
	
	@ExceptionHandler(BoardException.class)
	public ModelAndView handle(RuntimeException e) {
		
		
		return null;
	}
}
