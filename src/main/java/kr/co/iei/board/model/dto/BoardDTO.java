package kr.co.iei.board.model.dto;

import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Alias(value = "board")
public class BoardDTO {
	private int boardNo;
	private int memberNo;
	private String boardTitle;
	private String boardContent;
	private int readCount;
	private String regDate;
	private String boardImg;
	private List boardList;
}
