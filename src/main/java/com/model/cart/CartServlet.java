package com.model.cart;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String SESSION_CART_KEY = "session_cart_key";

	public CartServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String url = "/front-end/cart/protect/cart_page.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, res);
		return;
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// 取得session
		HttpSession session = req.getSession();

		Object object = session.getAttribute(SESSION_CART_KEY);

		// 購物車是列表
		List<CartVO> cartList = null;
		if (object == null) {// 如果得到的是空值，就產生一個ArrayList
			cartList = new ArrayList<CartVO>();
		} else {// 如果購物車有清單，把他存回去session裡，並強制轉型成(List<CartVO>)
			cartList = (List<CartVO>) object;
		}

		String resId = req.getParameter("resId");
		String resName = req.getParameter("resName");
		String productId = req.getParameter("Integer");
		String productName = req.getParameter("productName");
		String amountStr = req.getParameter("amount");
		String productPriceStr = req.getParameter("productPrice");
		String productLKcal = req.getParameter("productLKcal");
		Part productPic = req.getPart("productPic");

		int amount = 0;
		try {
			amount = Integer.parseInt(amountStr);
		} catch (Exception e) {
			System.err.println("數量錯誤:" + amount);
		}
		Integer productPrice = 0;
		try {
			productPrice = Integer.parseInt(productPriceStr);
		} catch (Exception e) {
			System.err.println("金額錯誤:" + productPrice);
		}

		if ("goToCart".equals(action)) {
			cartList = addToCart(cartList, resId, resName, productId, productName, amount, productPrice, productLKcal,
					productPic);

			session.setAttribute(SESSION_CART_KEY, cartList);
//			String url = "/front-end/cart/cart_page.jsp";
//			RequestDispatcher rd = req.getRequestDispatcher(url);
//			rd.forward(req, res);
			res.sendRedirect(req.getContextPath() + "/CartServlet");
		}

		if ("addToCart".equals(action)) {
			try {
				cartList = addToCart(cartList, resId, resName, productId, productName, amount, productPrice,
						productLKcal, productPic);

				session.setAttribute(SESSION_CART_KEY, cartList);
				res.getWriter().write("true");
			} catch (Exception e) {
				e.printStackTrace();
				res.getWriter().write("false");
			}
			return;
		}

		if ("delete".equals(action)) {
			String del = req.getParameter("del");
			Iterator<CartVO> item = cartList.iterator();
			while (item.hasNext()) {
				CartVO items = item.next();
				if (del.equals(items.getProductId())) {// 判斷。如果商品id和傳過來的id一樣，則刪除
					item.remove();
					break;
				}
			}
			session.setAttribute(SESSION_CART_KEY, cartList);
			String url = "/front-end/cart/protect/cart_page.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
			return;

		}
		if ("updateAmount".equals(action)) {
			try {
				cartList = updateAmount(cartList, productId, amount);

				session.setAttribute(SESSION_CART_KEY, cartList);
				res.getWriter().write("true");
			} catch (Exception e) {
				e.printStackTrace();
				res.getWriter().write("false");
			}
			return;

		}
