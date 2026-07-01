package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.domain.TestTodo;
import com.example.repository.TestTodoRepository;

@Service
public class TestTodoService {

    private final TestTodoRepository testTodoRepository;

    public TestTodoService(TestTodoRepository testTodoRepository) {
        this.testTodoRepository = testTodoRepository;
    }

    /**
     * テスト用モデルである todoリストを取得する
     * 
     * @return List<TestTodo> テスト用モデルである todoリスト
     */
    public List<TestTodo> getTestTodoList() {
        return testTodoRepository.findAll();
    }
}
