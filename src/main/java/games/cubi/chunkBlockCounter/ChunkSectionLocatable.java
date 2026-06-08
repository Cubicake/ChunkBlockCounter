package games.cubi.chunkBlockCounter;

import java.util.UUID;

public sealed interface ChunkSectionLocatable extends ChunkLocatable permits ChunkSectionLocatable.ImmutableChunkSectionLocatable {
    int chunkY();

    record ImmutableChunkSectionLocatable(UUID world, int chunkX, int chunkY, int chunkZ) implements ChunkSectionLocatable {
    }
}
