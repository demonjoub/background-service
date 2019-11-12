package org.com.services;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;
import android.widget.Toast;

import org.com.backgroundservices.MJobExecuter;

public class MyJobScheduler extends JobService {
    private MJobExecuter mJobExecuter;
    private final String TAG = "MyJobScheduler";
    @Override
    public boolean onStartJob(final JobParameters params) {
        Log.d(TAG, "on Start Job");
        mJobExecuter = new MJobExecuter()
        {
            @Override
            protected void onPostExecute(String s) {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                jobFinished(params, true);
            }
        };
        mJobExecuter.execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        mJobExecuter.cancel(true);
        return false;
    }
}
