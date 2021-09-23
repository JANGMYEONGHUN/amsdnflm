package com.spring.test.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.spring.test.vo.BoardVO;
import com.spring.test.vo.FileVO;
import com.spring.test.vo.ReplyVO;

public interface BoardMapper {

	ArrayList<BoardVO> selectAllBoard();

	BoardVO readBoard(int boardNum);

	int writeBoard(BoardVO newBoard);

	int updateBoard(BoardVO board);

	int deleteBoard(int boardNum);

	void updateCount(int boardNum);

	int writeReply(ReplyVO newReply);

	ArrayList<ReplyVO> readAllReply(int boardNum);

	int updateReply(ReplyVO reply);

	int deleteReply(int replyNum);

	int selectBoardNum();
	
	int uploadFile(FileVO file);
	
	FileVO getFileInfo(int boardNum);

	int deleteFileInfo(int boardNum);

	ArrayList<BoardVO> searchBoard(HashMap<String, String> map);

	

}
