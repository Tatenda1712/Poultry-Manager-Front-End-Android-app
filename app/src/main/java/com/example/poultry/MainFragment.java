package com.example.poultry;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.poultry.databinding.FragmentMainBinding;
import com.example.poultry.databinding.FragmentRegisterBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FragmentMainBinding mainBinding;
    SharedPreferences preferences;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainBinding= FragmentMainBinding.inflate(inflater,container,false);
        mainBinding.inventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InventoryFragment fragment = new InventoryFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.mainfragement, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        mainBinding.transactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransactionsFragment fragment = new TransactionsFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.mainfragement, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        mainBinding.health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HealthFragment fragment = new HealthFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.mainfragement, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        mainBinding.feeding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FeedingFragment fragment = new FeedingFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.mainfragement, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        mainBinding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SigninFragment fragment = new SigninFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.mainfragement, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return  mainBinding.getRoot();
    }
    }
