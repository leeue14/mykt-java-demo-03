/**
 * 
 */
package com.carelinker.nes2.algorithm;


/**
 * @author fengchu
 * 
 */
public class NesUtils {

	/**
	 * 参数命名说明 CC-correction coefficient,修正系数; LIC-labor intensity
	 * coefficient,劳动强度系数;DLCC-diabetes level correction coefficient,糖尿病程度修正系数
	 */

	public static double[][] tableForStandardWeightOfAge5To17 = {
			// 男 女 //年龄
			// a b a b
			{ 0.386, 23.699, 0.377, 22.75 }, // 5
			{ 0.461, 32.382, 0.458, 32.079 }, // 6
			{ 0.513, 38.878, 0.508, 38.367 }, // 7
			{ 0.592, 48.804, 0.561, 45.006 }, // 8
			{ 0.687, 61.39, 0.652, 56.992 }, // 9
			{ 0.752, 70.461, 0.73, 68.091 }, // 10
			{ 0.782, 75.106, 0.803, 78.846 }, // 11
			{ 0.783, 75.642, 0.796, 76.934 }, // 12
			{ 0.815, 81.348, 0.655, 54.234 }, // 13
			{ 0.832, 83.695, 0.594, 43.264 }, // 14
			{ 0.766, 70.989, 0.56, 37.002 }, // 15
			{ 0.656, 51.822, 0.578, 39.057 }, // 16
			{ 0.672, 53.642, 0.598, 42.339 } };// 17

	public static double[][] tableForLICAge1To17 = {
			// 男 女
			// 轻 中 重 轻 中 重 年龄
			{ 82.8, 91.4, 0, 82.8, 97.1, 0 }, // 1
			{ 84.3, 93.1, 0, 84.6, 99.2, 0 }, // 2
			{ 85.2, 94, 0, 85.4, 100.1, 0 }, // 3
			{ 81.3, 89.7, 0, 82.6, 96.9, 0 }, // 4
			{ 76.4, 84.3, 0, 78, 91.4, 0 }, // 5
			{ 74.7, 84.3, 93.9, 73.7, 81, 93.3 }, // 6
			{ 70.8, 79.9, 89, 70.1, 77.1, 88.8 }, // 7
			{ 68.1, 76.9, 85.7, 66.7, 73.3, 84.5 }, // 8
			{ 64.9, 73.3, 81.6, 63.4, 69.7, 80.3 }, // 9
			{ 62.8, 70.9, 79, 57.7, 63.5, 73.1 }, // 10
			{ 59.1, 66.8, 74.4, 53, 58.3, 67.2 }, // 11
			{ 56.1, 66.3, 70.6, 49.3, 54.3, 62.5 }, // 12
			{ 51.7, 58.3, 65, 45.6, 50.1, 57.7 }, // 13
			{ 51.7, 58.3, 65, 45.6, 50.1, 57.7 }, // 14
			{ 51.7, 58.3, 65, 45.6, 50.1, 57.7 }, // 15
			{ 48.2, 54.2, 61.7, 40.5, 46.1, 51.7 }, // 16
			{ 48.2, 54.2, 61.7, 40.5, 46.1, 51.7 } }; // 17

	public static double[][] tableForRateOfThreeMajorNutrientsAndWeightOfFibre = {
			// cabor protein fat fibre
			{ 0.52, 0.18, 0.30, 25 }, // 0 正常体重
			{ 0.55, 0.20, 0.25, 30 }, // 1 肥胖
			{ 0.50, 0.15, 0.35, 20 }, // 2 消瘦
			{ 0.55, 0.17, 0.28, 25 }, // 3 6.1-7.0 糖尿病等级1级
			{ 0.53, 0.18, 0.29, 25 }, // 4 7.1-10.0 糖尿病等级2级 1餐低GI
			{ 0.51, 0.19, 0.30, 30 }, // 5 10.1-12.0糖尿病等级3级 2餐低GI
			{ 0.48, 0.20, 0.32, 30 }, // 6 12.1-13.7糖尿病等级4级 3餐低GI
			{ 0.48, 0.20, 0.32, 30 }, // 7 13.8-16.0糖尿病等级5级 3餐低GI
			{ 0.60, 0.18, 0.22, 30 }, // 8 高胆固醇
			{ 0.60, 0.20, 0.20, 30 }, // 9 高三酰甘油
			{ 0.66, 0.08, 0.26, 30 }, // 10 肾功能不全
			{ 0.57, 0.18, 0.25, 30 }, // 11 高血压I 级
			{ 0.57, 0.18, 0.25, 30 }, // 12 高血压II 级
			{ 0.57, 0.18, 0.25, 30 } };// 13 高血压III 级

