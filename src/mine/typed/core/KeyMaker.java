package mine.typed.core;

import mine.typed.core.Type.TypeData;

/**
 *  변수의 값이 변경되어도 빠르게 복구가 가능하게 해주는 클래스입니다.
 * @author mrminer
 */
public class KeyMaker {

	double ore;
	double mould01 , mould02;
	double keyhole;

	TypeData td;

	/**
	 * @param keyhole 저장할 기초 값
	 */
	public KeyMaker( double keyhole ) {

		this.td = TypeData.getInstance( );
		this.ore = 0;
		this.mould01 = 0;
		this.mould02 = 0;
		this.keyhole = keyhole;

		this.genOre();
		this.genMould();
		this.Match();
	}
	/**
	 * 보안을 위해 직접 return 하지 않는다.
	 * 값은 ore 변수에 저장됩니다.
	 * 1 순위 호출 대상입니다.
	 */
	public void genOre(){
		final double tmpore1 =  Math.random();
		final double tmpore2 =  Math.random( );
		this.ore = TypeData.getRandom( )+(1 * tmpore1 * tmpore2);
		TypeData.setCuser( this.ore );
	}

	/*
	 * mould 를 생성합니다.
	 * 2 순위 호출 대상 입니다.
	 */
	public void genMould(){
		this.mould01 = this.keyhole - this.ore;
		this.mould02 = this.keyhole + this.ore;
	}

	public String getKeyHole(){
		this.td.setVariable( this.keyhole );
		return this.td.getBackUpdouble( );
	}

	public String getMould01(){
		this.td.setVariable( this.mould01 );
		return this.td.getBackUpdouble( );
	}

	public String getMould02(){
		this.td.setVariable( this.mould02 );
		return this.td.getBackUpdouble( );
	}

	public String getOre(){
		this.td.setVariable( this.ore );
		return this.td.getBackUpdouble( );
	}

	/**
	 * 보안 키들의 값을 검사하여 keyhole 의 값을 복구 합니다.
	 * 3 순위 호출 대상 입니다.
	 */
	public void Match(){
		if(this.ore != Double.parseDouble( TypeData.getCuser( ).toString( ) ) ){
			this.ore = Double.parseDouble( TypeData.getCuser( ).toString( ) );
		}

		if((this.keyhole != (this.mould01 + this.ore)) && (this.keyhole != (this.mould02 - this.ore))){
			this.keyhole = this.mould01 + this.ore;
		}
		if(this.mould01 != (this.keyhole - this.ore)){
			this.mould01 = this.keyhole - this.ore;
		}
		if(this.mould02 != (this.keyhole + this.ore)){
			this.mould02 = this.keyhole + this.ore;
		}

	}

	/**
	 * 모든 값을 초기화 합니다.
	 */
	public void deleteDate(){
		this.keyhole = 0;
		this.mould01 = 0;
		this.mould02 = 0;
		this.ore = 0;
	}

	/**
	 * keyhole 을 새로 생성합니다.
	 * @param 새로 저장할 기초 값
	 */
	public void reMake(double keyhole){
		this.deleteDate();
		this.keyhole = keyhole;
		this.genOre();
		this.genMould();
		this.Match();
	}
	
	@Override
	public int hashCode( ) {

		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits( this.keyhole );
		result = (prime * result) + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits( this.mould01 );
		result = (prime * result) + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits( this.mould02 );
		result = (prime * result) + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits( this.ore );
		result = (prime * result) + (int) (temp ^ (temp >>> 32));
		result = (prime * result) + ((this.td == null) ? 0 : this.td.hashCode( ));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {

		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( !(obj instanceof KeyMaker) )
			return false;
		final KeyMaker other = (KeyMaker) obj;
		if ( Double.doubleToLongBits( this.keyhole ) != Double.doubleToLongBits( other.keyhole ) )
			return false;
		if ( Double.doubleToLongBits( this.mould01 ) != Double.doubleToLongBits( other.mould01 ) )
			return false;
		if ( Double.doubleToLongBits( this.mould02 ) != Double.doubleToLongBits( other.mould02 ) )
			return false;
		if ( Double.doubleToLongBits( this.ore ) != Double.doubleToLongBits( other.ore ) )
			return false;
		if ( this.td == null ) {
			if ( other.td != null )
				return false;
		} else if ( !this.td.equals( other.td ) )
			return false;
		return true;
	}



}
