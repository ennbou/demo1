package com.ennbou.mvvm

import android.app.Activity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.ext.truth.content.IntentSubject.assertThat
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import com.ennbou.mvvm.ui.UserAdapter
import com.ennbou.mvvm.uicontrollers.DetailsActivity
import com.ennbou.mvvm.uicontrollers.ListActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ListDetailsActivities {

    @get:Rule
    val activityRule = ActivityScenarioRule(ListActivity::class.java)

    @Test
    fun startDetailsActivity() {

        Intents.init()

        onView(withId(R.id.user_list_refresh)).perform(swipeDown())


        onView(ViewMatchers.withId(R.id.user_list))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<UserAdapter.ViewHolder>(
                    0,
                    click()
                )
            )
        intended(hasComponent(DetailsActivity::class.java.name))

        Intents.release()
    }


    @Test
    fun checkIntentExtractData() {

        Intents.init()

        onView(withId(R.id.user_list_refresh)).perform(swipeDown())

        onView(ViewMatchers.withId(R.id.user_list))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<UserAdapter.ViewHolder>(
                    0,
                    click()
                )
            )
        intended(hasComponent(DetailsActivity::class.java.name))

        assertThat(getActivityInstance()?.intent).extras().containsKey("userid")
        assertThat(getActivityInstance()?.intent).extras().longInt("userid").isAtMost(1)

        Intents.release()

    }


    private fun getActivityInstance(): Activity? {
        val currentActivity = arrayOf<Activity?>(null)
        getInstrumentation().runOnMainSync(Runnable {
            val resumedActivity =
                ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED)
            val it: Iterator<Activity> = resumedActivity.iterator()
            currentActivity[0] = it.next()
        })
        return currentActivity[0]
    }


}