package com.model.reception;

public enum OrderStatus {
	
	status0("未成立",0),
	status1("備餐中",1),
	status2("外送中",2),
	status3("完成訂單",3),
	status4("拒絕接單",4);
	private String name;
	private int index;
	
	private OrderStatus(String name, int index) {
		this.name = name;
		this.index = index;
	}
	
	public static String getOrderStatus(int index) {
		for (OrderStatus os : OrderStatus.values()) {
			if (os.getIndex() == index) {				
				return os.name;
			}
		}
		return null;		
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	
	
}
