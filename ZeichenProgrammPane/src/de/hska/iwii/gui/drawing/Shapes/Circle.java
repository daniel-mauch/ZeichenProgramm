package de.hska.iwii.gui.drawing.Shapes;

import de.hska.iwii.gui.drawing.Groups;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

/**
 * Klasse Circle, die Ellipsen beschreibt.
 * @author Daniel Mauch
 */
public class Circle extends Ellipse implements Figures {

    private int quadrant;

    /**
     * Gibt an, ob das Objekt im Moment selektiert ist.
     * @return selected (true if selected)
     */
    public boolean isSelected() {

        return selected;
    }

    /**
     *
     */
    private boolean selected = false;

    /**
     * Gibt an, ob sich das Element im Moment in einer Gruppe befindet.
     * @return inGroup
     */
    public boolean isInGroup() {

        return inGroup;
    }

    /**
     *  Gibt zurueck, ob sich das Objekt in einer Gruppe befindet oder nicht.
     *  * @param inGroup (true, falls in einer Gruppe)
     */
    public void setInGroup(boolean inGroup) {

        this.inGroup = inGroup;
    }

    /**
     *
     */
    private boolean inGroup = false;

    /**
     * Gibt die Gruppe zurück, in der sich das Objekt befindet.
     * @return group
     */
    public Groups getGroup() {

        return group;
    }

    /**
     * Setzt die Gruppe, in der sich das Objekt befinden soll.
     * @param group (Gruppe, in der das Element enthalten ist.)
     */
    public void setGroup(Groups group) {

        this.group = group;
    }

    private Groups group;

    private double startPosX;

    private double startPosY;

    private double movePosX;
    private double movePosY;

    private double height = 1.0;
    private double width = 1.0;

    /**
     * Konstruktor der Ellipse.
     * @param xPos  X-Position des Mauszeigers beim erstellen des Objekts
     * @param yPos  Y-Position des Mauszeigers beim erstellen des Objekts
     */
    public Circle(double xPos, double yPos) {

        startPosX = xPos;
        startPosY = yPos;

        setCenterY(yPos);
        setCenterX(xPos);

        setRadiusX(1.0);
        setRadiusY(1.0);

        setStroke(Color.BLACK);
        setStrokeWidth(0.0);

        setFill(Color.YELLOW);

        setOpacity(0.9);
    }

    /**
     * Startet das Verschieben des Objekts. Initialisiert die Parameter, die zur Verschiebung notwendig sind.
     * @param xStart  X-Position des Mauszeigers beim Start der Verschiebung des Objekts
     * @param yStart  Y-Position des Mauszeigers beim Start der Verschiebung des Objekts
     */
    public void startMove(double xStart, double yStart) {

        if (contains(xStart, yStart)) {

            //erster Quadrant - fertig - links oben
            if (xStart <= startPosX && yStart <= startPosY) {

                quadrant = 0;

                movePosX = getCenterX() - xStart;
                movePosY = getCenterY() - yStart;
            }

            //zweiter Quadrant - links unten
            if (xStart <= startPosX && yStart >= startPosY) {

                quadrant = 1;

                movePosX = getCenterX() - xStart;
                movePosY = yStart - getCenterY();
            }

            //dritter Quadrant - rechts oben
            if (xStart > startPosX && yStart < startPosY) {

                quadrant = 2;

                movePosX = xStart - getCenterX();
                movePosY = getLayoutY() - yStart;
            }

            //vierter Quadrant - fertig - rechts unten
            if (xStart >= startPosX && yStart >= startPosY) {

                quadrant = 3;

                movePosX = xStart - getCenterX();
                movePosY = yStart - getCenterY();
            }
        }
    }

    /**
     * Berechnet mittels der Mauszeigerposition die Form der Ellipse.
     * @param xEnd X-Position des Mauszeigers.
     * @param yEnd Y-Position des Mauszeigers.
     */
    public void setEnd(double xEnd, double yEnd) {

        //erster Quadrant - fertig - links oben
        if (xEnd <= startPosX && yEnd <= startPosY) {
            height = startPosY - yEnd;
            width = startPosX - xEnd;

            setRadiusX(width / 2.0);
            setRadiusY(height / 2.0);

            setCenterX(startPosX - (width / 2.0));
            setCenterY(startPosY - (height / 2.0));
        }

        //zweiter Quadrant - links unten
        if (xEnd <= startPosX && yEnd >= startPosY) {
            height = yEnd - startPosY;
            width = startPosX - xEnd;

            setCenterY(startPosY + (height / 2.0));
            setCenterX(startPosX - (width / 2.0));

            setRadiusX(width / 2.0);
            setRadiusY(height / 2.0);
        }

        //dritter Quadrant - rechts oben
        if (xEnd > startPosX && yEnd < startPosY) {
            height = startPosY - yEnd;
            width = xEnd - startPosX;

            setCenterY(startPosY - (height / 2.0));
            setCenterX(startPosX + (width / 2.0));

            setRadiusX(width / 2.0);
            setRadiusY(height / 2.0);
        }

        //vierter Quadrant - fertig - rechts unten
        if (xEnd >= startPosX && yEnd >= startPosY) {
            height = yEnd - startPosY;
            width = xEnd - startPosX;

            setCenterX(startPosX + (height / 2.0));
            setCenterY(startPosY + (width / 2.0));

            setRadiusX(width / 2.0);
            setRadiusY(height / 2.0);
        }
    }

    /**
     * Verschiebt das Objekt entlang des Mauszeigers.
     * @param xVariable x-Position des Mauszeigers.
     * @param yVariable y-Position des Mauszeigers.
     */
    public void move(double xVariable, double yVariable) {

        switch (quadrant) {
            case 0:
                setCenterX(xVariable + movePosX);
                setCenterY(yVariable + movePosY);
                break;
            case 1:
                setCenterX(xVariable - movePosX);
                setCenterY(yVariable + movePosY);
                break;
            case 2:
                setCenterX(xVariable + movePosX);
                setCenterY(yVariable - movePosY);
                break;
            case 3:
                setCenterX(xVariable - movePosX);
                setCenterY(yVariable - movePosY);
                break;
            default:
                break;
        }
    }

    /**
     * Beendet die Verschiebung des Objekts.
     * @param xEnd x-Position des Mauszeigers beim Ende der Verschiebung.
     * @param yEnd y-Position des Mauszeigers beim Ende der Verschiebung.
     */
    public void endMove(double xEnd, double yEnd) {

    }

    /**
     * Rotiert das Objekt um den Winkel <code> double angle</code>.
     * @param node Das gewaehlte Objekt.
     * @param angle Der Winkel um den rotiert werden soll.
     */
    public void rotate(Node node, double angle) {

    }

    /**
     *
     */
    public void endFigure() {

    }

    /**
     * Entscheidet anhand des Uebergabeparameters <code>boolean select</code>, ob das Objekt selektiert oder deselektiert
     * werden soll.
     * @param select Beim Aufruf wird bestimmt, ob das Objekt selektiert oder deselektiert werden soll.
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
     * Klont das Element und kopiert die Attribute in das neue Objekt.
     * @return Objekt, das geklont wurde.
     */
    public Node cloneObject() {

        Circle circle = new Circle(0.0, 0.0);

        circle.setCenterX(Double.valueOf(getCenterX()));
        circle.setCenterY(Double.valueOf(getCenterY()));

        circle.setRadiusX(Double.valueOf(getRadiusX()));
        circle.setRadiusY(Double.valueOf(getRadiusY()));

        if (inGroup) {
            circle.setInGroup(true);
        }

        return circle;
    }
}
