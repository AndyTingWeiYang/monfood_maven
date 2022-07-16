package com.del.model;

import java.util.List;

public interface DelDAO {
	// 對Del表格的相關操作方法
	void add(DelVO delVO);

	void update(DelVO delVO);

	// 刪除會搭配條件進行 所以這邊用id作為條件進行刪除
	void delete(Integer delID);

	// 查詢單筆資料所以用pk ID作為條件搜尋會傳回一筆資料
	DelVO findByDelID(Integer delID);

	// select all 不用條件所以沒有參數 但是會回傳很多物件所以用有順序的list搭配delvo泛型做承接
	List<DelVO> getAll();
}
