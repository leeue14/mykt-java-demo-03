package leeue.config;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;

import java.util.Collection;

/**
 * @author liyue
 * @date 2020-03-03 14:23
 */
public class DatabaseShardingAlgorithm implements SingleKeyDatabaseShardingAlgorithm<Long> {

    @Override
    public String doEqualSharding(Collection<String> availableTargetNames, ShardingValue<Long> shardingValue) {

        for (String dbName : availableTargetNames) {

            System.out.println("当前数据库分片=" + dbName);

            if (dbName.endsWith(shardingValue.getValue() % 2 + "")) {

                return dbName;
            }
        }

        throw new IllegalArgumentException("数据库分片错误");
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
