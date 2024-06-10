package com.example.myapp.board.service;


import com.example.myapp.board.model.BoardCategory;

import java.util.List;

public interface IBoardCategoryService {
	List<BoardCategory> selectAllCategory();
	void insertNewCategory(BoardCategory boardCategory);
	void updateCategory(BoardCategory boardCategory);
	void deleteCategory(int categoryId);
}