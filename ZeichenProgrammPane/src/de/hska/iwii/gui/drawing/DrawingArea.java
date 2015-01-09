package de.hska.iwii.gui.drawing;

import javafx.scene.layout.Pane;

/**
 * Klasse DrawingArea, welche das Zeichenfeld implementiert.
 * @author Daniel Mauch
 */
public class DrawingArea extends Pane {

    private static final double LAYOUT_WIDTH = 1000.0;
    private static final double LAYOUT_HEIGHT = 800.0;

    /**
     * Konstruktor. Initialisiert das Zeichenfeld.
     */
    public DrawingArea() {

        setPrefSize(LAYOUT_WIDTH, LAYOUT_HEIGHT);
    }
}
