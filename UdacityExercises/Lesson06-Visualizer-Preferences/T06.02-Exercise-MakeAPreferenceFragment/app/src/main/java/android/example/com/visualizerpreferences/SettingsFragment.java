package android.example.com.visualizerpreferences;


import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

/**
 * Created by aab on 1/25/18.
 */

public class SettingsFragment extends PreferenceFragmentCompat {

  @Override
  public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
    addPreferencesFromResource(R.xml.perf_visualizer);
  }

  // COMPLETE (2) Create a class called SettingsFragment that extends PreferenceFragmentCompat
  // COMPLETE (3) In res->xml create a file called pref_visualizer
  // COMPLETE (4) In pref_visualizer create a preference screen containing a single check box preference
  // This check box preference should have a default value of true, the key 'show_bass', a
  // summaryOff of Hidden, a summaryOn of Shown and a title of 'Show Bass'

  // COMPLETE (5) In SettingsFragment's onCreatePreferences method add the preference file using the
  // addPreferencesFromResource method


  // COMPLETE (7) Set the root layout of activity_settings to our newly created SettingsFragment
  // and remove the padding.
  // [HINT] Use a <fragment> element in xml


}
