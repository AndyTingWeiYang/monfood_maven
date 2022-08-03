package com.model.del;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/DBGifReader2")
public class DBGifReader2 extends HttpServlet {

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			Statement stmt = con.createStatement();
			// id先放在form.html 
			String id = req.getParameter("id");
			ResultSet rs = stmt.executeQuery(
				"SELECT DEL_ID_PHOTO FROM DEL WHERE DEL_ID ="+ id );

			if (rs.next()) {
				byte[] pic = rs.getBytes("DEL_ID_PHOTO");
				out.write(pic);
//				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("DEL_ID_PHOTO"));
//				byte[] buf = new byte[4 * 1024]; // 4K buffer 資料庫的資料是一段段傳送所以無法使用avaliable分多次承接
//				int len;
//				while ((len = in.read(buf)) != -1) {
//					out.write(buf, 0, len);
//				}
//				in.close();
			} else {
				res.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/monfood?serverTimezone=Asia/Taipei", "root", "password");
		} catch (ClassNotFoundException e) {
			throw new UnavailableException("Couldn't load JdbcOdbcDriver");
		} catch (SQLException e) {
			throw new UnavailableException("Couldn't get db connection");
		}
	}

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}