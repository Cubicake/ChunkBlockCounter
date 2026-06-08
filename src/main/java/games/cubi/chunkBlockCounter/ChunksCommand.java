package games.cubi.chunkBlockCounter;

import net.strokkur.commands.Command;
import net.strokkur.commands.Executes;

import java.util.concurrent.ConcurrentHashMap;

@Command("listUniqueBlocksPerChunk")
public class ChunksCommand {
    @Executes
    public void execute() {
        ConcurrentHashMap<ChunkSectionLocatable, Integer> chunkBlockCounts = ChunkPacketListener.uniqueBlockCountByChunkSection;
        int[] numberOfChunksWithBlockCount = new int[4097];
        for (Integer blockCount : chunkBlockCounts.values()) {
            numberOfChunksWithBlockCount[blockCount]++;
        }
        for (int blockCount = 0; blockCount < numberOfChunksWithBlockCount.length; blockCount++) {
            int chunkCount = numberOfChunksWithBlockCount[blockCount];
            if (chunkCount > 0) {
                ChunkBlockCounter.logger.info("There are " + chunkCount + " chunks with " + blockCount + " unique blocks.");
            }
        }
    }
}
