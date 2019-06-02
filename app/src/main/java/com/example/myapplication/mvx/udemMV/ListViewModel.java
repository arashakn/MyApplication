package com.example.myapplication.mvx.udemMV;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.managers.charactermodel.CharManager;
import com.example.myapplication.managers.charactermodel.modelAPI.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListViewModel extends ViewModel {
    private final MutableLiveData<Result> repos = new MutableLiveData<>();
    private final MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private Call<Result> repoCall;

    public MutableLiveData<Result> getRepos() {
        return repos;
    }

    public MutableLiveData<Boolean> getRepoLoadError() {
        return repoLoadError;
    }

    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }


    public ListViewModel() {
        fetchRepos();
    }

    private void fetchRepos() {
        repoCall = CharManager.getInstance().getCharApi().getCharactersCall();
        loading.setValue(true);

        final MutableLiveData<Result> data = new MutableLiveData<>();
        CharManager.getInstance().getCharApi().getCharactersCall().enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    repoLoadError.setValue(false);
                    repos.setValue(response.body());// really important -->response.body()
                    loading.setValue(false);
                    repoCall = null;
                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {
                    repoLoadError.setValue(true);
                    loading.setValue(true);
                    data.setValue(null);
                    repoCall = null;
                }
            });

    }

    @Override
    protected void onCleared() {
        if(repoCall !=null){
            repoCall.cancel();
            repoCall = null;
        }
        super.onCleared();
    }
}
