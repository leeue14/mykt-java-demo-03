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
public class Meal implements Serializable{

	private static int DECIMAL = 0;
	
	private List<Dish> dishes;

	private String id;
	private Double energy;
	private Double proteinG;
	private Double fatG;
	private Double carboG;
	private Double fibreG;
	private Double rate;
	private Double stapleGI;
	private String isTake;
	private String type;

	public List<Dish> getDishes() {
		return dishes;
	}

	public void setDishes(List<Dish> dishes) {
		this.dishes = dishes;
	}

	public Double getEnergy() {
		return energy;
	}

	public void setEnergy(Double energy) {
		this.energy = MathUtils.formatDoubleRoundUp(energy, DECIMAL);
	}

	public Double getProteinG() {
		return proteinG;
	}

	public void setProteinG(Double proteinG) {
		this.proteinG = MathUtils.formatDoubleRoundUp(proteinG, DECIMAL);
	}

	public Double getFatG() {
		return fatG;
	}

	public void setFatG(Double fatG) {
		this.fatG = MathUtils.formatDoubleRoundUp(fatG, DECIMAL);
	}

	public Double getCarboG() {
		return carboG;
	}

	public void setCarboG(Double carboG) {
		this.carboG = MathUtils.formatDoubleRoundUp(carboG, DECIMAL);
	}

	public Double getFibreG() {
		return fibreG;
	}

	public void setFibreG(Double fibreG) {
		this.fibreG = MathUtils.formatDoubleRoundUp(fibreG, DECIMAL);
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = MathUtils.formatDoubleRoundUp(rate, 2);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getStapleGI() {
		return stapleGI;
	}

	public void setStapleGI(Double stapleGI) {
		this.stapleGI = stapleGI;
	}

	public String getIsTake() {
		return isTake;
	}

	public void setIsTake(String isTake) {
		this.isTake = isTake;
	}

}
