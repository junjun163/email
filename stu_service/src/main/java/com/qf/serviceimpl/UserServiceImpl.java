package com.qf.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.dao.UserMapper;
import com.qf.entity.User;
import com.qf.service.IStuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author junjun
 * @date 2019/6/30 18:02
 */
@Service
public class UserServiceImpl implements IStuService{
    @Autowired
    UserMapper userMapper;

    @Override
    public int register(User user) {
        return userMapper.insert(user);
    }

    @Override
    public List<User> getUserByName(Map<String, Object> map) {
        return userMapper.selectByMap(map);
    }

    @Override
    public int updateById(User user) {
        return userMapper.updateById(user);
    }
}
