package de.hska.iwii.gui.drawing.Shapes;

import de.hska.iwii.gui.drawing.Groups;
import javafx.scene.Node;
import javafx.scene.paint.Color;

/**
 * Klasse Rectangle, die Rechtecke beschreibt.
 * @author Daniel Mauch
 */
public class Rectangle extends javafx.scene.shape.Rectangle implements Figures {

    private double startPosX;
    private double startPosY;

    private double movePosX;
    private double movePosY;

    private double height = 1.0;
    private double width = 1.0;

    /**
     * Gibt die Gruppe zurueck, in der das Objekt ist.
     * @return Gruppe, in der das Objekt liegt.
     */
    public Groups getGroup() {

        return group;
    }

    /**
     * Setzt das Gruppenattribut, welches anzeigt, in welcher Gruppe das Objekt liegt.
     * @param group Gruppe in der das Objekt sein soll.
     */
    public void setGroup(Groups group) {

        this.group = group;
    }

    private Groups group;

    /**
     * Gibt an, ob das Objekt Teil einer Gruppe ist.
     * @return (True), falls in einer Gruppe.
     */
    public boolean isInGroup() {

        return inGroup;
    }

    /**
     * Setzt das Attribut, das anzeigt, ob das Objekt Teil einer Gruppe ist.
     * @param inGroup (True), falls in einer Gruppe.
     */
    public void setInGroup(boolean inGroup) {

        this.inGroup = inGroup;
    }

    private boolean inGroup = false;

    /**
     * Gibt an, ob das Objekt selektiert ist.
     * @return (True), falls selektiert.
     */
    public boolean isSelected() {

        return selected;
    }

    private boolean selected;

    /**
     * Konstruktor. Erzeugt das Objekt und setzt die Initialwerte.
     * @param xPos x-Position des Mauszeigers beim Erstellen des Objekts.
     * @param yPos y-Position des Mauszeigers beim Erstellen des Objekts.
     */
    public Rectangle(double xPos, double yPos) {

        startPosX = xPos;
        startPosY = yPos;

        setX(startPosX);
        setY(startPosY);

        setHeight(1.0);
        setWidth(1.0);

        setStroke(Color.BLACK);
        setStrokeWidth(0.0);

        setFill(Color.RED);

        setOpacity(0.9);
    }

    /**
     * Startet das Verschieben des Objekts.
     * @param xStart x-Position des Mauszeigers beim Start des Verschiebens.
     * @param yStart y-Position des Mauszeigers beim Start des Verschiebens.
     */
    public void startMove(double xStart, double yStart) {

        if (contains(xStart, yStart)) {
            movePosX = xStart - getX();
            movePosY = yStart - getY();
        }
    }

    /**
     * Setzt das Ende beim Erstellen des Objekts.
     * @param xEnd x-Position des Mauszeigers beim Ende der Erstellung.
     * @param yEnd y-Position des Mauszeigers beim Ende der Erstellung.
     */
    public void setEnd(double xEnd, double yEnd) {

        //erster Quadrant - fertig - links oben
        if (xEnd <= startPosX && yEnd <= startPosY) {
            height = startPosY - yEnd;
            width = startPosX - xEnd;

            setX(xEnd);
            setHeight(height);

            setY(yEnd);
            setWidth(width);
        }

        //zweiter Quadrant - links unten
        if (xEnd <= startPosX && yEnd >= startPosY) {
            height = yEnd - startPosY;
            width = startPosX - xEnd;

            setX(xEnd);
            setY(startPosY);

            setWidth(width);
            setHeight(height);
        }

        //dritter Quadrant - rechts oben
        if (xEnd > startPosX && yEnd < startPosY) {
            height = startPosY - yEnd;
            width = xEnd - startPosX;

            setX(startPosX);
            setY(yEnd);

            setHeight(height);
            setWidth(width);
        }

        //vierter Quadrant - fertig - rechts unten
        if (xEnd >= startPosX && yEnd >= startPosY) {
            height = yEnd - startPosY;
            width = xEnd - startPosX;

            setHeight(height);
            setWidth(width);
        }
    }

    /**
     * Verschiebt das Objekt anhand der Variablen <code>double xVariable</code> und <code>double yVariable</code>.
     * @param xVariable x-Position des Mauszeigers beim Verschieben des Objekts.
     * @param yVariable y-Position des Mauszeigers beim Verschieben des Objekts.
     */
    public void move(double xVariable, double yVariable) {

        setX(xVariable - movePosX);
        setY(yVariable - movePosY);
    }

    /**
     * Beendet die Verschiebung des Objekts.
     * @param xEnd x-Position des Mauszeigers beim Ende der Verschiebung.
     * @param yEnd y-Position des Mauszeigers beim Ende der Verschiebung.
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
     *
     */
    public void endFigure() {

    }

    /**
     * Setzt, ob das Objekt Teil einer Gruppe ist.
     * @param select (True), falls es selektiert sein soll.
     */
    public void select(boolean select) {

        if (select) {
            selected = true;
            setStrokeWidth(3.0);
        }
        else {
            selected = false;
            setStrokeWidth(0.0);
        }
    }

    /**
     * Erzeugt ein identisches Abbild vom Objekt, initialisiert es und gibt es zurueck.
     * @return Identisches Abbild des Objekts
     */
    public Node cloneObject() {

        Rectangle rect = new Rectangle(0.0, 0.0);

        rect.setHeight(getHeight());
        rect.setWidth(getWidth());

        rect.setX(getX());
        rect.setY(getY());

        if (inGroup) {
            rect.setInGroup(true);
        }

        return rect;
    }
}
