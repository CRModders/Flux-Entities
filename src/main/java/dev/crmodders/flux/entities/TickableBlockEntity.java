package dev.crmodders.flux.entities;

import dev.crmodders.flux.entities.interfaces.ITickable;
import dev.crmodders.flux.tags.Identifier;
import finalforeach.cosmicreach.blocks.BlockState;

public class TickableBlockEntity extends FluxBlockEntity implements ITickable {

    public TickableBlockEntity(Identifier id, int globalX, int globalY, int globalZ) {
        super(id, globalX, globalY, globalZ);
    }

    @Override
    public void onTick(float tps) {

    }

}
