package life.savag3.pp;

import lombok.NonNull;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;

// Suppress deprecation warnings because Paper is updating their API's, but they are still
// "beta" and not ready for production use... don't know why they are in the release builds & the existing API is deprecated.
@SuppressWarnings("deprecation")
public class PlaceholderAPISupport extends PlaceholderExpansion {

    private final PluginPlaceholders plugin;

    public PlaceholderAPISupport(PluginPlaceholders plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NonNull String getIdentifier() {
        return "plugin-placeholders";
    }

    @Override
    public @NonNull String getAuthor() {
        return String.join(", ", plugin.getDescription().getAuthors());
    }

    @Override
    public @NonNull String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onRequest(OfflinePlayer player, String s) {
        String[] args = s.split("_");
        Plugin p = Bukkit.getPluginManager().getPlugin(args[0]);

        if (p == null) return "Unknown Plugin";
        if (!p.isEnabled()) return "Disabled Plugin";

        return switch (args[1]) {
            case "version" -> p.getDescription().getVersion();
            case "authors" -> String.join(", ", p.getDescription().getAuthors());
            case "depends" -> String.join(", ", p.getDescription().getDepend());
            case "soft-depends" -> String.join(", ", p.getDescription().getSoftDepend());
            case "description" -> p.getDescription().getDescription();
            case "website" -> p.getDescription().getWebsite();
            case "commands" -> String.join(", /", p.getDescription().getCommands().keySet());
            case "api-version" -> p.getDescription().getAPIVersion();
            case "loaded" -> p.isEnabled() ? "Enabled" : "Disabled";
            case "main" -> p.getDescription().getMain();
            default -> "...";
        };
    }
}
