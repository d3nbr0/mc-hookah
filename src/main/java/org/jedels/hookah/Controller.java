package org.jedels.hookah;

import org.bukkit.plugin.java.JavaPlugin;

public class Controller {
    public static JavaPlugin plugin;

    public static void setup(JavaPlugin plugin) {
        Controller.plugin = plugin;
    }
}
