package dev.axyss.locatorToggle

import org.bukkit.Bukkit
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

        private fun getLocatorRadiusKey(): NamespacedKey {
            return NamespacedKey(plugin, "locator-radius")
        }
    }

    init {
        val locatorStatusKey = getLocatorStatusKey()
        if (!player.persistentDataContainer.has(locatorStatusKey)) {
            player.persistentDataContainer.set(locatorStatusKey, PersistentDataType.BOOLEAN, !plugin.config.getBoolean("locator-disabled-by-default"))
        }
        // LocatorRadiusKey should not be initialized in case Mojang modifies the default radius
    }

    fun isEnabled(): Boolean {
        return player.persistentDataContainer.get(getLocatorStatusKey(), PersistentDataType.BOOLEAN)!!
    }

    fun isDisabled(): Boolean { // Redundant method for better clarity
        return !isEnabled()
    }

    private fun hasRadius(): Boolean {
        return player.persistentDataContainer.has(getLocatorRadiusKey(), PersistentDataType.DOUBLE)
    }

    fun getRadius(): Double? {
        return player.persistentDataContainer.get(getLocatorRadiusKey(), PersistentDataType.DOUBLE)
    }

    fun enable() {
        enableTemporarily()
        player.persistentDataContainer.set(getLocatorStatusKey(), PersistentDataType.BOOLEAN, true)
    }

    fun disable() {
        disableTemporarily()
        player.persistentDataContainer.set(getLocatorStatusKey(), PersistentDataType.BOOLEAN, false)
    }

    // The temporary counterparts are used to enable/disable the locator bar when player joins/quits without changing
    // their preference. This prevents plugin behaviour from persisting after its removal.
    fun enableTemporarily(useCustomRadius: Boolean = true) {
        if (useCustomRadius && hasRadius()) {
            receiveRangeAttr?.baseValue = getRadius()!!
            transmitRangeAttr?.baseValue = getRadius()!!
        } else {
            val defaultRadius = plugin.config.getDouble("default-locator-radius", BlockDistance.WORLD_MAX.value)
            receiveRangeAttr?.baseValue = defaultRadius
            transmitRangeAttr?.baseValue = defaultRadius
        }
    }

    fun disableTemporarily() {
        receiveRangeAttr?.baseValue = BlockDistance.NONE.value
        transmitRangeAttr?.baseValue = BlockDistance.NONE.value
    }

    fun setRadius(radius: Double) {
        player.persistentDataContainer.set(getLocatorRadiusKey(), PersistentDataType.DOUBLE, radius)
        if (this.isEnabled()) {
            Bukkit.getScheduler().runTaskLater(plugin, Runnable { this.enableTemporarily() }, 2L)
        }
    }
}
