package fr.maxlego08.essentials.commands.commands;

import fr.maxlego08.essentials.api.EssentialsPlugin;
import fr.maxlego08.essentials.api.commands.CommandResultType;
import fr.maxlego08.essentials.api.commands.Permission;
import fr.maxlego08.essentials.api.messages.Message;
import fr.maxlego08.essentials.zutils.utils.commands.VCommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class CommandEssentials extends VCommand {

    public CommandEssentials(EssentialsPlugin plugin) {
        super(plugin);
        this.setPermission(Permission.ESSENTIALS_USE);
        this.addSubCommand(new CommandEssentialsReload(plugin));
    }

    @Override
    protected CommandResultType perform(EssentialsPlugin plugin) {

        message(sender, Message.COMMAND_ESSENTIALS, "%version%", plugin.getDescription().getVersion());

        return CommandResultType.SUCCESS;
    }
}
