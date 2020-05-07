package pl.middlers.kupujem;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import pl.middlers.kupujem.R;


public class Fragment_Checklisty_Odbierz extends Fragment {

    CheckBox checkBoxTABodbierz01, checkBoxTABodbierz02, checkBoxTABodbierz03, checkBoxTABodbierz04, checkBoxTABodbierz05, checkBoxTABodbierz06, checkBoxTABodbierz07, checkBoxTABodbierz08, checkBoxTABodbierz09, checkBoxTABodbierz10, checkBoxTABodbierz11;
    TextView mNapisTABodbierz01, mNapisTABodbierz02, mNapisTABodbierz03, mNapisTABodbierz04, mNapisTABodbierz05, mNapisTABodbierz06, mNapisTABodbierz07, mNapisTABodbierz08, mNapisTABodbierz09, mNapisTABodbierz10, mNapisTABodbierz11;

    public Fragment_Checklisty_Odbierz() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView2 = inflater.inflate(R.layout.fragment_checklisty_odbierz, container, false);
        // Inflate the layout for this fragment
        checkBoxTABodbierz01 = (CheckBox) myView2.findViewById(R.id.checkboxTABodbierz01);
        mNapisTABodbierz01 = (TextView) myView2.findViewById(R.id.napisTABodbierz01);

        checkBoxTABodbierz02 = (CheckBox) myView2.findViewById(R.id.checkboxTABodbierz02);
        mNapisTABodbierz02 = (TextView) myView2.findViewById(R.id.napisTABodbierz02);

        checkBoxTABodbierz03 = (CheckBox) myView2.findViewById(R.id.checkboxTABodbierz03);
        mNapisTABodbierz03 = (TextView) myView2.findViewById(R.id.napisTABodbierz03);

        checkBoxTABodbierz04 = (CheckBox) myView2.findViewById(R.id.checkboxTABodbierz04);
        mNapisTABodbierz04 = (TextView) myView2.findViewById(R.id.napisTABodbierz04);

        checkBoxTABodbierz05 = (CheckBox) myView2.findViewById(R.id.checkboxTABodbierz05);
        mNapisTABodbierz05 = (TextView) myView2.findViewById(R.id.napisTABodbierz05);

        checkBoxTABodbierz06 = (CheckBox) myView2.findViewById(R.id.checkboxTABodbierz06);
        mNapisTABodbierz06 = (TextView) myView2.findViewById(R.id.napisTABodbierz06);

        checkBoxTABodbierz07 = (CheckBox) myView2.findViewById(R.id.checkboxTABodbierz07);
        mNapisTABodbierz07 = (TextView) myView2.findViewById(R.id.napisTABodbierz07);

        checkBoxTABodbierz08 = (CheckBox) myView2.findViewById(R.id.checkboxTABodbierz08);
        mNapisTABodbierz08 = (TextView) myView2.findViewById(R.id.napisTABodbierz08);

        checkBoxTABodbierz09 = (CheckBox) myView2.findViewById(R.id.checkboxTABodbierz09);
        mNapisTABodbierz09 = (TextView) myView2.findViewById(R.id.napisTABodbierz09);

        checkBoxTABodbierz10 = (CheckBox) myView2.findViewById(R.id.checkboxTABodbierz10);
        mNapisTABodbierz10 = (TextView) myView2.findViewById(R.id.napisTABodbierz10);

        checkBoxTABodbierz11 = (CheckBox) myView2.findViewById(R.id.checkboxTABodbierz11);
        mNapisTABodbierz11 = (TextView) myView2.findViewById(R.id.napisTABodbierz11);





        loadPrefs2();

