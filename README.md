# Flux Entities
A Flux Addon that provides Tickable and Renderable Block Entities to the Game
### Basic Usage
```java
public class MyBlockEntity extends TickableBlockEntity implements IRenderable {

    public static void register() {
        BlockEntityCreator.registerBlockEntityCreator("base:myblock", (block, x, y, z) -> new MyBlockEntity(block.getStringId(), x, y, z));
    }

    public MyBlockEntity(String id, int x, int y, int z) {
        super(id, x, y, z);
    }

    @Override
    public void onTick(float tps) {
        System.err.println("HELLO " + position);
    }

    @Override
    public void onRender(Camera camera, float dt) {
        System.err.println("RENDER");
    }
}
```
