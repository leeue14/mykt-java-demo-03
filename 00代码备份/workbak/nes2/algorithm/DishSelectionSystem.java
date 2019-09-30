/**
 *
 */
package com.carelinker.nes2.algorithm;

import com.carelinker.common.utils.PrintUtils;
import com.carelinker.common.utils.SpringContextHelper;
import com.carelinker.nes2.algorithm.type.*;
import com.carelinker.nes2.mongo.entity.DishEntity;
import com.carelinker.nes2.mongo.entity.FoodEntity;
import com.carelinker.nes2.service.IDishService;
import com.carelinker.nes2.service.IFoodService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fengchu
 */
public class DishSelectionSystem {

    private static IDishService dishService = (IDishService) SpringContextHelper
            .getBean("dishServiceImpl");

    private static IFoodService foodService = (IFoodService) SpringContextHelper
            .getBean("foodServiceImpl");

    private static int LOW_GI = 52;

    FoodWeigtCalculator cal = new FoodWeigtCalculator();

    private MiddleParams middleParams;
    private InputParams inputParams;
    private MiddleRecipe recipe;

    private double[] mealRate = new double[3];

    private boolean lowGIStaple = false;
    private int maxGI;
    private double oilWeight;
    private double fatWeightWithoutOil;
    private boolean isEggUsed = false;

    DishSelectionSystem(MiddleRecipe recipe, InputParams inputParams,
                        MiddleParams middleParams) {
        if (middleParams.getIsHighCholesterol()) {
            this.isEggUsed = false;
        }
        this.maxGI = middleParams.getMaxGI();
        this.recipe = recipe;
        this.inputParams = inputParams;
        this.middleParams = middleParams;
    }

    public MiddleRecipe excute() {

        this.calOilWeight();
        this.calResetRate();

        // 确定三大营养素比例和纤维素克数，盐克数
        this.calRateOfThreeMajorNutrientsAndWeightOfFibreAndSalt();
        // 确定三大营养素质量
        this.calWeightOfThreeMajorNutrients();
        // 确定三餐比例
        this.calThreeMealsPercentage();
        // 确定三餐的初始能量和三大营养物质质量
        this.calEnergyAndWeightOfThreeMajorNutrientsAndFibreOfEachMeal();
        // 选定早餐
        PrintUtils.test("早餐开始");
        this.genBreakfast();
        this.resetEnergy(recipe.getBreakfast());
        // 修正中餐和晚餐的三大营养素质量，纤维素质量
        this.resetWeightOfThreeMajorNutrientsAndFibre();
        PrintUtils.test("早餐结束");
        PrintUtils.test("中餐开始");
        // 选定中餐
        this.genLunch();
        this.checkIsIncreased(recipe.getLunch());
        this.resetEnergy(recipe.getLunch());
        PrintUtils.test("中餐结束");
        PrintUtils.test("晚餐开始");
        // 选定晚餐
        this.genSupper();
        this.checkIsIncreased(recipe.getSupper());
        this.resetEnergy(recipe.getSupper());

        this.separateSideDishFromBreakfast();
        this.reCalRecipeParams();
        this.resetTotalEnergy();
        this.calSideMealGI();
        return recipe;
    }

    private void calResetRate() {

        this.middleParams.setResetRate(recipe.getEnergy() / 1800d);

        if (this.middleParams.getResetRate() < 1) {
            recipe.setEnergy(1800.0);
        }
    }

    private void calOilWeight() {

        if (2200 <= recipe.getEnergy()) {
            this.oilWeight = 25;
        } else if (1200 <= recipe.getEnergy() && recipe.getEnergy() < 2200) {
            this.oilWeight = 0.015 * (recipe.getEnergy() - 1200) + 10;
        } else {
            this.oilWeight = 10;
        }

    }

