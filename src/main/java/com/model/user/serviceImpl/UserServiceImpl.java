package com.model.user.serviceImpl;

import java.sql.Date;
import java.util.regex.Pattern;

import com.model.user.UserVO;
import com.model.user.dao.impl.UserDAOImpl;
import com.model.user.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDAOImpl dao;
	
	public UserServiceImpl(){
		dao = new UserDAOImpl();
	}

	@Override
	public String userRegister(UserVO userVO) {
		final String userName = userVO.getUserName();
		// 電子郵件格式驗證
		String regex = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?";
		// 密碼格式驗證
		String regex2 = "/^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$/";
		// 手機號碼格式驗證
		String regex3 = "/[0-9]/";
		String regex4 = "/^09/";
		// 生日格式驗證
		String regex5 = "/^\\d{4}\\-\\d{2}\\-\\d{2}$/";
		
		if(userName == null || userName.equals("")) {
			return "請輸入完整姓名";	
		}
		
		final String userAccount = userVO.getUserAccount();
		if(userAccount == null || userAccount.equals("")) {
			return "請輸入電子郵件";	
		}else if(!(Pattern.matches(regex, userAccount))){
			return "格式錯誤";		
		}
		
		final String userPassword = userVO.getUserPassword();
		if(userPassword == null || userPassword.equals("")) {
			return "請輸入密碼";	
		}else if(!(Pattern.matches(regex2, userPassword))) {
			return "*密碼長度須至少八個字元。密碼複雜度須包含:小寫字母、大寫字母、數字";
		}
		
		final String userTel = userVO.getUserTel();
		if(userTel == null || userTel.equals("")) {
			return "請輸入手機號碼";
		}else if(!(Pattern.matches(regex3, userTel))) {
			return "請輸入數字";
		}else if(!(Pattern.matches(regex4, userTel))) {
			return "手機號碼開頭為09";
		}else if(!(userTel.length()==10)) {
			return "手機號碼應為10碼";
		}
		
		final Date birthday = userVO.getBirthday();
		if(birthday == null || birthday.equals("")) {
			return "請輸入出生日期";	
		}else if(!(Pattern.matches(regex5, (CharSequence) birthday))) {
			return "輸入格式為年-月-日 範例：1990-01-01";
		}
		
		final int result = dao.insert(userVO);
		if (result < 1) {
			return "註冊失敗，請重新註冊";
		}
		
		return null;
	}

	@Override
	public String userLogin(UserVO userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String userModify(UserVO userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String userForgetPass(UserVO userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
