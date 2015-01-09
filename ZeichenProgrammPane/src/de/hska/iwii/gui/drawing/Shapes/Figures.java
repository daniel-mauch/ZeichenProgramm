package de.hska.iwii.gui.drawing.Shapes;

import de.hska.iwii.gui.drawing.Groups;
import javafx.scene.Node;

/**
 * Das Interface Figures beschreibt saemtliche Shapes.
 * @author Daniel Mauch
 */
public interface Figures {

    /**
     * Gibt die Gruppe zurueck, in der das Objekt enthalten ist.
     * @return Groups group
     */
    Groups getGroup();

    /**
     * Setzt die Gruppe in der das Objekt enthalten sein soll.
     * @param group Gruppe in der das Objekt sein soll.
     */
    void setGroup(Groups group);

    /**
     * Gibt zurueck, ob das Objekt Element einer Gruppe ist.
     * @return (True), falls in einer Gruppe.
     */
    boolean isInGroup();

    /**
     * Setzt, dass das Objekt in einer Gruppe ist.
     * @param inGroup (True), falls in einer Gruppe.
     */
    void setInGroup(boolean inGroup);

    /**
    * Die Figur an der mit <code>pos</code> gekennzeichneten Stelle
    * soll verschoben werden.
    * @param xStart X-Position des Mauszeigers waehrend des Klicks.
    * @param yStart y-Position des Mauszeigers waehrend des Klicks.
    */
    void startMove(double xStart, double yStart);


    /**
     * Der Mauszeiger wird mit gedrueckter Maustaste ueber die
     * Zeichenflaeche bewegt. Dabei wird die neu mit
     * <code>startCreateFigure</code> erzeugte Figur in der Groesse
     * veraendert.
     * @param xEnd X-Position des Mauszeigers waehrend des Verschiebens.
     * @param yEnd y-Position des Mauszeigers waehrend des Verschiebens.
     */
    void setEnd(double xEnd, double yEnd);

    /**
     * Der Mauszeiger wird mit gedrueckter Maustaste ueber die
     * Zeichenflaeche bewegt. Dabei wird eine mit
     * <code>startMoveFigure</code> gewaehlte Figur verschoben.
     * @param xVariable X-Position des Mauszeigers waehrend des Verschiebens.
     * @param yVariable y-Position des Mauszeigers waehrend des Verschiebens.
     */
    void move(double xVariable, double yVariable);


    /**
     * Der Mauszeiger wird wieder losgelassen. Das Verschieben
     * der Figur ist somit beendet.
     * @param xEnd X-Position des Mauszeigers nach Loslassen der Maustaste.
     * @param yEnd y-Position des Mauszeigers nach Loslassen der Maustaste.
     */
    void endMove(double xEnd, double yEnd);

    /**
     * Rotiert das Objekt <code>Node node</code> um den Winkel <code>double angle</code>.
     * @param node Objekt, das gedreht werden soll.
     * @param angle Winkel um den gedreht werden soll.
     */
    void rotate(Node node, double angle);

    /**
     * Beendet das Objekt.
     */
    void endFigure();

    /**
     * Gibt an, ob das Objekt selektiert ist.
     * @return (True), falls selektiert.
     */
    boolean isSelected();

    /**
     * Erstellt ein identisches Abbild des Objekts und gibt es zurueck.
     * @return Identisches Abbild des Objekts.
     */
    Node cloneObject();

    /**
     * Zeigt das Objekt als Selektiert an.
     * @param select (True), falls es selektiert sein soll.
     */
    void select(boolean select);
}
