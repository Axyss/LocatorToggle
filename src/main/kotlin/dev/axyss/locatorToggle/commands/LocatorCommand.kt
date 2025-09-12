package dev.axyss.locatorToggle.commands

import dev.axyss.locatorToggle.LocatorBarManager
import dev.axyss.locatorToggle.utils.Language
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player


class LocatorCommand(): CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, subcommand: String, p3: Array<out String>): Boolean {
        if (sender !is Player) return false
        val locatorBar = LocatorBarManager(sender)

        if (locatorBar.isEnabled()) {
            locatorBar.disable()
            sender.sendMessage(Language.getMessage("locator-toggled-off"))
        } else {
            locatorBar.enable()
            sender.sendMessage(Language.getMessage("locator-toggled-on"))
        }
        return true
    }
}