	/**
	 * 计算理想体重
	 * 
	 * @author fengchu
	 * @Time 2015年4月28日 下午1:54:14
	 * @Title calStandardWeight
	 * @param height
	 * @param age
	 * @param gender
	 * @return Double
	 * @throws
	 * @exception
	 */
	public static Double calStandardWeight(Double height, int gender) {

		double standardWeight = 0.0;
		if (gender == 1) {
			standardWeight = height - 105;
		} else {
			standardWeight = height - 105;
		}
		if (standardWeight < 50) {
			standardWeight = 50;
		}
		return standardWeight;

	}

	public static Double calBMI(double height, double weight) {
		return weight / height / height * 10000;
	}

	public static Double calLICForNormalPerson(int laborIntensity) {

		if (laborIntensity == NesConstants.LI_BED_REST) {
			return 1.5;
		}
		if (laborIntensity == NesConstants.LI_LIGHT) {
			return 1.5;
		}
		if (laborIntensity == NesConstants.LI_MEDIUM) {
			return 1.75;
		}
		if (laborIntensity == NesConstants.LI_HIGH) {
			return 2.0;
		}

		return 1.5;
	}

	public static double calBaseDailyEnergyForNormalPerson(
			double standardWeight, int age, int gender) {

		if (age < 18) {
			return standardWeight;
		}

		if (18 <= age && age < 30) {
			if (gender == 1) {
				return 24 * standardWeight;
			} else {
				return 23.6 * standardWeight;
			}
		}

		if (30 <= age && age < 50) {
			if (gender == 1) {
				return 22.3 * standardWeight;
			} else {
				return 21.7 * standardWeight;
			}
		}
		if (50 <= age && age < 65) {
			if (gender == 1) {
				return 21.5 * standardWeight;
			} else {
				return 20.7 * standardWeight;
			}
		}
		if (65 <= age && age < 80) {
			if (gender == 1) {
				return 21.4 * standardWeight;
			} else {
				return 20.1 * standardWeight;
			}
		}

		if (80 <= age) {
			if (gender == 1) {
				return 21.5 * standardWeight;
			} else {
				return 20.1 * standardWeight;
			}
		}

		return 0.0;
	}

