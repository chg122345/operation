/**
 * @author (c) 2018,
 * @date 2018/11/18  11:02
 * @version 1.0
 *
 * <p>
 * Find a way for success and not make excuses for failure.
 * </p>
 */
package org.jleopard.datam.service.impl;

import org.jleopard.datam.common.Msg;
import org.jleopard.datam.dao.TeamMapper;
import org.jleopard.datam.model.Team;
import org.jleopard.datam.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static org.jleopard.datam.common.MsgResultKey.TEAM_LIST;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamMapper teamMapper;

    @Override
    public Msg delete(Team team) {
        try {
            if (teamMapper.deleteByWhere(team) > 0){
                return Msg.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail();
        }
        return Msg.msg("删除失败");
    }

    @Override
    public Msg update(Team team) {
        try {
            if (team.getId() == null){
                if (teamMapper.insert(team) > 0){
                    return Msg.msg("新增成功");
                }
            }else {
                if (teamMapper.updateByPrimaryKey(team) > 0){
                    return Msg.msg("修改成功");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail();
        }
        return Msg.fail();
    }

    @Override
    public Msg list(Team team) {
        try {
            List<Team> teamList = teamMapper.selectByWhere(team);
            if (CollectionUtils.isEmpty(teamList)){
                return Msg.msg("暂无数据");
            }else {
                return Msg.success().put(TEAM_LIST,teamList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail();
        }
    }
}
