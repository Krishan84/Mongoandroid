package com.krishanpal.mongodbrestapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.design.widget.Snackbar;

import android.util.Log;

import com.krishanpal.mongodbrestapp.fragments.loginfrag;
import com.krishanpal.mongodbrestapp.fragments.reset_password_frag;


public class MainActivity extends AppCompatActivity implements reset_password_frag.Listener {
    public static final String TAG = MainActivity.class.getSimpleName();

    private loginfrag mLoginFragment;
    private reset_password_frag mResetPasswordDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {

            loadFragment();
        }


    }

    private void loadFragment(){

        if (mLoginFragment == null) {

            mLoginFragment = new loginfrag();
        }
        getFragmentManager().beginTransaction().replace(R.id.fragmentFrame,mLoginFragment,loginfrag.TAG).commit();
    }
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        String data = intent.getData().getLastPathSegment();
        Log.d(TAG, "onNewIntent: "+data);

        mResetPasswordDialog = (reset_password_frag) getFragmentManager().findFragmentByTag(reset_password_frag.TAG);

        if (mResetPasswordDialog != null)
            mResetPasswordDialog.setToken(data);
    }

    @Override
    public void onPasswordReset(String message) {

        showSnackBarMessage(message);
    }

    private void showSnackBarMessage(String message) {

        Snackbar.make(findViewById(R.id.activity_main),message,Snackbar.LENGTH_SHORT).show();

    }




