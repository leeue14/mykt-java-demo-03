/**
 * 
 */
package com.carelinker.nes2.algorithm;

/**
 * @author fengchu
 * 
 */
public abstract class AbstractDailyEnergyCalculator {
	protected double height; // 身高
	protected double weight; // 体重
	protected int age; // 年龄
	protected int gender; // 性别
	protected int laborLevel; // 劳动强度

	protected int pregnancy; // 是否怀孕
	protected int breastFeeding = NesConstants.BREAST_FEEDING_0; // 哺乳期
	protected double percentOfBreastFeeding = 0.0; // 母乳比例

	public AbstractDailyEnergyCalculator(InputParams inputParams) {

		this.height = inputParams.getHeight();
		this.weight = inputParams.getWeight();
		this.age = inputParams.getAge();
		this.gender = inputParams.getGender();
		this.laborLevel = inputParams.getLaborLevel();
		this.pregnancy = inputParams.getPregnancy();
		this.breastFeeding = inputParams.getBreastFeeding();
		this.percentOfBreastFeeding = inputParams.getPercentOfBreastFeeding();

	}

	public abstract double calDailyEnergy();
}
