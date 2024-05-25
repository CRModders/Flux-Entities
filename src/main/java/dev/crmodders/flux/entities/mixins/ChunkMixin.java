package dev.crmodders.flux.entities.mixins;

import com.badlogic.gdx.graphics.Camera;
import dev.crmodders.flux.entities.TickableBlockEntity;
import dev.crmodders.flux.entities.interfaces.IRenderable;
import dev.crmodders.flux.entities.interfaces.ITickable;
import finalforeach.cosmicreach.BlockGame;
import finalforeach.cosmicreach.blockentities.BlockEntity;
import finalforeach.cosmicreach.gamestates.InGame;
import finalforeach.cosmicreach.util.IPoint3DMap;
import finalforeach.cosmicreach.world.Chunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Chunk.class)
public class ChunkMixin implements ITickable, IRenderable {

    @Shadow
    IPoint3DMap<BlockEntity> blockEntities;

    @Override
    public void onTick(float tps) {
        if(blockEntities != null)
            blockEntities.forEach(entity -> {
                if(entity instanceof ITickable tickable) {
                    tickable.onTick(tps);
                }
            });
    }

    @Override
    public void onRender(Camera camera, float dt) {
        if(blockEntities != null)
            blockEntities.forEach(entity -> {
                if(entity instanceof IRenderable renderable) {
                    renderable.onRender(camera, dt);
                }
            });
    }

    @Inject(method = "setBlockEntity", at= @At(value = "INVOKE", target = "Lfinalforeach/cosmicreach/blockentities/BlockEntity;onRemove()V", shift = At.Shift.AFTER))
    private void destroyBlockEntity(BlockEntity blockEntity, int localX, int localY, int localZ, CallbackInfo ci) {
        if(blockEntity instanceof TickableBlockEntity tickableBlockEntity) {
            tickableBlockEntity.position = null;
        }
    }

    @Inject(method = "setBlockEntity", at= @At(value = "INVOKE", target = "Lfinalforeach/cosmicreach/blockentities/BlockEntity;onCreate()V", shift = At.Shift.BEFORE))
    private void initializeBlockEntity(BlockEntity blockEntity, int localX, int localY, int localZ, CallbackInfo ci) {
        if(blockEntity instanceof TickableBlockEntity tickableBlockEntity) {
            tickableBlockEntity.initialize((Chunk) (Object) this, localX, localY, localZ);
        }
    }

}
