package com.example.repository;

import com.example.model.Task;
import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("taskRepository")
public interface TaskRepository extends JpaRepository<Task, String>{
	List<Task> findByUser(User user);
	List<Task> findByStatus(String status);
	List<Task> findByUserAndStatus(User user, String status);

}
