/**
 * 
 */
package com.carelinker.nes2.service.impl;

import com.carelinker.common.service.healthprofile.model.HealthProfile;
import com.carelinker.common.service.healthprofile.service.IHealthProfileService;
import com.carelinker.common.utils.DateUtils;
import com.carelinker.common.utils.StringUtils;
import com.carelinker.common.utils.UUIDUtils;
import com.carelinker.nes2.algorithm.InputParams;
import com.carelinker.nes2.algorithm.NesAlgorithm;
import com.carelinker.nes2.mongo.IRecipeMongo;
import com.carelinker.nes2.mongo.entity.RecipeEntity;
import com.carelinker.nes2.service.IRecipeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author fengchu
 * 
 */
@Service
public class RecipeServiceImpl implements IRecipeService {

	@Resource
	private IRecipeMongo recipeMongo;

	@Resource
	private IHealthProfileService healthProfileService;

	@Override
	public RecipeEntity getByHealthProfileAndDate(HealthProfile profile, String date) {

		RecipeEntity recipe = recipeMongo.findOneByPatientIdAndDate(
				profile.getPatientId(), date);

		if (recipe != null) {
			return recipe;
		}

		return this.saveRecipe(profile, date);

	}

	@Override
	public void saveRecipe(RecipeEntity recipe) {

		recipeMongo.saveRecipe(recipe);
	}

	@Override
	public RecipeEntity saveRecipe(HealthProfile profile, String date) {

		RecipeEntity recipe = new RecipeEntity();

		String uuid = UUIDUtils.uuid();

		recipe.setId(uuid);
		recipe.setPatientId(profile.getPatientId());
		recipe.setDate(date);
		recipe.setStatus(0);
		recipeMongo.saveRecipe(recipe);

		Integer age = null;

		if (StringUtils.isNotEmpty(profile.getBirthday())) {
			age = DateUtils.getAgeFromBirthday(DateUtils.formatStringToDate(
					profile.getBirthday(), "yyyy-MM-dd"));
		}

		Boolean isDM = false;

		if (profile.getNeverHaveDm() != null && profile.getNeverHaveDm() == 0) {
			isDM = true;
		}

		InputParams inputParams = new InputParams(profile.getHeight(),
				profile.getWeight(), age, profile.getSex(),
				profile.getLabourLevel(), null, null, null,
				profile.getValueFbg(), profile.getSystolic(),
				profile.getDiastolic(), profile.getValueTc(),
				profile.getValueTg(), isDM);

		NesAlgorithm a = new NesAlgorithm(inputParams, profile.getPatientId(),
				date);

		recipe = a.calRecipe();
		recipe.setId(uuid);
		recipe.setShowGI(0);

		if (isDM || profile.getValueFbg() != null) {
			recipe.setShowGI(1);
		}
		recipeMongo.saveRecipe(recipe);

		return recipe;

	}

	@Override
	public RecipeEntity update(RecipeEntity recipe) {

		recipeMongo.saveRecipe(recipe);

		return recipe;
	}

	@Override
	public RecipeEntity getByPatientIdAndDate(String patientId, String date) {

		if (date.length() > 8) {
			date = date.substring(0, 8);
		}

		RecipeEntity recipe = recipeMongo.findOneByPatientIdAndDate(patientId,
				date);

		if (recipe != null) {
			return recipe;
		}

		HealthProfile profile = healthProfileService.getByPatientId(patientId);

		return this.saveRecipe(profile, date);

	}

	@Override
	public RecipeEntity getByPatientIdAndDateAllowNull(String patientId,
			String date) {

		return recipeMongo.findOneByPatientIdAndDate(patientId, date);

	}

	@Override
	public void deleteByPatientIdAndDate(String patientId, String date) {

		recipeMongo.deleteByPatientIdAndDate(patientId, date);

	}

}
