package kr.co.iei.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.co.iei.board.model.dto.BoardDTO;
import kr.co.iei.board.model.dto.BoardFile;
import kr.co.iei.board.model.service.BoardService;
import kr.co.iei.util.FileUtils;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	@Autowired
	private FileUtils fileUtils;
	@Value("${file.root}")
	private String root;
	
	@GetMapping(value = "/list")
	public String boardList(int reqPage, Model model) {
		List list = boardService.getList(reqPage);
		model.addAttribute("list", list);
		return "/board/list";
	}
	@GetMapping(value = "/writeFrm")
	public String writeFrm() {
		return "/board/writeFrm";
	}
	@ResponseBody
	@PostMapping(value = "/wirteImage", produces = "plain/text;charset=utf-8")
	public String writeImage(MultipartFile upfile) {
		String savepath = root +"/board/image/";
		String filepath = fileUtils.upload(savepath, upfile);
		return "/board/image/"+filepath;
	}
	@PostMapping(value = "/write")
	public String write(BoardDTO board, Model model,MultipartFile boardThumb) {
		String savepath = root +"/board/";
		String filepath = fileUtils.upload(savepath, boardThumb);
		board.setBoardImg(filepath);
	     int result = boardService.insertBoard(board);
	        if(result >0) {
	            model.addAttribute("title","작성 완료");
	            model.addAttribute("icon","success");
	            model.addAttribute("loc","/board/list?reqPage=1");
	            return "common/msg";
	        }
	        return "redirect:/board/writeFrm";
	}
	@GetMapping(value = "/view")
	public String view(int boardNo, Model model) {
		BoardDTO board = boardService.selectOneBoard(boardNo);
		model.addAttribute("board", board);
		return "/board/view";
	}
}
