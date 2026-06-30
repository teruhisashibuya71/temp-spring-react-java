package com.example.repository;

import com.example.domain.Task;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {

    @Query("""
        SELECT
            t."id",
            t."title",
            t."description",
            t."status",
            t."created_at",
            t."updated_at"
        FROM "tasks" t
        WHERE t."status" = :status
        ORDER BY t."id" DESC
        """)
    List<Task> findTasksByTaskStatus(@Param("status") String status);
}
