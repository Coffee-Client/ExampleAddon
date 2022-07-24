package me.x150.examplecoffeeaddon.commands;

import coffee.client.CoffeeMain;
import coffee.client.feature.command.Command;
import coffee.client.feature.command.coloring.ArgumentType;
import coffee.client.feature.command.coloring.PossibleArgument;
import coffee.client.feature.command.coloring.StaticArgumentServer;
import coffee.client.feature.command.exception.CommandException;
import net.minecraft.entity.Entity;

import java.util.Objects;
import java.util.stream.StreamSupport;

public class Remove extends Command {
    public Remove() {
        // Some info about the command
        super("Remove", "Removes an entity from your world", "remove");
    }

    // Gets the suggestions for the command, at specified argument index
    @Override
    public PossibleArgument getSuggestionsWithType(int index, String[] args) {
        return StaticArgumentServer.serveFromStatic(index, new PossibleArgument(
                ArgumentType.PLAYER,
                () -> StreamSupport.stream(Objects.requireNonNull(CoffeeMain.client.world)
                                .getEntities().spliterator(),false)
                        .map(Entity::getEntityName)
                        .toList()
                        .toArray(String[]::new)
        ));
    }

    @Override
    public void onExecute(String[] args) throws CommandException {
        validateArgumentsLength(args, 1, "Player name is required");
        String playerName = args[0];
        int removed = 0;
        for (Entity entity : CoffeeMain.client.world.getEntities()) {
            if (entity.getEntityName().equals(playerName)) {
                entity.remove(Entity.RemovalReason.DISCARDED);
                removed++;
            }
        }
        if (removed == 0) error("Didn't remove any entities");
        else success(String.format("Removed %d entities", removed));
    }
}
