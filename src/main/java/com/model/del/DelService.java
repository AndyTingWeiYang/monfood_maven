package com.model.del;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class DelService {
	
	private DelDAO dao;

	public DelService() {
		dao = new DelDAOImpl();
	}

	public DelVO addDel(String delName, String delAccount, String delPassword, String delTel, Date delBirthday,
			String platenumber, Integer status, Timestamp updateTime, byte[] delIDPhoto, byte[] carLicense,
			byte[] driverLicense, byte[] criminalRecord, byte[] insurance, String delAccountName, String delBankname,
			String delBankcode, String delBankaccount) {
		
		DelVO delVO = new DelVO();
		
		delVO.setDelName(delName);
		delVO.setDelAccount(delAccountName);
		delVO.setDelPassword(delPassword);
		delVO.setDelTel(delTel); 
		delVO.setDelBirthday(delBirthday);
		delVO.setPlatenumber(platenumber); 
		delVO.setStatus(status); 
		delVO.setUpdateTime(updateTime);
		delVO.setDelIDPhoto(delIDPhoto); 
		delVO.setCarLicense(carLicense);
		delVO.setDriverLicense(driverLicense);
		delVO.setCriminalRecord(criminalRecord); 
		delVO.setInsurance(insurance);
		delVO.setDelAccountName(delAccountName); 
		delVO.setDelBankname(delBankname); 
		delVO.setDelBankcode(delBankcode); 
		delVO.setDelBankaccount(delBankaccount); 
		
		return delVO;
		
	}
	
	
	public DelVO updateDel(DelVO delVO) {
				
		dao.update(delVO);
		
		return delVO;
		
	}
	
	public DelVO editBank(DelVO delVO) {
		
		dao.updateWithoutPic(delVO);
		
		return delVO;
		
	}


	public void deleteDel(Integer delID) {
		dao.delete(delID);
	}

	public DelVO getOneDel(Integer delID) {
		return dao.findByDelID(delID);
	}
	
	public DelVO findByDelNamePassword(String delName, String deTel) {
		return dao.findByDelNamePassword(delName, deTel);
	}

	public List<DelVO> getAll() {
		return dao.getAll();
	}

}
