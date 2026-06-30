package com.example.usecase.task;

import com.example.domain.Task;
import com.example.domain.TaskStatus;
import com.example.repository.TaskRepository;
import com.example.usecase.UseCaseResult;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultTaskUseCase implements TaskUseCase {

    private final TaskRepository taskRepository;

    public DefaultTaskUseCase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        taskRepository.findAll().forEach(tasks::add);
        return tasks;
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
            .orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " not found"));
    }

    @Override
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

    @Override
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

    @Override
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException("Task with id " + id + " not found");
        }
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findTasksByTaskStatus(status.name());
    }

    @Override
    public UseCaseResult<List<Task>> testWhenSentenceResult() {
        try {
            List<Task> tasks = getAllTasks();
            if (tasks.isEmpty()) {
                return new UseCaseResult.Failure<>("タスクが見つかりませんでした");
            }
            return new UseCaseResult.Success<>(tasks);
        } catch (Exception e) {
            return new UseCaseResult.Failure<>(
                "タスクの取得中にエラーが発生しました: " + e.getMessage(),
                e
            );
        }
    }
}
