package com.example.myapplication.mvx.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.mvx.R;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class RxJavaOperatorMainActivity extends AppCompatActivity {

    private  String[] greeting = {"Hello A", "Hello B" , "Hello C"};
    private Observable<Student> myObservable;
    private DisposableObserver<Student> myObserver;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_operator_main);
        myObservable = Observable.create(new ObservableOnSubscribe<Student>() {
                                             @Override
                                             public void subscribe(ObservableEmitter<Student> e) throws Exception {
                                                 ArrayList<Student> students = getStudents();
                                                 for (Student s : students){
                                                     e.onNext(s);
                                                 }
                                                 e.onComplete();
                                             }
                                         });
                compositeDisposable.add(myObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
//                        .map(new Function<Student, Student>() {
//                            @Override
//                            public Student apply(Student student) throws Exception {
//                                student.setName(student.getName().toUpperCase());
//                                return student;
//                            }
//                        })
                        .flatMap(new Function<Student, ObservableSource<Student>>() {
                            @Override
                            public ObservableSource<Student> apply(Student student) throws Exception {
                                return Observable.just(student);
                            }
                        })
                        .subscribeWith(getMyObserver())
                );
    }


    public DisposableObserver<Student> getMyObserver(){
        DisposableObserver<Student> myObservable = new DisposableObserver<Student>() {
            @Override
            public void onNext(Student value) {
                Toast.makeText(RxJavaOperatorMainActivity.this, value.getName() , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        return myObservable;
    }

    public static Intent getIntent(Context context){
        return new Intent(context, RxJavaOperatorMainActivity.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    public ArrayList<Student> getStudents(){
        Student student1 = new Student("Ar","aa@gmail.com","12");
        Student student2 = new Student("Di","da@gmail.com","14");
        Student student4 = new Student("SS","ss@gmail.com","16");
        ArrayList<Student> students = new ArrayList<>();

        students.add(student1);
        students.add(student2);
        students.add(student4);
        return  students;
    }

}
