/**
 * 
 */
package com.carelinker.nes2.algorithm.type;

import com.carelinker.common.utils.MathUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * @author fengchu
 * 
 */
public enum FoodType {

	/**
	 * 米
	 */
	RICE("rice", "01"),
	/**
	 * 面
	 */
	NOODLES("noodles", "02"),
	/**
	 * 包子
	 */
	STEAMED_STUFFED_BUNS("steamedStuffedBuns", "03"),
	/**
	 * 馒头
	 */
	STEAMED_BUNS("steamedBuns", "04"),
	/**
	 * 面包
	 */
	BREAD("bread", "05"),
	/**
	 * 麦片
	 */
	OATMEAL("oatmeal", "06"),
	/**
	 * 高蛋白
	 */
	PROTEIN_HIGH("proteinHigh", "07"),
	/**
	 * 肉蛋白
	 */
	PROTEIN_MEAT("proteinMeat", "08"),
	/**
	 * 肉脂肪
	 */
	FAT_MEAT("fatMeat", "09"),
	/**
	 * 高脂肪
	 */
	FAT_HIGH("fatHigh", "10"),
	/**
	 * 蔬菜
	 */
	VEGETABLE("vegetable", "11"),
	/**
	 * 蔬菜豆
	 */
	VEGETABLE_BEAN("vegetableBean", "12"),
	/**
	 * 蔬菜绿叶
	 */
	VEGETABLE_GREEN("vegetableGreen", "13"),
	/**
	 * 点心蛋白
	 */
	DESSERT_PROTEIN("dessertProtein", "14"),
	/**
	 * 点心脂肪
	 */
	DESSERT_FAT("dessertFat", "15"),
	/**
	 * 奶
	 */
	MILK("milk", "16"),
	/**
	 * 水果
	 */
	FRUIT("fruit", "17"),
	/**
	 * 早餐荤
	 */
	BREAKFAST_MEAT("breakfastMeat", "18"),
	/**
	 * 早餐素
	 */
	BREAKFAST_VEGETABLE("breakfastVegetable", "19"),
	/**
	 * 蛋
	 */
	EGG("egg", "20"),
	/**
	 * 非首选
	 */
	NEED_TO_VERIFY("needToVerify", "97"),
	/**
	 * 非首选
	 */
	NOT_FIRST_CHOICE("notFirstChoice", "98"),
	/**
	 * 不选择
	 */
	NEVER_CHOOSE("neverChoose", "99");

	private String name;
	private String id;

	private FoodType(String name, String id) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static FoodType randomBreakfastStapleType() {
		Map<FoodType, Double> map = new HashMap<FoodType, Double>();
		map.put(FoodType.RICE, 0.33);
		map.put(FoodType.BREAD, 0.33);
		map.put(FoodType.STEAMED_STUFFED_BUNS, 0.34);
		return MathUtils.randomByRate(map);
	}

	public static FoodType randomBreakfastMeatEgg() {
		Map<FoodType, Double> map = new HashMap<FoodType, Double>();
		map.put(FoodType.BREAKFAST_MEAT, 0.5);
		map.put(FoodType.EGG, 0.5);
		return MathUtils.randomByRate(map);
	}

	public static FoodType randomDessertFatAndProtein() {
		Map<FoodType, Double> map = new HashMap<FoodType, Double>();
		map.put(FoodType.DESSERT_FAT, 0.5);
		map.put(FoodType.DESSERT_PROTEIN, 0.5);
		return MathUtils.randomByRate(map);
	}

	public static FoodType randomVegetableTypeIncludeEgg() {
		Map<FoodType, Double> map = new HashMap<FoodType, Double>();
		map.put(FoodType.VEGETABLE, 0.3);
		map.put(FoodType.VEGETABLE_GREEN, 0.3);
		map.put(FoodType.VEGETABLE_BEAN, 0.2);
		map.put(FoodType.EGG, 0.2);
		return MathUtils.randomByRate(map);
	}

	public static FoodType randomVegetableTypeWithoutEgg() {
		Map<FoodType, Double> map = new HashMap<FoodType, Double>();
		map.put(FoodType.VEGETABLE, 0.4);
		map.put(FoodType.VEGETABLE_GREEN, 0.3);
		map.put(FoodType.VEGETABLE_BEAN, 0.3);
		return MathUtils.randomByRate(map);
	}

	public static FoodType randomMeatTypeFullProtein() {
		Map<FoodType, Double> map = new HashMap<FoodType, Double>();
		map.put(FoodType.PROTEIN_HIGH, 0.5);
		map.put(FoodType.PROTEIN_MEAT, 0.5);
		return MathUtils.randomByRate(map);
	}

	public static FoodType randomMeatTypeFullFat() {
		Map<FoodType, Double> map = new HashMap<FoodType, Double>();
		map.put(FoodType.FAT_HIGH, 0.5);
		map.put(FoodType.FAT_MEAT, 0.5);
		return MathUtils.randomByRate(map);
	}

	public static FoodType randomStapleFood() {
		Map<FoodType, Double> map = new HashMap<FoodType, Double>();
		map.put(RICE, 0.25);
		map.put(NOODLES, 0.75);
		return MathUtils.randomByRate(map);
	}

	public static boolean isMeat(FoodType type) {

		switch (type) {

		case FAT_HIGH:
			return true;

		case FAT_MEAT:
			return true;

		case PROTEIN_HIGH:
			return true;

		case PROTEIN_MEAT:
			return true;

		default:
			return false;

		}

	}

	public static FoodType self(String type) {

		for (FoodType c : FoodType.values()) {

			if (c.getId().equals(type)) {

				return c;

			}
		}
		return null;
	}

	public static FoodType getFoodTypeByRandomType(int randomType) {
		switch (randomType) {
		
		case RandomType.BREAKFAST_STAPLE_TYPE:
			return randomBreakfastStapleType();
		case RandomType.BREAKFAST_MEAT_EGG:
			return randomBreakfastMeatEgg();
		case RandomType.DESSERT_FAT_AND_PROTEIN:
			return randomDessertFatAndProtein();

		case RandomType.MEAT_TYPE_FULL_FAT:
			return randomMeatTypeFullFat();

		case RandomType.MEAT_TYPE_FULL_PROTEIN:
			return randomMeatTypeFullProtein();

		case RandomType.VEGETABLE_TYPE_INCLUDE_EGG:
			return randomVegetableTypeIncludeEgg();

		case RandomType.VEGETABLE_TYPE_WITHOUT_EGG:
			return randomVegetableTypeWithoutEgg();

		case RandomType.FRUIT:
			return FRUIT;

		case RandomType.STAPLE_FOOD:
			return randomStapleFood();
			
		case RandomType.EGG:
			return EGG;
			
		case RandomType.MILK:
			return MILK;
		case RandomType.BREAKFAST_VEGETABLE:
			return BREAKFAST_VEGETABLE;
		default:
			return null;
		}
	}
	
}
