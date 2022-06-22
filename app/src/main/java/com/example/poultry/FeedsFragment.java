package com.example.poultry;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.poultry.databinding.FragmentFeedsBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FeedsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeedsFragment extends Fragment {
    FragmentFeedsBinding feedsBinding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FeedsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FeedsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FeedsFragment newInstance(String param1, String param2) {
        FeedsFragment fragment = new FeedsFragment();
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
     feedsBinding=FragmentFeedsBinding.inflate(inflater,container,false);
        AddFeedsFragment fragment = new AddFeedsFragment();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.feedsfragment , fragment);
        transaction.addToBackStack(null);
        transaction.commit();
     feedsBinding.addfeeds.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             AddFeedsFragment fragment = new AddFeedsFragment();
             FragmentManager fm = getFragmentManager();
             FragmentTransaction transaction = fm.beginTransaction();
             transaction.replace(R.id.feedsfragment , fragment);
             transaction.addToBackStack(null);
             transaction.commit();
         }
     });
     feedsBinding.viewfeeds.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             ViewFeedsFragment fragment = new ViewFeedsFragment();
             FragmentManager fm = getFragmentManager();
             FragmentTransaction transaction = fm.beginTransaction();
             transaction.replace(R.id.feedsfragment , fragment);
             transaction.addToBackStack(null);
             transaction.commit();
         }
     });
     return feedsBinding.getRoot();
    }
}