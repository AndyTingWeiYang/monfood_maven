package com.model.promotelist.service.Impl;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.model.promotelist.PromoteListVO;
import com.model.promotelist.dao.PromoteListDAO;
import com.model.promotelist.dao.Impl.PromoteListJDBCDAO;
import com.model.promotelist.service.PromoteListService;

public class PromoteListServiceImpl implements PromoteListService {
	private PromoteListDAO dao;

	public PromoteListServiceImpl() {
		dao = new PromoteListJDBCDAO();
	}

	@Override
	public String adminAddPromoteList(PromoteListVO promoteListVO) {

		final String promoteCode = promoteListVO.getPromoteCode();
		if (promoteCode == null || promoteCode.trim().length() == 0) {
			return "優惠代碼請勿空白";
		}

		final Integer promotePrice = promoteListVO.getPromotePrice();
		if (promotePrice == null) {
			return "折扣金額請勿空白";
		}

		final Date starDate = promoteListVO.getStartDate();
		if (starDate == null) {
			return "請輸入開始日期";
		}

		final Date endDate = promoteListVO.getEndDate();
		if (endDate == null) {
			return "請輸入結束日期";
		}

		final Integer status = promoteListVO.getStatus();
		if (status == null) {
			return "請輸入優惠券狀態(數字)";
		}

		final Integer result = dao.insert(promoteListVO);
		if (result < 1) {
			return "系統錯誤";

		}

		return "新增成功";
	}

	@Override
	public List<PromoteListVO> adminFindPromoteListAll() {
		List<PromoteListVO> list = dao.getAll();
		return list;
	}

	@Override
	public PromoteListVO adminFindpromoteListOne(PromoteListVO promoteListVO) {
		Integer promoteId = promoteListVO.getPromoteId();
		PromoteListVO one = dao.findByPrimaryKey(promoteId);
		return one;
	}


	@Override
	public Integer adminUpdatePromoteList(PromoteListVO promoteListVO) {
		Integer status = dao.update(promoteListVO);
		return status;

	}

	@Override
	public PromoteListVO adminFindListByCode(PromoteListVO promoteListVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> showPromote() {
		Map<String, Object> map = dao.showPromote();
		return map;
	}
}
