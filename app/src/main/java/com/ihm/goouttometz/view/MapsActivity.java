package com.ihm.goouttometz.view;

import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ihm.goouttometz.R;
import com.ihm.goouttometz.bo.Category;
import com.ihm.goouttometz.bo.Site;
import com.ihm.goouttometz.service.CategoryService;
import com.ihm.goouttometz.service.SiteService;
import com.ihm.goouttometz.view.listener.DisplayFormButtonListener;
import com.ihm.goouttometz.view.listener.DisplayListButtonListener;
import com.ihm.goouttometz.view.listener.SearchButtonListener;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    CategoryService cs;
    SiteService ss;
    Location my_location;
    public static int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    public static final int distance = 5000;


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
        updateCamera(true);
        updateDisplayedPoints();
    }



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

    @Override
    public void onResume(){
        super.onResume();
        updateCamera(false);
    }

    public void updateDisplayedPoints(){
        if(my_location != null) {
            float[] results = new float[1];
            for (Site s : ss.getAll()) {
                Location.distanceBetween(my_location.getLatitude(), my_location.getLongitude(), s.getLatitude(), s.getLatitude(), results);
                if (results[0] >= distance) {
                    LatLng site_loc = new LatLng(s.getLatitude(), s.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(site_loc).title(s.getName()));
                }
            }
        }else{
            for (Site s: ss.getAll() ) {
                LatLng site_loc = new LatLng(s.getLatitude(), s.getLongitude());
                mMap.addMarker(new MarkerOptions().position(site_loc).title(s.getName()));
            }
        }

    }

    public void updateCamera(boolean need){
        if(mMap != null) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
                LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                Criteria criteria = new Criteria();
                String provider = locationManager.getBestProvider(criteria, true);
                my_location = locationManager.getLastKnownLocation(provider);
            } else {
                if (need)
                    ActivityCompat.requestPermissions(this,
                            new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                            PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            }
            if (my_location != null) {
                double latitude = my_location.getLatitude();
                double longitude = my_location.getLongitude();
                LatLng coordinate = new LatLng(latitude, longitude);
                CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 16);
                mMap.animateCamera(yourLocation);
            }
        }
    }

    public float[] getLatlng(){
         return new float[] {(float)my_location.getLatitude(), (float)my_location.getLongitude()};
    }

    public Location getMy_location(){
        return my_location;
    }

}