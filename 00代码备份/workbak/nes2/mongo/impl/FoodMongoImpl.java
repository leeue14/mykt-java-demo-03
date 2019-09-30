/**
 * 
 */
package com.carelinker.nes2.mongo.impl;

import com.carelinker.core.mongo.BaseMongo;
import com.carelinker.core.mongo.MongoParams;
import com.carelinker.nes2.algorithm.type.FoodType;
import com.carelinker.nes2.mongo.IFoodMongo;
import com.carelinker.nes2.mongo.entity.FoodEntity;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author fengchu
 * 
 */
@Repository
public class FoodMongoImpl extends BaseMongo<FoodEntity> implements IFoodMongo {

	@Override
	public FoodEntity getById(String id) {

		return super.findOne(new MongoParams().put("id", id));
	}

	@Override
	public FoodEntity getByFoodName(String foodName) {

		return super.findOne(new MongoParams().put("foodName", foodName));
	}

	@Override
	public List<FoodEntity> listFoodByType(FoodType foodType, int maxGI) {
		
		Query query = new Query(Criteria.where("foodType").is(foodType.getId()).and("GI").lt(maxGI));
		
		return super.findByQuery(query);
	}

	@Override
	public List<FoodEntity> listFoodByType(FoodType foodType, int maxGI,
										   double rateOfProteinAndFat) {
		
		Query query = new Query(Criteria.where("foodType").is(foodType.getId())
				.and("rateOfProteinAndFat").lt(rateOfProteinAndFat).and("GI").lt(maxGI));
		
		return super.findByQuery(query);
	}

	@Override
	public List<FoodEntity> listFoodAll() {
		return super.findAll();
	}
	
	
}
