package dev.axyss.locatorToggle

import dev.axyss.locatorToggle.commands.LocatorCommand
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    override fun onEnable() {
        getCommand("locator")?.setExecutor(LocatorCommand())
    }
}
