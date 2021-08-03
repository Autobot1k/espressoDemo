package com.example.android.testing.espresso.IdlingResourceSample;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
    public class ChangeTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);
    private IdlingResource mIdling;


    @Before
    public void registerIdlingResources() {

        mIdling = activityTestRule.getActivity().getIdlingResource();

        IdlingRegistry.getInstance().register(mIdling);
    }

    @After
    public void unRegisteredIdlingResources() {
        if (mIdling != null) {
            IdlingRegistry.getInstance().unregister(mIdling);
        }
    }

    @Test
    public void changeText_sameActivity() {
        onView(withId(R.id.editTextUserInput))
                .perform(typeText("Hello TAU"));
        closeSoftKeyboard();
        onView(withId(R.id.changeTextBt))
                .perform(click());
        onView(withId(R.id.textToBeChanged))
                .check(matches(withText("Hello TAU")));
    }
}