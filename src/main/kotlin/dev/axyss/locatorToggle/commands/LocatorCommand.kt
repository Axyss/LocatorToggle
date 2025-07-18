package dev.axyss.locatorToggle.commands

import dev.axyss.locatorToggle.utils.Language
import org.bukkit.attribute.Attribute
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

private enum class BlockDistance(val value: Double) {
    WORLD_MAX(6.0e7),
    NONE(0.0)
}

class LocatorCommand: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, subcommand: String, p3: Array<out String>): Boolean {
        if (sender !is Player) return false

        val receiveRangeAttr = sender.getAttribute(Attribute.WAYPOINT_RECEIVE_RANGE)
        val transmitRangeAttr = sender.getAttribute(Attribute.WAYPOINT_TRANSMIT_RANGE)

        if (receiveRangeAttr == null || transmitRangeAttr == null) {
            sender.sendMessage(Language.getMessage("not-supported"))
            return false
        }

        when (receiveRangeAttr.baseValue) {
            BlockDistance.WORLD_MAX.value -> {
                receiveRangeAttr.baseValue = BlockDistance.NONE.value
                transmitRangeAttr.baseValue = BlockDistance.NONE.value
                sender.sendMessage(Language.getMessage("locator-toggled-off"))
            }
            BlockDistance.NONE.value -> {
                receiveRangeAttr.baseValue = BlockDistance.WORLD_MAX.value
                transmitRangeAttr.baseValue = BlockDistance.WORLD_MAX.value
                sender.sendMessage(Language.getMessage("locator-toggled-on"))
            }
        }
        return true
    }
}
