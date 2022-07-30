package org.fgno.game

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = Lwjgl3ApplicationConfiguration()
        config.setTitle("fgno-game")
        config.setWindowedMode(800,480)
        config.useVsync(true)
        config.setForegroundFPS(60)
        Lwjgl3Application(FgnoGame(), config)
    }
}