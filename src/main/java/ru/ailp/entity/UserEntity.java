package ru.ailp.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.ailp.entity.abstr.AbstractEntity;
import ru.ailp.entity.helper.RoleEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "users", schema = "ailp")
public class UserEntity extends AbstractEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_user_id_seq")
    @SequenceGenerator(name = "users_user_id_seq", sequenceName = "ailp.users_user_id_seq", allocationSize = 1)
    @Column(name = "user_id")
    private Long id;
    private Long planId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDateTime createDate;
    private Boolean isActive;

    @ElementCollection(targetClass = RoleEntity.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), schema = "ailp")
    @Enumerated(EnumType.STRING)
    private Set<RoleEntity> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

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
}
