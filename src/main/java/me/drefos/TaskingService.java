package me.drefos;

import me.drefos.model.Group;
import me.drefos.model.Task;
import me.drefos.model.User;
import me.drefos.model.UserRole;
import me.drefos.repository.GroupRepository;
import me.drefos.repository.TaskRepository;
import me.drefos.repository.UserRepository;
import me.drefos.repository.UserRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TaskingService implements CommandLineRunner {
    private static final String DEFAULT_ROLE = "ROLE_USER";
    
    private final UserRepository userRepository;
    
    private final TaskRepository taskRepository;
    
    private final GroupRepository groupRepository;

    private final UserRoleRepository roleRepository;
    
    public TaskingService(UserRepository userRepository, TaskRepository taskRepository, GroupRepository groupRepository, UserRoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.groupRepository = groupRepository;
        this.roleRepository = roleRepository;
    }
    
    @Override
    public void run(String... args) {
        UserRole defaultRole = roleRepository.findByRole(DEFAULT_ROLE);

        User user = new User("Adam", "Szepan", "pass", "em@il.pl");
        user.setRole(defaultRole);
        userRepository.save(user);
    
        User[] users = new User[]{new User("Kamil", "Nowak", "haslo", "mai@le.pl"),
                new User("Ola", "Kowalska", "koń", "majl@grr.pl")};
        users[0].setRole(defaultRole);
        users[1].setRole(defaultRole);
        userRepository.save(users[0]);
        userRepository.save(users[1]);
    
        System.out.println("\n\n" + user + "\n\n");
    
        Task task = new Task("Title task", "description");
        taskRepository.save(task);
    
        System.out.println("\n\n" + task + "\n\n");
    
        Group group = new Group("Super group", user);
        group.getTasks().add(task);
        group.getUsers().add(users[0]);
        group.getUsers().add(users[1]);
    
        System.out.println("\n\n" + group + "\n\n");
        users[0].getGroups().add(group);
        users[1].getGroups().add(group);
    
        groupRepository.save(group);
    
        System.out.println("\n\n" + group + "\n\n");
    
        Group fromdb = groupRepository.getOne(1L);
    
    
        System.out.println("\n\n" + fromdb.getId() + "\n\n");
        System.out.println("\n\n" + fromdb.getName() + "\n\n");
        System.out.println("\n\n" + fromdb.getAdmin() + "\n\n");
        System.out.println("\n\n" + fromdb.getUsers() + "\n\n");
        System.out.println("\n\n" + fromdb.getTasks() + "\n\n");
        
    }
}
