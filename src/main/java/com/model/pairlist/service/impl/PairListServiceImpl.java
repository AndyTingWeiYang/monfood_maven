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

//	//findFriends()方法test [to be deleted]
//	public static void main(String[] args) {
//		PairListService pair = new PairListServiceImpl();
//		List<UserVO> result = pair.findFriends();
//		System.out.println(result);
//	}

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

	// findPairInfo()方法test [to be deleted]
//	public static void main(String[] args) {
//		PairListService pair = new PairListServiceImpl();
//		java.util.Date date = new java.util.Date();
//		java.sql.Date today = new java.sql.Date(date.getTime());
//		List<UserVO> result = pair.findPairInfo(1,today);
//		System.out.println(result);
//	}

	// 更新會員答案及狀態
	public boolean updateAnswerAndStatus(PairListVo pairListVo) {
		// 雙向查找useraId及userbId欄位，並修改答案
		pairDao.updateUseraAnswer(pairListVo);
		pairDao.updateUserbAnswer(pairListVo);
		// 如果兩者結果皆為1，將狀態改為1
		pairDao.updateStatus(pairListVo);
		return true;
	}

	// updateAnswerAndStatus()方法test [to be deleted]
//	public static void main(String[] args) {
//		PairListService pair = new PairListServiceImpl();
//		PairListVo vo = new PairListVo();
//		vo.setUseraId(11); //userID(參數為同個使用者)
//		vo.setUserbId(11); //userID(參數為同個使用者)
//		vo.setUseraAnswer(1); //前端傳來參數(參數為同個使用者)
//		vo.setUserbAnswer(1); //前端傳來參數(參數為同個使用者)
//		java.util.Date date = new java.util.Date();
//		java.sql.Date today = new java.sql.Date(date.getTime());
//		vo.setPairedDate(today);
//		pair.updateAnswerAndStatus(vo);
//
//	}

	// 配對方法 已移動[to be deleted]
//	public void match() {
//
//		// 取得所有會員ID
//		List<Integer> listUserVO = userDao.getAllUserId();
//		ArrayList<Integer> user = new ArrayList<>();
//		for (Integer userVO1 : listUserVO) {
//			int userId = userVO1;
//			// 將結果放進user集合中，user集合為所有會員ID的集合
//			user.add(userId);
//		}
//
//		System.out.println("所有會員ID" + user);
//
//		// 如果為可配對的user人數為奇數>>> 加入一個測試人員 [to be revised]
////		if (user.size() % 2 != 0) {
////			UserVO uVo = new UserVO();
////			uVo.setUserName("吳宜玲");
////			uVo.setUserAccount("accounttest@gmail.com");
////			uVo.setUserPassword("Passwordtest123");
////			uVo.setUserTel("0933333333");
////			uVo.setBirthday(java.sql.Date.valueOf("2013-09-04"));
////			uVo.setUserAccountStatus(1);	
////			uVo.setMonsLevel(1);
////			java.util.Date date = new java.util.Date();
////			uVo.setUpdateTime(new Timestamp(date.getTime()));
////			userDao.insert(uVo);
////			List<UserVO> list = userDao.selectByUserName("吳宜玲");
////			for (UserVO userVO2 : list) {
////				Integer testId = ((UserVO) userVO2).getUserId();
////				System.out.println("測試人員ID = " + testId);
////				user.add(testId);
////			}
////		}
//		
//		// 如果為可配對的user人數為奇數 >> 把某筆假資料刪除[to be revised]
////		if (user.size() % 2 != 0) {
////			userDao.delete(6);
////		}
//
////		user[0]為準備要被配對的使用者ID 
//
//		while (user.size() > 1) {
//
//			// firstUser為準備要配對的使用者集合
//			ArrayList<Integer> firstUser = new ArrayList<>();
//			firstUser.add(user.get(0));
//			System.out.println("要被配對的A會員" + firstUser);
//
////		檢查pairList裡userid = user[0] (代表已經配對過) ，並個別放入集合內
////		pairedId及pairedId2集合
//			List<PairListVo> pairedIdVo = pairDao.selectById(user.get(0));
//			ArrayList<Integer> pairedId = new ArrayList<>();
//			for (PairListVo alist : pairedIdVo) {
//				int id = alist.getUserbId();
//				pairedId.add(id);
//			}
//
//			List<PairListVo> pairedId2Vo = pairDao.selectById2(user.get(0));
//			ArrayList<Integer> pairedId2 = new ArrayList<>();
//			for (PairListVo alist : pairedId2Vo) {
//				int id = alist.getUseraId();
//				pairedId2.add(id);
//			}
//
//			// 取得pairedId及pairedId2聯集
//			// pairedAll為所有已與user[0]配對過的會員名單
//			List pairedAll = pairedId.parallelStream().collect(toList());
//			List pairedAll2 = pairedId2.parallelStream().collect(toList());
//			pairedAll.addAll(pairedAll2);
//
//			System.out.println("已與A會員配對過的會員" + pairedAll);
//
//			// 取得user與pairedAll的差集
//			List<Integer> reduce = user.stream().filter(item -> !pairedAll.contains(item)).collect(Collectors.toList());
//
//			// 再扣掉user[0]自己，差集結果為可放進user_b_id欄位的配對者(availblePaired)
//			List<Integer> availblePaired = reduce.stream().filter(item -> !firstUser.contains(item))
//					.collect(Collectors.toList());
//			System.out.println("可與A會員配對的配對名單" + availblePaired);
//
//			// toPairb為被配對到的B會員集合
//			ArrayList<Integer> toPairb = new ArrayList<>();
//			toPairb.add(availblePaired.get(0));
//			System.out.println("被配對到的B會員" + toPairb);
//
//			// 幫user[0]與availblePaired[0]配對，並加入配對日期
//			PairListVo vo = new PairListVo();
//			vo.setUseraId(user.get(0));
//			vo.setUserbId(availblePaired.get(0));
//			java.util.Date today = new java.util.Date();
//			java.sql.Date pairedDate = new java.sql.Date(today.getTime());
//			vo.setPairedDate(pairedDate);
//			pairDao.insert(vo);
//
////        從user集合裡將配對好的刪除
//
//			// paired為配對好的userID聯集
//			List paired = firstUser.parallelStream().collect(toList());
//			List pairedb = toPairb.parallelStream().collect(toList());
//			paired.addAll(pairedb);
//
//			System.out.println("配對好的userID聯集" + paired);
//
//			// 將已將配對好的刪除
//			user = (ArrayList<Integer>) user.stream().filter(item -> !paired.contains(item))
//					.collect(Collectors.toList());
//			System.out.println("當日未被配對過的會員" + user);
//
//		}
//
//	}

	// 配對方法test
//	public static void main(String[] args) {
//		PairListService p = new PairListServiceImpl();
//		p.match();
//	}

}
