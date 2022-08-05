package com.model.del.service;
//package com.model.del.service;
//
//import java.sql.Date;
//import java.sql.Timestamp;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.List;
//import java.util.UUID;
//import java.util.regex.Pattern;
//
//import com.model.del.DelDAOImpl;
//import com.model.del.DelVO;
//import com.model.user.UserVO;
//import com.model.user.dao.UserDAO;
//import com.model.user.dao.impl.UserDAOImpl;
//import com.model.user.service.UserService;
//
//import mailservice.MailService;
//
//public class DelServiceImpl implements DelService {
//	private DelDAO dao; // 介面型態 跟spring銜接
//
//	public DelServiceImpl() {
//		dao = new UserDAOImpl();
//	}
//
//	// 電子郵件格式驗證
//	String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
//	// 密碼格式驗證
//	String regex2 = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
//	// 手機號碼格式驗證
//	String regex3 = "\\d{10}";
//	String regex4 = "^09[0-9]{2}\\d{6}$";
//	// 生日格式驗證
//	String regex5 = "^\\d{4}\\-\\d{2}\\-\\d{2}$";
//
//	@Override
//	public String userRegister(UserVO userVO) {
//
//		final String userName = userVO.getUserName();
//
//		if (userName == null || userName.equals("")) {
//			return "請輸入完整姓名";
//		}
//
//		final String userAccount = userVO.getUserAccount();
//		if (userAccount == null || userAccount.equals("")) {
//			return "請輸入電子郵件";
//		} else if (!(Pattern.matches(regex, userAccount))) {
//			return "電子郵件格式錯誤";
//		}
//
//		final String userPassword = userVO.getUserPassword();
//		if (userPassword == null || userPassword.equals("")) {
//			return "請輸入密碼";
//		} else if (!(Pattern.matches(regex2, userPassword))) {
//			return "*密碼長度須至少八個字元。密碼複雜度須包含:小寫字母、大寫字母、數字";
//		}
//
//		final String userTel = userVO.getUserTel();
//		if (userTel == null || userTel.equals("")) {
//			return "請輸入手機號碼";
//		} else if (!(Pattern.matches(regex3, userTel))) {
//			return "手機號碼應為10碼數字";
//		} else if (!(Pattern.matches(regex4, userTel))) {
//			return "手機號碼開頭為09";
//		}
//
//		final Date birthday = userVO.getBirthday();
//		String birth = birthday.toString();
//		System.out.println(birth);
//		if (birthday == null || birthday.equals("")) {
//			return "請輸入出生日期";
//		} else if (!(Pattern.matches(regex5, birth))) {
//			return "輸入格式為年-月-日 範例：1990-01-01";
//		}
//
//		final int result = dao.insert(userVO);
//		if (result < 1) {
//			return "註冊失敗，請重新註冊";
//		} else {
//
//			long msb = System.currentTimeMillis();
//			long lsb = System.currentTimeMillis();
//			UUID uuidConstructor = new UUID(msb, lsb);
//			System.out.println("我是userAccount：" + userAccount);
//			String subject = "歡迎使用MonFood";
//			String ch_name = userName;
//			// http://localhost:8080/monfood_maven/userAccount/UUID
//			String messageText = "Hello! " + ch_name + " 請點選連結啟用帳號: " + "\n" + "http://localhost:8080/monfood_maven/"
//					+ "AccountStatusServlet/" + userAccount + "/" + uuidConstructor;
//			MailService mailService = new MailService();
//			mailService.sendMail(userAccount, subject, messageText);
//
//			System.out.println("im in UserServiceImpl userRegister 已發送mail");
//		}
//
//		System.out.println("im in UserServiceImpl");
//		return "Register Success"; // 沒有回傳就等於成功
//	}
//
//	@Override
//	public UserVO userLogin(UserVO userVO) {
//		final String loginUserAccount = userVO.getUserAccount();
//		UserVO loginUserVO = dao.selectByUserAccount(loginUserAccount);
//
//		return loginUserVO;
//	}
//
//	@Override
//	public String userModify(UserVO userVO) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String resetPassword(UserVO userVO) {
//
//		final String userAccount = userVO.getUserAccount();
//		if (userAccount == null || userAccount.equals("")) {
//			return "請輸入電子郵件";
//		} else if (!(Pattern.matches(regex, userAccount))) {
//			return "電子郵件格式錯誤";
//		}
//
//		final String userPassword = userVO.getUserPassword();
//		if (userPassword == null || userPassword.equals("")) {
//			return "請輸入密碼";
//		} else if (!(Pattern.matches(regex2, userPassword))) {
//			return "*密碼長度須至少八個字元。密碼複雜度須包含:小寫字母、大寫字母、數字";
//		}
//
//		String resetReult = dao.updatePassword(userVO);
//		System.out.println("我在UserServiceImpl = " + resetReult);
//		if (!("UpdateCompleted".equals(resetReult))) {
//			return "ResetFailed";
//		}
//		return "ResetSuccessfully";
//
//	}
//
//	@Override
//	public String resetAccountStatus(String userAccount) {
////		UserVO userVO = new UserVO();
////		String userAccont = userVO.getUserAccount();
//		String resetReult = dao.updateAccountStatus(userAccount);
//		System.out.println("我在UserServiceImpl的updateAccountStatus = " + resetReult);
//		if ("CheckFailed".equals(resetReult)) {
//			return "ResetFailed";
//		}
//		return "ResetSuccessfully";
//
//	}
//
//	@Override
//	public String isDuplicateAccount(UserVO userVO) {
//		final String userAccount = userVO.getUserAccount();
//		final List<UserVO> listUserVO = dao.isDuplicateAccount(userAccount);
////		System.out.println(listUserVO);
//
//		int count = 0;
//		for (UserVO userVO2 : listUserVO) {
////			System.out.println("USER_ACCOUNT:"+userVO2.getUserAccount());
//			count++;
//		}
//		System.out.println("isDuplicateAccount查詢帳號重複筆數" + count);
//		if (count >= 1) {
//			return "DuplicateAccount";
//		}
//
//		System.out.println("im in UserServiceImpl isDuplicateAccount ： Account Pass");
//		return "pass";
//
//	}
//
//	@Override
//	public DelVO delLogin(DelVO delVO) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
//
////int userId = loginUserVO.getUserId();	
////String userName =  loginUserVO.getUserName();
////String userAccount =loginUserVO.getUserAccount();
////String userPassword =  loginUserVO.getUserPassword();
////String userTel =  loginUserVO.getUserTel();
////String userProfile =  loginUserVO.getUserProfile();
////Date birthday =  loginUserVO.getBirthday();
////Integer calories = loginUserVO.getCalories();
////Integer budget = loginUserVO.getBudget();
////byte[] profilePic = loginUserVO.getProfilePic();
////Integer monsLevel = loginUserVO.getMonsLevel();
////String monsName =loginUserVO.getMonsName();
////Timestamp updateTime =loginUserVO.getUpdateTime();
