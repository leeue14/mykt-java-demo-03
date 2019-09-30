/**
 * 
 */
package com.carelinker.nes2.algorithm;

import com.carelinker.core.entity.BaseEntity;
import com.carelinker.nes2.mongo.entity.DishEntity;
import com.carelinker.nes2.mongo.entity.FoodEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fengchu
 * 
 */
public class MiddleMeal extends BaseEntity {

	private List<DishEntity> dishes;

	private double energy;
	private double proteinG;
	private double fatG;
	private double carboG;
	private double fibreG;
	private double rate;
	private double stapleGI;
	private String type;

	private double startCarboVector;
	private double startProteinVector;
	private double startFatVector;
	private double startFibreVector;

	public MiddleMeal(String type) {
		this.type = type;
	}

	public List<DishEntity> getDishes() {
		return dishes;
	}

	public void setDishes(List<DishEntity> dishes) {
		this.dishes = dishes;
	}

	public double getEnergy() {
		return energy;
	}

	public void setEnergy(double energy) {
		this.energy = energy;
	}

	public double getProteinG() {
		return proteinG;
	}

	public void setProteinG(double proteinG) {
		this.proteinG = proteinG;
	}

	public double getFatG() {
		return fatG;
	}

	public void setFatG(double fatG) {
		this.fatG = fatG;
	}

	public double getCarboG() {
		return carboG;
	}

	public void setCarboG(double carboG) {
		this.carboG = carboG;
	}

	public double getFibreG() {
		return fibreG;
	}

	public void setFibreG(double fibreG) {
		this.fibreG = fibreG;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getStartCarboVector() {
		return startCarboVector;
	}

	public void setStartCarboVector(double startCarboVector) {
		this.startCarboVector = startCarboVector;
	}

	public double getStartProteinVector() {
		return startProteinVector;
	}

	public void setStartProteinVector(double startProteinVector) {
		this.startProteinVector = startProteinVector;
	}

	public double getStartFatVector() {
		return startFatVector;
	}

	public void setStartFatVector(double startFatVector) {
		this.startFatVector = startFatVector;
	}

	public double getStartFibreVector() {
		return startFibreVector;
	}

	public void setStartFibreVector(double startFibreVector) {
		this.startFibreVector = startFibreVector;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getStapleGI() {
		return stapleGI;
	}

	public void setStapleGI(Double stapleGI) {
		this.stapleGI = stapleGI;
	}

	public void resetParams(double resetRate) {

		this.energy = this.energy * resetRate;
		this.carboG = this.carboG * resetRate;
		this.proteinG = this.proteinG * resetRate;
		this.fatG = this.fatG * resetRate;
		this.fibreG = this.fibreG * resetRate;

		for (DishEntity dishEntityIt : dishes) {

			dishEntityIt.setEnergy(dishEntityIt.getEnergy() * resetRate);

		}

		List<FoodEntity> foodList = new ArrayList<FoodEntity>();

		for (DishEntity dishIt : dishes) {
			foodList.addAll(dishIt.getFoods());
		}

		for (FoodEntity foodIt : foodList) {
			foodIt.calTraceElementsWithResetRate(resetRate);
		}

	}

}
