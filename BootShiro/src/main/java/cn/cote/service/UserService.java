package cn.cote.service;

import cn.cote.pojo.TUser;

import java.util.Set;

public interface UserService {

    TUser findUserByName(String userName);

    Set<String> findRolesByUserName(String userName);

    Set<String> findPermissionsByUserName(String userName);
}
