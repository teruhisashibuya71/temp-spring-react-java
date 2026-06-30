# TasksApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createTask**](TasksApi.md#createtask) | **POST** /api/tasks | タスク作成 |
| [**deleteTask**](TasksApi.md#deletetask) | **DELETE** /api/tasks/{task_id} | タスク削除 |
| [**filterTasksByStatus**](TasksApi.md#filtertasksbystatus) | **GET** /api/tasks/status | タスクステータスでフィルタリング |
| [**getTask**](TasksApi.md#gettask) | **GET** /api/tasks/{task_id} | タスク詳細取得 |
| [**listTasks**](TasksApi.md#listtasks) | **GET** /api/tasks | タスク一覧取得 |
| [**testWhenSentence**](TasksApi.md#testwhensentence) | **GET** /api/tests/whenSentence | when句のテスト |
| [**updateTask**](TasksApi.md#updatetask) | **PUT** /api/tasks/{task_id} | タスク更新 |



## createTask

> Task createTask(taskCreateRequest)

タスク作成

新しいタスクを作成します

### Example

```ts
import {
  Configuration,
  TasksApi,
} from '';
import type { CreateTaskRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new TasksApi();

  const body = {
    // TaskCreateRequest
    taskCreateRequest: ...,
  } satisfies CreateTaskRequest;

  try {
    const data = await api.createTask(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **taskCreateRequest** | [TaskCreateRequest](TaskCreateRequest.md) |  | |

### Return type

[**Task**](Task.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `application/json`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | 作成成功 |  -  |
| **400** | バリデーションエラー |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## deleteTask

> deleteTask(taskId)

タスク削除

指定されたIDのタスクを削除します

### Example

```ts
import {
  Configuration,
  TasksApi,
} from '';
import type { DeleteTaskRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new TasksApi();

  const body = {
    // number | タスクID
    taskId: 789,
  } satisfies DeleteTaskRequest;

  try {
    const data = await api.deleteTask(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **taskId** | `number` | タスクID | [Defaults to `undefined`] |

### Return type

`void` (Empty response body)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `application/json`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **204** | 削除成功 |  -  |
| **404** | タスクが見つかりません |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## filterTasksByStatus

> Array&lt;Task&gt; filterTasksByStatus(taskStatusFilterRequest)

タスクステータスでフィルタリング

指定されたステータスのタスクを取得します

### Example

```ts
import {
  Configuration,
  TasksApi,
} from '';
import type { FilterTasksByStatusRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new TasksApi();

  const body = {
    // TaskStatusFilterRequest
    taskStatusFilterRequest: ...,
  } satisfies FilterTasksByStatusRequest;

  try {
    const data = await api.filterTasksByStatus(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **taskStatusFilterRequest** | [TaskStatusFilterRequest](TaskStatusFilterRequest.md) |  | |

### Return type

[**Array&lt;Task&gt;**](Task.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `application/json`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | 成功 |  -  |
| **400** | バリデーションエラー |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## getTask

> Task getTask(taskId)

タスク詳細取得

指定されたIDのタスクを取得します

### Example

```ts
import {
  Configuration,
  TasksApi,
} from '';
import type { GetTaskRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new TasksApi();

  const body = {
    // number | タスクID
    taskId: 789,
  } satisfies GetTaskRequest;

  try {
    const data = await api.getTask(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **taskId** | `number` | タスクID | [Defaults to `undefined`] |

### Return type

[**Task**](Task.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `application/json`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | 成功 |  -  |
| **404** | タスクが見つかりません |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## listTasks

> Array&lt;Task&gt; listTasks()

タスク一覧取得

すべてのタスクを取得します

### Example

```ts
import {
  Configuration,
  TasksApi,
} from '';
import type { ListTasksRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new TasksApi();

  try {
    const data = await api.listTasks();
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters

This endpoint does not need any parameter.

### Return type

[**Array&lt;Task&gt;**](Task.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `application/json`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | 成功 |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## testWhenSentence

> Array&lt;Task&gt; testWhenSentence()

when句のテスト

すべてのタスクを取得します

### Example

```ts
import {
  Configuration,
  TasksApi,
} from '';
import type { TestWhenSentenceRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new TasksApi();

  try {
    const data = await api.testWhenSentence();
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters

This endpoint does not need any parameter.

### Return type

[**Array&lt;Task&gt;**](Task.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `application/json`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | 成功 |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## updateTask

> Task updateTask(taskId, taskUpdateRequest)

タスク更新

指定されたIDのタスクを更新します

### Example

```ts
import {
  Configuration,
  TasksApi,
} from '';
import type { UpdateTaskRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new TasksApi();

  const body = {
    // number | タスクID
    taskId: 789,
    // TaskUpdateRequest
    taskUpdateRequest: ...,
  } satisfies UpdateTaskRequest;

  try {
    const data = await api.updateTask(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **taskId** | `number` | タスクID | [Defaults to `undefined`] |
| **taskUpdateRequest** | [TaskUpdateRequest](TaskUpdateRequest.md) |  | |

### Return type

[**Task**](Task.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `application/json`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | 更新成功 |  -  |
| **404** | タスクが見つかりません |  -  |
| **400** | バリデーションエラー |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)

