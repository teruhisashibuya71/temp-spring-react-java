import { Configuration, TasksApi } from '../types/generated'

/** OpenAPI 生成クライアント（Vite プロキシ経由で BE に接続） */
export const tasksApi = new TasksApi(
  new Configuration({
    basePath: '',
  }),
)
