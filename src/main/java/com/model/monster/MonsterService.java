package com.model.monster;

import java.sql.SQLException;
import java.util.List;

public class MonsterService {

	private IMonsterDAO dao;
	
	public  MonsterService() {
		dao = new MonsterJDBCDAO();
	}

	public MonsterVO addMonster(Integer monsLevel, Integer discount, byte[] monsPic) throws SQLException {
		
		MonsterVO monsterVO = new MonsterVO();
		
		monsterVO.setMonsLevel(monsLevel);
		monsterVO.setDiscount(discount);
		monsterVO.setMonsPic(monsPic);
		
		dao.insert(monsterVO);
		
		return monsterVO;
	}
	
	public MonsterVO updateMonster(Integer monsLevel, Integer discount, byte[] monsPic) throws SQLException {
		
		MonsterVO monsterVO = new MonsterVO();
		
		monsterVO.setMonsLevel(monsLevel);
		monsterVO.setDiscount(discount);
		monsterVO.setMonsPic(monsPic);
		
		dao.update(monsterVO);
		
		return monsterVO;
	}
	
	public void deleteMonster(Integer monsLevel) throws SQLException {
		dao.delete(monsLevel);

	}

	public MonsterVO getOneMonster(Integer monsLevel) throws SQLException {
		return dao.findByPrimaryKey(monsLevel);
	}

	public List<MonsterVO> getAll() throws SQLException {
		return dao.getAll();
	}

}
