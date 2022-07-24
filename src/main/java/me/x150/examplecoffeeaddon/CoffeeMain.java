package me.x150.examplecoffeeaddon;

import coffee.client.feature.addon.Addon;
import coffee.client.feature.command.Command;
import coffee.client.feature.module.AddonModule;
import coffee.client.helper.render.Texture;
import coffee.client.helper.util.Utils;
import me.x150.examplecoffeeaddon.commands.Remove;
import me.x150.examplecoffeeaddon.modules.ExampleModule;
import net.minecraft.util.Identifier;

import javax.imageio.ImageIO;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

/*
 * Main entry point for the addon per coffee client's loader
 * The class name can be anything, it just has to extend `Addon`
 * There can only be exactly one class extending `Addon` in the jar, otherwise
 * coffee will not know which one to use as main entry point
 * */
@SuppressWarnings("unused")
public class CoffeeMain extends Addon {
    // Because of how the addon system works, directly referencing textures via `Identifier` does not work.
    // This means we have to load the file ourselves
    static Texture iconStored = null;

    public CoffeeMain() {
        // Some info about the addon
        super("ExampleAddon", "An example addon for coffee", new String[] { "0x150" });
    }

    Texture loadIcon() {
        if (iconStored != null) {
            return iconStored;
        }
        InputStream is = getClass().getClassLoader().getResourceAsStream("assets/ExampleCoffeeAddon/icon.png");
        Objects.requireNonNull(is);
        try {
            iconStored = new Texture(new Identifier("coffee-example-addon", "icon"));
            Utils.registerBufferedImageTexture(iconStored, ImageIO.read(is));
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return iconStored;
    }

    // Getter for the icon
    @Override
    public Identifier getIcon() {
        return loadIcon();
    }

    /*
    * Custom features
    * Serve as a way to put your features into the client for the user to use
    * */

    /*
    * A list of additional modules to register into the client
    * */
    @Override
    public List<AddonModule> getAdditionalModules() {
        return List.of(new ExampleModule());
    }

    /*
    * A list of additional commands to register into the client
    * */
    @Override
    public List<Command> getAdditionalCommands() {
        return List.of(new Remove());
    }

    /*
    * Events for the addon
    * Serve as a way to prepare for the coming event
    * All events here get called BEFORE the event takes place. Examples:
    *     <init>() called -> enabled() called -> features loaded
    *     reloaded() called -> disabled() called -> features unloaded -> <init>() called -> enabled() called -> features loaded
    * The disabled event does NOT get called when the game stops
    * */

    /*
    * Runs BEFORE the module is going to be enabled
    * */
    @Override
    public void enabled() {
        System.out.println("I'm going to be enabled");
    }

    /*
    * Runs BEFORE the module is going to be disabled
    * */
    @Override
    public void disabled() {
        System.out.println("I'm going to be disabled");
    }

    /*
    * Runs BEFORE the module is going to be reloaded
    * */
    @Override
    public void reloaded() {
        System.out.println("I'm going to be reloaded");
    }
}
