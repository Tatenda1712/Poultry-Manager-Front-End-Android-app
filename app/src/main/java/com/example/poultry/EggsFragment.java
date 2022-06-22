package com.example.poultry;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.poultry.databinding.FragmentEggsBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EggsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EggsFragment extends Fragment {
FragmentEggsBinding eggsBinding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EggsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EggsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EggsFragment newInstance(String param1, String param2) {
        EggsFragment fragment = new EggsFragment();
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
        // Inflate the layout for this fragment
        eggsBinding=FragmentEggsBinding.inflate(inflater,container,false);
        AddEggsFragment fragment = new AddEggsFragment();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.eggsfragment , fragment);
        transaction.addToBackStack(null);
        transaction.commit();
eggsBinding.addeggs.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        AddEggsFragment fragment = new AddEggsFragment();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.eggsfragment , fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
});
eggsBinding.vieweggs.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        ViewEggsFragment fragment = new ViewEggsFragment();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.eggsfragment , fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
});
return eggsBinding.getRoot();
    }
}