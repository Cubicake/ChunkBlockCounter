package games.cubi.chunkBlockCounter;

import java.util.UUID;

public sealed interface ChunkLocatable permits ChunkLocatable.ImmutableChunkLocatable, ChunkSectionLocatable {
    UUID world();
    int chunkX();
    int chunkZ();

    record ImmutableChunkLocatable(UUID world, int chunkX, int chunkZ) implements ChunkLocatable {
    }
}
