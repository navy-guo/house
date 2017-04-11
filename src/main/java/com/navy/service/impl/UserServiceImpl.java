package com.navy.service.impl;

import com.navy.config.BaseServiceImpl;
import com.navy.entity.User;
import com.navy.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements UserService {
}
