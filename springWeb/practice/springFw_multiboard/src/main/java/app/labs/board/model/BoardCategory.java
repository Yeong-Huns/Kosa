package app.labs.board.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter @Setter @ToString 
public class BoardCategory {
	private int categoryId;				// 카테고리 아이디
	private String categoryName;		// 카테고리 이름
	private String categoryDescription;	// 카테고리 설명
}