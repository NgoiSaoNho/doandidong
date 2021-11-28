package khang.ngodinh.doandidong.UI.Map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import khang.ngodinh.doandidong.R;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private final double LAT_STU = 10.738132442971562;
    private final double LNG_STU = 106.67841656842558;

    private GoogleMap mMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap){
        mMap = googleMap;

        LatLng latLngSTU = new LatLng(LAT_STU, LNG_STU);

        mMap.addMarker(new MarkerOptions()
                .position(latLngSTU)
                .title("Trường Đại Học Công Nghệ Sài Gòn - STU"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngSTU, 16));
    }
}
