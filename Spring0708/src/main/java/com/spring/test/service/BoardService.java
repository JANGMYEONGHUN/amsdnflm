package com.spring.test.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.test.dao.BoardDAO;
import com.spring.test.vo.BoardVO;
import com.spring.test.vo.FileVO;
import com.spring.test.vo.ReplyVO;

@Service
public class BoardService {
	@Autowired
	private BoardDAO dao;
	
	public ArrayList<BoardVO> selectAllBoard() {
		
		return dao.selectAllBoard();
	}

	public BoardVO readBoard(int boardNum) {
		
		return dao.readBoard(boardNum);
	}

	public boolean writeBoard(String boardTitle, String boardContent, String memberId) {
		BoardVO newBoard = new BoardVO();
		newBoard.setBoardTitle(boardTitle);
		newBoard.setBoardContent(boardContent);
		newBoard.setMemberId(memberId);
		if(dao.writeBoard(newBoard) > 0) return true;
		else	return false;
	}

	public boolean updateBoard(String boardTitle, String boardContent, int boardNum) {
		BoardVO board = new BoardVO();
		board.setBoardTitle(boardTitle);
		board.setBoardContent(boardContent);
		board.setBoardNum(boardNum);
		if(dao.updateBoard(board) > 0) return true;
		else	return false;
	}

	public boolean deleteBoard(int boardNum) {
		if(dao.deleteBoard(boardNum) > 0) return true;
		else return false;
	}

	public void updateCount(int boardNum) {
		dao.updateCount(boardNum);		
	}

	public boolean writeReply(String replyContent, int boardNum, String memberId) {
		ReplyVO newReply = new ReplyVO(boardNum,memberId,replyContent,null, 0);
		int result = dao.writeReply(newReply);
		if(result > 0) return true;
		return false;
	}

	public ArrayList<ReplyVO> readAllReply(int boardNum) {		
		return dao.readAllReply(boardNum);
	}

	public boolean updateReply(String replyContent, int replyNum) {
		ReplyVO reply = new ReplyVO();
		reply.setReplyContent(replyContent);
		reply.setReplyNum(replyNum);
		if(dao.updateReply(reply) > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean deleteReply(int replyNum) {
		if(dao.deleteReply(replyNum) > 0) {
			return true;
		}else {
			return false;
		}
	}

	public int selectBoardNum() {
		
		return dao.selectBoardNum();
	}

	public boolean uploadFile(int boardNum, String savedFileName, String originalFileName) {
		FileVO file = new FileVO();
		file.setBoardNum(boardNum);
		file.setSavedFileName(savedFileName);
		file.setOriginalFileName(originalFileName);
		if(dao.uploadFile(file) > 0) {
			return true;
		}else {
			return false;
		}
	}

	public FileVO getFileInfo(int boardNum) {
		
		return dao.getFileInfo(boardNum);
	}

	public boolean deleteFileInfo(int boardNum) {
		if(dao.deleteFileInfo(boardNum) > 0)return true;
		return false;
	}

	public ArrayList<BoardVO> searchBoard(String condition, String searchWord) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("condition", condition); //이름표을 붙여서 데이터를 저장.
		map.put("searchWord", searchWord);
		
		return dao.searchBoard(map);
	}

}
