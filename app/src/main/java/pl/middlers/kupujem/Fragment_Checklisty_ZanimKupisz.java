package pl.middlers.kupujem;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class Fragment_Checklisty_ZanimKupisz extends Fragment {


    CheckBox checkBoxTAB01, checkBoxTAB02, checkBoxTAB03, checkBoxTAB04, checkBoxTAB05, checkBoxTAB06, checkBoxTAB07, checkBoxTAB08, checkBoxTAB09;
    TextView mNapisTAB01, mNapisTAB02, mNapisTAB03, mNapisTAB04, mNapisTAB05, mNapisTAB06, mNapisTAB07, mNapisTAB08, mNapisTAB09;

    public Fragment_Checklisty_ZanimKupisz() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_checklisty_zanim_kupisz, container, false);
        // Inflate the layout for this fragment
        checkBoxTAB01 = (CheckBox) myView.findViewById(R.id.checkboxTAB01);
        mNapisTAB01 = (TextView) myView.findViewById(R.id.napisTAB01);

        checkBoxTAB02 = (CheckBox) myView.findViewById(R.id.checkboxTAB02);
        mNapisTAB02 = (TextView) myView.findViewById(R.id.napisTAB02);

        checkBoxTAB03 = (CheckBox) myView.findViewById(R.id.checkboxTAB03);
        mNapisTAB03 = (TextView) myView.findViewById(R.id.napisTAB03);

        checkBoxTAB04 = (CheckBox) myView.findViewById(R.id.checkboxTAB04);
        mNapisTAB04 = (TextView) myView.findViewById(R.id.napisTAB04);

        checkBoxTAB05 = (CheckBox) myView.findViewById(R.id.checkboxTAB05);
        mNapisTAB05 = (TextView) myView.findViewById(R.id.napisTAB05);

        checkBoxTAB06 = (CheckBox) myView.findViewById(R.id.checkboxTAB06);
        mNapisTAB06 = (TextView) myView.findViewById(R.id.napisTAB06);

        checkBoxTAB07 = (CheckBox) myView.findViewById(R.id.checkboxTAB07);
        mNapisTAB07 = (TextView) myView.findViewById(R.id.napisTAB07);

        checkBoxTAB08 = (CheckBox) myView.findViewById(R.id.checkboxTAB08);
        mNapisTAB08 = (TextView) myView.findViewById(R.id.napisTAB08);

        checkBoxTAB09 = (CheckBox) myView.findViewById(R.id.checkboxTAB09);
        mNapisTAB09 = (TextView) myView.findViewById(R.id.napisTAB09);


        loadPrefs();

        return myView;

    }

    private void loadPrefs() {


        SharedPreferences preferencesTAB01 = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final SharedPreferences.Editor editorTAB01 = preferencesTAB01.edit();
        if (preferencesTAB01.contains("checkedTAB01") && preferencesTAB01.getBoolean("checkedTAB01", false) == true) {
            checkBoxTAB01.setChecked(true);
            mNapisTAB01.setTextColor(Color.parseColor("#E0E0E0"));
            mNapisTAB01.setPaintFlags(mNapisTAB01.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            checkBoxTAB01.setChecked(false);
            mNapisTAB01.setTextColor(Color.parseColor("#000000"));
            mNapisTAB01.setPaintFlags(mNapisTAB01.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
        }
        checkBoxTAB01.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBoxTAB01.isChecked()) {
                    editorTAB01.putBoolean("checkedTAB01", true);
                    mNapisTAB01.setTextColor(Color.parseColor("#E0E0E0"));
                    mNapisTAB01.setPaintFlags(mNapisTAB01.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    editorTAB01.apply();
                } else {
                    editorTAB01.putBoolean("checkedTAB01", false);
                    mNapisTAB01.setTextColor(Color.parseColor("#000000"));
                    mNapisTAB01.setPaintFlags(mNapisTAB01.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                    editorTAB01.apply();
                }
            }
        });


        SharedPreferences preferencesTAB02 = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final SharedPreferences.Editor editorTAB02 = preferencesTAB01.edit();
        if (preferencesTAB02.contains("checkedTAB02") && preferencesTAB02.getBoolean("checkedTAB02", false) == true) {
            checkBoxTAB02.setChecked(true);
            mNapisTAB02.setTextColor(Color.parseColor("#E0E0E0"));
            mNapisTAB02.setPaintFlags(mNapisTAB02.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            checkBoxTAB02.setChecked(false);
            mNapisTAB02.setTextColor(Color.parseColor("#000000"));
            mNapisTAB02.setPaintFlags(mNapisTAB02.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
        }
        checkBoxTAB02.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBoxTAB02.isChecked()) {
                    editorTAB02.putBoolean("checkedTAB02", true);
                    mNapisTAB02.setTextColor(Color.parseColor("#E0E0E0"));
                    mNapisTAB02.setPaintFlags(mNapisTAB02.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    editorTAB02.apply();
                } else {
                    editorTAB02.putBoolean("checkedTAB02", false);
                    mNapisTAB02.setTextColor(Color.parseColor("#000000"));
                    mNapisTAB02.setPaintFlags(mNapisTAB02.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                    editorTAB02.apply();
                }
            }
        });

        SharedPreferences preferencesTAB03 = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final SharedPreferences.Editor editorTAB03 = preferencesTAB01.edit();
        if (preferencesTAB03.contains("checkedTAB03") && preferencesTAB03.getBoolean("checkedTAB03", false) == true) {
            checkBoxTAB03.setChecked(true);
            mNapisTAB03.setTextColor(Color.parseColor("#E0E0E0"));
            mNapisTAB03.setPaintFlags(mNapisTAB03.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            checkBoxTAB03.setChecked(false);
            mNapisTAB03.setTextColor(Color.parseColor("#000000"));
            mNapisTAB03.setPaintFlags(mNapisTAB03.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
        }
        checkBoxTAB03.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBoxTAB03.isChecked()) {
                    editorTAB03.putBoolean("checkedTAB03", true);
                    mNapisTAB03.setTextColor(Color.parseColor("#E0E0E0"));
                    mNapisTAB03.setPaintFlags(mNapisTAB03.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    editorTAB03.apply();
                } else {
                    editorTAB03.putBoolean("checkedTAB03", false);
                    mNapisTAB03.setTextColor(Color.parseColor("#000000"));
                    mNapisTAB03.setPaintFlags(mNapisTAB03.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                    editorTAB03.apply();
                }
            }
        });

        SharedPreferences preferencesTAB04 = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final SharedPreferences.Editor editorTAB04 = preferencesTAB01.edit();
        if (preferencesTAB04.contains("checkedTAB04") && preferencesTAB04.getBoolean("checkedTAB04", false) == true) {
            checkBoxTAB04.setChecked(true);
            mNapisTAB04.setTextColor(Color.parseColor("#E0E0E0"));
            mNapisTAB04.setPaintFlags(mNapisTAB04.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            checkBoxTAB04.setChecked(false);
            mNapisTAB04.setTextColor(Color.parseColor("#000000"));
            mNapisTAB04.setPaintFlags(mNapisTAB04.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
        }
        checkBoxTAB04.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBoxTAB04.isChecked()) {
                    editorTAB04.putBoolean("checkedTAB04", true);
                    mNapisTAB04.setTextColor(Color.parseColor("#E0E0E0"));
                    mNapisTAB04.setPaintFlags(mNapisTAB04.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    editorTAB04.apply();
                } else {
                    editorTAB04.putBoolean("checkedTAB04", false);
                    mNapisTAB04.setTextColor(Color.parseColor("#000000"));
                    mNapisTAB04.setPaintFlags(mNapisTAB04.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                    editorTAB04.apply();
                }
            }
        });


        SharedPreferences preferencesTAB05 = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final SharedPreferences.Editor editorTAB05 = preferencesTAB01.edit();
        if (preferencesTAB05.contains("checkedTAB05") && preferencesTAB05.getBoolean("checkedTAB05", false) == true) {
            checkBoxTAB05.setChecked(true);
            mNapisTAB05.setTextColor(Color.parseColor("#E0E0E0"));
            mNapisTAB05.setPaintFlags(mNapisTAB05.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            checkBoxTAB05.setChecked(false);
            mNapisTAB05.setTextColor(Color.parseColor("#000000"));
            mNapisTAB05.setPaintFlags(mNapisTAB05.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
        }
        checkBoxTAB05.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBoxTAB05.isChecked()) {
                    editorTAB05.putBoolean("checkedTAB05", true);
                    mNapisTAB05.setTextColor(Color.parseColor("#E0E0E0"));
                    mNapisTAB05.setPaintFlags(mNapisTAB05.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    editorTAB05.apply();
                } else {
                    editorTAB05.putBoolean("checkedTAB05", false);
                    mNapisTAB05.setTextColor(Color.parseColor("#000000"));
                    mNapisTAB05.setPaintFlags(mNapisTAB05.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                    editorTAB05.apply();
                }
            }
        });

        SharedPreferences preferencesTAB06 = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final SharedPreferences.Editor editorTAB06 = preferencesTAB01.edit();
        if (preferencesTAB06.contains("checkedTAB06") && preferencesTAB06.getBoolean("checkedTAB06", false) == true) {
            checkBoxTAB06.setChecked(true);
            mNapisTAB06.setTextColor(Color.parseColor("#E0E0E0"));
            mNapisTAB06.setPaintFlags(mNapisTAB06.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            checkBoxTAB06.setChecked(false);
            mNapisTAB06.setTextColor(Color.parseColor("#000000"));
            mNapisTAB06.setPaintFlags(mNapisTAB06.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
        }
        checkBoxTAB06.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBoxTAB06.isChecked()) {
                    editorTAB06.putBoolean("checkedTAB06", true);
                    mNapisTAB06.setTextColor(Color.parseColor("#E0E0E0"));
                    mNapisTAB06.setPaintFlags(mNapisTAB06.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    editorTAB06.apply();
                } else {
                    editorTAB06.putBoolean("checkedTAB06", false);
                    mNapisTAB06.setTextColor(Color.parseColor("#000000"));
                    mNapisTAB06.setPaintFlags(mNapisTAB06.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                    editorTAB06.apply();
                }
            }
        });

        SharedPreferences preferencesTAB07 = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final SharedPreferences.Editor editorTAB07 = preferencesTAB01.edit();
        if (preferencesTAB07.contains("checkedTAB07") && preferencesTAB07.getBoolean("checkedTAB07", false) == true) {
            checkBoxTAB07.setChecked(true);
            mNapisTAB07.setTextColor(Color.parseColor("#E0E0E0"));
            mNapisTAB07.setPaintFlags(mNapisTAB07.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            checkBoxTAB07.setChecked(false);
            mNapisTAB07.setTextColor(Color.parseColor("#000000"));
            mNapisTAB07.setPaintFlags(mNapisTAB07.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
        }
        checkBoxTAB07.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBoxTAB07.isChecked()) {
                    editorTAB07.putBoolean("checkedTAB07", true);
                    mNapisTAB07.setTextColor(Color.parseColor("#E0E0E0"));
                    mNapisTAB07.setPaintFlags(mNapisTAB07.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    editorTAB07.apply();
                } else {
                    editorTAB07.putBoolean("checkedTAB07", false);
                    mNapisTAB07.setTextColor(Color.parseColor("#000000"));
                    mNapisTAB07.setPaintFlags(mNapisTAB07.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                    editorTAB07.apply();
                }
            }
        });

        SharedPreferences preferencesTAB08 = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final SharedPreferences.Editor editorTAB08 = preferencesTAB01.edit();
        if (preferencesTAB08.contains("checkedTAB08") && preferencesTAB08.getBoolean("checkedTAB08", false) == true) {
            checkBoxTAB08.setChecked(true);
            mNapisTAB08.setTextColor(Color.parseColor("#E0E0E0"));
            mNapisTAB08.setPaintFlags(mNapisTAB08.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            checkBoxTAB08.setChecked(false);
            mNapisTAB08.setTextColor(Color.parseColor("#000000"));
            mNapisTAB08.setPaintFlags(mNapisTAB08.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
        }
        checkBoxTAB08.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBoxTAB08.isChecked()) {
                    editorTAB08.putBoolean("checkedTAB08", true);
                    mNapisTAB08.setTextColor(Color.parseColor("#E0E0E0"));
                    mNapisTAB08.setPaintFlags(mNapisTAB08.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    editorTAB08.apply();
                } else {
                    editorTAB08.putBoolean("checkedTAB08", false);
                    mNapisTAB08.setTextColor(Color.parseColor("#000000"));
                    mNapisTAB08.setPaintFlags(mNapisTAB08.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                    editorTAB08.apply();
                }
            }
        });

        SharedPreferences preferencesTAB09 = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final SharedPreferences.Editor editorTAB09 = preferencesTAB01.edit();
        if (preferencesTAB09.contains("checkedTAB09") && preferencesTAB09.getBoolean("checkedTAB09", false) == true) {
            checkBoxTAB09.setChecked(true);
            mNapisTAB09.setTextColor(Color.parseColor("#E0E0E0"));
            mNapisTAB09.setPaintFlags(mNapisTAB09.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            checkBoxTAB09.setChecked(false);
            mNapisTAB09.setTextColor(Color.parseColor("#000000"));
            mNapisTAB09.setPaintFlags(mNapisTAB09.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
        }
        checkBoxTAB09.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBoxTAB09.isChecked()) {
                    editorTAB09.putBoolean("checkedTAB09", true);
                    mNapisTAB09.setTextColor(Color.parseColor("#E0E0E0"));
                    mNapisTAB09.setPaintFlags(mNapisTAB09.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    editorTAB09.apply();
                } else {
                    editorTAB09.putBoolean("checkedTAB09", false);
                    mNapisTAB09.setTextColor(Color.parseColor("#000000"));
                    mNapisTAB09.setPaintFlags(mNapisTAB09.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                    editorTAB09.apply();
                }
            }
        });

















    }


}