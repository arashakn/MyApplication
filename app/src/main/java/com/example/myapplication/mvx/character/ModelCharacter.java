package com.example.myapplication.mvx.character;

import com.example.myapplication.managers.charactermodel.CharManager;
import com.example.myapplication.managers.charactermodel.modelAPI.Result;

import io.reactivex.Single;

public class ModelCharacter implements  MvpCharacter.Model{
    public ModelCharacter(){
    }

    @Override
    public Single<Result> getCharacters() {
        return CharManager.getInstance().getCharacters();
    }
}
