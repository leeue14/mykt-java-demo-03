/**
 * 
 */
package com.carelinker.nes2.service;

import com.carelinker.common.service.healthprofile.model.HealthProfile;
import com.carelinker.nes2.mongo.entity.RecipeEntity;

/**
 * @author fengchu
 * 
 */
public interface IRecipeService  {

	/**
	 * @Comments
	 * @author fengchu
	 * @Time 2015年7月12日 上午10:54:42 
	 * @Title update 
	 * @param recipe
	 * @return
	 * RecipeEntity
	 * @throws 
	 * @exception
	 * @sample
	 */
	RecipeEntity update(RecipeEntity recipe);

	/**
	 * @Comments
	 * @author fengchu
	 * @Time 2015年7月23日 下午1:13:39 
	 * @Title getByPatientIdAndDate 
	 * @param patientId
	 * @param date
	 * @return
	 * RecipeEntity
	 * @throws 
	 * @exception
	 * @sample
	 */
	RecipeEntity getByPatientIdAndDate(String patientId, String date);

	/**
	 * @Comments
	 * @author fengchu
	 * @Time 2015年7月23日 下午1:17:43 
	 * @Title getByHealthProfileAndDate 
	 * @param profile
	 * @param date
	 * @return
	 * RecipeEntity
	 * @throws 
	 * @exception
	 * @sample
	 */
	RecipeEntity getByHealthProfileAndDate(HealthProfile profile, String date);

	void saveRecipe(RecipeEntity recipe);

	/**
	 * @Comments
	 * @author fengchu
	 * @Time 2015年7月24日 上午10:46:23 
	 * @Title saveRecipe 
	 * @param profile
	 * @param date
	 * @return
	 * RecipeEntity
	 * @throws 
	 * @exception
	 * @sample
	 */
	RecipeEntity saveRecipe(HealthProfile profile, String date);

	/**
	 * @Comments
	 * @author fengchu
	 * @Time 2015年7月24日 上午10:59:54 
	 * @Title deleteByPatientIdAndDate 
	 * @param patientId
	 * @param date
	 * void
	 * @throws 
	 * @exception
	 * @sample
	 */
	void deleteByPatientIdAndDate(String patientId, String date);

	/**
	 * @Comments
	 * @author fengchu
	 * @Time 2015年8月4日 下午12:17:30 
	 * @Title getByPatientIdAndDateAllowNull 
	 * @param patientId
	 * @param date
	 * @return
	 * RecipeEntity
	 * @throws 
	 * @exception
	 * @sample
	 */
	RecipeEntity getByPatientIdAndDateAllowNull(String patientId, String date);

}
