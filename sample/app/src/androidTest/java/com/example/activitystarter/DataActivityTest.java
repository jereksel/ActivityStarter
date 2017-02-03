package com.example.activitystarter;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class DataActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void dataActivityTest() {
        ViewInteraction g = onView(withId(R.id.student_grade));
        g.perform(scrollTo(), replaceText("A"), closeSoftKeyboard());

        ViewInteraction i = onView(allOf(withId(R.id.show_data_button), withText("Show this data on new Activity")));
        i.perform(scrollTo(), click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.name_view), withText("Name: No name provided"),
                        childAtPosition(childAtPosition(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class), 0), 0),
                        isDisplayed()));
        textView.check(matches(withText("Name: No name provided")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.id_view), withText("Id: -1"),
                        childAtPosition(childAtPosition(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class), 0), 1),
                        isDisplayed()));
        textView2.check(matches(withText("Id: -1")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.grade_view), withText("Grade: A"),
                        childAtPosition(childAtPosition(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class), 0), 2),
                        isDisplayed()));
        textView3.check(matches(withText("Grade: A")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.is_passing_view), withText("Passing status: false"),
                        childAtPosition(childAtPosition(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class), 0), 3),
                        isDisplayed()));
        textView4.check(matches(withText("Passing status: false")));

        pressBack();

        ViewInteraction g2 = onView(withId(R.id.student_name));
        g2.perform(scrollTo(), replaceText("Marcin"), closeSoftKeyboard());

        ViewInteraction g3 = onView(withId(R.id.student_id));
        g3.perform(scrollTo(), replaceText("123"), closeSoftKeyboard());

        ViewInteraction switch_ = onView(allOf(withId(R.id.student_is_passing), withText("Is student passing?")));
        switch_.perform(scrollTo(), click());

        ViewInteraction i2 = onView(
                allOf(withId(R.id.show_data_button), withText("Show this data on new Activity")));
        i2.perform(scrollTo(), click());

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.name_view), withText("Name: Marcin"),
                        childAtPosition(childAtPosition(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class), 0), 0),
                        isDisplayed()));
        textView5.check(matches(withText("Name: Marcin")));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.id_view), withText("Id: 123"),
                        childAtPosition(childAtPosition(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class), 0), 1),
                        isDisplayed()));
        textView6.check(matches(withText("Id: 123")));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.grade_view), withText("Grade: A"),
                        childAtPosition(childAtPosition(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class), 0), 2),
                        isDisplayed()));
        textView7.check(matches(withText("Grade: A")));

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.is_passing_view), withText("Passing status: true"),
                        childAtPosition(childAtPosition(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class), 0), 3),
                        isDisplayed()));
        textView8.check(matches(withText("Passing status: true")));

        pressBack();
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
