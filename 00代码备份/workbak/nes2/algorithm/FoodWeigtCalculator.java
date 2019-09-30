/**
 * 
 */
package com.carelinker.nes2.algorithm;

import com.carelinker.common.utils.MathUtils;
import com.carelinker.common.utils.PrintUtils;
import com.carelinker.nes2.mongo.entity.DishEntity;
import com.carelinker.nes2.mongo.entity.FoodEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fengchu
 * 
 */
public class FoodWeigtCalculator {

	private static final Double END_CONDITION = 0.995;

	// private MiddleParams middleParams;
	// private InputParams inputParams;
	// private MiddleRecipe recipe;

	double endConditionEnergy;

	double maxVectorEvaluationValue = 0.0;

	private double[] currentVector = new double[3];
	private double[] targetVector = new double[3];
	private double[] cc;

	FoodWeigtCalculator() {

	}

	private List<String> steps = new ArrayList<>();

	private List<FoodEntity> foodList;

	/**
	 * 计算实际重量和能量
	 * 
	 * @author fengchu
	 * @Time 2015年5月22日 下午2:52:45
	 * @Title calActualWeightAndEnergy
	 * @param meal
	 *            void
	 * @throws
	 * @exception
	 */
	public boolean calActualWeightAndEnergy(MiddleMeal meal,
			MiddleRecipe recipe, MiddleParams middleParams) {
		// 计算起点向量
		this.calStartVector(meal);
		// 初始化参数
		this.initCalForActualWeightAndEnergy(meal);
		// 设置食材最大值
		this.calMaxWeight(meal, recipe.getEnergy());
		// 计算每个食材的重量
		this.calBacktracking(meal);
		// 计算食材的实际能量及其它参数
		this.calFoodParams(meal, middleParams.getResetRate());
		// 计算这一餐实际的三大营养质量
		return this.calActualThreeNurients(meal, recipe);

	}

	/**
	 * 计算起点向量
	 * 
	 * @author fengchu
	 * @Time 2015年5月22日 下午2:54:25
	 * @Title calStartVector
	 * @param meal
	 *            void
	 * @throws
	 * @exception
	 */
	private void calStartVector(MiddleMeal meal) {

		foodList = new ArrayList<FoodEntity>();
		List<DishEntity> dishes = meal.getDishes();

		for (DishEntity dishIt : dishes) {
			foodList.addAll(dishIt.getFoods());
		}

		double startCarboVector = 0.0;
		double startProteinVector = 0.0;
		double startFatVector = 0.0;
		double startFibreVector = 0.0;
		double startNum;

		PrintUtils.test("食物的个数：" + foodList.size());

		for (FoodEntity foodIt : foodList) {

			startNum = foodIt.getStartNum();
			startCarboVector += foodIt.getCarboG() * startNum;
			startProteinVector += foodIt.getProteinG() * startNum;
			startFatVector += foodIt.getFatG() * startNum;
			startFibreVector += foodIt.getFibreG() * startNum;
		}

		meal.setStartCarboVector(startCarboVector);
		meal.setStartProteinVector(startProteinVector);
		meal.setStartFatVector(startFatVector);
		meal.setStartFibreVector(startFibreVector);

		PrintUtils.test("StartCarboVector：" + startCarboVector);
		PrintUtils.test("StartProteinVector：" + startProteinVector);
		PrintUtils.test("StartFatVector：" + startFatVector);
		PrintUtils.test("StartFibreVector：" + startFibreVector);
	}

	// 初始化参数
	private void initCalForActualWeightAndEnergy(MiddleMeal meal) {

		foodList = new ArrayList<FoodEntity>();
		List<DishEntity> dishes = meal.getDishes();

		for (DishEntity dishIt : dishes) {
			foodList.addAll(dishIt.getFoods());
		}

		maxVectorEvaluationValue = 0.0;
		endConditionEnergy = END_CONDITION * meal.getEnergy();
		currentVector[0] = meal.getStartCarboVector();
		currentVector[1] = meal.getStartProteinVector();
		currentVector[2] = meal.getStartFatVector();
		targetVector[0] = meal.getCarboG();
		targetVector[1] = meal.getProteinG();
		targetVector[2] = meal.getFatG();

	}

