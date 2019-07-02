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
public class CreateBooksActivityTest {

    @Rule
    public ActivityTestRule<CreateBookActivity> createBookActivityActivityTestRule = new ActivityTestRule<CreateBookActivity>(CreateBookActivity.class);

    private CreateBookActivity createBookActivity = null;

    @Before
    public void setUp() throws Exception {
        createBookActivity = createBookActivityActivityTestRule.getActivity();
    }

    @Test
    public void testOnCreate() {

        assertNotNull(createBookActivity.findViewById(R.id.editText_isbn));
        assertNotNull(createBookActivity.findViewById(R.id.editText_title));
        assertNotNull(createBookActivity.findViewById(R.id.editText_author));
        assertNotNull(createBookActivity.findViewById(R.id.editText_publisher));
        assertNotNull(createBookActivity.findViewById(R.id.editText_image));
        assertNotNull(createBookActivity.findViewById(R.id.button_submit));

    }

    @Test
    public void userLogin() {
        String isbn ="32132dsfs";
        String title ="gg";
        String author ="google";
        String publisher ="google";
        String url ="https://storage.googleapis.com/gd-wagtail-prod-assets/original_images/evolving_google_identity_share.jpg";

        onView(withId(R.id.editText_isbn)).perform(ViewActions.typeText(isbn)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.editText_title)).perform(ViewActions.typeText(title)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.editText_author)).perform(ViewActions.typeText(author)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.editText_publisher)).perform(ViewActions.typeText(publisher)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.editText_image)).perform(ViewActions.typeText(url)).perform(ViewActions.closeSoftKeyboard());

        onView(withId(R.id.button_submit)).perform( click());

    }

    @After
    public void tearDown() throws Exception {
        createBookActivity = null;
    }




}