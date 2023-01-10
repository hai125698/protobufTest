package com.dtstack;

import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class Redis2Kafka {
    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // 生产环境中并行度设置为3,开发环境设置为1
        env.setParallelism(3);
        env.enableCheckpointing(10000L);
        env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
        env.getCheckpointConfig().setCheckpointTimeout(1000000L);
        env.getCheckpointConfig().setPreferCheckpointForRecovery(true);
        env.setRestartStrategy(RestartStrategies.fixedDelayRestart(3, 5000));
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);


    }
}
