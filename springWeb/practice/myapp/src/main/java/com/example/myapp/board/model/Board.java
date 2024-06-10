package com.example.myapp.board.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter @Setter @ToString 
public class Board {
	private int boardId;
	private int categoryId;
	private String writer;
	private String email;
	private String password;
	private String title;
	private String content;
	private Timestamp writeDate;
	private int masterId;
	private int readCount;
	private int replyNumber;
	private int replyStep;
	private int page;

	private MultipartFile file;
	private int fileId;
	private String fileName;
	private long fileSize;
	private String fileContentType;

}//end class