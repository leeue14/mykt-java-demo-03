package com.carelinker.nes2.mongo;

import com.carelinker.nes2.algorithm.type.LargeType;
import com.carelinker.nes2.mongo.entity.DishEntity;

import java.util.List;

/**
 * Created by fengchu on 13/06/2017.
 */
public interface IDishMongo {
    DishEntity getOneById(String id);

    List<DishEntity> listDishWithSomeFoodIds(LargeType largeType,
                                             String... foodIds);

    List<DishEntity> listDishWithAllFoodIds(LargeType largeType,
                                            String... foodIds);
}
