package com.assignment.numbergenerator.service;

import java.util.Optional;

import com.assignment.numbergenerator.entity.Task;

public interface NumberService {

	public Task generateNumber(int goal, int step);
	
	public Optional<Task> getTaskStatus(long taskId);
	
	public String getFileResult(String action, long taskId);
}
