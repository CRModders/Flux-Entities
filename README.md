> [!IMPORTANT]
> FluxAPI has been discontinued.
>
> It is still avaliable for legacy mods.

# Flux Entities
A Flux Addon that provides Tickable and Renderable Block Entities to the Game

### Using flux-entities in your projects

Step 1: Add [CRModder's Maven](https://maven.crmodders.dev/) in your build.gradle at the end of your repositories tag.\
Here is an example `repositories` section
```
repositories {
	maven { url 'https://maven.crmodders.dev/releases/' }
	maven { url 'https://maven.crmodders.dev/snapshots/' }
	
	mavenCentral()
}
```

Step 2: Add Flux to your dependencies using the text below
```
gameMod "dev.crmodders:flux-entities:{current_version_in_tags}"
```

### Basic Usage
```java
public class MyBlockEntity extends TickableBlockEntity implements IRenderable {

    public static void register() {
        BlockEntityCreator.registerBlockEntityCreator("base:myblock", (blockState, x, y, z) -> new MyBlockEntity(Identifier.fromString(blockState.getBlock().blockEntityId), x, y, z));
    }

    public MyBlockEntity(Identifier id, int x, int y, int z) {
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
