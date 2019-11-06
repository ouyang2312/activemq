package com.ouyang.activemq.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ouyang.activemq.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface UserDao extends BaseMapper<User> {

}
