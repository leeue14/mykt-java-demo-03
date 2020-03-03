package com.leeue.config;


import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;

import java.util.Collection;

/**
 * 单表的分表策略
 * <p>
 * 单分片
 *
 * @author liyue
 * @date 2020-03-03 12:18
 */
public class TableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<Long> {

    /**
     * 这个方法里面配置策略
     * 判断这个sql是否有条件判断，如果有条件判断都会走这个方法，需要配置分片的算法
     *
     * @param availableTargetNames
     * @param shardingValue
     * @return
     */

    @Override
    public String doEqualSharding(Collection<String> availableTargetNames, ShardingValue<Long> shardingValue) {

        for (String tableName : availableTargetNames) {

            System.out.println("当前数据库的名称是=" + tableName);

            if (tableName.endsWith(shardingValue.getValue() % 2 + "")) {

                System.out.println("当前表名称" + tableName);

                return tableName;
            }
        }
        throw new IllegalArgumentException("参数错误");
    }

    @Override
    public Collection<String> doInSharding(Collection<String> availableTargetNames, ShardingValue<Long> shardingValue) {
        return null;
    }

    @Override
    public Collection<String> doBetweenSharding(Collection<String> availableTargetNames, ShardingValue<Long> shardingValue) {
        return null;
    }


}
