package com.assignment.numbergenerator.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.numbergenerator.dao.TaskRepository;
import com.assignment.numbergenerator.entity.Task;
import com.assignment.numbergenerator.util.NumberUtils;
import com.assignment.numbergenerator.util.Status;

@Service
public class AsyncService {
	private ExecutorService executorService;

	@Autowired
	private NumberUtils utility;

	@Autowired
	private TaskRepository repository;

	@PostConstruct
	private void create() {
		executorService = Executors.newSingleThreadExecutor();
	}

	public void process(Task task, int goal, int step) {
		executorService.submit(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				boolean flag = utility.numberGenerator(task.getId(), goal, step);
				if (flag) {
					task.setStatus(Status.SUCCESS.name());
				} else {
					task.setStatus(Status.ERROR.name());
				}
				repository.save(task);
			}
		});
	}

	@PreDestroy
	private void destroy() {
		executorService.shutdown();
	}
}
