package be.digitalcity.laetitia.finalproject.models.entities;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "security_group")
@Data
public class Group implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String label;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Role.class)
    private Set<Role> roles;

    @Override
    public String getAuthority() {
        return this.label;
    }
}