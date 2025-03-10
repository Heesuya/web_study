package kr.co.iei.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.co.iei.board.model.dao.BoardDao;
import kr.co.iei.board.model.dto.BoardDTO;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;

	public List getList(int reqPage) {
		//페이지 넘버링(reqPage)에 따라 가져오는 테이블 게시물 내용이 달라진다.
				int numPerPage = 8;//한페이지에서 받을 게시물 수 
				int end = reqPage * numPerPage; 
				int start = end - numPerPage +1 ;
				
				List<BoardDTO> boardList = boardDao.selectBoardList(start, end);//한페이지 내에서 받을 게시물 Dao 에서 db작업
				
				//페이지 네비게이션 작업
				//게시물 총 개수 
				//int totalCount = boardDao.selectBoardTotalCount();
					
				return boardList;
	}

	@Transactional
	public int insertBoard(BoardDTO board) {	
		int result = boardDao.insertBoard(board);
		return result;
	}

	public BoardDTO selectOneBoard(int boardNo) {
		int result = boardDao.upBoardCount(boardNo);
		BoardDTO board = boardDao.selectOndeBoard(boardNo);
		return board;
	}
	@Transactional
	public int deleteBoard(int boardNo) {
		int result = boardDao.deleteBaord(boardNo);
		return result;
	}

	public BoardDTO update(int boardNo) {
		BoardDTO board = boardDao.selectOndeBoard(boardNo);
		return board;
	}

	@Transactional
	public int boardUpdate(BoardDTO board) {
		int result = boardDao.boardUpdate(board);
		return result;
	}

}
