package dev.axyss.locatorToggle

import dev.axyss.locatorToggle.commands.LocatorCommand
import dev.axyss.locatorToggle.listeners.PlayerListener
import dev.axyss.locatorToggle.utils.Language
import org.bstats.bukkit.Metrics
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    override fun onEnable() {
        Language.saveDefaultLang(this)
        Language.loadFile(this)

        if (Bukkit.getUnsafe().dataVersion < 771) {
            logger.severe(Language.getMessage("not-supported"))
            Bukkit.getPluginManager().disablePlugin(this)
            return
        }

        Bukkit.getPluginManager().registerEvents(PlayerListener, this)
        Bukkit.getVersion()
        getCommand("locator")?.setExecutor(LocatorCommand())
        val metrics = Metrics(this, 26388)
    }
}
