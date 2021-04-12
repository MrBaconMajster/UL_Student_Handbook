package com.example.ulstudenthandbook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsFrag  extends Fragment{

    String marker;

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
                //Buildings
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

                //Parking
                LatLng parkingPaid1 = new LatLng(52.67260599309352, -8.569047354229467);
                LatLng parkingPaid2 = new LatLng(52.67265649302877, -8.564941267893385);
                LatLng parkingPaid3 = new LatLng(52.67482238126959, -8.575756393633233);


                LatLng parkingFree1 = new LatLng(52.671729224196284, -8.565649482299666);
                LatLng parkingFree2 = new LatLng(52.67455039763344, -8.572393224158875);
                LatLng parkingFree3 = new LatLng(52.674777992897994, -8.57924902777863);
                LatLng parkingFree4 = new LatLng(52.67315044979818, -8.578639857305841);
                LatLng parkingFree5 = new LatLng(52.680020402028184, -8.569479487570957);
                LatLng parkingFree6 = new LatLng(52.68080964259418, -8.568886032457556);
                LatLng parkingFree7 = new LatLng(52.68050348785219, -8.570974278677058);


                LatLng parkingStaff1 = new LatLng(52.672923284470556, -8.56764896906389);
                LatLng parkingStaff2 = new LatLng(52.67374942188429, -8.56643926885988);
                LatLng parkingStaff3 = new LatLng(52.67331178628309, -8.567345963737981);
                LatLng parkingStaff4 = new LatLng(52.67515333215915, -8.574204637197049);
                LatLng parkingStaff5 = new LatLng(52.67476832270112, -8.578607897681858);
                LatLng parkingStaff6 = new LatLng(52.67332609926109, -8.578409187335653);
                LatLng parkingStaff7 = new LatLng(52.67229496174581, -8.576837412867256);
                LatLng parkingStaff8 = new LatLng(52.68067968376338, -8.57604644847448);
                LatLng parkingStaff9 = new LatLng(52.67969504716686, -8.56997276643686);
                LatLng parkingStaff10 = new LatLng(52.679478250452064, -8.567768963344285);
                LatLng parkingStaff11 = new LatLng(52.674098289446, -8.5774882806745);



                ArrayList<Marker> buildingArray = new ArrayList<>();
                ArrayList<Marker> parkingArray = new ArrayList<>();

                // Buildings
                buildingArray.add(googleMap.addMarker(new MarkerOptions().position(mainBuildingMarker).title("Main Building(A,B,C,D,E)")));
                buildingArray.add(googleMap.addMarker(new MarkerOptions().position(libraryMarker).title("Glucksman Library(GL)")));
                buildingArray.add(googleMap.addMarker(new MarkerOptions().position(concertHallMarker).title("Foundation(F)")));
                buildingArray.add(googleMap.addMarker(new MarkerOptions().position(csBuildingMarker).title("Computer Science(CS)")));
                buildingArray.add(googleMap.addMarker(new MarkerOptions().position(kemmyBusinessMarker).title("Kemmy Business School(KB)")));
                buildingArray.add(googleMap.addMarker(new MarkerOptions().position(schumannBuildingMarker).title("Schuman(S)")));
                buildingArray.add(googleMap.addMarker(new MarkerOptions().position(leroBuildingMarker).title("Tierney")));
                buildingArray.add(googleMap.addMarker(new MarkerOptions().position(lonsdaleBuildingMarker).title("Lonsdale(L)")));
                buildingArray.add(googleMap.addMarker(new MarkerOptions().position(analogDevicesBuildingMarker).title("Analog(AD)")));
                buildingArray.add(googleMap.addMarker(new MarkerOptions().position(schrodingerBuildingMarker).title("Schrödinger(SR)")));
                buildingArray.add(googleMap.addMarker(new MarkerOptions().position(pessBuildingMarker).title("PESS(P)")));
                buildingArray.add(googleMap.addMarker(new MarkerOptions().position(engineeringBuildingMarker).title("Engineering Research(ER)")));
                buildingArray.add(googleMap.addMarker(new MarkerOptions().position(languagesBuildingMarker).title("Languages(LC)")));
                buildingArray.add(googleMap.addMarker(new MarkerOptions().position(healthScienceBuildingMarker).title("Health Sciences(HS)")));
                buildingArray.add(googleMap.addMarker(new MarkerOptions().position(schoolOfMedicineBuildingMarker).title("Medical School(GEMS)")));
                buildingArray.add(googleMap.addMarker(new MarkerOptions().position(irishWorldAcademy).title("Irish World Academy(IW)")));

                parkingArray.add(googleMap.addMarker(new MarkerOptions().position(parkingPaid1).title("Paid Public Parking").icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("orange_parking_transparent",75, 100)))));
                parkingArray.add(googleMap.addMarker(new MarkerOptions().position(parkingPaid2).title("Paid Public Parking").icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("orange_parking_transparent",75, 100)))));
                parkingArray.add(googleMap.addMarker(new MarkerOptions().position(parkingPaid3).title("Paid Public Parking").icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("orange_parking_transparent",75, 100)))));

                parkingArray.add(googleMap.addMarker(new MarkerOptions().position(parkingFree1).title("Free Public Parking").icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("blue_parking_transparent",75, 100)))));
                parkingArray.add(googleMap.addMarker(new MarkerOptions().position(parkingFree2).title("Free Public Parking").icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("blue_parking_transparent",75, 100)))));
                parkingArray.add(googleMap.addMarker(new MarkerOptions().position(parkingFree3).title("Free Public Parking").icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("blue_parking_transparent",75, 100)))));
                parkingArray.add(googleMap.addMarker(new MarkerOptions().position(parkingFree4).title("Free Public Parking").icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("blue_parking_transparent",75, 100)))));
                parkingArray.add(googleMap.addMarker(new MarkerOptions().position(parkingFree5).title("Free Public Parking").icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("blue_parking_transparent",75, 100)))));
                parkingArray.add(googleMap.addMarker(new MarkerOptions().position(parkingFree6).title("Free Public Parking").icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("blue_parking_transparent",75, 100)))));
                parkingArray.add(googleMap.addMarker(new MarkerOptions().position(parkingFree7).title("Free Public Parking").icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("blue_parking_transparent",75, 100)))));

                parkingArray.add(googleMap.addMarker(new MarkerOptions().position(parkingStaff1).title("Staff Parking").icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("red_parking_transparent",75, 100)))));
                parkingArray.add(googleMap.addMarker(new MarkerOptions().position(parkingStaff2).title("Staff Parking").icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("red_parking_transparent",75, 100)))));
                parkingArray.add(googleMap.addMarker(new MarkerOptions().position(parkingStaff3).title("Staff Parking").icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("red_parking_transparent",75, 100)))));
                parkingArray.add(googleMap.addMarker(new MarkerOptions().position(parkingStaff4).title("Staff Parking").icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("red_parking_transparent",75, 100)))));
                parkingArray.add(googleMap.addMarker(new MarkerOptions().position(parkingStaff5).title("Staff Parking").icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("red_parking_transparent",75, 100)))));
                parkingArray.add(googleMap.addMarker(new MarkerOptions().position(parkingStaff6).title("Staff Parking").icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("red_parking_transparent",75, 100)))));
                parkingArray.add(googleMap.addMarker(new MarkerOptions().position(parkingStaff7).title("Staff Parking").icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("red_parking_transparent",75, 100)))));
                parkingArray.add(googleMap.addMarker(new MarkerOptions().position(parkingStaff8).title("Staff Parking").icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("red_parking_transparent",75, 100)))));
                parkingArray.add(googleMap.addMarker(new MarkerOptions().position(parkingStaff9).title("Staff Parking").icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("red_parking_transparent",75, 100)))));
                parkingArray.add(googleMap.addMarker(new MarkerOptions().position(parkingStaff10).title("Staff Parking").icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("red_parking_transparent",75, 100)))));
                parkingArray.add(googleMap.addMarker(new MarkerOptions().position(parkingStaff11).title("Staff Parking").icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("red_parking_transparent",75, 100)))));

                googleMap.setMapType(2);


                if(getArguments() != null) {
                    System.out.println(getArguments().getString("marker"));

                    marker = getArguments().getString("marker");

                    if(marker  == "mainBuildingMarker")
                    {
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mainBuildingMarker, 18));
                    }
                    else if(marker  == "libraryMarker")
                    {
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(libraryMarker, 18));
                    }
                    else if(marker  == "concertHallMarker")
                    {
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(concertHallMarker, 18));
                    }
                    else if(marker  == "csBuildingMarker")
                    {
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(csBuildingMarker, 18));
                    }
                    else if(marker  == "kemmyBusinessMarker")
                    {
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(kemmyBusinessMarker, 18));
                    }
                    else if(marker  == "schumannBuildingMarker")
                    {
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(schumannBuildingMarker, 18));
                    }
                    else if(marker  == "leroBuildingMarker")
                    {
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(leroBuildingMarker, 18));
                    }
                    else if(marker  == "lonsdaleBuildingMarker")
                    {
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lonsdaleBuildingMarker, 18));
                    }
                    else if(marker  == "analogDevicesBuildingMarker")
                    {
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(analogDevicesBuildingMarker, 18));
                    }
                    else if(marker  == "schrodingerBuildingMarker")
                    {
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(schrodingerBuildingMarker, 18));
                    }
                    else if(marker  == "pessBuildingMarker")
                    {
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pessBuildingMarker, 18));
                    }
                    else if(marker  == "engineeringBuildingMarker")
                    {
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(engineeringBuildingMarker, 18));
                    }
                    else if(marker  == "languagesBuildingMarker")
                    {
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(languagesBuildingMarker, 18));
                    }
                    else if(marker  == "healthScienceBuildingMarker")
                    {
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(healthScienceBuildingMarker, 18));
                    }
                    else if(marker  == "schoolOfMedicineBuildingMarker")
                    {
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(schoolOfMedicineBuildingMarker, 18));
                    }
                    else if(marker  == "irishWorldAcademy")
                    {
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(irishWorldAcademy, 18));
                    }

                }
                else
                {
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mainBuildingMarker, 16));
                }
//
//
//
            }

        });

        return view;
    }

    public Bitmap resizeBitmap(String drawableName,int width, int height){
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(),getResources().getIdentifier(drawableName, "drawable", getActivity().getPackageName()));
        return Bitmap.createScaledBitmap(imageBitmap, width, height, false);
    }

}