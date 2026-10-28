package com.example.quanlydanhba;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {

    public ContactAdapter(@NonNull Context context, @NonNull List<Contact> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_contact, parent, false);
        }

        Contact contact = getItem(position);
        if (contact != null) {
            TextView tvAvatar = convertView.findViewById(R.id.tvAvatar);
            TextView tvName = convertView.findViewById(R.id.tvItemName);
            TextView tvPhone = convertView.findViewById(R.id.tvItemPhone);
            TextView tvGroup = convertView.findViewById(R.id.tvItemGroup);
            TextView tvStar = convertView.findViewById(R.id.tvStar);

            tvAvatar.setText(contact.getAvatarLetter());
            tvName.setText(contact.getFullName());
            tvPhone.setText(contact.getPhone());
            tvGroup.setText(contact.getGroup());

            if (contact.isFavorite()) {
                tvStar.setVisibility(View.VISIBLE);
            } else {
                tvStar.setVisibility(View.GONE);
            }
        }
        return convertView;
    }
}