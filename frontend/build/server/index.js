import { PassThrough } from "node:stream";
import { createReadableStreamFromReadable } from "@react-router/node";
import { Links, Meta, Outlet, Scripts, ScrollRestoration, ServerRouter, UNSAFE_withComponentProps, useLoaderData } from "react-router";
import { isbot } from "isbot";
import { renderToPipeableStream } from "react-dom/server";
import { jsx, jsxs } from "react/jsx-runtime";
//#region \0rolldown/runtime.js
var __defProp = Object.defineProperty;
var __exportAll = (all, no_symbols) => {
	let target = {};
	for (var name in all) __defProp(target, name, {
		get: all[name],
		enumerable: true
	});
	if (!no_symbols) __defProp(target, Symbol.toStringTag, { value: "Module" });
	return target;
};
//#endregion
//#region node_modules/@react-router/dev/dist/config/defaults/entry.server.node.tsx
var entry_server_node_exports = /* @__PURE__ */ __exportAll({
	default: () => handleRequest,
	streamTimeout: () => streamTimeout
});
var streamTimeout = 5e3;
function handleRequest(request, responseStatusCode, responseHeaders, routerContext, loadContext) {
	if (request.method.toUpperCase() === "HEAD") return new Response(null, {
		status: responseStatusCode,
		headers: responseHeaders
	});
	return new Promise((resolve, reject) => {
		let shellRendered = false;
		let userAgent = request.headers.get("user-agent");
		let readyOption = userAgent && isbot(userAgent) || routerContext.isSpaMode ? "onAllReady" : "onShellReady";
		let timeoutId = setTimeout(() => abort(), 6e3);
		const { pipe, abort } = renderToPipeableStream(/* @__PURE__ */ jsx(ServerRouter, {
			context: routerContext,
			url: request.url
		}), {
			[readyOption]() {
				shellRendered = true;
				const body = new PassThrough({ final(callback) {
					clearTimeout(timeoutId);
					timeoutId = void 0;
					callback();
				} });
				const stream = createReadableStreamFromReadable(body);
				responseHeaders.set("Content-Type", "text/html");
				pipe(body);
				resolve(new Response(stream, {
					headers: responseHeaders,
					status: responseStatusCode
				}));
			},
			onShellError(error) {
				reject(error);
			},
			onError(error) {
				responseStatusCode = 500;
				if (shellRendered) console.error(error);
			}
		});
	});
}
//#endregion
//#region src/root.tsx
var root_exports = /* @__PURE__ */ __exportAll({
	Layout: () => Layout,
	default: () => root_default
});
function Layout({ children }) {
	return /* @__PURE__ */ jsxs("html", {
		lang: "ja",
		children: [/* @__PURE__ */ jsxs("head", { children: [
			/* @__PURE__ */ jsx("meta", { charSet: "utf-8" }),
			/* @__PURE__ */ jsx("meta", {
				name: "viewport",
				content: "width=device-width, initial-scale=1.0"
			}),
			/* @__PURE__ */ jsx("title", { children: "temp-spring-react-java" }),
			/* @__PURE__ */ jsx(Meta, {}),
			/* @__PURE__ */ jsx(Links, {})
		] }), /* @__PURE__ */ jsxs("body", { children: [
			children,
			/* @__PURE__ */ jsx(ScrollRestoration, {}),
			/* @__PURE__ */ jsx(Scripts, {})
		] })]
	});
}
var root_default = UNSAFE_withComponentProps(function Root() {
	return /* @__PURE__ */ jsx(Outlet, {});
});
//#endregion
//#region src/features/tasks/components/task-item-list.tsx
function TaskItemList({ tasks }) {
	return /* @__PURE__ */ jsx("ul", {
		className: "task-list",
		children: tasks.map((task) => /* @__PURE__ */ jsxs("li", {
			className: "task-item",
			children: [/* @__PURE__ */ jsx("span", {
				className: "task-title",
				children: task.title
			}), /* @__PURE__ */ jsx("span", {
				className: "task-status",
				children: task.status
			})]
		}, task.id))
	});
}
//#endregion
//#region src/features/tasks/index.tsx
/**
* タスク一覧プレゼンター（props を受け取って表示するだけ）
*/
function Presenter(props) {
	return /* @__PURE__ */ jsxs("main", {
		className: "tasks-page",
		children: [
			/* @__PURE__ */ jsx("h1", { children: "タスク一覧" }),
			/* @__PURE__ */ jsx("p", {
				className: "lead",
				children: "OpenAPI 生成型（TasksApi）から取得したデータです。"
			}),
			/* @__PURE__ */ jsxs("p", { children: [
				"合計: ",
				props.totalCount,
				"件"
			] }),
			/* @__PURE__ */ jsx(TaskItemList, { tasks: props.tasks })
		]
	});
}
//#endregion
//#region src/features/tasks/functions/ssr-api.ts
var BE_API_URL = process.env.BE_API_URL ?? "http://localhost:8080";
/**
* SSR 時の BE API 通信（loader から呼ばれる）
*/
async function fetchTasksFromBE() {
	const response = await fetch(`${BE_API_URL}/api/tasks`);
	if (!response.ok) throw new Response(`BE Error: ${response.status}`, { status: response.status });
	return await response.json();
}
//#endregion
//#region src/features/tasks/hooks/use-tasks.ts
/**
* BE から取得した生データを加工する（loader から呼ばれる純粋関数）
*/
function buildTaskPageProps(tasks) {
	return {
		tasks,
		totalCount: tasks.length
	};
}
//#endregion
//#region src/routes/tasks.tsx
var tasks_exports = /* @__PURE__ */ __exportAll({
	default: () => tasks_default,
	loader: () => loader
});
async function loader() {
	return buildTaskPageProps(await fetchTasksFromBE());
}
var tasks_default = UNSAFE_withComponentProps(function TasksRoute() {
	return /* @__PURE__ */ jsx(Presenter, { ...useLoaderData() });
});
//#endregion
//#region \0virtual:react-router/server-manifest
var server_manifest_default = {
	"entry": {
		"module": "/assets/entry.client-Dru20kDT.js",
		"imports": ["/assets/jsx-runtime-DUlKwb0C.js"],
		"css": []
	},
	"routes": {
		"root": {
			"id": "root",
			"parentId": void 0,
			"path": "",
			"index": void 0,
			"caseSensitive": void 0,
			"hasAction": false,
			"hasLoader": false,
			"hasClientAction": false,
			"hasClientLoader": false,
			"hasClientMiddleware": false,
			"hasDefaultExport": true,
			"hasErrorBoundary": false,
			"module": "/assets/root-BFrsRR9X.js",
			"imports": ["/assets/jsx-runtime-DUlKwb0C.js"],
			"css": ["/assets/root-BjU2VVL2.css"],
			"clientActionModule": void 0,
			"clientLoaderModule": void 0,
			"clientMiddlewareModule": void 0,
			"hydrateFallbackModule": void 0
		},
		"routes/tasks": {
			"id": "routes/tasks",
			"parentId": "root",
			"path": "/tasks",
			"index": void 0,
			"caseSensitive": void 0,
			"hasAction": false,
			"hasLoader": true,
			"hasClientAction": false,
			"hasClientLoader": false,
			"hasClientMiddleware": false,
			"hasDefaultExport": true,
			"hasErrorBoundary": false,
			"module": "/assets/tasks-D8MnmmNj.js",
			"imports": ["/assets/jsx-runtime-DUlKwb0C.js"],
			"css": ["/assets/tasks-Oo_qTNcV.css"],
			"clientActionModule": void 0,
			"clientLoaderModule": void 0,
			"clientMiddlewareModule": void 0,
			"hydrateFallbackModule": void 0
		}
	},
	"url": "/assets/manifest-4233c8de.js",
	"version": "4233c8de",
	"sri": void 0
};
//#endregion
//#region \0virtual:react-router/server-build
var assetsBuildDirectory = "build/client";
var basename = "/";
var future = {
	"unstable_optimizeDeps": false,
	"v8_passThroughRequests": false,
	"v8_trailingSlashAwareDataRequests": false,
	"unstable_previewServerPrerendering": false,
	"v8_middleware": false,
	"v8_splitRouteModules": false,
	"v8_viteEnvironmentApi": false
};
var ssr = true;
var isSpaMode = false;
var prerender = [];
var routeDiscovery = {
	"mode": "lazy",
	"manifestPath": "/__manifest"
};
var publicPath = "/";
var entry = { module: entry_server_node_exports };
var routes = {
	"root": {
		id: "root",
		parentId: void 0,
		path: "",
		index: void 0,
		caseSensitive: void 0,
		module: root_exports
	},
	"routes/tasks": {
		id: "routes/tasks",
		parentId: "root",
		path: "/tasks",
		index: void 0,
		caseSensitive: void 0,
		module: tasks_exports
	}
};
var allowedActionOrigins = false;
//#endregion
export { allowedActionOrigins, server_manifest_default as assets, assetsBuildDirectory, basename, entry, future, isSpaMode, prerender, publicPath, routeDiscovery, routes, ssr };
