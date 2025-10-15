package dev.axyss.locatorToggle.commands

import dev.axyss.locatorToggle.LocatorBarManager
import dev.axyss.locatorToggle.utils.Language
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class RadiusCommand(private val plugin: JavaPlugin): CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, subcommand: String, p3: Array<out String>): Boolean {
        if (sender !is Player) return false
        val locatorBar = LocatorBarManager(sender)
        val radius = p3.getOrNull(0)?.toIntOrNull()

        if (radius == null || radius < 1 || radius > 60_000_000) {
            sender.sendMessage(Language.getMessage("locator-radius-invalid"))
            return false;
        }

        locatorBar.setCustomRadius(radius.toDouble())
        if (locatorBar.isEnabled()) {
            locatorBar.disableTemporarily()
            Bukkit.getScheduler().runTaskLater(plugin, Runnable { locatorBar.enableTemporarily() }, 10L)
        }
        sender.sendMessage(Language.getMessage("locator-radius-set").replace("{radius}", radius.toString()))
        return true
    }
}