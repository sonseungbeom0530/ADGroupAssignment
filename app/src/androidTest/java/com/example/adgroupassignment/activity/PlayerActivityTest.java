package com.example.adgroupassignment.activity;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.adgroupassignment.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class PlayerActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.WRITE_EXTERNAL_STORAGE");

    @Test
    public void playerActivityTest() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.etEmail),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("test10@test.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.etEmail), withText("test10@test.com"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText2.perform(pressImeActionButton());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.etPass),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("test1010"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.etPass), withText("test1010"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText4.perform(pressImeActionButton());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btnLogin), withText("LogIn"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        0),
                                4),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recyclerView),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(2, click()));

        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.id_shuffle),
                        childAtPosition(
                                allOf(withId(R.id.relative_layout_for_bottom),
                                        childAtPosition(
                                                withId(R.id.mContainer),
                                                4)),
                                0),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction appCompatImageView2 = onView(
                allOf(withId(R.id.id_next),
                        childAtPosition(
                                allOf(withId(R.id.relative_layout_for_bottom),
                                        childAtPosition(
                                                withId(R.id.mContainer),
                                                4)),
                                4),
                        isDisplayed()));
        appCompatImageView2.perform(click());

        ViewInteraction appCompatImageView3 = onView(
                allOf(withId(R.id.id_prev),
                        childAtPosition(
                                allOf(withId(R.id.relative_layout_for_bottom),
                                        childAtPosition(
                                                withId(R.id.mContainer),
                                                4)),
                                2),
                        isDisplayed()));
        appCompatImageView3.perform(click());

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
                allOf(withId(R.id.id_next),
                        childAtPosition(
                                allOf(withId(R.id.relative_layout_for_bottom),
                                        childAtPosition(
                                                withId(R.id.mContainer),
                                                4)),
                                4),
                        isDisplayed()));
        appCompatImageView5.perform(click());

        ViewInteraction appCompatImageView6 = onView(
                allOf(withId(R.id.forward_5),
                        childAtPosition(
                                allOf(withId(R.id.relative_layout_for_bottom),
                                        childAtPosition(
                                                withId(R.id.mContainer),
                                                4)),
                                5),
                        isDisplayed()));
        appCompatImageView6.perform(click());

        ViewInteraction appCompatImageView7 = onView(
                allOf(withId(R.id.replay_5),
                        childAtPosition(
                                allOf(withId(R.id.relative_layout_for_bottom),
                                        childAtPosition(
                                                withId(R.id.mContainer),
                                                4)),
                                1),
                        isDisplayed()));
        appCompatImageView7.perform(click());

        ViewInteraction appCompatImageView8 = onView(
                allOf(withId(R.id.id_next),
                        childAtPosition(
                                allOf(withId(R.id.relative_layout_for_bottom),
                                        childAtPosition(
                                                withId(R.id.mContainer),
                                                4)),
                                4),
                        isDisplayed()));
        appCompatImageView8.perform(click());

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

        ViewInteraction appCompatImageView9 = onView(
                allOf(withId(R.id.id_prev),
                        childAtPosition(
                                allOf(withId(R.id.relative_layout_for_bottom),
                                        childAtPosition(
                                                withId(R.id.mContainer),
                                                4)),
                                2),
                        isDisplayed()));
        appCompatImageView9.perform(click());

        ViewInteraction appCompatImageView10 = onView(
                allOf(withId(R.id.id_repeat),
                        childAtPosition(
                                allOf(withId(R.id.relative_layout_for_bottom),
                                        childAtPosition(
                                                withId(R.id.mContainer),
                                                4)),
                                6),
                        isDisplayed()));
        appCompatImageView10.perform(click());

        ViewInteraction appCompatImageView11 = onView(
                allOf(withId(R.id.id_next),
                        childAtPosition(
                                allOf(withId(R.id.relative_layout_for_bottom),
                                        childAtPosition(
                                                withId(R.id.mContainer),
                                                4)),
                                4),
                        isDisplayed()));
        appCompatImageView11.perform(click());

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
