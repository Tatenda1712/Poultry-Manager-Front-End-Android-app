package com.example.poultry;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.poultry.databinding.FragmentAddFlockBinding;
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
 * Use the {@link AddFlockFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFlockFragment extends BaseFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public OkHttpClient client;
    FragmentAddFlockBinding addFlockBinding;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddFlockFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddFlockFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddFlockFragment newInstance(String param1, String param2) {
        AddFlockFragment fragment = new AddFlockFragment();
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
      addFlockBinding=FragmentAddFlockBinding.inflate(inflater,container,false);
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
addFlockBinding.submit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        addFlock();
    }
});
        return addFlockBinding.getRoot();
    }
    private void addFlock() {
        Retrofit retrofit;
        retrofit = new Retrofit.Builder().client(client).baseUrl(baseUrl).build();
        RetrofitApi retrofitservice = retrofit.create(RetrofitApi.class);
        Call<ResponseBody> signup = retrofitservice.addFlock(
                addFlockBinding.batchname.getText().toString(),
                addFlockBinding.number.getText().toString(),
                addFlockBinding.purpose.getText().toString()
        );

        signup.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    JSONObject serverResponse = new JSONObject(response.body().string());

                    if (serverResponse.getString("data").equals("saved")) {
                        Toast.makeText(getContext(), "Data Saved", Toast.LENGTH_SHORT).show();
                        getParentFragmentManager().popBackStack();
                        getParentFragmentManager().beginTransaction().replace(R.id.flockfragment,new AddFlockFragment()).commit();


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