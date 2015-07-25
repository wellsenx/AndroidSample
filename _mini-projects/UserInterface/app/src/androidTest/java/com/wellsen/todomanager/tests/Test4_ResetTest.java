package com.wellsen.todomanager.tests;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import com.wellsen.todomanager.ToDoManagerActivity;

public class Test4_ResetTest extends
		ActivityInstrumentationTestCase2<ToDoManagerActivity> {
	private Solo solo;

	public Test4_ResetTest() {
		super(ToDoManagerActivity.class);
	}

	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
	}

	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}

	// Executes the ResetTest
	public void testRun() {

		int delay = 2000;

		// ============= Section One ==============
		// Wait for activity: 'com.wellsen.todomanager.ToDoManagerActivity'
		assertTrue("ResetTest failed:" + "Section One:"
				+ "ToDoManagerActivity did not correctly load.",
				solo.waitForActivity(
						com.wellsen.todomanager.ToDoManagerActivity.class,
						delay));

		// Click on action bar item
		solo.clickOnActionBarItem(0x1);

		solo.sleep(delay);
		
		// Click on Add New ToDo Item
		solo.clickOnView(solo.getView(com.wellsen.todomanager.R.id.footerView));

		// Wait for activity: 'com.wellsen.todomanager.AddToDoActivity'
		assertTrue("ResetTest failed:" + "Section One:"
				+ "AddToDoActivity did not correctly load.",
				solo.waitForActivity(
						com.wellsen.todomanager.AddToDoActivity.class, delay));

		// Hide the soft keyboard
		solo.hideSoftKeyboard();

		// Enter the text: 't2'
		solo.clearEditText((android.widget.EditText) solo
				.getView(com.wellsen.todomanager.R.id.title));
		
		solo.enterText((android.widget.EditText) solo
				.getView(com.wellsen.todomanager.R.id.title), "t2");

		// Hide the soft keyboard
		solo.hideSoftKeyboard();
		
		// Click on Done:
		solo.clickOnView(solo.getView(com.wellsen.todomanager.R.id.statusDone));

		// Click on High
		solo.clickOnView(solo
				.getView(com.wellsen.todomanager.R.id.highPriority));

		// Click on Reset
		solo.clickOnView(solo.getView(com.wellsen.todomanager.R.id.resetButton));

		solo.sleep(delay);
		
		// Click on Submit
		solo.clickOnView(solo
				.getView(com.wellsen.todomanager.R.id.submitButton));

		
		// ============= Section Two =================
		// Checks that reset button reset the text

		// Wait for activity: 'com.wellsen.todomanager.AddToDoActivity'
		assertTrue("ResetTest failed:" + "Section Two:"
				+ "AddToDoActivity did not correctly load.",
				solo.waitForActivity(
						com.wellsen.todomanager.AddToDoActivity.class, delay));
		
		assertFalse("ResetTest failed:" + "Section Two:"
				+ "Title of ToDo Task was not correctly reset.",
				solo.searchText("t2"));

		// Makes sure that the check box is not checked
		assertFalse("ResetTest failed:" + "SectionTwo:"
				+ "Done status of ToDo Task was not correctly reset",
				solo.isCheckBoxChecked(0));

		// Makes sure that the priority was reset to Medium
		assertTrue("ResetTest failed:" + "Section Two:"
				+ "Priority of ToDo Task was not correctly reset",
				solo.searchText("[mM][eE][dD]"));

		// Clicks on the Done box
		solo.clickOnCheckBox(0);

		// Makes sure that was able to correctly change completion status from
		// ToDoManagerActivity
		assertTrue(
				"ResetTest failed:"
						+ "Section Two:"
						+ "Was not able to modify Done status of ToDo Task from ToDoManagerActivity",
				solo.isCheckBoxChecked(0));

	}
}