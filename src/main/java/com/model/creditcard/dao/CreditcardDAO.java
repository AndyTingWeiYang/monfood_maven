package com.model.creditcard.dao;

import java.sql.SQLException;
import java.util.List;

import com.model.creditcard.CreditcardVO;

public interface CreditcardDAO {

	//取得連線,拋出SQL例外
	//public void getConnection() throws SQLException;
	
	//進行新增(新增對象),拋出SQL例外
	public void insert(CreditcardVO creditcardVO) throws SQLException;
	
	//進行修改(修改對象),拋出SQL例外
	public void update(CreditcardVO creditcardVO) throws SQLException;
	
	//進行刪除(刪除對象)根據PK鍵,拋出SQL例外
	public void delete(Integer creditcardId) throws SQLException;
	
	//進行VO單一數據查找根據PK鍵,拋出SQL例外
	public CreditcardVO findByPrimaryKey(Integer creditcardId) throws SQLException;
	
	//進行VO全部查找,拋出SQL例外
	public List<CreditcardVO> getAll() throws SQLException;
	
	//關閉連線,拋出SQL例外
	//public void closeConn() throws SQLException;
	
}