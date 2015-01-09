package de.hska.iwii.gui.drawing.Shapes;

import de.hska.iwii.gui.drawing.Groups;
import javafx.scene.Node;
import javafx.scene.paint.Color;

/**
 * Klasse Line, die Linien beschreibt.
 * @author Daniel Mauch
 */
public class Line extends javafx.scene.shape.Line implements Figures {

    private double startPosX;
    private double startPosY;

    private int quadrantStart;
    private int quadrantEnd;

    private double moveStartPosX;
    private double moveStartPosY;

    private double moveEndPosX;
    private double moveEndPosY;

    /**
     * Gibt die Gruppe zurueck, in der das Objekt enthalten ist.
     * @return Groups group
     */
    public Groups getGroup() {
        return group;
    }

    /**
     * Setzt die Gruppe, in der das Objekt enthalten sein soll.
     * @param group Gruppe in der das Objekt sein soll.
     */
    public void setGroup(Groups group) {
        this.group = group;
    }

    private Groups group;

    /**
     * Zeigt an, ob das Objekt in einer Gruppe ist.
     * @return (True), falls in einer Gruppe.
     */
    public boolean isInGroup() {

        return inGroup;
    }

    /**
     * Setzt, dass das Objekt in einer Gruppe ist.
     * @param inGroup (True), falls in einer Gruppe.
     */
    public void setInGroup(boolean inGroup) {

        this.inGroup = inGroup;
    }

    private boolean inGroup = false;

    /**
     * Gibt an, ob das Objekt ausgewaehlt ist.
     * @return (True), falls selektiert.
     */
    public boolean isSelected() {

        return selected;
    }

    private boolean selected;

    /**
     * Konstruktor. Erzeugt das Objekt und belegt es mit den Initialwerten.
     * @param xPos x-Position des Mauszeigers beim Erzeugen.
     * @param yPos y-Position des Mauszeigers beim Erzeugen.
     */
    public Line(double xPos, double yPos) {

        setStartX(xPos);
        setStartY(yPos);

        setEndX(xPos);
        setEndY(yPos);

        startPosX = xPos;
        startPosY = yPos;

        setFill(Color.BLUE);

        setStroke(Color.BLACK);
        setStrokeWidth(2.0);

        setOpacity(0.9);
    }

    /**
     * Starten die Verschiebung des Objekts.
     * @param xStart x-Position des Mauszeigers beim Start der Verschiebung.
     * @param yStart y-Position des Mauszeigers beim Start der Verschiebung.
     */
    public void startMove(double xStart, double yStart) {

        if (contains(xStart, yStart)) {
            //erster Quadrant - fertig - links oben
            if (xStart <= getStartX() && yStart <= getStartY()) {

                quadrantStart = 0;
                moveStartPosX = getStartX() - xStart;
                moveStartPosY = getStartY() - yStart;
            }

            //zweiter Quadrant - links unten
            if (xStart <= getStartX() && yStart >= getStartY()) {

                quadrantStart = 1;
                moveStartPosX = getStartX() - xStart;
                moveStartPosY = yStart - getStartY();
            }

            //dritter Quadrant - rechts oben
            if (xStart > getStartX() && yStart < getStartY()) {

                quadrantStart = 2;
                moveStartPosX = xStart - getStartX();
                moveStartPosY = getStartY() - yStart;
            }

            //vierter Quadrant - fertig - rechts unten
            if (xStart >= getStartX() && yStart >= getStartY()) {

                quadrantStart = 3;
                moveStartPosX = xStart - getStartX();
                moveStartPosY = yStart - getStartY();
            }

            //erster Quadrant - Ende - links oben
            if (xStart <= getEndX() && yStart <= getEndY()) {

                quadrantEnd = 0;
                moveEndPosX = getEndX() - xStart;
                moveEndPosY = getEndY() - yStart;
            }

            //zweiter Quadrant - Ende - links unten
            if (xStart <= getEndX() && yStart >= getEndY()) {

                quadrantEnd = 1;
                moveEndPosX = getEndX() - xStart;
                moveEndPosY = yStart - getEndY();
            }

            //dritter Quadrant - Ende - rechts oben
            if (xStart > getEndX() && yStart < getEndY()) {

                quadrantEnd = 2;
                moveEndPosX = xStart - getEndX();
                moveEndPosY = getEndY() - yStart;
            }

            //vierter Quadrant - Ende - rechts unten
            if (xStart >= getEndX() && yStart >= getEndY()) {

                quadrantEnd = 3;
                moveEndPosX = xStart - getEndX();
                moveEndPosY = yStart - getEndY();
            }
        }
    }

    /**
     * Setzt das Ende des Objekts beim Erzeugen.
     * @param xEnd x-Position des Mauszeigers beim Ende der Erzeugung.
     * @param yEnd y-Position des Mauszeigers beim Ende der Erzeugung.
     */
    public void setEnd(double xEnd, double yEnd) {

        setStartX(startPosX);
        setStartY(startPosY);

        setEndX(xEnd);
        setEndY(yEnd);
    }

    /**
     * Verschiebt das Objekt um die Werte <code>double xVariable</code> und <code>double yVariable</code>.
     * @param xVariable x-Position des Mauszeigers beim Verschieben des Objekts.
     * @param yVariable y-Position des Mauszeigers beim Verschieben des Objekts.
     */
    public void move(double xVariable, double yVariable) {

        switch (quadrantStart) {
            case 0:
                setStartX(xVariable + moveStartPosX);
                setStartY(yVariable + moveStartPosY);
                break;
            case 1:
                setStartX(xVariable + moveStartPosX);
                setStartY(yVariable - moveStartPosY);
                break;
            case 2:
                setStartX(xVariable - moveStartPosX);
                setStartY(yVariable + moveStartPosY);
                break;
            case 3:
                setStartX(xVariable - moveStartPosX);
                setStartY(yVariable - moveStartPosY);
                break;
            default:
                break;
        }

        switch (quadrantEnd) {
            case 0:
                setEndX(xVariable + moveEndPosX);
                setEndY(yVariable + moveEndPosY);
                break;
            case 1:
                setEndX(xVariable + moveEndPosX);
                setEndY(yVariable - moveEndPosY);
                break;
            case 2:
                setEndX(xVariable - moveEndPosX);
                setEndY(yVariable + moveEndPosY);
                break;
            case 3:
                setEndX(xVariable - moveEndPosX);
                setEndY(yVariable - moveEndPosY);
                break;
            default:
                break;
        }
    }

    /**
     * Beendet die Verschiebung des Objektes an den Punkten <code>double xEnd</code> und <code>double yEnd</code>.
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
     * Setzt das Attribut, welches die Selektion anzeigt.
     * @param select (True), falls es selektiert sein soll.
     */
    public void select(boolean select) {

        if (select) {
            selected = true;
            setStrokeWidth(5.0);
        }
        else {
            selected = false;
            setStrokeWidth(2.0);
        }
    }

    /**
     * Erzeugt ein identisches Abbild des Objekts und gibt es zurueck.
     * @return Geklontes Objekt
     */
    public Node cloneObject() {
        Line line = new Line(0.0, 0.0);

        line.setStartX(getStartX());
        line.setStartY(getStartY());

        line.setEndX(getEndX());
        line.setEndY(getEndY());

        if (inGroup) {
            line.setInGroup(true);
        }
        return line;
    }
}
