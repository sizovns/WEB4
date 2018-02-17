package model;

import javax.persistence.*;

@Entity
@Table(name = "roles", schema = "test")
public class Role {

    @Id
    @Column(name = "rid")
    @GeneratedValue
    private long id;

    @JoinColumn (name="user_id")
    @ManyToOne (fetch=FetchType.LAZY,
            cascade=CascadeType.ALL)
    private User user;

    @Column(name = "role")
    private String role;

    public Role(){
        super();
    }

    public Role(long id, User user,  String role){
        super();
        this.id = id;
        this.user = user;
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

}
