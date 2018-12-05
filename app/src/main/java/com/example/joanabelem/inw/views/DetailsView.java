package com.example.joanabelem.inw.views;

import com.example.joanabelem.inw.models.User;

import java.util.List;

public interface DetailsView {

    void hideProgress();
    void showMessage(String message);
    void createTable(List<User> followers);
}
