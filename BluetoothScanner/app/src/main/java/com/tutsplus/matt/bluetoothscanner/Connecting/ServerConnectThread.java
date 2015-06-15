package com.tutsplus.matt.bluetoothscanner.Connecting;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by User on 6/5/2015.
 */
public class ServerConnectThread extends Thread{
    private BluetoothSocket bTSocket;

    public ServerConnectThread() { }

    public void acceptConnect(BluetoothAdapter bTAdapter, UUID mUUID) {
        BluetoothServerSocket temp = null;
        try {
            temp = bTAdapter.listenUsingRfcommWithServiceRecord("Service_Name", mUUID);
        } catch(IOException e) {
            Log.d("SERVERCONNECT", "Could not get a BluetoothServerSocket:" + e.toString());
        }
        while(true) {
            try {
                bTSocket = temp.accept();
            } catch (IOException e) {
                Log.d("SERVERCONNECT", "Could not accept an incoming connection.");
                break;
            }
            if (bTSocket != null) {
                try {
                    temp.close();
                } catch (IOException e) {
                    Log.d("SERVERCONNECT", "Could not close ServerSocket:" + e.toString());
                }
                break;
            }
        }
    }

    public void closeConnect() {
        try {
            bTSocket.close();
        } catch(IOException e) {
            Log.d("SERVERCONNECT", "Could not close connection:" + e.toString());
        }
    }
}
