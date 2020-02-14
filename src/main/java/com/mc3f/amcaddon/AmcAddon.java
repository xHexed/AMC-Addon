package com.mc3f.amcaddon;

import co.aikar.commands.PaperCommandManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashSet;

@SuppressWarnings({"SpellCheckingInspection", "unused"})
public class AmcAddon extends JavaPlugin {
    public AmcAddon plugin;
    private static final HashSet<String> amc_commands = new HashSet<>();

    @Override
    public void onEnable() {
        plugin = this;
        amc_commands.addAll(Arrays.asList("amc give", "amc sgive", "amc giveall", "amc pouch give", "amc pouch sgive", "pouch giveall"));
        new PaperCommandManager(this).registerCommand(new Command());
        Bukkit.getPluginManager().registerEvents(new CommandListener(this), this);
    }

    @Override public void onDisable() { }

    public final HashSet<String> getAmcCommands() { return amc_commands; }
}
