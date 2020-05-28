package pl.middlers.kupujem;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Tabs_Checklisty extends AppCompatActivity {

    private TextView mNapisX;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private CheckBox checkBoxX;

    //Context ctx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs_checklisty);

        toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Checklisty");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Pokoloruj navigation bar na biało
     //   if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      //      getWindow().setNavigationBarColor(getResources().getColor(R.color.fafafa));
      //  }

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }


//CHECKLISTA ZANIM KUPISZ --------------------------------------------------------------------------

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment_Checklisty_ZanimKupisz(), "sprawdź zanim kupisz");
        adapter.addFragment(new Fragment_Checklisty_Odbierz(), "odbierz M od dewelopera");
        //adapter.addFragment(new ThreeFragment(), "THREE");
        viewPager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }
        @Override
        public int getCount() {
            return mFragmentList.size();
        }
        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }





    // KIEDY KLIKNIESZ W LOKALIZACJĘ
    public void lokalizacja(View clickedButton) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.TITLE_przed_zakupem_1);
        builder.setMessage(R.string.BODY_przed_zakupem_1);
        builder.setPositiveButton("zamknij", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    // KIEDY KLIKNIESZ W OKOLICĘ
    public void okolica(View clickedButton) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.TITLE_przed_zakupem_2);
        builder.setMessage(R.string.BODY_przed_zakupem_2);
        builder.setPositiveButton("zamknij", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    // KIEDY KLIKNIESZ W STRONY ŚWIATA
    public void strony_swiata(View clickedButton) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.TITLE_przed_zakupem_3);
        builder.setMessage(R.string.BODY_przed_zakupem_3);
        builder.setPositiveButton("zamknij", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }



    // KIEDY KLIKNIESZ W STRONY ŚWIATA
    public void sasiedzi(View clickedButton) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.TITLE_przed_zakupem_4);
        builder.setMessage(R.string.BODY_przed_zakupem_4);
        builder.setPositiveButton("zamknij", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }



    // KIEDY KLIKNIESZ W CZYNSZ
    public void czynsz(View clickedButton) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.TITLE_przed_zakupem_5);
        builder.setMessage(R.string.BODY_przed_zakupem_5);
        builder.setPositiveButton("zamknij", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    // KIEDY KLIKNIESZ W ŻRÓDŁO CIEPŁA
    public void zrodlo_ciepla (View clickedButton) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.TITLE_przed_zakupem_7);
        builder.setMessage(R.string.BODY_przed_zakupem_7);
        builder.setPositiveButton("zamknij", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    // KIEDY KLIKNIESZ W STAN BUDYNKU
    public void stan_budynku (View clickedButton) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.TITLE_przed_zakupem_8);
        builder.setMessage(R.string.BODY_przed_zakupem_8);
        builder.setPositiveButton("zamknij", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    // KIEDY KLIKNIESZ W STAN BUDYNKU
    public void stan_wyposazenia (View clickedButton) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.TITLE_przed_zakupem_9);
        builder.setMessage(R.string.BODY_przed_zakupem_9);
        builder.setPositiveButton("zamknij", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    // KIEDY KLIKNIESZ W STAN BUDYNKU
    public void co_zostaje (View clickedButton) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.TITLE_przed_zakupem_10);
        builder.setMessage(R.string.BODY_przed_zakupem_10);
        builder.setPositiveButton("zamknij", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }





//CHECKLISTA ODBIOR M OD DEWELOPERA --------------------------------------------------------------------------



    // KIEDY KLIKNIESZ W POWIERZCHNIE M2
    public void start(View clickedButton) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.TITLE_odbior_0);
        builder.setMessage(R.string.BODY_odbior_0);
        builder.setPositiveButton("zamknij", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    // KIEDY KLIKNIESZ W POWIERZCHNIE M2
    public void powierzchnia(View clickedButton) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.TITLE_odbior_1);
        builder.setMessage(R.string.BODY_odbior_1);
        builder.setPositiveButton("zamknij", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    // KIEDY KLIKNIESZ W PODLOGI
    public void podlogi(View clickedButton) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.TITLE_odbior_2);
        builder.setMessage(R.string.BODY_odbior_2);
        builder.setPositiveButton("zamknij", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
    // KIEDY KLIKNIESZ W ŚCIANY - KĄTY
    public void sciany_katy(View clickedButton) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.TITLE_odbior_3);
        builder.setMessage(R.string.BODY_odbior_3);
        builder.setPositiveButton("zamknij", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    // KIEDY KLIKNIESZ W ŚCIANY - POWIERZCHNIA
    public void sciany_powierzchnia(View clickedButton) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.TITLE_odbior_4);
        builder.setMessage(R.string.BODY_odbior_4);
        builder.setPositiveButton("zamknij", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    // KIEDY KLIKNIESZ W ŚCIANY - RYSY I SPĘKANIA
    public void sciany_rysy(View clickedButton) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.TITLE_odbior_5);
        builder.setMessage(R.string.BODY_odbior_5);
        builder.setPositiveButton("zamknij", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    // KIEDY KLIKNIESZ W ŚCIANY - RYSY I SPĘKANIA
    public void wentylacja (View clickedButton) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.TITLE_odbior_6);
        builder.setMessage(R.string.BODY_odbior_6);
        builder.setPositiveButton("zamknij", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    // KIEDY KLIKNIESZ W ŚCIANY - RYSY I SPĘKANIA
    public void okna (View clickedButton) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.TITLE_odbior_7);
        builder.setMessage(R.string.BODY_odbior_7);
        builder.setPositiveButton("zamknij", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    // KIEDY KLIKNIESZ W ŚCIANY - RYSY I SPĘKANIA
    public void woda (View clickedButton) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.TITLE_odbior_8);
        builder.setMessage(R.string.BODY_odbior_8);
        builder.setPositiveButton("zamknij", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    // KIEDY KLIKNIESZ W GARAŻ i piwnicę
    public void garaz_piwnica (View clickedButton) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.TITLE_odbior_9);
        builder.setMessage(R.string.BODY_odbior_9);
        builder.setPositiveButton("zamknij", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    // KIEDY KLIKNIESZ W STANDARD WYKOŃCZENIA
    public void wykonczenie (View clickedButton) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.TITLE_odbior_10);
        builder.setMessage(R.string.BODY_odbior_10);
        builder.setPositiveButton("zamknij", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
