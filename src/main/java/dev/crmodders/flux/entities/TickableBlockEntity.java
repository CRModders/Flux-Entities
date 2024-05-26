package dev.crmodders.flux.entities;

import dev.crmodders.flux.entities.interfaces.ITickable;

@Deprecated
public class TickableBlockEntity extends FluxBlockEntity implements ITickable {

    protected TickableBlockEntity() {
    }

    public TickableBlockEntity(String id, int globalX, int globalY, int globalZ) {
        super(id, globalX, globalY, globalZ);
    }

    @Override
    public void onTick(float tps) {

    }

}
