package dev.axyss.locatorToggle.utils;

import dev.axyss.locatorToggle.LocatorBarManager;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.jetbrains.annotations.NotNull;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PapiExpansion extends PlaceholderExpansion {

    private final Plugin plugin;

    public PapiExpansion(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    @NotNull
    public String getAuthor() {
        return String.join(" ", plugin.getDescription().getAuthors());
    }

    @Override
    @NotNull
    public String getIdentifier() {
        return "locatortoggle";
    }

    @Override
    @NotNull
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String params) {
        if (params.equalsIgnoreCase("status")) {
            return String.valueOf(new LocatorBarManager(player).isEnabled());
        } else if (params.equalsIgnoreCase("radius")) {
            return String.valueOf(new LocatorBarManager(player).getRadius());
        }
        return null;
    }
}
