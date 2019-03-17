package com.example.workclout;

/*
 * Tests that HomePage Activity starts properly
 * @author Benjamin Napier
 */

import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class HomePageStartTest {
    @Rule
    public ActivityTestRule<HomePage> mActivityTestRule = new ActivityTestRule<HomePage>(HomePage.class);

    private HomePage mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        View view = mActivity.findViewById(R.id.toolbar);

        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }
}