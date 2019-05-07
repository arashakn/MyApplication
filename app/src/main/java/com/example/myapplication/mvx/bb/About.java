package com.example.myapplication.mvx.bb;

/**
 * Created by Backbase R&D B.V on 28/06/2018.
 * Mvp contract for AboutActivity
 */

public interface About {

    interface Model {
        void getAboutInfo();
    }

    interface Presenter {
        void getAboutInfo();
        void onSuccess(AboutInfo aboutInfo);
        void onFail();
    }

    interface View {
        void setCompanyName(String companyName);
        void setCompanyAddress(String companyAddress);
        void setCompanyPostalCode(String postalCode);
        void setCompanyCity(String companyCity);
        void setAboutInfo(String info);
        void showError();
        void showProgress();
        void hideProgress();
    }
}
