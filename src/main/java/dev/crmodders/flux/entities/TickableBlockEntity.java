package dev.crmodders.flux.entities;

import dev.crmodders.flux.entities.interfaces.IRenderable;
import dev.crmodders.flux.entities.interfaces.ITickable;
import finalforeach.cosmicreach.blockentities.BlockEntity;
import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.world.Chunk;
import finalforeach.cosmicreach.world.Zone;

public class TickableBlockEntity extends BlockEntity implements ITickable {

    public BlockPosition position;

    protected TickableBlockEntity() {
    }

    public TickableBlockEntity(String id, int globalX, int globalY, int globalZ) {
        super(id, globalX, globalY, globalZ);
    }

    public void initialize(Chunk chunk, int localX, int localY, int localZ) {
        position = new BlockPosition(chunk, localX, localY, localZ);
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
