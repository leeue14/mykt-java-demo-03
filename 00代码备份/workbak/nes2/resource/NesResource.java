/**
 *
 */
package com.carelinker.nes2.resource;


import com.carelinker.common.service.healthprofile.service.IHealthProfileService;
import com.carelinker.config.ResourcePath;
import com.carelinker.core.resource.DataPublicResponseParams;
import com.carelinker.core.resource.PRPFactory;
import com.carelinker.core.resource.PublicResponseParams;
import com.carelinker.nes2.algorithm.InputParams;
import com.carelinker.nes2.algorithm.NesAlgorithm;
import com.carelinker.nes2.algorithm.type.MealType;
import com.carelinker.nes2.mongo.entity.RecipeEntity;
import com.carelinker.nes2.service.IRecipeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author fengchu
 */
@Api(tags = "营养方案", description = "营养方案")
@RestController
@RequestMapping(ResourcePath.NES_2)
public class NesResource {

    @Resource
    private IRecipeService recipeService;
    @Resource
    private IHealthProfileService healthProfileService;

    @ApiOperation(value = "获取单天营养食谱全部信息")
    @RequestMapping(path = "/totalRecipes", method = RequestMethod.GET)
    public DataPublicResponseParams<RecipeEntity> Recipe(@ApiParam(value = "患者ID", defaultValue = "7937")
                                                         @RequestParam(value = "patientId", required = false) String patientId,
                                                         @ApiParam(value = "日期", defaultValue = "20150505")
                                                         @RequestParam(value = "date", required = false) String date,
                                                         @ApiParam(value = "身高")
                                                         @RequestParam(value = "height", required = false) Double height,
                                                         @ApiParam(value = "体重")
                                                         @RequestParam(value = "weight", required = false) Double weight,
                                                         @ApiParam(value = "年龄")
                                                         @RequestParam(value = "age", required = false) Integer age,
                                                         @ApiParam(value = "性别")
                                                         @RequestParam(value = "gender", required = false) Integer gender,
                                                         @ApiParam(value = "劳动强度，1-卧床休息2-轻体力3-中体力4-重体力")
                                                         @RequestParam(value = "laborIntensity", required = false) Integer laborIntensity,
                                                         @ApiParam(value = "收缩压")
                                                         @RequestParam(value = "systolic", required = false) Integer systolic,
                                                         @ApiParam(value = "舒张压")
                                                         @RequestParam(value = "diastolic", required = false) Integer diastolic,
                                                         @ApiParam(value = "血糖")
                                                         @RequestParam(value = "bloodsugar", required = false) Double bloodsugar,
                                                         @ApiParam(value = "是否糖尿病")
                                                         @RequestParam(value = "isDM", required = false) Integer isDM,
                                                         @ApiParam(value = "胆固醇")
                                                         @RequestParam(value = "cholesterol", required = false) Double cholesterol,
                                                         @ApiParam(value = "三酰甘油")
                                                         @RequestParam(value = "TG", required = false) Double TG,
                                                         @ApiParam(value = "孕期：0-没有怀孕，1-孕早期，2-孕中期，3-孕晚期")
                                                         @RequestParam(value = "pregnancy", required = false) Integer pregnancy,
                                                         @ApiParam(value = "哺乳期：0-不在哺乳期，1-哺乳期1-6个月，2-哺乳期6个月以上")
                                                         @RequestParam(value = "breastFeeding", required = false) Integer breastFeeding,
                                                         @ApiParam(value = "母乳比例")
                                                         @RequestParam(value = "percentOfBreastFeeding", required = false) Double percentOfBreastFeeding) {

        boolean isDMFlag;
        if (isDM == null) {
            isDMFlag = false;
        } else if (isDM == 1) {
            isDMFlag = true;
        } else {
            isDMFlag = false;
        }

        InputParams inputParams = new InputParams(height, weight, age, gender,
                laborIntensity, pregnancy, breastFeeding,
                percentOfBreastFeeding, bloodsugar, systolic, diastolic,
                cholesterol, TG, isDMFlag);

        NesAlgorithm a = new NesAlgorithm(inputParams, patientId, date);

        RecipeEntity recipe = a.calRecipe();

        recipeService.saveRecipe(recipe);

        return PRPFactory.getPrpData(recipe);
    }

    @ApiOperation(value = "更新患者的营养方案")
    @RequestMapping(path = "/recipes", method = RequestMethod.PUT)
    public PublicResponseParams updateRecipes(@ApiParam(value = "患者ID", defaultValue = "7937")
                                              @RequestParam(value = "patientId", required = false) String patientId,
                                              @ApiParam(value = "日期", defaultValue = "20150505")
                                              @RequestParam(value = "date", required = false) String date) {

        recipeService.saveRecipe(healthProfileService.getByPatientId(patientId), date);

        return PRPFactory.getSuccess();
    }

    @ApiOperation(value = "获取患者的营养方案")
    @RequestMapping(path = "/recipes", method = RequestMethod.GET)
    public DataPublicResponseParams<RecipeEntity> overviewRecipes(@ApiParam(value = "患者ID", defaultValue = "7937")
                                                                  @RequestParam(value = "patientId", required = false) String patientId,
                                                                  @ApiParam(value = "日期", defaultValue = "20150505")
                                                                  @RequestParam(value = "date", required = false) String date) {

        RecipeEntity recipe = recipeService.getByPatientIdAndDate(patientId,
                date);
        recipe.formatDecimal();

        return PRPFactory.getPrpData(recipe);
    }

    @ApiOperation(value = "更新一餐的状态")
    @RequestMapping(path = "/mealStatus", method = RequestMethod.PUT)
    public PublicResponseParams updateMealStatus(@ApiParam(value = "患者ID", defaultValue = "7937")
                                                 @RequestParam(value = "patientId", required = false) String patientId,
                                                 @ApiParam(value = "日期", defaultValue = "20150505")
                                                 @RequestParam(value = "date", required = false) String date,
                                                 @ApiParam(value = "餐类型,01-早餐，02-早餐加餐，03-中餐，04-中餐加餐，05-晚餐，06晚餐加餐", defaultValue = "20150505")
                                                 @RequestParam(value = "type", required = false) String type,
                                                 @ApiParam(value = "是否采用")
                                                 @RequestParam(value = "isTake", required = false) String isTake
    ) {

        RecipeEntity recipe = recipeService.getByPatientIdAndDate(patientId,
                date);

        switch (type) {
            case MealType.BREAKFAST:
                recipe.getBreakfast().setIsTake(isTake);
                break;
            case MealType.AFTER_BREAKFAST:
                recipe.getAfterBreakfast().setIsTake(isTake);
                break;
            case MealType.LUNCH:
                recipe.getLunch().setIsTake(isTake);
                break;
            case MealType.AFTER_LUNCH:
                recipe.getAfterLunch().setIsTake(isTake);
                break;
            case MealType.SUPPER:
                recipe.getSupper().setIsTake(isTake);
                break;
            case MealType.AFTER_SUPPER:
                recipe.getAfterSupper().setIsTake(isTake);
                break;
            default:
                break;
        }

        recipeService.update(recipe);

        return PRPFactory.getSuccess();

    }

}
