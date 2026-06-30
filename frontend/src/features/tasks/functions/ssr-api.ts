import type { Task } from '../../../types/generated'

const BE_API_URL = process.env.BE_API_URL ?? 'http://localhost:8080'

/**
 * SSR 時の BE API 通信（loader から呼ばれる）
 */
export async function fetchTasksFromBE(): Promise<Task[]> {
  const response = await fetch(`${BE_API_URL}/api/tasks`)

  if (!response.ok) {
    throw new Response(`BE Error: ${response.status}`, { status: response.status })
  }

  return (await response.json()) as Task[]
}