    private void calRateOfThreeMajorNutrientsAndWeightOfFibreAndSalt() {

        int i = 0;

        if (inputParams.getIsRenalInadequacy()) {
            i = 10;
        } else if (middleParams.getDiabetesLevel() == NesConstants.DIABETES_LEVEL_3) {
            i = 5;
        } else if (middleParams.getDiabetesLevel() == NesConstants.DIABETES_LEVEL_4) {
            i = 6;
        } else if (middleParams.getDiabetesLevel() == NesConstants.DIABETES_LEVEL_5) {
            i = 7;
        } else if (middleParams.getIsHTG()) {
            i = 9;
        } else if (middleParams.getIsHighCholesterol()) {
            i = 8;
        } else if (middleParams.getDiabetesLevel() == NesConstants.DIABETES_LEVEL_2) {
            i = 4;
        } else if (middleParams.getDiabetesLevel() == NesConstants.DIABETES_LEVEL_1) {
            i = 3;
        } else if (middleParams.getHypertensionLevel() == NesConstants.HYPERTENSION_LEVEL_1) {
            i = 11;
        } else if (middleParams.getHypertensionLevel() == NesConstants.HYPERTENSION_LEVEL_2) {
            i = 12;
        } else if (middleParams.getHypertensionLevel() == NesConstants.HYPERTENSION_LEVEL_3) {
            i = 13;
        } else if (middleParams.getWeightLevel() == NesConstants.WEIGHT_LEVEL_NORMAL) {
            i = 0;
        } else if (middleParams.getWeightLevel() == NesConstants.WEIGHT_LEVEL_FAT) {
            i = 1;
        } else if (middleParams.getWeightLevel() == NesConstants.WEIGHT_LEVEL_THIN) {
            i = 2;
        }

        String saltWeight;

        if (middleParams.getHypertensionLevel() == NesConstants.HYPERTENSION_LEVEL_1) {
            saltWeight = "3-5";
        } else if (middleParams.getHypertensionLevel() == NesConstants.HYPERTENSION_LEVEL_2) {
            saltWeight = "1-2";
        } else if (middleParams.getHypertensionLevel() == NesConstants.HYPERTENSION_LEVEL_3) {
            saltWeight = "0";
        } else {
            saltWeight = "3-5";
        }

        recipe.setCarboRate(NesUtils.tableForRateOfThreeMajorNutrientsAndWeightOfFibre[i][0]);
        recipe.setProteinRate(NesUtils.tableForRateOfThreeMajorNutrientsAndWeightOfFibre[i][1]);
        recipe.setFatRate(NesUtils.tableForRateOfThreeMajorNutrientsAndWeightOfFibre[i][2]);
        recipe.setFibreG(NesUtils.tableForRateOfThreeMajorNutrientsAndWeightOfFibre[i][3]);
        recipe.setSaltG(saltWeight);

        PrintUtils.test("确定三大营养素比例和纤维素，盐的质量：" + "\n" + "CarboRate: "
                + recipe.getCarboRate() + "\n" + "ProteinRate: "
                + recipe.getProteinRate() + "\n" + "FatRate: "
                + recipe.getFatRate() + "\n" + "FibreWeight: "
                + recipe.getFibreG() + "\n" + "SaltWeight: "
                + recipe.getSaltG() + "\n");
    }

    private void calWeightOfThreeMajorNutrients() {

        recipe.setCarboG(recipe.getEnergy() * recipe.getCarboRate() / 4);
        recipe.setProteinG(recipe.getEnergy() * recipe.getProteinRate() / 4);
        recipe.setFatG(recipe.getEnergy() * recipe.getFatRate() / 9);

        PrintUtils.test("三大营养素质量：\n" + "CarboWeight: " + recipe.getCarboG()
                + "\n" + "ProteinWeight: " + recipe.getProteinG() + "\n"
                + "FatWeight: " + recipe.getFatG() + "\n");

        fatWeightWithoutOil = recipe.getFatG() - oilWeight;
    }

