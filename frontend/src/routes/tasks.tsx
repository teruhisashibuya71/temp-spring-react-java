import { useLoaderData } from 'react-router'
import Presenter from '../features/tasks'
import { fetchTasksFromBE } from '../features/tasks/functions/ssr-api'
import { buildTaskPageProps } from '../features/tasks/hooks/use-tasks'

/**
 * ssr関連の処理を呼びます
 * データはフックスに渡します
 * 
 * @returns BEから取得したデータ
 */
export async function loader() {
  const beData = await fetchTasksFromBE()
  return buildTaskPageProps(beData)
}

/**
 * フックス関連の処理を呼ぶ
 * 処理結果はプレゼンターに渡します
 * 
 * @returns フックスのprops
 */
export default function TasksRoute() {
  const props = useLoaderData<typeof loader>()
  return <Presenter {...props} />
}
