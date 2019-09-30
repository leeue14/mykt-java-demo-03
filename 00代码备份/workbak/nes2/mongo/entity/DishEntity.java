/**
 * 
 */
package com.carelinker.nes2.mongo.entity;

import com.carelinker.core.entity.BaseEntity;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Transient;
import java.util.List;


/**
 * @author fengchu
 * 
 */
@Document(collection = "dish")
public class DishEntity extends BaseEntity implements Cloneable {

	@DBRef
	private List<FoodEntity> foods;

	private String dishName;
	private String largeType;
	private String largeTypeName;
	@Transient
	private Double energy;

	public DishEntity() {

	}

	public DishEntity(String largeType) {
		this.largeType = largeType;
	}

	public DishEntity(List<FoodEntity> foods, String dishName,
                      String largeType) {

		this.foods = foods;
		this.dishName = dishName;
		this.largeType = largeType;
	}

	@Override
	public DishEntity clone() {
		DishEntity o = null;
		try {
			o =  (DishEntity) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}
	
	public List<FoodEntity> getFoods() {
		return foods;
	}

	public void setFoods(List<FoodEntity> foods) {
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
		this.energy = energy;
	}

}
