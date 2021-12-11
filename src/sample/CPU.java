package sample;

public class CPU {
    private static Cores[] core;

    public static String corePrint() {
        String result = "Cores busyness: ";
        for (Cores cores : core) {
            result += cores + " ";
        }
        return result;
    }

    public static void coresInitialization(){
        core = new Cores[Configuration.getCoresQuantity()];
        for(int i = 0; i < core.length; i++)
            core[i] = new Cores();
    }

    public static Cores[] getCore() { return core; }

    public static void setCore(Cores[] core) { CPU.core = core; }
}