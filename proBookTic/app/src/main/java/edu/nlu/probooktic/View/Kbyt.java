package edu.nlu.probooktic.View;

<<<<<<< HEAD
=======
import androidx.appcompat.app.AppCompatActivity;

>>>>>>> main
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

<<<<<<< HEAD
import androidx.appcompat.app.AppCompatActivity;

import edu.nlu.probooktic.R;

public class Kbyt extends AppCompatActivity {

    static final int RESULT_NO=1025;
    
//    private Context context;
//
//    RadioGroup rGroupGender, rGroupHo, rGroupSot, rGroupDauHong, rGroupNon, rGroupNoiBan, rGroupXuatHuyet, rGroupTiepXuc, rGroupKhoTho, rGroupTieuChay;
//    String nameCus, dayOfBirth, gender, numCMND, address, phone, email, placesIn14Days, tiepXuc, sot, ho, khoTho, buonNon, xuatHuyet, noiBan, dauHong, tieuChay;
//    EditText eName, eBirthday, eCMND, eAddress, ePhone, eEmail, ePlaces;
//    public static ArrayList<InfoHealthDeclaration> listForm = new ArrayList<>();
//    String idCus="C001";
//
//    private DatabaseReference mdata;

=======
import edu.nlu.probooktic.R;

public class Kbyt extends AppCompatActivity {
static final int RESULT_NO=1025;
>>>>>>> main
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kbyt);
<<<<<<< HEAD
        
//        context = this;
//
//        rGroupGender = findViewById(R.id.rgGender);
//        rGroupTiepXuc = findViewById(R.id.rgTiepXuc);
//        rGroupSot = findViewById(R.id.rgSot);
//        rGroupHo = findViewById(R.id.rgHo);
//        rGroupKhoTho = findViewById(R.id.rgKhoTho);
//        rGroupDauHong = findViewById(R.id.rgDauHong);
//        rGroupNon = findViewById(R.id.rgNon);
//        rGroupTieuChay = findViewById(R.id.rgTieuChay);
//        rGroupXuatHuyet = findViewById(R.id.rgXuatHuyet);
//        rGroupNoiBan = findViewById(R.id.rgNoiBan);
//
//        eName = findViewById(R.id.edtNameCus);
//        eBirthday = findViewById(R.id.edtDayOfBirth);
//        eCMND = findViewById(R.id.edtNumCMND);
//        eAddress = findViewById(R.id.edtAddress);
//        ePhone = findViewById(R.id.edtPhone);
//        eEmail = findViewById(R.id.edtEmailAddress);
//        ePlaces = findViewById(R.id.edtPlaces);
////radioGroup
//        rGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                switch (checkedId){
//                    case R.id.rbtnNam:
//                            gender = "Nam";
//                        break;
//                    case R.id.rbtnNu:
//                            gender = "Nữ";
//                        break;
//                }
//            }
//        });
//        rGroupTiepXuc.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                switch (checkedId){
//                    case R.id.rbtnNam:
//                        tiepXuc = "Có tiếp xúc";
//                        break;
//                    case R.id.rbtnNu:
//                        tiepXuc = "Không tiếp xúc";
//                        break;
//                }
//            }
//        });
//        rGroupSot.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                switch (checkedId){
//                    case R.id.rbtnNam:
//                        sot = "Sốt";
//                        break;
//                    case R.id.rbtnNu:
//                        sot = "Không";
//                        break;
//                }
//            }
//        });
//        rGroupHo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                switch (checkedId){
//                    case R.id.rbtnNam:
//                        ho = "Ho";
//                        break;
//                    case R.id.rbtnNu:
//                        ho = "Không";
//                        break;
//                }
//            }
//        });
//        rGroupKhoTho.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                switch (checkedId){
//                    case R.id.rbtnNam:
//                        khoTho = "Khó thở";
//                        break;
//                    case R.id.rbtnNu:
//                        khoTho = "Không";
//                        break;
//                }
//            }
//        });
//        rGroupDauHong.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                switch (checkedId){
//                    case R.id.rbtnNam:
//                        dauHong = "Đau họng";
//                        break;
//                    case R.id.rbtnNu:
//                        dauHong = "Không";
//                        break;
//                }
//            }
//        });
//        rGroupNon.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                switch (checkedId){
//                    case R.id.rbtnNam:
//                        buonNon = "Nôn/Buồn nôn";
//                        break;
//                    case R.id.rbtnNu:
//                        buonNon = "Không";
//                        break;
//                }
//            }
//        });
//        rGroupTieuChay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                switch (checkedId){
//                    case R.id.rbtnNam:
//                        tieuChay = "Tiêu chảy";
//                        break;
//                    case R.id.rbtnNu:
//                        tieuChay = "Không";
//                        break;
//                }
//            }
//        });
//        rGroupXuatHuyet.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                switch (checkedId){
//                    case R.id.rbtnNam:
//                        xuatHuyet = "Xuất huyết ngoài da";
//                        break;
//                    case R.id.rbtnNu:
//                        xuatHuyet = "Không";
//                        break;
//                }
//            }
//        });
//        rGroupNoiBan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                switch (checkedId){
//                    case R.id.rbtnNam:
//                        noiBan = "Nổi ban ngoài da";
//                        break;
//                    case R.id.rbtnNu:
//                        noiBan = "Không";
//                        break;
//                }
//            }
//        });
//
//        InfoHealthDeclaration hd = new InfoHealthDeclaration(idCus,eName.getText().toString(),eBirthday.getText().toString(),gender.toString(), eCMND.getText().toString(),
//                eAddress.getText().toString(), ePhone.getText().toString(), eEmail.getText().toString(), ePlaces.getText().toString(),sot.toString(),ho.toString(),khoTho.toString()
//        , dauHong.toString(), buonNon.toString(), tieuChay.toString(), xuatHuyet.toString(), noiBan.toString());
//        listForm.add(hd);
////editText
//        eName.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                hd.setNameCus(eName.getText().toString());
//            }
//        });
//        eBirthday.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                hd.setDayOfBirth(eBirthday.getText().toString());
//            }
//        });
//        eCMND.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                hd.setNumCMND(eCMND.getText().toString());
//            }
//        });
//        eAddress.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                hd.setAddress(eAddress.getText().toString());
//            }
//        });
//        ePhone.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                hd.setPhone(ePhone.getText().toString());
//            }
//        });
//        eEmail.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                hd.setEmail(eEmail.getText().toString());
//            }
//        });
//        ePlaces.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                hd.setPlacesIn14Days(ePlaces.getText().toString());
//            }
//        });
//
//
    Button confirm = (Button)findViewById(R.id.btn_confirm_KBYT);

=======
        Button confirm = (Button)findViewById(R.id.btn_confirm_KBYT);
>>>>>>> main
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("RESULT_KB",true);
                setResult(RESULT_NO,intent);
                finish();
            }
        });

    }
