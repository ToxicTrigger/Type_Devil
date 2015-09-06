package mine.typed.core.Type;

import android.os.Debug;
import android.util.Log;

/**
 * 변수들을 관리하기 편하게 하는 클래스. 버퍼에 보호하고자 하는 변수를 담아두는 원리. 값들을 복호화 암호화 하여 저장할수 있음.
 * 
 * @author mrminer
 * 
 */
public class TypeData
{
	// 변수 백업용 변수
	protected int BackUpint;
	protected long BackUplong;
	protected float BackUpfloat;
	protected double BackUpdouble;
	protected short BackUpshort;
	protected boolean BackUpboolean;
	protected byte BackUpbyte;
	protected Object BackUpobj;
	protected String BackUpstr;

	/**
	 * ARGB 컬러의 최대값
	 */
	public final static int ARGB_MAX_INT = 255;

	// 이벤트 처리용
	protected static boolean m_post;
	protected static boolean m_read;

	// 커서
	protected static Object cuser;

	private volatile static TypeData me;

	private long now;
	private int framesCount = 0;
	private int framesCountAvg = 0;
	private long framesTimer = 0;

	// ===================================================================

	public static TypeData getInstance()
	{
		if( TypeData.me == null )
		{
			synchronized( TypeData.class )
			{
				if( TypeData.me == null )
				{
					TypeData.me = new TypeData();
				}
			}
		}
		return TypeData.me;
	}

	public TypeData()
	{

		// 사용을 위한 초기화
		this.BackUpint = 0;
		this.BackUplong = 0;
		this.BackUpfloat = 0;
		this.BackUpdouble = 0;
		this.BackUpshort = 0;
		this.BackUpboolean = false;
		this.BackUpbyte = 0;
		this.BackUpstr = new String();
		this.BackUpobj = new Object();
		TypeData.m_post = false;
		TypeData.m_read = false;
		TypeData.cuser = new Object();

	}

	// ===================================================================
	// =======================변수 확인 메서드===============================

	// 목적 변수의 값을 확인해서 값이 달라지면 true 를 같으면 false 를 던집니다.
	public boolean checkVariable(final int variable)
	{

		if( this.BackUpint == variable )
		{
			return true;
		} else
		{
			return false;
		}
	}

	public boolean checkVariable(final String variable)
	{

		if( this.BackUpstr == variable )
		{
			return true;
		} else
		{
			return false;
		}
	}

	public boolean checkVariable(final long variable)
	{

		if( this.BackUplong == variable )
		{
			return true;
		} else
		{
			return false;
		}
	}

	public boolean checkVariable(final double variable)
	{

		if( this.BackUpdouble == variable )
		{
			return true;
		} else
		{
			return false;
		}
	}

	public boolean checkVariable(final float variable)
	{

		if( this.BackUpfloat == variable )
		{
			return true;
		} else
		{
			return false;
		}
	}

	public boolean checkVariable(final boolean variable)
	{

		if( this.BackUpboolean == variable )
		{
			return true;
		} else
		{
			return false;
		}
	}

	public boolean checkVariable(final byte variable)
	{

		if( this.BackUpbyte == variable )
		{
			return true;
		} else
		{
			return false;
		}
	}

	public boolean checkVariable(final short variable)
	{

		if( this.BackUpshort == variable )
		{
			return true;
		} else
		{
			return false;
		}
	}

	public boolean checkVariable(final Object variable)
	{

		if( this.BackUpobj == variable )
		{
			return true;
		} else
		{
			return false;
		}
	}

	// ===================================================================
	// =======================변수에 값을 대입하는 메서드=====================
	// 기존 변수에 BackUp 시리즈 변수를 대입합니다.
	// 바뀐 변수를 던집니다.
	public int getVariable(int variable)
	{

		variable = this.BackUpint;
		return variable;
	}

	public String getVariable(String variable)
	{

		variable = this.BackUpstr;
		return variable;
	}

	public long getVariable(long variable)
	{

		variable = this.BackUplong;
		return variable;
	}

	public float getVariable(float variable)
	{

		variable = this.BackUpfloat;
		return variable;
	}

	public double getVariable(double variable)
	{

		variable = this.BackUpdouble;
		return variable;
	}

