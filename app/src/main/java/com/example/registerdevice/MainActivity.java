package com.example.registerdevice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Button btnSubmit ;
    private EditText editTextEmail;
    private TextView textViewStatus;

    static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3001/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    static ServiceNetwork serviceNetwork = retrofit.create(ServiceNetwork.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewInit();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getEditableText().toString() ;
                JSONObject userReqString = new JSONObject() ;

                try {
                    userReqString.put("email",email);
                    Log.println(Log.INFO,"email isssss",email);
                }catch (JSONException e) {
                    e.printStackTrace();
                }
                final RequestBody body = RequestBody.create(MediaType.parse("application/json"), userReqString.toString()) ;
                serviceNetwork.registerUser(body).enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
//                        Log.println()
                         textViewStatus.setText(response.body().getStatus());
                        Log.d("success", "onResponse: ");
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        Log.e("error", "exception: " + t.getMessage());
                        Log.e("error", "exception: " + t.toString());
                    }
                });
            }
        });
    }

    void viewInit () {
        btnSubmit = findViewById(R.id.submit);
        editTextEmail = findViewById(R.id.email);
        textViewStatus = findViewById(R.id.status);

    }

}
