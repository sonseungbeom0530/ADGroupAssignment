package com.example.adgroupassignment.activity;


import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;
import androidx.test.runner.AndroidJUnit4;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static androidx.test.InstrumentationRegistry.getInstrumentation;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import com.example.adgroupassignment.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class IntegrationTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.WRITE_EXTERNAL_STORAGE");

    @Test
    public void integrationTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btnLogin), withText("LogIn"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        0),
                                4),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.search_button), withContentDescription("Search"),
                        childAtPosition(
                                allOf(withId(R.id.search_bar),
                                        childAtPosition(
                                                withId(R.id.search_option),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction searchAutoComplete = onView(
                allOf(withId(R.id.search_src_text),
                        childAtPosition(
                                allOf(withId(R.id.search_plate),
                                        childAtPosition(
                                                withId(R.id.search_edit_frame),
                                                1)),
                                0),
                        isDisplayed()));
        searchAutoComplete.perform(replaceText(""), closeSoftKeyboard());

        ViewInteraction overflowMenuButton = onView(
                allOf(withContentDescription("More options"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                1),
                        isDisplayed()));
        overflowMenuButton.perform(click());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.title), withText("Sort by"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.title), withText("Size"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView2.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recyclerView),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction appCompatImageView2 = onView(
                allOf(withId(R.id.forward_5),
                        childAtPosition(
                                allOf(withId(R.id.relative_layout_for_bottom),
                                        childAtPosition(
                                                withId(R.id.mContainer),
                                                4)),
                                5),
                        isDisplayed()));
        appCompatImageView2.perform(click());

        ViewInteraction appCompatImageView3 = onView(
                allOf(withId(R.id.replay_5),
                        childAtPosition(
                                allOf(withId(R.id.relative_layout_for_bottom),
                                        childAtPosition(
                                                withId(R.id.mContainer),
                                                4)),
                                1),
                        isDisplayed()));
        appCompatImageView3.perform(click());

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.play_pause),
                        childAtPosition(
                                allOf(withId(R.id.relative_layout_for_bottom),
                                        childAtPosition(
                                                withId(R.id.mContainer),
                                                4)),
                                3),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction floatingActionButton2 = onView(
                allOf(withId(R.id.play_pause),
                        childAtPosition(
                                allOf(withId(R.id.relative_layout_for_bottom),
                                        childAtPosition(
                                                withId(R.id.mContainer),
                                                4)),
                                3),
                        isDisplayed()));
        floatingActionButton2.perform(click());

        ViewInteraction appCompatImageView4 = onView(
                allOf(withId(R.id.id_repeat),
                        childAtPosition(
                                allOf(withId(R.id.relative_layout_for_bottom),
                                        childAtPosition(
                                                withId(R.id.mContainer),
                                                4)),
                                6),
                        isDisplayed()));
        appCompatImageView4.perform(click());

        ViewInteraction appCompatImageView5 = onView(
                allOf(withId(R.id.id_shuffle),
                        childAtPosition(
                                allOf(withId(R.id.relative_layout_for_bottom),
                                        childAtPosition(
                                                withId(R.id.mContainer),
                                                4)),
                                0),
                        isDisplayed()));
        appCompatImageView5.perform(click());

        ViewInteraction floatingActionButton3 = onView(
                allOf(withId(R.id.play_pause),
                        childAtPosition(
                                allOf(withId(R.id.relative_layout_for_bottom),
                                        childAtPosition(
                                                withId(R.id.mContainer),
                                                4)),
                                3),
                        isDisplayed()));
        floatingActionButton3.perform(click());

        pressBack();

        ViewInteraction appCompatImageView6 = onView(
                allOf(withId(R.id.menuMore),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recyclerView),
                                        0),
                                2),
                        isDisplayed()));
        appCompatImageView6.perform(click());

        ViewInteraction appCompatTextView3 = onView(
                allOf(withId(android.R.id.title), withText("Share"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView3.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
