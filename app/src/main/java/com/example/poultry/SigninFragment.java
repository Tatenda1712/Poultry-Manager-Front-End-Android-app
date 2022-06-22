package com.example.poultry;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.poultry.databinding.FragmentSigninBinding;
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
 * Use the {@link SigninFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SigninFragment extends BaseFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FragmentSigninBinding signinBinding;
    public OkHttpClient client;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SigninFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SigninFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SigninFragment newInstance(String param1, String param2) {
        SigninFragment fragment = new SigninFragment();
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
        signinBinding=FragmentSigninBinding.inflate(inflater,container,false);
        preferences = getActivity().getSharedPreferences("user", MODE_PRIVATE);
        editor= preferences.edit();
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
        signinBinding.signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               login();
            }
        });
        signinBinding.tosignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterFragment fragment = new RegisterFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.mainfragement, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return signinBinding.getRoot();
    }
    private void login() {
        Retrofit retrofit = new Retrofit.Builder().client(client).baseUrl(baseUrl).build();
        RetrofitApi retrofitservice = retrofit.create(RetrofitApi.class);
        Call<ResponseBody> login = retrofitservice.authenticate(
                signinBinding.email.getText().toString(),
                signinBinding.password.getText().toString()
        );

        login.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    JSONObject serverResponse = new JSONObject(response.body().string());
//                    if(response.body() != null) {
//                        String s = response.body().string();
//                    }
                    if (!serverResponse.getString("data").equals("failed")){
                        editor.putString("self",serverResponse.getJSONObject("data").getString("id"));
                        editor.commit();
                        getParentFragmentManager().popBackStack();
                        getParentFragmentManager().beginTransaction().replace(R.id.mainfragement,new MainFragment()).commit();

                    }
                    else {
                        Toast.makeText(getContext(), "Incorrect Credentials", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException | IOException e) {
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