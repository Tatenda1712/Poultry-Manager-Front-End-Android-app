package com.example.poultry;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.poultry.databinding.FragmentTransactionsBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TransactionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TransactionsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FragmentTransactionsBinding transactionsBinding;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TransactionsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TransactionsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TransactionsFragment newInstance(String param1, String param2) {
        TransactionsFragment fragment = new TransactionsFragment();
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
      transactionsBinding=FragmentTransactionsBinding.inflate(inflater,container,false);
        AddTransactionFragment fragment = new AddTransactionFragment();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.transactionfragment , fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        transactionsBinding.addtransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTransactionFragment fragment = new AddTransactionFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.transactionfragment , fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        transactionsBinding.viewtransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewTransactionFragment fragment = new ViewTransactionFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.transactionfragment , fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
      return transactionsBinding.getRoot();
    }
}