package sample;

public class Scheduler extends Thread {

    public static void programStart() {
        CPU.coresInitialization();
        Configuration.setProgramWork(true);
        ProgramTimer timer = new ProgramTimer();
        timer.start();
        MemoryScheduler.blockAdd(new MemoryBlock(Configuration.getLowerMemory(), Configuration.getAllMemory()));
    }

    @Override
    public void run(){
        while (Configuration.isProgramWork()){
            ProcessQueue.processAdd(2);
            Configuration.setActualProcesses(ProcessQueue.actualPrint());
            Configuration.setRejectedProcesses(ProcessQueue.rejectedPrint());
            Configuration.setFinalProcesses(ProcessQueue.finishedPrint());
            Configuration.setMemoryBlocks(MemoryScheduler.print());
            Configuration.setActiveCores(CPU.corePrint());
            try {
                Thread.sleep(Configuration.threadSleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
