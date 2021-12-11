package sample;

public class Configuration {
    private static final int allMemory = 4096;
    public static final int OSMemory = 128;
    private static final int coresQuantity = 4;
    private static final int maxPriority = 32;
    private static final int minProcessMemory = 10;
    private static final int maxProcessMemory = 512;
    private static final int minTactInterval = 5;
    private static final int maxTactInterval = 10;
    private static final int lowerMemory = 0;
    private static boolean programWork = false;
    private static int processId = 0;
    private static int blockId = 0;

    private static String actualProcesses;
    private static String rejectedProcesses;
    private static String finalProcesses;
    private static String activeCores;
    private static String memoryBlocks;

    public static int memoryProcessesFill = 0;
    public static int threadSleep = 5000;

    public static int getProcessId() {
        return processId;
    }

    public static void setProcessId(int id) {
        processId = id;
    }

    public static int getMaxPriority() {
        return maxPriority;
    }

    public static int getAllMemory() {
        return allMemory;
    }

    public static int getLowerMemory() { return lowerMemory; }

    public static int getMinProcessMemory() { return minProcessMemory; }

    public static int getMaxProcessMemory() { return maxProcessMemory; }

    public static int getMinTactInterval() { return minTactInterval; }

    public static int getMaxTactInterval() { return maxTactInterval; }

    public static int getOSMemory() { return OSMemory; }

    public static int getBlockId() { return blockId; }

    public static void setBlockId(int blockId) { Configuration.blockId = blockId; }

    public static boolean isProgramWork() { return programWork; }

    public static void setProgramWork(boolean programWork) { Configuration.programWork = programWork; }

    public static int getCoresQuantity() { return coresQuantity; }

    public static String getRejectedProcesses() { return rejectedProcesses; }

    public static void setRejectedProcesses(String rejectedProcesses) { Configuration.rejectedProcesses = rejectedProcesses; }

    public static String getActualProcesses() { return actualProcesses; }

    public static void setActualProcesses(String actualProcesses) { Configuration.actualProcesses = actualProcesses; }

    public static String getFinalProcesses() { return finalProcesses; }

    public static void setFinalProcesses(String finalProcesses) { Configuration.finalProcesses = finalProcesses; }

    public static String getActiveCores() { return activeCores; }

    public static void setActiveCores(String activeCores) { Configuration.activeCores = activeCores; }

    public static String getMemoryBlocks() { return memoryBlocks; }

    public static void setMemoryBlocks(String memoryBlocks) { Configuration.memoryBlocks = memoryBlocks; }
}