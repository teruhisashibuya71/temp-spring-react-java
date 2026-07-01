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

    /**
     * テスト用PI 
     * todoリストを取得する
     * 
     * テスト用URL
     * http://localhost:8080/api/testTodos
     */
    @Override
    public ResponseEntity<List<TestTodo>> getTestTodoList() {
        
        // mapメソッドはstreamクラスのメソッドのため、 .stream() でstreamオブジェクトを作成してからmapメソッドを呼び出す
        List<TestTodo> testTodoList = testTodoService.getTestTodoList().stream()
            .map(TestTodoMapper::toApiTestTodo)
            .toList(); // ここだけjava16からの新しいAPI

        // 以下のラムダ式と同じ
        // .map(domainTestTodo -> TestTodoMapper.toApiTestTodo(domainTestTodo))
        // static メソッドを1つ渡す時の省略記法
        
        return ResponseEntity.ok(testTodoList);
    }
}