    private void calThreeMealsPercentage() {

        mealRate = NesUtils.calMealRate(middleParams.getMealCountType());

        PrintUtils.test("确定三餐比例：" + "\n" + "breakfastRate: " + mealRate[0]
                + "\n" + "lunchRate: " + mealRate[1] + "\n" + "supperRate: "
                + mealRate[2] + "\n");
    }

    private void calEnergyAndWeightOfThreeMajorNutrientsAndFibreOfEachMeal() {

        this.setMealParams(recipe.getBreakfast());

        this.setMealParams(recipe.getLunch());

        this.setMealParams(recipe.getSupper());

    }

    private void setMealParams(MiddleMeal meal) {

        double rate = 0.0;

        String mealName = "";
        switch (meal.getType()) {
            case MealType.BREAKFAST:
                rate = mealRate[0];
                mealName = "早餐";
                break;
            case MealType.LUNCH:
                rate = mealRate[1];
                mealName = "中餐";
                break;
            case MealType.SUPPER:
                rate = mealRate[2];
                mealName = "晚餐";
                break;
        }

        meal.setRate(rate);
        meal.setEnergy((recipe.getEnergy() - oilWeight * 9) * rate);
        meal.setCarboG(recipe.getCarboG() * rate);
        meal.setProteinG(recipe.getProteinG() * rate);
        meal.setFatG(fatWeightWithoutOil * rate);
        meal.setFibreG(recipe.getFibreG() * rate);

        PrintUtils.test(mealName + "： \n" + "Energy: " + meal.getEnergy()
                + "\n" + "CarboG: " + meal.getCarboG() + "\n" + "ProteinG: "
                + meal.getProteinG() + "\n" + "FatG: " + meal.getFatG() + "\n"
                + "FibreG: " + meal.getFibreG() + "\n");

    }

    /**
     * 生成早餐
     *
     * @return List<DishEntity>
     * @throws
     * @throws
     * @author fengchu
     * @Time 2015年6月10日 下午1:56:50
     * @Title genBreakfast
     */
    private void genBreakfast() {

        List<DishEntity> dishes = new ArrayList<DishEntity>();

        DishEntity dishEntity;
        // 早餐
        // 标配为辅餐主食,如果低GI主食数量大于0，则减一
        if (middleParams.getStapleFoodCountOfLowGI() == 3) {

            dishEntity = this.getDishEntity(LargeType.BREAKFAST_STAPLE,
                    RandomType.BREAKFAST_STAPLE_TYPE, LOW_GI);

            middleParams.setStapleFoodCountOfLowGI(middleParams
                    .getStapleFoodCountOfLowGI() - 1);
        } else {

            dishEntity = this.getDishEntity(LargeType.BREAKFAST_STAPLE,
                    RandomType.BREAKFAST_STAPLE_TYPE, 120);
        }

        dishes.add(dishEntity);
        // 早餐主食从 “米” 类中选出时，配一荤一素
        if (dishEntity.getFoods().get(0).getFoodType()
                .equals(FoodType.RICE.getId())) {

            // 从食材表中的早餐荤或蛋选一个
            dishes.add(this.getDishEntity(LargeType.BREAKFAST_DISH,
                    RandomType.BREAKFAST_MEAT_EGG, maxGI));

            // 从食材表中的早餐素选一个
            dishes.add(this.getDishEntity(LargeType.BREAKFAST_DISH,
                    RandomType.BREAKFAST_VEGETABLE, maxGI));

            this.isEggUsed = true;

        } else if (dishEntity.getFoods().get(0).getFoodType()
                .equals(FoodType.BREAD.getId())) {// 早餐主食从“面包”类中选出时，配菜为“蛋”类，加“牛奶”类

            dishes.add(this.getDishEntity(LargeType.BREAKFAST_DISH,
                    RandomType.EGG, maxGI));

            this.isEggUsed = true;

            dishes.add(this.getDishEntity(LargeType.MILK, RandomType.MILK,
                    maxGI));

        } else if (dishEntity.getFoods().get(0).getFoodType()
                .equals(FoodType.STEAMED_STUFFED_BUNS.getId())) {// 早餐主食从“包子”类中选出时，没有配菜,有“牛奶”类

            dishes.add(this.getDishEntity(LargeType.MILK, RandomType.MILK,
                    maxGI));

        }

        // 根据辅餐数量选出点心
        for (int i = 0; i < middleParams.getMealCount() - 3; i++) {

            dishes.add(this.getDishEntity(LargeType.DESSERT,
                    RandomType.DESSERT_FAT_AND_PROTEIN, middleParams.getMaxGI()));

        }

        MiddleMeal breakfast = recipe.getBreakfast();

        breakfast.setDishes(dishes);

        this.calStapleGI(breakfast);

        cal.calActualWeightAndEnergy(breakfast, recipe, middleParams);

    }

