package com.wellsen.location;

import android.app.ListActivity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class PlaceViewActivity extends ListActivity implements LocationListener {
	private static final long FIVE_MINS = 5 * 60 * 1000;
	private static final String TAG = "Lab-Location";

	// False if you don't have network access
	public static boolean sHasNetwork = false;

	private Location mLastLocationReading;
	private PlaceViewAdapter mAdapter;
	private LocationManager mLocationManager;
	private boolean mMockLocationOn = false;

	// default minimum time between new readings
	private long mMinTime = 5000;

	// default minimum distance between old and new readings.
	private float mMinDistance = 1000.0f;

	// A fake location provider used for testing
	private MockLocationProvider mMockLocationProvider;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Set up the app's user interface. This class is a ListActivity,
        // so it has its own ListView. ListView's adapter should be a PlaceViewAdapter

		mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		ListView placesListView = getListView();

		// TODO - add a footerView to the ListView
		// You can use footer_view.xml to define the footer

        View footerView = null;
		footerView = getLayoutInflater().inflate(R.layout.footer_view, placesListView,false);
		placesListView.addFooterView(footerView);

		// TODO - footerView must respond to user clicks, handling 3 cases:

                footerView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
                Log.i(TAG, "Entered footerView.OnClickListener.onClick()");

                boolean lastLocation = mLastLocationReading != null && ageInMilliseconds(mLastLocationReading) < FIVE_MINS;
                if (!lastLocation) {
                    // There is no current location - response is up to you. The best
                    // solution is to always disable the footerView until you have a
                    // location.
                    Log.i(TAG, "There is no current location");

                } else {

                    if (mAdapter.intersects(mLastLocationReading)){
                        // There is a current location, but the user has already acquired a
                        // PlaceBadge for this location - issue a Toast message with the text -
                        // "You already have this location badge."
                        // Use the PlaceRecord class' intersects() method to determine whether
                        // a PlaceBadge already exists for a given location
                        Log.i(TAG, "You already have this location badge");
                        Toast.makeText(arg0.getContext(), "You already have this location badge", Toast.LENGTH_LONG).show();
                    } else {
                        // There is a current location for which the user does not already have
                        // a PlaceBadge. In this case download the information needed to make a new
                        // PlaceBadge.
                        Log.i(TAG, "Download information to make a new PlaceBadge");
                        addNewPlace(new PlaceRecord(mLastLocationReading));
                        new PlaceDownloaderTask(PlaceViewActivity.this, sHasNetwork).execute(mLastLocationReading);
                    }

                }
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
			}

		});

		placesListView.addFooterView(footerView);
		mAdapter = new PlaceViewAdapter(getApplicationContext());
		setListAdapter(mAdapter);

	}

	@Override
	protected void onResume() {
		super.onResume();

		startMockLocationManager();

		// TODO - Check NETWORK_PROVIDER for an existing location reading.
		// Only keep this last reading if it is fresh - less than 5 minutes old

        mLastLocationReading = null;
        mLastLocationReading = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (mLastLocationReading != null && ageInMilliseconds(mLastLocationReading) > FIVE_MINS) {
            // Age of last reading is too old, discard.
            mLastLocationReading = null;
        }

		// TODO - register to receive location updates from NETWORK_PROVIDER
        mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, mMinTime, mMinDistance, this);




    }

	@Override
	protected void onPause() {

		// TODO - unregister for location updates
        mLocationManager.removeUpdates(this);
        
        
		shutdownMockLocationManager();
		super.onPause();
	}

	// Callback method used by PlaceDownloaderTask
	public void addNewPlace(PlaceRecord place) {
	
		// TODO - Attempt to add place to the adapter, considering the following cases

		// A PlaceBadge for this location already exists - issue a Toast message
		// with the text - "You already have this location badge." Use the PlaceRecord 
		// class' intersects() method to determine whether a PlaceBadge already exists
		// for a given location. Do not add the PlaceBadge to the adapter

        if (place == null) {
            // The place is null - issue a Toast message with the text
            // "PlaceBadge could not be acquired"
            // Do not add the PlaceBadge to the adapter
            Toast.makeText(this, "PlaceBadge could not be acquired", Toast.LENGTH_LONG).show();
        } else if (place.getPlace() == null) {
            // The place has no country name - issue a Toast message
            // with the text - "There is no country at this location".
            // Do not add the PlaceBadge to the adapter
            Toast.makeText(this, "There is no country at this location", Toast.LENGTH_LONG).show();
        } else {
            // Otherwise - add the PlaceBadge to the adapter
            mAdapter.add(place);
        }

        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
	}

	// LocationListener methods
	@Override
	public void onLocationChanged(Location currentLocation) {

		// TODO - Update location considering the following cases.
		// 1) If there is no last location, set the last location to the current
		// location.
		// 2) If the current location is older than the last location, ignore
		// the current location
		// 3) If the current location is newer than the last locations, keep the
		// current location.

        if (mLastLocationReading == null || currentLocation.getTime() > mLastLocationReading.getTime()) {
            mLastLocationReading = currentLocation;
        }





        // mLastLocationReading = null;
	}

	@Override
	public void onProviderDisabled(String provider) {
		// not implemented
	}

	@Override
	public void onProviderEnabled(String provider) {
		// not implemented
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// not implemented
	}

	// Returns age of location in milliseconds
	private long ageInMilliseconds(Location location) {
		return System.currentTimeMillis() - location.getTime();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.delete_badges:
			mAdapter.removeAllViews();
			return true;
		case R.id.place_one:
			mMockLocationProvider.pushLocation(37.422, -122.084);
			return true;
		case R.id.place_no_country:
			mMockLocationProvider.pushLocation(0, 0);
			return true;
		case R.id.place_two:
			mMockLocationProvider.pushLocation(38.996667, -76.9275);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void shutdownMockLocationManager() {
		if (mMockLocationOn) {
			mMockLocationProvider.shutdown();
		}
	}

	private void startMockLocationManager() {
		if (!mMockLocationOn) {
			mMockLocationProvider = new MockLocationProvider(
					LocationManager.NETWORK_PROVIDER, this);
		}
	}
}
