package com.example.beauty.database;

import androidx.lifecycle.LiveData;

import com.example.beauty.WorkPhoto.PostPhoto;
import com.google.firebase.database.DataSnapshot;

import java.util.List;

public interface DataChangeInfo {

    public void dataOnDataChange(DataSnapshot snapshot);

}
