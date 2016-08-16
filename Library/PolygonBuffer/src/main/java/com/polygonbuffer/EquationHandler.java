package com.polygonbuffersample.areabuffer;

public class EquationHandler {
    private static final String TAG = "EquationHandler";

    /**
     * Function Get equation of a line from given point.
     *
     * @param firstPoint  Point : First point on a plane
     * @param secondPoint Point : Second point on the same plane
     * @return LineEquation : Equation of a line between given points.
     */
    public LineEquation getLineEquation(Point firstPoint, Point secondPoint) throws Exception {
        LineEquation lineEquation = new LineEquation();
        /**
         * m represents the slope of a line
         * From math formula : m = (y2-y1) / (x2-x1)
         */
        double m = (secondPoint.getY() - firstPoint.getY())
                / (secondPoint.getX() - firstPoint.getX());
        m = Double.isInfinite(m) ? 0 : m;
        lineEquation.setM(m);
        /**
         * c represents 'y' intercept of a line.
         * From math formula : c = y - mx
         */
        double c = firstPoint.getY() - (m * firstPoint.getX());
        lineEquation.setC(c);
        return lineEquation;
    }

    /**
     * Function to get the equation of a line perpendicular to given line and passing though a
     * point.
     *
     * @param givenLineEquation LineEquation : Given line equation
     * @param crossingPoint     Point : Point through with the line should pass.
     * @return LineEquation : Line perpendicular to given line and passing through given point.
     */
    public LineEquation getPerpendicularLineEquation(LineEquation givenLineEquation,
                                                     Point crossingPoint) throws Exception {
        LineEquation lineEquation = new LineEquation();
        /**
         * Step 1: Get slope of the perpendicular line by formula m1.m2 = -1
         * given m1 is slop of given line and m2 will be slope of line perpendicular to it.
         */
        double m = -1 / givenLineEquation.getM();
        m = Double.isInfinite(m) ? 0 : m;
        /**
         * Step 2: Get 'y' intercept using given crossing point in formula y = mx + c.
         * so c = y - mx
         */
        double c = crossingPoint.getY() - (m * crossingPoint.getX());
        lineEquation.setC(c);
        lineEquation.setM(m);
        return lineEquation;
    }

    /**
     * Function to get the equation of a line parallel to given line and passing though a
     * point.
     *
     * @param givenLineEquation LineEquation : Given line equation
     * @param crossingPoint     Point : Point through with the line should pass.
     * @return LineEquation : Line parallel to given line and passing through given point.
     */
    public LineEquation getParallelLineEquation(LineEquation givenLineEquation,
                                                Point crossingPoint) throws Exception {
        LineEquation lineEquation = new LineEquation();
        /**
         * Step 1: Get slope of the parallel line by formula m1 = m2
         * given m1 is slop of given line and m2 will be slope of line parallel to it.
         */
        double m = givenLineEquation.getM();
        /**
         * Step 2: Get 'y' intercept using given crossing point in formula y = mx + c.
         * so c = y - mx
         */
        double c = crossingPoint.getY() - (m * crossingPoint.getX());
        lineEquation.setC(c);
        lineEquation.setM(Double.isInfinite(m) ? 0 : m);
        return lineEquation;
    }

    /**
     * Function to get the intersection point of two given lines.
     *
     * @param firstLine  LineEquation : Equation of first line
     * @param secondLine LineEquation : Equation of second line
     * @return Point Intersection point of two lines.
     */
    public Point getIntersectionPoint(LineEquation firstLine, LineEquation secondLine) throws Exception {
        Point point = new Point();
        /**
         * Given    first line :  y = m1x + c1
         *          Second line : y = m2x + c2
         * Find x : x = (c2 - c1) / (m1 - m2)
         */
        double x = (secondLine.getC() - firstLine.getC()) / (firstLine.getM() - secondLine.getM());
        /**
         * Now put 'x' in any one line equation ie. y = mx + c.
         */
        double y = (firstLine.getM() * x) + firstLine.getC();
        point.setX(x);
        point.setY(y);
        return point;
    }

    /**
     * Function to get point on a line at a given distance from given point
     *
     * @param lineEquation LineEquation : Line on which you have to find the point
     * @param pointOnLine  Point : Point from where you want a point at a given distance
     * @param distance     double : Distance at which you want the point to be.
     * @param polygon
     * @return Point Point at the given distance on the given line
     */
    public Point getPointOnLineAtDistance(LineEquation lineEquation, Point pointOnLine,
                                          double distance, Point[] polygon) throws Exception {
        /**
         * Using line equation 'y=mx+c' and point (x,y) and distance 'd'
         * You will get two points on either side of the line.
         */
        Point firstPoint = new Point();
        Point secondPoint = new Point();
        /**
         * Step 1 : Get x point using formula x = x1 + (d / sqrt (1 + m * m))
         */
        //'x' for First point
        double xFirstPoint = pointOnLine.getX() + (distance /
                (Math.sqrt((1 + (Math.pow(lineEquation.getM(), 2))))));
        firstPoint.setX(xFirstPoint);
        //'x' for Second point
        double xSecondPoint = pointOnLine.getX() - (distance /
                (Math.sqrt((1 + (Math.pow(lineEquation.getM(), 2))))));
        secondPoint.setX(xSecondPoint);
        /**
         * Step 2 : Put 'x' in 'y = mx + c' to get 'y'
         */
        //'y' for first point
        double yFirstPoint = (lineEquation.getM() * xFirstPoint) + lineEquation.getC();
        firstPoint.setY(yFirstPoint);
        //'y' for second point
        double ySecondPoint = (lineEquation.getM() * xSecondPoint) + lineEquation.getC();
        secondPoint.setY(ySecondPoint);
        Polygon polygonPoints = buildPolygon(polygon);
        if (polygonPoints.contains(new com.polygonbuffersample.areabuffer.Point(firstPoint
                .getX(), firstPoint.getY())))
            return secondPoint;
        else
            return firstPoint;
    }

    private Polygon buildPolygon(Point[] polygon) {
        Polygon.Builder builder = Polygon.Builder();
        for (int i = 0; i < polygon.length - 1; i++) {
            Point point = polygon[i];
            builder.addVertex(
                    new com.polygonbuffersample.areabuffer.Point(point.getX(), point.getY()));
        }
        return builder.build();
    }
}
