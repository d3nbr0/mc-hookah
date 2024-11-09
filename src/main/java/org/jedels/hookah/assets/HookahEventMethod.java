package org.jedels.hookah.assets;

import org.bukkit.entity.Player;

@FunctionalInterface
public interface HookahEventMethod {
    void execute(Player player);
}
