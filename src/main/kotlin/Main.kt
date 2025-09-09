package com.github.ringoame196

fun main(args: Array<String>) {
	val usage = "Usage: java -jar ScreenSizeChange.jar <windowName> <minimizeWidth> <minimizeHeight>"
	if (args.size < 3) {
		println(usage)
		return
	}

	val windowName = args[0]
	val minimizeWidth = args[1].toIntOrNull()
	val minimizeHeight = args[2].toIntOrNull()

	if (minimizeWidth == null || minimizeHeight == null) {
		println(usage)
		return
	}

	val windowManager = WindowManager(windowName, minimizeWidth, minimizeHeight)
	windowManager.changeWindow()
}

