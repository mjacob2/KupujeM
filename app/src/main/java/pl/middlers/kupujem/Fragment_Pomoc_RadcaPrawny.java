package pl.middlers.kupujem;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.middlers.kupujem.R;


public class Fragment_Pomoc_RadcaPrawny extends Fragment {


    public Fragment_Pomoc_RadcaPrawny() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pomoc_radca_prawny, container, false);
    }

}