package com.example.kapil.formtask1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.security.acl.Owner;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private final AppCompatActivity activity = MainActivity.this;
    EditText editText,editText2,editText3,editText4,editText5;
    String first_name,last_name,phone,address,restau_name;
    private APIService mAPIService;
    DatabaseHelper databaseHelper;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
        mySpinner.setEnabled(false);
        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText4);
        editText5 = (EditText) findViewById(R.id.editText5);
        Button button =(Button) findViewById(R.id.button);

       // mAPIService = ApiUtils.getAPIService();
        databaseHelper = new DatabaseHelper(activity);
        user =new User();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()) {
                    if (!databaseHelper.checkUser(editText2.getText().toString().trim())) {
                        mySpinner.setEnabled(true);
                        Toast.makeText(MainActivity.this, "Please Select the Request Type Now", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "Request From This Phone Already Exist", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (validate()) {
                    if (i == 1) {
                        user.setRequestby("1");
                       // sendPost("1");
                        sendUserData();
                        startActivity(new Intent(MainActivity.this, OwnerActivity.class));
                    } else if (i == 2) {
                        user.setRequestby("2");
                       // sendPost("2");
                        sendUserData();
                        startActivity(new Intent(MainActivity.this, ManagerActivity.class));
                    } else if (i == 3) {
                        user.setRequestby("3");
                       // sendPost("3");
                        sendUserData();
                        startActivity(new Intent(MainActivity.this, OtherActivity.class));
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

   public Boolean validate(){
        Boolean result=false;
       first_name=editText.getText().toString();
       last_name=editText5.getText().toString();
       phone=editText2.getText().toString();
       address=editText3.getText().toString();
       restau_name=editText4.getText().toString();

        if(first_name.isEmpty() || last_name.isEmpty() || phone.isEmpty() || address.isEmpty() || restau_name.isEmpty()){
            Toast.makeText(this,"Please Enter All Details First",Toast.LENGTH_SHORT).show();
        }
        else {
            result=true;
        }
        return result;
   }

   public void sendUserData(){
       if (!databaseHelper.checkUser(editText2.getText().toString().trim())) {

           user.setFirst_name(editText.getText().toString().trim());
           user.setLast_name(editText5.getText().toString().trim());
           user.setPhone(editText2.getText().toString().trim());
           user.setAddress(editText3.getText().toString().trim());
           user.setRestau_name(editText4.getText().toString().trim());

           databaseHelper.addUser(user);

           emptyInputEditText();

           Toast.makeText(this,"Data Sent Sucessfully to SQL Lite",Toast.LENGTH_SHORT).show();
       }
   }

    private void emptyInputEditText(){
        editText.setText(null);
        editText5.setText(null);
        editText2.setText(null);
        editText3.setText(null);
        editText4.setText(null);
    }

    public void sendPost(String requestby) {
        mAPIService.savePost(requestby).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if(response.isSuccessful()) {
                    Toast.makeText(MainActivity.this,"API Call Sucessfull",Toast.LENGTH_SHORT).show();
                    Log.i("Sucessfull", "post submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this,"API Can't Able to Call",Toast.LENGTH_SHORT).show();
                Log.e("UnSucessfull", "Unable to submit post to API.");
            }
        });
    }
}


