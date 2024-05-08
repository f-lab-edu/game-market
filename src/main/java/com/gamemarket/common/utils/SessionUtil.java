package com.gamemarket.common.utils;

import com.gamemarket.common.exception.user.UserException;
import com.gamemarket.common.exception.user.UserExceptionCode;
import com.gamemarket.user.domain.entity.User;
import jakarta.servlet.http.HttpSession;
import lombok.experimental.UtilityClass;

import static com.gamemarket.user.utils.UserConst.SESSION_USER_KEY;

@UtilityClass
public class SessionUtil {

    public static User getUserFromSession(final HttpSession session) {
        final User user = (User) session.getAttribute(SESSION_USER_KEY);

        if (user == null) {
            throw new UserException(UserExceptionCode.UNAUTHORIZED_USER);
        }

        return user;
    }

}
