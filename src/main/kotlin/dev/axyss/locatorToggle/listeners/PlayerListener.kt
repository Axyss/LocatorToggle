package dev.axyss.locatorToggle.listeners

import dev.axyss.locatorToggle.commands.BlockDistance
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

object PlayerListener : Listener {
    @EventHandler
    fun onPlayerJoin(event: org.bukkit.event.player.PlayerJoinEvent) {
        val player = event.player
        val receiveRangeAttr = player.getAttribute(org.bukkit.attribute.Attribute.WAYPOINT_RECEIVE_RANGE)
        val transmitRangeAttr = player.getAttribute(org.bukkit.attribute.Attribute.WAYPOINT_TRANSMIT_RANGE)

        receiveRangeAttr?.baseValue = BlockDistance.NONE.value
        transmitRangeAttr?.baseValue = BlockDistance.NONE.value
    }

    @EventHandler
    fun onPlayerQuit(event: org.bukkit.event.player.PlayerQuitEvent) {
        val player = event.player
        val receiveRangeAttr = player.getAttribute(org.bukkit.attribute.Attribute.WAYPOINT_RECEIVE_RANGE)
        val transmitRangeAttr = player.getAttribute(org.bukkit.attribute.Attribute.WAYPOINT_TRANSMIT_RANGE)

        receiveRangeAttr?.baseValue = BlockDistance.WORLD_MAX.value
        transmitRangeAttr?.baseValue = BlockDistance.WORLD_MAX.value
    }
}