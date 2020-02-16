package com.mc3f.amcaddon;

import co.aikar.commands.PaperCommandManager;
import com.google.common.collect.ImmutableSet;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

@SuppressWarnings("SpellCheckingInspection")
public class AmcAddon extends JavaPlugin {
    private static final ImmutableSet<String> amc_commands = ImmutableSet.of(
            "amc give",
            "amc sgive",
            "amc giveall",
            "amc pouch give",
            "amc pouch sgive",
            "pouch giveall");

    @Override
    public void onEnable() {
        final PaperCommandManager commandManager = new PaperCommandManager(this);
        commandManager.getCommandReplacements().addReplacement("pouch", String.join("|", YamlConfiguration.loadConfiguration(new File("plugins/AdvancedMonthlyCrates", "pouches.yml")).getConfigurationSection("Pouches").getKeys(false)));
        commandManager.registerCommand(new Command());
        Bukkit.getPluginManager().registerEvents(new CommandListener(this), this);
    }

    @Override
    public void onDisable() { HandlerList.unregisterAll(this); }

    public final ImmutableSet<String> getAmcCommands() { return amc_commands; }
}
