package dev.crmodders.flux.entities;

import dev.crmodders.flux.annotations.FluxInternal;
import finalforeach.cosmicreach.blockentities.BlockEntity;
import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.world.Chunk;

public class FluxBlockEntity extends BlockEntity {

    public BlockPosition position;

    protected FluxBlockEntity() {
    }

    public FluxBlockEntity(String id, int globalX, int globalY, int globalZ) {
        super(id, globalX, globalY, globalZ);
    }

    @FluxInternal
    public void initialize(Chunk chunk, int localX, int localY, int localZ) {
        position = new BlockPosition(chunk, localX, localY, localZ);
    }

}
