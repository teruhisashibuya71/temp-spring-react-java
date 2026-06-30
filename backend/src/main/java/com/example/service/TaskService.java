package com.example.service;

import com.example.domain.Task;
import com.example.domain.TaskStatus;
import com.example.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
            .orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " not found"));
    }

    public Task createTask(String title, String description, TaskStatus status) {
        LocalDateTime now = LocalDateTime.now();
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setStatus(status);
        task.setCreatedAt(now);
        task.setUpdatedAt(now);
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, String title, String description, TaskStatus status) {
        Task existingTask = getTaskById(id);
        if (title != null) {
            existingTask.setTitle(title);
        }
        if (description != null) {
            existingTask.setDescription(description);
        }
        if (status != null) {
            existingTask.setStatus(status);
        }
        existingTask.setUpdatedAt(LocalDateTime.now());
        return taskRepository.save(existingTask);
    }

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException("Task with id " + id + " not found");
        }
        taskRepository.deleteById(id);
    }

    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatusOrderByIdDesc(status);
    }
}
