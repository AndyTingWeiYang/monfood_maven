package com.model.promotedetail.serviceImpl;

import java.util.List;

import com.model.promotedetail.PromoteDetailVO;
import com.model.promotedetail.dao.PromoteDetailDAO;
import com.model.promotedetail.dao.Impl.PromoteDetailJDBCDAO;

public class PromoteDetailServiceImpl {

	private PromoteDetailDAO dao;

	public PromoteDetailServiceImpl() {
		dao = new PromoteDetailJDBCDAO();
	}

	public PromoteDetailVO updatePromoteDetail(
			Integer promoteId, Integer userId, Integer usedStatus) {

		PromoteDetailVO promoteDetailVO = new PromoteDetailVO();

		promoteDetailVO.setPromoteId(promoteId);
		promoteDetailVO.setUserId(userId);
		promoteDetailVO.setUsedStatus(usedStatus);

		dao.update(promoteDetailVO);

		return promoteDetailVO;
	}

	public PromoteDetailVO getOnePromoteDetail(Integer promoteId) {
		return dao.findByPrimaryKey(promoteId);
	}

	public List<PromoteDetailVO> getAll() {
		return dao.getAll();
	}
	
}
