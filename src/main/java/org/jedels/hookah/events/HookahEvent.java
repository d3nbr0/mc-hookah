package org.jedels.hookah.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import org.jedels.hookah.assets.HookahEventMethod;

import java.util.List;
import java.util.ArrayList;

public class HookahEvent implements Listener {
    private final List<HookahEventMethod> events = new ArrayList<>();

    public HookahEvent() {
        this.registerEvent(HookahSmokeEvent::onPlayerSmoked);
    }

    public void registerEvent(HookahEventMethod eventMethod) {
        events.add(eventMethod);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        Block target = event.getClickedBlock();

        if (target == null || target.getType() != Material.BREWING_STAND) {
            return;
        }

        Block lowerBlock = target.getRelative(BlockFace.DOWN);

        if (!lowerBlock.getType().toString().endsWith("GLASS")) {
            return;
        }

        event.setCancelled(true);

        Player player = event.getPlayer();

        for (HookahEventMethod eventMethod : this.events) {
            eventMethod.execute(player);
        }
    }
}
