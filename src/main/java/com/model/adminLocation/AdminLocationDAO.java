package com.model.adminLocation;

import java.util.List;

import com.model.del.DelVO;

public interface AdminLocationDAO {
	
	void add(AdminLocationVO adminLocationVO);

	void update(AdminLocationVO adminLocationVO);

	// 刪除會搭配條件進行 所以這邊用id作為條件進行刪除
	void delete(Integer zipCode);

	// 查詢單筆資料所以用pk ID作為條件搜尋會傳回一筆資料
	AdminLocationVO findByzipCode(Integer zipCode);

	// select all 不用條件所以沒有參數 但是會回傳很多物件所以用有順序的list搭配delvo泛型做承接
	List<AdminLocationVO> getAll();

}
