package com.example.myinventotrack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import android.content.Context;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Test
    public void testAppContextPackageName() {
        Context appContext = ApplicationProvider.getApplicationContext();
        assertEquals("com.example.myinventotrack", appContext.getPackageName());
    }

    @Test
    public void testAppContextNotNull() {
        Context appContext = ApplicationProvider.getApplicationContext();
        assertNotNull("App context should not be null", appContext);
    }

    @Test
    public void testAppContextApplicationName() {
        Context appContext = ApplicationProvider.getApplicationContext();
        assertEquals("MyInventoTrack", appContext.getString(R.string.app_name));
    }
}
