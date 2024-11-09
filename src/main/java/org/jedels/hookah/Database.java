package org.jedels.hookah;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.logging.Level;

public class Database {
    private static Database INSTANCE;

    private final String fileName = "database.yml";
    private final File file;
    private final FileConfiguration database;

    public Database() {
        this.file = new File(Controller.plugin.getDataFolder(), this.fileName);

        if (!this.file.exists()) {
            this.file.getParentFile().mkdirs();
            Controller.plugin.saveResource(this.fileName, false);
        }

        this.database = YamlConfiguration.loadConfiguration(this.file);
        this.saveTask();
    }

    public void deleteUser(Player player) {
        this.database.set(this.getPath(player, "users.{0}"), null);
    }

    public void deleteUser(String player) {
        this.database.set(this.getPath(player, "users.{0}"), null);
    }

    public void increasePuffs(Player player) {
        final int puffs = this.getPuffs(player);
        final long now = System.currentTimeMillis() / 1000L;

        this.database.set(this.getPath(player, "users.{0}.count"), puffs + 1);
        this.database.set(this.getPath(player, "users.{0}.ts"), now);
    }

    public int getPuffs(Player player) {
        return this.database.getInt(this.getPath(player, "users.{0}.count"));
    }

    public int getPuffs(String player) {
        return this.database.getInt(this.getPath(player, "users.{0}.count"));
    }

    public long getTimestamp(Player player) {
        return this.database.getLong(this.getPath(player, "users.{0}.ts"));
    }

    public long getTimestamp(String player) {
        return this.database.getLong(this.getPath(player, "users.{0}.ts"));
    }

    public void save() {
        try {
            database.save(file);
        } catch (IOException e) {
            Controller.plugin.getLogger().log(
                Level.SEVERE,
                "Could not save config to " + String.valueOf(file),
                e
            );
        }
    }

    public void clearGarbage() {
        ConfigurationSection users = this.database.getConfigurationSection("users");

        if (users == null) {
            return;
        }

        Settings settings = Settings.getInstance();
        final long now = System.currentTimeMillis() / 1000L;

        for (String playerName : users.getValues(false).keySet()) {
            if (this.getPuffs(playerName) == 0 || now - this.getTimestamp(playerName) >= settings.smokeDuration) {
                this.deleteUser(playerName);
            }
        }
    }

    private String getPath(Player player, String path) {
        return MessageFormat.format(path, player.getDisplayName());
    }

    private String getPath(String player, String path) {
        return MessageFormat.format(path, player);
    }

    private void saveTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                clearGarbage();
                save();
            }
        }.runTaskTimer(Controller.plugin, 60L * 20L, 60L * 20);
    }

    public static Database getInstance() {
        if (INSTANCE == null) {
            synchronized (Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Database();
                }
            }
        }

        return INSTANCE;
    }
}
