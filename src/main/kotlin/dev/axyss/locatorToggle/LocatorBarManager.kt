package dev.axyss.locatorToggle

import org.bukkit.NamespacedKey
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Player
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.Plugin

private enum class BlockDistance(val value: Double) {
    WORLD_MAX(6.0e7),
    NONE(0.0)
}

class LocatorBarManager(private val player: Player) {
    private val receiveRangeAttr = player.getAttribute(Attribute.WAYPOINT_RECEIVE_RANGE)
    private val transmitRangeAttr = player.getAttribute(Attribute.WAYPOINT_TRANSMIT_RANGE)

    companion object {
        private val locatorStatusKey = NamespacedKey(plugin, "is-locator-enabled")
        lateinit var plugin: Plugin
    }

    init {
        if (!player.persistentDataContainer.has(locatorStatusKey)) {
            player.persistentDataContainer.set(locatorStatusKey, PersistentDataType.BOOLEAN, true)
        }
    }

    fun isEnabled(): Boolean {
        return player.persistentDataContainer.get(locatorStatusKey, PersistentDataType.BOOLEAN)!!
    }

    fun isDisabled(): Boolean { // Redundant method for better clarity
        return !isEnabled()
    }

    fun enable() {
        receiveRangeAttr?.baseValue = BlockDistance.WORLD_MAX.value
        transmitRangeAttr?.baseValue = BlockDistance.WORLD_MAX.value
        player.persistentDataContainer.set(locatorStatusKey, PersistentDataType.BOOLEAN, true)
    }

    fun disable() {
        receiveRangeAttr?.baseValue = BlockDistance.NONE.value
        transmitRangeAttr?.baseValue = BlockDistance.NONE.value
        player.persistentDataContainer.set(locatorStatusKey, PersistentDataType.BOOLEAN, false)
    }
}
