package dev.axyss.locatorToggle.commands

import dev.axyss.locatorToggle.LocatorBarManager
import dev.axyss.locatorToggle.utils.Language
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class RadiusCommand(): CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, subcommand: String, p3: Array<out String>): Boolean {
        if (sender !is Player) return false
        if (!sender.hasPermission("locator.radius")) return false

        val locatorBar = LocatorBarManager(sender)
        val radius = p3.getOrNull(0)?.toIntOrNull()

        if (radius == null || radius < 1 || radius > 60_000_000) {
            sender.sendMessage(Language.getMessage("locator-radius-invalid"))
            return false;
        }

        locatorBar.setRadius(radius.toDouble())
        sender.sendMessage(Language.getMessage("locator-radius-set").replace("{radius}", radius.toString()))
        return true
    }
}