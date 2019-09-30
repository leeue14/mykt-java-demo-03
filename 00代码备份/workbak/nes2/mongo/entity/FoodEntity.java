/**
 * 
 */
package com.carelinker.nes2.mongo.entity;

import com.carelinker.common.utils.MathUtils;
import com.carelinker.core.entity.BaseEntity;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * @author fengchu
 * 
 */
@Document(collection = "food")
public class FoodEntity extends BaseEntity {

	private String foodName;
	private Double weight;
	private Double GI;
	private String foodType;
	private String foodTypeName;
	private Double energyRange;

	private Double energy;
	private Double edibleAmount;
	private Double waterG;
	private Double proteinG;
	private Double fatG;
	private Double carboG;
	private Double fibreG;
	private Double vaUG;
	private Double vb1MG;
	private Double vb2MG;
	private Double vb3MG;
	private Double vcMG;
	private Double veMG;
	private Double naMG;
	private Double caMG;
	private Double feMG;
	private Double choMG;

	private Double startNum;
	private Double incrementNum;
	private Double maxNum;
	private String unit;
	private Double GIAdjusted;
	private Double[] weightIncrementVector = new Double[3];
	private Double incrementEnergy;

	public void setWeightIncrementVector(Double[] weightIncrementVector) {
		this.weightIncrementVector = weightIncrementVector;
	}

	public void calWeightIncrementVector() {
		weightIncrementVector[0] = this.getCarboG() * incrementNum;
		weightIncrementVector[1] = this.getProteinG() * incrementNum;
		weightIncrementVector[2] = this.getFatG() * incrementNum;
	}

	public void calGIAdjusted() {
		this.GIAdjusted = MathUtils.formatDoubleRoundUp(1.0 - (this.getGI() / 100.0),
				2);
	}

	public void calIncrementEnergy() {
		incrementEnergy = weightIncrementVector[0] * 4
				+ weightIncrementVector[1] * 4 + weightIncrementVector[2] * 9;
	}

	public void calTraceElements() {

		this.waterG = this.waterG * this.weight;
		this.proteinG = this.proteinG * this.weight;
		this.fatG = this.fatG * this.weight;
		this.carboG = this.carboG * this.weight;
		this.fibreG = this.fibreG * this.weight;
		this.vaUG = this.vaUG * this.weight;
		this.vb1MG = this.vb1MG * this.weight;
		this.vb2MG = this.vb2MG * this.weight;
		this.vb3MG = this.vb3MG * this.weight;
		this.vcMG = this.vcMG * this.weight;
		this.veMG = this.veMG * this.weight;
		this.naMG = this.naMG * this.weight;
		this.caMG = this.caMG * this.weight;
		this.feMG = this.feMG * this.weight;
		this.choMG = this.choMG * this.weight;
		this.energy = this.carboG * 4 + this.proteinG * 4 + this.fatG * 9;
	}

	public void calTraceElementsWithResetRate(double resetRate) {

		if (resetRate > 1.0) {
			resetRate = 1.0;
		}

		this.weight = this.weight * resetRate;
		this.carboG = this.carboG * resetRate;
		this.proteinG = this.proteinG * resetRate;
		this.proteinG = this.proteinG * resetRate;

		this.waterG = this.waterG * resetRate;
		this.proteinG = this.proteinG * resetRate;
		this.fatG = this.fatG * resetRate;
		this.carboG = this.carboG * resetRate;
		this.fibreG = this.fibreG * resetRate;
		this.vaUG = this.vaUG * resetRate;
		this.vb1MG = this.vb1MG * resetRate;
		this.vb2MG = this.vb2MG * resetRate;
		this.vb3MG = this.vb3MG * resetRate;
		this.vcMG = this.vcMG * resetRate;
		this.veMG = this.veMG * resetRate;
		this.naMG = this.naMG * resetRate;
		this.caMG = this.caMG * resetRate;
		this.feMG = this.feMG * resetRate;
		this.choMG = this.choMG * resetRate;
		this.energy = this.carboG * 4 + this.proteinG * 4 + this.fatG * 9;
	}
	
	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getGI() {
		return GI;
	}

