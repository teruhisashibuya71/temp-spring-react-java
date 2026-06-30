package com.example.mapper;

import com.example.domain.Task;
import com.example.domain.TaskStatus;
import com.example.generated.model.TaskCreateRequest;
import com.example.generated.model.TaskStatusFilterRequest;
import com.example.generated.model.TaskUpdateRequest;
import org.openapitools.jackson.nullable.JsonNullable;

import java.time.ZoneOffset;

public final class TaskMapper {

    private TaskMapper() {}

    public static com.example.generated.model.Task toApiTask(Task domainTask) {
        if (domainTask.getId() == null) {
            throw new IllegalArgumentException("Task ID is required");
        }

        com.example.generated.model.Task apiTask = new com.example.generated.model.Task();
        apiTask.setId(domainTask.getId());
        apiTask.setTitle(domainTask.getTitle());
        if (domainTask.getDescription() != null) {
            apiTask.setDescription(JsonNullable.of(domainTask.getDescription()));
        }
        apiTask.setStatus(toApiStatus(domainTask.getStatus()));
        apiTask.setCreatedAt(domainTask.getCreatedAt().atOffset(ZoneOffset.UTC));
        apiTask.setUpdatedAt(domainTask.getUpdatedAt().atOffset(ZoneOffset.UTC));
        return apiTask;
    }

    public static TaskStatus toDomainTaskStatus(TaskCreateRequest.StatusEnum status) {
        if (status == null) {
            return TaskStatus.TODO;
        }
        return switch (status) {
            case TODO -> TaskStatus.TODO;
            case IN_PROGRESS -> TaskStatus.IN_PROGRESS;
            case DONE -> TaskStatus.DONE;
        };
    }

    public static TaskStatus toDomainTaskStatus(TaskUpdateRequest.StatusEnum status) {
        if (status == null) {
            return null;
        }
        return switch (status) {
            case TODO -> TaskStatus.TODO;
            case IN_PROGRESS -> TaskStatus.IN_PROGRESS;
            case DONE -> TaskStatus.DONE;
        };
    }

    public static TaskStatus toDomainTaskStatus(TaskStatusFilterRequest.StatusEnum status) {
        return switch (status) {
            case TODO -> TaskStatus.TODO;
            case IN_PROGRESS -> TaskStatus.IN_PROGRESS;
            case DONE -> TaskStatus.DONE;
        };
    }

    private static com.example.generated.model.Task.StatusEnum toApiStatus(TaskStatus status) {
        return switch (status) {
            case TODO -> com.example.generated.model.Task.StatusEnum.TODO;
            case IN_PROGRESS -> com.example.generated.model.Task.StatusEnum.IN_PROGRESS;
            case DONE -> com.example.generated.model.Task.StatusEnum.DONE;
        };
    }
}
