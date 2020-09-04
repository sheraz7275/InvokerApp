package com.example.invokerapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ornach.nobobutton.NoboButton;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity2 extends AppCompatActivity {



    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rlWIFI)
    RelativeLayout rlWIFI;
    @BindView(R.id.rlRinging)
    RelativeLayout rlRinging;
    @BindView(R.id.rlFlash)
    RelativeLayout rlFlash;
    @BindView(R.id.rlBluetooth)
    RelativeLayout rlBluetooth;
    @BindView(R.id.btnOnWIFI)
    NoboButton btnOnWIFI;
    @BindView(R.id.btnOfWIFI)
    NoboButton btnOfWIFI;
    @BindView(R.id.btnOnRing)
    NoboButton btnOnRing;
    @BindView(R.id.btnOfRing)
    NoboButton btnOfRing;
    @BindView(R.id.btnOnFlash)
    NoboButton btnOnFlash;
    @BindView(R.id.btnOfFlash)
    NoboButton btnOfFlash;
    @BindView(R.id.btnOnBluetooth)
    NoboButton btnOnBluetooth;
    @BindView(R.id.btnOfBluetooth)
    NoboButton btnOfBluetooth;



    FirebaseDatabase database;
    DatabaseReference mRefWIFI, mRefRing, mRefFlash, mRefBluetooth;
    DatabaseReference acK_Wifi, ack_Flash, ack_Bluetooth, ack_Ringing;
    DatabaseReference mAckSection,mRoot;
    int statusWIFI,statusRing,statusFlash,statusBluetooth;
    int fireBit=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);


        statusBluetooth=10;statusFlash=10;statusRing=10;statusWIFI=10;
        referenceInitialization();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


        wifiListener();
        ringListener();
        bluetoothListener();
        flashListener();









    }



    void referenceInitialization() {


        database = FirebaseDatabase.getInstance();

        mAckSection = database.getReference("AckSection");
        mRoot = database.getReference("invoker");
        mRefWIFI = mRoot.child("wifi");
        mRefRing = mRoot.child("ringing");
        mRefFlash = mRoot.child("flash");
        mRefBluetooth = mRoot.child("bluetooth");






        acK_Wifi = mAckSection.getRef().child("wifi");
        ack_Flash = mAckSection.getRef().child("flash");
        ack_Bluetooth = mAckSection.getRef().child("bluetooth");
        ack_Ringing = mAckSection.getRef().child("ringing");


    }


    void wifiListener() {
        acK_Wifi.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("", "Value is: " + value);

                fireBit=10;


                assert value != null;
                statusWIFI=Integer.parseInt(value);

                if (statusWIFI == 1) {

                    setBackGround(rlWIFI,true);



                }
                if (statusWIFI == 0) {

                    setBackGround(rlWIFI,false);



                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("", "Failed to read value.", error.toException());
            }
        });


    }

    void ringListener() {

        ack_Ringing.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("", "Value is: " + value);
                fireBit=10;


                statusRing=Integer.valueOf(value);
                if (statusRing == 1) {


                    setBackGround(rlRinging,true);



                }
                if (statusRing == 0) {

                    setBackGround(rlRinging,false);


                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("", "Failed to read value.", error.toException());
            }
        });

    }

    void bluetoothListener() {

        ack_Bluetooth.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("", "Value is: " + value);

                fireBit=10;


                statusBluetooth=Integer.valueOf(value);
                if (statusBluetooth == 1) {

                    setBackGround(rlBluetooth,true);



                }
                if (statusBluetooth == 0) {

                    setBackGround(rlBluetooth,false);


                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("", "Failed to read value.", error.toException());
            }
        });
    }

    void flashListener() {

        ack_Flash.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("", "Value is: " + value);

                fireBit=10;


                statusFlash=Integer.valueOf(value);
                if (statusFlash == 1) {

                    setBackGround(rlFlash,true);


                }
                if (statusFlash == 0) {
                    setBackGround(rlFlash,false);


                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("", "Failed to read value.", error.toException());
            }
        });
    }




    void setDataBase(DatabaseReference ref, String value) {

        ref.setValue(value);
    }

    @OnClick(R.id.btnOnWIFI)
    public void onBtnOnWIFIClicked() {
        if(statusWIFI==0){
            setDataBase(mRefWIFI,"1");

            btnOnWIFI.setEnabled(false);

            setHandler(mRefWIFI,1,btnOnWIFI);

        }else{

            Toast.makeText(this, "Already Enabled", Toast.LENGTH_SHORT).show();

        }





    }

    @OnClick(R.id.btnOfWIFI)
    public void onBtnOfWIFIClicked() {

        if(statusWIFI==1){
            setDataBase(mRefWIFI,"0");

            btnOfWIFI.setEnabled(false);

            setHandler(mRefWIFI,1,btnOfWIFI);
        }else {

            Toast.makeText(this, "Already Disabled", Toast.LENGTH_SHORT).show();

        }


    }

    @OnClick(R.id.btnOnRing)
    public void onBtnOnRingClicked() {

        if(statusRing==0){
            setDataBase(mRefRing,"1");

            btnOnRing.setEnabled(false);

            setHandler(mRefRing,1,btnOnRing);

        }else {

            Toast.makeText(this, "Already Enabled", Toast.LENGTH_SHORT).show();

        }





    }

    @OnClick(R.id.btnOfRing)
    public void onBtnOfRingClicked() {

        if(statusRing==1){

            setDataBase(mRefRing,"0");

            btnOfRing.setEnabled(false);

            setHandler(mRefRing,0,btnOfRing);
        }else {

            Toast.makeText(this, "Already Disabled", Toast.LENGTH_SHORT).show();

        }




    }

    @OnClick(R.id.btnOnFlash)
    public void onBtnOnFlashClicked() {

        if(statusFlash==0){

            setDataBase(mRefFlash,"1");

            btnOnFlash.setEnabled(false);

            setHandler(mRefFlash,1,btnOnFlash);


        }else {

            Toast.makeText(this, "Already Enabled", Toast.LENGTH_SHORT).show();


        }

    }

    @OnClick(R.id.btnOfFlash)
    public void onBtnOfFlashClicked() {

        if(statusFlash==1){

            setDataBase(mRefFlash,"0");

            btnOfFlash.setEnabled(false);

            setHandler(mRefFlash,0,btnOfFlash);
        }else{

            Toast.makeText(this, "Already Disabled", Toast.LENGTH_SHORT).show();

        }



    }

    @OnClick(R.id.btnOnBluetooth)
    public void onBtnOnBluetoothClicked() {


        if(statusBluetooth==0){
            setDataBase(mRefBluetooth,"1");

            btnOnBluetooth.setEnabled(false);

            setHandler(mRefBluetooth,1,btnOnBluetooth);

        }else{

            Toast.makeText(this, "Already Enabled", Toast.LENGTH_SHORT).show();

        }



    }

    @OnClick(R.id.btnOfBluetooth)
    public void onBtnOfBluetoothClicked() {


        if(statusBluetooth==1){

            setDataBase(mRefBluetooth,"0");


            btnOfBluetooth.setEnabled(false);

            setHandler(mRefBluetooth,0,btnOfBluetooth);
        }else {

            Toast.makeText(this, "Already Disabled", Toast.LENGTH_SHORT).show();

        }



    }



    void setBackGround(RelativeLayout relativeLayout,Boolean status) {


        if(status){
            relativeLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.circlegray));

        }
        else{
            relativeLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.circle));


        }

    }


    void setHandler(DatabaseReference reference,int val,NoboButton btn){

        fireBit=0;

        Handler hnd = new Handler();
        hnd.postDelayed(new Runnable() {
            @Override
            public void run() {


                if(fireBit==10){


                    fireBit=0;
                    btn.setEnabled(true);



                }else {


                    fireBit=0;
                    btn.setEnabled(true);
                    Toast.makeText(MainActivity2.this, "No Response", Toast.LENGTH_SHORT).show();


                    if(val==1){
                        setDataBase(reference,"0");
                    }else {
                        setDataBase(reference,"1");

                    }



                }


            }
        }, 3000);


    }



}