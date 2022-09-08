package pre045.board_service.member.token.config;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import pre045.board_service.exception.BusinessLogicException;

import static pre045.board_service.exception.ExceptionCode.NO_AUTHENTICATION;

public class SecurityUtil {

    public SecurityUtil() {
    }

    public static Long getCurrentMemberId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getName() == null || authentication.getName().equals("anonymousUser")) {
            throw new BusinessLogicException(NO_AUTHENTICATION);
        }

        return Long.parseLong(authentication.getName());
    }
}
