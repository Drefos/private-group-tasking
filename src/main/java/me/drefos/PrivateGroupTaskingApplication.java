package me.drefos;

import me.drefos.model.Group;
import me.drefos.model.Task;
import me.drefos.model.TaskState;
import me.drefos.model.User;
import me.drefos.repository.GroupRepository;
import me.drefos.repository.TaskRepository;
import me.drefos.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class PrivateGroupTaskingApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(PrivateGroupTaskingApplication.class, args);

		User user = new User("Adam", "Szepan", "pass", "em@il.pl");
		UserRepository userRepository = ctx.getBean(UserRepository.class);
		userRepository.save(user);

        User[] users = new User[]{new User("Kamil", "Nowak", "haslo", "mai@le.pl"),
                                    new User("Ola", "Kowalska", "koń", "majl@grr.pl")};
		userRepository.save(users[0]);
		userRepository.save(users[1]);

        System.out.println("\n\n" + user + "\n\n");

		Task task = new Task("Title task", "description", TaskState.TO_DO);
		TaskRepository taskRepository = ctx.getBean(TaskRepository.class);
		taskRepository.save(task);

        System.out.println("\n\n" + task + "\n\n");

		Group group = new Group("Super group", user);
		group.getTasks().add(task);
		group.getUsers().add(users[0]);
		group.getUsers().add(users[1]);

		System.out.println("\n\n" + group + "\n\n");
        users[0].getGroups().add(group);
        users[1].getGroups().add(group);

		GroupRepository groupRepo = ctx.getBean(GroupRepository.class);
		groupRepo.save(group);

        System.out.println("\n\n" + group + "\n\n");

		Group fromdb = groupRepo.getOne(1l);


        System.out.println("\n\n" + fromdb.getId() + "\n\n");
//        System.out.println("\n\n" + fromdb.getName() + "\n\n");
//        System.out.println("\n\n" + fromdb.getAdmin() + "\n\n");
//        System.out.println("\n\n" + fromdb.getUsers() + "\n\n");
//        System.out.println("\n\n" + fromdb.getTasks() + "\n\n");

		ctx.close();
	}
}
