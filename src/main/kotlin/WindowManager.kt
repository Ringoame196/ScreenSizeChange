package com.github.ringoame196

import com.sun.jna.platform.win32.User32
import com.sun.jna.platform.win32.WinDef

object WindowManager {
	const val SWP_NOZORDER = 0x0004
	const val SWP_NOACTIVATE = 0x0010
	const val MINIMIZE_WIDTH = 136
	const val MINIMIZE_HEIGHT = 128

	fun changeWindow(windowTitle: String) {
		val hwnd = User32.INSTANCE.FindWindow(null, windowTitle)
		if (hwnd == null) {
			println("ウインドウが見つかりません: $windowTitle")
			return
		}
		// サイズ自動切り替え

		if (isMinimize(hwnd)) {
			User32.INSTANCE.ShowWindow(hwnd, User32.SW_MAXIMIZE)
		}
		else {
			minimizeWindow(hwnd)
		}
		User32.INSTANCE.SetForegroundWindow(hwnd) // フォーカスも当てる
		println("ウインドウのサイズを変更しました")
	}

	fun minimizeWindow(hwnd:WinDef.HWND) {
		val user32 = User32.INSTANCE
		// 位置 (X=0, Y=0) に 最小のサイズで移動
		user32.SetWindowPos(hwnd, null, 0, 0, MINIMIZE_WIDTH, MINIMIZE_HEIGHT, SWP_NOZORDER or SWP_NOACTIVATE)
	}

	fun isMinimize(hwnd:WinDef.HWND): Boolean {
		val pair = getWindowSize(hwnd) ?: return false
		return pair == Pair(MINIMIZE_WIDTH,MINIMIZE_HEIGHT)
	}

	fun getWindowSize(hwnd:WinDef.HWND): Pair<Int, Int>? {
		val rect = WinDef.RECT()
		if (User32.INSTANCE.GetWindowRect(hwnd, rect)) {
			val width = rect.right - rect.left
			val height = rect.bottom - rect.top
			return width to height
		}
		return null
	}
}