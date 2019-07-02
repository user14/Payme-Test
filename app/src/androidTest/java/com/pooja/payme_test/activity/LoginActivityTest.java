package com.pooja.payme_test.activity;

import androidx.test.espresso.action.ViewActions;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.pooja.payme_test.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4ClassRunner.class)
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> loginActivityActivityTestRule = new ActivityTestRule<LoginActivity>(LoginActivity.class);

    private LoginActivity loginActivity = null;

    @Before
    public void setUp() throws Exception {
        loginActivity = loginActivityActivityTestRule.getActivity();
    }

    @Test
    public void testOnCreate() {

        assertNotNull(loginActivity.findViewById(R.id.username));
        assertNotNull(loginActivity.findViewById(R.id.password));
        assertNotNull(loginActivity.findViewById(R.id.login));

    }

    @Test
    public void userLogin() {
        onView(withId(R.id.username)).perform(ViewActions.typeText("hsbc")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.password)).perform(ViewActions.typeText("123456")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.login)).perform( click());

    }

    @After
    public void tearDown() throws Exception {
        loginActivity = null;
    }




}