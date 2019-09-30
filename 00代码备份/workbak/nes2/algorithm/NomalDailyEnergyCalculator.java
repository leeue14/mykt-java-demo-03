/**
 * 
 */
package com.carelinker.nes2.algorithm;


import com.carelinker.common.utils.PrintUtils;

/**
 * @author fengchu
 * 
 */
public class NomalDailyEnergyCalculator extends AbstractDailyEnergyCalculator {

	private boolean isDm;
	private boolean isBsControlInstability;

	public NomalDailyEnergyCalculator(InputParams inputParams,
			MiddleParams middleParams) {
		super(inputParams);
		this.isDm = inputParams.getIsDM();
		this.isBsControlInstability = middleParams.getIsBsControlInstability();
	}

	@Override
	public double calDailyEnergy() {

		// 计算理想体重
		double standardWeight = NesUtils.calStandardWeight(height, gender);
		PrintUtils.test("standardWeight: " + standardWeight);

		// 计算BMI
		double BMI = NesUtils.calBMI(height, weight);
		PrintUtils.test("BMI: " + BMI);

		double dailyEnergy;

		if (!this.isDm && BMI < 24) {
			double LIC = NesUtils.calLICForNormalPerson(laborLevel);
			// 计算每日基础能量

			double dailyBaseEnergy = NesUtils
					.calBaseDailyEnergyForNormalPerson(standardWeight, age,
							gender);
			PrintUtils.test("dailyBaseEnergy: " + dailyBaseEnergy);
			// 计算每日所需能量
			dailyEnergy = dailyBaseEnergy * LIC;

		} else {
			double energyCCForDMPerson = NesUtils.calEnergyCCForDMPerson(
					laborLevel, BMI, isBsControlInstability);
			dailyEnergy = energyCCForDMPerson * standardWeight;
		}

		// 如果处于妊娠期，增加200kcal
		if (pregnancy == NesConstants.PREGNANCY_0) {
			switch (pregnancy) {
			case NesConstants.PREGNANCY_EARLY:
				dailyEnergy += 50;
				break;
			case NesConstants.PREGNANCY_MIDDLE:
				dailyEnergy += 300;
				break;
			case NesConstants.PREGNANCY_LATE:
				dailyEnergy += 450;
				break;
			}
		}
		// 如果处于哺乳期，根据母乳喂养比例修正每日所需能量
		if (breastFeeding == NesConstants.BREAST_FEEDING_1_6_MONTHS) {
			dailyEnergy = dailyEnergy + percentOfBreastFeeding * 500;
		}
		if (breastFeeding == NesConstants.BREAST_FEEDING_ABOVE_6_MONTHS) {
			dailyEnergy = dailyEnergy + percentOfBreastFeeding * 575;
		}
		PrintUtils.test("dailyEnergy: " + dailyEnergy);

		return dailyEnergy;
	}
}
