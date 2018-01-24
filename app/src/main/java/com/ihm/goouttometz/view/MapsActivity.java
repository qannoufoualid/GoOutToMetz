package com.ihm.goouttometz.view;

import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Button;
import android.content.Intent;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.location.FusedLocationProviderClient;

import com.ihm.goouttometz.R;
import com.ihm.goouttometz.bo.Category;
import com.ihm.goouttometz.bo.Site;
import com.ihm.goouttometz.service.CategoryService;
import com.ihm.goouttometz.service.SiteService;
import com.ihm.goouttometz.view.listener.DisplayFormButtonListener;
import com.ihm.goouttometz.view.listener.DisplayListButtonListener;
import com.ihm.goouttometz.view.listener.MyLocationListener;
import com.ihm.goouttometz.view.listener.SearchButtonListener;

//assynctask

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    // CONSTANTES
    private static final int DEFAULT_ZOOM = 15;
    private final LatLng metz_location = new LatLng(-33.8523341, 151.2106085);


    private GoogleMap mMap;
    private Location mLastKnownLocation;
    private CameraPosition mCameraPosition;
    CategoryService cs;
    SiteService ss;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private MyLocationListener mLocationListener;
    boolean mLocationPermissionGranted;

    public static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1; //What is the purpose of this ??


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Button button_search = findViewById(R.id.buttonSearch);
        Button button_add = findViewById(R.id.buttonAdd);
        Button button_list = findViewById(R.id.buttonList);
        button_add.setOnClickListener(new DisplayFormButtonListener(this));
        button_list.setOnClickListener(new DisplayListButtonListener(this));
        button_search.setOnClickListener(new SearchButtonListener(this));

        // Let's set some data here !!
        cs = CategoryService.getInstance(this);
        cs.generateData();
        ss = SiteService.getInstance(this);
        ss.generateData();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        LatLng user_position;
        getLocationPermission();
        //updateLocationUI();
        getDeviceLocation();

//        if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
//            ActivityCompat.requestPermissions( this,
//                    new String[] {  android.Manifest.permission.ACCESS_COARSE_LOCATION  },
//                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION ); //TODO: we should use a constant to store this (maybe)
//        }

//        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//        Criteria criteria = new Criteria();
//        String provider = locationManager.getBestProvider(criteria, true);
//        Location location = locationManager.getLastKnownLocation(provider);
//
//
//        if (location != null) {
//            double latitude = location.getLatitude();
//            double longitude = location.getLongitude();
//            LatLng latLng = new LatLng(latitude, longitude);
//            user_position = new LatLng(latitude, longitude);
//
//
//            LatLng coordinate = new LatLng(latitude, longitude);
//            CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 19);
//            mMap.animateCamera(yourLocation);
//        }
    }
    private void getLocationPermission() {
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }



    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Résultat de la popup
        if(requestCode == 1){
            if(resultCode == 1){
                Log.i("Pouet",data.getStringArrayExtra("lol")[0]+data.getStringArrayExtra("lol")[1]);
                Log.i("Info", "I will know give you the museum");
                Log.i("Existence", ss.toString());
                for(Site s : ss.findSitesByCateory(3)){
                    System.out.println(s.getName());
                }
            }else{
                System.out.println("Le résult est pas bon : "+ resultCode);
            }
        }
        // Résutlat de l'ajout de site
        else if(requestCode == 2){
            if(resultCode == 1){
                updateDisplayedPoints();
            }else{
                Log.i("Info", "L'utilisateur n'a pas mis de nouveau point"); //Might be a bit overkill
            }
        }else {
            System.out.println("Le code a changé");
        }
    }

    public void updateDisplayedPoints(){

    }


    //===============================

    private void getDeviceLocation() {
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            String provider = locationManager.getBestProvider(criteria, true);
            mLastKnownLocation = locationManager.getLastKnownLocation(provider);
        } else {
            mMap.setMyLocationEnabled(false);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
            mLastKnownLocation = null;
            getLocationPermission();
        }

    }

//    private void getDeviceLocation() {
//        if (mLocationPermissionGranted) {
//            mLastKnownLocation = LocationServices.FusedLocationApi.getLastLocation();
//        }
//
//        // Set the map's camera position to the current location of the device.
//        if (mCameraPosition != null) {
//            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(mCameraPosition));
//        } else if (mLastKnownLocation != null) {
//            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
//                    new LatLng(mLastKnownLocation.getLatitude(),
//                            mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));
//        } else {
//            Log.d("log", "Current location is null. Using defaults.");
//            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(metz_location, DEFAULT_ZOOM));
//            mMap.getUiSettings().setMyLocationButtonEnabled(false);
//        }
//
//    }
//
//   private void updateLocationUI() {
//        if (mMap == null) {
//            return;
//        }
//        try {
//            if (mLocationPermissionGranted) {
//                mMap.setMyLocationEnabled(true);
//                mMap.getUiSettings().setMyLocationButtonEnabled(true);
//            } else {
//                mMap.setMyLocationEnabled(false);
//                mMap.getUiSettings().setMyLocationButtonEnabled(false);
//                mLastKnownLocation = null;
//                getLocationPermission();
//            }
//        } catch (SecurityException e)  {
//            Log.e("Exception: %s", e.getMessage());
//        }
//    }

    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
       // updateLocationUI();
    }


}
