package com.example.project_with_database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project_with_database.database.StoreDatabase;

import static com.example.project_with_database.database.StoreDatabase.COLUMN_USER_EMAIL;
import static com.example.project_with_database.database.StoreDatabase.COLUMN_USER_NAME;
import static com.example.project_with_database.database.StoreDatabase.COLUMN_USER_PASSWORD;
import static com.example.project_with_database.database.StoreDatabase.COLUMN_USER_PHONE;
import static com.example.project_with_database.database.StoreDatabase.TABLE_USERS;

public class registration extends AppCompatActivity implements View.OnClickListener {

    private StoreDatabase storeDatabase;
    private SQLiteDatabase sqbd;

    EditText name;
    EditText email;
    EditText password;
    EditText phone;
    Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        storeDatabase = new StoreDatabase(this);
        sqbd = storeDatabase.getWritableDatabase();

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        phone = findViewById(R.id.phone);
        btn_submit = findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(TextUtils.isEmpty(name.getText())){
            name.setError("Fill again");
        }
        if(TextUtils.isEmpty(email.getText())){
            email.setError("Fill again");
        }
        if(TextUtils.isEmpty(password.getText())){
            password.setError("Fill again");
        }
        if(TextUtils.isEmpty(phone.getText())){
            phone.setError("Fill again");
        }

        ContentValues versionValues = new ContentValues();
        versionValues.put(COLUMN_USER_NAME,name.getText().toString());
        versionValues.put(COLUMN_USER_EMAIL,email.getText().toString());
        versionValues.put(COLUMN_USER_PASSWORD,password.getText().toString());
        versionValues.put(COLUMN_USER_PHONE,phone.getText().toString());

        sqbd.insert(TABLE_USERS, null, versionValues);
        Toast.makeText(this, "Inserted to Database", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, LoginActivity2.class);
        startActivity(intent);
    }
}