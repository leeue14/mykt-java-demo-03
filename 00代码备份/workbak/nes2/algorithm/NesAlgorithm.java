/**
 * 
 */
package com.carelinker.nes2.algorithm;

import com.alibaba.fastjson.JSONObject;
import com.carelinker.common.utils.DateUtils;
import com.carelinker.common.utils.ProgramTimer;
import com.carelinker.common.utils.SpringContextHelper;
import com.carelinker.core.resource.AuthorizationFilter;
import com.carelinker.nes2.algorithm.type.DishId;
import com.carelinker.nes2.algorithm.type.LargeType;
import com.carelinker.nes2.mongo.entity.RecipeEntity;
import com.carelinker.nes2.service.IRecipeService;
import com.carelinker.nes2.vo.Dish;
import com.carelinker.nes2.vo.Meal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author fengchu
 * 
 */
public class NesAlgorithm {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationFilter.class);

	@Resource
	private IRecipeService recipeService = (IRecipeService) SpringContextHelper
			.getBean("recipeServiceImpl");;

	private ProgramTimer timer = new ProgramTimer("cal recipe", LOGGER);

	private MiddleRecipe recipe;

	private InputParams inputParams;

	private MiddleParams middleParams;

	private String patientId;

	private String date;

	public NesAlgorithm(InputParams inputParams, String patientId, String date) {
		this.patientId = patientId;
		this.date = date;
		this.inputParams = inputParams;
		this.middleParams = this.init();
	}

	public RecipeEntity calRecipe() {

		double dailyEnergy = this.calActualDailyEnergy();

		new DishSelectionSystem(recipe, inputParams, middleParams).excute();

		timer.stop();

		RecipeEntity recipeEntity = this.castToRecipe();

		recipeEntity.setPatientId(patientId);
		recipeEntity.setDate(date);
		recipeEntity.setStatus(1);
		if (dailyEnergy < 1200) {

			recipeEntity.setAttention("您所需能量太低，需经专业医师或营养师确定最终营养方案。");
		}

		return recipeEntity;
	}

	/**
	 * 初始化参数
	 */
	private MiddleParams init() {
		String attention = NesUtils.checkInputParams(inputParams);
		inputParams.CheckInputParams();
		int diabetesLevel = NesUtils.calDiabetesLevel(inputParams
				.getBloodsugar());
		MiddleParams middleParams = new MiddleParams();
		middleParams.setBMI(NesUtils.calBMI(inputParams.getHeight(),
				inputParams.getWeight()));
		middleParams.setIsBsControlInstability(NesUtils
				.isBsControlInstability(inputParams.getBloodsugar()));
		middleParams.setDiabetesLevel(diabetesLevel);
		middleParams.setHypertensionLevel(NesUtils.calHypertensionLevel(
				inputParams.getSystolic(), inputParams.getDiastolic()));
		middleParams.setWeightLevel(NesUtils.calWeightLevel(middleParams
				.getBMI()));
		middleParams
				.setMaxGI(NesUtils.calMaxGI(diabetesLevel,
						middleParams.getIsBsControlInstability(),
						middleParams.getBMI()));
		middleParams.setStapleFoodCountOfLowGI(NesUtils
				.calStapleFoodCountOfLowGI(diabetesLevel));
		middleParams.setMealCount(NesUtils.calMealCount(diabetesLevel));
		middleParams.setMealCountType(NesUtils.calMealCountType(diabetesLevel));
		middleParams.setIsHTG(NesUtils.isHTG(inputParams.getTG()));

		middleParams.setIsHighCholesterol(NesUtils
				.isHighCholesterol(inputParams.getCholesterol()));
		recipe = new MiddleRecipe();
		recipe.setAttention(attention);
		if (middleParams.getIsHighCholesterol()) {
			recipe.setChoMG(200.0);
		} else {
			recipe.setChoMG(300.0);
		}
		recipe.setMUFARate(0.12);
		recipe.setPUFARate(0.1);

		if (inputParams.getIsDM() || inputParams.getBloodsugar() != null) {
			recipe.setShowGI(1);
		} else {
			recipe.setShowGI(0);
		}

		RecipeEntity recipeEntity;

		int OperationDays = DateUtils.getBetweenDays(
				DateUtils.formatStringToDate(date, "yyyyMMdd"), new Date()) - 1;

		int days = 2;
		String day;
		Set<String> dishNameSet = middleParams.getSelectedDishes();
		for (int i = days; i > 0; i--) {
			day = DateUtils.formatDateStrToString(
					DateUtils.getBefore(i + OperationDays, null, null, null),
					"yyyy-MM-dd", "yyyyMMdd");

			recipeEntity = recipeService.getByPatientIdAndDateAllowNull(
					patientId, day);

			if (recipeEntity != null) {
				dishNameSet.addAll(this.getDishes(recipeEntity.getBreakfast()));
				dishNameSet.addAll(this.getDishes(recipeEntity.getLunch()));
				dishNameSet.addAll(this.getDishes(recipeEntity.getSupper()));
			}

		}

		return middleParams;
	}

	private Set<String> getDishes(Meal meal) {
		Set<String> dishNameSet = new HashSet<String>();
		if (meal != null) {
			for (Dish dishIt : meal.getDishes()) {
				if (!dishIt.getLargeType().equals(LargeType.MILK.getId())
						&& !dishIt.getLargeType().equals(
								LargeType.FRUIT.getId())
						&& !dishIt.getId().equals(DishId.GRAIN_RICE)
						&& !dishIt.getId().equals(DishId.BOILED_EGG1)
						&& !dishIt.getId().equals(DishId.BOILED_EGG2)) {
					dishNameSet.add(dishIt.getDishName());
				}

			}
		}
		return dishNameSet;
	}

	private double calActualDailyEnergy() {

		AbstractDailyEnergyCalculator calculator = new NomalDailyEnergyCalculator(
				inputParams, middleParams);

		double dailyEnergy = calculator.calDailyEnergy();

		recipe.setEnergy(dailyEnergy);

		return dailyEnergy;
	}

	private RecipeEntity castToRecipe() {

		JSONObject.parseObject(JSONObject.toJSONString(recipe), RecipeEntity.class);

		RecipeEntity recipeEntity1 = JSONObject.parseObject(JSONObject.toJSONString(recipe), RecipeEntity.class);

		recipeEntity1.formatDecimal();

//		RecipeEntity recipeEntity = new RecipeEntity();
//		recipeEntity.setCarboG(recipe.getCarboG());
//		recipeEntity.setCarboRate(recipe.getCarboRate());
//		recipeEntity.setChoMG(recipe.getChoMG());
//		recipeEntity.setEnergy(recipe.getEnergy());
//		recipeEntity.setFatG(recipe.getFatG());
//		recipeEntity.setFatRate(recipe.getFatRate());
//		recipeEntity.setFibreG(recipe.getFibreG());
//		recipeEntity.setSaltG(recipe.getSaltG());
//		recipeEntity.setMUFARate(recipe.getMUFARate());
//		recipeEntity.setPUFARate(recipe.getPUFARate());
//		recipeEntity.setProteinG(recipe.getProteinG());
//		recipeEntity.setProteinRate(recipe.getProteinRate());
//		recipeEntity.setShowGI(recipe.getShowGI());
//		recipeEntity.setAttention(recipe.getAttention());
//
//		Meal breakfast = new Meal();
//		breakfast.set

		return recipeEntity1;
	}

}
