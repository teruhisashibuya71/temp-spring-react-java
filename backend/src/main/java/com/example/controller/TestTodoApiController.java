package com.example.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.generated.api.TestTodosApi;
import com.example.generated.model.TestTodo;
import com.example.mapper.TestTodoMapper;
import com.example.service.TestTodoService;

@RestController
public class TestTodoApiController implements TestTodosApi {

    private final TestTodoService testTodoService;

    public TestTodoApiController(TestTodoService testTodoService) {
        this.testTodoService = testTodoService;
    }

    @Override
    public ResponseEntity<List<TestTodo>> getTestTodoList() {
        List<TestTodo> testTodoList = testTodoService.getTestTodoList().stream()
            .map(TestTodoMapper::toApiTestTodo)
            .toList();
        return ResponseEntity.ok(testTodoList);
    }
}
