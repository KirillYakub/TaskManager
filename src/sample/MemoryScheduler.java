package sample;

import java.util.ArrayList;

public class MemoryScheduler {
    private static ArrayList<MemoryBlock> memoryBlocks = new ArrayList<>();

    public static String print() {
        String result = "Memory blocks:\n";
        for (MemoryBlock memoryBlock : memoryBlocks) {
            result += memoryBlock + "\n";
        }
        return result;
    }

    public static void blockAdd(MemoryBlock memoryBlock){
        memoryBlocks.add(memoryBlock);
    }

    private static void afterMemoryFill(int blockId) {
        if(memoryBlocks.size() > 1)
            memoryBlocks.sort(MemoryBlock.byEnd);
        for(int j = 0; j < memoryBlocks.size(); j++) {
            if (memoryBlocks.get(j).blockId == blockId) {
                if (j != memoryBlocks.size() - 1) {
                    memoryBlocks.get(j + 1).startValue = memoryBlocks.get(j).endValue;
                    memoryBlocks.get(j + 1).memoryVolume =
                            memoryBlocks.get(j + 1).endValue - memoryBlocks.get(j + 1).startValue;
                } else
                    blockAdd(new MemoryBlock(memoryBlocks.get(j).endValue, Configuration.getAllMemory()));
            }
        }
    }

    public static boolean fillMemoryBlock(Process process) {
        int blockId;
        if(memoryBlocks.size() > 1)
            memoryBlocks.sort(MemoryBlock.byVolume);
        for(int i = 0; i < memoryBlocks.size(); i++) {
            if(memoryBlocks.get(i).blockState == BlockState.Empty) {
                if(memoryBlocks.get(i).memoryVolume > process.memoryBlock) {
                    memoryBlocks.get(i).blockState = BlockState.Fill;
                    memoryBlocks.get(i).idOfActiveProcess = process.id;
                    memoryBlocks.get(i).memoryVolume = process.memoryBlock;
                    memoryBlocks.get(i).endValue = memoryBlocks.get(i).startValue + memoryBlocks.get(i).memoryVolume;
                    blockId = memoryBlocks.get(i).blockId;
                    afterMemoryFill(blockId);
                    return true;
                }
            }
        }
        return false;
    }

    private static void blockBefore(int index){
        memoryBlocks.get(index).startValue -= memoryBlocks.get(index - 1).memoryVolume;
        memoryBlocks.get(index).memoryVolume += memoryBlocks.get(index - 1).memoryVolume;
        memoryBlocks.remove(memoryBlocks.get(index - 1));
    }

    private static void blockAfter(int index){
        memoryBlocks.get(index).endValue += memoryBlocks.get(index + 1).memoryVolume;
        memoryBlocks.get(index).memoryVolume += memoryBlocks.get(index + 1).memoryVolume;
        memoryBlocks.remove(memoryBlocks.get(index + 1));
    }

    private static void freePastBlock(int index, int freeBlocksQuantity){
        if(index == memoryBlocks.size() - 1 && memoryBlocks.get(index).startValue == memoryBlocks.get(index - 1).endValue) {
            if(memoryBlocks.get(index - 1).blockState == BlockState.Empty) {
                blockBefore(index);
                freeBlocksQuantity++;
            }
        }
    }

    private static void freeNextBlock(int index, int freeBlocksQuantity){
        if (index == 0 && memoryBlocks.get(index).endValue == memoryBlocks.get(index + 1).startValue) {
            if(memoryBlocks.get(index + 1).blockState == BlockState.Empty) {
                blockAfter(index);
                freeBlocksQuantity++;
            }
        }
    }

    private static void freeBothBlocks(int index, int freeBlocksQuantity){
        if(index > 0 && index < memoryBlocks.size() - 1) {
            if(memoryBlocks.get(index).endValue == memoryBlocks.get(index + 1).startValue &&
                    memoryBlocks.get(index).startValue == memoryBlocks.get(index - 1).endValue) {
                if (memoryBlocks.get(index + 1).blockState == BlockState.Empty) {
                    blockAfter(index);
                    freeBlocksQuantity++;
                }
                if (memoryBlocks.get(index - 1).blockState == BlockState.Empty) {
                    blockBefore(index);
                    freeBlocksQuantity++;
                }
            }
        }
    }

    public static void release() {
        int freeBlocksQuantity = 0;
        if(ProcessQueue.getActualProcesses().size() > 0) {
            memoryBlocks.sort(MemoryBlock.byEnd);
            for (int i = 0; i < ProcessQueue.getActualProcesses().size(); i++) {
                ProcessQueue.getActualProcesses().get(i).burstTime++;
                if (ProcessQueue.getActualProcesses().get(i).burstTime == ProcessQueue.getActualProcesses().get(i).tactInterval) {
                    for(int j = 0; j < memoryBlocks.size(); j++) {
                        if(memoryBlocks.get(j).idOfActiveProcess == ProcessQueue.getActualProcesses().get(i).id) {
                            memoryBlocks.get(j).blockState = BlockState.Empty;
                            memoryBlocks.get(j).idOfActiveProcess = -1;
                            if(memoryBlocks.size() > 1) {
                                freeNextBlock(j, freeBlocksQuantity);
                                freePastBlock(j, freeBlocksQuantity);
                                freeBothBlocks(j, freeBlocksQuantity);
                            }
                            CPU.getCore()[ProcessQueue.getActualProcesses().get(i).processCore - 1].setProcessQuantity(
                                    CPU.getCore()[ProcessQueue.getActualProcesses().get(i).processCore - 1].getProcessQuantity() - 1);
                            if(CPU.getCore()[ProcessQueue.getActualProcesses().get(i).processCore - 1].getProcessQuantity() == 0)
                                CPU.getCore()[ProcessQueue.getActualProcesses().get(i).processCore - 1].setFree(true);
                            Configuration.memoryProcessesFill -= ProcessQueue.getActualProcesses().get(i).memoryBlock;
                            ProcessQueue.getActualProcesses().get(i).processState = ProcessState.Finished;
                            ProcessQueue.getActualProcesses().get(i).processCore = -1;
                            ProcessQueue.getFinishedProcesses().add(ProcessQueue.getActualProcesses().get(i));
                            ProcessQueue.getActualProcesses().remove(ProcessQueue.getActualProcesses().get(i));
                        }
                    }
                }
            }
        }
    }

    public static ArrayList<MemoryBlock> getMemoryBlocks() {
        return memoryBlocks;
    }
}