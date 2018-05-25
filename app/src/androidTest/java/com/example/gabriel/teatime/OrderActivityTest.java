package com.example.gabriel.teatime;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(JUnit4.class)
public class OrderActivityTest {
    @Rule
    public ActivityTestRule<OrderActivity> mActivityTestRule = new ActivityTestRule<>(OrderActivity.class);

    @Test
    public void clickIncrementButtonAndChangesQuantityAndCost() {
        onView((withId(R.id.btn_increment))).perform(click());
        onView(withId(R.id.tv_quantity)).check(matches(withText("1")));
        onView(withId(R.id.tv_total)).check(matches(withText("$5.00")));
    }

    @Test
    public void clickDecrementButtonAndChangesQuantityAndCost() {
        onView(withId(R.id.tv_quantity)).check(matches(withText("0")));
        onView((withId(R.id.btn_decrement))).perform(click());
        onView(withId(R.id.tv_quantity)).check(matches(withText("0")));
        onView(withId(R.id.tv_total)).check(matches(withText("$0.00")));
    }
}
