package owner.code.demo.Sharding;

import lombok.Getter;
import lombok.Setter;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;
import owner.code.demo.constants.ShardingConstants;

import java.util.Collection;
import java.util.Properties;

/**
 * <p>
 * 动账明细 分片算法 根据uid取模分片
 * </p>
 *
 * @author dushuai
 * @version V1.0
 * @date 2023-06-28
 */
public class TransactionShardingAlgorithm implements StandardShardingAlgorithm<String> {

    @Getter
    @Setter
    private Properties props;

    @Override
    public void init(final Properties props) {
        this.props = props;
    }

    @Override
    public String getType() {
        return ShardingConstants.TRANSACTION_ALGORITHM;
    }

    /**
     * 精确查询
     *
     * @param availableTargetNames available data sources or table names
     * @param shardingValue        sharding value
     * @return sharding result for data source or table name
     */
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<String> shardingValue) {
        String uid = shardingValue.getValue();
        Integer hashCode = uid.hashCode() & Integer.MAX_VALUE;;
        String suffix = Integer.toString(hashCode % 16);
        String tableName = shardingValue.getLogicTableName() + "_" + suffix;
        availableTargetNames.add(tableName);
        return tableName;
    }

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<String> rangeShardingValue) {
        return collection;
    }


}
