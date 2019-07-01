package com.qf.service;

import com.qf.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author junjun
 * @date 2019/6/30 14:09
 */
public interface IStuService {
    int register(User user);
    List<User> getUserByName(Map<String,Object> map);
    int updateById(User user);
}
