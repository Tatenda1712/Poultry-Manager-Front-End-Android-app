package com.example.poultry;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.poultry.databinding.FragmentAddTransactionBinding;
import com.example.poultry.service.RetrofitApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddTransactionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddTransactionFragment extends BaseFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FragmentAddTransactionBinding addTransactionBinding;
    public OkHttpClient client;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddTransactionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddTransactionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddTransactionFragment newInstance(String param1, String param2) {
        AddTransactionFragment fragment = new AddTransactionFragment();
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
        addTransactionBinding=FragmentAddTransactionBinding.inflate(inflater,container,false);
        client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String s, SSLSession sslSession) {
                        return true;
                    }
                })
                .build();
        addTransactionBinding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTransaction();
            }
        });
        return  addTransactionBinding.getRoot();
    }
    private void addTransaction() {
        Retrofit retrofit;
        retrofit = new Retrofit.Builder().client(client).baseUrl(baseUrl).build();
        RetrofitApi retrofitservice = retrofit.create(RetrofitApi.class);
        Call<ResponseBody> signup = retrofitservice.addTransactions(
                addTransactionBinding.purpose.getText().toString(),
                addTransactionBinding.amount.getText().toString(),
                addTransactionBinding.profit.getText().toString()
        );

        signup.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    JSONObject serverResponse = new JSONObject(response.body().string());

                    if (serverResponse.getString("data").equals("saved")) {
                        Toast.makeText(getContext(), "Data Saved", Toast.LENGTH_SHORT).show();
                        getParentFragmentManager().popBackStack();
                        getParentFragmentManager().beginTransaction().replace(R.id.transactionfragment,new AddTransactionFragment()).commit();


                    }
                } catch(JSONException e){
                    e.printStackTrace();
                } catch(IOException e){
                    e.printStackTrace();
                }
            }


            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}