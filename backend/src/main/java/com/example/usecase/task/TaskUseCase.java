package com.example.usecase.task;

import com.example.domain.Task;
import com.example.domain.TaskStatus;
import com.example.usecase.UseCaseResult;

import java.util.List;

public interface TaskUseCase {

    List<Task> getAllTasks();

    Task getTaskById(Long id);

    Task createTask(String title, String description, TaskStatus status);

    Task updateTask(Long id, String title, String description, TaskStatus status);

    void deleteTask(Long id);

    List<Task> getTasksByStatus(TaskStatus status);

    UseCaseResult<List<Task>> testWhenSentenceResult();
}
