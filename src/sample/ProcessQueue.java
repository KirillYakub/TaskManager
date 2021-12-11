package sample;

import java.util.ArrayList;

public class ProcessQueue {
    private static ArrayList<Process> actualProcesses = new ArrayList<>();
    private static ArrayList<Process> rejectedProcesses = new ArrayList<>();
    private static ArrayList<Process> finishedProcesses = new ArrayList<>();

    public static String finishedPrint() {
        String result = "Finished processes:\n";
        if(finishedProcesses.size() > 0) {
            for (Process process : finishedProcesses) {
                result += process + "\n";
            }
        }
        else
            result += "No processes\n";
        return result;
    }

    public static String actualPrint() {
        String result = "Actual processes:\n";
        if(actualProcesses.size() > 0) {
            for (Process process : actualProcesses) {
                result += process + "\n";
            }
        }
        else
            result += "No processes\n";
        return result;
    }

    public static String rejectedPrint() {
        String result = "Rejected processes:\n";
        if(rejectedProcesses.size() > 0) {
            for (Process process : rejectedProcesses) {
                result += process + "\n";
            }
        }
        else
            result += "No processes\n";
        return result;
    }

    public static void processAdd(int quantity) {
        for(int i = 0; i < quantity; i++)
            processAdd();
    }

    private static boolean isMaxPriority(Process process){
        if(process.priority > rejectedProcesses.get(0).priority)
            return true;
        else
            return false;
    }

    public static void processAdd() {
        Process process = new Process();
        if(rejectedProcesses.size() > 1)
            rejectedProcesses.sort(Process.byPriority);
        if(rejectedProcesses.size() > 0 && !isMaxPriority(process)){
            process = rejectedProcesses.get(0);
            rejectedProcesses.remove(rejectedProcesses.get(0));
        }
        if(!MemoryScheduler.fillMemoryBlock(process)) {
            process.processState = ProcessState.Waiting;
            rejectedProcesses.add(process);
        }
        else {
            process.processState = ProcessState.Running;
            process.processCore = Utils.getRandomArrayElement(CPU.getCore().length) + 1;
            CPU.getCore()[process.processCore - 1].setProcessQuantity(
                    CPU.getCore()[process.processCore - 1].getProcessQuantity() + 1);
            CPU.getCore()[process.processCore - 1].setFree(false);
            actualProcesses.add(process);
            Configuration.memoryProcessesFill += process.memoryBlock;
        }
    }

    public static ArrayList<Process> getActualProcesses() {
        return actualProcesses;
    }

    public static ArrayList<Process> getRejectedProcesses() {
        return rejectedProcesses;
    }

    public static ArrayList<Process> getFinishedProcesses() {
        return finishedProcesses;
    }
}