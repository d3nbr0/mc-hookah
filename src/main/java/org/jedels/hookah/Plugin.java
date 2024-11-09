package org.jedels.hookah;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import org.jedels.hookah.events.HookahEvent;

public final class Plugin extends JavaPlugin {
    @Override
    public void onEnable() {
        Controller.setup(this);

        Listener hookahEventListener = new HookahEvent();

        getServer().getPluginManager().registerEvents(hookahEventListener, this);
    }

    @Override
    public void onDisable() {
        Database.getInstance().save();
    }
}