	int num = 0;

	private void calMaxWeight(MiddleMeal meal, double energy) {

		if (energy < 2000) {
			for (DishEntity dishIt : meal.getDishes()) {

				for (FoodEntity foodIt : dishIt.getFoods()) {
					foodIt.setMaxNum(foodIt.getMaxNum()
							- foodIt.getEnergyRange());
				}

			}
			return;
		}

		if (2000 <= energy && energy < 3000) {
			return;
		}

		int i = ((int) energy - 3000) / 400 + 1;

		for (DishEntity dishIt : meal.getDishes()) {

			for (FoodEntity foodIt : dishIt.getFoods()) {
				foodIt.setMaxNum(foodIt.getMaxNum() + i
						* foodIt.getEnergyRange());
			}

		}

	}

	private void calBacktracking(MiddleMeal meal) {
		// PrintUtil.print("第" + (++num) + "轮");
		// steps.add("第" + (++num) + "轮");
		double currentEnergy = currentVector[0] * 4 + currentVector[1] * 4
				+ currentVector[2] * 9;

		PrintUtils.test("当前能量： " + currentEnergy + "目标能量： "
				+ endConditionEnergy);
		// steps.add(("当前能量： " + currentEnergy + "目标能量： " +
		// endConditionEnergy));
		if (currentEnergy > endConditionEnergy * 0.98) {
			maxVectorEvaluationValue = 0.0;
			num = 0;
			PrintUtils.test("结束");
			return;
		}
		double tempVectorEvaluationValue;
		Double[] weightIncrementVector;
		double[] candidateVectordefinitized = new double[3];

		FoodEntity candidateFood = null;

		for (FoodEntity foodIt : foodList) {
			if (foodIt.getWeight() < foodIt.getMaxNum()) {
				weightIncrementVector = foodIt.getWeightIncrementVector();
				double[] candidateVector = {
						weightIncrementVector[0] + currentVector[0],
						weightIncrementVector[1] + currentVector[1],
						weightIncrementVector[2] + currentVector[2] };
				tempVectorEvaluationValue = this.calVectorEvaluationValue(
						foodIt, candidateVector);
				if (tempVectorEvaluationValue > maxVectorEvaluationValue) {
					maxVectorEvaluationValue = tempVectorEvaluationValue;
					candidateVectordefinitized = candidateVector;
					candidateFood = foodIt;
				}

				// PrintUtil.print("当前重量：" + foodIt.getWeight() + "最大重量："
				// + foodIt.getMaxNum() + "该食材评价值："
				// + tempVectorEvaluationValue + "本轮最大评价值："
				// + maxVectorEvaluationValue + "++++"
				// + foodIt.getFoodName());
				// steps.add("当前重量：" + foodIt.getWeight() + "最大重量："
				// + foodIt.getMaxNum() + "该食材评价值："
				// + tempVectorEvaluationValue + "本轮最大评价值："
				// + maxVectorEvaluationValue + "++++"
				// + foodIt.getFoodName());
			}
		}

		maxVectorEvaluationValue = 0.0;
		int i = foodList.indexOf(candidateFood);

		foodList.get(i)
				.setWeight(
						foodList.get(i).getWeight()
								+ foodList.get(i).getIncrementNum());

		// PrintUtil.print("选定增加的食物: " + foodList.get(i).getFoodName()
		// + "增加后重量为： " + foodList.get(i).getWeight());
		currentVector = candidateVectordefinitized;
		calBacktracking(meal);

	}

	private double calVectorEvaluationValue(FoodEntity food,
			double[] candidateVector) {

		double cos = MathUtils.calCos(candidateVector, targetVector);
		// PrintUtil.print("cos" + cos);
		return cos;
		// return cc[0] * cos;
		// return cc[0] * cos + cc[1] * food.getFibreG() + cc[2]
		// * food.getGIAdjusted();
	}

