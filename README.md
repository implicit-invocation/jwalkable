# jwalkable
Easy 2D polygonal pathfinding for Java.

![Demo](images/jwalkable.gif?raw=true "demo")

## Installation

You can download jar file from https://jitpack.io/com/github/implicit-invocation/jwalkable/master-c3314c07d6-1/jwalkable-master-c3314c07d6-1.jar

Using Gradle

 ```gradle
 repositories { 
      jcenter()
      maven { url "https://jitpack.io" }
 }
 dependencies {
       compile 'com.github.implicit-invocation:jwalkable:master-SNAPSHOT'
 }
 ```  

Using Maven

```maven
  <repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
  </repositories>
 ...
  <dependency>
      <groupId>com.github.implicit-invocation</groupId>
      <artifactId>jwalkable</artifactId>
      <version>master-SNAPSHOT</version>
  </dependency>

```
## Usage

Setup a `PathHelper`

```Java
pathHelper = new PathHelper(worldWidth, worldHeight);
```

After that, you can add some obstacles

```Java
// polygon
pathHelper.addPolygon(polygon.getTransformedVertices());
pathHelper.addPolygon(polygon.getVertices(), polygon.getX(), polygon.getY());

// polyline
pathHelper.addPolyline(polyline.getTransformedVertices());

// rectangle
pathHelper.addRect(x, y, w, h);
```
Vertices for polylines and polygons should be a float array `new float[] { x1, y1, x2, y2, ...}`.
Polylines must have more than 2 points (4 vertices) and polygons must have more than 3 points (6 vertices).

You can remove an obstacle

```Java
Obstacle rect = pathHelper.addRect(x, y, w, h);
pathHelper.removeObstacle(rect);
```

Now run the pathfinding

```Java
FloatArray path = new FloatArray();
pathHelper.findPath(start.x, start.y, end.x, end.y, radius, path);
```

`radius` decides how big the entity is.
