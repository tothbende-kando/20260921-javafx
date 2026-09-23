package com.example.listak;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
    protected void initialize() {
        if (!Files.exists(Path.of(ListaTest.isRunningTest ? "test.txt" : "listak.txt"))) {
            System.out.println("Nincs");
            return;
        }
        try {
            parse_file(Files.readString(Path.of(ListaTest.isRunningTest ? "test.txt" : "listak.txt")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


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
        int selected = ListaTest.isRunningTest ? 0 : leftList.getSelectionModel().getSelectedIndex();
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
        int selected = ListaTest.isRunningTest ? 0 : rightList.getSelectionModel().getSelectedIndex();
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
        try {
            FileWriter writer = new FileWriter(ListaTest.isRunningTest ? "test.txt" : "listak.txt");
            writer.write(construct_save_string());
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    protected void update_view() {
        if (!ListaTest.isRunningTest) leftList.setItems(FXCollections.observableArrayList(leftListContent));
        if (!ListaTest.isRunningTest) rightList.setItems(FXCollections.observableArrayList(rightListContent));

        if (!ListaTest.isRunningTest) catCounterText.setText(String.valueOf(catCounter));
        if (!ListaTest.isRunningTest) mushroomCounterText.setText(String.valueOf(mushroomCounter));
        if (!ListaTest.isRunningTest) birdCounterText.setText(String.valueOf(birdCounter));

        if (!ListaTest.isRunningTest) totalCounterText.setText("%s / %s elem van a listákban".formatted(leftListContent.size(), rightListContent.size()));
    }


    protected String construct_save_string() {
        String str = "";

        for (String a : leftListContent) {
            str += a + "\n";
        }

        str = str + "\n";

        for (String b : rightListContent) {
            str += b + "\n";
        }
        return str;
    }


    protected void parse_file(String raw_file) {
        String[] rows = raw_file.split("\n");
        boolean is_second_list = false;

        for (String row : rows) {
            if (row.isEmpty()) {
                is_second_list = true;
                continue;
            }
            row = row.strip();
            if (is_second_list) rightListContent.add(row);
            else leftListContent.add(row);
        }
        update_view();
    }
}