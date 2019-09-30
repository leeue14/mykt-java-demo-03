/**
 * 
 */
package com.carelinker.nes2.mongo.impl;

import com.carelinker.core.mongo.BaseMongo;
import com.carelinker.core.mongo.MongoParams;
import com.carelinker.nes2.algorithm.type.LargeType;
import com.carelinker.nes2.mongo.IDishMongo;
import com.carelinker.nes2.mongo.entity.DishEntity;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author fengchu
 * 
 */
@Repository
public class DishMongoImpl extends BaseMongo<DishEntity> implements IDishMongo {

	@Override
	public DishEntity getOneById(String id) {

		return super.findOne(new MongoParams().put("id", id));
	}

	@Override
	public List<DishEntity> listDishWithSomeFoodIds(LargeType largeType,
													String... foodIds) {

		if (foodIds.length < 1) {
			return null;
		} else {
			Query query = new Query(Criteria.where("largeType")
					.is(largeType.getId()).and("foods").in((Object[]) foodIds));
			return super.findByQuery(query);
		}
	}

	@Override
	public List<DishEntity> listDishWithAllFoodIds(LargeType largeType,
												   String... foodIds) {

		Criteria c = Criteria.where("largeType").is(largeType.getId());

		if (foodIds.length >= 1) {

			c.and("foods").is(foodIds[0]);

		}

		for (int i = 1; i < foodIds.length; i++) {
			c.andOperator(Criteria.where("foods").is(foodIds[i]));
		}

		Query query = new Query(c);
		return super.findByQuery(query);
	}

	public class Count {
		int count;

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

	}

}
