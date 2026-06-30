package com.example.controller;

import com.example.generated.api.TasksApi;
import com.example.generated.model.ErrorResponse;
import com.example.generated.model.Task;
import com.example.generated.model.TaskCreateRequest;
import com.example.generated.model.TaskStatusFilterRequest;
import com.example.generated.model.TaskUpdateRequest;
import com.example.usecase.UseCaseResult;
import com.example.usecase.task.TaskMapper;
import com.example.usecase.task.TaskNotFoundException;
import com.example.usecase.task.TaskUseCase;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class TasksApiController implements TasksApi {

    private final TaskUseCase taskUseCase;

    public TasksApiController(TaskUseCase taskUseCase) {
        this.taskUseCase = taskUseCase;
    }

    @Override
    public ResponseEntity<List<Task>> listTasks() {
        List<Task> tasks = taskUseCase.getAllTasks().stream()
            .map(TaskMapper::toApiTask)
            .toList();
        return ResponseEntity.ok(tasks);
    }

    @Override
    public ResponseEntity<Task> getTask(Long taskId) {
        Task task = TaskMapper.toApiTask(taskUseCase.getTaskById(taskId));
        return ResponseEntity.ok(task);
    }

    @Override
    public ResponseEntity<Task> createTask(TaskCreateRequest taskCreateRequest) {
        var domainTask = taskUseCase.createTask(
            taskCreateRequest.getTitle(),
            nullableToString(taskCreateRequest.getDescription()),
            TaskMapper.toDomainTaskStatus(taskCreateRequest.getStatus())
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(TaskMapper.toApiTask(domainTask));
    }

    @Override
    public ResponseEntity<Task> updateTask(Long taskId, TaskUpdateRequest taskUpdateRequest) {
        var domainTask = taskUseCase.updateTask(
            taskId,
            taskUpdateRequest.getTitle(),
            nullableToString(taskUpdateRequest.getDescription()),
            TaskMapper.toDomainTaskStatus(taskUpdateRequest.getStatus())
        );
        return ResponseEntity.ok(TaskMapper.toApiTask(domainTask));
    }

    @Override
    public ResponseEntity<Void> deleteTask(Long taskId) {
        taskUseCase.deleteTask(taskId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<List<Task>> filterTasksByStatus(TaskStatusFilterRequest taskStatusFilterRequest) {
        var status = TaskMapper.toDomainTaskStatus(taskStatusFilterRequest.getStatus());
        List<Task> tasks = taskUseCase.getTasksByStatus(status).stream()
            .map(TaskMapper::toApiTask)
            .toList();
        return ResponseEntity.ok(tasks);
    }

    @Override
    public ResponseEntity<List<Task>> testWhenSentence() {
        UseCaseResult<List<com.example.domain.Task>> result = taskUseCase.testWhenSentenceResult();
        if (result instanceof UseCaseResult.Success<List<com.example.domain.Task>> success) {
            List<Task> apiTasks = success.data().stream()
                .map(TaskMapper::toApiTask)
                .toList();
            return ResponseEntity.ok(apiTasks);
        }

        UseCaseResult.Failure<List<com.example.domain.Task>> failure =
            (UseCaseResult.Failure<List<com.example.domain.Task>>) result;
        System.err.println("Error: " + failure.error());
        if (failure.exception() != null) {
            failure.exception().printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTaskNotFoundException(TaskNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setCode("TASK_NOT_FOUND");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    private static String nullableToString(JsonNullable<String> value) {
        return value == null || !value.isPresent() ? null : value.get();
    }
}
