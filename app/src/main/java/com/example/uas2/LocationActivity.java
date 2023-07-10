package com.example.uas2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;

import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.config.Configuration;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.Marker;

import java.util.ArrayList;

public class LocationActivity extends AppCompatActivity implements MapEventsReceiver{
    private final int REQEST_PERMISSION_REQUEST_CODE = 1;
    private MapView map = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        map = (MapView)findViewById(R.id.location_mapview);
        MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(this, this);
        map.getOverlays().add(0, mapEventsOverlay);
        map.setTileSource(TileSourceFactory.MAPNIK);

        //region permission checking
        String[] permissionStrings = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};
        requestPermissionIfNecessary(permissionStrings);
        map.getController().setZoom(15.0);
        GeoPoint g = new GeoPoint(-6.915845285206341, 107.58613438261567);
        map.getController().setCenter(g);

        GeoPoint[] outletGPList = {g,
                                    new GeoPoint(-6.916319633556482, 107.59370478791487),
                                    new GeoPoint(-6.912804868628957, 107.59174141113208)};
        String[] outletTitles = {"Outlet A\nJl. xxx no. 00", "Outlet B\nRuko Paskal HyperSquare blok 00", "Outlet C\nJl. xxx no. 00"};

        for (int i=0; i<3; i++) {
            GeoPoint outletPoint = outletGPList[i];
            Marker startMarker = new Marker(map);
            startMarker.setPosition(outletPoint);
            startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            startMarker.setTitle(outletTitles[i]);
            map.getOverlays().add(startMarker);
        }

        map.invalidate();
        //endregion
    }

    private void requestPermissionIfNecessary(String[] permissions){
        ArrayList<String> permissionToRequest = new ArrayList<>();
        for (String permission:permissions){
            if(ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED){
                permissionToRequest.add(permission);
            }
        }
        if (permissionToRequest.size() > 0) {
            ActivityCompat.requestPermissions(this, permissionToRequest.toArray(new String[permissionToRequest.size()]), REQEST_PERMISSION_REQUEST_CODE);

        }
    }
    @Override
    public boolean singleTapConfirmedHelper(GeoPoint p) {
        return false;
    }

    @Override
    public boolean longPressHelper(GeoPoint p) {
        return false;
    }
}