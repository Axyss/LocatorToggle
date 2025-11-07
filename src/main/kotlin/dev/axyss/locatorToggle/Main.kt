package dev.axyss.locatorToggle

import com.tchristofferson.configupdater.ConfigUpdater
import dev.axyss.locatorToggle.listeners.PlayerListener
import dev.axyss.locatorToggle.utils.Language
import dev.axyss.locatorToggle.utils.PapiExpansion
import org.bstats.bukkit.Metrics
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import co.aikar.commands.PaperCommandManager
import java.io.File
import java.util.Locale


class Main : JavaPlugin() {
    private lateinit var commandManager: PaperCommandManager

    override fun onEnable() {
        if (Bukkit.getUnsafe().dataVersion < 771) {
            logger.severe("Locator bar is not supported on this server version")
            Bukkit.getPluginManager().disablePlugin(this)
            return
        }
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            PapiExpansion(this).register()
        }

        saveDefaultConfig()
        Language.saveDefaultLang(this)
        LocatorBarManager.plugin = this
        Language.loadFile(this)
        ConfigUpdater.update(this, "config.yml", File(dataFolder, "config.yml"), listOf())
        ConfigUpdater.update(this, "lang.yml", File(dataFolder, "lang.yml"), listOf())
        reloadConfig()

        commandManager = PaperCommandManager(this)
        commandManager.locales.loadYamlLanguageFile("lang.yml", Locale.ENGLISH)
        commandManager.locales.defaultLocale = Locale.ENGLISH
        commandManager.registerCommand(LocatorCommand())

        Bukkit.getPluginManager().registerEvents(PlayerListener, this)

        // Partial plugman compatibility
        for (player in Bukkit.getOnlinePlayers()) {
            val locatorBar = LocatorBarManager(player)
            if (locatorBar.isDisabled()) {
                locatorBar.disableTemporarily()
            } else {
                locatorBar.enableTemporarily()
            }
        }
        val metrics = Metrics(this, 26388) // Telemetry
    }

    // Partial plugman compatibility
    override fun onDisable() {
        for (player in Bukkit.getOnlinePlayers()) {
            LocatorBarManager(player).enableTemporarily(false)
        }
    }
}
