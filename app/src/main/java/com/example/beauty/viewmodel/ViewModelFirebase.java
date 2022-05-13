package com.example.beauty.viewmodel;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.beauty.database.DataChangeInfo;
import com.example.beauty.database.DatabaseLogic;

import java.util.Objects;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class ViewModelFirebase extends ViewModel{

    @SuppressLint("StaticFieldLeak")
    private DatabaseLogic db = new DatabaseLogic();


    public void register(String email, String password, String password_confirm){

        if(Objects.equals(password, password_confirm)) {

            Observable
                    .just(db)
                    .observeOn(Schedulers.io())
                    .map(d -> {
                        d.register(email, password);
                        return d;
                    })
                    .subscribe();

        }
    }


    public void logIn(String email, String password){
        Observable
                .just(db)
                .observeOn(Schedulers.io())
                .map(d -> {
                    d.logIn(email, password);
                    return d;
                })
                .subscribe();
    }


    public void pushImageDatabase(byte[] bytePhoto, String description){
        Observable
                .just(db)
                .observeOn(Schedulers.io())
                .map(d -> {
                    d.pushImageDatabase(bytePhoto, description);
                    return d;
                })
                .subscribe();
    }



    public void getPhotoFirebase(DataChangeInfo info) {
        Observable
                .just(db)
                .observeOn(Schedulers.io())
                .map( base -> {
                    base.getPhotoFirebase(info);
                    return base;
                })
                .subscribe();
    }


    public Boolean userIsLoginAndVerification(){
        return db.checkEmailVerification();
    }


    public void resetPassword(String email){
        Observable
                .just(db)
                .observeOn(Schedulers.io())
                .map(d -> {
                    d.resetPassword(email);
                    return d;
                })
                .subscribe();
    }


    public void signOut(){
        Observable
                .just(db)
                .observeOn(Schedulers.io())
                .map(d -> {
                    d.signOut();
                    return d;
                })
                .subscribe();
    }


}
