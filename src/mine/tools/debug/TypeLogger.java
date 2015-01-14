
package mine.tools.debug;



import java.util.Calendar;


/**
 * 보기 편한 기록을 남기도록 도와준다.
 * @author mrminer
 *
 */
public class TypeLogger {

	private boolean isOn;


	/**
	 * 디버거를 사용할지 설정한다.
	 * @param use true 라면 TypeDebug 의 여러 메서드들이 동작하도록 해준다. false 라면 TypeDebug 의 메서드들이 동작하지 않게 한다.
	 * 
	 */
	public void setUseLogger(final boolean use){
		this.isOn = use;
	}

	/**
	 * @return 디버거의 사용이 허용 되었는지의 여부를 반환한다.
	 */
	public boolean hasUseLogger(){
		return this.isOn;
	}


	/**
	 * 각 변수는 캘린더 에서 추출해온다. 표기 포멧은 년 / 월 / 일 / 시 / 분 / 초 \n
	 */
	int year;
	int month;
	int day;
	int hour;
	int min;
	long sec;

	String errMsg;

	public Calendar c;

	/**
	 * 원자성을 보장합니다.
	 */
	private volatile static TypeLogger me;

	/**
	 * 단일성을 위한 싱글턴 메서드
	 * @return this
	 */
	public static TypeLogger getInstance(){
		if(TypeLogger.me == null){
			synchronized(TypeLogger.class){
				if(TypeLogger.me == null){
					TypeLogger.me = new TypeLogger();
				}
			}
		}
		return TypeLogger.me;
	}

	/**
	 * 상수들은 각각 분기점이 되는 것들을 기록한 상수들이다.
	 */
	public String mTag;
	public final static String Logger_TAG_ACTIVITY = "<Activity> ";
	public final static String Logger_TAG_EXCEPTION = "<Exception> ";
	public final static String Logger_TAG_GL = "<GL> ";
	public final static String Logger_TAG_LOGGER = "<Logger> ";

	/**
	 * 메인 엑티비티에서 생성하고 활성화 메서드를 통해 작동. 분기점마다 호출이 가능하며 원하는 상황에 원하는 것을 덤프나 크래쉬 포인트,
	 * 메시지 , 로그등을 저장, 출력이 가능하게 한다.
	 * 
	 * @author mrminer
	 * @param GL
	 *            Log 의 본체를 설정.
	 */
	public TypeLogger( ) {

		this.c = Calendar.getInstance( );

		this.year = this.c.get( Calendar.YEAR );
		this.month = this.c.get( Calendar.MONTH );
		this.day = this.c.get( Calendar.DAY_OF_MONTH );
		this.hour = this.c.get( Calendar.HOUR_OF_DAY );
		this.min = this.c.get( Calendar.MINUTE );
		this.sec = this.c.get( Calendar.SECOND );

		System.out.print( this.getMsgString( this , TypeLogger.Logger_TAG_ACTIVITY , "All Ready" ) );

	}
	
	/**
	 * 인자에 들어온 클래스의 패키지명과 클래스명을 알아낸다.
	 * @param o
	 * @return 클래스의 패키지와 클래스 명 
	 */
	public String getClassNameSting(final Object o){
		return o.getClass( ).getCanonicalName( );
	}

	/**
	 * 이 메서드는 날짜 포멧을 만들어 반환한다.
	 * 
	 * @return
	 */
	public String getDataString( ) {

		return "#================================== \n" + this.year + " / " + this.month
				+ " / " + this.day + " / " + this.hour + "/ " + this.min + "/ " + this.sec + "/ \n";
	}

	/**
	 * 포멧에 맞도록 인수를 배치하여 문자열로 보내준다.
	 * 
	 * @param Type
	 * @param Msg
	 * @return
	 */
	public String getMsgString(final Object o ,final String Type, final String Msg) {

		return this.getDataString( ) + this.getClassNameSting( o ) + "\n" + Type + " Message is : " + Msg
				+ "\n#==================================\n";
	}



	/**
	 * 메시지를 출력한다.
	 * @param o
	 * 			분류
	 * @param Tag
	 * 			종류
	 * @param Msg
	 * 			출력하고자 하는 메세지
	 */
	public void printMsg(final Object o , final String Tag , final String Msg ){
		if(this.isOn){
			System.out.println( this.getMsgString( o , Tag , Msg ) );
		}
	}


}
