package pl.middlers.kupujem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


public class Fragment_Kalkulatory_Zdolnosc extends Fragment {


  //  public static     int mClickCount = 0;

    public Fragment_Kalkulatory_Zdolnosc() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //załaduj zapisane SharedPreferences
      //  loadPrefs();


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kalkulatory_zdolnosc, container, false);



    }

   // private void loadPrefs() {

   //     SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
   ////     SharedPreferences.Editor editor = sp.edit();
   //     editor.putInt("your_int_key", mClickCount);
   //     editor.commit();

  //  }



}