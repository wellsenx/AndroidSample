package com.wellsen.location.tests;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import com.wellsen.location.PlaceViewActivity;
import com.wellsen.location.R;

public class TestOneValidLocation extends
		ActivityInstrumentationTestCase2<PlaceViewActivity> {
	private Solo solo;

	public TestOneValidLocation() {
		super(PlaceViewActivity.class);
	}

	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
		PlaceViewActivity.sHasNetwork = false;
	}

	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}

	public void testRun() {

		int delay = 2000;
		int longDelay = 5000;

		// Wait for activity: 'com.wellsen.location.PlaceViewActivity'
		solo.waitForActivity(com.wellsen.location.PlaceViewActivity.class,
				2000);

		// Click on action bar item
		solo.clickOnActionBarItem(com.wellsen.location.R.id.place_one);

		solo.sleep(delay);

		// Click on Get New Place
		assertTrue("Footer view isn't visible",
				solo.waitForView(com.wellsen.location.R.id.footer, 0, delay));
		solo.clickOnView(solo.getView(com.wellsen.location.R.id.footer));

		// Assert that PlaceBadge is shown
		assertTrue("PlaceBadge is not shown!", solo.waitForText(
				solo.getString(R.string.the_greenhouse_string), 1, longDelay));

	}
}
