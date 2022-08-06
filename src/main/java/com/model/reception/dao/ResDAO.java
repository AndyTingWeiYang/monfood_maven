package com.model.reception.dao;

import java.util.Map;

import com.model.reception.ResVO;

public interface ResDAO {

		//取得連線
		//public void getConnection() throws SQLException;
		
		//進行新增(新增對象)
//		public void insert(ResVO resVO); 
		
		//進行修改(修改對象)
	    public void update(ResVO resVO); 
	    
	    //進行刪除(刪除對象)根據PK鍵
//	    public void delete(Integer resId) ;
	    
	    //進行VO單一數據查找根據PK鍵
	    public ResVO findByPrimaryKey(Integer resId) ;

	    
	    //進行VO全部查找
//	    public List<ResVO> getAll() ;
	    
	    //關閉連線,拋出SQL例外
	    //public void closeConn() throws SQLException;
	    
}
