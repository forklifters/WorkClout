package com.example.workclout;

/*
 * Tests that SetupProfile Activity starts properly
 * @author Benjamin Napier
 */

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class SetupProfileStartTest {
    @Rule
    public ActivityTestRule<SetupProfile> mActivityTestRule = new ActivityTestRule<SetupProfile>(SetupProfile.class);

    private SetupProfile mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        View view = mActivity.findViewById(R.id.WeightID);

        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }
}