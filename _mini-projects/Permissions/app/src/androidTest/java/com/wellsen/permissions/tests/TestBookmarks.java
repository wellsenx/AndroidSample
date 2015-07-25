package com.wellsen.permissions.tests;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import com.wellsen.permissions.ActivityLoaderActivity;

public class TestBookmarks extends
		ActivityInstrumentationTestCase2<ActivityLoaderActivity> {
	private Solo solo;

	public TestBookmarks() {
		super(ActivityLoaderActivity.class);
	}

	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation());
		getActivity();
	}

	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}

	// Executes TestBookmarks
	public void testRun() {
		
		int delay = 2000;
		
		// ================ Section One ===============
		// Wait for activity:
		// 'com.wellsen.permissions.ActivityLoaderActivity'
		assertTrue(
				"TestBookmarks:" +
				"Section One:" +
				"ActivityLoaderActivity did not load correctly.",
				solo.waitForActivity(com.wellsen.permissions.ActivityLoaderActivity.class, delay));

		// Click on Bookmarks Activity
		solo.clickOnView(solo
				.getView(com.wellsen.permissions.R.id.start_bookmarks_button));

		// Wait for activity: 'com.wellsen.permissions.BookmarksActivity'
		assertTrue(
				"TestBookmarks:" +
				"Section One:" +
				"BookmarksActivity did not load correctly.",
				solo.waitForActivity(com.wellsen.permissions.BookmarksActivity.class, delay));
		
		// ================ Section Two ===============
		// Click on Get Bookmarks
		solo.clickOnView(solo
				.getView(com.wellsen.permissions.R.id.get_bookmarks_button));

		// Check for at least one bookmark
		assertTrue("TestBookmarks:" +
				   "Section Two:" +
				   "Bookmarks are not correctly displayed.",
				solo.waitForText("http"));

		

	}
}
