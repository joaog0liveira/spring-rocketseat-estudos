package br.com.javaestudos.todolist.task;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface iTaskRepository extends JpaRepository<TaskModel, UUID> {

}
