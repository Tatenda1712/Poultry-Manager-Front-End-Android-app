package com.example.poultry;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.poultry.Adapter.EggsAdapter;
import com.example.poultry.Adapter.FlockAdapter;
import com.example.poultry.databinding.FragmentViewEggsBinding;
import com.example.poultry.databinding.FragmentViewFLockBinding;
import com.example.poultry.service.RetrofitApi;

import org.json.JSONArray;
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
 * Use the {@link ViewEggsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewEggsFragment extends BaseFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "mParam2";
    public OkHttpClient client;
    FragmentViewEggsBinding vieweggsBinding;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ViewEggsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewEggsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewEggsFragment newInstance(String param1, String param2) {
        ViewEggsFragment fragment = new ViewEggsFragment();
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
        vieweggsBinding= FragmentViewEggsBinding.inflate(inflater,container,false);
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
        getEggs();
        return vieweggsBinding.getRoot();
    }
    private void getEggs() {
        Retrofit retrofit = new Retrofit.Builder().client(client).baseUrl(baseUrl).build();
        RetrofitApi service = retrofit.create(RetrofitApi.class);
        Call<ResponseBody> eggs = service.getEggs();

        eggs.enqueue(new Callback<ResponseBody>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    JSONObject serverResponse = new JSONObject(response.body().string());

                    JSONArray items = serverResponse.getJSONArray("data");

                    EggsAdapter adapter = new EggsAdapter(items,getContext());
                    vieweggsBinding.eggsitems.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
                    vieweggsBinding.eggsitems.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
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