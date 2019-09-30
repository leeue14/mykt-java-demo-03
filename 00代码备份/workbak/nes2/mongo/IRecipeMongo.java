package com.carelinker.nes2.mongo;

import com.carelinker.nes2.mongo.entity.RecipeEntity;

/**
 * Created by fengchu on 13/06/2017.
 */
public interface IRecipeMongo {
    RecipeEntity findOneByPatientIdAndDate(String patientId, String date);

    void deleteByPatientIdAndDate(String patientId, String date);

    void saveRecipe(RecipeEntity recipe);
}
