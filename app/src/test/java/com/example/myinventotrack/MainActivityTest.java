package com.example.myinventotrack;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.android.controller.ActivityController;
import android.app.Application;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {28}, application = MainActivityTest.TestApplication.class, manifest = Config.NONE)
public class MainActivityTest {
    private MainActivity mainActivity;
    private ActivityController<MainActivity> controller;

    @Before
    public void setUp() {
        controller = Robolectric.buildActivity(MainActivity.class)
                .create()
                .start()
                .resume();
        mainActivity = controller.get();
    }

    @After
    public void tearDown() {
        controller.pause().stop().destroy();
    }

    @Test
    public void usernameValidation_CorrectUsername_ReturnsTrue() {
        assertTrue("Valid username should return true", mainActivity.isValidUsername("valid_user123"));
    }

    @Test
    public void usernameValidation_InvalidUsername_ReturnsFalse() {
        assertFalse("Invalid username should return false", mainActivity.isValidUsername("us"));
    }

    public static class TestApplication extends Application {
    }
}
