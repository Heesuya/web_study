package kr.co.iei.board.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.iei.board.model.dto.BoardDTO;
import kr.co.iei.board.model.dto.BoardFile;

@Mapper
public interface BoardDao {

	List<BoardDTO> selectBoardList(int start, int end);

	int selectBoardTotalCount();

	int insertBoard(BoardDTO board);

	BoardDTO selectOndeBoard(int boardNo);

	int upBoardCount(int boardNo);

	int deleteBaord(int boardNo);

	int boardUpdate(BoardDTO board);

}
