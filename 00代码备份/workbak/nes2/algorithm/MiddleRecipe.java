/**
 * 
 */
package com.carelinker.nes2.algorithm;

import com.carelinker.nes2.algorithm.type.MealType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fengchu
 * 
 */

public class MiddleRecipe {

	private String patientId;

	private String date;
	private String attention;
	private Integer showGI;
	private Double energy;
	private Double carboRate;
	private Double carboG;
	private Double proteinRate;
	private Double proteinG;
	private Double fatRate;
	private Double fatG;
	private Double fibreG;
	private String saltG;
	private Double choMG;
	private Double MUFARate;
	private Double PUFARate;
	private MiddleMeal breakfast;
	private MiddleMeal afterBreakfast;
	private MiddleMeal lunch;
	private MiddleMeal afterLunch;
	private MiddleMeal supper;
	private MiddleMeal afterSupper;

	List<String> steps;

	public MiddleRecipe() {
		this.steps = new ArrayList();
		this.breakfast = new MiddleMeal(MealType.BREAKFAST);
		this.afterBreakfast = null;
		this.lunch = new MiddleMeal(MealType.LUNCH);
		this.afterLunch = null;
		this.supper = new MiddleMeal(MealType.SUPPER);
		this.afterSupper = null;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAttention() {
		return attention;
	}

	public void setAttention(String attention) {
		this.attention = attention;
	}

	public Integer getShowGI() {
		return showGI;
	}

	public void setShowGI(Integer showGI) {
		this.showGI = showGI;
	}

	public Double getEnergy() {
		return energy;
	}

	public void setEnergy(Double energy) {
		this.energy = energy;
	}

	public Double getCarboRate() {
		return carboRate;
	}

	public void setCarboRate(Double carboRate) {
		this.carboRate = carboRate;
	}

	public Double getCarboG() {
		return carboG;
	}

	public void setCarboG(Double carboG) {
		this.carboG = carboG;
	}

	public Double getProteinRate() {
		return proteinRate;
	}

	public void setProteinRate(Double proteinRate) {
		this.proteinRate = proteinRate;
	}

	public Double getProteinG() {
		return proteinG;
	}

	public void setProteinG(Double proteinG) {
		this.proteinG = proteinG;
	}

	public Double getFatRate() {
		return fatRate;
	}

	public void setFatRate(Double fatRate) {
		this.fatRate = fatRate;
	}

	public Double getFatG() {
		return fatG;
	}

	public void setFatG(Double fatG) {
		this.fatG = fatG;
	}

	public Double getFibreG() {
		return fibreG;
	}

	public void setFibreG(Double fibreG) {
		this.fibreG = fibreG;
	}

	public String getSaltG() {
		return saltG;
	}

	public void setSaltG(String saltG) {
		this.saltG = saltG;
	}

	public MiddleMeal getBreakfast() {
		return breakfast;
	}

	public void setBreakfast(MiddleMeal breakfast) {
		this.breakfast = breakfast;
	}

	public MiddleMeal getAfterBreakfast() {
		return afterBreakfast;
	}

	public void setAfterBreakfast(MiddleMeal afterBreakfast) {
		this.afterBreakfast = afterBreakfast;
	}

	public MiddleMeal getLunch() {
		return lunch;
	}

	public void setLunch(MiddleMeal lunch) {
		this.lunch = lunch;
	}

	public MiddleMeal getAfterLunch() {
		return afterLunch;
	}

	public void setAfterLunch(MiddleMeal afterLunch) {
		this.afterLunch = afterLunch;
	}

	public MiddleMeal getSupper() {
		return supper;
	}

	public void setSupper(MiddleMeal supper) {
		this.supper = supper;
	}

	public MiddleMeal getAfterSupper() {
		return afterSupper;
	}

	public void setAfterSupper(MiddleMeal afterSupper) {
		this.afterSupper = afterSupper;
	}

	public List<String> getSteps() {
		return steps;
	}

	public void setSteps(List<String> steps) {
		this.steps = steps;
	}

	public Double getChoMG() {
		return choMG;
	}

	public void setChoMG(Double choMG) {
		this.choMG = choMG;
	}

	public Double getMUFARate() {
		return MUFARate;
	}

	public void setMUFARate(Double mUFARate) {
		this.MUFARate = mUFARate;
	}

	public Double getPUFARate() {
		return PUFARate;
	}

	public void setPUFARate(Double pUFARate) {
		this.PUFARate = pUFARate;
	}

	public void resetParams(double resetRate) {
		this.energy = this.energy * resetRate;
		this.carboG = this.carboG * resetRate;
		this.proteinG = this.proteinG * resetRate;
		this.fatG = this.fatG * resetRate;
		this.fibreG = this.fibreG * resetRate;
	}

}
