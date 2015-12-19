package com.hm.antiworldfly.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.hm.antiworldfly.AntiWorldFly;
import com.hm.antiworldfly.AntiWorldFlyRunnable;

public class AntiWorldFlyPlayerJoin implements Listener {

	private AntiWorldFly plugin;

	public AntiWorldFlyPlayerJoin(AntiWorldFly awf) {

		this.plugin = awf;
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onPlayerJoin(PlayerJoinEvent event) {

		for (String world : plugin.getAntiFlyWorlds()) {

			if (event.getPlayer().getWorld().getName().equalsIgnoreCase(world)) {
				// Schedule runnable to disable flying.
				Bukkit.getServer()
						.getScheduler()
						.scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("AntiWorldFly"),
								new AntiWorldFlyRunnable(event.getPlayer(), plugin), 20);

				break;
			}
		}
	}

}
