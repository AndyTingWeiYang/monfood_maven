package com.model.user.serviceImpl;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import org.apache.commons.lang3.ObjectUtils.Null;

import com.model.user.UserVO;
import com.model.user.dao.UserDAO;
import com.model.user.dao.impl.UserDAOImpl;
import com.model.user.service.UserService;

import mailservice.MailService;

public class UserServiceImpl implements UserService {
	private UserDAO dao; // 介面型態 跟spring銜接

	public UserServiceImpl() {
		dao = new UserDAOImpl();
	}

	// 電子郵件格式驗證
	String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	// 密碼格式驗證
	String regex2 = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
	// 手機號碼格式驗證
	String regex3 = "\\d{10}";
	String regex4 = "^09[0-9]{2}\\d{6}$";
	// 生日格式驗證
	String regex5 = "^\\d{4}\\-\\d{2}\\-\\d{2}$";

	@Override
	public String userRegister(UserVO userVO) {

		final String userName = userVO.getUserName();

		if (userName == null || userName.equals("")) {
			return "請輸入完整姓名";
		}

		final String userAccount = userVO.getUserAccount();
		if (userAccount == null || userAccount.equals("")) {
			return "請輸入電子郵件";
		} else if (!(Pattern.matches(regex, userAccount))) {
			return "電子郵件格式錯誤";
		}

		final String userPassword = userVO.getUserPassword();
		if (userPassword == null || userPassword.equals("")) {
			return "請輸入密碼";
		} else if (!(Pattern.matches(regex2, userPassword))) {
			return "*密碼長度須至少八個字元。密碼複雜度須包含:小寫字母、大寫字母、數字";
		}

		final String userTel = userVO.getUserTel();
		if (userTel == null || userTel.equals("")) {
			return "請輸入手機號碼";
		} else if (!(Pattern.matches(regex3, userTel))) {
			return "手機號碼應為10碼數字";
		} else if (!(Pattern.matches(regex4, userTel))) {
			return "手機號碼開頭為09";
		}

		final Date birthday = userVO.getBirthday();
		String birth = birthday.toString();
		System.out.println(birth);
		if (birthday == null || birthday.equals("")) {
			return "請輸入出生日期";
		} else if (!(Pattern.matches(regex5, birth))) {
			return "輸入格式為年-月-日 範例：1990-01-01";
		}

		final int result = dao.insert(userVO);
		if (result < 1) {
			return "註冊失敗，請重新註冊";
		} else {

			long msb = System.currentTimeMillis();
			long lsb = System.currentTimeMillis();
			UUID uuidConstructor = new UUID(msb, lsb);
			String subject = "歡迎使用MonFood";
			String ch_name = userName;
			// http://localhost:8080/monfood_maven/userAccount/UUID
			// https://35.201.129.109:8443/monfood_maven/userAccount/UUID
			String messageText = "Hello! " + ch_name + " 請點選連結啟用帳號: " + "\n" + "<a href=\"https://35.201.129.109:8443/monfood_maven/"
					+ "AccountStatusServlet?email=" + userAccount + "&token=" + uuidConstructor +"\">MonFood</a>";
			MailService mailService = new MailService();
			mailService.sendMail(userAccount, subject, messageText);

		}

		return "Register Success"; // 沒有回傳就等於成功
	}

	@Override
	public UserVO userLogin(UserVO userVO) {
		final String loginUserAccount = userVO.getUserAccount();
		UserVO loginUserVO = dao.selectByUserAccount(loginUserAccount);

		return loginUserVO;
	}

