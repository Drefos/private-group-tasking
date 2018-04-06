package me.drefos.repository;

import me.drefos.model.Group;
import me.drefos.model.Task;
import me.drefos.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}
