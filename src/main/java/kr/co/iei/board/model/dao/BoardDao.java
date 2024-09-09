package kr.co.iei.board.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.iei.board.model.dto.BoardDTO;

@Mapper
public interface BoardDao {

	List<BoardDTO> selectBoardList(int start, int end);

	int selectBoardTotalCount();

}
