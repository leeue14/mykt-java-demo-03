/**
 * 
 */
package com.carelinker.nes2.vo;

import com.carelinker.common.utils.MathUtils;

import java.io.Serializable;


/**
 * @author fengchu
 * 
 */
public class Food implements Serializable{

	private static int DECIMAL = 0;
	
	private String id;
	private String foodName;
	private Double weight;
	private Integer GI;
	private Integer type;

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

	private String unit;

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
		this.weight = MathUtils.formatDoubleRoundUp(weight, DECIMAL);
	}

	public Integer getGI() {
		return GI;
	}

	public void setGI(Integer gI) {
		this.GI = gI;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Double getEdibleAmount() {
		return edibleAmount;
	}

	public void setEdibleAmount(Double edibleAmount) {
		this.edibleAmount = MathUtils.formatDoubleRoundUp(edibleAmount, 2);
	}

	public Double getWaterG() {
		return waterG;
	}

	public void setWaterG(Double waterG) {
		this.waterG = MathUtils.formatDoubleRoundUp(waterG, 2);
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
		this.fibreG = MathUtils.formatDoubleRoundUp(fibreG, 2);
	}

	public Double getVaUG() {
		return vaUG;
	}

	public void setVaUG(Double vaUG) {
		this.vaUG = MathUtils.formatDoubleRoundUp(vaUG, 2);
	}

	public Double getVb1MG() {
		return vb1MG;
	}

	public void setVb1MG(Double vb1mg) {
		this.vb1MG = MathUtils.formatDoubleRoundUp(vb1mg, 2);
	}

	public Double getVb2MG() {
		return vb2MG;
	}

	public void setVb2MG(Double vb2mg) {
		this.vb2MG = MathUtils.formatDoubleRoundUp(vb2mg, 2);
	}

	public Double getVb3MG() {
		return vb3MG;
	}

	public void setVb3MG(Double vb3mg) {
		this.vb3MG = MathUtils.formatDoubleRoundUp(vb3mg, 2);
	}

	public Double getVcMG() {
		return vcMG;
	}

	public void setVcMG(Double vcMG) {
		this.vcMG = MathUtils.formatDoubleRoundUp(vcMG, 2);
	}

	public Double getVeMG() {
		return veMG;
	}

	public void setVeMG(Double veMG) {
		this.veMG = MathUtils.formatDoubleRoundUp(veMG, 2);
	}

	public Double getNaMG() {
		return naMG;
	}

	public void setNaMG(Double naMG) {
		this.naMG = MathUtils.formatDoubleRoundUp(naMG, 2);
	}

	public Double getCaMG() {
		return caMG;
	}

	public void setCaMG(Double caMG) {
		this.caMG = MathUtils.formatDoubleRoundUp(caMG, 2);
	}

	public Double getFeMG() {
		return feMG;
	}

	public void setFeMG(Double feMG) {
		this.feMG = MathUtils.formatDoubleRoundUp(feMG, 2);
	}

	public Double getChoMG() {
		return choMG;
	}

	public void setChoMG(Double choMG) {
		this.choMG = MathUtils.formatDoubleRoundUp(choMG, 2);
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getEnergy() {
		return energy;
	}

	public void setEnergy(Double energy) {
		this.energy = MathUtils.formatDoubleRoundUp(energy, 2);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
