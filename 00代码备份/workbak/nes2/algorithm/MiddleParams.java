/**
 * 
 */
package com.carelinker.nes2.algorithm;

import com.carelinker.common.utils.PrintUtils;

import java.util.HashSet;
import java.util.Set;


/**
 * @author fengchu
 * 
 */
public class MiddleParams {

	private double BMI;
	private int mealCount = 0;
	private int mealCountType = 0;
	private int maxGI;
	private int stapleFoodCountOfLowGI;
	private int diabetesLevel;
	private int hypertensionLevel;
	private int weightLevel;
	private Boolean isBsControlInstability = false;// 血糖控制不稳
	private Boolean isHighCholesterol = false;// 高胆固醇
	private Boolean isHTG = false;// 高三酰甘油
	private double resetRate;// 总能量1500以下时，要按比例减少
	Set<String> selectedDishes = new HashSet<String>();
	public double getBMI() {
		return BMI;
	}

	public void setBMI(double bMI) {
		this.BMI = bMI;
		PrintUtils.test("BMI: " + BMI);
	}

	public int getMealCount() {
		return mealCount;

	}

	public void setMealCount(int mealCount) {
		this.mealCount = mealCount;
		PrintUtils.test("mealCount: " + mealCount);
	}

	public int getMealCountType() {
		return mealCountType;
	}

	public void setMealCountType(int mealCountType) {
		this.mealCountType = mealCountType;
		PrintUtils.test("mealCountType: " + mealCountType);
	}

	public int getMaxGI() {
		return maxGI;
	}

	public void setMaxGI(int maxGI) {
		this.maxGI = maxGI;
		PrintUtils.test("maxGI: " + maxGI);
	}

	public int getStapleFoodCountOfLowGI() {
		return stapleFoodCountOfLowGI;
	}

	public void setStapleFoodCountOfLowGI(int stapleFoodCountOfLowGI) {
		this.stapleFoodCountOfLowGI = stapleFoodCountOfLowGI;
		PrintUtils.test("stapleFoodCountOfLowGI: " + stapleFoodCountOfLowGI);
	}

	public int getDiabetesLevel() {
		return diabetesLevel;
	}

	public void setDiabetesLevel(int diabetesLevel) {
		this.diabetesLevel = diabetesLevel;
		PrintUtils.test("diabetesLevel: " + diabetesLevel);
	}

	public int getHypertensionLevel() {
		return hypertensionLevel;
	}

	public void setHypertensionLevel(int hypertensionLevel) {
		this.hypertensionLevel = hypertensionLevel;
		PrintUtils.test("hypertensionLevel: " + hypertensionLevel);
	}

	public int getWeightLevel() {
		return weightLevel;
	}

	public void setWeightLevel(int weightLevel) {
		this.weightLevel = weightLevel;
		PrintUtils.test("weightLevel: " + weightLevel);
	}

	public Boolean getIsBsControlInstability() {
		return isBsControlInstability;
	}

	public void setIsBsControlInstability(Boolean isBsControlInstability) {
		this.isBsControlInstability = isBsControlInstability;
	}

	public Boolean getIsHighCholesterol() {
		return isHighCholesterol;
	}

	public void setIsHighCholesterol(Boolean isHighCholesterol) {
		this.isHighCholesterol = isHighCholesterol;
	}

	public Boolean getIsHTG() {
		return isHTG;
	}

	public void setIsHTG(Boolean isHTG) {
		this.isHTG = isHTG;
	}

	public double getResetRate() {
		return resetRate;
	}

	public void setResetRate(double resetRate) {
		this.resetRate = resetRate;
	}

	public Set<String> getSelectedDishes() {
		return selectedDishes;
	}

	public void setSelectedDishes(Set<String> selectedDishes) {
		this.selectedDishes = selectedDishes;
	}

}
