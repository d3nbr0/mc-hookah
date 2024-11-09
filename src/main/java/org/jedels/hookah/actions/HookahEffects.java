package org.jedels.hookah.actions;

import org.bukkit.entity.Player;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import org.jedels.hookah.Settings;

public class HookahEffects {
    public static void addPositiveEffects(Player player) {
        Settings settings = Settings.getInstance();

        PotionEffect healthEffect = new PotionEffect(
            PotionEffectType.REGENERATION,
            settings.positiveEffectDuration * 20,
            0
        );

        player.addPotionEffect(healthEffect);
    }

    public static void addNegativeEffects(Player player, boolean isDied) {
        Settings settings = Settings.getInstance();

        PotionEffect slowEffect = new PotionEffect(
            PotionEffectType.SLOWNESS,
            settings.negativeEffectDuration * 20,
            0
        );

        player.addPotionEffect(slowEffect);
        player.damage(settings.damage);

        if (isDied) {
            player.damage(1000);
        }
    }
}
