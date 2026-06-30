import { Configuration, TasksApi } from '../../../types/generated'

/** クライアント側 API 通信（Vite プロキシ経由で BE に接続） */
export const tasksApi = new TasksApi(
  new Configuration({
    basePath: '',
  }),
)
