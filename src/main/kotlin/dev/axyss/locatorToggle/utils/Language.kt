package dev.axyss.locatorToggle.utils

import org.bukkit.ChatColor
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

object Language {
    private lateinit var langFile: YamlConfiguration

    fun saveDefaultLang(plugin: JavaPlugin) {
        val langFile = File(plugin.dataFolder, "lang.yml")
        if (!langFile.exists()) {
            plugin.saveResource("lang.yml", false)
        }
    }

    fun loadFile(plugin: JavaPlugin) {
        val langFile = File(plugin.dataFolder, "lang.yml")
        Language.langFile = YamlConfiguration.loadConfiguration(langFile)
    }

    fun getMessage(messageKey: String): String {
        val message = langFile.getString("messages.$messageKey") ?: "&cMessage not found!"
        return ChatColor.translateAlternateColorCodes('&', message)
    }

    fun getPrefixedMessage(messageKey: String): String {
        return getMessage("plugin-prefix") + " " + ChatColor.RESET + getMessage(messageKey)
    }
}
