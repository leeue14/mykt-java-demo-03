/**
 *
 */
package com.carelinker.nes2.service.impl;


import com.carelinker.common.utils.CollectionUtils;
import com.carelinker.nes2.algorithm.type.FoodType;
import com.carelinker.nes2.mongo.IFoodMongo;
import com.carelinker.nes2.mongo.entity.FoodEntity;
import com.carelinker.nes2.service.IFoodService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author fengchu
 */
@Service
public class FoodServiceImpl implements IFoodService {

    @Resource
    private IFoodMongo foodMongo;

    @Override
    public FoodEntity getById(String id) {

        return foodMongo.getById(id);
    }

    @Override
    public FoodEntity randomByFoodType(FoodType foodType, int maxGI) {

        List<FoodEntity> foodList = foodMongo.listFoodByType(foodType, maxGI);

        return CollectionUtils.randomOne(foodList);
    }

    @Override
    public FoodEntity findOneByFoodName(String foodName) {

        return foodMongo.getByFoodName(foodName);
    }

    @Override
    public List<FoodEntity> listFoodAll() {
        return foodMongo.listFoodAll();
    }

}
