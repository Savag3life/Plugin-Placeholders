package life.savag3.placeholders;

import org.bukkit.plugin.java.JavaPlugin;

public class PluginPlaceholders extends JavaPlugin {

    @Override
    public void onEnable() {
        new PlaceholderAPISupport(this).register();
    }

}
