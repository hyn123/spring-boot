package com.nn.rabbitmq.mapper;

import com.nn.rabbitmq.pojo.MsgLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: hyn
 * @Date: 2019-12-29 15:43
 */
@Mapper
public interface MsgLogMapper {

    @Transactional
    @Insert("INSERT INTO msg_log(msg_id, msg, exchange, routing_key, status, try_count, next_try_time, create_time, update_time) " +
            "        VALUES (#{msgId}, #{msg}, #{exchange}, #{routingKey}, #{status}, #{tryCount}, #{nextTryTime}, #{createTime}, #{updateTime})")
    void insert(MsgLog msgLog);

    @Transactional
    @Update("update msg_log set status=#{i},update_time=sysdate() where msg_id=#{msgId}")
    void updateStatus(String msgId, int i);
    @Select("select * from msg_log where msg_id=#{msgId}")
    public MsgLog selectByPrimaryKey(String msgId);
}
