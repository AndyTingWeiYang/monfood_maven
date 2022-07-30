package com.model.monster;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class test {

	public static void main(String[] args) throws SQLException, IOException {
		MonsterVO monsterVO = new MonsterVO();
		IMonsterDAO dao = new MonsterJDBCDAO();
		
		File file = new File("C:/JavaFramework/eclipse-workspace/monfood_maven/src/main/webapp/images/MonsterLV1.png");
		FileInputStream fis = new FileInputStream(file);
		byte[] pic = new byte[fis.available()];
		fis.read(pic);
		//1
		monsterVO.setMonsLevel(1);
		monsterVO.setDiscount(0);
		monsterVO.setMonsPic(pic);
		
		dao.update(monsterVO);
		
		//2
		File file2 = new File("C:/JavaFramework/eclipse-workspace/monfood_maven/src/main/webapp/images/MonsterLV2.png");
		FileInputStream fis2 = new FileInputStream(file2);
		byte[] pic2 = new byte[fis2.available()];
		fis2.read(pic2);
		monsterVO.setMonsLevel(2);
		monsterVO.setDiscount(5);
		monsterVO.setMonsPic(pic2);
		
		dao.update(monsterVO);
		//3
		File file3 = new File("C:/JavaFramework/eclipse-workspace/monfood_maven/src/main/webapp/images/MonsterLV3.png");
		FileInputStream fis3 = new FileInputStream(file3);
		byte[] pic3 = new byte[fis3.available()];
		fis3.read(pic3);
		monsterVO.setMonsLevel(3);
		monsterVO.setDiscount(10);
		monsterVO.setMonsPic(pic3);
		
		dao.update(monsterVO);
		//4
		File file4 = new File("C:/JavaFramework/eclipse-workspace/monfood_maven/src/main/webapp/images/MonsterLV4.png");
		FileInputStream fis4 = new FileInputStream(file4);
		byte[] pic4 = new byte[fis4.available()];
		fis4.read(pic4);
		monsterVO.setMonsLevel(4);
		monsterVO.setDiscount(15);
		monsterVO.setMonsPic(pic4);
		
		dao.update(monsterVO);
		//5
		File file5 = new File("C:/JavaFramework/eclipse-workspace/monfood_maven/src/main/webapp/images/MonsterLV5.png");
		FileInputStream fis5 = new FileInputStream(file5);
		byte[] pic5 = new byte[fis5.available()];
		fis5.read(pic5);
		monsterVO.setMonsLevel(5);
		monsterVO.setDiscount(20);
		monsterVO.setMonsPic(pic5);
		
		dao.update(monsterVO);
	}

}
