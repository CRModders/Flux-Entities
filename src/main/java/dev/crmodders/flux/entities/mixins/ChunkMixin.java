package dev.crmodders.flux.entities.mixins;

import com.badlogic.gdx.graphics.Camera;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import dev.crmodders.flux.entities.FluxBlockEntity;
import dev.crmodders.flux.entities.interfaces.IRenderable;
import dev.crmodders.flux.entities.interfaces.ITickable;
import finalforeach.cosmicreach.blockentities.BlockEntity;
import finalforeach.cosmicreach.blockentities.BlockEntityCreator;
import finalforeach.cosmicreach.blocks.Block;
import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.savelib.blockdata.IBlockData;
import finalforeach.cosmicreach.util.IPoint3DMap;
import finalforeach.cosmicreach.world.Chunk;
import finalforeach.cosmicreach.world.Zone;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Chunk.class)
public abstract class ChunkMixin implements ITickable, IRenderable {

    @Shadow
    IPoint3DMap<BlockEntity> blockEntities;

    @Shadow public IBlockData<BlockState> blockData;

    @Shadow public abstract void setBlockEntity(BlockEntity blockEntity, int localX, int localY, int localZ);

    @Shadow public transient boolean isSaved;

    @Shadow public int blockX;

    @Shadow public int blockY;

    @Shadow public int blockZ;

    @Shadow public abstract boolean hasNeighbouringBlockLightChunks(Zone zone);

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
        if(blockEntity instanceof FluxBlockEntity fluxBlockEntity) {
            fluxBlockEntity.position = null;
        }
    }

    @Inject(method = "setBlockEntity", at= @At(value = "INVOKE", target = "Lfinalforeach/cosmicreach/blockentities/BlockEntity;onCreate()V", shift = At.Shift.BEFORE))
    private void initializeBlockEntity(BlockEntity blockEntity, int localX, int localY, int localZ, CallbackInfo ci) {
        if(blockEntity instanceof FluxBlockEntity fluxBlockEntity) {
            fluxBlockEntity.initialize((Chunk) (Object) this, localX, localY, localZ);
        }
    }

    @Inject(method = "setBlockEntity", at= @At("TAIL"))
    private void fireNeighbors(BlockEntity blockEntity, int localX, int localY, int localZ, CallbackInfo ci) {

    }

    /**
     * @author nanobass
     * @reason replacing a block state should not replace the block entity if the block is the same
     */
    @Overwrite
    public void setBlockState(BlockState newState, int x, int y, int z) {
        BlockState oldState = blockData.getBlockValue(x, y, z);
        Block oldBlock = oldState.getBlock();
        blockData = blockData.setBlockValue(newState, x, y, z);
        Block newBlock = newState.getBlock();
        if(oldBlock != newBlock) {
            if (newBlock.blockEntityId != null) {
                setBlockEntity(BlockEntityCreator.get(newBlock, blockX + x, blockY + y, blockZ + z), x, y, z);
            } else if (this.blockEntities != null) {
                setBlockEntity(null, x, y, z);
            }
        }
        this.isSaved = false;
    }


}
