package com.mc3f.amcaddon;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Optional;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.command.CommandSender;

import static org.bukkit.Bukkit.getServer;

@CommandAlias("pouch")
public class Command extends BaseCommand {
    @Subcommand("giveall")
    @CommandCompletion("%pouch 1 true|false")
    public static void onPouchGiveAll(final CommandSender sender, final String crate, @Optional final String number, @Optional final String silent) {
        if (!sender.isOp()) return;
        getServer().getOnlinePlayers().forEach(player -> getServer().dispatchCommand(getServer().getConsoleSender(), "amc pouch " + (silent == null ? "" : (Boolean.parseBoolean(silent) ? "s" : "")) + "give " + player.getName() + " " + crate + " " + (number == null ? 1 : number)));
    }
}