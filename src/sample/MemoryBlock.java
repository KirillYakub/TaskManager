package sample;

import java.util.Comparator;

public class MemoryBlock {
    int startValue;
    int memoryVolume;
    int endValue;
    int idOfActiveProcess;
    int blockId;
    BlockState blockState;

    @Override
    public String toString() {
        String result = "";
        result += "BlockId=" + blockId +
                ", StartValue=" + startValue +
                ", EndValue=" + endValue +
                ", BlockState=" + blockState +
                ", ActiveProcessId=" + idOfActiveProcess;
        return result;
    }

    public static Comparator<MemoryBlock> byEnd = new Comparator<MemoryBlock>() {
        @Override
        public int compare(MemoryBlock o1, MemoryBlock o2) {
            return o1.endValue - o2.endValue;
        }
    };

    public static Comparator<MemoryBlock> byVolume = new Comparator<MemoryBlock>() {
        @Override
        public int compare(MemoryBlock o1, MemoryBlock o2) {
            return o1.memoryVolume- o2.memoryVolume;
        }
    };

    public MemoryBlock(int start, int finish) {
        this.blockId = Configuration.getBlockId();
        this.startValue = start;
        this.endValue = finish;
        this.memoryVolume = endValue - startValue;
        this.blockState = BlockState.Empty;
        this.idOfActiveProcess = -1;

        Configuration.setBlockId(this.blockId + 1);
    }

    public int getStartValue() { return startValue; }

    public void setStartValue(int startValue) { this.startValue = startValue; }

    public int getMemoryVolume() { return memoryVolume; }

    public void setMemoryVolume(int memoryVolume) { this.memoryVolume = memoryVolume; }

    public int getEndValue() { return endValue; }

    public void setEndValue(int endValue) { this.endValue = endValue; }

    public BlockState getBlockState() { return blockState; }

    public void setBlockState(BlockState blockState) { this.blockState = blockState; }

    public int getIdOfActiveProcess() {
        return idOfActiveProcess;
    }

    public void setIdOfActiveProcess(int idOfActiveProcess) {
        this.idOfActiveProcess = idOfActiveProcess;
    }

    public int getBlockId() {
        return blockId;
    }

    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }
}