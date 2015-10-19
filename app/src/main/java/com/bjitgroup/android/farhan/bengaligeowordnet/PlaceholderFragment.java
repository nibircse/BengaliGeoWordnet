package com.bjitgroup.android.farhan.bengaligeowordnet;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment implements View.OnClickListener {

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    public Button loadGPS, resetGPS;
    public EditText longitude, latitude;
    public Spinner fClass;
    private LocationListener locLsnr = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            longitude.setText(String.valueOf(location.getLongitude()));
            latitude.setText(String.valueOf(location.getLatitude()));
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    public PlaceholderFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER, 1);
        View rootView;
        if (sectionNumber == 4)
            rootView = inflater.inflate(R.layout.fragment_login, container, false);
        else if (sectionNumber == 3)
            rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        else if (sectionNumber == 2)
            rootView = inflater.inflate(R.layout.fragment_history, container, false);
        else {
            rootView = inflater.inflate(R.layout.fragment_insert, container, false);
            loadGPS = (Button) rootView.findViewById(R.id.buttonLoadCoordinate);
            loadGPS.setOnClickListener(this);
            resetGPS = (Button) rootView.findViewById(R.id.buttonResetCoordinate);
            resetGPS.setOnClickListener(this);

            longitude = (EditText) rootView.findViewById(R.id.editText_longitude);
            latitude = (EditText) rootView.findViewById(R.id.editText_latitude);
            fClass = (Spinner) rootView.findViewById(R.id.spinner_geo_class);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                    R.array.feature_class, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            fClass.setAdapter(adapter);
        }
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((NavigationActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonLoadCoordinate) {
            LocationManager locMgr = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

            if (ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
            }
            locMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5, 0, locLsnr);
        } else if (v.getId() == R.id.buttonResetCoordinate) {
            longitude.setText("");
            latitude.setText("");
        }
    }
}
