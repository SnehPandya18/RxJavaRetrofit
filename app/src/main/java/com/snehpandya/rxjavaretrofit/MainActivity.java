package com.snehpandya.rxjavaretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.snehpandya.rxjavaretrofit.apiservice.RetrofitService;
import com.snehpandya.rxjavaretrofit.model.Response;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getMovies();
    }

    private void getMovies() {
        RetrofitService retrofitService = new RetrofitService();
        Observable<Response> responseObservable = retrofitService.mTMDBApi.getResponse("YOUR_API_KEY", 1);
        responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::responseData, this::handleError);
    }

    private void responseData(Response response) {
        Log.d("TAG", "responseData: " + response.toString());
    }

    private void handleError(Throwable throwable) {
        Log.d("TAG", "handleError: " + throwable);
    }
}
