package org.jedels.hookah.actions;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import org.jedels.hookah.Settings;
import org.jedels.hookah.Controller;

import java.text.MessageFormat;

public class HookahMessages {
    public static void sendMessageNearby(Player player, String text) {
        Settings settings = Settings.getInstance();
        Location location = player.getLocation();

        String formattedMessage = MessageFormat.format(text, player.getDisplayName());

        for (Player p : Controller.plugin.getServer().getOnlinePlayers()) {
            if (p.getLocation().distance(location) < settings.messageRadius) {
                p.sendMessage(formattedMessage);
            }
        }
    }

    public static void playSoundNearby(Player player) {
        Settings settings = Settings.getInstance();
        Location location = player.getLocation();

        for (Player p : Controller.plugin.getServer().getOnlinePlayers()) {
            if (p.getLocation().distance(location) < settings.messageRadius) {
                p.playSound(location, Sound.ENTITY_BOAT_PADDLE_WATER, 10, 1);
            }
        }
    }
}