	@Override
	public String userModify(UserVO userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String resetPassword(UserVO userVO) {

		final String userAccount = userVO.getUserAccount();
		if (userAccount == null || userAccount.equals("")) {
			return "請輸入電子郵件";
		} else if (!(Pattern.matches(regex, userAccount))) {
			return "電子郵件格式錯誤";
		}

		final String userPassword = userVO.getUserPassword();
		if (userPassword == null || userPassword.equals("")) {
			return "請輸入密碼";
		} else if (!(Pattern.matches(regex2, userPassword))) {
			return "*密碼長度須至少八個字元。密碼複雜度須包含:小寫字母、大寫字母、數字";
		}

		String resetReult = dao.updatePassword(userVO);
		if (!("UpdateCompleted".equals(resetReult))) {
			return "ResetFailed";
		}
		return "ResetSuccessfully";

	}

	@Override
	public String resetAccountStatus(String userAccount) {
//		UserVO userVO = new UserVO();
//		String userAccont = userVO.getUserAccount();
		String resetReult = dao.updateAccountStatus(userAccount);
		if ("CheckFailed".equals(resetReult)) {
			return "ResetFailed";
		}
		return "ResetSuccessfully";

	}

	@Override
	public String isDuplicateAccount(UserVO userVO) {
		final String userAccount = userVO.getUserAccount();
		final List<UserVO> listUserVO = dao.isDuplicateAccount(userAccount);
//		System.out.println(listUserVO);

		int count = 0;
		for (UserVO userVO2 : listUserVO) {
//			System.out.println("USER_ACCOUNT:"+userVO2.getUserAccount());
			count++;
		}
		if (count >= 1) {
			return "DuplicateAccount";
		}

		return "pass";

	}

	@Override
	public UserVO getOneByUserAccount(String userAccount) {
		UserVO getUserVO = dao.selectByUserAccount(userAccount);

		return getUserVO;
		
	}

	@Override
	public List<UserVO> getOneByUserName(String userName) {
		List<UserVO> listUserVO = dao.selectByUserName(userName);

		return listUserVO;
			
	}

	@Override
	public List<UserVO> getAll() {
		List<UserVO> listUserVO = dao.getAll();
		
		return listUserVO;
	}
	
	@Override
	public UserVO getOneByUserId(UserVO userVO) {
		
		final Integer userId = userVO.getUserId();
		
		if (userId == null) {
			return null;
		}
		
		userVO = dao.selectByUserId(userId);
		
		if (userVO == null) {
			return null;
		}
		
		return userVO;
	}
	
	@Override
	public String updateProfile(Integer userId, String data, String msg) {
		String updateMsg;
		if (userId == null) {
			return "會員編號不存在, 無法更新";
		}
		
		if ("\"budget\"".equals(msg)) {
			Integer budget = Integer.parseInt(data);
			updateMsg = dao.updateBudget(budget, userId);
			if ("UpdateFailed".equals(updateMsg)) {
				return "updateFailed";
			}
		}
		if ("\"kcal\"".equals(msg)) {
			Integer kcal = Integer.parseInt(data);
			updateMsg = dao.updateKcal(kcal, userId);
			if ("UpdateFailed".equals(updateMsg)) {
				return "updateFailed";
			}
		}
		if ("\"introduction\"".equals(msg)) {
			String profile = data.substring(1, data.length()-1);
			updateMsg = dao.updateProfile(profile, userId);
			if ("UpdateFailed".equals(updateMsg)) {
				return "updateFailed";
			}
		}
		return "updateSucess";
	}
	
	@Override
	public String updateProfilePic(byte[] pic, Integer userId) {
		if (pic == null) {
			return "UpdateFailed";
		}
		return dao.updateProfilePic(pic, userId);
	}

}

//int userId = loginUserVO.getUserId();	
//String userName =  loginUserVO.getUserName();
//String userAccount =loginUserVO.getUserAccount();
//String userPassword =  loginUserVO.getUserPassword();
//String userTel =  loginUserVO.getUserTel();
//String userProfile =  loginUserVO.getUserProfile();
//Date birthday =  loginUserVO.getBirthday();
//Integer calories = loginUserVO.getCalories();
//Integer budget = loginUserVO.getBudget();
//byte[] profilePic = loginUserVO.getProfilePic();
//Integer monsLevel = loginUserVO.getMonsLevel();
//String monsName =loginUserVO.getMonsName();
//Timestamp updateTime =loginUserVO.getUpdateTime();