	public static double calEnergyCCForDMPerson(int laborIntensity, double BMI,
			boolean isBsControlInstability) {
		if (BMI < 18.5) {
			if (isBsControlInstability) {
				if (laborIntensity == NesConstants.LI_BED_REST) {
					return 25;
				}
				if (laborIntensity == NesConstants.LI_LIGHT) {
					return 30;
				}
				if (laborIntensity == NesConstants.LI_MEDIUM) {
					return 35;
				}
				if (laborIntensity == NesConstants.LI_HIGH) {
					return 40;
				}
			} else {
				if (laborIntensity == NesConstants.LI_BED_REST) {
					return 30;
				}
				if (laborIntensity == NesConstants.LI_LIGHT) {
					return 35;
				}
				if (laborIntensity == NesConstants.LI_MEDIUM) {
					return 40;
				}
				if (laborIntensity == NesConstants.LI_HIGH) {
					return 45;
				}
			}
		}

		if (18.5 <= BMI && BMI < 24) {
			if (isBsControlInstability) {
				if (laborIntensity == NesConstants.LI_BED_REST) {
					return 25;
				}
				if (laborIntensity == NesConstants.LI_LIGHT) {
					return 27.5;
				}
				if (laborIntensity == NesConstants.LI_MEDIUM) {
					return 30;
				}
				if (laborIntensity == NesConstants.LI_HIGH) {
					return 35;
				}
			} else {
				if (laborIntensity == NesConstants.LI_BED_REST) {
					return 25;
				}
				if (laborIntensity == NesConstants.LI_LIGHT) {
					return 30;
				}
				if (laborIntensity == NesConstants.LI_MEDIUM) {
					return 35;
				}
				if (laborIntensity == NesConstants.LI_HIGH) {
					return 40;
				}
			}

		}
		if (24 <= BMI && BMI < 28) {
			if (isBsControlInstability) {
				if (laborIntensity == NesConstants.LI_BED_REST) {
					return 20;
				}
				if (laborIntensity == NesConstants.LI_LIGHT) {
					return 25;
				}
				if (laborIntensity == NesConstants.LI_MEDIUM) {
					return 27.5;
				}
				if (laborIntensity == NesConstants.LI_HIGH) {
					return 30;
				}
			} else {
				if (laborIntensity == NesConstants.LI_BED_REST) {
					return 20;
				}
				if (laborIntensity == NesConstants.LI_LIGHT) {
					return 25;
				}
				if (laborIntensity == NesConstants.LI_MEDIUM) {
					return 30;
				}
				if (laborIntensity == NesConstants.LI_HIGH) {
					return 35;
				}
			}

		}

		if (28 <= BMI) {

			if (laborIntensity == NesConstants.LI_BED_REST) {
				return 20;
			}
			if (laborIntensity == NesConstants.LI_LIGHT) {
				return 25;
			}
			if (laborIntensity == NesConstants.LI_MEDIUM) {
				return 25;
			}
			if (laborIntensity == NesConstants.LI_HIGH) {
				return 30;
			}

		}

		return 0;
	}

	public static int calDiabetesLevel(double bloodsugar) {
		if (6.1 <= bloodsugar && bloodsugar <= 7.0) {
			return NesConstants.DIABETES_LEVEL_1;
		}
		if (7.1 <= bloodsugar && bloodsugar <= 10.0) {
			return NesConstants.DIABETES_LEVEL_2;
		}
		if (10.1 <= bloodsugar && bloodsugar <= 12.0) {
			return NesConstants.DIABETES_LEVEL_3;
		}
		if (12.1 <= bloodsugar && bloodsugar <= 13.7) {
			return NesConstants.DIABETES_LEVEL_4;
		}
		if (13.8 <= bloodsugar) {
			return NesConstants.DIABETES_LEVEL_5;
		}
		return NesConstants.DIABETES_LEVEL_0;
	}

	public static double calDLCC(int diabetesLevel, boolean isPregnant,
			int breastFeeding) {
		if (isPregnant) {
			return 1;
		}
		if (breastFeeding != NesConstants.BREAST_FEEDING_0) {
			return 1;
		}
		switch (diabetesLevel) {
		case NesConstants.DIABETES_LEVEL_0:

			return 1;
		case NesConstants.DIABETES_LEVEL_1:

			return 1;
		case NesConstants.DIABETES_LEVEL_2:

			return 0.95;
		case NesConstants.DIABETES_LEVEL_3:

			return 0.9;
		case NesConstants.DIABETES_LEVEL_4:

			return 0.85;
		case NesConstants.DIABETES_LEVEL_5:

			return 0.85;
		}
		return 1;
	}

	public static double calActualDailyEnergy(double dailyEnergy, double DLCC) {
		return dailyEnergy * DLCC;
	}

	public static int calMealCount(int diabetesLevel) {
		switch (diabetesLevel) {
		case NesConstants.DIABETES_LEVEL_0:

			return 3;
		case NesConstants.DIABETES_LEVEL_1:

			return 4;
		case NesConstants.DIABETES_LEVEL_2:

			return 4;
		case NesConstants.DIABETES_LEVEL_3:

			return 5;
		case NesConstants.DIABETES_LEVEL_4:

			return 5;
		case NesConstants.DIABETES_LEVEL_5:

			return 5;
		}
		return 0;
	}

