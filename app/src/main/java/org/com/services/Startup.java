package org.com.services;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Startup extends BroadcastReceiver {
    private final String TAG = "Startup";
    private JobScheduler jobScheduler;
    private JobInfo jobInfo;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Startup.onReceive");

        ComponentName service = new ComponentName(context, MyJobScheduler.class);
        JobInfo.Builder builder = new JobInfo.Builder(0, service);
        builder.setMinimumLatency(1000);
        builder.setOverrideDeadline(20 * 60000);
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);

        jobInfo = builder.build();
        jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);

        onStartJob();
    }

    private void onStartJob(){
        jobScheduler.schedule(jobInfo);
    }
}
