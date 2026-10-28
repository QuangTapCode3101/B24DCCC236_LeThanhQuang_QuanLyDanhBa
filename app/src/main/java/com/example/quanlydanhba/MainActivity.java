package com.example.quanlydanhba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvTotalCount, tvFavoriteCount;
    private ListView lvContacts;
    private Button btnOpenAdd;
    private ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContactData.initSampleData();

        tvTotalCount = findViewById(R.id.tvTotalCount);
        tvFavoriteCount = findViewById(R.id.tvFavoriteCount);
        lvContacts = findViewById(R.id.lvContacts);
        btnOpenAdd = findViewById(R.id.btnOpenAdd);

        adapter = new ContactAdapter(this, ContactData.contactList);
        lvContacts.setAdapter(adapter);

        btnOpenAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
                startActivity(intent);
            }
        });

        lvContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact selectedContact = ContactData.contactList.get(position);
                Intent intent = new Intent(MainActivity.this, ContactDetailActivity.class);
                intent.putExtra("EXTRA_CONTACT", selectedContact);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateStatisticsAndList();
    }

    private void updateStatisticsAndList() {
        adapter.notifyDataSetChanged();
        tvTotalCount.setText(String.valueOf(ContactData.contactList.size()));
        tvFavoriteCount.setText(String.valueOf(ContactData.getFavoriteCount()));
    }
}