	public byte getVariable(byte variable)
	{

		variable = this.BackUpbyte;
		return variable;
	}

	public boolean getVariable(boolean variable)
	{

		variable = this.BackUpboolean;
		return variable;
	}

	public short getVariable(short variable)
	{

		variable = this.BackUpshort;
		return variable;
	}

	public Object getVariable(Object variable)
	{

		variable = this.BackUpobj;
		return variable;
	}

	// ===================================================================
	// =========================변수값을 대입==============================
	// 변수를 백업용 변수에 저장 합니다.
	public void setVariable(final int variable)
	{

		this.BackUpint = variable;
	}

	public void setVariable(final String variable)
	{

		this.BackUpstr = variable;
	}

	public void setVariable(final long variable)
	{

		this.BackUplong = variable;
	}

	public void setVariable(final byte variable)
	{

		this.BackUpbyte = variable;
	}

	public void setVariable(final short variable)
	{

		this.BackUpshort = variable;
	}

	public void setVariable(final float variable)
	{

		this.BackUpfloat = variable;
	}

	public void setVariable(final double variable)
	{

		this.BackUpdouble = variable;
	}

	public void setVariable(final boolean variable)
	{

		this.BackUpboolean = variable;
	}

	public void setVariable(final Object variable)
	{

		this.BackUpobj = variable;
	}

	// ///////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////

	/**
	 * 이벤트를 설정합니다.
	 * 
	 * @param 설정될
	 *            값
	 */
	public static void setEvent(final boolean onoff)
	{

		TypeData.m_post = onoff;
	}

	// 이벤트의 상태를 가져옵니다.
	/**
	 * @return 이벤트의 값을 가져옵니다.
	 */
	public static boolean getEvent()
	{

		return TypeData.m_post;
	}

	// 이벤트를 완료합니다.
	/**
	 * NOT 연산을 통해 이벤트 값을 조정 합니다.
	 * 
	 * @return 이벤트의 상태가 true 라면 이벤트의 값을 false 로 변경하고 true 를 출력합니다. 반대로 이벤트가
	 *         false 라면 true 로 변경하여 true 를 출력 합니다.
	 */
	public static boolean eventComplete()
	{

		if( TypeData.m_post )
		{
			TypeData.m_post = false;
			return true;
		} else
		{
			TypeData.m_post = true;
			return TypeData.m_post;
		}
	}

	// 커서를 설정 합니다.
	/**
	 * 커서에 인자를 대입 합니다.
	 * 
	 * @param data
	 */
	public static void setCuser(final Object data)
	{

		TypeData.cuser = data;
	}

	// 커서의 값을 돌려줍니다.
	/**
	 * setCuser(Object) 로 설정된 값을 반환 합니다.
	 * 
	 * @return
	 */
	public static Object getCuser()
	{

		return TypeData.cuser;
	}

	//
	/**
	 * 현재의 모든 백업 변수의 값을 로그로 출력합니다.
	 */
	public void outData()
	{

		Log.i("<PTData>", "<PTData> int is " + this.BackUpint);
		Log.i("<PTData>", "<PTData> boolean is " + this.BackUpboolean);
		Log.i("<PTData>", "<PTData> short is " + this.BackUpshort);
		Log.i("<PTData>", "<PTData> byte is " + this.BackUpbyte);
		Log.i("<PTData>", "<PTData> Object is " + this.BackUpobj.toString());
		Log.i("<PTData>", "<PTData> long is " + this.BackUplong);
		Log.i("<PTData>", "<PTData> float is " + this.BackUpfloat);
		Log.i("<PTData>", "<PTData> double is " + this.BackUpdouble);

	}

	/**
	 * 최대 사용 가능한 메모리의 양과 현재 사용중인 메모리의 양을 로그로 남깁니다.
	 */
	public void getMemory()
	{

		Log.i("<PTData>", "NativeHeapSize is " + Debug.getNativeHeapSize());
		Log.i("<PTData>", "NativeHeapFreeSize is " + Debug.getNativeHeapFreeSize());
	}

