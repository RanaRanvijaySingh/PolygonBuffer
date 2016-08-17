# Polygon Buffer
This library lets you create a buffer area with same shape as your original one, at a given distance on maps. Its purely java code so can be used without any dependencies. 
#Preview
- Pink Polygon : User given polygon.
- Blue Polygon : Buffered or generated polygon.
![screenshot_20160812-174634](https://cloud.githubusercontent.com/assets/4836122/17727132/f797eba0-6474-11e6-8ab2-ed631d35d479.png)


### Instructions
There are two ways in which you can use this library.
- First : Just copy paste entire **polygonbuffer** package from **PolygonBuffer/src/main/java/com** in your project and start using it.
- Second : You can import given **jar file** in your application.

### How to use it
All you need to use is this line  **List<Point> bufferedPolygonList = AreaBuffer.buffer(pointList);**.

    final List<LatLng> latLngPolygon = new ArrayList<>();
    {
        latLngPolygon.add(new LatLng(18.5222294252479, 73.77664268016815 // First point
        latLngPolygon.add(new LatLng(18.522987318585017, 73.77766728401184));
        latLngPolygon.add(new LatLng(18.522977145542317, 73.77920687198639));
        latLngPolygon.add(new LatLng(18.52205139612452, 73.77998471260071));
        latLngPolygon.add(new LatLng(18.52091709192927, 73.77995252609253));
        latLngPolygon.add(new LatLng(18.520316874109714, 73.77870798110962));
        latLngPolygon.add(new LatLng(18.520454212271208, 73.77709329128265));
        // Same as first point as polygon has to be enclosed.
        latLngPolygon.add(new LatLng(18.5222294252479, 73.77664268016815)); 
    }
    // You may have to convert your List of lat and lng to list of Point object that this library supports.
      List<Point> pointList = new ArrayList<>();
        for (int i = 0; i < latLngPolygon.size(); i++) {
            Point point = new Point(latLngPolygon.get(i).getLatitude(),
                    latLngPolygon.get(i).getLongitude());
            pointList.add(point);
        }
    // Once that is done just call this function and you will get a list of buffered polygon points.
    List<Point> bufferedPolygonList = new AreaBuffer().buffer(pointList);
    


### Version
1.0
