package com.example.android.sunshine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.example.android.sunshine.R;

/**
 * Created by aab on 1/22/18.
 */

public class DetailActivity extends AppCompatActivity{

  private static final String FORECAST_SHARE_HASHTAG = "#SunshineApp";

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);
  }
}
