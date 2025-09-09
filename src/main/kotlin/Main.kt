package com.github.ringoame196

import java.io.File

fun main(args: Array<String>) {
	val usage = "Usage: java -jar ScreenSizeChange.jar <windowName> (<minimizeWidth>) (<minimizeHeight>)"

	if (args.size == 1) {
		val windowTitle = args[0]
		val windowSize = WindowSizeHelper.getWindowSize(windowTitle)
		if (windowSize != null) {
			sendWindowSize(windowSize)
			makeRunBatFile(windowTitle,windowSize)
		} else {
			println("ウィンドウが見つかりませんでした")
		}
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
	val minimizeSize = WindowSize(minimizeWidth,minimizeHeight)

	val windowManager = WindowManager(windowTitle, minimizeSize)
	windowManager.changeWindow()
}

private fun sendWindowSize(size: WindowSize) {
	println("現在のサイズ: 幅=${size.width}, 高さ=${size.height}")
}

private fun makeRunBatFile(windowTitle:String,size: WindowSize) {
	val jarName = getJarName()
	if (jarName != null) {
		val batFile = File("./run-${windowTitle}.bat")
		batFile.writeText("java -jar $jarName $windowTitle ${size.width} ${size.height}")
		println("起動用のbatを生成しました")
	} else {
		println("jarファイルの取得に失敗しました")
	}
}

private fun getJarName(): String? {
	val codeSource = object {}.javaClass.protectionDomain.codeSource
	return codeSource?.location?.path?.substringAfterLast("/") // パスの最後を取得
}
