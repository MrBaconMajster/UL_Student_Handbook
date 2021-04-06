package com.example.ulstudenthandbook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFrag extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);

        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map);

        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                //When map is loaded
                LatLng mainBuildingMarker = new LatLng(52.673861309293834, -8.57192221735993);
                LatLng libraryMarker = new LatLng(52.673342482233245, -8.573470562705015);
                LatLng concertHallMarker = new LatLng(52.674367089655384, -8.573400825257732);
                LatLng csBuildingMarker = new LatLng(52.67394586507124, -8.575611636050743);
                LatLng kemmyBusinessMarker = new LatLng(52.672648825627206, -8.576746881016634);
                LatLng schumannBuildingMarker = new LatLng(52.67323351463209, -8.577927723535101);
                LatLng leroBuildingMarker = new LatLng(52.67450492192215, -8.577291369446533);
                LatLng lonsdaleBuildingMarker = new LatLng(52.67393489705817, -8.568863482479623);
                LatLng analogDevicesBuildingMarker = new LatLng(52.673240020168905, -8.568684160713504);
                LatLng schrodingerBuildingMarker = new LatLng(52.6738744214346, -8.567448185883915);
                LatLng pessBuildingMarker = new LatLng(52.67465088540518, -8.567623345887826);
                LatLng engineeringBuildingMarker = new LatLng(52.67495216225222, -8.572725579144697);
                LatLng languagesBuildingMarker = new LatLng(52.675558872020616, -8.573407287533216);
                LatLng healthScienceBuildingMarker = new LatLng(52.67771437969179, -8.56894191631096);
                LatLng schoolOfMedicineBuildingMarker = new LatLng(52.67834171505514, -8.568195335353717);
                LatLng irishWorldAcademy = new LatLng(52.67819532839715, -8.569706030546842);


                googleMap.addMarker(new MarkerOptions().position(mainBuildingMarker).title("Main Building(A,B,C,D,E)"));
                googleMap.addMarker(new MarkerOptions().position(libraryMarker).title("Glucksman Library(GL)"));
                googleMap.addMarker(new MarkerOptions().position(concertHallMarker).title("Foundation(F)"));
                googleMap.addMarker(new MarkerOptions().position(csBuildingMarker).title("Computer Science(CS)"));
                googleMap.addMarker(new MarkerOptions().position(kemmyBusinessMarker).title("Kemmy Business School(KB)"));
                googleMap.addMarker(new MarkerOptions().position(schumannBuildingMarker).title("Schuman(S)"));
                googleMap.addMarker(new MarkerOptions().position(leroBuildingMarker).title("Lero"));
                googleMap.addMarker(new MarkerOptions().position(lonsdaleBuildingMarker).title("Lonsdale(L)"));
                googleMap.addMarker(new MarkerOptions().position(analogDevicesBuildingMarker).title("Analog(AD)"));
                googleMap.addMarker(new MarkerOptions().position(schrodingerBuildingMarker).title("Schrödinger(SR)"));
                googleMap.addMarker(new MarkerOptions().position(pessBuildingMarker).title("PESS(P)"));
                googleMap.addMarker(new MarkerOptions().position(engineeringBuildingMarker).title("Engineering Research(ER)"));
                googleMap.addMarker(new MarkerOptions().position(languagesBuildingMarker).title("Languages(LC)"));
                googleMap.addMarker(new MarkerOptions().position(healthScienceBuildingMarker).title("Health Sciences(HS)"));
                googleMap.addMarker(new MarkerOptions().position(schoolOfMedicineBuildingMarker).title("Medical School(GEMS)"));
                googleMap.addMarker(new MarkerOptions().position(irishWorldAcademy).title("Irish World Academy(IW)"));

                googleMap.setMapType(2);
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mainBuildingMarker, 18));
            }
        });

        return view;
    }


}