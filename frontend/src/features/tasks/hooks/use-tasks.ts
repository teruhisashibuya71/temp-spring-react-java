import type { Task } from '../../../types/generated'
import type { TaskPageProps } from '../types/task-page-props'

/**
 * BE から取得した生データを加工する（loader から呼ばれる純粋関数）
 */
export function buildTaskPageProps(tasks: Task[]): TaskPageProps {
  return {
    tasks,
    totalCount: tasks.length,
  }
}
