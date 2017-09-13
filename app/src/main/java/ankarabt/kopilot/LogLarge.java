package ankarabt.kopilot;

public class LogLarge {

    public static void v(String TAG, String message) {
        int maxLogSize = 2000;
        for(int i = 0; i <= message.length() / maxLogSize; i++) {
            int start = i * maxLogSize;
            int end = (i+1) * maxLogSize;
            end = end > message.length() ? message.length() : end;
            android.util.Log.d(TAG, message.substring(start, end));
        }
    }

}