<<<<<<< HEAD
//    private void ok() {
//        // get data
//        String name = eName.getText().toString().trim();
//        String phone = ePhone.getText().toString().trim();
//        String email = eEmail.getText().toString().trim();
//        String cmnd = eCMND.getText().toString().trim();
//        String address = eAddress.getText().toString().trim();
//        String places = ePlaces.getText().toString().trim();
//        String birthDay = eBirthday.getText().toString().trim();
//// check data
//        if (TextUtils.isEmpty(name)) {
//            eName.requestFocus();
//            Toast.makeText(context, getString(R.string.please_enter_name), Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(phone)) {
//            ePhone.requestFocus();
//            Toast.makeText(context, getString(R.string.please_enter_phone), Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(email)) {
//            eEmail.requestFocus();
//            Toast.makeText(context, getString(R.string.please_enter_email), Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(address)) {
//            eAddress.requestFocus();
//            Toast.makeText(context, getString(R.string.please_enter_address), Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        if (TextUtils.isEmpty(birthDay)) {
//            eBirthday.requestFocus();
//            Toast.makeText(context, getString(R.string.please_enter_birthday), Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        if (TextUtils.isEmpty(cmnd)) {
//            eCMND.requestFocus();
//            Toast.makeText(context, getString(R.string.please_enter_cmnd), Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        // if all is ok, we process data and show dialog to notify
//
//        String notifySuccess = getString(context, R.string.success);
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setTitle(R.string.success)
//                .setMessage(notifySuccess)
//                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // do something
//                    }
//                })
//                .create()
//                .show();
//    }
//    public static String getString(Context context, int id) {
//        return context.getResources().getString(id);
//    }

=======
>>>>>>> main
}