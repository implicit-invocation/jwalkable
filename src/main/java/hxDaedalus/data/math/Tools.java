// Generated by Haxe 3.4.2
package hxDaedalus.data.math;

import haxe.root.*;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public class Tools extends haxe.lang.HxObject
{
	public Tools(haxe.lang.EmptyObject empty)
	{
	}
	
	
	public Tools()
	{
		//line 14 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
		hxDaedalus.data.math.Tools.__hx_ctor_hxDaedalus_data_math_Tools(this);
	}
	
	
	public static void __hx_ctor_hxDaedalus_data_math_Tools(hxDaedalus.data.math.Tools __hx_this)
	{
	}
	
	
	public static hxDaedalus.data.Mesh extractMeshFromBitmap(hxPixels._Pixels.PixelsData pixels, haxe.root.Array<hxDaedalus.data.math.Point2D> vertices, haxe.root.Array<java.lang.Object> triangles)
	{
		//line 19 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
		haxe.root.Array<haxe.root.Array<java.lang.Object>> shapes = hxDaedalus.data.math.Potrace.buildShapes(pixels, null, null);
		//line 21 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
		return hxDaedalus.data.math.Tools.extractMeshFromShapes(shapes, pixels.width, pixels.height, vertices, triangles, null);
	}
	
	
	public static hxDaedalus.data.Mesh extractMeshFromShapes(haxe.root.Array<haxe.root.Array<java.lang.Object>> shapes, int width, int height, haxe.root.Array<hxDaedalus.data.math.Point2D> vertices, haxe.root.Array<java.lang.Object> triangles, java.lang.Object invertWinding)
	{
		//line 36 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
		boolean __temp_invertWinding21 = ( (haxe.lang.Runtime.eq(invertWinding, null)) ? (false) : (haxe.lang.Runtime.toBool(invertWinding)) );
		//line 39 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
		haxe.root.Array<hxDaedalus.data.graph.Graph> graphs = new haxe.root.Array<hxDaedalus.data.graph.Graph>();
		//line 40 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
		{
			//line 40 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
			int _g1 = 0;
			//line 40 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
			int _g = shapes.length;
			//line 40 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
			while (( _g1 < _g ))
			{
				//line 40 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
				int i = _g1++;
				//line 40 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
				graphs.push(hxDaedalus.data.math.Potrace.buildGraph(shapes.__get(i)));
			}
			
		}
		
		//line 43 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
		haxe.root.Array<haxe.root.Array<java.lang.Object>> polygons = new haxe.root.Array<haxe.root.Array<java.lang.Object>>();
		//line 44 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
		{
			//line 44 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
			int _g11 = 0;
			//line 44 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
			int _g2 = graphs.length;
			//line 44 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
			while (( _g11 < _g2 ))
			{
				//line 44 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
				int i1 = _g11++;
				//line 44 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
				polygons.push(hxDaedalus.data.math.Potrace.buildPolygon(graphs.__get(i1), null));
			}
			
		}
		
		//line 47 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
		hxDaedalus.data.Mesh mesh = hxDaedalus.factories.RectMesh.buildRectangle(((double) (width) ), ((double) (height) ));
		//line 48 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
		haxe.root.Array<hxDaedalus.data.Edge> edges = new haxe.root.Array<hxDaedalus.data.Edge>();
		//line 49 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
		hxDaedalus.data.ConstraintSegment segment = null;
		//line 51 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
		{
			//line 51 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
			int _g12 = 0;
			//line 51 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
			int _g3 = polygons.length;
			//line 51 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
			while (( _g12 < _g3 ))
			{
				//line 51 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
				int i2 = _g12++;
				//line 53 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
				int j = 0;
				//line 54 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
				while (( j < ( polygons.__get(i2).length - 2 ) ))
				{
					//line 55 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
					segment = mesh.insertConstraintSegment(((double) (haxe.lang.Runtime.toDouble(polygons.__get(i2).__get(j))) ), ((double) (haxe.lang.Runtime.toDouble(polygons.__get(i2).__get(( j + 1 )))) ), ((double) (haxe.lang.Runtime.toDouble(polygons.__get(i2).__get(( j + 2 )))) ), ((double) (haxe.lang.Runtime.toDouble(polygons.__get(i2).__get(( j + 3 )))) ));
					//line 56 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
					if (( j == 0 )) 
					{
						//line 57 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
						if (( ( segment.get_edges().__get(0).get_originVertex().get_pos().x == ((double) (haxe.lang.Runtime.toDouble(polygons.__get(i2).__get(j))) ) ) && ( segment.get_edges().__get(0).get_originVertex().get_pos().y == ((double) (haxe.lang.Runtime.toDouble(polygons.__get(i2).__get(( j + 1 )))) ) ) )) 
						{
							//line 58 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
							if ( ! (__temp_invertWinding21) ) 
							{
								//line 59 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
								edges.push(segment.get_edges().__get(0));
							}
							else
							{
								//line 61 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
								edges.push(segment.get_edges().__get(0).get_oppositeEdge());
							}
							
						}
						else
						{
							//line 64 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
							if ( ! (__temp_invertWinding21) ) 
							{
								//line 65 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
								edges.push(segment.get_edges().__get(0).get_oppositeEdge());
							}
							else
							{
								//line 67 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
								edges.push(segment.get_edges().__get(0));
							}
							
						}
						
					}
					
					//line 71 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
					j += 2;
				}
				
				//line 73 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
				mesh.insertConstraintSegment(((double) (haxe.lang.Runtime.toDouble(polygons.__get(i2).__get(0))) ), ((double) (haxe.lang.Runtime.toDouble(polygons.__get(i2).__get(1))) ), ((double) (haxe.lang.Runtime.toDouble(polygons.__get(i2).__get(j))) ), ((double) (haxe.lang.Runtime.toDouble(polygons.__get(i2).__get(( j + 1 )))) ));
			}
			
		}
		
		//line 77 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
		haxe.ds.ObjectMap<hxDaedalus.data.Vertex, java.lang.Object> indicesDict = new haxe.ds.ObjectMap<hxDaedalus.data.Vertex, java.lang.Object>();
		//line 79 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
		hxDaedalus.data.Vertex vertex = null;
		//line 80 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
		hxDaedalus.data.math.Point2D point = null;
		//line 81 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
		{
			//line 81 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
			int _g13 = 0;
			//line 81 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
			int _g4 = mesh._vertices.length;
			//line 81 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
			while (( _g13 < _g4 ))
			{
				//line 81 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
				int i3 = _g13++;
				//line 82 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
				vertex = mesh._vertices.__get(i3);
				//line 83 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
				if (( ( ( ( vertex.get_isReal() && ( vertex.get_pos().x > 0 ) ) && ( vertex.get_pos().x < width ) ) && ( vertex.get_pos().y > 0 ) ) && ( vertex.get_pos().y < height ) )) 
				{
					//line 87 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
					point = new hxDaedalus.data.math.Point2D(vertex.get_pos().x, vertex.get_pos().y);
					//line 88 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
					vertices.push(point);
					//line 89 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
					{
						//line 89 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
						int v = ( vertices.length - 1 );
						//line 89 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
						indicesDict.set(vertex, v);
					}
					
				}
				
			}
			
		}
		
		//line 93 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
		haxe.ds.ObjectMap<hxDaedalus.data.Face, java.lang.Object> facesDone = new haxe.ds.ObjectMap<hxDaedalus.data.Face, java.lang.Object>();
		//line 94 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
		haxe.root.Array<hxDaedalus.data.Face> openFacesList = new haxe.root.Array<hxDaedalus.data.Face>();
		//line 95 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
		{
			//line 95 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
			int _g14 = 0;
			//line 95 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
			int _g5 = edges.length;
			//line 95 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
			while (( _g14 < _g5 ))
			{
				//line 95 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
				int i4 = _g14++;
				//line 95 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
				openFacesList.push(edges.__get(i4).get_rightFace());
			}
			
		}
		
		//line 96 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
		hxDaedalus.data.Face currFace = null;
		//line 97 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
		while (( openFacesList.length > 0 ))
		{
			//line 99 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
			currFace = ((hxDaedalus.data.Face) (openFacesList.pop()) );
			//line 100 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
			if (haxe.lang.Runtime.toBool((((java.lang.Object) (facesDone.get(currFace)) )))) 
			{
				//line 100 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
				continue;
			}
			
			//line 102 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
			triangles.push(indicesDict.get(currFace.get_edge().get_originVertex()));
			//line 103 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
			triangles.push(indicesDict.get(currFace.get_edge().get_nextLeftEdge().get_originVertex()));
			//line 104 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
			triangles.push(indicesDict.get(currFace.get_edge().get_nextLeftEdge().get_destinationVertex()));
			//line 106 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
			if ( ! (currFace.get_edge().get_isConstrained()) ) 
			{
				//line 106 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
				openFacesList.push(currFace.get_edge().get_rightFace());
			}
			
			//line 107 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
			if ( ! (currFace.get_edge().get_nextLeftEdge().get_isConstrained()) ) 
			{
				//line 107 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
				openFacesList.push(currFace.get_edge().get_nextLeftEdge().get_rightFace());
			}
			
			//line 108 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
			if ( ! (currFace.get_edge().get_prevLeftEdge().get_isConstrained()) ) 
			{
				//line 108 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
				openFacesList.push(currFace.get_edge().get_prevLeftEdge().get_rightFace());
			}
			
			//line 110 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
			facesDone.set(currFace, true);
		}
		
		//line 113 "/Users/tao/projects/hxDaedalus/src/hxDaedalus/data/math/Tools.hx"
		return mesh;
	}
	
	
}

