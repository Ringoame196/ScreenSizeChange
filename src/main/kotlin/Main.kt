package com.github.ringoame196

fun main(args: Array<String>) {
	val usage = "Usage: java -jar ScreenSizeChange.jar <windowName> (<minimizeWidth>) (<minimizeHeight>)"

	if (args.size == 1) {
		val windowTitle = args[0]
		sendWindowSize(windowTitle)
		println(usage)
		return
	}

	if (args.size < 3) {
		println(usage)
		return
	}

	val windowTitle = args[0]
	val minimizeWidth = args[1].toIntOrNull()
	val minimizeHeight = args[2].toIntOrNull()

	if (minimizeWidth == null || minimizeHeight == null) {
		println(usage)
		return
	}

	val windowManager = WindowManager(windowTitle, minimizeWidth, minimizeHeight)
	windowManager.changeWindow()
}

private fun sendWindowSize(windowName: String) {
	val size = WindowSizeHelper.getWindowSize(windowName)
	if (size != null) {
		println("ウィンドウ '$windowName' の現在のサイズ: 幅=${size.first}, 高さ=${size.second}")
	} else {
		println("ウィンドウ '$windowName' が見つかりませんでした")
	}
}
