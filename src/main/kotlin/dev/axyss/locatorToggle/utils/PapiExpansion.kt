package dev.axyss.locatorToggle.utils

import dev.axyss.locatorToggle.LocatorBarManager
import me.clip.placeholderapi.expansion.PlaceholderExpansion
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin

class PapiExpansion(private val plugin: Plugin) : PlaceholderExpansion() {

    override fun getAuthor(): String {
        return plugin.description.authors.joinToString(" ")
    }

    override fun getIdentifier(): String {
        return "locatortoggle"
    }

    override fun getVersion(): String {
        return plugin.description.version
    }

    override fun persist(): Boolean {
        return true
    }

    override fun onPlaceholderRequest(player: Player?, params: String): String? {
        player ?: return null

        return when {
            params.equals("status", ignoreCase = true) -> {
                LocatorBarManager(player).isEnabled().toString()
            }
            params.equals("radius", ignoreCase = true) -> {
                LocatorBarManager(player).getRadius().toString()
            }
            else -> null
        }
    }
}

