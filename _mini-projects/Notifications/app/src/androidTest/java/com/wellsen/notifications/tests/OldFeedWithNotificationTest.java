	package com.wellsen.notifications.tests;

	import android.test.ActivityInstrumentationTestCase2;

	import com.robotium.solo.Solo;

	import com.wellsen.notifications.TestFrontEndActivity;

	public class OldFeedWithNotificationTest extends
			ActivityInstrumentationTestCase2<TestFrontEndActivity> {
		private Solo solo;

		public OldFeedWithNotificationTest() {
			super(TestFrontEndActivity.class);
		}

		public void setUp() throws Exception {
			solo = new Solo(getInstrumentation());
			getActivity();
		}

		@Override
		public void tearDown() throws Exception {
			solo.finishOpenedActivities();
		}

		public void testRun() {

			int shortDelay = 2000;

			// Clear the log
			solo.clearLog();

			// Wait for activity:
			// 'com.wellsen.notifications.TestFrontEndActivity'
			solo.waitForActivity(
					com.wellsen.notifications.TestFrontEndActivity.class,
					shortDelay);

			// Click on Make Tweets Old
			solo.clickOnView(solo
					.getView(com.wellsen.notifications.R.id.age_tweets_button));

			// Click on Start Main Activity
			solo.clickOnView(solo
					.getView(com.wellsen.notifications.R.id.start_main_button));

			// Wait for activity: 'com.wellsen.notifications.MainActivity'
			assertTrue(
					"com.wellsen.notifications.MainActivity is not found!",
					solo.waitForActivity(com.wellsen.notifications.MainActivity.class));

			// Press menu back key
			solo.goBackToActivity("TestFrontEndActivity");

/*
			// Wait for activity:
			// 'com.wellsen.notifications.TestFrontEndActivity'
	//		assertTrue(
	//				"com.wellsen.notifications.TestFrontEndActivity is not found!",
	//				solo.waitForActivity(com.wellsen.notifications.TestFrontEndActivity.class));
*/

			// Robotium can't check notification area directly
			String msg = getActivity().getString(com.wellsen.notifications.R.string.notification_sent_string);
			assertTrue("Notification was not sent", solo.waitForText(msg));
		}
	}
