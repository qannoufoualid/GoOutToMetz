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
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ihm.goouttometz.R;
import com.ihm.goouttometz.bo.Category;
import com.ihm.goouttometz.bo.Site;
import com.ihm.goouttometz.service.CategoryService;
import com.ihm.goouttometz.service.SiteService;
import com.ihm.goouttometz.view.adapters.MarkerInfo;
import com.ihm.goouttometz.view.listener.DisplayFormButtonListener;
import com.ihm.goouttometz.view.listener.DisplayListButtonListener;
import com.ihm.goouttometz.view.listener.SearchButtonListener;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {


    private GoogleMap mMap;
    CategoryService cs;
    SiteService ss;
    Location my_location;
    public static int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    public int distance;
    public int asked_category;


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

        distance = 5000;
        asked_category = 0;

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
        updateCamera(true);
        updateDisplayedPoints();

        mMap.setInfoWindowAdapter(new MarkerInfo(getLayoutInflater()));
        mMap.setOnInfoWindowClickListener(this);
    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Résultat de la popup
        if(requestCode == 1){
            if(resultCode == 1){
                Log.i("INFO !! ",  "Catégorie : " + String.valueOf(data.getIntArrayExtra("lol")[0]+1) + ", Distance : " + String.valueOf(data.getIntArrayExtra("lol")[1]));
                asked_category = data.getIntArrayExtra("lol")[0]+1;
                distance = data.getIntArrayExtra("lol")[1];
                updateDisplayedPoints();
            }else{
                System.out.println("Le résult est pas bon : "+ resultCode);
            }
        }
        // Résutlat de l'ajout de site
        else if(requestCode == 2){
            if(resultCode == 1){
                updateDisplayedPoints();
            }else{
                Log.i("INFO !! ", "L'utilisateur n'a pas mis de nouveau point"); //Might be a bit overkill
            }
        }else {
            System.out.println("Le code a changé");
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        updateCamera(false);
        updateDisplayedPoints();
    }

    public void updateDisplayedPoints(){
        if(mMap != null) {
            mMap.clear();
            Log.i("INFO !! ", "J'ai effacé les marker !!");
            if (my_location != null && asked_category != 0) {// very clumsy
                Location loc = new Location("Mais pourquoi diable a t'on besoin de ca !!");
                for (Site s : ss.findSitesByCateory(asked_category)) {
                    loc.setLatitude(s.getLatitude());
                    loc.setLongitude(s.getLongitude());
                    if (my_location.distanceTo(loc) <= distance) {
//                        Log.i("INFO !! ", "Distance entre moi et le point : " + String.valueOf(my_location.distanceTo(loc)));
//
//                        LatLng site_loc = new LatLng(s.getLatitude(), s.getLongitude());
//                        mMap.addMarker(new MarkerOptions().position(site_loc).title(s.getName()).snippet(s.getAddress()));
//                        Log.i("FILL", "Fill the map : I placed " + s.getName());
                        addMarker(s.getLatitude(), s.getLongitude(), s.getName(), s.getSummary(), my_location.distanceTo(loc));
                    }
                }
            } else {
                Log.i("INFO !! ", "Je suis passé par ici");
                for (Site s : ss.getAll()) {
//                    LatLng site_loc = new LatLng(s.getLatitude(), s.getLongitude());
//                    mMap.addMarker(new MarkerOptions().position(site_loc).title(s.getName()).snippet(s.getAddress()));
                    addMarker(s.getLatitude(), s.getLongitude(), s.getName(), s.getSummary(), 0);

                }
            }
        }
    }

    private void addMarker(float lat, float lo, String title, String description, float dist){
        LatLng site_loc = new LatLng(lat, lo);

        mMap.addMarker(new MarkerOptions().position(site_loc).title(title).snippet(description + "\n" + "Distance : "+String.valueOf(dist)));
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
                CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 12);
                mMap.animateCamera(yourLocation);
            }else{
                LatLng metz = new LatLng(49.119118, 6.176321);
                CameraUpdate look_at_metz = CameraUpdateFactory.newLatLngZoom(metz, 11);
                mMap.animateCamera(look_at_metz);
            }
        }
    }

    public float[] getLatlng(){
         return new float[] {(float)my_location.getLatitude(), (float)my_location.getLongitude()};
    }

    public Location getMy_location(){
        return my_location;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Log.i("FAKENEWS !!", "Je ne sais pas quand est-ce que ça apparait.");
    }
}