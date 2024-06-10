package com.example.myapp.board.dao;


import com.example.myapp.board.model.BoardCategory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface IBoardCategoryRepository {
	int selectMaxCategoryId();
	List<BoardCategory> selectAllCategory();
	void insertNewCategory(BoardCategory boardCategory);
	void updateCategory(BoardCategory boardCategory);
	void deleteCategory(int categoryId);
}