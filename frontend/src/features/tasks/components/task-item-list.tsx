import type { Task } from '../../../types/generated'

interface TaskItemListProps {
  tasks: Task[]
}

export function TaskItemList({ tasks }: TaskItemListProps) {
  return (
    <ul className="task-list">
      {tasks.map((task) => (
        <li key={task.id} className="task-item">
          <span className="task-title">{task.title}</span>
          <span className="task-status">{task.status}</span>
        </li>
      ))}
    </ul>
  )
}
