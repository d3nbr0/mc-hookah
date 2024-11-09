package org.jedels.hookah.events;

import org.bukkit.GameMode;

import org.bukkit.entity.Player;

import org.jedels.hookah.Settings;
import org.jedels.hookah.Database;

import org.jedels.hookah.actions.HookahEffects;
import org.jedels.hookah.actions.HookahMessages;
import org.jedels.hookah.actions.HookahParticles;

public class HookahSmokeEvent {
    public static void onPlayerSmoked(Player player) {
        Settings settings = Settings.getInstance();
        Database database = Database.getInstance();

        GameMode gamemode = player.getGameMode();

        final long now = System.currentTimeMillis() / 1000L;

        if (now - database.getTimestamp(player) >= settings.smokeDuration) {
            database.deleteUser(player);
        }

        boolean isDamaged = false;
        boolean isDied = false;
        String message;

        database.increasePuffs(player);

        if (!player.hasPermission("hookah.nonegative") && (gamemode == GameMode.SURVIVAL || gamemode == GameMode.ADVENTURE)) {
            final int puffs = database.getPuffs(player);

            if (puffs > settings.maxSmoked) {
                isDamaged = true;

                if (settings.damage >= player.getHealth()) {
                    isDied = true;
                } else if (!settings.limitMessage.isEmpty() && puffs == settings.maxSmoked + 1) {
                    player.sendMessage(settings.limitMessage);
                }
            }
        }

        HookahParticles.spawnParticles(player);

        if (isDamaged || isDied) {
            HookahEffects.addNegativeEffects(player, isDied);

            if (isDied) {
                message = settings.getRandomDieMessage();
                database.deleteUser(player);
            } else {
                message = settings.getRandomDamageMessage();
            }
        } else {
            HookahEffects.addPositiveEffects(player);
            message = settings.getRandomPuffMessage();
        }

        HookahMessages.sendMessageNearby(player, message);
        HookahMessages.playSoundNearby(player);
    }
}
