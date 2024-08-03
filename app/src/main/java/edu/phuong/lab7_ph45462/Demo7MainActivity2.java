package edu.phuong.lab7_ph45462;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Demo7MainActivity2 extends AppCompatActivity {
    private Button bntSelect;
    private TextView tvKQ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bntSelect = findViewById(R.id.btn_select);
        tvKQ = findViewById(R.id.tv_kq);

        bntSelect.setOnClickListener(v -> {
            selectVolley();
        });
    }

    String strKQ = "";

    private void selectVolley() {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "https://hungnq28.000webhostapp.com/su2024/select.php";
        JsonObjectRequest request = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    //lay ve array:  "products": [
                    JSONArray jsonArray = response.getJSONArray("products");
                    //for array
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject p = jsonArray.getJSONObject(i);//lay ve doi tuong i
                        String MaSP = p.getString("MaSP");
                        String TenSP = p.getString("TenSP");
                        String MoTa = p.getString("MoTa");
                        strKQ += "MaSP: " + MaSP + "; TenSP:" + TenSP + "; MoTa: " + MoTa + "\n";
                    }
                    tvKQ.setText(strKQ);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvKQ.setText(error.getMessage());
            }
        });
        //4. thuc thi request
        queue.add(request);
    }
}