//		if (!"checkout".equals(action)) {
//
//			// 刪除購物車中的商品
//			if ("delete".equals(action)) {
//				String delete_cartProduct = req.getParameter("id");
//
//				// 從集合中刪除一個商品
//				Iterator<CartVO> item = cartList.iterator();
//				while (item.hasNext()) {// 是否存在下一個對象
//					CartVO items = item.next();// 拿到購物車中所有商品
//					if (delete_cartProduct.equals(items.getProduct_id())) {// 判斷。如果商品id和傳過來的id一樣，則刪除
//						item.remove();
//						break;
//
//						// 新增商品至購物車中
//					} else if ("add".equals(action)) {
//						
//						boolean match = false;
//						// 取得後來新增的商品
//						CartVO productItem = getItem(req);
//
//						// 新增第一個商品
//						if (cartList == null) {
//							cartList = new ArrayList<CartVO>();
//							cartList.add(productItem);
//						} else {
//							for (int i = 0; i < cartList.size(); i++) {
//								CartVO prodItems = cartList.get(i);
//
//								// 假若新增的商品和購物車的商品一樣時
//								if (prodItems.getProduct_id().equals(productItem.getProduct_id())) {
//									prodItems.setProduct_quantity(
//											prodItems.getProduct_quantity() + productItem.getProduct_quantity());
//									cartList.set(i, productItem);
//									match = true;
//								}
//							}
//							if (!match) {
//								cartList.add(productItem);
//							}
//						}
//						session.setAttribute(SESSION_CART_KEY, cartList);
//						String url = "/front-end/cart/cart_page.jsp";
//						RequestDispatcher rd = req.getRequestDispatcher(url);
//						rd.forward(req, res);
//					}
//				}
//
//			} else if ("checkout".equals(action)) {
//				// 結帳，計算購物車商品價錢總數
//				float total = 0;
//				for (int i = 0; i < cartList.size(); i++) {
//					CartVO order = cartList.get(i);
//					double price = order.getProduct_price();
//					 quantity = order.getProduct_quantity();
//					total += (price * quantity);
//
//				}
//				String amount = String.valueOf(total);
//				req.setAttribute("amount", amount);
//				String url = "";
//				RequestDispatcher rd = req.getRequestDispatcher(url);
//				rd.forward(req, res);
//			}
//
//		}
//	}
//
//	private CartVO getItem(HttpServletRequest req) {
//		String product_id = req.getParameter("product_id");
//		String productphoto_id = req.getParameter("productphoto_id");
//		String product_name = req.getParameter("product_name");
//		String product_price = req.getParameter("product_price");
//		String product_quantity = req.getParameter("product_quantity");
//		CartVO item = new CartVO();
//		item.setProduct_id(product_id);
//		item.setProductphoto_id(productphoto_id);
//		item.setProduct_name(product_name);
//		item.setProduct_price((new Double(product_price)).doubleValue());
//		item.setProduct_quantity((new Integer(product_quantity)).intValue());
//
//		return item;
//	}
	}

	private List<CartVO> addToCart(List<CartVO> cartList, String resId, String resName, String productId,
			String productName, int amount, Integer productPrice, String productLKcal, Part productPic) throws IOException {
		boolean match = false;

		// 前往購物車明細頁
		for (CartVO vo : cartList) {
			if (productId.equals(vo.getProductId())) {
				// 如果productId一樣

				// 最終數量等於原數量加新數量
				int newAmount = vo.getAmount() + amount;
				vo.setAmount(newAmount);
				match = true;
				break;
			}
		}

		if (!match) {
			// 如果購物車沒這商品，則新增一筆至購物車
			CartVO vo = new CartVO();
			vo.setResId(Integer.parseInt(resId));
			vo.setResName(resName);
			vo.setProductId(Integer.parseInt(productId));
			vo.setProductName(productName);
			vo.setAmount(amount);
			vo.setProductPrice(productPrice);
			vo.setProductKcal(Integer.parseInt(productLKcal));
			InputStream is=productPic.getInputStream();
			byte[] buffer=new byte[is.available()];
			is.read(buffer);

			vo.setProductPic(buffer);
			cartList.add(vo);

		}
		return cartList;
	}

	private List<CartVO> updateAmount(List<CartVO> cartList, String productId, int amount) {
		for (CartVO vo : cartList) {
			if (productId.equals(vo.getProductId())) {
				// 如果productId一樣

				// 最終數量等於原數量加新數量
				int newAmount = vo.getAmount() + amount;
				vo.setAmount(newAmount);

			} else {
				System.out.println("有誤");
			}

		}
		return cartList;
	}
}

//	private List<CartVO> addToCart(List<CartVO> cartList, Integer resId, String resName, Integer productId,
//			String productName, Integer amount, Integer productPrice, Integer productKcal, byte[] productPic) {
//		
//}
