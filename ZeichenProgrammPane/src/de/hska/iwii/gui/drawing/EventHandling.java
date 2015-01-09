package de.hska.iwii.gui.drawing;

import de.hska.iwii.gui.drawing.Shapes.Circle;
import de.hska.iwii.gui.drawing.Shapes.Figures;
import de.hska.iwii.gui.drawing.Shapes.Line;
import de.hska.iwii.gui.drawing.Shapes.Rectangle;
import javafx.scene.Node;
import java.util.ArrayList;

/**
 * Klasse EventHandling, welche saemtliche Funktionen des Zeichenfeld implementiert.
 * @author Daniel Mauch
 */
public class EventHandling implements DrawingListener {
    private Figures temp;
    private DrawingArea panel;
    private ArrayList<Node> selected = new ArrayList<>();
    private ArrayList<Node> copied = new ArrayList<>();

    /**
     * Konstruktor des Listeners.
     * @param panel Das Zeichenfeld, auf das gezeichnet werden soll.
     */
    public EventHandling(DrawingArea panel) {

        this.panel = panel;
    }

    /**
     * @param figureType Zu erzeugende Figur:
     *                   <ul>
     *                     <li><code>"circle"</code> Es soll ein neuer Kreis erzeugt werden.
     *                     <li><code>"rect"</code> Es soll ein neues Rechteck erzeugt werden.
     *                     <li><code>"line"</code> Es soll eine neue Linie erzeugt werden.
     *                   </ul>
     * @param xPos X-Position des Mauszeigers waehrend des Klicks.
     * @param yPos y-Position des Mauszeigers waehrend des Klicks.
     */
    public void startCreateFigure(String figureType, double xPos, double yPos) {

        if (figureType.equals("ellipse")) {
            temp = new Circle(xPos, yPos);
            panel.getChildren().add((Node) temp);
        }

        if (figureType.equals("rectangle")) {
            temp = new Rectangle(xPos, yPos);
            panel.getChildren().add((Node) temp);
        }

        if (figureType.equals("line")) {
            temp = new Line(xPos, yPos);
            panel.getChildren().add((Node) temp);
        }
    }

    /**
     * Startet das Verschieben des ausgewaehlten Objekts <code>Node node</code>.
     * @param node Zu verschiebende Figure.
     * @param xPos X-Position des Mauszeigers waehrend des Klicks.
     * @param yPos y-Position des Mauszeigers waehrend des Klicks.
     */
    public void startMoveFigure(Node node, double xPos, double yPos) {

        if (node instanceof Figures) {
            if (((Figures) node).isInGroup()) {
                ((Figures) node).getGroup().startMove(xPos, yPos);
            }
            else {
                ((Figures) node).startMove(xPos, yPos);
            }
        }
    }

    /**
     * Setzt das Ende des zu erstellenden Objekts.
     * @param xPos X-Position des Mauszeigers waehrend des Verschiebens.
     * @param yPos y-Position des Mauszeigers waehrend des Verschiebens.
     */
    public void workCreateFigure(double xPos, double yPos) {

        temp.setEnd(xPos, yPos);
    }

    /**
     * Verschiebt das ausgewaehlte Objekt <code>Node node</code> entlang des Mauszeigers.
     * @param node Zu verschiebende Figure.
     * @param xPos X-Position des Mauszeigers waehrend des Verschiebens.
     * @param yPos y-Position des Mauszeigers waehrend des Verschiebens.
     */
    public void workMoveFigure(Node node, double xPos, double yPos) {

        if (node instanceof Figures) {
            if (((Figures) node).isInGroup()) {
                ((Figures) node).getGroup().move(xPos, yPos);
            }
            else {
                ((Figures) node).move(xPos, yPos);
            }
        }
    }

    /**
     * Beendet das Erstellen des gewaehlten Objekts.
     * @param xPos X-Position des Mauszeigers nach Loslassen der Maustaste.
     * @param yPos y-Position des Mauszeigers nach Loslassen der Maustaste.
     */
    public void endCreateFigure(double xPos, double yPos) {

        System.out.println("endCreateFigure");
    }

