package com.mc3f.amcaddon;

import co.aikar.commands.PaperCommandManager;
import com.google.common.collect.ImmutableSet;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

class AMCAddon extends JavaPlugin {
    private static final ImmutableSet<String> amc_commands = ImmutableSet.of(
            "amc give",
            "amc sgive",
            "amc giveall",
            "amc pouch give",
            "amc pouch sgive",
            "pouch giveall");

    static ImmutableSet<String> getAmcCommands() { return amc_commands; }

    @Override
    public void onDisable() { HandlerList.unregisterAll(this); }

    @Override
    public void onEnable() {
        final PaperCommandManager commandManager = new PaperCommandManager(this);
        commandManager.getCommandReplacements().addReplacement("pouch", String.join("|", Objects.requireNonNull(YamlConfiguration.loadConfiguration(new File("plugins/AdvancedMonthlyCrates", "pouches.yml")).getConfigurationSection("Pouches")).getKeys(false)));
        commandManager.registerCommand(new Command());
        Bukkit.getPluginManager().registerEvents(new CommandListener(), this);
    }
}
