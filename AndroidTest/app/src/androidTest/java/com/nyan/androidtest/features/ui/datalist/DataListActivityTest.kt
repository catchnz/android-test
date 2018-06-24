package com.nyan.androidtest.features.ui.datalist

import android.content.Intent
import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.swipeDown
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.matcher.IntentMatchers
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.nyan.androidtest.R
import com.nyan.androidtest.features.ui.data_details.DetailActivity
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)

class DataListActivityTest {
    @get:Rule
    private val repositoriesActivityRule = ActivityTestRule<DataListActivity>(DataListActivity::class.java, true, false)


    @Before
    fun setUp() {
        Intents.init();
    }

    @After
    fun tearDown() {
        Intents.release();
    }

    @Test
    fun pullDownAndLoadData() {
        val intent = Intent()
        repositoriesActivityRule.launchActivity(intent)
        Espresso.onView(ViewMatchers.withId(R.id.swipeToRefresh)).perform(swipeDown());
        Espresso.onView(ViewMatchers.withId(R.id.rvData)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Intents.intended(IntentMatchers.hasComponent(DetailActivity::class.java.name))
    }


}