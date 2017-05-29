package occasionfinder.zjacer.com.occasionfinderandroid;

import android.content.Context;
import android.content.Intent;
import android.os.Process;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionHandler implements java.lang.Thread.UncaughtExceptionHandler {
    private final Context myContext;

    public ExceptionHandler(Context context) {
        myContext = context;
    }

    public void uncaughtException(Thread thread, Throwable exception) {
        StringWriter stackTrace = new StringWriter();
        exception.printStackTrace(new PrintWriter(stackTrace));
        System.err.println(stackTrace);

        Intent intent = new Intent(myContext, CrashActivity.class);
        myContext.startActivity(intent);

        Process.killProcess(Process.myPid());
        System.exit(0);
    }
}
