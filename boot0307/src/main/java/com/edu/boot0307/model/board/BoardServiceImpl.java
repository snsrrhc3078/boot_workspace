package com.edu.boot0307.model.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.boot0307.domain.Board;
import com.edu.boot0307.exception.BoardException;

@Service
public class BoardServiceImpl implements BoardService{

		@Autowired
		private BoardDAO boardDAO;

		public void insert(Board board) throws BoardException{
			boardDAO.insert(board);
		}
		
		
}
