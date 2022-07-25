package com.model.user.serviceImpl;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Pattern;

import com.model.user.UserVO;
import com.model.user.dao.UserDAO;
import com.model.user.dao.impl.UserDAOImpl;
import com.model.user.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDAO dao;  // 介面型態 跟spring銜接
	
	public UserServiceImpl(){
		dao = new UserDAOImpl();
	}

	@Override
	public String userRegister(UserVO userVO) {
		final String userName = userVO.getUserName();
		// 電子郵件格式驗證
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";		                         
		// 密碼格式驗證
		String regex2 = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
		// 手機號碼格式驗證
		String regex3 = "\\d{10}";
		String regex4 = "^09[0-9]{2}\\d{6}$";
		// 生日格式驗證
		String regex5 = "^\\d{4}\\-\\d{2}\\-\\d{2}$";
		
		if(userName == null || userName.equals("")) {
			return "請輸入完整姓名";	
		}
		
		final String userAccount = userVO.getUserAccount();
		if(userAccount == null || userAccount.equals("")) {
			return "請輸入電子郵件";	
		}else if(!(Pattern.matches(regex, userAccount))){
			return "電子郵件格式錯誤";		
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
			return "手機號碼應為10碼數字";
		}else if(!(Pattern.matches(regex4, userTel))) {
			return "手機號碼開頭為09";
		}
		
		final Date birthday = userVO.getBirthday();
		String birth = birthday.toString();
		System.out.println(birth);		
		if(birthday == null || birthday.equals("")) {
			return "請輸入出生日期";	
		}else if(!(Pattern.matches(regex5, birth))) {
			return "輸入格式為年-月-日 範例：1990-01-01";
		}
		
		final int result = dao.insert(userVO);
		if (result < 1) {
			return "註冊失敗，請重新註冊";
		}
		
		System.out.println("im in UserServiceImpl");
		return "Register Success";   // 沒有回傳就等於成功
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

	@Override
	public String isDuplicateAccount(UserVO userVO) {
		final String userAccount = userVO.getUserAccount();
		final List<UserVO> listUserVO = dao.isDuplicateAccount(userAccount);
//		System.out.println(listUserVO);
		
		int count = 0;
		for (UserVO userVO2: listUserVO) {
//			System.out.println("USER_ACCOUNT:"+userVO2.getUserAccount());
			count++;
		}
		System.out.println(count);
		if (count >= 1 ) {
			return "帳號重複，請重新輸入";
		}
		
		System.out.println("im in UserServiceImpl Account Pass");
		return "pass";   
		
	}

	

}
