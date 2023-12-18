package bvic.apps.scorecards.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

public class CustomUserDetails implements UserDetails {

    private User user;
    private Role role;

    Collection<SimpleGrantedAuthority> authorities = new HashSet<>();

    public CustomUserDetails(User user) {
        super();
        this.user = user;

        if (!user.getRoles().isEmpty()) {
            this.role = user.getRoles().iterator().next(); // inisialisasi role dengan role pertama dari user
        }

        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getPassword() {
        return user.getUserPassword();
    }

    public String getUsername() {
        return user.getUserId();
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    public String getFullName() {
        return user.getUserName();
    }

    public String getRoleName() {
        if (this.role != null) {
            return this.role.getRoleName();
        }
        return null;
    }

}
