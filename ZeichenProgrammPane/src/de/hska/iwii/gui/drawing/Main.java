package de.hska.iwii.gui.drawing;

import javafx.application.Application;

/**
 * Informatik 2 - HS Karlsruhe - Technik und Wirtschaft.
 * Fakultät IWI - Informatik Bachelor
 * @author Daniel Mauch
 */
public class Main {

    /**
     * MainMethode aus welcher das Programm gestartet wird.
     * @param args Argumente
     */
    public static void main(String[] args) {

        MainWindow.setHorizontalToolBar(false);

        DrawingArea panel = new DrawingArea();
        EventHandling listener = new EventHandling(panel);

        MainWindow.setMainPanel(panel);
        MainWindow.setDrawingListener(listener);

        Application.launch(MainWindow.class, args);
    }
}
