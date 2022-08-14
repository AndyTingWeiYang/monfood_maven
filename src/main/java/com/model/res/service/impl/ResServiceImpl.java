package com.model.res.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import com.model.order.OrderVO;
import com.model.order.dao.OrderDAO;
import com.model.order.dao.impl.OrderJDBCDAOimpl;
import com.model.product.util.ErrorMsgException;
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
	public Map<String, Object> updateResInfo(ResDto resDto) throws ErrorMsgException {
		ErrorMsgException eme = null;

		String ownerName = resDto.getOwnerName();
		if (proccessCheckMsg(ownerName)) {
			eme = getErrorMsgException(eme, "ownerName", "聯絡人姓名有特殊字元，請重新輸入");
		}

		String resPhone = resDto.getResPhone();
		if (proccessCheckMsg(resPhone)) {
			eme = getErrorMsgException(eme, "resPhone", "餐廳電話有特殊字元，請重新輸入");
		}

		String bzLocation = resDto.getBzAdd();
		if (proccessCheckMsg(bzLocation)) {
			eme = getErrorMsgException(eme, "resPhone", "餐廳地址有特殊字元，請重新輸入");
		}

		// 拋出錯誤例外
		if (eme != null) {
			throw eme;
		}

		return dao.updateResInfo(resDto);
	}

	@Override
	public ResVO selectByResId(Integer resId) {
		return dao.selectByResId(resId);
	}

	@Override
	public List<Map<String, Object>> resFindOrderService(OrderVO orderVO) {
		OrderDAO orderDAO = new OrderJDBCDAOimpl();

		return orderDAO.resFindOrderService(orderVO);
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

	@Override
	public List<Map<String, Object>> getRate() {
		List<Map<String, Object>> list = dao.getRate();
		return list;
	}

	@Override
	public List<Map<String, Object>> searchProduct(String searchPdt) {
		List<Map<String, Object>> list = dao.searchProduct(searchPdt);
		return list.stream().filter(distinctByKey(map -> MapUtils.getString(map, "resId")))
				.collect(Collectors.toList());
	}

	@Override
	public List<Map<String, Object>> getResComment(Integer resId) {
		List<Map<String, Object>> list = dao.getResComment(resId);
		return list;
	}

	@Override
	public List<Map<String, Object>> adminFindByCategory(Integer resCategory) {
		List<Map<String, Object>> list = dao.selectByCategory(resCategory);
		return list;
	}

	@Override

	public List<ResVO> getByZipcode(String zipcode) {
		List<ResVO> resList = new ArrayList<ResVO>();
		List<ResVO> resultList = new ArrayList<ResVO>();
		resList = dao.getResForZipcode();
		for (ResVO resVO : resList) {
			if (zipcode.equals(String.valueOf(resVO.getZipCode()))) {
				resultList.add(resVO);
			}
		}
		System.out.println("service:" + resultList);
		return resultList;
	}

	public Map<String, Object> getToResPage(Integer resId) {
		Map<String, Object> list = dao.getToResPage(resId);
		return list;
	}

	@Override
	public Map<String, Object> selectResInfo(Integer resID) {

		return dao.selectResInfo(resID);
	}

	public boolean proccessCheckMsg(String msg) {
		if (StringUtils.isNoneBlank(msg)) {
			String[] msgSp = msg.split("");
			String[] flag = new String[] { "!", "@", "#", "$", "%", "^", "&" };

			for (int i = 0; i < msgSp.length; i++) {
				for (int j = 0; j < flag.length; j++) {
					if (msgSp[i].equals(flag[j])) {
						System.out.println("有特殊字元，請重新輸入");
						return true;
					}
				}
			}
		}
		return false;
	}

	// 錯誤例外處理
	private ErrorMsgException getErrorMsgException(ErrorMsgException eme, String msgKey, String msgValue) {
		if (eme == null) {
			eme = new ErrorMsgException();
		}

		eme.appendMessage(msgKey, msgValue);
		return eme;
	}

	private <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
		Map<Object, Boolean> seen = new ConcurrentHashMap<>();

		return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}

}
