package me.x150.examplecoffeeaddon.modules;

import coffee.client.feature.module.AddonModule;
import coffee.client.helper.render.Renderer;
import coffee.client.helper.util.Utils;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;

import java.awt.Color;

public class ExampleModule extends AddonModule {
    public ExampleModule() {
        // Some info about the module
        super("Example", "An example module made by the example addon");
    }

    // Called when a game tick happened
    @Override
    public void tick() {
        Utils.Logging.message("Ticked");
    }

    // Called when the module has been enabled
    @Override
    public void enable() {
        Utils.Logging.success("Enabled");
    }

    // Called when the module has been disabled
    @Override
    public void disable() {
        Utils.Logging.error("Disabled");
    }

    // The text to show right next to the module name on the arraylist
    @Override
    public String getContext() {
        return "Context";
    }

    // Called when the world has been rendered
    @Override
    public void onWorldRender(MatrixStack matrices) {
        // 3 blocks above the player's feet (lowest pos of the hitbox)
        Vec3d startPos = client.player.getPos().add(0, 3, 0);
        // 1x1x1 cube to represent dimensions
        Vec3d dimensions = new Vec3d(1, 1, 1);
        // Render a cube, color green with opacity 100/255, outline red, centered at 3 blocks above the player, with 1x1x1 dimensions
        Renderer.R3D.renderEdged(matrices, startPos.subtract(dimensions.multiply(0.5)), dimensions, new Color(50, 255, 50, 100), Color.RED);
    }

    // Called when the hud has been rendered
    @Override
    public void onHudRender() {
        // Render a rounded rectangle, color white, from x 100 y 100 to x 150 y 150, with 5 px round corners and 10 samples per corner
        Renderer.R2D.renderRoundedQuad(Renderer.R3D.getEmptyMatrixStack(), Color.WHITE, 100, 100, 150, 150, 5, 10);
    }
}
