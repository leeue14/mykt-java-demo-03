/**
 * 
 */
package com.carelinker.nes2.service;

import com.carelinker.nes2.algorithm.type.LargeType;
import com.carelinker.nes2.mongo.entity.DishEntity;

/**
 * @author fengchu
 * 
 */
public interface IDishService {

	/**
	 * @author fengchu
	 * @Time 2015年6月9日 下午2:55:00
	 * @Title randomOneWithSomeFoodIds
	 * @param largeType
	 * @param foodIds
	 * @return DishEntity
	 * @throws
	 * @exception
	 */
	DishEntity randomOneWithSomeFoodIds(LargeType largeType, String... foodIds);

	/**
	 * @author fengchu
	 * @Time 2015年6月9日 下午2:55:07
	 * @Title randomOneWithAllFoodIds
	 * @param largeType
	 * @param foodIds
	 * @return DishEntity
	 * @throws
	 * @exception
	 */
	DishEntity randomOneWithAllFoodIds(LargeType largeType, String... foodIds);

	/**
	 * @Comments
	 * @author fengchu
	 * @Time 2015年7月13日 下午3:19:33 
	 * @Title getById 
	 * @param id
	 * @return
	 * DishEntity
	 * @throws 
	 * @exception
	 * @sample
	 */
	DishEntity getById(String id);

}
