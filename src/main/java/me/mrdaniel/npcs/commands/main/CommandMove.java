package me.mrdaniel.npcs.commands.main;

import me.mrdaniel.npcs.NPCs;
import me.mrdaniel.npcs.commands.NPCCommand;
import me.mrdaniel.npcs.exceptions.NPCException;
import me.mrdaniel.npcs.interfaces.mixin.NPCAble;
import me.mrdaniel.npcs.io.INPCData;
import me.mrdaniel.npcs.menu.chat.npc.PropertiesChatMenu;
import me.mrdaniel.npcs.utils.Position;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class CommandMove extends NPCCommand {

	public CommandMove() {
		super(PropertiesChatMenu::new, false);
	}

	@Override
	public void execute(Player p, INPCData data, NPCAble npc, CommandContext args) throws CommandException {
		data.setNPCPosition(new Position(p.getWorld().getName(), p.getLocation().getPosition(), p.getHeadRotation())).saveNPC();

		try {
			NPCs.getInstance().getNPCManager().spawn(data);
		} catch (NPCException exc) {
			throw new CommandException(Text.of(TextColors.RED, "Failed to respawn NPC: ", exc.getMessage()));
		}
	}

	public CommandSpec build() {
		return CommandSpec.builder()
				.description(Text.of(TextColors.GOLD, "NPCs | Move"))
				.permission("npc.edit.move")
				.arguments(NPCCommand.ID_ARG)
				.executor(this)
				.build();
	}
}
