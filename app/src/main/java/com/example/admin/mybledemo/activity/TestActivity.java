//package com.example.admin.mybledemo.activity;
//
//import android.bluetooth.BluetoothGatt;
//import android.bluetooth.BluetoothGattCharacteristic;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import com.example.admin.mybledemo.LeDeviceListAdapter;
//import com.example.admin.mybledemo.R;
//import com.example.admin.mybledemo.annotation.LLAnnotation;
//import com.example.admin.mybledemo.annotation.ViewInit;
//import com.orhanobut.logger.Logger;
//
//import java.util.Arrays;
//
//import cn.com.heaton.blelibrary.ble.BleLisenter;
//import cn.com.heaton.blelibrary.ble.BleManager;
//import cn.com.heaton.blelibrary.ble.BleDevice;
//import cn.com.heaton.blelibrary.ble.BleStates;
//
//public class TestActivity extends AppCompatActivity {
//
//    private static final String TAG = "TestActivity";
//    @ViewInit(R.id.lv_scan)
//    private ListView mListView;
//    private LeDeviceListAdapter mLeDeviceListAdapter;
//
//    private BleManager<BleDevice> mManager;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_test);
//        //初始化注解  替代findViewById
//        LLAnnotation.viewInit(this);
//
//        try {
//            mManager = BleManager.getInstance(this);
//            mManager.registerBleListener(mLisenter);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        initView();
//    }
//
//    public void scan(View view){
////        mManager.startScan(new BleScanLisenter<BleDevice>() {
////            @Override
////            public void onLeScan(BleDevice device, int rssi, byte[] scanRecord) {
////
////            }
////        });
//    }
//
//    private <T> T getDevice(T device){
//        return device;
//    }
//
//    private void initView() {
//        mLeDeviceListAdapter = new LeDeviceListAdapter(this);
//        mListView.setAdapter(mLeDeviceListAdapter);
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                final BleDevice device = mLeDeviceListAdapter.getDevice(position);
//                if (device == null) return;
//                if (mManager.isScanning()) {
//                    mManager.scanLeDevice(false);
//                }
//                if (device.isConnected()) {
//                    mManager.disconnect(device);
//                } else {
//                    mManager.connect(device);
//                }
//            }
//        });
//
//    }
//
//
//    private BleLisenter mLisenter = new BleLisenter() {
//        @Override
//        public void onStart() {
//            super.onStart();
//            //可以选择性实现该方法   不需要则不用实现
//        }
//
//        @Override
//        public void onStop() {
//            super.onStop();
//            //可以选择性实现该方法   不需要则不用实现
//            invalidateOptionsMenu();
//        }
//
//        @Override
//        public void onConnectException(final BleDevice device, final int errorCode) {
//            super.onConnectException(device,errorCode);
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    Log.i(TAG, "errorCode: "+errorCode+"--------蓝牙名称："+device.getmBleName());
//                    Toast.makeText(getApplication(), errorCode+"", Toast.LENGTH_SHORT).show();
//                    synchronized (mManager.getLocker()) {
//                        mLeDeviceListAdapter.notifyDataSetChanged();
//                    }
//                }
//            });
//        }
//
//        @Override
//        public void onLeScan(final BleDevice device, int rssi, byte[] scanRecord) {
//            Logger.e("onLeScan");
////                            //可以选择性的根据scanRecord蓝牙广播包进行过滤
////                            如下  此处注释（根据你们产品的广播进行过滤或者根据产品的特定name或者address进行过滤也可以）
////                            if(!BleConfig.matchProduct(scanRecord)){
////                                return;
////                            }
//            synchronized (mManager.getLocker()) {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mLeDeviceListAdapter.addDevice(device);
//                        mLeDeviceListAdapter.notifyDataSetChanged();
//                    }
//                });
//            }
//        }
//
//        @Override
//        public void onConnectionChanged(final BleDevice device) {
//            Logger.e("onConnectionChanged" + device.getConnectionState() + device.isConnected());
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    for (int i = 0; i < mLeDeviceListAdapter.getCount(); i++) {
//                        if (device.getBleAddress().equals(mLeDeviceListAdapter.getDevice(i).getBleAddress())) {
//                            if (device.isConnected()) {
//                                mLeDeviceListAdapter.getDevice(i).setConnectionState(BleStates.BleStatus.CONNECTED);
//                            } else if (device.isConnectting()) {
//                            } else {
//                                Toast.makeText(TestActivity.this, R.string.line_disconnect, Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    }
//                    synchronized (mManager.getLocker()) {
//                        mLeDeviceListAdapter.notifyDataSetChanged();
//                    }
//                }
//            });
//        }
//
//        @Override
//        public void onServicesDiscovered(BluetoothGatt gatt) {
//            super.onServicesDiscovered(gatt);
//            //可以选择性实现该方法   不需要则不用实现  库中已设置Notify
//        }
//
//        @Override
//        public void onChanged(BluetoothGattCharacteristic characteristic) {
//            Logger.e("data===" + Arrays.toString(characteristic.getValue()));
//            //可以选择性实现该方法   不需要则不用实现
//            //硬件mcu 返回数据
//        }
//
//        @Override
//        public void onWrite(BluetoothGatt gatt) {
//            //可以选择性实现该方法   不需要则不用实现
//        }
//
//        @Override
//        public void onRead(BleDevice device) {
//            super.onRead(device);
//            //可以选择性实现该方法   不需要则不用实现
//            Logger.e("onRead");
//        }
//
//        @Override
//        public void onDescriptorWriter(BluetoothGatt gatt) {
//            super.onDescriptorWriter(gatt);
//            //可以选择性实现该方法   不需要则不用实现
//        }
//    };
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (mManager != null) {
//            mManager.unService();
//            mManager.unRegisterBleListener(mLisenter);
//        }
//    }
//}
