package dev.crmodders.flux.entities.mixins;

import dev.crmodders.flux.entities.interfaces.ITickable;
import finalforeach.cosmicreach.blockentities.BlockEntity;
import finalforeach.cosmicreach.util.IPoint3DMap;
import finalforeach.cosmicreach.world.Chunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Chunk.class)
public class ChunkMixin implements ITickable {

    @Shadow
    IPoint3DMap<BlockEntity> blockEntities;

    @Override
    public void onTick(float tps) {
        blockEntities.forEach(entity -> {
            if(entity instanceof ITickable tickable) {
                tickable.onTick(tps);
            }
        });
    }

}
