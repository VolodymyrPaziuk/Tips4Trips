package com.softserve.tips4trips.fragments;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.softserve.tips4trips.R;
import com.softserve.tips4trips.database.entities.Account;
import com.softserve.tips4trips.view_models.AccountProfileViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountProfileFragment extends Fragment {

    // FOR DATA
    public static final String UID_KEY = "uid";
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private AccountProfileViewModel viewModel;


    //@BindView(R.id.fragment_user_profile_image)
    //ImageView imageView;
    @BindView(R.id.fragment_user_profile_username)
    TextView username;
    @BindView(R.id.fragment_user_profile_company)
    TextView company;
    @BindView(R.id.fragment_user_profile_website)
    TextView website;

    public AccountProfileFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_user_profile, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.configureDagger();
        this.configureViewModel();
    }


    private void configureDagger(){
        AndroidSupportInjection.inject(this);
    }

    private void configureViewModel(){
        String userLogin = getArguments().getString(UID_KEY);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AccountProfileViewModel.class);
        viewModel.init(userLogin);
        viewModel.getaccount().observe(this, user -> updateUI(user));
    }


    private void updateUI(@Nullable Account account){
        if (account != null){
           // Glide.with(this).load(account.getAvatar_url()).apply(RequestOptions.circleCropTransform()).into(imageView);
            this.username.setText(account.getFirstName());
            this.company.setText(account.getLastName());
            this.website.setText(account.getAbout());
        }
    }
}
