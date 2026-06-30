import { type RouteConfig, route } from '@react-router/dev/routes'

export default [
  // テスト実装
  route('/tasks', './routes/tasks.tsx'),
  // 手動実装
  //route('/testTodos', './routes/testTodos.tsx'),
] satisfies RouteConfig
