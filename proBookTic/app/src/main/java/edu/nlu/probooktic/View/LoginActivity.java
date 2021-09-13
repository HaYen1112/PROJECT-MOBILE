package edu.nlu.probooktic.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

import edu.nlu.probooktic.Admin.TrangChuAdmin;
import edu.nlu.probooktic.Model.Acount;
import edu.nlu.probooktic.R;

public class LoginActivity extends AppCompatActivity {
    private ImageView comeSignIn;
    private EditText nameEdit, passWEdit;
    private FirebaseAuth firebaseAuth;
    private Button btnlogin;
    DatabaseReference dataAcount;
    String nameDN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        comeSignIn = findViewById(R.id.comesignin);
        nameEdit = findViewById(R.id.editTextName);
        passWEdit = findViewById(R.id.editTextPassword);
        btnlogin = findViewById(R.id.cirLoginButton);
        firebaseAuth = FirebaseAuth.getInstance();

        comeSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }


        });

    }
    private void login() {
        String name, pass;
        name = nameEdit.getText().toString();
        pass = passWEdit.getText().toString();
        dataAcount = FirebaseDatabase.getInstance().getReference();
        // kiểm tra ô trống hay không
        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"Vui Lòng Nhập Tên!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Vui Lòng Nhập Mật PassWord!",Toast.LENGTH_SHORT).show();
            return;
        }
        firebaseAuth.signInWithEmailAndPassword(name,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Đăng Nhập Thành Công!",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, TrangChuAdmin.class);
//                    startActivity(i);
                    dataAcount.child("Acount").addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                            Acount acount = snapshot.getValue(Acount.class);
                            if (acount.getMail().equalsIgnoreCase(name)){
                                nameDN = (String) acount.getName();
                                Toast.makeText(getApplicationContext(), acount.getName(),Toast.LENGTH_SHORT).show();
                                i.putExtra("name", nameDN);
                                startActivity(i);
                            }
                        }
                        @Override
                        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }else{
                    Toast.makeText(getApplicationContext(),"Đăng Nhập Không Thành Công!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void onLoginClick(View view) {
    }

}