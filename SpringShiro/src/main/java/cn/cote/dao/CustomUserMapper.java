package cn.cote.dao;

import cn.cote.pojo.User;

import java.util.Set;

public interface CustomUserMapper {
    String findPassWordByName(User user);
    Set<String> findRoles(User user);
}
