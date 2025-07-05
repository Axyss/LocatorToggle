package dev.axyss.locatorToggle

import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    override fun onEnable() {
        getCommand("locator")?.setExecutor(LocatorCommand())
    }
}
