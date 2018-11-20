/**
 * @author (c) 2018,
 * @date 2018/11/16  16:49
 * @version 1.0
 *
 * <p>
 * Find a way for success and not make excuses for failure.
 * </p>
 */
package org.jleopard.datam.service.impl;

import org.jleopard.datam.common.Msg;
import org.jleopard.datam.dao.UserMapper;
import org.jleopard.datam.model.User;
import org.jleopard.datam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

import static org.jleopard.datam.common.MsgResultKey.USER_LIST;

/**
 *
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Msg register(User user) {
        try {
            if (userMapper.selectByPrimaryKey(user.getId()) != null){
                return Msg.msg("学号"+user.getId()+"已被注册");
            }
            if (userMapper.insert(user) > 0){
                return Msg.success();
            }else {
                return Msg.msg("注册失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail();
        }
    }

    @Override
    public Msg login(HttpSession session, String id, String password) {
        try {
            User user = userMapper.selectByPrimaryKey(id);
            if (user == null){
                return Msg.msg("学号 "+ id +" 不存在");
            }
            if (!password.equalsIgnoreCase(user.getPassword())){
                return Msg.msg("密码错误");
            }
            session.setAttribute("user",user);
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail();
        }
        return Msg.success();
    }

    @Override
    public Msg update(User user) {
        try {
            if (userMapper.updateByPrimaryKey(user)>0){
                return Msg.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail();
        }
        return Msg.msg("更新失败");
    }

    @Override
    public Msg list(User user) {
        try {
            List<User> userList = userMapper.selectByWhere(user);
            if (CollectionUtils.isEmpty(userList)){
                return Msg.msg("暂无符合条件的数据");
            }else {
                return Msg.success().put(USER_LIST,userList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail();
        }
    }

    @Override
    public Msg delete(User user) {
        try {
            if (userMapper.deleteByWhere(user)>0){
                return Msg.success();
            }else {
                return Msg.msg("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail();
        }
    }
}
