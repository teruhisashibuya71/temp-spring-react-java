import type { Task } from '../../../types/generated'

export interface TaskPageProps {
  tasks: Task[]
  totalCount: number
}
