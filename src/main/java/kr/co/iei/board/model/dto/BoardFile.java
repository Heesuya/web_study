package kr.co.iei.board.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardFile {
	private int fileNo;
	private int boardNo;
	private String filename;
	private String filepath;
}
