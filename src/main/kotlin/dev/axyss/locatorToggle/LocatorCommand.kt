package dev.axyss.locatorToggle

import dev.axyss.locatorToggle.utils.Language
import org.bukkit.entity.Player
import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.CommandPermission
import co.aikar.commands.annotation.Default
import co.aikar.commands.annotation.Subcommand


@CommandAlias("locator")
class LocatorCommand : BaseCommand() {

    @Default
    @CommandPermission("locator.toggle")
    fun toggle(sender: Player) {
        val locatorBar = LocatorBarManager(sender)

        if (locatorBar.isEnabled()) {
            locatorBar.disable()
            sender.sendMessage(Language.getMessage("locator-toggled-off"))
        } else {
            locatorBar.enable()
            sender.sendMessage(Language.getMessage("locator-toggled-on"))
        }
    }

    @Subcommand("radius")
    @CommandPermission("locator.radius")
    fun radius(sender: Player, radius: Long) {
        val maxRadius = LocatorBarManager.plugin.config.getLong("locator-max-radius", 60000000L)

        if (radius !in 1..maxRadius) {
            sender.sendMessage(Language.getMessage("locator-radius-invalid").replace("{max}", maxRadius.toString()))
            return
        }

        LocatorBarManager(sender).setRadius(radius.toDouble())
        sender.sendMessage(Language.getMessage("locator-radius-set").replace("{radius}", radius.toString()))
    }
}