	public static int calMealCountType(int diabetesLevel) {
		switch (diabetesLevel) {
		case NesConstants.DIABETES_LEVEL_0:

			return NesConstants.MEAL_COUNT_3;
		case NesConstants.DIABETES_LEVEL_1:
			return NesConstants.MEAL_COUNT_4_BREAKFAST;
		case NesConstants.DIABETES_LEVEL_2:
			return NesConstants.MEAL_COUNT_4_BREAKFAST;
		case NesConstants.DIABETES_LEVEL_3:
			return NesConstants.MEAL_COUNT_5_BREAKFAST_SUPPER;
		case NesConstants.DIABETES_LEVEL_4:

			return NesConstants.MEAL_COUNT_5_BREAKFAST_SUPPER;
		case NesConstants.DIABETES_LEVEL_5:

			return NesConstants.MEAL_COUNT_5_BREAKFAST_SUPPER;
		}
		return 0;
	}

	public static double[] calEvaluationValueCC(int diabetesLevel) {

		double[] cc = new double[3];

		switch (diabetesLevel) {
		case NesConstants.DIABETES_LEVEL_0:
			cc[0] = 0.9;
			cc[1] = 0.1;
			cc[2] = 0.0;
			break;
		case NesConstants.DIABETES_LEVEL_1:
			cc[0] = 0.850;
			cc[1] = 0.100;
			cc[2] = 0.050;
			break;

		case NesConstants.DIABETES_LEVEL_2:
			cc[0] = 0.825;
			cc[1] = 0.100;
			cc[2] = 0.075;
			break;

		case NesConstants.DIABETES_LEVEL_3:
			cc[0] = 0.800;
			cc[1] = 0.100;
			cc[2] = 0.100;
			break;

		case NesConstants.DIABETES_LEVEL_4:
			cc[0] = 0.775;
			cc[1] = 0.100;
			cc[2] = 0.125;
			break;

		case NesConstants.DIABETES_LEVEL_5:
			cc[0] = 0.775;
			cc[1] = 0.100;
			cc[2] = 0.125;
			break;
		}

		return cc;
	}

	public static int calHypertensionLevel(int systolic, int diastolic) {

		int systolic_status, diastolic_status, status;

		if (systolic <= 89) {
			systolic_status = NesConstants.HYPERTENSION_LEVEL_0;
		} else if (systolic >= 90 && systolic <= 139) {
			systolic_status = NesConstants.HYPERTENSION_LEVEL_0;
		} else if (systolic >= 140 && systolic <= 159) {
			systolic_status = NesConstants.HYPERTENSION_LEVEL_1;
		} else if (systolic >= 160 && systolic <= 179) {
			systolic_status = NesConstants.HYPERTENSION_LEVEL_2;
		} else {
			systolic_status = NesConstants.HYPERTENSION_LEVEL_3;
		}

		if (diastolic <= 59) {
			diastolic_status = NesConstants.HYPERTENSION_LEVEL_0;
		} else if (diastolic >= 60 && diastolic <= 89) {
			diastolic_status = NesConstants.HYPERTENSION_LEVEL_0;
		} else if (diastolic >= 90 && diastolic <= 99) {
			diastolic_status = NesConstants.HYPERTENSION_LEVEL_1;
		} else if (diastolic >= 100 && diastolic <= 109) {
			diastolic_status = NesConstants.HYPERTENSION_LEVEL_2;
		} else {
			diastolic_status = NesConstants.HYPERTENSION_LEVEL_3;
		}

		if (systolic_status >= diastolic_status) {
			status = systolic_status;
		} else {
			status = diastolic_status;
		}

		if (systolic_status == NesConstants.HYPERTENSION_LEVEL_0
				&& diastolic_status == NesConstants.HYPERTENSION_LEVEL_0) {
			status = NesConstants.HYPERTENSION_LEVEL_0;
		}

		if (systolic_status == NesConstants.HYPERTENSION_LEVEL_0
				&& diastolic_status == NesConstants.HYPERTENSION_LEVEL_0) {
			status = NesConstants.HYPERTENSION_LEVEL_0;
		}
		return status;
	}

	public static int calWeightLevel(double BMI) {
		if (BMI < 18.5) {
			return NesConstants.WEIGHT_LEVEL_THIN;
		} else if (18.5 <= BMI && BMI < 25) {
			return NesConstants.WEIGHT_LEVEL_NORMAL;
		} else {
			return NesConstants.WEIGHT_LEVEL_FAT;
		}
	}

