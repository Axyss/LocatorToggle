package dev.axyss.locatorToggle

import dev.axyss.locatorToggle.utils.Language
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.incendo.cloud.annotation.specifier.Range
import org.incendo.cloud.annotations.Command
import org.incendo.cloud.annotations.CommandDescription
import org.incendo.cloud.annotations.Argument


class LocatorCommand {

    @Command("locator toggle")
    @CommandDescription("Toggle the locator bar")
    fun toggleSubcommand(sender: CommandSender) {
        if (sender !is Player) return
        if (!sender.hasPermission("locator.toggle")) return

        val locatorBar = LocatorBarManager(sender)

        if (locatorBar.isEnabled()) {
            locatorBar.disable()
            sender.sendMessage(Language.getMessage("locator-toggled-off"))
        } else {
            locatorBar.enable()
            sender.sendMessage(Language.getMessage("locator-toggled-on"))
        }
    }

    @Command("locator radius <radius>")
    @CommandDescription("Set the locator bar radius")
    fun radiusSubcommand(sender: CommandSender, @Argument("radius") @Range(min = "1", max = "60000000") radius: Long) {
        if (sender !is Player) return
        if (!sender.hasPermission("locator.radius")) return

        val locatorBar = LocatorBarManager(sender)

        locatorBar.setRadius(radius.toDouble())
        sender.sendMessage(Language.getMessage("locator-radius-set").replace("{radius}", radius.toString()))
    }
}
