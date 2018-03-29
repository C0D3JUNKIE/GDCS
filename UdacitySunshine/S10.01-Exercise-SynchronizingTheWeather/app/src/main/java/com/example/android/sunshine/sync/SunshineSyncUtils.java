package com.example.android.sunshine.sync;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

public class SunshineSyncUtils{

// COMPLETE (9) Create a class called SunshineSyncUtils
  //  COMPLETE (10) Create a public static void method called startImmediateSync
  //  COMPLETE (11) Within that method, start the SunshineSyncIntentService

  public static void startImmediateSync(@NonNull final Context context){
    Intent intentToSyncImmediately = new Intent(context, SunshineSyncIntentService.class);
    context.startService(intentToSyncImmediately);
  }

}
