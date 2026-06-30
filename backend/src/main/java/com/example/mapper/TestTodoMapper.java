package com.example.mapper;

import org.openapitools.jackson.nullable.JsonNullable;

public final class TestTodoMapper {

    private TestTodoMapper() {}

    public static com.example.generated.model.TestTodo toApiTestTodo(com.example.domain.TestTodo domainTestTodo) {
        com.example.generated.model.TestTodo apiTestTodo = new com.example.generated.model.TestTodo();
        apiTestTodo.setTitle(domainTestTodo.getTitle());
        if (domainTestTodo.getDescription() != null) {
            apiTestTodo.setDescription(JsonNullable.of(domainTestTodo.getDescription()));
        }
        return apiTestTodo;
    }
}
