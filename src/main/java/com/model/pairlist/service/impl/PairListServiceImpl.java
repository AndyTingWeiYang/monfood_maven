package com.model.pairlist.service.impl;

import java.util.ArrayList;
import java.util.List;


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

	// 配對方法
	public void match() {
		// 取得所有會員ID
		List<UserVO> listUserVO = userDao.getAllUserId();
		ArrayList<Integer> user = new ArrayList<>();
		for (UserVO userVO1 : listUserVO) {
			int userId = userVO1.getUserId();
			// 將結果放進user集合中
			user.add(userId);
		}
		//將user集合轉為int陣列->user[]
//		int user[] = arrayList.stream().mapToInt(i -> i).toArray();

		System.out.println(user);

//		user[0]為準備要被配對的使用者ID 
		while (user.size() > 1) {
//		檢查pairList裡userid = user[0] (代表已經配對過)
		List<PairListVo> pairedId = pairDao.selectById(user.get(0)); //sql語法錯 為啥QQ
		System.out.println(pairedId);
			
		}

	     
	}

	// 配對方法test
	public static void main(String[] args) {
		PairListService p = new PairListServiceImpl();
		p.match();
	}

}