    private void resetWeightOfThreeMajorNutrientsAndFibre() {

        double lunchRateCC = mealRate[1] / (mealRate[1] + mealRate[2]);
        double supperRateCC = mealRate[2] / (mealRate[1] + mealRate[2]);

        double leftCarboG = recipe.getCarboG()
                - recipe.getBreakfast().getCarboG();
        double leftProteinG = recipe.getProteinG()
                - recipe.getBreakfast().getProteinG();
        double leftFatG = fatWeightWithoutOil - recipe.getBreakfast().getFatG();
        double leftFibreG = recipe.getFibreG()
                - recipe.getBreakfast().getFibreG();

        recipe.getLunch().setCarboG(leftCarboG * lunchRateCC);
        recipe.getLunch().setProteinG(leftProteinG * lunchRateCC);
        recipe.getLunch().setFatG(leftFatG * lunchRateCC);
        recipe.getLunch().setFibreG(leftFibreG * lunchRateCC);
        recipe.getSupper().setCarboG(leftCarboG * supperRateCC);
        recipe.getSupper().setProteinG(leftProteinG * supperRateCC);
        recipe.getSupper().setFatG(leftFatG * supperRateCC);
        recipe.getSupper().setFibreG(leftFibreG * supperRateCC);
    }

    private void genLunch() {

        MiddleMeal lunch = recipe.getLunch();
        lunch.setDishes(this.genLunchOrSupper(MealType.LUNCH));

        while (!cal.calActualWeightAndEnergy(lunch, recipe, middleParams)) {

            for (DishEntity dishEntityIt : lunch.getDishes()) {
                middleParams.getSelectedDishes().remove(
                        dishEntityIt.getDishName());
            }

            lunch.getDishes().removeAll(lunch.getDishes());
            lunch.setDishes(this.genLunchOrSupper(MealType.LUNCH));
        }

        if (this.lowGIStaple) {
            middleParams.setStapleFoodCountOfLowGI(middleParams
                    .getStapleFoodCountOfLowGI() - 1);
            this.lowGIStaple = false;
        }

        this.calStapleGI(lunch);

        recipe.setLunch(lunch);

    }

    private void genSupper() {

        MiddleMeal supper = recipe.getSupper();
        supper.setDishes(this.genLunchOrSupper(MealType.SUPPER));

        while (!cal.calActualWeightAndEnergy(supper, recipe, middleParams)) {
            supper.getDishes().removeAll(supper.getDishes());
            supper.setDishes(this.genLunchOrSupper(MealType.SUPPER));
        }

        if (this.lowGIStaple) {
            middleParams.setStapleFoodCountOfLowGI(middleParams
                    .getStapleFoodCountOfLowGI() - 1);
            this.lowGIStaple = false;
        }

        this.calStapleGI(supper);

        recipe.setSupper(supper);

    }

