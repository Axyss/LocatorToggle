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
        lateinit var plugin: Plugin

        private fun getLocatorStatusKey(): NamespacedKey {
            return NamespacedKey(plugin, "is-locator-enabled")
        }
    }

    init {
        val locatorStatusKey = getLocatorStatusKey()
        if (!player.persistentDataContainer.has(locatorStatusKey)) {
            player.persistentDataContainer.set(locatorStatusKey, PersistentDataType.BOOLEAN, true)
        }
    }

    fun isEnabled(): Boolean {
        return player.persistentDataContainer.get(getLocatorStatusKey(), PersistentDataType.BOOLEAN)!!
    }

    fun isDisabled(): Boolean { // Redundant method for better clarity
        return !isEnabled()
    }

    // The temporal attribute is used to enable/disable the locator bar when player joins/quits without changing
    // their preference. This prevents plugin behaviour from persisting after unloading or removing.
    fun enable(temporal: Boolean = false) {
        receiveRangeAttr?.baseValue = BlockDistance.WORLD_MAX.value
        transmitRangeAttr?.baseValue = BlockDistance.WORLD_MAX.value
        if (!temporal) {
            player.persistentDataContainer.set(getLocatorStatusKey(), PersistentDataType.BOOLEAN, true)
        }
    }

    fun disable(temporal: Boolean = false) {
        receiveRangeAttr?.baseValue = BlockDistance.NONE.value
        transmitRangeAttr?.baseValue = BlockDistance.NONE.value
        if (!temporal) {
            player.persistentDataContainer.set(getLocatorStatusKey(), PersistentDataType.BOOLEAN, false)
        }
    }
}
