package affirm;

public class LogCenter {
    int[] times;
    int[] logs;

    public LogCenter(){
        times = new int[60];
        logs = new int[60];
    }

    public void writeLog(int timestamp, int logNum) {
        int index = timestamp % 60;
        if (times[index] != timestamp) {
            times[index] = timestamp;
            logs[index] = 0;
        }
        logs[index] += logNum;
    }

    // Get the total numbers in the last hour
    public int getTotalLogNums(int timestamp) {
        int total = 0;
        for (int i = 0; i < 60; i++) {
            if (timestamp - times[i] < 60)
                total += logs[i];
        }
        return total;
    }
}

// Basic ideal is using buckets. 1 bucket for every minutes because we
// only need to keep the recent log info for 60 minutes.
// logs[] array is wrapped around by mod operation.
// Each log bucket is associated with times[] bucket which record
// current time. If it is not current time, it means it is
// 1 hour or two hours... ago and need to reset to the current log num.
