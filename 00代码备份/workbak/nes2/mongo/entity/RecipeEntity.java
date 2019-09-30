/**
 * 
 */
package com.carelinker.nes2.mongo.entity;

import com.carelinker.common.utils.MathUtils;
import com.carelinker.core.entity.BaseEntity;
import com.carelinker.nes2.vo.Meal;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fengchu
 * 
 */
@Document(collection = "recipe")
public class RecipeEntity extends BaseEntity {

	private static int DECIMAL = 0;

	@Indexed
	private String patientId;
	@Indexed
	private String date;
	private String attention;
	private Integer status;
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
	private Meal breakfast;
	private Meal afterBreakfast;
	private Meal lunch;
	private Meal afterLunch;
	private Meal supper;
	private Meal afterSupper;

	public RecipeEntity() {

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
		MUFARate = mUFARate;
	}

	public Double getPUFARate() {
		return PUFARate;
	}

	public void setPUFARate(Double pUFARate) {
		PUFARate = pUFARate;
	}

	public Meal getBreakfast() {
		return breakfast;
	}

	public void setBreakfast(Meal breakfast) {
		this.breakfast = breakfast;
	}

	public Meal getAfterBreakfast() {
		return afterBreakfast;
	}

	public void setAfterBreakfast(Meal afterBreakfast) {
		this.afterBreakfast = afterBreakfast;
	}

	public Meal getLunch() {
		return lunch;
	}

	public void setLunch(Meal lunch) {
		this.lunch = lunch;
	}

	public Meal getAfterLunch() {
		return afterLunch;
	}

	public void setAfterLunch(Meal afterLunch) {
		this.afterLunch = afterLunch;
	}

	public Meal getSupper() {
		return supper;
	}

	public void setSupper(Meal supper) {
		this.supper = supper;
	}

	public Meal getAfterSupper() {
		return afterSupper;
	}

	public void setAfterSupper(Meal afterSupper) {
		this.afterSupper = afterSupper;
	}

	public void formatDecimal() {

		this.energy = MathUtils.formatDoubleRoundUp(energy, DECIMAL);
		this.carboG = MathUtils.formatDoubleRoundUp(carboG, DECIMAL);
		this.proteinG = MathUtils.formatDoubleRoundUp(proteinG, DECIMAL);
		this.fatG = MathUtils.formatDoubleRoundUp(fatG, DECIMAL);
		this.fibreG = MathUtils.formatDoubleRoundUp(fibreG, DECIMAL);
	}
}
