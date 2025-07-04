package dev.axyss.locatorToggle.commands

import org.bukkit.attribute.Attribute
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

private enum class BlockDistance(val distance: Double) {
    WORLD_MAX(60_000_000.0),
    NONE(0.0)
}

class LocatorCommand: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, subcommand: String, p3: Array<out String>): Boolean {
        if (sender !is Player) return false

        var locationReceiveRange = sender.getAttribute(Attribute.WAYPOINT_RECEIVE_RANGE)?.value
        var locationTransmitRange = sender.getAttribute(Attribute.WAYPOINT_TRANSMIT_RANGE)?.value
        if (locationTransmitRange == null || locationReceiveRange == null) {
            sender.sendMessage("§cLocator is not supported on this server version.")
            return false
        }

        when (locationReceiveRange) {
            BlockDistance.WORLD_MAX.distance -> {
                locationReceiveRange = BlockDistance.NONE.distance
                locationTransmitRange = BlockDistance.WORLD_MAX.distance
                sender.sendMessage("§aLocator toggled §cOFF§a.")
            }
            BlockDistance.NONE.distance -> {
                locationReceiveRange = BlockDistance.WORLD_MAX.distance
                locationTransmitRange = BlockDistance.NONE.distance
                sender.sendMessage("§aLocator toggled §aON§a.")
            }
        }
        return true
    }
}
