package dev.axyss.locatorToggle.commands

import dev.axyss.locatorToggle.LocatorBarManager
import dev.axyss.locatorToggle.utils.Language
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class RadiusCommand: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, subcommand: String, p3: Array<out String>): Boolean {
        if (sender !is Player) return false
        val locatorBar = LocatorBarManager(sender)

        // todo Validate user input
        // todo Fix the need to re-toggle the locator bar for changes to take effect
        locatorBar.setCustomRadius(p3.get(0).toDouble())
        sender.sendMessage(Language.getMessage("locator-radius-set").replace("{radius}", p3.get(0)))
        return true
    }
}