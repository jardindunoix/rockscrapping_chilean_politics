//package com.example.rockscrappinchileanpolitics;
//
//import android.content.BroadcastReceiver;
//import android.os.Build;
//import android.os.Bundle;
//
//import com.example.rockscrappinchileanpolitics.d_utilities.NetworkStatusReceiver;
//
//public class HomeDetailActivity extends AppCompatActivity {
//    private BroadcastReceiver broadcastNetworkReceiver;
//    @Override
//    protected void onCreate( Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        broadcastNetworkReceiver = new NetworkStatusReceiver();
//        registerNetworkBroadcast();
//    }
////    public static void messageConnectionStatus(boolean value){
////        if(value){
////            connectionStatus.setText("¡Tenemos acceso a internet!");
////            connectionStatus.setBackgroundColor(Color.GREEN);
////            connectionStatus.setTextColor(Color.WHITE);
////
////        }else {
////            connectionStatus.setVisibility(View.VISIBLE);
////            connectionStatus.setText("¡No podemos conectarnos!");
////            connectionStatus.setBackgroundColor(Color.RED);
////            connectionStatus.setTextColor(Color.WHITE);
////        }
////    }
//    private void registerNetworkBroadcast() {
//        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            registerReceiver(broadcastNetworkReceiver, new
//                    IntentFilter( ConnectivityManager.CONNECTIVITY_ACTION));
//        }
//    }
//    protected void unregisterNetworkBroadcast() {
//        try {
//            unregisterReceiver(broadcastNetworkReceiver);
//        } catch (IllegalArgumentException e) {
//            e.printStackTrace();
//        }
//    }
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        unregisterNetworkBroadcast();
//    }
//}