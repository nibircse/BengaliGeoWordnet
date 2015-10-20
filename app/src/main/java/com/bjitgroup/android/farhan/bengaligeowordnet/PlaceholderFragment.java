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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment implements View.OnClickListener {

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String TAG = "PlaceholderFragment";

    private Button loadGPS, resetGPS;
    private EditText longitude, latitude;
    private Spinner fClass;

    private EditText bUsername, bPassword;
    private TextView errorText;
    private Button login;


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
        if (sectionNumber == 4) {
            rootView = inflater.inflate(R.layout.fragment_login, container, false);
            fragment_login_jobs(rootView);
        } else if (sectionNumber == 3)
            rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        else if (sectionNumber == 2)
            rootView = inflater.inflate(R.layout.fragment_history, container, false);
        else {
            rootView = inflater.inflate(R.layout.fragment_insert, container, false);
            fragment_insert_jobs(rootView);
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
        } else if (v.getId() == R.id.buttonLogin) {

            if (ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.INTERNET) == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.INTERNET}, 0);
            }
            String userName = bUsername.getText().toString();
            String passWord = bPassword.getText().toString();

            //errorText.setVisibility(View.INVISIBLE);
            //Toast.makeText(getActivity(), "LOGIN BUTTON", Toast.LENGTH_SHORT).show();
            if (validateLogin(userName, passWord).equals("")) {
                errorText.setVisibility(View.VISIBLE);
            }

        }
    }

    /**
     * Handles all jobs if fragment_insert is loaded.
     *
     * @param rootView
     */
    private void fragment_insert_jobs(View rootView) {
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

    /**
     * Handles all jobs if fragment_login is loaded.
     *
     * @param rootView
     */
    private void fragment_login_jobs(View rootView) {
        bUsername = (EditText) rootView.findViewById(R.id.editText_login_username);
        bPassword = (EditText) rootView.findViewById(R.id.editText_login_password);
        errorText = (TextView) rootView.findViewById(R.id.textView_login_error);
        login = (Button) rootView.findViewById(R.id.buttonLogin);
        login.setOnClickListener(this);
    }

    /**
     * @param username Username data received from the user.
     * @param password password data received from the user.
     * @return Returns token if user is validated. An empty string("") otherwise.
     */
    private String validateLogin(String username, String password) {
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = "http://www.farhanarrafi.com/user/login.html";
        final String uname = username;
        final String pass = password;
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i(TAG, response.toString());
                        //Toast.makeText(getActivity(), "Response is: " + response.substring(0, 500), Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Volley didn't work", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", uname);
                params.put("password", pass);
                return params;

            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                Log.i(TAG, response.headers.get("Set-Cookie"));
                return super.parseNetworkResponse(response);
            }
        };
        queue.add(request);

        //
        // To be continued from
        // http://developer.android.com/training/volley/simple.html#simple
        //
        return "";
    }
}
