package life.savag3.pp;

import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

    public static Core core;

    @Override
    public void onEnable() {
        new PAPIHandler().register();
    }
}
