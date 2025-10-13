package dev.axyss.locatorToggle.commands

import dev.axyss.locatorToggle.LocatorBarManager
import dev.axyss.locatorToggle.Main
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

        // todo Validate user input
        locatorBar.setCustomRadius(p3.get(0).toDouble())
        if (locatorBar.isEnabled()) {
            locatorBar.disable(true)
            Bukkit.getScheduler().runTaskLater(plugin, Runnable { locatorBar.enable(true) }, 10L)
        }
        sender.sendMessage(Language.getMessage("locator-radius-set").replace("{radius}", p3.get(0)))
        return true
    }
}