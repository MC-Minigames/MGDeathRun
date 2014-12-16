package com.comze_instancelabs.deathrun;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import com.comze_instancelabs.minigamesapi.Arena;
import com.comze_instancelabs.minigamesapi.ArenaState;
import com.comze_instancelabs.minigamesapi.ArenaType;
import com.comze_instancelabs.minigamesapi.MinigamesAPI;
import com.comze_instancelabs.minigamesapi.util.Util;
import com.comze_instancelabs.minigamesapi.util.Validator;

public class IArena extends Arena {

	Main m = null;
	BukkitTask tt;

	public IArena(Main m, String arena) {
		super(m, arena, ArenaType.REGENERATION);
		this.m = m;
		MinigamesAPI.getAPI().pinstances.get(m).getArenaListener().loseY = 15;
	}

	@Override
	public void start(boolean tp) {
		super.start(tp);
		final IArena a = this;
		Bukkit.getScheduler().runTaskLater(m, new Runnable() {
			public void run() {
				for (String p_ : a.getAllPlayers()) {
					if (Validator.isPlayerOnline(p_)) {
						Player p = Bukkit.getPlayer(p_);
						p.setWalkSpeed(0.2F);
						p.setFoodLevel(20);
						p.removePotionEffect(PotionEffectType.JUMP);
					}
				}
			}
		}, 20L);
	}

	@Override
	public void started() {
		final IArena a = this;
		tt = Bukkit.getScheduler().runTaskTimer(m, new Runnable() {
			public void run() {
				for (String p_ : a.getAllPlayers()) {
					Player p = Bukkit.getPlayer(p_);
					if (p != null) {
						Location l = p.getLocation().add(0D, -1D, 0D);
						// l.setPitch(0F);
						// Vector dir = l.getDirection().normalize().multiply(0.5D);
						// p.setVelocity(dir);
						final Location b1 = l.clone().add(0.3, 0, -0.3);
						final Location b2 = l.clone().add(-0.3, 0, -0.3);
						final Location b3 = l.clone().add(0.3, 0, 0.3);
						final Location b4 = l.clone().add(-0.3, 0, +0.3);
						a.getSmartReset().addChanged(b1.getBlock());
						a.getSmartReset().addChanged(b2.getBlock());
						a.getSmartReset().addChanged(b3.getBlock());
						a.getSmartReset().addChanged(b4.getBlock());

						Bukkit.getScheduler().runTaskLater(m, new Runnable() {
							public void run() {
								b1.getBlock().setType(Material.AIR);
								b2.getBlock().setType(Material.AIR);
								b3.getBlock().setType(Material.AIR);
								b4.getBlock().setType(Material.AIR);
							}
						}, 15L);
					}
				}
			}
		}, 20L, 3L);

	}

	@Override
	public void stop() {
		if (tt != null) {
			tt.cancel();
			tt = null;
			final Arena a = this;
			Bukkit.getScheduler().runTaskLater(m, new Runnable() {
				public void run() {
					if (m.isEnabled()) {
						a.stop();
					}
				}
			}, 20L);
		} else {
			super.stop();
		}

	}

}
