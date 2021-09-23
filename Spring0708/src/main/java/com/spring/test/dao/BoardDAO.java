package com.spring.test.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.test.vo.BoardVO;
import com.spring.test.vo.FileVO;
import com.spring.test.vo.ReplyVO;

@Repository
public class BoardDAO {
	
	@Autowired
	private SqlSession session;
	
	public ArrayList<BoardVO> selectAllBoard() {
		ArrayList<BoardVO> result = null;
		BoardMapper mapper = null;
		try {
			mapper = session.getMapper(BoardMapper.class);
			result = mapper.selectAllBoard();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public BoardVO readBoard(int boardNum) {
		BoardVO result = null;
		BoardMapper mapper = null;
		try {
			mapper = session.getMapper(BoardMapper.class);
			result = mapper.readBoard(boardNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int writeBoard(BoardVO newBoard) {
		int result = 0;
		BoardMapper mapper = null;
		try {
			mapper = session.getMapper(BoardMapper.class);
			result = mapper.writeBoard(newBoard);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateBoard(BoardVO board) {
		int result = 0;
		BoardMapper mapper = null;
		try {
			mapper = session.getMapper(BoardMapper.class);
			result = mapper.updateBoard(board);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteBoard(int boardNum) {
		int result = 0;
		BoardMapper mapper = null;
		try {
			mapper = session.getMapper(BoardMapper.class);
			result = mapper.deleteBoard(boardNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void updateCount(int boardNum) {		
		BoardMapper mapper = null;
		try {
			mapper = session.getMapper(BoardMapper.class);
			mapper.updateCount(boardNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int writeReply(ReplyVO newReply) {
		int result = 0;
		BoardMapper mapper = null;
		try {
			mapper = session.getMapper(BoardMapper.class);
			result = mapper.writeReply(newReply);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<ReplyVO> readAllReply(int boardNum) {
		ArrayList<ReplyVO> result = null;
		BoardMapper mapper = null;
		try {
			mapper = session.getMapper(BoardMapper.class);
			result = mapper.readAllReply(boardNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateReply(ReplyVO reply) {
		int result = 0;
		BoardMapper mapper = null;
		try {
			mapper = session.getMapper(BoardMapper.class);
			result = mapper.updateReply(reply);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteReply(int replyNum) {
		int result = 0;
		BoardMapper mapper = null;
		try {
			mapper = session.getMapper(BoardMapper.class);
			result = mapper.deleteReply(replyNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int selectBoardNum() {
		int result = 0;
		BoardMapper mapper = null;
		try {
			mapper = session.getMapper(BoardMapper.class);
			result = mapper.selectBoardNum();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int uploadFile(FileVO file) {
		int result = 0;
		BoardMapper mapper = null;
		try {
			mapper = session.getMapper(BoardMapper.class);
			result = mapper.uploadFile(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public FileVO getFileInfo(int boardNum) {
		FileVO result = null;
		BoardMapper mapper = null;
		try {
			mapper = session.getMapper(BoardMapper.class);
			result = mapper.getFileInfo(boardNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteFileInfo(int boardNum) {
		int result = 0;
		BoardMapper mapper = null;
		try {
			mapper = session.getMapper(BoardMapper.class);
			result = mapper.deleteFileInfo(boardNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<BoardVO> searchBoard(HashMap<String, String> map) {
		ArrayList<BoardVO> result = new ArrayList<BoardVO>();
		BoardMapper mapper = null;
		try {
			mapper = session.getMapper(BoardMapper.class);
			result = mapper.searchBoard(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