    private List<DishEntity> genLunchOrSupper(String mealType) {

        List<DishEntity> dishes = new ArrayList<DishEntity>();

        // 标配为主食
        if (middleParams.getStapleFoodCountOfLowGI() > 0) {

            dishes.add(this.getDishEntity(LargeType.STAPLE,
                    RandomType.STAPLE_FOOD, LOW_GI));
            this.lowGIStaple = true;

        } else {

            dishes.add(this.getRice());

        }

        // 一道素菜
        if (isEggUsed) {

            dishes.add(this.getDishEntity(LargeType.VEGETABLE,
                    RandomType.VEGETABLE_TYPE_WITHOUT_EGG, maxGI));

        } else {

            DishEntity dish = this.getDishEntity(LargeType.VEGETABLE,
                    RandomType.VEGETABLE_TYPE_INCLUDE_EGG, maxGI);

            dishes.add(dish);
            isEggUsed = this.checkConteinsEgg(dish);
        }

        // 两道荤菜
        // 对于一般人,从低蛋白或肉蛋白中选一个
        dishes.add(this.getDishEntity(LargeType.MEAT,
                RandomType.MEAT_TYPE_FULL_FAT, maxGI));

        dishes.add(this.getDishEntity(LargeType.MEAT,
                RandomType.MEAT_TYPE_FULL_PROTEIN, maxGI));

        // 标配一个奶
        // 中午酸奶
        if (mealType == MealType.LUNCH) {
            dishes.add(this.getYogurt());
        }
        // 晚餐牛奶
        if (mealType == MealType.SUPPER) {
            dishes.add(this.getMilk());
        }

        // 标配一个水果
        dishes.add(this.getDishEntity(LargeType.FRUIT, RandomType.FRUIT, maxGI));

        return dishes;
    }

    private DishEntity getDishEntity(LargeType largeType, int randomType,
                                     int maxGI) {

        FoodEntity food;
        DishEntity dish = null;
        FoodType foodType;

        while (dish == null) {
            foodType = FoodType.getFoodTypeByRandomType(randomType);
            food = foodService.randomByFoodType(foodType, maxGI);

            if (food == null) {

                PrintUtils.test("没有找到符合条件的食材");
                dish = null;
            } else {

                PrintUtils.test("找到符合条件的食材" + food.getFoodName());
                PrintUtils.test("找到符合条件的食材" + food.getId());
                dish = dishService.randomOneWithAllFoodIds(largeType,
                        food.getId());
                if (dish != null) {
                    if (isEggUsed && this.checkConteinsEgg(dish)) {
                        dish = null;
                    }
                }

            }

            if (dish != null
                    && (!dish.getLargeType().equals(LargeType.MILK.getId())
                    && !dish.getLargeType().equals(
                    LargeType.FRUIT.getId()) && !dish.getId()
                    .equals(DishId.GRAIN_RICE))
                    && !dish.getId().equals(DishId.BOILED_EGG1)
                    && !dish.getId().equals(DishId.BOILED_EGG2)) {

                if (middleParams.getSelectedDishes().contains(
                        dish.getDishName())) {
                    dish = null;
                } else {
                    middleParams.getSelectedDishes().add(dish.getDishName());
                }

            }
        }

        return dish;
    }

    private DishEntity getMilk() {
        return dishService.getById(DishId.MILK);
    }

    private DishEntity getYogurt() {
        return dishService.getById(DishId.YOGURT);
    }

    private DishEntity getRice() {
        return dishService.getById(DishId.RICE);
    }

    private boolean checkConteinsEgg(DishEntity dish) {
        for (FoodEntity foodIt : dish.getFoods()) {
            if (foodIt.getFoodType().equals(FoodType.EGG.getId())) {
                return true;
            }
        }
        return false;
    }

