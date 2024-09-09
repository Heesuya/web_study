package kr.co.iei.util;

import java.util.List;

import kr.co.iei.board.model.dto.BoardDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardList {
	private List<BoardDTO> boardList;
	private String pageNavi;
}
