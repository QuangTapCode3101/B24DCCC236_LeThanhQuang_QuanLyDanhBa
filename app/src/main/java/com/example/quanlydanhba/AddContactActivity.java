package com.example.quanlydanhba;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddContactActivity extends AppCompatActivity {

    private EditText edtFullName, edtPhone, edtNote;
    private Spinner spnGroup;
    private RadioGroup rgGender;
    private CheckBox cbFavorite;
    private Button btnSaveContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        edtFullName = findViewById(R.id.edtFullName);
        edtPhone = findViewById(R.id.edtPhone);
        edtNote = findViewById(R.id.edtNote);
        spnGroup = findViewById(R.id.spnGroup);
        rgGender = findViewById(R.id.rgGender);
        cbFavorite = findViewById(R.id.cbFavorite);
        btnSaveContact = findViewById(R.id.btnSaveContact);

        String[] groups = {"Bạn bè", "Gia đình", "Công việc", "Khác"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, groups);
        spnGroup.setAdapter(spinnerAdapter);

        btnSaveContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveContact();
            }
        });
    }

    private void saveContact() {
        String fullName = edtFullName.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        String group = spnGroup.getSelectedItem().toString();
        String note = edtNote.getText().toString().trim();
        boolean isFavorite = cbFavorite.isChecked();

        String gender = "Nam";
        int checkedId = rgGender.getCheckedRadioButtonId();
        if (checkedId == R.id.rbFemale) {
            gender = "Nữ";
        } else if (checkedId == R.id.rbOther) {
            gender = "Khác";
        }

        if (fullName.isEmpty()) {
            edtFullName.setError("Họ và tên không được để trống");
            edtFullName.requestFocus();
            return;
        }

        if (!phone.matches("^0\\d{9}$")) {
            edtPhone.setError("Số điện thoại phải gồm 10 chữ số và bắt đầu bằng 0");
            edtPhone.requestFocus();
            return;
        }

        if (ContactData.isPhoneExists(phone)) {
            edtPhone.setError("Số điện thoại đã tồn tại trong danh bạ");
            edtPhone.requestFocus();
            return;
        }

        Contact newContact = new Contact(fullName, phone, group, gender, isFavorite, note);
        ContactData.contactList.add(newContact);

        Toast.makeText(this, "Đã thêm liên hệ thành công", Toast.LENGTH_SHORT).show();
        finish();
    }
}