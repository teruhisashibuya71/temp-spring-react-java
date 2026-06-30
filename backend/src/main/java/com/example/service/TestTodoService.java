package com.example.service;

import com.example.domain.TestTodo;
import com.example.repository.TestTodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestTodoService {

    private final TestTodoRepository testTodoRepository;

    public TestTodoService(TestTodoRepository testTodoRepository) {
        this.testTodoRepository = testTodoRepository;
    }

    public List<TestTodo> getTestTodoList() {
        return testTodoRepository.findAll();
    }
}
