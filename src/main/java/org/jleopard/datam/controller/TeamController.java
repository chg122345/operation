/**
 * @author (c) 2018,
 * @date 2018/11/19  11:02
 * @version 1.0
 *
 * <p>
 * Find a way for success and not make excuses for failure.
 * </p>
 */
package org.jleopard.datam.controller;

import org.jleopard.datam.common.Msg;
import org.jleopard.datam.model.Team;
import org.jleopard.datam.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping("/teamList")
    public Msg teamList(){
        return teamService.list(new Team());
    }
}
