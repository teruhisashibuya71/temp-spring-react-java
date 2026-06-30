import { TaskItemList } from './components/task-item-list'
import type { TaskPageProps } from './types/task-page-props'
import './styles/tasks-page.css'

/**
 * タスク一覧プレゼンター（props を受け取って表示するだけ）
 */
export default function Presenter(props: TaskPageProps) {
  return (
    <main className="tasks-page">
      <h1>タスク一覧</h1>
      <p className="lead">OpenAPI 生成型（TasksApi）から取得したデータです。</p>
      <p>合計: {props.totalCount}件</p>
      <TaskItemList tasks={props.tasks} />
    </main>
  )
}
