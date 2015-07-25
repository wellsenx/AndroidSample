package com.wellsen.todomanager.tests;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import com.wellsen.todomanager.ToDoManagerActivity;

public class Test3_CancelTest extends
		ActivityInstrumentationTestCase2<ToDoManagerActivity> {
	private Solo solo;

	public Test3_CancelTest() {
		super(ToDoManagerActivity.class);
	}

	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation(),getActivity());
	}

	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}

	// Executes the CancelTest
	public void testRun() {

		int delay = 2000;

		// Wait for activity: 'com.wellsen.todomanager.ToDoManagerActivity'
		assertTrue("CancelTest failed:" +
				"Section One:" +
				"ToDoManagerActivity did not load correctly.",
				solo.waitForActivity(
						com.wellsen.todomanager.ToDoManagerActivity.class, 2000));

		// Click on action bar item
		solo.clickOnActionBarItem(0x1);

		solo.sleep(delay);
		
		// Click on Add New ToDo Item
		solo.clickOnView(solo.getView(com.wellsen.todomanager.R.id.footerView));

		// Wait for activity: 'com.wellsen.todomanager.AddToDoActivity'
		assertTrue("CancelTest failed:" +
				"Section One:" +
				"AddToDoActivity did not load correctly.",
				solo.waitForActivity(com.wellsen.todomanager.AddToDoActivity.class));

		// Hide the soft keyboard
		solo.hideSoftKeyboard();
		
		// Enter the text: 't3'
		solo.clearEditText((android.widget.EditText) solo
				.getView(com.wellsen.todomanager.R.id.title));
		
		solo.enterText((android.widget.EditText) solo
				.getView(com.wellsen.todomanager.R.id.title), "t3");
		
		// Hide the soft keyboard
		solo.hideSoftKeyboard();
		
		// Click on Done:
		solo.clickOnView(solo.getView(com.wellsen.todomanager.R.id.statusDone));
		
		// Click on High
		solo.clickOnView(solo
				.getView(com.wellsen.todomanager.R.id.highPriority));

		// Click on Cancel
		solo.clickOnView(solo
				.getView(com.wellsen.todomanager.R.id.cancelButton));
	
		// Wait for activity: 'com.wellsen.todomanager.AddToDoActivity'
		assertTrue("Cancel test failed:" + "Section One:"
				+ "AddToDoActivity did not correctly load.",
				solo.waitForActivity(
						com.wellsen.todomanager.AddToDoActivity.class, delay));
		
		// Click on Add New ToDo Item
		solo.clickOnView(solo.getView(com.wellsen.todomanager.R.id.footerView));

		// Wait for activity: 'com.wellsen.todomanager.AddToDoActivity'
		assertTrue("CancelTest failed:" +
				"Section One:" +
				"AddToDoActivity did not load correctly.",
				solo.waitForActivity(com.wellsen.todomanager.AddToDoActivity.class));

		// Hide the soft keyboard
		solo.hideSoftKeyboard();
		
		// Enter the text: 't4'
		solo.clearEditText((android.widget.EditText) solo
				.getView(com.wellsen.todomanager.R.id.title));
		
		solo.enterText((android.widget.EditText) solo
				.getView(com.wellsen.todomanager.R.id.title), "t4");

		// Hide the soft keyboard
		solo.hideSoftKeyboard();
		
		// Click on Done:
		solo.clickOnView(solo.getView(com.wellsen.todomanager.R.id.statusDone));
		
		// Click on Low
		solo.clickOnView(solo.getView(com.wellsen.todomanager.R.id.lowPriority));

		// Click on Submit
		solo.clickOnView(solo
				.getView(com.wellsen.todomanager.R.id.submitButton));
		
		// ================ Section Two ===================
		
		// Wait for activity: 'com.wellsen.todomanager.ToDoManagerActivity'
		assertTrue("CancelTest failed:" +
				"Section Two:" +
				"ToDoManagerActivity did not load correctly.",
				solo.waitForActivity(com.wellsen.todomanager.ToDoManagerActivity.class));

		assertFalse("CancelTest failed:" +
				"Section Two:" +
				"Did not correctly cancel the creation of a ToDo Task.",
				solo.searchText("t3"));
		
		assertTrue("CancelTest failed:" +
				"Section Two:" +
				"Did not correctly set title of ToDo Task following cancel.",
				solo.searchText("t4"));
		
		assertTrue("CancelTest failed:" +
				"Section Two:" +
				"Did not correctly set priority of ToDo Task following cancel.",
				solo.searchText("[lL][oO][wW]"));
		
	}
}
