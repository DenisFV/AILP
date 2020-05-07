package ru.ailp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import ru.ailp.entity.enums.UserRole;
import ru.ailp.entity.abstr.AbstractEntity;
import ru.ailp.entity.enums.AuthProvider;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@EqualsAndHashCode(callSuper = false)
@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel(value = "UserDto", description = "UserDto")
public class UserDto extends AbstractEntity implements UserDetails, OAuth2User {

    @ApiModelProperty(value = "Индентификатор объекта", position = 1, example = "1")
    @JsonProperty("user_id")
    Long id;
    Long planId;
    String username;
    String password;
    String email;
    String imageUrl;
    String firstName;
    String lastName;
    String country;
    String city;
    LocalDateTime createDate;
    Boolean isActive;
    String providerId;
    AuthProvider provider;
    Map<String, Object> attributes;
    Set<UserRole> roles = new HashSet<>();

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return getIsActive();
    }

    @Override
    public String getName() {
        return (lastName + " " + firstName).trim();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }
}
