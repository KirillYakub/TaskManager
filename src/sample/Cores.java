package sample;

public class Cores {
    private int processQuantity;
    private boolean isFree;

    @Override
    public String toString() {
        String result = "";
        result += isFree + " ; ";
        return result;
    }

    public Cores() {
        this.processQuantity = 0;
        this.isFree = true;
    }

    public int getProcessQuantity() {
        return processQuantity;
    }

    public void setProcessQuantity(int processQuantity) {
        this.processQuantity = processQuantity;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }
}
