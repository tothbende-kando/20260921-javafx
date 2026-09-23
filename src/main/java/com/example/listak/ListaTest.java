package com.example.listak;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class ListaTest {
    public static boolean isRunningTest = false;

    @Before
    public void before() {
        isRunningTest = true;
    }

    @After
    public void after() {
        isRunningTest = false;
    }

    @Test
    public void testMain() {
        ListaApplication.main(null);
    }

    @Test
    public void testStart() {
        ListaApplication app = new ListaApplication();
        try {
            app.start(null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testController() {
        ListaController cont = new ListaController();

        cont.initialize();
        cont.onCatClick();
        cont.onBirdClick();
        cont.onMushroomClick();
        cont.onAddClick();
        cont.onAddClick();
        cont.onDelClick();
        cont.onLeftTrashClick();
        cont.onRightTrashClick();
        cont.onSaveClick();
    }
}
