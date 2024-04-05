package fr.maxlego08.essentials.commands.commands.economy;

import fr.maxlego08.essentials.api.EssentialsPlugin;
import fr.maxlego08.essentials.api.commands.CommandResultType;
import fr.maxlego08.essentials.api.commands.Permission;
import fr.maxlego08.essentials.api.messages.Message;
import fr.maxlego08.essentials.zutils.utils.commands.VCommand;

public class CommandEconomy extends VCommand {


    public CommandEconomy(EssentialsPlugin plugin) {
        super(plugin);
        this.setPermission(Permission.ESSENTIALS_ECO_USE);
        this.setDescription(Message.DESCRIPTION_ECO);
        this.addSubCommand(new CommandEconomyGive(plugin));
        this.addSubCommand(new CommandEconomyTake(plugin));
        this.addSubCommand(new CommandEconomyGiveAll(plugin));
    }

    @Override
    protected CommandResultType perform(EssentialsPlugin plugin) {
        this.syntaxMessage();
        return CommandResultType.SUCCESS;
    }
}
