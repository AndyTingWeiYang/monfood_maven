package com.model.pairlist.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;

import java.sql.Timestamp;

import com.model.pairlist.PairListVo;
import com.model.pairlist.dao.PairListDao;
import com.model.pairlist.dao.impl.PairListDaoImpl;
import com.model.pairlist.service.PairListService;
import com.model.user.UserVO;
import com.model.user.dao.UserDAO;
import com.model.user.dao.impl.UserDAOImpl;

public class PairListServiceImpl implements PairListService {

	private PairListDao pairDao;
	private UserDAO userDao;

	public PairListServiceImpl() {
		pairDao = new PairListDaoImpl();
		userDao = new UserDAOImpl();
	}

	// 取好友名單ID並取出資料
	public List<UserVO> findFriends(Integer useraId) {
		// 要拿到的list
		List<UserVO> listUserVO = new ArrayList<UserVO>();
		UserVO userVO = null;
		List<PairListVo> list = pairDao.selectByIdAndStatus(useraId);
		for (PairListVo alist : list) {
			// 取出a會員的所有好友id
			int a = alist.getUserbId();
			// 取出所有好友id的會員資料
			userVO = userDao.selectByUserId(a);
			// 資料放進list
			listUserVO.add(userVO);
		}

		return listUserVO;

	}



	// 取得當日配對者ID並取出資料
	public List<UserVO> findPairInfo(Integer useraId, java.sql.Date pairedDate) {
		// 要拿到的list
		List<UserVO> listUserVO = new ArrayList<UserVO>();
		UserVO userVO = null;
		List<PairListVo> list = pairDao.selectByIdAndPairedDate(useraId, pairedDate);
		for (PairListVo alist : list) {
			// 取出a會員的所有好友id
			int a = alist.getUserbId();
			// 取出配對者id的會員資料
			userVO = userDao.selectByUserId(a);
			// 資料放進list
			listUserVO.add(userVO);
		}

		return listUserVO;

	}



	// 更新會員答案及狀態
	public boolean updateAnswerAndStatus(PairListVo pairListVo) {
		// 雙向查找useraId及userbId欄位，並修改答案
		pairDao.updateUseraAnswer(pairListVo);
		pairDao.updateUserbAnswer(pairListVo);
		// 如果兩者結果皆為1，將狀態改為1
		pairDao.updateStatus(pairListVo);
		return true;
	}

	


}