    private void checkIsIncreased(MiddleMeal meal) {

        int removeNum = 0;

        List<DishEntity> dishListBak = new ArrayList<DishEntity>();

        for (DishEntity dishIt : meal.getDishes()) {

            if (dishIt.getLargeType().equals(LargeType.MEAT.getId())) {
                for (FoodEntity foodIt : dishIt.getFoods()) {

                    if (FoodType.isMeat(FoodType.self(foodIt.getFoodType()))) {

                        if (foodIt.getWeight().intValue() == foodIt
                                .getStartNum().intValue()) {

                            dishListBak.add(dishIt);
                            removeNum++;
                        }
                    }
                }
            }
        }

        while (removeNum > 0) {
            meal.getDishes().removeAll(dishListBak);

            meal.getDishes().add(
                    this.getDishEntity(LargeType.VEGETABLE,
                            RandomType.VEGETABLE_TYPE_WITHOUT_EGG, maxGI));

            removeNum--;
        }
        List<DishEntity> dishList = new ArrayList<DishEntity>();
        for (DishEntity dishIt : meal.getDishes()) {
            dishList.add(dishService.getById(dishIt.getId()));
        }
        meal.setDishes(dishList);
        cal.calActualWeightAndEnergy(meal, recipe, middleParams);
    }

    private void resetEnergy(MiddleMeal meal) {

        double energy = 0.0;

        for (DishEntity dishIt : meal.getDishes()) {

            if (dishIt.getLargeType().equals(LargeType.MEAT.getId())
                    || dishIt.getLargeType()
                    .equals(LargeType.VEGETABLE.getId())) {
                dishIt.setEnergy(dishIt.getEnergy() + oilWeight * 9 / 6f);
            }

            energy += dishIt.getEnergy();
        }

        meal.setEnergy(energy);

    }

    private void resetTotalEnergy() {

        double energy = recipe.getBreakfast().getEnergy();
        energy += recipe.getLunch().getEnergy();
        energy += recipe.getSupper().getEnergy();
        if (recipe.getAfterBreakfast() != null) {
            energy += recipe.getAfterBreakfast().getEnergy();
        }
        if (recipe.getAfterLunch() != null) {
            energy += recipe.getAfterLunch().getEnergy();
        }
        if (recipe.getAfterSupper() != null) {
            energy += recipe.getAfterSupper().getEnergy();
        }
        recipe.setEnergy(energy);
    }

    /**
     * 从早餐中选出一个加餐
     *
     * @param dishList
     * @param mealType 加餐的类型
     * @return MiddleMeal
     * @throws
     * @throws
     * @author fengchu
     * @Time 2015年5月22日 下午2:15:28
     * @Title genASideMeal
     */
    private MiddleMeal genASideMeal(List<DishEntity> dishList, String mealType) {
        // 初始化一个加餐
        MiddleMeal meal = new MiddleMeal(mealType);
        // 初始化加餐食谱列表
        List<DishEntity> afterMealDishList = new ArrayList<DishEntity>();
        // 选出一道菜
        DishEntity dish = dishList.get(dishList.size() - 1);
        dishList.remove(dishList.size() - 1);
        // 加到加餐中
        afterMealDishList.add(dish);
        meal.setDishes(afterMealDishList);
        return meal;
    }

    private void calSideMealParams() {

        if (recipe.getAfterBreakfast() != null) {
            this.calSideMealParams(recipe.getAfterBreakfast());
        }
        if (recipe.getAfterLunch() != null) {
            this.calSideMealParams(recipe.getAfterLunch());
        }
        if (recipe.getAfterSupper() != null) {
            this.calSideMealParams(recipe.getAfterSupper());
        }

    }

    private void calSideMealParams(MiddleMeal meal) {

        List<DishEntity> dishList = meal.getDishes();

        if (dishList == null) {

            meal = null;

            return;
        }

        List<FoodEntity> foodList = new ArrayList<FoodEntity>();
        for (DishEntity dishIt : dishList) {
            foodList.addAll(dishIt.getFoods());
        }
        for (FoodEntity foodIt : foodList) {

            meal.setCarboG(meal.getCarboG() + foodIt.getCarboG());
            meal.setProteinG(meal.getProteinG() + foodIt.getProteinG());
            meal.setFatG(meal.getFatG() + foodIt.getFatG());
            meal.setFibreG(meal.getFibreG() + foodIt.getFibreG());
            meal.setEnergy(meal.getEnergy() + foodIt.getEnergy());

        }

        meal.setRate(0.1);
    }

