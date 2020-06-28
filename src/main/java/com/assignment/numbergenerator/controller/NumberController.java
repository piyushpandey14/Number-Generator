package com.assignment.numbergenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.numbergenerator.entity.GeneratorRequest;
import com.assignment.numbergenerator.entity.ResultResponse;
import com.assignment.numbergenerator.entity.Task;
import com.assignment.numbergenerator.exception.ResourceNotFoundException;
import com.assignment.numbergenerator.service.NumberService;

@RestController
@RequestMapping("/api")
public class NumberController {

	@Autowired
	private NumberService service;

	@PostMapping("/generate")
	public Task generateNumber(@RequestBody GeneratorRequest request) {
		return service.generateNumber(request.getGoal(), request.getStep());
	}

	@GetMapping("/tasks/{taskId}/status")
	public ResponseEntity<ResultResponse> getTaskStatus(@PathVariable Long taskId) {
		return service.getTaskStatus(taskId).map(task -> {
			ResultResponse result = new ResultResponse();
			result.setResult(task.getStatus());
			return new ResponseEntity<>(result, HttpStatus.OK);
		}).orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + taskId));
	}
	
	@GetMapping("/tasks/{taskId}")
	public ResponseEntity<ResultResponse> getResult(@RequestParam("action") String action, @PathVariable Long taskId){
		return service.getTaskStatus(taskId).map(task -> {
			ResultResponse result = new ResultResponse();
			result.setResult(service.getFileResult(action, taskId));
			return new ResponseEntity<>(result, HttpStatus.OK);
		}).orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + taskId));
	}
}
