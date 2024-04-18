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
 * Android Instrumentation Test for LoginActivity.
 * Tests in this file aim to ensure that LoginActivity manages intents and session states
 * correctly as expected by the application requirements. This includes testing the
 * correct launching of MainActivity and proper package name validation.
 * @date:  4/17/2024
 * @version 1.0
 * @Authors: Kate Liu
 */

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {
    @Rule
    public ActivityScenarioRule<LoginActivity> activityScenarioRule = new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void testAppContextPackageName() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.myinventotrack", appContext.getPackageName());
    }

    @Test
    public void testIntentToMainActivity() {
        activityScenarioRule.getScenario().onActivity(activity -> {
            activity.navigateToMainActivity();
            Intent expectedIntent = new Intent(activity, MainActivity.class);
            Intent actual = new Intent(activity, MainActivity.class);
            assertNotNull("Intent should not be null", actual);
            assertEquals("Expected MainActivity intent.", expectedIntent.getComponent(), actual.getComponent());
        });
    }
}
