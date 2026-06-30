package com.example.controller;

import java.util.Collections;
import java.util.List;

import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.example.generated.api.TasksApi;
import com.example.generated.model.ErrorResponse;
import com.example.generated.model.Task;
import com.example.generated.model.TaskCreateRequest;
import com.example.generated.model.TaskStatusFilterRequest;
import com.example.generated.model.TaskUpdateRequest;
import com.example.mapper.TaskMapper;
import com.example.service.TaskNotFoundException;
import com.example.service.TaskService;

@RestController
public class TasksApiController implements TasksApi {

    private final TaskService taskService;

    public TasksApiController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public ResponseEntity<List<Task>> listTasks() {
        List<Task> tasks = taskService.getAllTasks().stream()
            .map(TaskMapper::toApiTask)
            .toList();
        return ResponseEntity.ok(tasks);
    }

    @Override
    public ResponseEntity<Task> getTask(Long taskId) {
        Task task = TaskMapper.toApiTask(taskService.getTaskById(taskId));
        return ResponseEntity.ok(task);
    }

    @Override
    public ResponseEntity<Task> createTask(TaskCreateRequest taskCreateRequest) {
        var domainTask = taskService.createTask(
            taskCreateRequest.getTitle(),
            nullableToString(taskCreateRequest.getDescription()),
            TaskMapper.toDomainTaskStatus(taskCreateRequest.getStatus())
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(TaskMapper.toApiTask(domainTask));
    }

    @Override
    public ResponseEntity<Task> updateTask(Long taskId, TaskUpdateRequest taskUpdateRequest) {
        var domainTask = taskService.updateTask(
            taskId,
            taskUpdateRequest.getTitle(),
            nullableToString(taskUpdateRequest.getDescription()),
            TaskMapper.toDomainTaskStatus(taskUpdateRequest.getStatus())
        );
        return ResponseEntity.ok(TaskMapper.toApiTask(domainTask));
    }

    @Override
    public ResponseEntity<Void> deleteTask(Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<List<Task>> filterTasksByStatus(TaskStatusFilterRequest taskStatusFilterRequest) {
        var status = TaskMapper.toDomainTaskStatus(taskStatusFilterRequest.getStatus());
        List<Task> tasks = taskService.getTasksByStatus(status).stream()
            .map(TaskMapper::toApiTask)
            .toList();
        return ResponseEntity.ok(tasks);
    }

    @Override
    public ResponseEntity<List<Task>> testWhenSentence() {
        try {
            List<com.example.domain.Task> domainTasks = taskService.getAllTasks();
            if (domainTasks.isEmpty()) {
                System.err.println("Error: タスクが見つかりませんでした");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
            }
            List<Task> apiTasks = domainTasks.stream()
                .map(TaskMapper::toApiTask)
                .toList();
            return ResponseEntity.ok(apiTasks);
        } catch (Exception e) {
            System.err.println("Error: タスクの取得中にエラーが発生しました: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
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
