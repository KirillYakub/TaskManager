package sample;

import java.util.Comparator;

public class Process {
    String processName;
    int priority;
    int id;
    int tactInterval;
    int memoryBlock;
    int startTact;
    int burstTime;
    int processCore;
    ProcessState processState;

    public static Comparator<Process> byPriority = new Comparator<Process>() {
        @Override
        public int compare(Process o1, Process o2) {
            return o2.priority - o1.priority;
        }
    };

    @Override
    public String toString() {
        String result = "";
        result += "Name=" + processName + '\'' +
                ", Priority=" + priority +
                ", Id=" + id +
                ", MemoryBlock=" + memoryBlock +
                ", ProcessCore=" + processCore +
                ", State=" + processState;
        return result;
    }

    public Process() {
        this.id = Configuration.getProcessId();
        this.memoryBlock = Utils.getRandomValue(Configuration.getMinProcessMemory(), Configuration.getMaxProcessMemory());
        this.tactInterval = Utils.getRandomValue(Configuration.getMinTactInterval(), Configuration.getMaxTactInterval());
        this.priority = Utils.getRandomValue(Configuration.getMaxPriority());
        this.startTact = ProgramTimer.getProgramTact();
        this.burstTime = 0;
        this.processState = ProcessState.Ready;
        this.processName = "process" + this.id;
        this.processCore = -1;

        Configuration.setProcessId(this.id + 1);
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public void setState(ProcessState processState) {
        this.processState = processState;
    }

    public String getProcessName() {
        return processName;
    }

    public int getPriority() {
        return priority;
    }

    public int getId() {
        return id;
    }

    public int getTactInterval() {
        return tactInterval;
    }

    public int getMemoryBlock() {
        return memoryBlock;
    }

    public int getStartTact() {
        return startTact;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public ProcessState getState() {
        return processState;
    }
}