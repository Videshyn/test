package com.example.user.mapapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.math.BigDecimal;
import java.util.ArrayList;

public class MainActivity extends Activity {

    private GoogleMap googleMap;
    private final double EARTH_RADIUS = 6371;
    private TextView textDistance;
    private Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textDistance= (TextView) findViewById(R.id.distance);
        btnReset = (Button) findViewById(R.id.reset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleMap.clear();
                markers.removeAll(markers);
                textDistance.setText("");
            }
        });
        createMapView();

    }

    ArrayList<Marker> markers = new ArrayList<Marker>();
    static final int POLYGON_POINTS = 2;
    Marker marker;

    private void createMapView(){
        /**
         * Catch the null pointer exception that
         * may be thrown when initialising the map
         */
        try {
            if(null == googleMap){
                googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.mapView)).getMap();

                googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                    @Override
                    public void onMapLongClick(LatLng latLng) {
                        if(null != googleMap){
                            MarkerOptions markerOptions = new MarkerOptions()
                                    .title("MyMarker")
                                    .draggable(true)
                                    .position(latLng);
                            if (markers.size() < POLYGON_POINTS){
                                marker = googleMap.addMarker(markerOptions);
                                markers.add(marker);
                            }

                            if (markers.size() >= POLYGON_POINTS){
                                drawLine();
                                textDistance.setText("distance = " + getDistance() + " km");
                            }
                        }
                    }
                });

//                if (markers.size() > 2){
//                    drawLine();
//                }

                /**
                 * If the map is still null after attempted initialisation,
                 * show an error to the user
                 */
                if(null == googleMap) {
                    Toast.makeText(getApplicationContext(),
                            "Error creating map",Toast.LENGTH_SHORT).show();
                }
            }
        } catch (NullPointerException exception){
            Log.e("mapApp", exception.toString());
        }
    }




    private void drawLine(){
        PolylineOptions polylineOptions = new PolylineOptions();
        for(int i=0; i < POLYGON_POINTS;i++){
            polylineOptions.add(markers.get(i).getPosition());
        }
        Polyline polyline = googleMap.addPolyline(polylineOptions);

    }



   private double getDistance(){



       double radLat1 = getRad(markers.get(0).getPosition().latitude);
       double radLat2 = getRad(markers.get(1).getPosition().latitude);
       double radLng1 = getRad(markers.get(0).getPosition().longitude);
       double radLng2 = getRad(markers.get(1).getPosition().longitude);

       // the angular difference
//       double angular = Math.acos(Math.sin(radLat1) * Math.sin(radLat2) + Math.cos(radLng1)
//               * Math.cos(radLng2) * Math.cos(radLng2 - radLng1));
//
//       double angular2 = Math.acos(Math.sin(radLat2) * Math.sin(radLat1) + Math.cos(radLng1)
//               * Math.cos(radLat2) * Math.cos(radLat1 - radLat2));

       // distance in kilometers
       //distance = angular2 * EARTH_RADIUS;

       double angular = Math.sqrt(Math.pow(Math.sin((radLat2 - radLat1) / 2), 2) + Math.cos(radLat2) * Math.cos(radLat1)
               * Math.pow(Math.sin((radLng2 - radLng1) / 2), 2));
       double distance = 2 * Math.asin(angular) * EARTH_RADIUS;
       BigDecimal bd = new BigDecimal(distance);
       distance = bd.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
       return distance;
   }

    private static double getRad(final double degree) {
        return degree * (Math.PI / 180);
    }




//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        menu.add(1, 1, 1, "clear");
//        menu.add(1, 1, 2, "exit");
//        return super.onCreateOptionsMenu(menu);
//
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case 2:
//                finish();
//                break;
//            case 1:
//                marker.remove();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
