/**
 * 
 */
package com.carelinker.nes2.mongo.impl;

import com.carelinker.core.mongo.BaseMongo;
import com.carelinker.nes2.mongo.IRecipeMongo;
import com.carelinker.nes2.mongo.entity.RecipeEntity;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * @author fengchu
 * 
 */
@Repository
public class RecipeMongoImpl extends BaseMongo<RecipeEntity> implements IRecipeMongo {

	@Override
	public RecipeEntity findOneByPatientIdAndDate(String patientId, String date) {

		Query query = new Query(Criteria.where("patientId").is(patientId)
				.and("date").is(date));

		return super.findOneByQuery(query);
	}

	@Override
	public void deleteByPatientIdAndDate(String patientId, String date) {

		Query query = new Query(Criteria.where("patientId").is(patientId)
				.and("date").is(date));

		super.deleteByQuery(query);
	}

	@Override
	public void saveRecipe(RecipeEntity recipe) {

		super.save(recipe);
	}

}
