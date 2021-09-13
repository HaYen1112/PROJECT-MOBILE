package edu.nlu.probooktic.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import edu.nlu.probooktic.Admin.TrangChuAdmin;
import edu.nlu.probooktic.Model.Acount;
import edu.nlu.probooktic.R;

public class RegisterActivity extends AppCompatActivity {
    private ImageView comeLogin;
    private EditText nameEdit, passEdit, sdtEdit, emailEdit;
    private Button signInbtn;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        comeLogin = findViewById(R.id.comelogin);
        nameEdit = findViewById(R.id.editTextName);
        emailEdit =findViewById(R.id.editTextEmail);
        sdtEdit = findViewById(R.id.editTextMobile);
        passEdit = findViewById(R.id.editTextPassword);
        signInbtn = findViewById(R.id.cirRegisterButton);
        firebaseAuth = FirebaseAuth.getInstance();

        signInbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }


        });
        comeLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
    private void register() {
        String pass,email, name, sdt;
        pass = passEdit.getText().toString();
        email = emailEdit.getText().toString();
        name = nameEdit.getText().toString();
        sdt = sdtEdit.getText().toString();

        // kiểm tra ô trống hay không
        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"Vui Lòng Nhập Tên!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Vui Lòng Nhập PassWord!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Vui Lòng Nhập Email!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(sdt)){
            Toast.makeText(this,"Vui Lòng Nhập Số Điện Thoại!",Toast.LENGTH_SHORT).show();
            return;
        }
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Tạo Tài Khoảng Thành Công!", Toast.LENGTH_SHORT ).show();
                    Intent i = new Intent(RegisterActivity.this, TrangChuAdmin.class);
                    i.putExtra("name", name);
                    startActivity(i);
                    databaseReference.child("Acount").child(name).setValue(new Acount(name, pass, email, sdt));
                }else{
                    Toast.makeText(getApplicationContext(),"Tạo Tài Khoảng Không Thành Công!", Toast.LENGTH_SHORT ).show();

                }
            }
        }) ;

    }
    public void onLoginClick(View view) {
    }
}