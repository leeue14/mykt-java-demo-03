/**
 * 
 */
package com.carelinker.nes2.algorithm;

/**
 * @author fengchu
 * 
 */
public class InputParams {

	private Double height; // 身高
	private Double weight; // 体重
	private Integer age; // 年龄
	private Integer gender; // 性别
	private Integer laborLevel; // 劳动强度

	private Integer pregnancy; // 孕期
	private Integer breastFeeding = NesConstants.BREAST_FEEDING_0; // 哺乳期
	private Double percentOfBreastFeeding = 0.0; // 母乳比例

	private Double bloodsugar; // 空腹血糖
	private Integer systolic; // 高压
	private Integer diastolic; // 低压

	private Double cholesterol;
	private Double TG;

	private Boolean isRenalInadequacy = false;// 肾功能不全
	
	private Boolean isDM;// 是否有糖尿病

	public InputParams(){
		
	}
	
	public InputParams(Double height, Double weight, Integer age,
			Integer gender, Integer laborLevel, Integer pregnancy,
			Integer breastFeeding, Double percentOfBreastFeeding,
			Double bloodsugar, Integer systolic, Integer diastolic,
			Double cholesterol, Double tG, Boolean isDM) {
		this.height = height;
		this.weight = weight;
		this.age = age;
		this.gender = gender;
		this.laborLevel = laborLevel;
		this.pregnancy = pregnancy;
		this.breastFeeding = breastFeeding;
		this.percentOfBreastFeeding = percentOfBreastFeeding;
		this.bloodsugar = bloodsugar;
		this.systolic = systolic;
		this.diastolic = diastolic;
		this.cholesterol = cholesterol;
		this.TG = tG;
		this.isDM = isDM;
	}

	public void CheckInputParams() {
		if (height == null || height < 120) {
			this.height = 165.0;
		} 
		if (weight == null || weight < 30) {
			this.weight = 50.0;
		}
		if (age == null || age < 10) {
			this.age = 30;
		} 
		if (gender == null || gender != 1 || gender != 2) {
			this.gender = 1;
		} 
		if (laborLevel == null || laborLevel < 1 || laborLevel > 5) {
			this.laborLevel = 2;
		} 
		if (pregnancy == null || pregnancy < 0 || pregnancy > 3) {
			this.pregnancy = 0;
		} 
		if (breastFeeding == null || breastFeeding < 0 || breastFeeding > 2) {
			this.breastFeeding = 0;
		} 
		if (percentOfBreastFeeding == null || percentOfBreastFeeding < 0.0
				|| percentOfBreastFeeding > 1.0) {
			this.percentOfBreastFeeding = 0.0;
		} 
		if (bloodsugar == null || bloodsugar < 1.0) {
			this.bloodsugar = 4.5;
		} 
		if (systolic == null || systolic < 50) {
			this.systolic = 120;
		} 
		if (diastolic == null || diastolic < 30) {
			this.diastolic = 80;
		} 
		
		if (cholesterol == null) {
			this.cholesterol = 4.5;
		} 
		if (TG == null) {
			this.TG = 2.0;
		} 
		if (isDM == null) {
			this.isDM = false;
		} 
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getLaborLevel() {
		return laborLevel;
	}

	public void setLaborLevel(Integer laborLevel) {
		this.laborLevel = laborLevel;
	}

	public Integer getPregnancy() {
		return pregnancy;
	}

	public void setPregnancy(Integer pregnancy) {
		this.pregnancy = pregnancy;
	}

	public Integer getBreastFeeding() {
		return breastFeeding;
	}

	public void setBreastFeeding(Integer breastFeeding) {
		this.breastFeeding = breastFeeding;
	}

	public Double getPercentOfBreastFeeding() {
		return percentOfBreastFeeding;
	}

	public void setPercentOfBreastFeeding(Double percentOfBreastFeeding) {
		this.percentOfBreastFeeding = percentOfBreastFeeding;
	}

	public Double getBloodsugar() {
		return bloodsugar;
	}

	public void setBloodsugar(Double bloodsugar) {
		this.bloodsugar = bloodsugar;
	}

	public Integer getSystolic() {
		return systolic;
	}

	public void setSystolic(Integer systolic) {
		this.systolic = systolic;
	}

	public Integer getDiastolic() {
		return diastolic;
	}

	public void setDiastolic(Integer diastolic) {
		this.diastolic = diastolic;
	}

	public Double getCholesterol() {
		return cholesterol;
	}

	public void setCholesterol(Double cholesterol) {
		this.cholesterol = cholesterol;
	}

	public Double getTG() {
		return TG;
	}

	public void setTG(Double TG) {
		this.TG = TG;
	}

	public Boolean getIsRenalInadequacy() {
		return isRenalInadequacy;
	}

	public void setIsRenalInadequacy(Boolean isRenalInadequacy) {
		this.isRenalInadequacy = isRenalInadequacy;
	}

	public Boolean getIsDM() {
		return isDM;
	}

	public void setIsDM(Boolean isDM) {
		this.isDM = isDM;
	}

}
