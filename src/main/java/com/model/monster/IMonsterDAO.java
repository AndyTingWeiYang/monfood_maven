package com.model.monster;

import java.sql.SQLException;
import java.util.List;

public interface IMonsterDAO {
	
	public void insert(MonsterVO monsterVO) throws SQLException;
	
    public void update(MonsterVO monsterVO) throws SQLException;
    
    public void delete(Integer monsLevel) throws SQLException;
    
    public MonsterVO findByPrimaryKey(Integer monsLevel) throws SQLException;
    
    public List<MonsterVO> getAll() throws SQLException;
    
}
