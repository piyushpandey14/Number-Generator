package com.assignment.numbergenerator.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.numbergenerator.dao.TaskRepository;
import com.assignment.numbergenerator.entity.Task;
import com.assignment.numbergenerator.util.NumberUtils;
import com.assignment.numbergenerator.util.Status;

@Service
public class NumberServiceImpl implements NumberService {

	public static final Logger logger = LoggerFactory.getLogger(NumberServiceImpl.class);

	@Autowired
	private TaskRepository repository;
	
	@Autowired
	private NumberUtils utility;

	@Autowired
	private AsyncService service;
	
	@Override
	public Task generateNumber(int goal, int step) {
		Task task = new Task();
		task.setStatus(Status.IN_PROGRESS.name());

		task = repository.save(task);

		if (task.getId() != 0) {
			service.process(task, goal, step);
		}
		return task;
	}

	@Override
	public Optional<Task> getTaskStatus(long taskId) {
		return repository.findById(taskId);
	}

	@Override
	public String getFileResult(String action, long taskId) {
		if(action.equals("get_numlist"))
			return utility.readFromFile(taskId);
		return null;
	}
}
