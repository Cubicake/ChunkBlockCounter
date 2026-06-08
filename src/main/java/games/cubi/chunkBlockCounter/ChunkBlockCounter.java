package games.cubi.chunkBlockCounter;

import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class ChunkBlockCounter extends JavaPlugin {
    public static Logger logger;

    @Override
    public void onEnable() {
        // Plugin startup logic
        new ChunkPacketListener();
        logger = getLogger();
        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS.newHandler(event -> {
            ChunksCommandBrigadier.register(event.registrar());
        }));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


}
