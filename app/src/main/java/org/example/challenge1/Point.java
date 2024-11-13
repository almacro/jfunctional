
package org.example.challenge1;

/**
 *
 * @author almacro
 */

// code from Shaun Wassell's course [Funtional Programming with Java](https://www.linkedin.com/learning/functional-programming-with-java/coderpad-solution-applying-functional-concepts?resume=false&u=2012979)
class Point {

    private float x;
    private float y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Point add(Point p) {
        return new Point(this.x + p.getX(), this.y + p.getY());
    }

    public Point subtract(Point p) {
        return new Point(this.x - p.getX(), this.y - p.getY());
    }

    // Don't change these 2 methods
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    public static boolean pointsAreEqual(Point p1, Point p2) {
        return p1.getX() == p2.getX() && p1.getY() == p2.getY();
    }

}

