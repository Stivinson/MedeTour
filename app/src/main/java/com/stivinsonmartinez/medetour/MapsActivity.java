package com.stivinsonmartinez.medetour;

import android.content.IntentSender;
import android.location.Location;
import android.location.LocationListener;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {

    private LocationRequest mlocation;
    private GoogleMap mMap;
    private GoogleApiClient mgoogle;
    public static final String TAG=MapsActivity.class.getSimpleName();
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST=9000;
    public CameraUpdate mCam;
    double lat;
    double lon;
    String place;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mlocation=LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10*1000)
                .setFastestInterval(1*1000);

        mgoogle=new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
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

        lat=getIntent().getExtras().getDouble("latitud");
        lon=getIntent().getExtras().getDouble("longitud");
        place=getIntent().getExtras().getString("lugar");
            // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(lat, lon);
        mMap.addMarker(new MarkerOptions().position(sydney).title(place));
        CameraPosition cameraPosition=new CameraPosition.Builder().target(sydney).zoom(15).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
       // mCam= CameraUpdateFactory.newLatLngZoom(new LatLng(lat,lon), 15);
       // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setMyLocationEnabled(true);
    }

    @Override

    protected void onResume() {

        super.onResume();
        setUpMapIfNeeded();
        mgoogle.connect();

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mgoogle.isConnected()){
            LocationServices.FusedLocationApi.removeLocationUpdates(mgoogle, this);
            mgoogle.disconnect();

        }
    }

    ///////////////toco crearla
    private void setUpMapIfNeeded() {
    }

    @Override
    public void onConnected(Bundle bundle) {

        Location location = LocationServices.FusedLocationApi.getLastLocation(mgoogle);
        if (location == null) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mgoogle,mlocation,this);
        } else {
       // handleNewLocation(location);
        }
    }

    /*private void handleNewLocation(Location location) {
        Log.d(TAG,location.toString());

        double latitud=location.getLatitude();
        double longitud=location.getLongitude();

        LatLng latLng= new LatLng(latitud,longitud);

        MarkerOptions options=new MarkerOptions()
                .position(latLng)
                .title("Mi ubicacion");
        //LatLng sydney = new LatLng(latitud, longitud);
       // mMap.addMarker(options);
        //mCam= CameraUpdateFactory.newLatLngZoom(new LatLng(lat,lon), 10);

       // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }*/

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

            if (connectionResult.hasResolution()){
                try{
                    connectionResult.startResolutionForResult(this,CONNECTION_FAILURE_RESOLUTION_REQUEST);
                }catch (IntentSender.SendIntentException e){
                    e.printStackTrace();
                }
            }else {
                Log.i(TAG,"Location services connection failed with code"+connectionResult.getErrorCode());
            }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    private void   setMarker(LatLng position,String title,String info,float opacity,float dimension1,float dimension2){
        mMap.addMarker(new MarkerOptions()
                        .position(position)
                        .title(title)
                        .snippet(info)
                        .alpha(opacity)
                        .anchor(dimension1, dimension2)
                //.icon(BitmapDescriptorFactory.fromResource(icon))

        );
    }
}
