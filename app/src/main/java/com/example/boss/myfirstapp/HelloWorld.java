package com.example.boss.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public  class HelloWorld extends AppCompatActivity{

   CallbackManager callbackManager;

   private ProfileTracker mProfileTracker;

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.hello_world);
      ((TextView)findViewById(R.id.hello)).setText("venky");
      callbackManager = CallbackManager.Factory.create();

      final LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
      Toast.makeText(getApplicationContext(),"test",Toast.LENGTH_LONG).show();
//      loginButton.setText("venk");
//      loginButton.setFragment(this);
//      callbackManager.onActivityResult();
      ((TextView)findViewById(R.id.hello)).setText("venkyy");
      loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
         @Override
         public void onSuccess(LoginResult loginResult) {
            ((TextView)findViewById(R.id.hello)).setText("boss");

            if(Profile.getCurrentProfile() == null) {
               mProfileTracker = new ProfileTracker() {
                  @Override
                  protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                     Toast.makeText(getApplicationContext(),"pp"+currentProfile.getFirstName(),Toast.LENGTH_LONG).show();
                     mProfileTracker.stopTracking();
                  }
               };
               // no need to call startTracking() on mProfileTracker
               // because it is called by its constructor, internally.
            }
            else {
               Profile profile = Profile.getCurrentProfile();
               Toast.makeText(getApplicationContext(),"pp"+profile.getFirstName(),Toast.LENGTH_LONG).show();
            }
            Toast.makeText(getApplicationContext(),"Hello JavatPoint success",Toast.LENGTH_LONG).show();
         }

         @Override
         public void onCancel() {
            Toast.makeText(getApplicationContext(),"Hello JavatPoint on cacle",Toast.LENGTH_LONG).show();
         }

         @Override
         public void onError(FacebookException error) {
            Toast.makeText(getApplicationContext(),"Hello JavatPoint on error",Toast.LENGTH_LONG).show();
         }

      });
   }

   @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      super.onActivityResult(requestCode, resultCode, data);
      callbackManager.onActivityResult(requestCode,resultCode,data);
   }
}