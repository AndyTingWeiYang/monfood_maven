package com.model.pairlist.dao;

import java.util.List;

import com.model.pairlist.PairListVo;

public interface PairListDao {
	
	boolean insert(PairListVo pairListVo);
	boolean updateUserAAnswer(PairListVo pairListVo);
	boolean updateUserBAnswer(PairListVo pairListVo);
	boolean updateStatus(PairListVo pairListVo);
	List<PairListVo> selectByIdAndStatus(Integer userAId) ;
	List<PairListVo> selectById(Integer userAId);

}