	public void setGI(Double gI) {
		this.GI = gI;
	}

	public Double getEdibleAmount() {
		return edibleAmount;
	}

	public void setEdibleAmount(Double edibleAmount) {
		this.edibleAmount = edibleAmount;
	}

	public Double getWaterG() {
		return waterG;
	}

	public void setWaterG(Double waterG) {
		this.waterG = waterG;
	}

	public Double getProteinG() {
		return proteinG;
	}

	public void setProteinG(Double proteinG) {
		this.proteinG = proteinG;
	}

	public Double getFatG() {
		return fatG;
	}

	public void setFatG(Double fatG) {
		this.fatG = fatG;
	}

	public Double getCarboG() {
		return carboG;
	}

	public void setCarboG(Double carboG) {
		this.carboG = carboG;
	}

	public Double getFibreG() {
		return fibreG;
	}

	public void setFibreG(Double fibreG) {
		this.fibreG = fibreG;
	}

	public Double getVaUG() {
		return vaUG;
	}

	public void setVaUG(Double vaUG) {
		this.vaUG = vaUG;
	}

	public Double getVb1MG() {
		return vb1MG;
	}

	public void setVb1MG(Double vb1mg) {
		this.vb1MG = vb1mg;
	}

	public Double getVb2MG() {
		return vb2MG;
	}

	public void setVb2MG(Double vb2mg) {
		this.vb2MG = vb2mg;
	}

	public Double getVb3MG() {
		return vb3MG;
	}

	public void setVb3MG(Double vb3mg) {
		this.vb3MG = vb3mg;
	}

	public Double getVcMG() {
		return vcMG;
	}

	public void setVcMG(Double vcMG) {
		this.vcMG = vcMG;
	}

	public Double getVeMG() {
		return veMG;
	}

	public void setVeMG(Double veMG) {
		this.veMG = veMG;
	}

	public Double getNaMG() {
		return naMG;
	}

	public void setNaMG(Double naMG) {
		this.naMG = naMG;
	}

	public Double getCaMG() {
		return caMG;
	}

	public void setCaMG(Double caMG) {
		this.caMG = caMG;
	}

	public Double getFeMG() {
		return feMG;
	}

	public void setFeMG(Double feMG) {
		this.feMG = feMG;
	}

	public Double getChoMG() {
		return choMG;
	}

	public void setChoMG(Double choMG) {
		this.choMG = choMG;
	}

	public Double getStartNum() {
		return startNum;
	}

	public void setStartNum(Double startNum) {
		this.startNum = startNum;
	}

	public Double getIncrementNum() {
		return incrementNum;
	}

	public void setIncrementNum(Double incrementNum) {
		this.incrementNum = incrementNum;
	}

	public Double getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(Double maxNum) {
		this.maxNum = maxNum;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getGIAdjusted() {
		return GIAdjusted;
	}

	public void setGIAdjusted(Double gIAdjusted) {
		this.GIAdjusted = gIAdjusted;
	}

	public Double getIncrementEnergy() {
		return incrementEnergy;
	}

	public void setIncrementEnergy(Double incrementEnergy) {
		this.incrementEnergy = incrementEnergy;
	}

	public Double[] getWeightIncrementVector() {
		return weightIncrementVector;
	}

	public Double getEnergy() {
		return energy;
	}

	public void setEnergy(Double energy) {
		this.energy = energy;
	}

	public String getFoodType() {
		return foodType;
	}

	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}

	public String getFoodTypeName() {
		return foodTypeName;
	}

	public void setFoodTypeName(String foodTypeName) {
		this.foodTypeName = foodTypeName;
	}

	public Double getEnergyRange() {
		return energyRange;
	}

	public void setEnergyRange(Double energyRange) {
		this.energyRange = energyRange;
	}

}
