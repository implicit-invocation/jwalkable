// Generated by Haxe 3.4.2
package hxPixels._Pixels;

import haxe.root.*;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public final class Pixel_Impl_
{
	
	
	public static int get_A(int this1)
	{
		//line 555 "/Users/tao/projects/hxDaedalus/src/hxPixels/Pixels.hx"
		return ( ( this1 >> 24 ) & 255 );
	}
	
	
	public static int set_A(int this1, int a)
	{
		//line 558 "/Users/tao/projects/hxDaedalus/src/hxPixels/Pixels.hx"
		a &= 255;
		//line 559 "/Users/tao/projects/hxDaedalus/src/hxPixels/Pixels.hx"
		this1 = ( ( this1 & 16777215 ) | ( a << 24 ) );
		//line 560 "/Users/tao/projects/hxDaedalus/src/hxPixels/Pixels.hx"
		return a;
	}
	
	
	
	
	public static int get_R(int this1)
	{
		//line 565 "/Users/tao/projects/hxDaedalus/src/hxPixels/Pixels.hx"
		return ( ( this1 >> 16 ) & 255 );
	}
	
	
	public static int set_R(int this1, int r)
	{
		//line 568 "/Users/tao/projects/hxDaedalus/src/hxPixels/Pixels.hx"
		r &= 255;
		//line 569 "/Users/tao/projects/hxDaedalus/src/hxPixels/Pixels.hx"
		this1 = ( ( this1 & -16711681 ) | ( r << 16 ) );
		//line 570 "/Users/tao/projects/hxDaedalus/src/hxPixels/Pixels.hx"
		return r;
	}
	
	
	
	
	public static int get_G(int this1)
	{
		//line 575 "/Users/tao/projects/hxDaedalus/src/hxPixels/Pixels.hx"
		return ( ( this1 >> 8 ) & 255 );
	}
	
	
	public static int set_G(int this1, int g)
	{
		//line 578 "/Users/tao/projects/hxDaedalus/src/hxPixels/Pixels.hx"
		g &= 255;
		//line 579 "/Users/tao/projects/hxDaedalus/src/hxPixels/Pixels.hx"
		this1 = ( ( this1 & -65281 ) | ( g << 8 ) );
		//line 580 "/Users/tao/projects/hxDaedalus/src/hxPixels/Pixels.hx"
		return g;
	}
	
	
	
	
	public static int get_B(int this1)
	{
		//line 585 "/Users/tao/projects/hxDaedalus/src/hxPixels/Pixels.hx"
		return ( this1 & 255 );
	}
	
	
	public static int set_B(int this1, int b)
	{
		//line 588 "/Users/tao/projects/hxDaedalus/src/hxPixels/Pixels.hx"
		b &= 255;
		//line 589 "/Users/tao/projects/hxDaedalus/src/hxPixels/Pixels.hx"
		this1 = ( ( this1 & -256 ) | b );
		//line 590 "/Users/tao/projects/hxDaedalus/src/hxPixels/Pixels.hx"
		return b;
	}
	
	
}

