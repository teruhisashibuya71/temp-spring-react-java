package com.example.usecase;

public sealed interface UseCaseResult<T> permits UseCaseResult.Success, UseCaseResult.Failure {

    record Success<T>(T data) implements UseCaseResult<T> {}

    record Failure<T>(String error, Exception exception) implements UseCaseResult<T> {
        public Failure(String error) {
            this(error, null);
        }
    }
}
