package com.example.myapplication.mvx.charMvvm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.managers.charactermodel.CharManager;
import com.example.myapplication.managers.charactermodel.modelAPI.Item;
import com.example.myapplication.managers.charactermodel.modelAPI.Result;
import com.example.myapplication.mvx.R;
import com.example.myapplication.mvx.character.ModelCharacter;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ViewModelCharacters extends AndroidViewModel {
    private CharManager charManager;
    private MvpCharacter.View view;
    private List<Item> items;
    private MutableLiveData<Result> result = new MutableLiveData<>();
    private MutableLiveData<Boolean> resultError = new MutableLiveData<>();

    public ViewModelCharacters(@NonNull Application application ) {
        super(application);
        charManager  = new CharManager();
        result = charManager.getCharactersNoRxJava();
    }

    public MutableLiveData<Result> getResult() {
        return result;
    }
}
