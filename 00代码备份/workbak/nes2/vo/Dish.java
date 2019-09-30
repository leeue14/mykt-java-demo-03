/**
 * 
 */
package com.carelinker.nes2.vo;

import com.carelinker.common.utils.MathUtils;

import java.io.Serializable;
import java.util.List;


/**
 * @author fengchu
 * 
 */
public class Dish implements Serializable{
	
	private static int DECIMAL = 0;
	
	private List<Food> foods;

	private String id;
	private String dishName;
	private String largeType;
	private String largeTypeName;
	private Double energy;

	public Dish() {

	}

	public Dish(String largeType) {
		this.largeType = largeType;
	}

	public Dish(List<Food> foods, String dishName, String largeType) {
		this.foods = foods;
		this.dishName = dishName;
		this.largeType = largeType;

	}

	public List<Food> getFoods() {
		return foods;
	}

	public void setFoods(List<Food> foods) {
		this.foods = foods;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public String getLargeType() {
		return largeType;
	}

	public void setLargeType(String largeType) {
		this.largeType = largeType;
	}

	public String getLargeTypeName() {
		return largeTypeName;
	}

	public void setLargeTypeName(String largeTypeName) {
		this.largeTypeName = largeTypeName;
	}

	public Double getEnergy() {
		return energy;
	}

	public void setEnergy(Double energy) {
		this.energy = MathUtils.formatDoubleRoundUp(energy, DECIMAL);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
