package spring.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "role")
    private String role;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "users_role",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    public Role(Long id, String role) {
        this.id = id;
        this.role = role;
    }

    public Role() {

    }

    @Override
    public String getAuthority() {
        return getRole();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<User> getStudents() {
        return users;
    }

    public void setStudents(List<User> users) {
        this.users = users;
    }

    public Role(List<User> users) {
        this.users = users;
    }

    public Role(String role) {
        this.role = role;
    }
}
