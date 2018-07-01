package com.udacity.gradle.builditbigger;


import android.app.Application;
import android.support.test.filters.SmallTest;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.LargeTest;
import android.support.test.runner.AndroidJUnit4;


import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AsyncTest extends ApplicationTestCase<Application> implements EndpointAsyncTask.EndpointTaskNotification {
    CountDownLatch mSignal;
    private Boolean mNotNull = false;

    public AsyncTest() {
        super(Application.class);
    }

    @Test
    public void testAsyncRequestNotNull() {
        try {
            mSignal = new CountDownLatch(1);
            EndpointAsyncTask task = new EndpointAsyncTask();
            task.setListener(this);
            task.execute();
            mSignal.await();
        } catch (InterruptedException e) {
            fail();
        }
        assert mNotNull.equals(true);
    }

    @Override
    public void endpointTaskComplete(String s) {
        mNotNull = (s != null);
        mSignal.countDown();
    }
}
