package com.github.ringoame196

import com.sun.jna.platform.win32.User32
import com.sun.jna.platform.win32.WinDef

object WindowSizeHelper {
	fun getWindowSize(windowTitle: String): WindowSize? {
		val user32 = User32.INSTANCE
		val hwnd = user32.FindWindow(null, windowTitle)
		return getWindowSize(hwnd)
	}

	fun getWindowSize(hwnd: WinDef.HWND): WindowSize? {
		val rect = WinDef.RECT()
		if (User32.INSTANCE.GetWindowRect(hwnd, rect)) {
			val width = rect.right - rect.left
			val height = rect.bottom - rect.top
			return WindowSize(width,height)
		}
		return null
	}
}