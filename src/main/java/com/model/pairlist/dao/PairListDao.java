package com.model.pairlist.dao;

import java.util.List;

import com.model.pairlist.PairListVo;

public interface PairListDao {
	
	boolean insert(PairListVo pairListVo);
	boolean updateUseraAnswer(PairListVo pairListVo);
	boolean updateUserbAnswer(PairListVo pairListVo);
	boolean updateStatus(PairListVo pairListVo);
	List<PairListVo> selectByIdAndStatus(Integer useraId) ;
	List<PairListVo> selectById(Integer useraId);
	List<PairListVo> selectById2(Integer useraId);
	List<PairListVo> selectByIdAndPairedDate(Integer useraId, java.sql.Date pairedDate);

}
