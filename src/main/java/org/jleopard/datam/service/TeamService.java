/**
 * @author (c) 2018,
 * @date 2018/11/18  11:00
 * @version 1.0
 *
 * <p>
 * Find a way for success and not make excuses for failure.
 * </p>
 */
package org.jleopard.datam.service;

import org.jleopard.datam.common.Msg;
import org.jleopard.datam.model.Team;

public interface TeamService {

    Msg delete(Team team);

    Msg update(Team team);

    Msg list(Team team);
}
