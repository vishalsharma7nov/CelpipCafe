package com.allumez.celpipcafe.LoginAndRegistration;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.allumez.celpipcafe.R;
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

public class CelpipCafe_RegistrationActivity extends AppCompatActivity {

    static EditText editTextUsername,editTextEmailAddress,editTextPassword;
    static Button buttonRegister;
    String registrationURL = "http://www.celpipcafe.com/api/registration.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmailAddress = findViewById(R.id.editTextEmailAddress);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registration();
            }
        });
    }
    public void registration() {
        final ProgressDialog loading = ProgressDialog.show(this,"Loading","Please wait...",false,false);
        final String username = editTextUsername.getText().toString();
        final String email = editTextEmailAddress.getText().toString();
        final String password = editTextPassword.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, registrationURL,
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
                                Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                loading.dismiss();
                                Toast.makeText(getApplicationContext(), "Please Fill The Required Fields", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getApplicationContext(),"Error"+error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("Username", username);
                params.put("EmailAddress", email);
                params.put("Password", password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}
