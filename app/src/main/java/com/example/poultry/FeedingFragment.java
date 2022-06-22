package com.example.poultry;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.poultry.databinding.FragmentFeedingBinding;
import com.example.poultry.databinding.FragmentFeedsBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FeedingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeedingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
  FragmentFeedingBinding feedingBinding;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FeedingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FeedingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FeedingFragment newInstance(String param1, String param2) {
        FeedingFragment fragment = new FeedingFragment();
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
        feedingBinding=FragmentFeedingBinding.inflate(inflater,container,false);
        feedingBinding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount= feedingBinding.birdsnumber.getText().toString();
                int number = Integer.parseInt(amount);
                if (number<=0){
                    Toast.makeText(getContext(), "You can not enter a number less than or equal to 0", Toast.LENGTH_SHORT).show();
                }
                else if (number<=70){
                    feedingBinding.kgamount.setText("25kg");
                    feedingBinding.kgamount2.setText("25kg");
                    feedingBinding.kgamount3.setText("50kg");
                }
                else if (number>70 &&number<=150){
                    feedingBinding.kgamount.setText("50kg");
                    feedingBinding.kgamount2.setText("50kg");
                    feedingBinding.kgamount3.setText("100kg");
                }
                else if (number>150 &&number<=250){
                    feedingBinding.kgamount.setText("100kg");
                    feedingBinding.kgamount2.setText("100kg");
                    feedingBinding.kgamount3.setText("200kg");
                }
                else if (number>250 &&number<=500){
                    feedingBinding.kgamount.setText("150kg");
                    feedingBinding.kgamount2.setText("150kg");
                    feedingBinding.kgamount3.setText("300kg");
                }
                else if (number>500 &&number<=100){
                    feedingBinding.kgamount.setText("200kg");
                    feedingBinding.kgamount2.setText("200kg");
                    feedingBinding.kgamount3.setText("400kg");
                }
                else{
                    feedingBinding.kgamount.setText("500kg");
                    feedingBinding.kgamount2.setText("500kg");
                    feedingBinding.kgamount3.setText("1000kg");
                }
            }
        });


        return feedingBinding.getRoot();
    }
}