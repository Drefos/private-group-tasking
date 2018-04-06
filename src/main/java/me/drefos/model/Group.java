package me.drefos.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
public class Group implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_group")
    private Long id;
    @NotEmpty
    private String name;
    @NotNull
    @ManyToOne
    private User admin;
    @ManyToMany //<<====================
    @JoinTable(name = "group_users",
        joinColumns = {@JoinColumn(name = "group_id", referencedColumnName = "Id_group")},
        inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id_user")})
    private List<User> users = new ArrayList<>();
    @OneToMany
    @JoinColumn(name = "group_id", referencedColumnName = "id_group")
    private List<Task> tasks = new ArrayList<>();

    public Group() {
    }

    public Group(@NotEmpty String name, @NotEmpty User admin) {
        this.name = name;
        this.admin = admin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", admin=" + admin +
                ", users=" + users +
                ", tasks=" + tasks +
                '}';
    }
}