    private void calBreakfastParams() {

        List<MiddleMeal> mealList = new ArrayList<MiddleMeal>();
        mealList.add(recipe.getAfterBreakfast());
        mealList.add(recipe.getAfterLunch());
        mealList.add(recipe.getAfterSupper());

        double energy = 0.0;
        double carboG = 0.0;
        double proteinG = 0.0;
        double fatG = 0.0;
        double fibreG = 0.0;
        double rate = 0.0;

        for (MiddleMeal mealIt : mealList) {
            if (mealIt != null) {
                energy += mealIt.getEnergy();
                carboG += mealIt.getCarboG();
                proteinG += mealIt.getProteinG();
                fatG += mealIt.getFatG();
                fibreG += mealIt.getFibreG();
                rate += 0.1;
            }
        }

        MiddleMeal meal = recipe.getBreakfast();

        meal.setEnergy(meal.getEnergy() - energy);
        meal.setCarboG(meal.getCarboG() - carboG);
        meal.setProteinG(meal.getProteinG() - proteinG);
        meal.setFatG(meal.getFatG() - fatG);
        meal.setFibreG(meal.getFibreG() - fibreG);
        meal.setRate(meal.getRate() - rate);

    }

    private void separateSideDishFromBreakfast() {
        List<DishEntity> dishList = recipe.getBreakfast().getDishes();

        switch (middleParams.getMealCountType()) {
            case NesConstants.MEAL_COUNT_4_BREAKFAST:

                recipe.setAfterBreakfast(this.genASideMeal(dishList,
                        MealType.AFTER_BREAKFAST));
                break;
            case NesConstants.MEAL_COUNT_5_BREAKFAST_SUPPER:
                recipe.setAfterBreakfast(this.genASideMeal(dishList,
                        MealType.AFTER_BREAKFAST));
                recipe.setAfterSupper(this.genASideMeal(dishList,
                        MealType.AFTER_SUPPER));
                break;

            default:
                break;
        }

        this.calSideMealParams();
        this.calBreakfastParams();
    }

    private void calStapleGI(MiddleMeal meal) {
        List<DishEntity> dishList = meal.getDishes();
        for (DishEntity dishIt : dishList) {
            if (dishIt.getLargeType().equals(LargeType.STAPLE.getId())
                    || dishIt.getLargeType().equals(
                    LargeType.BREAKFAST_STAPLE.getId())) {
                meal.setStapleGI(dishIt.getFoods().get(0).getGI() + 0.0);
            }
        }

    }

    private void calSideMealGI() {

        if (recipe.getAfterBreakfast() != null) {
            recipe.getAfterBreakfast().setStapleGI(
                    recipe.getAfterBreakfast().getDishes().get(0).getFoods()
                            .get(0).getGI() + 0.0);
        }

        if (recipe.getAfterLunch() != null) {
            recipe.getAfterLunch().setStapleGI(
                    recipe.getAfterLunch().getDishes().get(0).getFoods().get(0)
                            .getGI() + 0.0);
        }

        if (recipe.getAfterSupper() != null) {
            recipe.getAfterSupper().setStapleGI(
                    recipe.getAfterSupper().getDishes().get(0).getFoods()
                            .get(0).getGI() + 0.0);
        }

    }

    private void reCalRecipeParams() {

        if (this.middleParams.getResetRate() < 1.0) {

            double resetRate = this.middleParams.getResetRate();

            recipe.resetParams(resetRate);

            recipe.getBreakfast().resetParams(resetRate);

            recipe.getLunch().resetParams(resetRate);
            recipe.getSupper().resetParams(resetRate);
            if (recipe.getAfterBreakfast() != null) {
                recipe.getAfterBreakfast().resetParams(resetRate);
            }
            if (recipe.getAfterLunch() != null) {
                recipe.getAfterLunch().resetParams(resetRate);
            }
            if (recipe.getAfterSupper() != null) {
                recipe.getAfterSupper().resetParams(resetRate);
            }
        }

    }

}
