package com.Test.WSIE

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import java.io.IOException
import java.util.*


class Activity_maps_searchEatery : AppCompatActivity(),
    OnMapReadyCallback,
    ActivityCompat.OnRequestPermissionsResultCallback {
    val BUSAN = LatLng(35.17944, 129.07556)

    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var location: Location
    // Snackbar 사용하기 위해서 View가 필요
    private lateinit var mLayout: View
    // (참고로 Toast에서는 Context가 필요했습니다.)
    private lateinit var currentPosition: LatLng

    private val TAG = "googlemap_example"
    private val GPS_ENABLE_REQUEST_CODE = 2001
    private val UPDATE_INTERVAL_MS = 1000L // 1초
    private val FASTEST_UPDATE_INTERVAL_MS = 500L // 0.5초

    private var mMap: GoogleMap? = null
    private var currentMarker: Marker? = null

    // onRequestPermissionsResult에서 수신된 결과에서 ActivityCompat.requestPermissions를 사용한 퍼미션 요청을 구별하기 위해 사용됩니다.
    private val PERMISSIONS_REQUEST_CODE = 100
    var needRequest = false

    // 앱을 실행하기 위해 필요한 퍼미션을 정의합니다.
    var REQUIRED_PERMISSIONS = arrayOf<String>(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    ) // 외부 저장소

    var mCurrentLocatiion: Location? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
        );
        setContentView(R.layout.activity_maps)
        mLayout = findViewById(R.id.layout_main)
        locationRequest = LocationRequest()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(UPDATE_INTERVAL_MS)
            .setFastestInterval(FASTEST_UPDATE_INTERVAL_MS)
        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(locationRequest)
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart");

        if (checkPermission()) {
            Log.d(TAG, "onStart : call mFusedLocationClient.requestLocationUpdates");
            mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);

            Log.d(TAG, "mMap = " + mMap)
            if (mMap != null)
                mMap?.setMyLocationEnabled(true);

        }
    }

    //맵이 준비가 되었을 때 호출되는 함수
    override fun onMapReady(p0: GoogleMap) {
        mMap = p0
        //런타임 퍼미션 요청 대화상자나 GPS 활성 요청 대화상자 보이기전에
        //지도의 초기위치를 부산으로 이동
        setDefaultLocation();

        //런타임 퍼미션 처리

        Log.d(TAG, "퍼미션 확인 직전")
        if (checkPermission()
        ) {
            // 2. 이미 퍼미션을 가지고 있다면
            // ( 안드로이드 6.0 이하 버전은 런타임 퍼미션이 필요없기 때문에 이미 허용된 걸로 인식합니다.)
            Log.d(TAG, "startLocationUpdates 함수 실행")
            startLocationUpdates(); // 3. 위치 업데이트 시작
        }
    }

    //ActivityCompat.requestPermissions를 사용한 퍼미션 요청의 결과를 리턴받는 함수
    override fun onRequestPermissionsResult(
        permsRequestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (permsRequestCode == PERMISSIONS_REQUEST_CODE && grantResults.size == REQUIRED_PERMISSIONS.size) {

            // 요청 코드가 PERMISSIONS_REQUEST_CODE 이고, 요청한 퍼미션 개수만큼 수신되었다면

            var check_result = true;

            // 모든 퍼미션을 허용했는지 체크합니다.
            for (result in grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }


            if (check_result) {
                // 퍼미션을 허용했다면 위치 업데이트를 시작합니다.
                startLocationUpdates();
            } else {
                // 거부한 퍼미션이 있다면 앱을 사용할 수 없는 이유를 설명해주고 앱을 종료합니다.2 가지 경우가 있습니다.
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        REQUIRED_PERMISSIONS[0]
                    )
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        REQUIRED_PERMISSIONS[1]
                    )
                ) {
                    // 사용자가 거부만 선택한 경우에는 앱을 다시 실행하여 허용을 선택하면 앱을 사용할 수 있습니다.
                    Snackbar.make(
                        mLayout, "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요. ",
                        Snackbar.LENGTH_INDEFINITE
                    ).setAction("확인", View.OnClickListener() {
                        finish()
                    }).show()
                } else {
                    // "다시 묻지 않음"을 사용자가 체크하고 거부를 선택한 경우에는 설정(앱 정보)에서 퍼미션을 허용해야 앱을 사용할 수 있습니다.
                    Snackbar.make(
                        mLayout, "퍼미션이 거부되었습니다. 설정(앱 정보)에서 퍼미션을 허용해야 합니다. ",
                        Snackbar.LENGTH_INDEFINITE
                    ).setAction("확인", View.OnClickListener() {
                        finish();
                    }).show();
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            GPS_ENABLE_REQUEST_CODE ->
                //사용자가 GPS 활성 시켰는지 검사
                if (checkLocationServicesStatus()) {
                    Log.d(TAG, "onActivityResult : GPS 활성화 되있음");
                    needRequest = true;
                    return;
                }
        }
    }

    override fun onStop() {
        super.onStop()
        if (mFusedLocationClient != null) {
            Log.d(TAG, "onStop : call stopLocationUpdates");
            mFusedLocationClient.removeLocationUpdates(locationCallback);
        }
    }

    fun setDefaultLocation() { //디폴트 위치, 부산
        val markerTitle = "위치정보 가져올 수 없음"
        val markerSnippet = "위치 퍼미션과 GPS 활성 요부 확인하세요"
        currentMarker?.remove()
        val markerOptions = MarkerOptions()
        markerOptions.position(BUSAN)
        markerOptions.title(markerTitle)
        markerOptions.snippet(markerSnippet)
        markerOptions.draggable(true)
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        currentMarker = mMap?.addMarker(markerOptions)
        val cameraUpdate = CameraUpdateFactory.newLatLngZoom(BUSAN, 15f)
        mMap?.moveCamera(cameraUpdate)
    }

    private fun startLocationUpdates() {
        //로케이션 서비스 상태 확인
        if (!checkLocationServicesStatus()) {
            Log.d(TAG, "startLocationUpdates : call showDialogForLocationServiceSetting")
            showDialogForLocationServiceSetting()
        } else {
            val hasFineLocationPermission = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            val hasCoarseLocationPermission = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            if (hasFineLocationPermission != PackageManager.PERMISSION_GRANTED ||
                hasCoarseLocationPermission != PackageManager.PERMISSION_GRANTED
            ) {
                Log.d(TAG, "startLocationUpdates : 퍼미션 안가지고 있음")
                return
            }
            Log.d(TAG, "startLocationUpdates : call mFusedLocationClient.requestLocationUpdates")
            mFusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.myLooper()
            )
            if (checkPermission()) mMap?.isMyLocationEnabled = true
        }
    }

    fun checkLocationServicesStatus(): Boolean {
        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    //여기부터는 GPS 활성화를 위한 메소드들
    fun showDialogForLocationServiceSetting() {

        val builder = AlertDialog.Builder(this);
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage(
            "앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n"
                    + "위치 설정을 수정하실래요?"
        );
        builder.setCancelable(true);
        builder.setPositiveButton("설정",
            DialogInterface.OnClickListener { DialogInterface, Int ->
                val callGPSSettingIntet =
                    Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivityForResult(callGPSSettingIntet, GPS_ENABLE_REQUEST_CODE)
            })
        builder.setNegativeButton("취소",
            DialogInterface.OnClickListener { dialog: DialogInterface, id: Int ->
                dialog.cancel()
            })
        builder.create().show();
    }

    val locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult?) {
            super.onLocationResult(p0)

            val locationList = p0!!.locations

            if (locationList.size > 0) {
                location = locationList.get(locationList.size - 1);

                currentPosition = LatLng(location.getLatitude(), location.getLongitude());

                val markerTitle = getCurrentAddress(currentPosition);
                val markerSnippet =
                    "위도:" + (location.getLatitude().toString()) + " 경도:" +
                            (location.getLongitude().toString())

                Log.d(TAG, "onLocationResult : " + markerSnippet);

                //현재 위치에 마커 생성하고 이동
                setCurrentLocation(location, markerTitle, markerSnippet);

                mCurrentLocatiion = location;
            }
        }
    }

    fun getCurrentAddress(latlng: LatLng): String {
        //지오코더... GPS를 주소로 변환
        val geocoder = Geocoder(this, Locale.getDefault());
        var addresses = mutableListOf<Address>()
        try {
            addresses = geocoder.getFromLocation(
                latlng.latitude,
                latlng.longitude,
                1
            );
        } catch (ioException: IOException) {
            //네트워크 문제
            Toast.makeText(this, "지오코더 서비스 사용불가", Toast.LENGTH_LONG).show();
            return "지오코더 서비스 사용불가";
        } catch (illegalArgumentException: IllegalArgumentException) {
            Toast.makeText(this, "잘못된 GPS 좌표", Toast.LENGTH_LONG).show();
            return "잘못된 GPS 좌표";
        }

        if (addresses == null || addresses.size == 0) {
            Toast.makeText(this, "주소 미발견", Toast.LENGTH_LONG).show();
            return "주소 미발견";

        } else {
            val address = addresses.get(0);
            return address.getAddressLine(0).toString();
        }
    }

    fun setCurrentLocation(location: Location, markerTitle: String, markerSnippet: String) {
        currentMarker?.remove();

        val currentLatLng = LatLng(location.getLatitude(), location.getLongitude());

        val markerOptions = MarkerOptions();
        markerOptions.position(currentLatLng);
        markerOptions.title(markerTitle);
        markerOptions.snippet(markerSnippet);
        markerOptions.draggable(true);

        currentMarker = mMap?.addMarker(markerOptions);

        val cameraUpdate = CameraUpdateFactory.newLatLng(currentLatLng);
        mMap?.moveCamera(cameraUpdate);
    }

    //여기부터는 런타임 퍼미션 처리을 위한 메소드들
    private fun checkPermission(): Boolean {
        val hasFineLocationPermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        );
        val hasCoarseLocationPermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        );

        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
            hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED
        ) {
            return true;
        }
        return false;
    }
}
