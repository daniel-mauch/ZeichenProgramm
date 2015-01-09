package de.hska.iwii.gui.drawing;

import de.hska.iwii.gui.drawing.Shapes.Figures;
import javafx.scene.Group;
import javafx.scene.Node;

import java.util.ArrayList;

/**
 * Klasse, welche eine Gruppe von Figuren oder anderen Gruppen beschreibt.
 * @author Daniel Mauch
 */
public class Groups extends Group implements Figures {

    /**
     * Gibt an, ob das Objekt selektiert ist.
     * @return (True), falls selektiert.
     */
    public boolean isSelected() {
        return selected;
    }

    private boolean selected;

    /**
     * Gibt an, ob das Objekt in einer Gruppe ist.
     * @return (True), falls in einer Gruppe.
     */
    public boolean isInGroup() {

        return inGroup;
    }

    /**
     * Setzt, ob das Objekt in einer Gruppe ist oder nicht.
     * @param inGroup (True), falls in einer Gruppe.
     */
    public void setInGroup(boolean inGroup) {

        this.inGroup = inGroup;
    }

    private boolean inGroup = false;

    /**
     * Gibt die Gruppe zurueck, in der das Objekt enthalten ist.
     * @return Gruppe
     */
    public Groups getGroup() {

        return group;
    }

    /**
     * Setzt die Gruppe in der das Objekt enthalten ist.
     * @param group Gruppe in der das Objekt sein soll.
     */
    public void setGroup(Groups group) {

        this.group = group;
    }

    private Groups group;

    private int quadrant;

    private double movePosX;
    private double movePosY;

    /**
     * Konstruktor. Initialisiert die Gruppe direkt mit der übergebenen Liste.
     * @param list Liste von Objekten, die in der Gruppe enthalten sein sollen.
     */
    public Groups(ArrayList<Node> list) {

        if (list != null) {
            for (Node node : list) {
                ((Figures) node).select(false);
            }
            selected = false;
            getChildren().addAll(list);
        }
    }

    /**
     * Startet die Verschiebung des Objekts.
     * @param xStart X-Position des Mauszeigers waehrend des Klicks.
     * @param yStart y-Position des Mauszeigers waehrend des Klicks.
     */
    public void startMove(double xStart, double yStart) {

        if (contains(xStart, yStart)) {
            //erster Quadrant - fertig - links oben
            if (getLayoutX() <= xStart && yStart <= getLayoutY()) {
                quadrant = 0;

                movePosX = getLayoutX() - xStart;
                movePosY = getLayoutY() - yStart;
            }

            //zweiter Quadrant - links unten
            if (xStart <= getLayoutX() && yStart >= getLayoutY()) {
                quadrant = 1;

                movePosX = getLayoutX() - xStart;
                movePosY = yStart - getLayoutY();
            }

            //dritter Quadrant - rechts oben
            if (xStart > getLayoutX() && yStart < getLayoutY()) {
                quadrant = 2;

                movePosX = xStart - getLayoutX();
                movePosY = getLayoutY() - yStart;
            }

            //vierter Quadrant - fertig - rechts unten
            if (xStart >= getLayoutX() && yStart >= getLayoutY()) {
                quadrant = 3;

                movePosX = xStart - getLayoutX();
                movePosY = yStart - getLayoutY();
            }
        }
    }

    /**
     * Setzt das Ende des Objekts bei der Erzeugung.
     * @param xEnd X-Position des Mauszeigers waehrend des Verschiebens.
     * @param yEnd y-Position des Mauszeigers waehrend des Verschiebens.
     */
    public void setEnd(double xEnd, double yEnd) {

    }

    /**
     * Verschiebt das Objekt in Abbhaengigkeit der X- und Y-Position des Mauszeigers.
     * @param xVariable X-Position des Mauszeigers waehrend des Verschiebens.
     * @param yVariable y-Position des Mauszeigers waehrend des Verschiebens.
     */
    public void move(double xVariable, double yVariable) {

        switch (quadrant) {
            case 0:
                setTranslateX(xVariable + movePosX);
                setTranslateY(yVariable + movePosY);
                break;
            case 1:
                setTranslateX(xVariable - movePosX);
                setTranslateY(yVariable + movePosY);
                break;
            case 2:
                setTranslateX(xVariable + movePosX);
                setTranslateY(yVariable - movePosY);
                break;
            case 3:
                setTranslateX(xVariable - movePosX);
                setTranslateY(yVariable - movePosY);
                break;
            default: break;
        }
    }

    /**
     * Beendet die Verschiebung des Objekts.
     * @param xEnd X-Position des Mauszeigers nach Loslassen der Maustaste.
     * @param yEnd y-Position des Mauszeigers nach Loslassen der Maustaste.
     */
    public void endMove(double xEnd, double yEnd) {

    }

    /**
     * Rotiert das Objekt <code>Node node</code> um den Winkel <code>double angle</code>.
     * @param node Objekt, das gedreht werden soll.
     * @param angle Winkel um den gedreht werden soll.
     */
    public void rotate(Node node, double angle) {

    }

    /**
     * Beendet die Figur.
     */
    public void endFigure() {

    }

    /**
     * Selektiert das Objekt.
     * @param select (True), falls es selektiert sein soll.
     */
    public void select(boolean select) {

        for (Node node: getChildren()) {
            ((Figures) node).select(false);
        }

        if (select) {
            selected = true;
            for (Node node: getChildren()) {
                ((Figures) node).select(true);
            }
        }
        else {
            selected = false;
            for (Node node: getChildren()) {
                ((Figures) node).select(false);
            }
        }
    }

    /**
     * Erzeugt ein identisches Abbild des Objekts, initialisiert es und gibt es zurueck.
     * @return Identisches Abbild des Objekts.
     */
    public Node cloneObject() {

        Groups group = new Groups(null);

        group.setLayoutX(getLayoutX());
        group.setLayoutY(getLayoutY());

        group.setScaleX(getScaleX());
        group.setScaleY(getScaleY());

        for (Node node: getChildren()) {
            if (node instanceof Figures) {
                Node figure = ((Figures) node).cloneObject();
                ((Figures) figure).setGroup(group);
                group.getChildren().add(figure);
            }
        }

        if (inGroup) {
            group.setInGroup(true);
        }

        return group;
    }
}
