package com.example.repository;

import com.example.domain.TestTodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestTodoRepository extends JpaRepository<TestTodo, Long> {
}
