package dev.axyss.locatorToggle.utils;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.jetbrains.annotations.NotNull;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PapiExpansion extends PlaceholderExpansion {

    @Override
    @NotNull
    public String getAuthor() {
        return "Axyss";
    }

    @Override
    @NotNull
    public String getIdentifier() {
        return "locatortoggle";
    }

    @Override
    @NotNull
    public String getVersion() {
        return "1.0.0";
    }

    public String onRequest(OfflinePlayer player, @NotNull String params) {
        return null;
    }

    public String onPlaceholderRequest(Player player, @NotNull String params) {
        return null;
    }
}
