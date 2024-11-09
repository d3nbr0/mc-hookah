package org.jedels.hookah.actions;

import org.bukkit.Location;
import org.bukkit.Particle;

import org.bukkit.entity.Player;

import org.jedels.hookah.Controller;
import org.jedels.hookah.Settings;

public class HookahParticles {
    public static void spawnParticles(Player player) {
        Settings settings = Settings.getInstance();

        Location location = player.getLocation();
        location.add(0, player.isSneaking() ? 1.4 : 1.8, 0);

        for (Player p : Controller.plugin.getServer().getOnlinePlayers()) {
            if (p.getLocation().distance(location) < settings.messageRadius) {
                p.spawnParticle(Particle.CLOUD, location, 10, 0, 0, 0, 0.075);
                p.spawnParticle(Particle.WHITE_SMOKE, location, 25, 0, 0, 0, 0.075);
            }
        }
    }
}
