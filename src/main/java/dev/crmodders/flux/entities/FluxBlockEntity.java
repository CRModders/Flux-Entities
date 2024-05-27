package dev.crmodders.flux.entities;

import dev.crmodders.flux.annotations.FluxInternal;
import dev.crmodders.flux.tags.Identifier;
import finalforeach.cosmicreach.blockentities.BlockEntity;
import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.world.Chunk;

public class FluxBlockEntity extends BlockEntity {

    public Identifier blockEntityId;
    public BlockPosition position;

    public FluxBlockEntity(Identifier id, int globalX, int globalY, int globalZ) {
        super(globalX, globalY, globalZ);
    }

    @Override
    public String getBlockEntityId() {
        return blockEntityId.toString();
    }

    @FluxInternal
    public void initialize(Chunk chunk, int localX, int localY, int localZ) {
        position = new BlockPosition(chunk, localX, localY, localZ);
    }

}
