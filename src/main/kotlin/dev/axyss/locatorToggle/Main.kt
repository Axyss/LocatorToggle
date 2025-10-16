package dev.axyss.locatorToggle

import dev.axyss.locatorToggle.commands.LocatorCommand
import dev.axyss.locatorToggle.commands.RadiusCommand
import dev.axyss.locatorToggle.listeners.PlayerListener
import dev.axyss.locatorToggle.utils.Language
import org.bstats.bukkit.Metrics
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    override fun onEnable() {
        if (Bukkit.getUnsafe().dataVersion < 771) {
            logger.severe("Locator bar is not supported on this server version")
            Bukkit.getPluginManager().disablePlugin(this)
            return
        }

        LocatorBarManager.plugin = this
        Language.saveDefaultLang(this)
        Language.loadFile(this)
        Bukkit.getPluginManager().registerEvents(PlayerListener, this)
        getCommand("locator")?.setExecutor(LocatorCommand())
        getCommand("radius")?.setExecutor(RadiusCommand())

        // Partial plugman compatibility
        for (player in Bukkit.getOnlinePlayers()) {
            val locatorBar = LocatorBarManager(player)
            if (locatorBar.isDisabled()) {
                locatorBar.disableTemporarily()
            }
        }
        val metrics = Metrics(this, 26388) // Telemetry
    }

    // Partial plugman compatibility
    override fun onDisable() {
        for (player in Bukkit.getOnlinePlayers()) {
            val locatorBar = LocatorBarManager(player)
            if (locatorBar.isDisabled()) {
                locatorBar.enableTemporarily()
            }
        }
    }
}
