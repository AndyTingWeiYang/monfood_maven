package com.model.product.util;

public enum ProductStatus {
	
	status0("上架",1),
	status1("售完",2),
	status2("下架",3);

	private String name;
	private int index;
	
	private ProductStatus(String name, int index) {
		this.name = name;
		this.index = index;
	}
	
	public static String getProductStatus(int index) {
		for (ProductStatus os : ProductStatus.values()) {
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
