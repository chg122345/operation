/**
 * @author (c) 2018,
 * @date 2018/11/16  15:41
 * @version 1.0
 *
 * <p>
 * Find a way for success and not make excuses for failure.
 * </p>
 */
package org.jleopard.datam.controller;

import org.jleopard.datam.annotation.SysLog;
import org.jleopard.datam.common.Msg;
import org.jleopard.datam.dao.TeamMapper;
import org.jleopard.datam.dao.UploadFileMapper;
import org.jleopard.datam.model.Team;
import org.jleopard.datam.model.UploadFile;
import org.jleopard.datam.model.User;
import org.jleopard.datam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.jleopard.datam.common.MsgResultKey.FILE;
import static org.jleopard.datam.common.MsgResultKey.FILE_LIST;
import static org.jleopard.datam.common.MsgResultKey.TEAM_LIST;

@RestController
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private UploadFileMapper uploadFileMapper;

    @SysLog("去登录页")
    @GetMapping("/")
    public ModelAndView login(){
        List<Team> teamList = teamMapper.selectByWhere(new Team());
        return new ModelAndView("login").addObject(TEAM_LIST,teamList);
    }
    @SysLog("浏览首页")
    @GetMapping("/index")
    public ModelAndView home(){
        List<UploadFile> uploadFileList = uploadFileMapper.selectByWhere(new UploadFile());
        return new ModelAndView("index").addObject(FILE_LIST,uploadFileList);
    }
    @SysLog("去上传页")
    @GetMapping("/upload")
    public ModelAndView upload(){
        List<Team> teamList = teamMapper.selectByWhere(new Team());
        return new ModelAndView("upload").addObject(TEAM_LIST,teamList);
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @SysLog("用户注册")
    @PostMapping("/register")
    public Msg register(User user){
        return userService.register(user);
    }

    /**
     * 用户登录
     * @param id
     * @param password
     * @return
     */
    @SysLog("验证登录信息")
    @PostMapping("/login")
    public Msg login(HttpSession session, String id, String password){
        return userService.login(session,id,password);
    }

    @SysLog("搜索")
    @PostMapping("/search")
    public ModelAndView search(@RequestParam("model") Integer code,@RequestParam("keyWords") String keyWords){
        List<UploadFile> uploadFiles = new ArrayList<>();
        if (code == 1){
            UploadFile uploadFile = new UploadFile();
            uploadFile.setTitle(keyWords);
            uploadFiles = uploadFileMapper.selectByWhere(uploadFile);
        }else if (code == 2){
            Team team = new Team();
            team.setTitle(keyWords);
            List<Team> teams = teamMapper.selectByWhere(team);
            for (Team t:teams){
                UploadFile uploadFile$ = new UploadFile();
                uploadFile$.setTeamId(t.getId());
                List<UploadFile> uploadFiles$ = uploadFileMapper.selectByWhere(uploadFile$);
                uploadFiles.addAll(uploadFiles$);
            }
        }
        return new ModelAndView("index").addObject(FILE_LIST,uploadFiles);
    }

    @SysLog("详情页")
    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") Integer id){
        UploadFile uploadFile = uploadFileMapper.selectByPrimaryKey(id);
        return new ModelAndView("detail").addObject(FILE,uploadFile);
    }
}
