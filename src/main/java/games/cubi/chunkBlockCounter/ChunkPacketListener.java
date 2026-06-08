package games.cubi.chunkBlockCounter;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.packettype.PacketTypeCommon;
import com.github.retrooper.packetevents.protocol.world.chunk.impl.v_1_18.Chunk_v1_18;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerChunkData;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ChunkPacketListener implements PacketListener {
    public static final ConcurrentHashMap<ChunkSectionLocatable, Integer> uniqueBlockCountByChunkSection = new ConcurrentHashMap<>();

    public ChunkPacketListener() {
        PacketEvents.getAPI().getEventManager().registerListener(this, PacketListenerPriority.HIGHEST);
    }

    @Override
    public void onPacketSend(PacketSendEvent event) {
        if (event.getPacketType() == PacketType.Play.Server.CHUNK_DATA) {
            WrapperPlayServerChunkData packet = new WrapperPlayServerChunkData(event);
            UUID world = ((Player) event.getPlayer()).getWorld().getUID();
            int offsetY = event.getUser().getMinWorldHeight() >> 4;
            for (int sectionY = 0; sectionY < packet.getColumn().getChunks().length; sectionY++) {
                ChunkSectionLocatable locatable = new ChunkSectionLocatable.ImmutableChunkSectionLocatable(world, packet.getColumn().getX(), packet.getColumn().getZ(), sectionY + offsetY);
                uniqueBlockCountByChunkSection.put(locatable, ((Chunk_v1_18) packet.getColumn().getChunks()[sectionY]).getChunkData().palette.size());
            }
        }
    }
}
