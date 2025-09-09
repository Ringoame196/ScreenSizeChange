package com.github.ringoame196

import com.sun.jna.platform.win32.User32
import com.sun.jna.platform.win32.WinDef

class WindowManager(
	val windowTitle: String,
	val minimizeWidth: Int,
	val minimizeHeight: Int
) {

	fun changeWindow() {
		val user32 = User32.INSTANCE
		val hwnd = user32.FindWindow(null, windowTitle)
		if (hwnd == null) {
			println("ウインドウが見つかりません: $windowTitle")
			return
		}

		// サイズ自動切り替え
		if (isMinimize(hwnd)) {
			// 最小化して最大化することで 変更を安定させる
			user32.ShowWindow(hwnd, User32.SW_HIDE)
			user32.ShowWindow(hwnd, User32.SW_MAXIMIZE)
		} else {
			// 指定サイズに変更
			user32.SetWindowPos(
				hwnd,
				null,
				0, 0,
				minimizeWidth,
				minimizeHeight,
				Data.SWP_NOZORDER or Data.SWP_NOACTIVATE
			)
		}

		// フォーカスも当てる
		user32.SetForegroundWindow(hwnd)

		println("${windowTitle}のサイズを変更しました")
	}


	private fun isMinimize(hwnd:WinDef.HWND): Boolean {
		val pair = getWindowSize(hwnd) ?: return false
		return pair == Pair(minimizeWidth,minimizeHeight)
	}

	private fun getWindowSize(hwnd:WinDef.HWND): Pair<Int, Int>? {
		val rect = WinDef.RECT()
		if (User32.INSTANCE.GetWindowRect(hwnd, rect)) {
			val width = rect.right - rect.left
			val height = rect.bottom - rect.top
			return width to height
		}
		return null
	}
}