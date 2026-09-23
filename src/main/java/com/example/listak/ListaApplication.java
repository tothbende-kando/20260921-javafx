package com.example.listak;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class ListaApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ListaApplication.class.getResource("lista-view.fxml"));
        Scene scene = null;
        if (!ListaTest.isRunningTest) scene = new Scene(fxmlLoader.load(), 960, 540);
        if (!ListaTest.isRunningTest) stage.setTitle("Listák");
        if (!ListaTest.isRunningTest) stage.getIcons().add(new Image("file:icons/kitty.png"));
        if (!ListaTest.isRunningTest) stage.setResizable(false);
        if (!ListaTest.isRunningTest) stage.setScene(scene);
        if (!ListaTest.isRunningTest) stage.show();
    }

    public static void main(String[] args) {
        if (!ListaTest.isRunningTest) launch();
    }
}