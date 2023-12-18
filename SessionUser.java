package bvic.apps.scorecards.security;

import bvic.apps.scorecards.model.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

@Repository
public class SessionUser {
    public String getLoggedinUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetails) {
            return ((CustomUserDetails) principal).getFullName();
        }
        return principal.toString();
    }

    public String getLoggedinUserRoleId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetails) {
            return ((CustomUserDetails) principal).getRoleName();
        }
        return principal.toString();
    }
}
