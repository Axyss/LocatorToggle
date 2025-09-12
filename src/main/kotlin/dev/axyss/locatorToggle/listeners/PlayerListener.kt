package dev.axyss.locatorToggle.listeners

import dev.axyss.locatorToggle.LocatorBarManager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

object PlayerListener : Listener {
    @EventHandler
    fun onPlayerJoin(event: org.bukkit.event.player.PlayerJoinEvent) {
        val locatorBar = LocatorBarManager(event.player)
        if (locatorBar.isDisabled()) {
            locatorBar.disable(true)
        }
    }

    @EventHandler
    fun onPlayerQuit(event: org.bukkit.event.player.PlayerQuitEvent) {
        val locatorBar = LocatorBarManager(event.player)
        if (locatorBar.isDisabled()) {
            locatorBar.enable(true)
        }
    }
}