        return myView2;
    }

    private void loadPrefs2() {

        SharedPreferences preferencesTABodbierz01 = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final SharedPreferences.Editor editorTABodbierz01 = preferencesTABodbierz01.edit();
        if (preferencesTABodbierz01.contains("checkedTABodbierz01") && preferencesTABodbierz01.getBoolean("checkedTABodbierz01", false) == true) {
            checkBoxTABodbierz01.setChecked(true);
            mNapisTABodbierz01.setTextColor(Color.parseColor("#E0E0E0"));
            mNapisTABodbierz01.setPaintFlags(mNapisTABodbierz01.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            checkBoxTABodbierz01.setChecked(false);
            mNapisTABodbierz01.setTextColor(Color.parseColor("#000000"));
            mNapisTABodbierz01.setPaintFlags(mNapisTABodbierz01.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
        }
        checkBoxTABodbierz01.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBoxTABodbierz01.isChecked()) {
                    editorTABodbierz01.putBoolean("checkedTABodbierz01", true);
                    mNapisTABodbierz01.setTextColor(Color.parseColor("#E0E0E0"));
                    mNapisTABodbierz01.setPaintFlags(mNapisTABodbierz01.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    editorTABodbierz01.apply();
                } else {
                    editorTABodbierz01.putBoolean("checkedTABodbierz01", false);
                    mNapisTABodbierz01.setTextColor(Color.parseColor("#000000"));
                    mNapisTABodbierz01.setPaintFlags(mNapisTABodbierz01.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                    editorTABodbierz01.apply();
                }
            }
        });


        SharedPreferences preferencesTABodbierz02 = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final SharedPreferences.Editor editorTABodbierz02 = preferencesTABodbierz02.edit();
        if (preferencesTABodbierz02.contains("checkedTABodbierz02") && preferencesTABodbierz02.getBoolean("checkedTABodbierz02", false) == true) {
            checkBoxTABodbierz02.setChecked(true);
            mNapisTABodbierz02.setTextColor(Color.parseColor("#E0E0E0"));
            mNapisTABodbierz02.setPaintFlags(mNapisTABodbierz02.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            checkBoxTABodbierz02.setChecked(false);
            mNapisTABodbierz02.setTextColor(Color.parseColor("#000000"));
            mNapisTABodbierz02.setPaintFlags(mNapisTABodbierz02.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
        }
        checkBoxTABodbierz02.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBoxTABodbierz02.isChecked()) {
                    editorTABodbierz02.putBoolean("checkedTABodbierz02", true);
                    mNapisTABodbierz02.setTextColor(Color.parseColor("#E0E0E0"));
                    mNapisTABodbierz02.setPaintFlags(mNapisTABodbierz02.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    editorTABodbierz02.apply();
                } else {
                    editorTABodbierz02.putBoolean("checkedTABodbierz02", false);
                    mNapisTABodbierz02.setTextColor(Color.parseColor("#000000"));
                    mNapisTABodbierz02.setPaintFlags(mNapisTABodbierz02.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                    editorTABodbierz02.apply();
                }
            }
        });



        SharedPreferences preferencesTABodbierz03 = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final SharedPreferences.Editor editorTABodbierz03 = preferencesTABodbierz03.edit();
        if (preferencesTABodbierz03.contains("checkedTABodbierz03") && preferencesTABodbierz03.getBoolean("checkedTABodbierz03", false) == true) {
            checkBoxTABodbierz03.setChecked(true);
            mNapisTABodbierz03.setTextColor(Color.parseColor("#E0E0E0"));
            mNapisTABodbierz03.setPaintFlags(mNapisTABodbierz03.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            checkBoxTABodbierz03.setChecked(false);
            mNapisTABodbierz03.setTextColor(Color.parseColor("#000000"));
            mNapisTABodbierz03.setPaintFlags(mNapisTABodbierz03.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
        }
        checkBoxTABodbierz03.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBoxTABodbierz03.isChecked()) {
                    editorTABodbierz03.putBoolean("checkedTABodbierz03", true);
                    mNapisTABodbierz03.setTextColor(Color.parseColor("#E0E0E0"));
                    mNapisTABodbierz03.setPaintFlags(mNapisTABodbierz03.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    editorTABodbierz03.apply();
                } else {
                    editorTABodbierz03.putBoolean("checkedTABodbierz03", false);
                    mNapisTABodbierz03.setTextColor(Color.parseColor("#000000"));
                    mNapisTABodbierz03.setPaintFlags(mNapisTABodbierz03.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                    editorTABodbierz03.apply();
                }
            }
        });


        SharedPreferences preferencesTABodbierz04 = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final SharedPreferences.Editor editorTABodbierz04 = preferencesTABodbierz04.edit();
        if (preferencesTABodbierz04.contains("checkedTABodbierz04") && preferencesTABodbierz04.getBoolean("checkedTABodbierz04", false) == true) {
            checkBoxTABodbierz04.setChecked(true);
            mNapisTABodbierz04.setTextColor(Color.parseColor("#E0E0E0"));
            mNapisTABodbierz04.setPaintFlags(mNapisTABodbierz04.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            checkBoxTABodbierz04.setChecked(false);
            mNapisTABodbierz04.setTextColor(Color.parseColor("#000000"));
            mNapisTABodbierz04.setPaintFlags(mNapisTABodbierz04.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
        }
        checkBoxTABodbierz04.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBoxTABodbierz04.isChecked()) {
                    editorTABodbierz04.putBoolean("checkedTABodbierz04", true);
                    mNapisTABodbierz04.setTextColor(Color.parseColor("#E0E0E0"));
                    mNapisTABodbierz04.setPaintFlags(mNapisTABodbierz04.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    editorTABodbierz04.apply();
                } else {
                    editorTABodbierz04.putBoolean("checkedTABodbierz04", false);
                    mNapisTABodbierz04.setTextColor(Color.parseColor("#000000"));
                    mNapisTABodbierz04.setPaintFlags(mNapisTABodbierz04.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                    editorTABodbierz04.apply();
                }
            }
        });


        SharedPreferences preferencesTABodbierz05 = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final SharedPreferences.Editor editorTABodbierz05 = preferencesTABodbierz05.edit();
        if (preferencesTABodbierz05.contains("checkedTABodbierz05") && preferencesTABodbierz05.getBoolean("checkedTABodbierz05", false) == true) {
            checkBoxTABodbierz05.setChecked(true);
            mNapisTABodbierz05.setTextColor(Color.parseColor("#E0E0E0"));
            mNapisTABodbierz05.setPaintFlags(mNapisTABodbierz05.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            mNapisTABodbierz05.setPaintFlags(mNapisTABodbierz05.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
        } else {
            checkBoxTABodbierz05.setChecked(false);
            mNapisTABodbierz05.setTextColor(Color.parseColor("#000000"));
        }
        checkBoxTABodbierz05.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBoxTABodbierz05.isChecked()) {
                    editorTABodbierz05.putBoolean("checkedTABodbierz05", true);
                    mNapisTABodbierz05.setTextColor(Color.parseColor("#E0E0E0"));
                    mNapisTABodbierz05.setPaintFlags(mNapisTABodbierz05.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    editorTABodbierz05.apply();
                } else {
                    editorTABodbierz05.putBoolean("checkedTABodbierz05", false);
                    mNapisTABodbierz05.setTextColor(Color.parseColor("#000000"));
                    mNapisTABodbierz05.setPaintFlags(mNapisTABodbierz05.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                    editorTABodbierz05.apply();
                }
            }
        });



        SharedPreferences preferencesTABodbierz06 = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final SharedPreferences.Editor editorTABodbierz06 = preferencesTABodbierz06.edit();
        if (preferencesTABodbierz06.contains("checkedTABodbierz06") && preferencesTABodbierz06.getBoolean("checkedTABodbierz06", false) == true) {
            checkBoxTABodbierz06.setChecked(true);
            mNapisTABodbierz06.setTextColor(Color.parseColor("#E0E0E0"));
            mNapisTABodbierz06.setPaintFlags(mNapisTABodbierz06.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            checkBoxTABodbierz06.setChecked(false);
            mNapisTABodbierz06.setTextColor(Color.parseColor("#000000"));
            mNapisTABodbierz06.setPaintFlags(mNapisTABodbierz06.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
        }
        checkBoxTABodbierz06.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBoxTABodbierz06.isChecked()) {
                    editorTABodbierz06.putBoolean("checkedTABodbierz06", true);
                    mNapisTABodbierz06.setTextColor(Color.parseColor("#E0E0E0"));
                    mNapisTABodbierz06.setPaintFlags(mNapisTABodbierz06.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    editorTABodbierz06.apply();
                } else {
                    editorTABodbierz06.putBoolean("checkedTABodbierz06", false);
                    mNapisTABodbierz06.setTextColor(Color.parseColor("#000000"));
                    mNapisTABodbierz06.setPaintFlags(mNapisTABodbierz06.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                    editorTABodbierz06.apply();
                }
            }
        });


        SharedPreferences preferencesTABodbierz07 = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final SharedPreferences.Editor editorTABodbierz07 = preferencesTABodbierz07.edit();
        if (preferencesTABodbierz07.contains("checkedTABodbierz07") && preferencesTABodbierz07.getBoolean("checkedTABodbierz07", false) == true) {
            checkBoxTABodbierz07.setChecked(true);
            mNapisTABodbierz07.setTextColor(Color.parseColor("#E0E0E0"));
            mNapisTABodbierz07.setPaintFlags(mNapisTABodbierz07.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            checkBoxTABodbierz07.setChecked(false);
            mNapisTABodbierz07.setTextColor(Color.parseColor("#000000"));
            mNapisTABodbierz07.setPaintFlags(mNapisTABodbierz07.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
        }
        checkBoxTABodbierz07.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBoxTABodbierz07.isChecked()) {
                    editorTABodbierz07.putBoolean("checkedTABodbierz07", true);
                    mNapisTABodbierz07.setTextColor(Color.parseColor("#E0E0E0"));
                    mNapisTABodbierz07.setPaintFlags(mNapisTABodbierz07.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    editorTABodbierz07.apply();
                } else {
                    editorTABodbierz07.putBoolean("checkedTABodbierz07", false);
                    mNapisTABodbierz07.setTextColor(Color.parseColor("#000000"));
                    mNapisTABodbierz07.setPaintFlags(mNapisTABodbierz07.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                    editorTABodbierz07.apply();
                }
            }
        });


        SharedPreferences preferencesTABodbierz08 = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final SharedPreferences.Editor editorTABodbierz08 = preferencesTABodbierz08.edit();
        if (preferencesTABodbierz08.contains("checkedTABodbierz08") && preferencesTABodbierz08.getBoolean("checkedTABodbierz08", false) == true) {
            checkBoxTABodbierz08.setChecked(true);
            mNapisTABodbierz08.setTextColor(Color.parseColor("#E0E0E0"));
            mNapisTABodbierz08.setPaintFlags(mNapisTABodbierz08.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            checkBoxTABodbierz08.setChecked(false);
            mNapisTABodbierz08.setTextColor(Color.parseColor("#000000"));
            mNapisTABodbierz08.setPaintFlags(mNapisTABodbierz08.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
        }
        checkBoxTABodbierz08.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBoxTABodbierz08.isChecked()) {
                    editorTABodbierz08.putBoolean("checkedTABodbierz08", true);
                    mNapisTABodbierz08.setTextColor(Color.parseColor("#E0E0E0"));
                    mNapisTABodbierz08.setPaintFlags(mNapisTABodbierz08.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    editorTABodbierz08.apply();
                } else {
                    editorTABodbierz08.putBoolean("checkedTABodbierz08", false);
                    mNapisTABodbierz08.setTextColor(Color.parseColor("#000000"));
                    mNapisTABodbierz08.setPaintFlags(mNapisTABodbierz08.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                    editorTABodbierz08.apply();
                }
            }
        });



        SharedPreferences preferencesTABodbierz09 = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final SharedPreferences.Editor editorTABodbierz09 = preferencesTABodbierz09.edit();
        if (preferencesTABodbierz09.contains("checkedTABodbierz09") && preferencesTABodbierz09.getBoolean("checkedTABodbierz09", false) == true) {
            checkBoxTABodbierz09.setChecked(true);
            mNapisTABodbierz09.setTextColor(Color.parseColor("#E0E0E0"));
            mNapisTABodbierz09.setPaintFlags(mNapisTABodbierz09.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            checkBoxTABodbierz09.setChecked(false);
            mNapisTABodbierz09.setTextColor(Color.parseColor("#000000"));
            mNapisTABodbierz09.setPaintFlags(mNapisTABodbierz09.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
        }
        checkBoxTABodbierz09.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBoxTABodbierz09.isChecked()) {
                    editorTABodbierz09.putBoolean("checkedTABodbierz09", true);
                    mNapisTABodbierz09.setTextColor(Color.parseColor("#E0E0E0"));
                    mNapisTABodbierz09.setPaintFlags(mNapisTABodbierz09.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    editorTABodbierz09.apply();
                } else {
                    editorTABodbierz09.putBoolean("checkedTABodbierz09", false);
                    mNapisTABodbierz09.setTextColor(Color.parseColor("#000000"));
                    mNapisTABodbierz09.setPaintFlags(mNapisTABodbierz09.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                    editorTABodbierz09.apply();
                }
            }
        });


        SharedPreferences preferencesTABodbierz10 = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final SharedPreferences.Editor editorTABodbierz10 = preferencesTABodbierz10.edit();
        if (preferencesTABodbierz10.contains("checkedTABodbierz10") && preferencesTABodbierz10.getBoolean("checkedTABodbierz10", false) == true) {
            checkBoxTABodbierz10.setChecked(true);
            mNapisTABodbierz10.setTextColor(Color.parseColor("#E0E0E0"));
            mNapisTABodbierz10.setPaintFlags(mNapisTABodbierz10.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            checkBoxTABodbierz10.setChecked(false);
            mNapisTABodbierz10.setTextColor(Color.parseColor("#000000"));
            mNapisTABodbierz10.setPaintFlags(mNapisTABodbierz10.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
        }
        checkBoxTABodbierz10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBoxTABodbierz10.isChecked()) {
                    editorTABodbierz10.putBoolean("checkedTABodbierz10", true);
                    mNapisTABodbierz10.setTextColor(Color.parseColor("#E0E0E0"));
                    mNapisTABodbierz10.setPaintFlags(mNapisTABodbierz10.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    editorTABodbierz10.apply();
                } else {
                    editorTABodbierz10.putBoolean("checkedTABodbierz10", false);
                    mNapisTABodbierz10.setTextColor(Color.parseColor("#000000"));
                    mNapisTABodbierz10.setPaintFlags(mNapisTABodbierz10.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                    editorTABodbierz10.apply();
                }
            }
        });


        SharedPreferences preferencesTABodbierz11 = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final SharedPreferences.Editor editorTABodbierz11 = preferencesTABodbierz11.edit();
        if (preferencesTABodbierz11.contains("checkedTABodbierz11") && preferencesTABodbierz11.getBoolean("checkedTABodbierz11", false) == true) {
            checkBoxTABodbierz11.setChecked(true);
            mNapisTABodbierz11.setTextColor(Color.parseColor("#E0E0E0"));
            mNapisTABodbierz11.setPaintFlags(mNapisTABodbierz11.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            checkBoxTABodbierz11.setChecked(false);
            mNapisTABodbierz11.setTextColor(Color.parseColor("#000000"));
            mNapisTABodbierz11.setPaintFlags(mNapisTABodbierz11.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
        }
        checkBoxTABodbierz11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBoxTABodbierz11.isChecked()) {
                    editorTABodbierz11.putBoolean("checkedTABodbierz11", true);
                    mNapisTABodbierz11.setTextColor(Color.parseColor("#E0E0E0"));
                    mNapisTABodbierz11.setPaintFlags(mNapisTABodbierz11.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    editorTABodbierz11.apply();
                } else {
                    editorTABodbierz11.putBoolean("checkedTABodbierz11", false);
                    mNapisTABodbierz11.setTextColor(Color.parseColor("#000000"));
                    mNapisTABodbierz11.setPaintFlags(mNapisTABodbierz11.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                    editorTABodbierz11.apply();
                }
            }
        });





    }



    }

