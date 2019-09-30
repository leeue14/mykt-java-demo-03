/**
 * 
 */
package com.carelinker.nes2.service.impl;

import com.carelinker.common.utils.CollectionUtils;
import com.carelinker.nes2.algorithm.type.LargeType;
import com.carelinker.nes2.mongo.IDishMongo;
import com.carelinker.nes2.mongo.entity.DishEntity;
import com.carelinker.nes2.service.IDishService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author fengchu
 * 
 */
@Service
public class DishServiceImpl implements IDishService {

	@Resource
	private IDishMongo dishMongo;

	@Override
	public DishEntity getById(String id) {
		return dishMongo.getOneById(id).clone();
	}

	@Override
	public DishEntity randomOneWithAllFoodIds(LargeType largeType, String... foodIds) {

		List<DishEntity> list = dishMongo.listDishWithAllFoodIds(largeType, foodIds);

		return CollectionUtils.randomOne(list);
	}

	@Override
	public DishEntity randomOneWithSomeFoodIds(LargeType largeType,
			String... foodIds) {

		return CollectionUtils.randomOne(dishMongo.listDishWithSomeFoodIds(largeType, foodIds));
	}

}
