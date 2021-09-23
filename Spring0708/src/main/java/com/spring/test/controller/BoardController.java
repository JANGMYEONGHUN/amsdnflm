package com.spring.test.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.spring.test.service.BoardService;
import com.spring.test.util.FileService;
import com.spring.test.vo.BoardVO;
import com.spring.test.vo.FileVO;
import com.spring.test.vo.ReplyVO;

@Controller
@RequestMapping(value="/board")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	BoardService service;
	
	@RequestMapping(value = "/boardList", method = RequestMethod.GET)
	public String boardList(Model model) {
		logger.info("boardList 메소드 실행(GET)");
		
		//ArrayList<BoardVO> boardList = service.selectAllBoard();
		ArrayList<BoardVO> boardList = service.searchBoard(null,null);
		logger.info("boardList: {}", boardList);
		model.addAttribute("boardList", boardList);
		
		return "board/boardList";
	}
	
	@RequestMapping(value = "/readBoard", method = RequestMethod.GET)
	public String readBoard(int boardNum, Model model) {		
		logger.info("readBoard 메소드 실행(GET)");
		
		logger.info("boardNum: {}",boardNum);
		service.updateCount(boardNum); //조회수 1 증가
		
		
		BoardVO board = service.readBoard(boardNum); //특정 게시글 상세 정보를 가져옴(제목,내용,날짜,etc) 
		logger.info("board: {}",board);
		model.addAttribute("board",board);
		
		ArrayList<ReplyVO> replyList = service.readAllReply(boardNum); //특정 게시글에 대한 모든 댓글 정보를 가져옴
		logger.info("replyList: {}",replyList);
		model.addAttribute("replyList",replyList);
		
		FileVO file = service.getFileInfo(boardNum);
		logger.info("file: {}",file);
		if(file != null) {
			model.addAttribute("file",file); //jsp로 정보를 넘겨줌
		}
		return"board/readBoard";
	}
	
	@RequestMapping(value ="/download", method=RequestMethod.GET)
	public String download(int boardNum, HttpServletResponse response) {
		logger.info("download 메소드 실행(GET)");
		
		//파일 저장 정보 불러오기
		FileVO file = service.getFileInfo(boardNum);
		String savedFileName = file.getSavedFileName();
		logger.info("savedFileName: {}",savedFileName);
		
		String filePath = "C:/Upload Files/"+savedFileName;
		
		//파일 다운로드 기능을 실행하기 위한 세팅
		try {
			response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(file.getOriginalFileName(),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		FileInputStream fis = null;
		ServletOutputStream sos = null;		
		try {
			fis = new FileInputStream(filePath);
			sos = response.getOutputStream();
			
			FileCopyUtils.copy(fis, sos);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(fis != null) fis.close();
				if(sos != null) sos.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	@RequestMapping(value="/writeBoard", method = RequestMethod.GET)
	public String writeBoard() {
		logger.info("writeBoard 메소드 실행(GET)");
		
		return"board/writeBoard";
	}
	/*
	 * @RequestMapping(value="/writeBoard", method = RequestMethod.POST) public
	 * String writeBoard(String boardTitle,String boardContent,HttpSession session)
	 * { logger.info("writeBoard 메소드 실행(POST)");
	 * 
	 * logger.info("boardTitle: {}",boardTitle);
	 * logger.info("boardContent: {}",boardContent); String memberId =
	 * (String)session.getAttribute("memberId");
	 * logger.info("memberId: {}",memberId);
	 * 
	 * return"redirect:/board/boardList"; }
	 */
	
	@RequestMapping(value="/writeBoard", method = RequestMethod.POST)
	public String writeBoard(String boardTitle,String boardContent,String memberId, MultipartFile uploadFile) {
		logger.info("writeBoard 메소드 실행(POST)");
		
		logger.info("boardTitle: {}",boardTitle);
		logger.info("boardContent: {}",boardContent);
		logger.info("memberId: {}",memberId);
		logger.info("uploadFile: {}",uploadFile);
		logger.info("originalFileName: {}",uploadFile.getOriginalFilename());
		
		boolean result = service.writeBoard(boardTitle,boardContent,memberId);
		String returnUrl = null;
		if(result) {
			logger.info("게시글 작성 성공");
			returnUrl = "redirect:/board/boardList";
		}else {
			logger.info("게시글 작성 실패");
			returnUrl = "board/writeBoard";
		}
		//파일 저장 시작
		String savedFileName = FileService.saveFile(uploadFile, "c:\\Upload Files");
		logger.info("저장된 파일명: {}", savedFileName);		
		
		 if(savedFileName != null) {
			 int boardNum = service.selectBoardNum();
			 logger.info("boardNum: {}",boardNum);
			 boolean result2 = service.uploadFile(boardNum, savedFileName, uploadFile.getOriginalFilename());
			 if(result2) {
				 logger.info("파일 정보 저장 성공.");
			 }else {
				 logger.info("파일 정보 저장 실패");
			 }
		}		
		return returnUrl;
	}
	
	@RequestMapping(value="/updateBoard", method = RequestMethod.GET)
	public String updateBoard(int boardNum, Model model, HttpSession session) {
		logger.info("updateBoard 메소드 실행(GET)");
		
		BoardVO board = service.readBoard(boardNum);
		
		String loginId = (String)session.getAttribute("memberId"); //HttpSession이 매개변수이고 getAttribute속성을 사용할때 Object로 return돼서 앞에 (String)으로 강제변환시켜서 형을 맞춰야한다.
		String writerId = board.getMemberId();
		
		String returnUrl = null;
		if(loginId.equals(writerId)) {
			returnUrl = "board/updateBoard";
		} else {
			returnUrl = "board/readBoard";
		}		
		model.addAttribute("board",board);
		
		return returnUrl;
	}
	
	@RequestMapping(value="/updateBoard", method = RequestMethod.POST)
	public String updateBoard(String boardTitle,String boardContent,int boardNum) {
		logger.info("updateBoard 메소드 실행(POST)");
		
		logger.info("boardTitle: {}",boardTitle);
		logger.info("boardContent: {}",boardContent);
		logger.info("memberId: {}", boardNum);
		
		boolean result = service.updateBoard(boardTitle,boardContent,boardNum);
		if(result) {
			logger.info("게시글 수정 성공");			
		}else {
			logger.info("게시글 수정 실패");
		}
		return "redirect:/board/boardList";
	}
	
	@RequestMapping(value="/deleteBoard", method = RequestMethod.POST)
	public String deleteBoard(int boardNum) {
		logger.info("deleteBoard 메소드 실행(POST)");
		logger.info("boardNum: {}",boardNum);		
		
		//파일 삭제
		FileVO file = service.getFileInfo(boardNum);
		logger.info("file info: {}",file);
		if(file != null) {
			String filePath = "C:/Upload Files/"+file.getSavedFileName();
			FileService.deleteFile(filePath);	
		}
		
		
		//파일 저장 정보 삭제 
		boolean result2 = service.deleteFileInfo(boardNum);
		if(result2) {
			logger.info("파일 정보 삭제 성공.");
		}else {
			logger.info("파일 정본 삭제 실패.");
		}
		
		//게시글 삭제
		boolean result = service.deleteBoard(boardNum);
		if(result) {
			logger.info("게시물 삭제 성공.");
		}else {
			logger.info("게시물 삭제 실패.");
		}		
		return "redirect:/board/boardList";
	}
	@RequestMapping(value="/writeReply", method = RequestMethod.POST)
	public String writeReply(String replyContent, int boardNum,HttpSession session) {
		logger.info("writeReply 메소드 실행(POST)");
		
		logger.info("replyContent: {}",replyContent);
		logger.info("boardNum: {}",boardNum);
		String memberId = (String) session.getAttribute("memberId");
		
		boolean result = service.writeReply(replyContent,boardNum,memberId);
		if(result) {
			logger.info("댓글 작성 성공.");
		}else {
			logger.info("댓글 작성 실패.");
		}
		return "redirect:/board/readBoard?boardNum="+ boardNum; //댓글을 작성하고 나서 댓글을 적은 글에 다시 남는다.
	}
	@RequestMapping(value="/updateReply", method = RequestMethod.POST)
	public String updateReply(String replyContent, int replyNum, int boardNum) {
		logger.info("updateReply 메소드 실행(POST)");
		
		logger.info("replyContent: {}",replyContent);
		logger.info("replyNum: {}",replyNum);
		logger.info("boardNum: {}",boardNum);
		
		boolean result = service.updateReply(replyContent,replyNum);
		if(result) {
			logger.info("댓글 수정 성공");
		}else {
			logger.info("댓글 수정 실패");
		}
		return "redirect:/board/readBoard?boardNum="+boardNum;
	}
	@RequestMapping(value="/deleteReply", method = RequestMethod.GET)
	public String deleteReply(int replyNum, int boardNum) {
			logger.info("deleteReply 메소드 실행(GET)");
			
			logger.info("replyNum: {}", replyNum);
			logger.info("boardNum: {}", boardNum);
			
			boolean result = service.deleteReply(replyNum);
			if(result) {
				logger.info("댓글 삭제 성공");
			}else {
				logger.info("댓글 삭제 실패");
			}
			
			return"redirect:/board/readBoard?boardNum=" + boardNum;
	}
	
	@RequestMapping(value="searchBoard", method=RequestMethod.GET)
	public String searchBoard(String condition, String searchWord, Model model) {
		logger.info("searchBoard 메소드 실행(GET)");
		
		logger.info("condition: {}",condition);
		logger.info("searchWord: {}",searchWord);
		
		ArrayList<BoardVO> boardList = service.searchBoard(condition,searchWord);
		logger.info("검색 결과: {}",boardList.size());
		model.addAttribute("boardList", boardList);
		
		return "board/boardList";
	}
}
