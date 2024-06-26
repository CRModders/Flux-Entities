package dev.crmodders.flux.entities.mixins;

import com.badlogic.gdx.graphics.Camera;
import dev.crmodders.flux.entities.interfaces.IRenderable;
import dev.crmodders.flux.entities.interfaces.ITickable;
import finalforeach.cosmicreach.util.Point3DMap;
import finalforeach.cosmicreach.world.Chunk;
import finalforeach.cosmicreach.world.Zone;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Zone.class)
public class ZoneMixin implements IRenderable {

    @Shadow private @Final Point3DMap<Chunk> chunks;

    @Inject(method = "runScheduledTriggers", at = @At("HEAD"))
    private void zoneUpdate(CallbackInfo ci) {
        synchronized (chunks) {
            chunks.forEach(chunk -> {
                if (chunk instanceof ITickable tickable) {
                    tickable.onTick(1f / 20f); // TODO get tps here
                }
            });
        }
    }

    @Override
    public void onRender(Camera camera, float dt) {
        synchronized (chunks) {
            chunks.forEach(chunk -> {
                if(chunk instanceof IRenderable renderable) {
                    renderable.onRender(camera, dt);
                }
            });
        }
    }
}
