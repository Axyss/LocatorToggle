package dev.axyss.locatorToggle

import dev.axyss.locatorToggle.commands.LocatorCommand
import dev.axyss.locatorToggle.utils.Language
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    override fun onEnable() {
        Language.saveDefaultLang(this)
        Language.loadFile(this)
        getCommand("locator")?.setExecutor(LocatorCommand())
    }
}
