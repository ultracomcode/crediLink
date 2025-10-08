package com.main.CrediLink.application.user;

import com.main.CrediLink.application.user.enuns.StatusUser;
import com.main.CrediLink.application.user.enuns.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity(name = "usuarios")
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserEntity implements UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private UUID identifier;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String cpfCnpj;

    private String phone;

    @Column(unique = true)
    private String idCrm;

    @Column(unique = true)
    private String idContrato;

    @Enumerated(EnumType.STRING)
    private StatusUser status;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_USER")
            );
        } else {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_USER")
            );
        }
    }

    @PrePersist
    private void onCreate(){
        this.status = StatusUser.A;
        this.identifier = UUID.randomUUID();
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
        return this.status == StatusUser.A;
    }

    public void setRole(String role) {
        this.role = UserRole.valueOf(role.toUpperCase());
    }

    public boolean isAdmin() {
        return role != null && role.isAdmin();
    }

}