    /**
     * Beendet das Verschieben des ausgewaehlten Objekts <code>Node node</code>.
     * @param node Zu verschiebende Figure.
     * @param xPos X-Position des Mauszeigers nach Loslassen der Maustaste.
     * @param yPos y-Position des Mauszeigers nach Loslassen der Maustaste.
     */
    public void endMoveFigure(Node node, double xPos, double yPos) {

        System.out.println("endMoveFigure");
    }

    /**
     * Fuegt die angeklickten Objekte <code>Node node</code> zu der Arraylist <code>Arraylist<Node> selected</code>
     * hinzu.
     * @param node Zu verschiebende Figure.
     * @param xPos X-Position des Mauszeigers waehrend des Klicks.
     * @param yPos y-Position des Mauszeigers waehrend des Klicks.
     * @param shiftPressed <code>true</code>: Die Shift-Taste wurde waehrend des
     */
    public void selectFigure(Node node, double xPos, double yPos, boolean shiftPressed) {

        if (node instanceof Figures) {
            if (!shiftPressed) {
                if (selected.size() != 0) {
                    for (Node figure : selected) {
                        if (!((Figures) figure).isInGroup()) {
                            ((Figures) figure).select(false);
                        }
                        else {
                            ((Figures) figure).getGroup().select(false);
                        }
                    }
                    selected.clear();
                }
                if (!((Figures) node).isInGroup()) {
                    ((Figures) node).select(true);
                    selected.add(node);
                }
                else {
                    ((Figures) node).getGroup().select(true);
                    selected.add(((Figures) node).getGroup());
                }

            }
            else {
                if (!((Figures) node).isInGroup()) {
                    ((Figures) node).select(true);
                    selected.add(node);
                }
                else {
                    ((Figures) node).getGroup().select(true);
                    selected.add(((Figures) node).getGroup());
                }
            }
        }
        else {
            for (Node figure : selected) {
                if (!((Figures) figure).isInGroup()) {
                    ((Figures) figure).select(false);
                }
                else {
                    ((Figures) figure).getGroup().select(false);
                }
            }
            selected.clear();
        }
    }

    /**
     * Rotiert das ausgewaehlte Objekt <code>Node node</code> um den Winkel <code>double angle</code>.
     * @param node Zu drehende Figure.
     * @param angle Winkel, um den die Figur weitergedreht werden soll.
     */
    public void rotate(Node node, double angle) {

        System.out.println("rotate");
    }

    /**
     * Schiebt das ausgewaehlte Objekt <code>Node node</code> um die Parameter <code>double deltaX</code> und
     * <code>deltaY</code> weiter.
     * @param node Zu verschiebende Figure.
     * @param deltaX Abstand in x-Richtung, um den die Figur weitergeschoben werden soll.
     * @param deltaY Abstand in y-Richtung, um den die Figur weitergeschoben werden soll.
     */
    public void translate(Node node, double deltaX, double deltaY) {

        System.out.println("translate");
    }

    /**
     * Vergroessert bzw. verkleinert das Objekt <code>Node node</code> um den Faktor <code>double zoomFactor</code>.
     * @param node Zu veraendernde Figure.
     * @param zoomFactor Faktor, um den die Figur vergroessert oder verkleinert werden soll.
     */
    public void zoom(Node node, double zoomFactor) {

        System.out.println("zoom");
    }

    /**
     * Loescht das selektierte Objekt vom Zeichenfeld.
     */
    public void deleteFigures() {

        panel.getChildren().removeAll(selected);
        selected.clear();
    }

    /**
     * Kopiert die selektierten Objekte in eine neue Liste namens <code>Arraylist<Node> copied</code> und deselektiert
     * die Auswahl.
     * */
    public void copyFigures() {

        copied.clear();
        for (Node node: selected) {
            ((Figures) node).select(false);
            copied.add(node);
        }
        selected.clear();
    }

    /**
     * Fuegt die zuvor in die Liste namens copied eingefuegten Objekte auf das Zeichefeld hinzu.
     */
    public void pasteFigures() {

        ArrayList<Node> newFigures = new ArrayList<>();
        for (Node node: copied) {
            Node figure = ((Figures) node).cloneObject();
            ((Figures) figure).select(true);
            ((Figures) node).select(false);

            newFigures.add(figure);
        }
        panel.getChildren().addAll(newFigures);
        selected.clear();
        selected.addAll(newFigures);
    }

