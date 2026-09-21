package com.example.listak;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class ListaController {
    @FXML
    private Label catCounterText;
    @FXML
    private Label mushroomCounterText;
    @FXML
    private Label birdCounterText;

    @FXML
    private Label totalCounterText;

    @FXML
    private ListView<String> leftList;
    @FXML
    private ListView<String> rightList;


    private ArrayList<String> leftListContent = new ArrayList<>();
    private ArrayList<String> rightListContent = new ArrayList<>();
    private int catCounter = 0;
    private int mushroomCounter = 0;
    private int birdCounter = 0;


    @FXML
    protected void onCatClick() {
        catCounter++;
        leftListContent.add("Kitty");
        update_view();
    }
    @FXML
    protected void onMushroomClick() {
        mushroomCounter++;
        leftListContent.add("Gomba");
        update_view();
    }
    @FXML
    protected void onBirdClick() {
        birdCounter++;
        leftListContent.add("Madár");
        update_view();
    }

    @FXML
    protected void onAddClick() {
        int selected = leftList.getSelectionModel().getSelectedIndex();
        if (selected < 0) return;

        String content = leftListContent.get(selected);
        rightListContent.add(content);
        leftListContent.remove(selected);
        switch (content) {
            case "Kitty":
                catCounter--;
                break;
            case "Gomba":
                mushroomCounter--;
                break;
            case "Madár":
                birdCounter--;
                break;
        }
        update_view();
    }
    @FXML
    protected void onDelClick() {
        int selected = rightList.getSelectionModel().getSelectedIndex();
        if (selected < 0) return;

        rightListContent.remove(selected);
        update_view();
    }

    @FXML
    protected void onLeftTrashClick() {
        catCounter = 0;
        mushroomCounter = 0;
        birdCounter = 0;
        leftListContent.clear();
        update_view();
    }

    @FXML
    protected void onRightTrashClick() {
        rightListContent.clear();
        update_view();
    }

    @FXML
    protected void onSaveClick() {

    }


    protected void update_view() {
        leftList.setItems(FXCollections.observableArrayList(leftListContent));
        rightList.setItems(FXCollections.observableArrayList(rightListContent));

        catCounterText.setText(String.valueOf(catCounter));
        mushroomCounterText.setText(String.valueOf(mushroomCounter));
        birdCounterText.setText(String.valueOf(birdCounter));

        totalCounterText.setText("%s / %s elem van a listákban".formatted(leftListContent.size(), rightListContent.size()));
    }
}