package com.example.myinventotrack;
import android.content.Context;
import android.content.Intent;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

/**
 * Android Instrumentation Test for MainActivity.
 * This test suite is designed to verify that the MainActivity functions correctly
 * within the Android framework, focusing on intent handling and UI interactions.
 * @date:  4/17/2024
 * @version 1.0
 * @Authors: Kate Liu
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);
    @Test
    public void testAppContextPackageName() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.myinventotrack", appContext.getPackageName());
    }
    public void testIntentToLoginActivity() {
        activityRule.getScenario().onActivity(activity -> {
            Intent expectedIntent = new Intent(activity, LoginActivity.class);
            activity.navigateToLoginActivity();
            Intent actual = activity.getIntent();
            assertNotNull("Intent should not be null", actual);
            assertEquals("Expected LoginActivity intent.", expectedIntent.getComponent(), actual.getComponent());
        });
    }
}