    /**
     * Setzt die selektierten Objekte auf die oberste Ebene auf dem Zeichenfeld.
     */
    public void moveSelectedFiguresToTop() {

        panel.getChildren().removeAll(selected);
        panel.getChildren().addAll(selected);
    }

    /**
     * Setzt die selektierten Objekte auf die unterste Ebene auf dem Zeichenfeld.
     */
    public void moveSelectedFiguresToBottom() {

        ArrayList<Node> cache = new ArrayList<>();
        panel.getChildren().removeAll(selected);
        cache.addAll(panel.getChildren());
        panel.getChildren().removeAll(panel.getChildren());
        panel.getChildren().addAll(selected);
        panel.getChildren().addAll(cache);
    }

    /**
     * Setzt die selektierten Objekte um eine Ebene nach unten auf dem Zeichenfeld.
     */
    public void moveSelectedFiguresDown() {

        for (Node node: selected) {
            int index = panel.getChildren().indexOf(node);
            if (index != 0) {
                Node copyNode = panel.getChildren().get(index);
                Node copyPrevNode = panel.getChildren().get(index - 1);

                panel.getChildren().remove(copyNode);
                panel.getChildren().remove(copyPrevNode);

                panel.getChildren().add(index - 1, copyNode);
                panel.getChildren().add(index, copyPrevNode);
            }
        }
    }

    /**
     * Setzt die selektierten Objekte um eine Ebene nach oben auf dem Zeichenfeld.
     */
    public void moveSelectedFiguresUp() {

        for (Node node: selected) {
            int index = panel.getChildren().indexOf(node);
            if (index + 1 < panel.getChildren().size()) {
                Node copyNextNode = panel.getChildren().get(index + 1);
                Node copyNode = panel.getChildren().get(index);

                panel.getChildren().remove(copyNode);
                panel.getChildren().remove(copyNextNode);

                panel.getChildren().add(index, copyNextNode);
                panel.getChildren().add(index + 1, copyNode);
            }
        }
    }

    /**
     * Gruppiert die selektierten Objekte und fuegt sie dem Zeichenfeld wieder hinzu.
     */
    public void groupFigures() {

        ArrayList<Node> copyGroupNodes = new ArrayList<>();

        for (Node node: selected) {
            if (node instanceof Groups) {
                Node figure = ((Groups) node).cloneObject();
                copyGroupNodes.addAll(((Groups) figure).getChildren());
            }
            else {
                Node figure = ((Figures) node).cloneObject();
                copyGroupNodes.add(figure);
            }
        }
        Groups group = new Groups(copyGroupNodes);

        for (Node node: group.getChildren()) {
            ((Figures) node).setGroup(group);
            ((Figures) node).setInGroup(true);
        }

        panel.getChildren().removeAll(selected);
        selected.clear();
        panel.getChildren().add(group);
        System.out.println("groupFigures");
    }

    /**
     * Degruppiert die ausgewaehlte Gruppe und fuegt die einzelnen Objekte wieder dem Zeichenfeld hinzu.
     */
    public void ungroupFigures() {

        for (Node node: ((Groups) selected.get(0)).getChildren()) {
            ((Figures) node).setInGroup(false);
            ((Figures) node).select(false);
        }
        panel.getChildren().addAll(((Groups) selected.get(0)).getChildren());
        panel.getChildren().remove(selected.get(0));
    }

    /**
     * Gibt die Groesse der Liste der selektierten Objekte zurueck.
     * @return Die Groesse der Liste der ausgewaehlten Objekte.
     */
    public int getSelectedFiguresCount() {

        return selected.size();
    }

    /**
     * Gibt die Groesse der kopierten Objekte zurueck.
     * @return Groesse der Liste <code>Arraylist<Node> copied</code>
     */
    public int getFiguresInClipboardCount() {

        return copied.size();
    }

    /**
     * Gibt zurueck, ob genau eine Gruppe selektiert wurde.
     * @return (True), falls eine Gruppe ausgewaehlt wurde.
     */
    public boolean isGroupSelected() {

        if (selected.size() == 1) {
            if (selected.get(0) instanceof Groups) {
                return true;
            }
        }
        return false;
    }
}
