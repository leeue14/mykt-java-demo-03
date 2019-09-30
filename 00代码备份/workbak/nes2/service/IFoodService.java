/**
 * 
 */
package com.carelinker.nes2.service;

import com.carelinker.nes2.algorithm.type.FoodType;
import com.carelinker.nes2.mongo.entity.FoodEntity;

import java.util.List;

/**
 * @author fengchu
 *
 */
public interface IFoodService {

	/**
	 * @Comments 通过ID查找一个食物
	 * @author fengchu
	 * @Time 2015年7月7日 下午7:57:48 
	 * @Title getById 
	 * @param id
	 * @return
	 * FoodEntity
	 * @throws 
	 * @exception
	 * @sample
	 */
	FoodEntity getById(String id);

	/**
	 * @Comments 通过FoodType和maxGI随机查找一个符合条件的食物
	 * @author fengchu
	 * @Time 2015年7月7日 下午7:58:18 
	 * @Title randomByFoodType 
	 * @param foodType
	 * @param maxGI
	 * @return
	 * FoodEntity
	 * @throws 
	 * @exception
	 * @sample
	 */
	FoodEntity randomByFoodType(FoodType foodType, int maxGI);

	/**
	 * @Comments
	 * @author fengchu
	 * @Time 2015年7月24日 下午2:31:59 
	 * @Title findOneByFoodName 
	 * @param foodName
	 * @return
	 * FoodEntity
	 * @throws 
	 * @exception
	 * @sample
	 */
	FoodEntity findOneByFoodName(String foodName);

	/**
	 * @Comments
	 * @author fengchu
	 * @Time 2015年8月25日 上午10:31:35 
	 * @Title listFoodAll 
	 * @return
	 * List<FoodEntity>
	 * @throws 
	 * @exception
	 * @sample
	 */
	List<FoodEntity> listFoodAll();

}