	/**
	 * 그려지는 프레임의 수를 알아냅니다.
	 * 
	 * @return 초당 그려지는 프레임의 갯수
	 */
	public float getFPS()
	{

		this.now = System.currentTimeMillis();
		this.framesCount++;
		if( (this.now - this.framesTimer) > 1000 )
		{
			this.framesTimer = this.now;
			this.framesCountAvg = this.framesCount;
			this.framesCount = 0;
		}
		return this.framesCountAvg;
	}

	//
	//
	/**
	 * 랜덤 값을 반환합니다.
	 * 
	 * @return 0 ~ 9 까지의 int 형
	 */
	public static int getRandom()
	{

		double r = Math.random();
		r *= 10;
		return (int) r;
	}

	public String getBackUpint()
	{

		return Long.toBinaryString(this.BackUpint);
	}

	public String getBackUplong()
	{

		return Long.toBinaryString(this.BackUplong);
	}

	/**
	 * Float Hex
	 * 
	 * @return
	 */
	public String getBackUpfloat()
	{
		return Float.toHexString(this.BackUpfloat);
	}

	/**
	 * Double hex
	 * 
	 * @return
	 */
	public String getBackUpdouble()
	{

		return Double.toHexString(this.BackUpdouble);
	}

	public String getBackUpshort()
	{

		return Long.toBinaryString(this.BackUpshort);
	}

	public String isBackUpboolean()
	{

		return Boolean.toString(this.BackUpboolean);
	}

	/**
	 * Long 8진수
	 * 
	 * @return
	 */
	public String getBackUpbyte()
	{

		return Long.toOctalString(this.BackUpbyte);
	}

	public Object getBackUpobj()
	{

		return this.BackUpobj;
	}

	public String getBackUpstr()
	{

		return this.BackUpstr;
	}

	public void callGC()
	{
		System.gc();
	}

	@Override
	public int hashCode()
	{

		final int prime = 31;
		int result = 1;
		result = (prime * result) + (this.BackUpboolean ? 1231 : 1237);
		result = (prime * result) + this.BackUpbyte;
		long temp;
		temp = Double.doubleToLongBits(this.BackUpdouble);
		result = (prime * result) + (int) (temp ^ (temp >>> 32));
		result = (prime * result) + Float.floatToIntBits(this.BackUpfloat);
		result = (prime * result) + this.BackUpint;
		result = (prime * result) + (int) (this.BackUplong ^ (this.BackUplong >>> 32));
		result = (prime * result) + ((this.BackUpobj == null) ? 0 : this.BackUpobj.hashCode());
		result = (prime * result) + this.BackUpshort;
		result = (prime * result) + ((this.BackUpstr == null) ? 0 : this.BackUpstr.hashCode());
		result = (prime * result) + this.framesCount;
		result = (prime * result) + this.framesCountAvg;
		result = (prime * result) + (int) (this.framesTimer ^ (this.framesTimer >>> 32));
		result = (prime * result) + (int) (this.now ^ (this.now >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{

		if( this == obj ) return true;
		if( obj == null ) return false;
		if( !(obj instanceof TypeData) ) return false;
		final TypeData other = (TypeData) obj;
		if( this.BackUpboolean != other.BackUpboolean ) return false;
		if( this.BackUpbyte != other.BackUpbyte ) return false;
		if( Double.doubleToLongBits(this.BackUpdouble) != Double.doubleToLongBits(other.BackUpdouble) ) return false;
		if( Float.floatToIntBits(this.BackUpfloat) != Float.floatToIntBits(other.BackUpfloat) ) return false;
		if( this.BackUpint != other.BackUpint ) return false;
		if( this.BackUplong != other.BackUplong ) return false;
		if( this.BackUpobj == null )
		{
			if( other.BackUpobj != null ) return false;
		} else if( !this.BackUpobj.equals(other.BackUpobj) ) return false;
		if( this.BackUpshort != other.BackUpshort ) return false;
		if( this.BackUpstr == null )
		{
			if( other.BackUpstr != null ) return false;
		} else if( !this.BackUpstr.equals(other.BackUpstr) ) return false;
		if( this.framesCount != other.framesCount ) return false;
		if( this.framesCountAvg != other.framesCountAvg ) return false;
		if( this.framesTimer != other.framesTimer ) return false;
		if( this.now != other.now ) return false;
		return true;
	}

}
