package dev.crmodders.flux.entities;

import dev.crmodders.flux.entities.interfaces.ITickable;
import finalforeach.cosmicreach.blockentities.BlockEntity;
import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.world.Chunk;
import finalforeach.cosmicreach.world.Zone;

public class FunctionalBlock extends BlockEntity implements ITickable {

    public BlockPosition position;

    public void initialize(Chunk chunk) {
        position = new BlockPosition(chunk, getGlobalX() - chunk.blockX, getGlobalY() - chunk.blockY, getGlobalZ() - chunk.blockZ);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onInteract(Zone zone, BlockPosition blockPos, boolean interactJustPressed, boolean interactHeld, double timeSinceLastInteract) {
        super.onInteract(zone, blockPos, interactJustPressed, interactHeld, timeSinceLastInteract);
    }

    @Override
    public void onTick(float tps) {

    }

    @Override
    public void onRemove() {
        super.onRemove();
    }
}
