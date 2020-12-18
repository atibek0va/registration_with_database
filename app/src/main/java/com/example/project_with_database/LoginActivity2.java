package com.example.project_with_database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
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

public class LoginActivity2 extends AppCompatActivity implements View.OnClickListener{
    private StoreDatabase storeDatabase;
    private SQLiteDatabase sqbd;

    EditText email;
    EditText password;
    Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        storeDatabase = new StoreDatabase(this);
        sqbd = storeDatabase.getWritableDatabase();

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btn_submit = findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(TextUtils.isEmpty(email.getText())){
            email.setError("Fill again");
        }
        if(TextUtils.isEmpty(password.getText())){
            password.setError("Fill again");
        }

        Cursor userCursor = sqbd.rawQuery(" SELECT * FROM " + TABLE_USERS +
                " WHERE " + COLUMN_USER_PHONE + " =? AND " + COLUMN_USER_PASSWORD + " =? ",
                new String[]{email.getText().toString(), password.getText().toString()});

        if (((userCursor != null) && (userCursor.getCount() > 0))){
            userCursor.moveToFirst();
            String userName = userCursor.getString(userCursor.getColumnIndex(COLUMN_USER_NAME));
            Toast.makeText(this, "User is here! Welcome, " + userName + "!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Invalid User!", Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(this, "Inserted to Database", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, LoginActivity2.class);
        startActivity(intent);
    }
}