package ru.ailp.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.ailp.entity.abstr.AbstractEntity;
import ru.ailp.entity.enums.AuthProvider;
import ru.ailp.entity.enums.UserRole;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users", schema = "ailp")
public class UserEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_user_id_seq")
    @SequenceGenerator(name = "users_user_id_seq", sequenceName = "ailp.users_user_id_seq", allocationSize = 1)
    @Column(name = "user_id")
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

    @Enumerated(EnumType.STRING)
    AuthProvider provider;

    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), schema = "ailp")
    @Enumerated(EnumType.STRING)
    Set<UserRole> roles = new HashSet<>();

    @Transient
    Map<String, Object> attributes;
}
