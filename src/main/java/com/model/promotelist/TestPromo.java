package com.model.promotelist;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class TestPromo {

	public static void main(String[] args) throws ParseException {
		Scanner sc = new Scanner(System.in);
//insert new del member
		System.out.println("請輸入promoID");
		Integer promoteID = sc.nextInt();
		System.out.println("請輸入promocode");
		String promocode = sc.next();
		System.out.println("請輸入promoprice");
		Integer promoprice = sc.nextInt();
		System.out.println("請輸入sDate格式:yyyy-MM-dd");
		String sDate = sc.next();
		java.util.Date sDateUtil = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
		java.sql.Date startDate = new java.sql.Date(sDateUtil.getTime());
		System.out.println("請輸入eDate格式:yyyy-MM-dd");
		String eDate = sc.next();
		java.util.Date eDateUtil = new SimpleDateFormat("yyyy-MM-dd").parse(eDate);
		java.sql.Date enDate = new java.sql.Date(eDateUtil.getTime());
		System.out.println("請輸入status");
		Integer status = sc.nextInt();
		sc.close();

		PromoteListVO pListVO = new PromoteListVO(promoteID, promocode, promoprice, startDate, enDate, status);
		PromoteListDAO_interface daoInsert = new PromoteListJDBCDAO();
		daoInsert.insert(pListVO);

		System.out.println("新增成功");
	}

}
