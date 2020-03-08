package me.mrdaniel.npcs.commands.action.edit;

import me.mrdaniel.npcs.actions.Action;
import me.mrdaniel.npcs.actions.actions.ActionPlayerCommand;
import me.mrdaniel.npcs.catalogtypes.actiontype.ActionTypes;
import me.mrdaniel.npcs.commands.action.ActionCommand;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class CommandSetPlayerCommand extends ActionCommand {

	public CommandSetPlayerCommand() {
		super(ActionTypes.PLAYERCMD);
	}

	@Override
	public void execute(final Player p, final Action a, final CommandContext args) throws CommandException {
		((ActionPlayerCommand) a).setCommand(args.<String>getOne("command").get());
	}

	public CommandSpec build() {
		return CommandSpec.builder()
				.description(Text.of(TextColors.GOLD, "NPCs | Set Player Command"))
				.permission("npc.action.edit.command.player")
				.arguments(GenericArguments.remainingRawJoinedStrings(Text.of("command")))
				.executor(this)
				.build();
	}
}
