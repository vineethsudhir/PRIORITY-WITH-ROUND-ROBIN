public class Main {

    private static PT[] ProcessTable;
    static int TimeQuanta = 4;
    static int time = 0;
    static int remaining = 0;
    static int processCount = 2;
    static gantt[] g = new gantt[100];
    static int gc = 0;
    public static void main(String[] args) {
        ProcessTable = genTable();
        while(remaining > 0){
            PT a = FindNext();
            if(a!=null)
                exec(a);
        }
        printGantt();
    }

    static void exec(PT current){
        current.exec++;
        if(current.TimeRemaining > TimeQuanta){
            current.TimeRemaining -= TimeQuanta;
            time += TimeQuanta;
            if(g[gc] != null && g[gc].PID == current.PID)
                g[gc].end = time;
            else
                g[++gc] = new gantt(current.PID, time-TimeQuanta, time);
        }
        else {
            time += current.TimeRemaining;
            if(g[gc]!=null && g[gc].PID == current.PID)
                g[gc].end = time;
            else
                g[++gc] = new gantt(current.PID, time-current.TimeRemaining, time);
            current.TimeRemaining = 0;
            remaining--;
            current.completed = true;
        }
        if(current.BurstTime == 10) {
            int comp = current.BurstTime - current.TimeRemaining;
                forkPA(time - (comp % 3));
        }
        if(current.BurstTime == 7) {
            int comp = current.BurstTime - current.TimeRemaining;
            forkPB(time - (comp % 3));
        }
    }

    private static void forkPB(int t){
        ProcessTable[++processCount] = new PT(processCount, 1, t, 5);
        remaining++;
    }

    private static void forkPA(int t){
        ProcessTable[++processCount] = new PT(processCount, 3, t, 7);
        remaining++;
    }

    static PT FindNext(){
        int count = 0;
        int MinimumPriority = 100;
        int tr = 0;
        for(int i =1; i < processCount+1; i++ ){
            if(ProcessTable[i].Priority < MinimumPriority && !ProcessTable[i].completed && ProcessTable[i].ArrivalTime <= time){
                count = i;
                MinimumPriority = ProcessTable[i].Priority;
                tr = ProcessTable[i].exec;
            }
            if(ProcessTable[i].Priority == MinimumPriority && ProcessTable[i].exec < tr && ProcessTable[i].ArrivalTime <= time){
                count = i;
                MinimumPriority = ProcessTable[i].Priority;
                tr = ProcessTable[i].exec;
            }

        }
        return ProcessTable[count];
    }

    static PT[] genTable(){
        PT[] PROC = new PT[40];
        PROC[1] = new PT(1, 2, 0, 10);
        PROC[2] = new PT(2, 3, 0, 7);
        remaining = 2;
        return PROC;
    }

    static void printGantt(){
        System.out.println("PID \t Start time   End time");
        for(int i =1; i <= gc; i++){
            System.out.println("P"+ g[i].PID + "\t\t\t" + g[i].start + "\t\t\t" + g[i].end + "");
        }
    }

    static class PT{
        int PID, Priority, ArrivalTime, BurstTime, TimeRemaining, exec;
        boolean completed;
        PT(int PID, int Priority, int ArrivalTime, int BurstTime){
            this.PID = PID;
            this.Priority = Priority;
            this.ArrivalTime = ArrivalTime;
            this.BurstTime = BurstTime;
            this.TimeRemaining = BurstTime;
            completed = false;
            this.exec = 0;
        }
    }
    static class gantt{
        int PID;
        int start;
        int end;

        gantt(int PID, int start, int end){
            this.PID = PID;
            this.start = start;
            this.end = end;
        }
    }
}
