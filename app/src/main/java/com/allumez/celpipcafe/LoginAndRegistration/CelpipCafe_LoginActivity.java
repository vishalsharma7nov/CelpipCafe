package com.allumez.celpipcafe.LoginAndRegistration;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.allumez.celpipcafe.R;
import com.allumez.celpipcafe.UserDashBoard.UserDashboardActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CelpipCafe_LoginActivity extends AppCompatActivity {

    EditText editTextUsername,editTextPassowrd;
    Button buttonLogin;
    String LoginUrl = "http://celpipcafe.com/api/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassowrd = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }
    public void login() {
        final ProgressDialog loading = ProgressDialog.show(this,"Loading","Please wait...",false,false);
        final String Username = editTextUsername.getText().toString();
        final String Password = editTextPassowrd.getText().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,LoginUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try
                        {
                            JSONObject obj = new JSONObject(response);
                            int abc = Integer.parseInt(obj.getString("status"));
                            if (abc == 1)
                            {
                                loading.dismiss();
                                Toast.makeText(CelpipCafe_LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), UserDashboardActivity.class);
                                intent.putExtra("username",Username);
                                startActivity(intent);
                            }
                            else
                            {
                                loading.dismiss();
                                Toast.makeText(getApplicationContext(), "Invalid Username or Password"+response, Toast.LENGTH_SHORT).show();
                            }
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                            loading.dismiss();
                            Toast.makeText(getApplicationContext(),"Error"+e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(getApplicationContext(), "Error"+error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("Username", Username);
                params.put("password", Password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}
