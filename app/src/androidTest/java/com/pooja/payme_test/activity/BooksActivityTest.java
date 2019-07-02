package com.pooja.payme_test.activity;

import androidx.test.espresso.action.ViewActions;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import com.pooja.payme_test.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4ClassRunner.class)
public class BooksActivityTest {

    @Rule
    public ActivityTestRule<BooksActivity> booksActivityActivityTestRule = new ActivityTestRule<BooksActivity>(BooksActivity.class);

    private BooksActivity booksActivity = null;

    @Before
    public void setUp() throws Exception {
        booksActivity = booksActivityActivityTestRule.getActivity();
    }

    @Test
    public void testOnCreate() {

        assertNotNull(booksActivity.findViewById(R.id.recyclerView));
        assertNotNull(booksActivity.findViewById(R.id.addBook));

    }

    @Test
    public void submit() {
         onView(withId(R.id.addBook)).perform( click());
    }

    @After
    public void tearDown() throws Exception {
        booksActivity = null;
    }

}