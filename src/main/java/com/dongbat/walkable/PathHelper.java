/*
 * Copyright 2017 Dong Bat Co.,Ltd..
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dongbat.walkable;

import haxe.root.Array;
import hxDaedalus.ai.EntityAI;
import hxDaedalus.ai.PathFinder;
import hxDaedalus.data.Mesh;
import hxDaedalus.data.Obstacle;
import hxDaedalus.factories.RectMesh;

import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper for hxDaedalus
 * @author tao
 */
public class PathHelper {
  private final Mesh mesh;
  private final EntityAI entity;
  private final PathFinder pathFinder;
  private final Array path;
  private final List<Obstacle> obstacles = new ArrayList<Obstacle>();
  private int scale;

  /**
   * Constructs a PathFinder with the given world width and world height
   * @param w the world width
   * @param h the world height
   */
  public PathHelper(float w, float h) {
    mesh = RectMesh.buildRectangle(w, h);
    entity = new EntityAI();
    pathFinder = new PathFinder();
    pathFinder.set_mesh(mesh);
    pathFinder.entity = entity;
    path = new Array();
    scale = 1;
  }

  /** If adding obstacles to PathHelper gets stuck in an infinite loop, try using a larger scale.
   * Your obstacle details may be too small. */
  public PathHelper(float w, float h, int scale) {
    mesh = RectMesh.buildRectangle(w * scale, h * scale);
    entity = new EntityAI();
    pathFinder = new PathFinder();
    pathFinder.set_mesh(mesh);
    pathFinder.entity = entity;
    path = new Array();
    this.scale = scale;
  }

  public Obstacle addPolyline(float[] vertices) {
    return addPolyline(vertices, 0, 0);
  }

  public Obstacle addPolyline(float[] vertices, float x, float y) {
    if (vertices.length < 4) {
      throw new IllegalArgumentException("Polylines must contain at least 2 points.");
    }
    Obstacle object = new Obstacle();
    Array array = new Array();
    object.set_coordinates(array);
    float prevX = 0, prevY = 0;
    for (int i = 0; i < vertices.length; i += 2) {
      float vx = vertices[i] * scale;
      float vy = vertices[i + 1] * scale;

      if (i > 0) {
        array.push(prevX);
        array.push(prevY);
        array.push(vx);
        array.push(vy);
      }

      prevX = vx;
      prevY = vy;
    }
    object.set_x(x * scale);
    object.set_y(y * scale);
    mesh.insertObject(object);
    obstacles.add(object);

    return object;
  }

  public Obstacle addPolygon(float[] vertices) {
    return addPolygon(vertices, 0, 0);
  }

  public Obstacle addPolygon(float[] vertices, float x, float y) {
    if (vertices.length < 6) {
      throw new IllegalArgumentException("Polygons must contain at least 3 points.");
    }
    Obstacle object = new Obstacle();
    Array array = new Array();
    object.set_coordinates(array);
    float prevX = vertices[vertices.length - 2] * scale, prevY = vertices[vertices.length - 1] * scale;
    for (int i = 0; i < vertices.length; i += 2) {
      float vx = vertices[i] * scale;
      float vy = vertices[i + 1] * scale;

      array.push(prevX);
      array.push(prevY);
      array.push(vx);
      array.push(vy);

      prevX = vx;
      prevY = vy;
    }
    object.set_x(x * scale);
    object.set_y(y * scale);
    mesh.insertObject(object);
    obstacles.add(object);

    return object;
  }

  public Obstacle addRect(float x, float y, float w, float h) {
    x *= scale;
    y *= scale;
    w *= scale;
    h *= scale;

    Obstacle object = new Obstacle();
    Array array = new Array();
    array.push(0);
    array.push(0);

    array.push(w);
    array.push(0);

    array.push(w);
    array.push(0);

    array.push(w);
    array.push(h);

    array.push(w);
    array.push(h);

    array.push(0);
    array.push(h);

    array.push(0);
    array.push(h);

    array.push(0);
    array.push(0);

    object.set_coordinates(array);
    object.set_x(x);
    object.set_y(y);
    mesh.insertObject(object);
    obstacles.add(object);

    return object;
  }

  /**
   * Removes the given obstacle
   */
  public void removeObstacle(Obstacle obstacle) {
    mesh.deleteObject(obstacle);
    obstacles.remove(obstacle);
  }

  /**
   * Removes all obstacles
   */
  public void clearObstacles(){
    for (Obstacle obstacle : obstacles){
      removeObstacle(obstacle);
    }
    obstacles.clear();
  }

  private void doFindPath(float fromX, float fromY, float toX, float toY, float radius) {
    entity.set_radius(radius * scale);
    entity.x = fromX * scale;
    entity.y = fromY * scale;
    try {
      pathFinder.findPath(toX * scale, toY * scale, path);
    } catch (Exception e) {
      path.splice(0, path.length);
      throw new PathfinderException(e);
    }
  }

  /**
   * @throws PathfinderException occasionally, if there's an internal problem in hxDaedalus
   */
  public float[] findPath(float fromX, float fromY, float toX, float toY, float radius) {
    doFindPath(fromX, fromY, toX, toY, radius);

    float[] result = new float[path.length];

    for (int i = 0; i < path.length; i++) {
      result[i] = ((Double) path.__get(i)).floatValue() / scale;
    }
    return result;
  }

  /**
   * @throws PathfinderException occasionally, if there's an internal problem in hxDaedalus
   */
  public int findPath(float fromX, float fromY, float toX, float toY, float radius, float[] result) {
    doFindPath(fromX, fromY, toX, toY, radius);

    for (int i = 0; i < path.length; i++) {
      result[i] = ((Double) path.__get(i)).floatValue() / scale;
    }
    return path.length;
  }

  /**
   * @throws PathfinderException occasionally, if there's an internal problem in hxDaedalus
   */
  public void findPath(float fromX, float fromY, float toX, float toY, float radius, List<Float> result) {
    doFindPath(fromX, fromY, toX, toY, radius);

    result.clear();
    for (int i = 0; i < path.length; i++) {
      result.add(((Double) path.__get(i)).floatValue() / scale);
    }
  }

  /**
   * @throws PathfinderException occasionally, if there's an internal problem in hxDaedalus
   */
  public void findPath(float fromX, float fromY, float toX, float toY, float radius, FloatArray result) {
    doFindPath(fromX, fromY, toX, toY, radius);

    result.clear();
    for (int i = 0; i < path.length; i++) {
      result.add(((Double) path.__get(i)).floatValue() / scale);
    }
  }
}
