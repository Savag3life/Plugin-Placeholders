package life.savag3.pp;

import lombok.NonNull;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;

import java.util.StringJoiner;

public class PAPIHandler extends PlaceholderExpansion {

    @Override
    public @NonNull String getIdentifier() {
        return "plugins";
    }

    @Override
    public @NonNull String getAuthor() {
        return "Savag3life";
    }

    @Override
    public @NonNull String getVersion() {
        return Core.core.getDescription().getVersion();
    }

    @Override
    public String onRequest(OfflinePlayer player, String s) {
        String[] args = s.split("_");
        Plugin p = Bukkit.getPluginManager().getPlugin(args[0]);

        if (p == null) return "&cUnknown Plugin";
        if (!p.isEnabled()) return "&cDisabled Plugin";

        StringJoiner joiner = new StringJoiner(", ");

        switch (args[1]) {
            case "version":
                return p.getDescription().getVersion();
            case "authors":
                p.getDescription().getAuthors().forEach(joiner::add);
                return joiner.toString();
            case "depends":
                p.getDescription().getDepend().forEach(joiner::add);
                return joiner.toString();
            case "soft-depends":
                p.getDescription().getSoftDepend().forEach(joiner::add);
                return joiner.toString();
            default: return "...";
        }
    }
}
