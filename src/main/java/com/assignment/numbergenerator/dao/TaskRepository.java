package com.assignment.numbergenerator.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.numbergenerator.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
