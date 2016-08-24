package com.precisionhawk.inflightmobile.flightplanning.polygonbuffer;

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
        double m;
        double c;
        double a = 1;
        /**
         * If x1 == x2 then slope of that line will always be -1 and c = X and a = 0 in ay = mx + c.
         */
        if (firstPoint.getX() == secondPoint.getX()) {
            m = -1;
            c = firstPoint.getX();
            a = 0;
        }
        /**
         * If y1 == y2 then slope of that line will always be 0 and c = Y in ay = mx + c.
         */
        else if (firstPoint.getY() == secondPoint.getY()) {
            m = 0;
            c = firstPoint.getY();
        } else {
            /**
             * m represents the slope of a line
             * From math formula : m = (y2-y1) / (x2-x1)
             */
            m = (secondPoint.getY() - firstPoint.getY())
                    / (secondPoint.getX() - firstPoint.getX());
            m = Double.isInfinite(m) ? 0 : m;
            /**
             * c represents 'y' intercept of a line.
             * From math formula : c = y - mx
             */
            c = firstPoint.getY() - (m * firstPoint.getX());
        }
        lineEquation.setM(m);
        lineEquation.setA(a);
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
        double m;
        double c;
        double a = 1;
        /**
         * If m == 0 ie. line is parallel to x-axis then
         * slope of the line will be -1
         * y intercept will be 'x' from given point
         */
        if (givenLineEquation.getM() == 0) {
            m = -1;
            a = 0;
            c = crossingPoint.getX();
        }
        /**
         * If a == 0 in line ay = mx + c that means that the line is parallel to y-axis then
         * slope of the line will be 0
         * y intercept will be 'y' from given point
         */
        else if (givenLineEquation.getA() == 0) {
            m = 0;
            c = crossingPoint.getY();
        } else {
            /**
             * Step 1: Get slope of the perpendicular line by formula m1.m2 = -1
             * given m1 is slop of given line and m2 will be slope of line perpendicular to it.
             */
            m = -1 / givenLineEquation.getM();
            m = Double.isInfinite(m) ? 0 : m;
            /**
             * Step 2: Get 'y' intercept using given crossing point in formula y = mx + c.
             * so c = y - mx
             */
            c = crossingPoint.getY() - (m * crossingPoint.getX());
        }
        lineEquation.setA(a);
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
        double m;
        double c;
        double a = 1;
        /**
         * If m == 0 ie. line is parallel to x-axis then
         * slope of the line will be same
         * y intercept will be 'y' from given point
         */
        if (givenLineEquation.getM() == 0) {
            m = givenLineEquation.getM();
            a = 1;
            c = crossingPoint.getY();
        }
        /**
         * If a == 0 in line ay = mx + c that means that the line is parallel to y-axis then
         * slope of the line will be same
         * x intercept will be 'x' from given point
         */
        else if (givenLineEquation.getA() == 0) {
            m = givenLineEquation.getM();
            a = 0;
            c = crossingPoint.getX();
        } else {
            /**
             * Step 1: Get slope of the parallel line by formula m1 = m2
             * given m1 is slop of given line and m2 will be slope of line parallel to it.
             */
            m = givenLineEquation.getM();
            /**
             * Step 2: Get 'y' intercept using given crossing point in formula y = mx + c.
             * so c = y - mx
             */
            c = crossingPoint.getY() - (m * crossingPoint.getX());
        }
        lineEquation.setC(c);
        lineEquation.setA(a);
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
        /**
         * Point of intersection will be infinite if slopes are same
         */
        if (firstLine.getM() == secondLine.getM())
            return new Point(Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY);
        /**
         * There will be point of intersection only if there slope is different
         */
        /**
         * Check the value of "a" in ay = mx + c
         * As if a == 0  then given line is parallel to y-axis and
         */
        double x;
        /**
         * X will always be 'C' from y = mx + c
         */
        if (firstLine.getA() == 0) {
            x = firstLine.getC();
        } else if (secondLine.getA() == 0) {
            x = secondLine.getC();
        } else {
            /**
             * Given    first line :  y = m1x + c1
             *          Second line : y = m2x + c2
             * Find x : x = (c2 - c1) / (m1 - m2)
             */
            x = (secondLine.getC() - firstLine.getC()) / (firstLine.getM() - secondLine.getM());
        }
        double y;
        /**
         * Now put 'x' in any one line equation ie. y = mx + c.
         */
        if (secondLine.getM() == 0) {
            y = secondLine.getC();
        } else if (firstLine.getM() == 0) {
            y = firstLine.getC();
        } else {
            y = (firstLine.getM() * x) + firstLine.getC();
        }
        return new Point(x, y);
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
//        DecimalFormat f = new DecimalFormat("###.######");
//        String string = f.format(distance);
//        double distanceFormated = Double.parseDouble(string);
        Point firstPoint = new Point();
        Point secondPoint = new Point();
        double xFirstPoint;
        double xSecondPoint;
        double yFirstPoint;
        double ySecondPoint;
        /**
         * If line is parallel to x-axis ie. y = 4 then
         * point on the same with 'd' distance will be (x1 + d , y) and (x1 - d, y)
         */
        if (lineEquation.getM() == 0) {
            xFirstPoint = pointOnLine.getX() + distance;
            xSecondPoint = pointOnLine.getX() - distance;
            yFirstPoint = pointOnLine.getY();
            ySecondPoint = pointOnLine.getY();
        }
        /**
         * If line is parallel to y-axis ie. x = 4 then
         * point on the same with 'd' distance will be (x , y + d ) and (x , y - d)
         */
        else if (lineEquation.getA() == 0) {
            xFirstPoint = pointOnLine.getX();
            xSecondPoint = pointOnLine.getX();
            yFirstPoint = pointOnLine.getY() + distance;
            ySecondPoint = pointOnLine.getY() - distance;
        } else {
            /**
             * Step 1 : Get x point using formula x = x1 + (d / sqrt (1 + m * m))
             */
            //'x' for First point
            xFirstPoint = pointOnLine.getX() + (distance /
                    (Math.sqrt((1 + (Math.pow(lineEquation.getM(), 2))))));
            //'x' for Second point
            xSecondPoint = pointOnLine.getX() - (distance /
                    (Math.sqrt((1 + (Math.pow(lineEquation.getM(), 2))))));
            /**
             * Step 2 : Put 'x' in 'y = mx + c' to get 'y'
             */
            //'y' for first point
            yFirstPoint = (lineEquation.getM() * xFirstPoint) + lineEquation.getC();
            //'y' for second point
            ySecondPoint = (lineEquation.getM() * xSecondPoint) + lineEquation.getC();
        }
        firstPoint.setX(xFirstPoint);
        secondPoint.setX(xSecondPoint);
        secondPoint.setY(ySecondPoint);
        firstPoint.setY(yFirstPoint);
        Polygon polygonPoints = buildPolygon(polygon);
        if (polygonPoints.contains(new Point(firstPoint.getX(), firstPoint.getY())))
            return secondPoint;
        else
            return firstPoint;
    }

    private Polygon buildPolygon(Point[] polygon) {
        Polygon.Builder builder = Polygon.Builder();
        for (int i = 0; i < polygon.length; i++) {
            Point point = polygon[i];
            builder.addVertex(
                    new Point(point.getX(), point.getY()));
        }
        return builder.build();
    }
}
