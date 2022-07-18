package com.model.product;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.model.administrator.AdministratorVO;
import com.model.administrator.dao.AdministratorDAO;
import com.model.administrator.dao.impl.AdministratorDAOImpl;
import com.model.product.dao.ProductDAO;
import com.model.product.dao.impl.ProductDAOImpl;

public class TestProductAdmin {

	public static void main(String[] args) throws IOException {
//		proccessInsertProduct();
//		proccessUpdateAdmin();
//		proccessInsertProduct();
		proccessUpdateProduct();

	}

	private static void proccessInsertAdmin() {
		AdministratorVO admin = new AdministratorVO();
		admin.setAdminAccount("ccc@yahoo.com.tw");
		admin.setAdminPassword("123456abc");
		admin.setPermission(1);

		AdministratorDAO adminDAO = new AdministratorDAOImpl();
		adminDAO.insert(admin);
		System.out.println("success insert");

	}

	private static void proccessUpdateAdmin() {
		AdministratorVO admin = new AdministratorVO();

		admin.setAdminPassword("123456abc");
		admin.setPermission(2);
		admin.setAdminID(3);

		AdministratorDAO adminDAO = new AdministratorDAOImpl();
		adminDAO.update(admin);
		System.out.println("success update");

	}

	private static void proccessInsertProduct() throws IOException {
		InputStream in = new FileInputStream("C:\\Users\\Tibame_T14\\Desktop\\test\\fries.jpg"); // 輸入圖片路徑
		byte[] buf = new byte[in.available()];
		in.read(buf);

		ProductVO product = new ProductVO();
//		product.setProductKcal(123);
//		product.setProductName("latte");
//		product.setProductPic(buf);
//		product.setProductPrice(65);
//		product.setProductStatus(1);
//		product.setResID(4);

		product.setProductKcal(265);
		product.setProductName("fries");
		product.setProductPic(buf);
		product.setProductPrice(45);
		product.setProductStatus(1);
		product.setResID(4);

		ProductDAO productDAO = new ProductDAOImpl();
		productDAO.insert(product);

		in.close();
		System.out.println("success insert");
	}

	private static void proccessUpdateProduct() throws IOException {
		InputStream in = new FileInputStream("C:\\Users\\Tibame_T14\\Desktop\\test\\filet-fish.jpg");
		byte[] buf = new byte[in.available()];
		in.read(buf);
		ProductVO product = new ProductVO();

		product.setProductKcal(198);
		product.setProductName("filet-fish");
		product.setProductPic(buf);
		product.setProductPrice(45);
		product.setProductStatus(1);
		product.setResID(4);
		product.setProductID(14);

		ProductDAO productDAO = new ProductDAOImpl();
		productDAO.update(product);
		System.out.println("success update");

	}

}
