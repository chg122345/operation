/**
 * @author (c) 2018,
 * @date 2018/11/16  16:45
 * @version 1.0
 *
 * <p>
 * Find a way for success and not make excuses for failure.
 * </p>
 */
package org.jleopard.datam.service;

import org.jleopard.datam.common.Msg;
import org.jleopard.datam.model.User;

import javax.servlet.http.HttpSession;

public interface UserService {

    Msg register(User user);

    Msg login(HttpSession session, String id, String password);

    Msg update(User user);

    Msg list(User user);

    Msg delete(User user);
}
