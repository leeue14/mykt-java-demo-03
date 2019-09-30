package com.carelinker.nes2.mongo;

import com.carelinker.nes2.algorithm.type.FoodType;
import com.carelinker.nes2.mongo.entity.FoodEntity;

import java.util.List;

/**
 * Created by fengchu on 13/06/2017.
 */
public interface IFoodMongo {
    FoodEntity getById(String id);

    FoodEntity getByFoodName(String foodName);

    List<FoodEntity> listFoodByType(FoodType foodType, int maxGI);

    List<FoodEntity> listFoodByType(FoodType foodType, int maxGI,
                                    double rateOfProteinAndFat);

    List<FoodEntity> listFoodAll();
}
