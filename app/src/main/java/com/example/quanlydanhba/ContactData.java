package com.example.quanlydanhba;

import java.util.ArrayList;

public class ContactData {
    public static final ArrayList<Contact> contactList = new ArrayList<>();
    private static boolean isInitialized = false;

    public static void initSampleData() {
        if (!isInitialized) {
            contactList.add(new Contact("Nguyễn Văn An", "0901234567", "Bạn bè", "Nam", false, "Bạn đại học"));
            contactList.add(new Contact("Trần Thị Bình", "0912345678", "Gia đình", "Nữ", true, "Chị gái, hay gọi buổi tối"));
            contactList.add(new Contact("Lê Minh Châu", "0987654321", "Công việc", "Nam", false, "Đối tác dự án"));
            isInitialized = true;
        }
    }

    public static boolean isPhoneExists(String phone) {
        for (Contact c : contactList) {
            if (c.getPhone().equals(phone)) {
                return true;
            }
        }
        return false;
    }

    public static Contact findByPhone(String phone) {
        for (Contact c : contactList) {
            if (c.getPhone().equals(phone)) {
                return c;
            }
        }
        return null;
    }

    public static void deleteByPhone(String phone) {
        for (int i = 0; i < contactList.size(); i++) {
            if (contactList.get(i).getPhone().equals(phone)) {
                contactList.remove(i);
                return;
            }
        }
    }

    public static int getFavoriteCount() {
        int count = 0;
        for (Contact c : contactList) {
            if (c.isFavorite()) count++;
        }
        return count;
    }
}