	private void calFoodParams(MiddleMeal meal, double resetRate) {
		List<DishEntity> dishList = meal.getDishes();
		List<FoodEntity> foodList = new ArrayList<FoodEntity>();

		for (DishEntity dishIt : dishList) {
			foodList.addAll(dishIt.getFoods());
		}

		for (FoodEntity foodIt : foodList) {
			resetRate = 1;
			foodIt.calTraceElements();
		}

		for (DishEntity dishIt : dishList) {
			this.calDishEnergy(dishIt);
		}
	}

	public void calDishEnergy(DishEntity dish) {
		double energy = 0;
		List<FoodEntity> foodList = dish.getFoods();
		for (FoodEntity foodIt : foodList) {
			energy += foodIt.getEnergy();
		}
		dish.setEnergy(energy);
	}

	private boolean calActualThreeNurients(MiddleMeal meal, MiddleRecipe recipe) {

		double actualCarboG = 0.0;
		double actualProteinG = 0.0;
		double actualFatG = 0.0;
		double actualEnergy = 0.0;
		double actualFibreG = 0.0;

		for (DishEntity dishIt : meal.getDishes()) {
			for (FoodEntity foodIt : dishIt.getFoods()) {
				actualCarboG += foodIt.getCarboG();
				actualProteinG += foodIt.getProteinG();
				actualFatG += foodIt.getFatG();
				actualEnergy += foodIt.getEnergy();
				actualFibreG += foodIt.getFibreG();
			}
		}

		steps = recipe.getSteps();
		switch (meal.getType()) {
		case "01":
			steps.add("早餐");
			break;
		case "03":
			steps.add("中餐");
			break;
		case "05":
			steps.add("晚餐");
			break;
		default:
			break;
		}

		double actualCarboGError = (actualCarboG - meal.getCarboG())
				/ meal.getCarboG();
		double actualProteinGError = (actualProteinG - meal.getProteinG())
				/ meal.getProteinG();
		double actualFatGError = (actualFatG - meal.getFatG()) / meal.getFatG();
		double actualEnergyError = (actualEnergy - meal.getEnergy())
				/ meal.getEnergy();

		steps.add("应有碳水化合物： " + meal.getCarboG());
		steps.add("实有碳水化合物： " + actualCarboG);
		steps.add("碳水化合物误差： " + actualCarboGError);

		steps.add("应有蛋白质： " + meal.getProteinG());
		steps.add("实有蛋白质： " + actualProteinG);
		steps.add("蛋白质误差： " + actualProteinGError);

		steps.add("应有脂肪： " + meal.getFatG());
		steps.add("实有脂肪： " + actualFatG);
		steps.add("脂肪误差： " + actualFatGError);

		steps.add("应有纤维素： " + meal.getFibreG());
		steps.add("实有纤维素： " + actualFibreG);
		steps.add("纤维素误差： " + actualEnergyError);

		steps.add("应有能量： " + meal.getEnergy());
		steps.add("实有能量： " + actualEnergy);
		steps.add("能量误差： " + actualEnergyError);

		PrintUtils.test(actualCarboGError);
		PrintUtils.test(actualProteinGError);
		PrintUtils.test(actualFatGError);
		PrintUtils.test(actualEnergyError);
		boolean flag = false;
		if (isErrorLargerThan10(actualCarboGError)) {

			flag = false;
		} else if (isErrorLargerThan10(actualProteinGError)) {

			flag = false;
		} else if (isErrorLargerThan10(actualFatGError)) {

			flag = false;
		} else if (isErrorLargerThan10(actualEnergyError)) {

			flag = false;
		} else {
			flag = true;
		}
		if (flag) {
			meal.setEnergy(actualEnergy);
			meal.setCarboG(actualCarboG);
			meal.setProteinG(actualProteinG);
			meal.setFatG(actualFatG);
			meal.setFibreG(actualFibreG);
		}
		return flag;
	}

	private boolean isErrorLargerThan10(double error) {

		if (Math.abs(error) > 0.1) {
			PrintUtils.test("isErrorLargerThan10");
			return true;
		} else {
			return false;
		}
	}
}
