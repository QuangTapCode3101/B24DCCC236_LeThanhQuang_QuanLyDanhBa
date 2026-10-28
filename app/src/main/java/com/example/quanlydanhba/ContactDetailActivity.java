package com.example.quanlydanhba;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ContactDetailActivity extends AppCompatActivity {

    private TextView tvDetailAvatar, tvDetailName, tvDetailPhone, tvDetailGroup, tvDetailGender, tvDetailNote;
    private CheckBox cbDetailFavorite;
    private Button btnCall, btnBack, btnDelete;
    private Contact receivedContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        tvDetailAvatar = findViewById(R.id.tvDetailAvatar);
        tvDetailName = findViewById(R.id.tvDetailName);
        tvDetailPhone = findViewById(R.id.tvDetailPhone);
        tvDetailGroup = findViewById(R.id.tvDetailGroup);
        tvDetailGender = findViewById(R.id.tvDetailGender);
        tvDetailNote = findViewById(R.id.tvDetailNote);
        cbDetailFavorite = findViewById(R.id.cbDetailFavorite);
        btnCall = findViewById(R.id.btnCall);
        btnBack = findViewById(R.id.btnBack);
        btnDelete = findViewById(R.id.btnDelete);

        Intent intent = getIntent();
        receivedContact = (Contact) intent.getSerializableExtra("EXTRA_CONTACT");

        if (receivedContact == null) {
            finish();
            return;
        }

        tvDetailAvatar.setText(receivedContact.getAvatarLetter());
        tvDetailName.setText(receivedContact.getFullName());
        tvDetailPhone.setText(receivedContact.getPhone());
        tvDetailGroup.setText(receivedContact.getGroup());
        tvDetailGender.setText(receivedContact.getGender());
        tvDetailNote.setText(receivedContact.getNote().isEmpty() ? "(Không có ghi chú)" : receivedContact.getNote());
        cbDetailFavorite.setChecked(receivedContact.isFavorite());

        cbDetailFavorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Contact original = ContactData.findByPhone(receivedContact.getPhone());
                if (original != null) {
                    original.setFavorite(isChecked);
                }
            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:" + receivedContact.getPhone()));
                startActivity(dialIntent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactData.deleteByPhone(receivedContact.getPhone());
                Toast.makeText(ContactDetailActivity.this, "Đã xóa liên hệ", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}