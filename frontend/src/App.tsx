import { useEffect, useState } from 'react'
import type { Task } from './types/generated'
import { tasksApi } from './api/tasksClient'
import './App.css'

function App() {
  const [tasks, setTasks] = useState<Task[]>([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState<string | null>(null)

  useEffect(() => {
    void (async () => {
      try {
        const data = await tasksApi.listTasks()
        setTasks(data)
      } catch (e) {
        setError(e instanceof Error ? e.message : 'タスク取得に失敗しました')
      } finally {
        setLoading(false)
      }
    })()
  }, [])

  return (
    <main className="app">
      <h1>タスク一覧</h1>
      <p className="lead">OpenAPI 生成型（TasksApi）から取得したデータです。</p>

      {loading && <p>読み込み中…</p>}
      {error && <p className="error">{error}</p>}

      {!loading && !error && (
        <ul className="task-list">
          {tasks.map((task) => (
            <li key={task.id} className="task-item">
              <span className="task-title">{task.title}</span>
              <span className="task-status">{task.status}</span>
            </li>
          ))}
        </ul>
      )}
    </main>
  )
}

export default App
