package com.wellsen.notifications;

interface SelectionListener {
	public void onItemSelected(int position);
	public boolean canAllowUserClicks();
}