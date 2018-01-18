package com.example.android.sunshine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by aab on 1/18/18.
 */

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastAdapterViewHolder> {

  // Within ForecastAdapter.java /////////////////////////////////////////////////////////////////
  // COMPLETE (15) Add a class file called ForecastAdapter
  // COMPLETE (22) Extend RecyclerView.Adapter<ForecastAdapter.ForecastAdapterViewHolder>

  // COMPLETE (23) Create a private string array called mWeatherData

  // COMPLETE (47) Create the default constructor (we will pass in parameters in a later lesson)
  private String[] mWeatherData;

  public ForecastAdapter(){

  }

//  public ForecastAdapter(String[] mWeatherData) {
//    this.mWeatherData = mWeatherData;
//  }

  // COMPLETE (16) Create a class within ForecastAdapter called ForecastAdapterViewHolder
  // COMPLETE (17) Extend RecyclerView.ViewHolder
  class ForecastAdapterViewHolder extends RecyclerView.ViewHolder{

    // Within ForecastAdapterViewHolder ///////////////////////////////////////////////////////////
    // COMPLETE (18) Create a public final TextView variable called mWeatherTextView
    // COMPLETE (19) Create a constructor for this class that accepts a View as a parameter
    // COMPLETE (20) Call super(view) within the constructor for ForecastAdapterViewHolder
    // COMPLETE (21) Using view.findViewById, get a reference to this layout's TextView and save it to mWeatherTextView
    // Within ForecastAdapterViewHolder ///////////////////////////////////////////////////////////
    public final TextView mWeatherTextView;

    public ForecastAdapterViewHolder(View itemView) {
      super(itemView);
      this.mWeatherTextView = itemView.findViewById(R.id.tv_weather_data);
    }

  }

  // COMPLETE (24) Override onCreateViewHolder
  // COMPLETE (25) Within onCreateViewHolder, inflate the list item xml into a view
  // COMPLETE (26) Within onCreateViewHolder, return a new ForecastAdapterViewHolder with the above view passed in as a parameter
  @Override
  public ForecastAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    Context context = parent.getContext();
    int layoutIdForListItem = R.layout.forecast_list_item;
    LayoutInflater inflater = LayoutInflater.from(context);
    boolean shouldAttachToParentImmediately = false;

    View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
    return new ForecastAdapterViewHolder(view);
  }

  // COMPLETE (27) Override onBindViewHolder
  // COMPLETE (28) Set the text of the TextView to the weather for this list item's position
  @Override
  public void onBindViewHolder(ForecastAdapterViewHolder holder, int position) {
    String weatherForThisDay = mWeatherData[position];
    holder.mWeatherTextView.setText(weatherForThisDay);
  }

  // COMPLETE (29) Override getItemCount
  // COMPLETE (30) Return 0 if mWeatherData is null, or the size of mWeatherData if it is not null
  @Override
  public int getItemCount() {
    if(null == mWeatherData){return 0;}
    return mWeatherData.length;
  }

  // COMPLETE (31) Create a setWeatherData method that saves the weatherData to mWeatherData
  // COMPLETE (32) After you save mWeatherData, call notifyDataSetChanged
  // Within ForecastAdapter.java /////////////////////////////////////////////////////////////////
  public void setmWeatherData(String[] weatherData){
    mWeatherData = weatherData;
    notifyDataSetChanged();
  }

}