	public static int calMaxGI(int diabetesLevel,
			Boolean isBsControlInstability, double BMI) {

		if (!isBsControlInstability && BMI < 28) {
			return 120;
		}

		switch (diabetesLevel) {
		case NesConstants.DIABETES_LEVEL_0:
			if (BMI >= 28) {
				return 120;
			} else {
				return 52;
			}

		case NesConstants.DIABETES_LEVEL_1:
			return 70;
		case NesConstants.DIABETES_LEVEL_2:
			return 70;
		case NesConstants.DIABETES_LEVEL_3:
			return 60;
		case NesConstants.DIABETES_LEVEL_4:
			return 52;
		case NesConstants.DIABETES_LEVEL_5:
			return 52;
		default:
			return 120;
		}
	}

	public static int calStapleFoodCountOfLowGI(int diabetesLevel) {
		switch (diabetesLevel) {
		case NesConstants.DIABETES_LEVEL_0:
			return 0;
		case NesConstants.DIABETES_LEVEL_1:
			return 1;
		case NesConstants.DIABETES_LEVEL_2:
			return 1;
		case NesConstants.DIABETES_LEVEL_3:
			return 2;
		case NesConstants.DIABETES_LEVEL_4:
			return 3;
		case NesConstants.DIABETES_LEVEL_5:
			return 3;
		default:
			return 0;
		}
	}

	public static double[] calMealRate(int mealCountType) {

		double[] mealRate = new double[3];

		switch (mealCountType) {
		case NesConstants.MEAL_COUNT_3:
			mealRate[0] = 0.3;
			mealRate[1] = 0.4;
			mealRate[2] = 0.3;
			break;
		case NesConstants.MEAL_COUNT_4_BREAKFAST:
			mealRate[0] = 0.35;
			mealRate[1] = 0.35;
			mealRate[2] = 0.3;
			break;
		case NesConstants.MEAL_COUNT_5_BREAKFAST_SUPPER:
			mealRate[0] = 0.4;
			mealRate[1] = 0.35;
			mealRate[2] = 0.25;
			break;
		default:
			break;
		}
		return mealRate;
	}

	public static boolean isHTG(Double HTG) {
		if (HTG == null) {
			return false;
		}
		if (HTG > 2.3) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isHighCholesterol(Double cholesterol) {
		if (cholesterol == null) {
			return false;
		}
		if (cholesterol > 5.2) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isBsControlInstability(Double bloodsugar) {
		if (bloodsugar == null) {
			return false;
		}
		if (bloodsugar >= 7.0) {
			return true;
		} else {
			return false;
		}
	}

	public static String checkInputParams(InputParams inputParams) {
		String content = "";
		String defaultStr = "";
		boolean flag = false;

		if (inputParams.getIsDM() == null) {
			flag = true;
			defaultStr += "不是糖尿病患者，";
		}

		if (inputParams.getHeight() == null) {
			flag = true;
			defaultStr += "身高165厘米，";
		}

		if (inputParams.getWeight() == null) {
			flag = true;
			defaultStr += "体重50千克，";
		}

		if (inputParams.getAge() == null) {
			flag = true;
			defaultStr += "年龄30岁，";
		}

		if (inputParams.getGender() == null) {
			flag = true;
			defaultStr += "性别男，";
		}

		if (inputParams.getLaborLevel() == null) {
			flag = true;
			defaultStr += "劳动强度轻，";
		}

		if (inputParams.getBloodsugar() == null) {
			flag = true;
			defaultStr += "空腹血糖4.5，";
		}

		if (inputParams.getSystolic() == null) {
			flag = true;
			defaultStr += "收缩压120，";
		}

		if (inputParams.getDiastolic() == null) {
			flag = true;
			defaultStr += "舒张压80，";
		}

		if (inputParams.getCholesterol() == null) {
			flag = true;
			defaultStr += "胆固醇4.5，";
		}

		if (inputParams.getTG() == null) {
			flag = true;
			defaultStr += "三酰甘油2.0，";
		}

		if (flag) {
			content = "您的信息不全，系统将按以下默认值计算食谱：";
			return content + defaultStr + "若想得到精确结果，请完善健康档案！";
		} else {
			return "";
		}

	}
}
