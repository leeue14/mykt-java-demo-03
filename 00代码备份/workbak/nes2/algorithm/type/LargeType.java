/**
 * 
 */
package com.carelinker.nes2.algorithm.type;

/**
 * @author fengchu
 * 
 */
public enum LargeType {

	/**
	 * 主食
	 */
	STAPLE("staple", "01"),
	/**
	 * 点心
	 */
	DESSERT("dessert", "02"),
	/**
	 * 奶
	 */
	MILK("milk", "03"),
	/**
	 * 水果
	 */
	FRUIT("fruit", "04"),
	/**
	 * 荤菜
	 */
	MEAT("meat", "05"),
	/**
	 * 素菜
	 */
	VEGETABLE("vegetable", "06"),
	/**
	 * 早餐主食
	 */
	BREAKFAST_STAPLE("breakfastStaple", "07"),
	/**
	 * 早餐菜
	 */
	BREAKFAST_DISH("breakfastDish", "08");

	private String name;
	private String id;

	private LargeType(String name, String id) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
