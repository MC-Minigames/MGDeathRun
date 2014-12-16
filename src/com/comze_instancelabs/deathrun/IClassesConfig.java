package com.comze_instancelabs.deathrun;

import com.comze_instancelabs.minigamesapi.config.ClassesConfig;

public class IClassesConfig extends ClassesConfig {

	public IClassesConfig(Main m) {
		super(m, true);
		this.getConfig().options().header("Used for saving classes. Default class:");

		// default
		this.getConfig().addDefault("config.kits.default.name", "default");
		this.getConfig().addDefault("config.kits.default.items", "potioneffect:SPEED:99999#1");
		this.getConfig().addDefault("config.kits.default.lore", "Speed I");
		this.getConfig().addDefault("config.kits.default.requires_money", false);
		this.getConfig().addDefault("config.kits.default.requires_permission", false);
		this.getConfig().addDefault("config.kits.default.money_amount", 100);
		this.getConfig().addDefault("config.kits.default.permission_node", "minigames.kits.default");

		// jump
		this.getConfig().addDefault("config.kits.jump.name", "jump");
		this.getConfig().addDefault("config.kits.jump.items", "potioneffect:JUMP:99999#1");
		this.getConfig().addDefault("config.kits.jump.lore", "Jump I");
		this.getConfig().addDefault("config.kits.jump.requires_money", false);
		this.getConfig().addDefault("config.kits.jump.requires_permission", false);
		this.getConfig().addDefault("config.kits.jump.money_amount", 100);
		this.getConfig().addDefault("config.kits.jump.permission_node", "minigames.kits.jump");

		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
	}

}
