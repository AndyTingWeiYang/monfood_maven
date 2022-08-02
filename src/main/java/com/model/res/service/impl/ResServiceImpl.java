package com.model.res.service.impl;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.model.order.OrderVO;
import com.model.order.dao.OrderDAO;
import com.model.order.dao.impl.OrderJDBCDAOimpl;
import com.model.res.ResDto;
import com.model.res.ResVO;
import com.model.res.dao.ResDAO;
import com.model.res.dao.impl.ResDAOImpl;
import com.model.res.service.ResService;

public class ResServiceImpl implements ResService {
	private ResDAO dao;

	public ResServiceImpl() {
		dao = new ResDAOImpl();
	}

	// 統一編號格式驗證
	String regex = "\\d{8}";
	// 密碼格式驗證
	String regex2 = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
	// 手機號碼格式驗證
	String regex3 = "\\d{10}";
	String regex4 = "^09[0-9]{2}\\d{6}$";

	@Override
	public String resRegister(ResVO resVO) {

		final String resName = resVO.getResName();

		if (resName == null || resName.equals("")) {
			return "請輸入完整姓名";
		}

		final String resAccount = resVO.getResAccount();
		if (resAccount == null || resAccount.equals("")) {
			return "請輸入統一編號";
		} else if (!(Pattern.matches(regex, resAccount))) {
			return "統一編號格式錯誤";
		}

		final String resPassword = resVO.getResPassword();
		if (resPassword == null || resPassword.equals("")) {
			return "請輸入密碼";
		} else if (!(Pattern.matches(regex2, resPassword))) {
			return "*密碼長度須至少八個字元。密碼複雜度須包含:小寫字母、大寫字母、數字";
		}

		final String ownerTel = resVO.getOwnerTel();
		if (ownerTel == null || ownerTel.equals("")) {
			return "請輸入手機號碼";
		} else if (!(Pattern.matches(regex3, ownerTel))) {
			return "手機號碼應為10碼數字";
		} else if (!(Pattern.matches(regex4, ownerTel))) {
			return "手機號碼開頭為09";
		}

		final int result = dao.insert(resVO);
		if (result < 1) {
			return "註冊失敗，請重新註冊";
		}

		System.out.println("im in resServiceImpl");
		return "Register Success";
	}

	@Override
	public ResVO resLogin(ResVO resVO) {
		final String loginResAccount = resVO.getResAccount();
		ResVO resAccount = dao.selectByResAccount(loginResAccount);

		return resAccount;
	}

	@Override
	public String resModify(ResVO resVO) {
		return null;
	}

	@Override
	public String resetPassword(ResVO resVO) {
		final String resAccount = resVO.getResAccount();
		if (resAccount == null || resAccount.equals("")) {
			return "請輸入統一編號";
		} else if (!(Pattern.matches(regex, resAccount))) {
			return "統一編號格式錯誤";
		}

		final String resPassword = resVO.getResPassword();
		if (resPassword == null || resPassword.equals("")) {
			return "請輸入密碼";
		} else if (!(Pattern.matches(regex2, resPassword))) {
			return "*密碼長度須至少八個字元。密碼複雜度須包含:小寫字母、大寫字母、數字";
		}

		String resetReult = dao.updatePassword(resVO);
		System.out.println("我在ResServiceImpl = " + resetReult);
		if (!("UpdateCompleted".equals(resetReult))) {
			return "ResetFailed";
		}
		return "ResetSuccessfully";
	}

	@Override
	public String isDuplicateAccount(ResVO resVO) {
		final String resAccount = resVO.getResAccount();
		final List<ResVO> listResVO = dao.isDuplicateAccount(resAccount);
//		System.out.println(listUserVO);

		int count = 0;
		for (ResVO resVO2 : listResVO) {
//			System.out.println("USER_ACCOUNT:"+userVO2.getUserAccount());
			count++;
		}
		System.out.println("isDuplicateAccount查詢帳號重複筆數" + count);
		if (count >= 1) {
			return "DuplicateAccount";
		}

		System.out.println("im in ResServiceImpl isDuplicateAccount ： Account Pass");
		return "pass";

	}

	@Override
	public List<ResVO> adminFindResAll() {
		List<ResVO> list = dao.getAll();
		return list;
	}

	@Override
	public List<ResVO> adminFindByCategory(Integer resCategory) {
		List<ResVO> list = dao.selectByCategory(resCategory);
		return list;
	}

	@Override
	public boolean updateResInfo(ResDto resDto) {
		return dao.updateResInfo(resDto);
	}

	@Override
	public ResVO selectByResId(Integer resId) {
		return dao.selectByResId(resId);
	}

	@Override
	public List<Map<String, Object>> resFindOrderService(Integer orderId) {
		OrderDAO orderDAO = new OrderJDBCDAOimpl();

		return orderDAO.resFindOrderService(orderId);
	}

	public List<ResVO> getByResName(String resName) {
		List<ResVO> listResVO = dao.selectByResName(resName);
		return listResVO;
	}

	@Override
	public ResVO getOneByResAccount(String resAccount) {
		ResVO resVO = dao.selectByResAccount(resAccount);
		return resVO;
	}

}
