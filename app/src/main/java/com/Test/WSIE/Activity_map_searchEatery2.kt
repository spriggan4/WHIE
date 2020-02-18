package com.Test.WSIE

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_map2.*


class Activity_map_searchEatery2 : AppCompatActivity(), OnMapReadyCallback
    , ActivityCompat.OnRequestPermissionsResultCallback {
    //런타임에서 권한이 필요한 퍼미션 목록
    val PERMISSIONS = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    //퍼미션 승인 요청시 사용하는 요청 코드
    val REQUEST_PERMISSION_CODE = 1

    //기본 맵 줌 레벨
    val DEFAULT_ZOOM_LEVEL = 17f

    //구글 맵 객체를 참조할 멤버 변수
    private lateinit var mMap: GoogleMap

    //부산 위도,경도
    val BUSAN = LatLng(35.17944, 129.07556)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map2)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        Log.d("TAG","onCreate")
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
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        //현재 위치를 찾는데 시간이 걸리므로 먼저 부산 이동
        mMap.addMarker(MarkerOptions().position(BUSAN).title("Marker in BUSAN"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(BUSAN))
        //현재 위치 표시 비활성화
        mMap.isMyLocationEnabled=false
        when {
            //권한 있으면 현재 위치로 이동
            hasPermissions() -> {
                moveMyLocation()
            }
            else->{
                //권한요청
                ActivityCompat.requestPermissions(this,PERMISSIONS,REQUEST_PERMISSION_CODE)
        }
    }
        Log.d("TAG","onMapReady")
}

override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    moveMyLocation()
    Log.d("TAG","onRequestPermissionResult")
}

//맵 초기화 함수
@SuppressLint("MissingPermission")
fun moveMyLocation() {
    //현재위치 표시 활성화
    mMap.isMyLocationEnabled = true
    //현재위치로 카메라 이동
    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(getMyLocation(), DEFAULT_ZOOM_LEVEL))
}

fun hasPermissions(): Boolean {
    //퍼미션 목록중 하나라도 권한이 없으면 false 반환
    for (permission in PERMISSIONS) {
        if (ActivityCompat.checkSelfPermission(this, permission) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            return false
        }
    }
    return true
}

@SuppressLint("MissingPermission")
fun getMyLocation(): LatLng {
    //위치를 측정하는 프로바이더를 GPS 센서로 지정
    val locationProvider = LocationManager.GPS_PROVIDER
    //위치 서비스 객체를 불러옴
    val locationManager =
        getSystemService(Context.LOCATION_SERVICE) as LocationManager
    //마지막으로 업데이트된 위치를 가져옴
    val lastKnownLocation = locationManager.getLastKnownLocation(locationProvider)
    //위도 경도 객체로 반환
    //값이 제대로 들어갔으면 반환 null이면 부산 위치 반환
    lastKnownLocation?.let {
        return LatLng(lastKnownLocation.latitude, lastKnownLocation.longitude)
    }
    return BUSAN
}

override fun onResume() {
    super.onResume()
    map.onResume()
    Log.d("TAG","onResume")
}

override fun onPause() {
    super.onPause()
    map.onPause()
}

override fun onDestroy() {
    super.onDestroy()
    map.onDestroy()
}

override fun onLowMemory() {
    super.onLowMemory()
    map.onLowMemory()
}
}



