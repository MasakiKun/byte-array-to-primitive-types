package kr.ayukawa;

/**
 * byte array ↔ primitive type 간 변환을 위해 만든 유틸리티 클래스
 * https://www.daniweb.com/programming/software-development/code/216874/primitive-types-as-byte-arrays 문서를 보고
 * 이 중 primitive type의 배열은 제외하고 작성하였다.
 */
public class ByteUtils {
	// primitive types to byte array

	/**
	 * byte를 byte array로 변환한다
	 * @param data
	 * @return
	 */
	public static byte[] toByteArrays(byte data) {
		return new byte[] { data };
	}

	/**
	 * short를 byte array로 변환한다
	 * @param data
	 * @return
	 */
	public static byte[] toByteArrays(short data) {
		return new byte[] {
				(byte)((data >> 8) & 0xff),
				(byte)((data >> 0) & 0xff)
		};
	}

	/**
	 * char를 byte array로 변환한다
	 * @param data
	 * @return
	 */
	public static byte[] toByteArrays(char data) {
		return new byte[] {
				(byte)((data >> 8) & 0xff),
				(byte)((data >> 0) & 0xff)
		};
	}

	/**
	 * int를 byte array로 변환한다
	 * @param data
	 * @return
	 */
	public static byte[] toByteArrays(int data) {
		return new byte[] {
				(byte)((data >> 24) & 0xff),
				(byte)((data >> 16) & 0xff),
				(byte)((data >> 8) & 0xff),
				(byte)((data >> 0) & 0xff),
		};
	}

	/**
	 * long을 byte array로 변환한다
	 * @param data
	 * @return
	 */
	public static byte[] toByteArrays(long data) {
		return new byte[] {
				(byte)((data >> 56) & 0xff),
				(byte)((data >> 48) & 0xff),
				(byte)((data >> 40) & 0xff),
				(byte)((data >> 32) & 0xff),
				(byte)((data >> 24) & 0xff),
				(byte)((data >> 16) & 0xff),
				(byte)((data >> 8) & 0xff),
				(byte)((data >> 0) & 0xff),
		};
	}

	/**
	 * float을 byte array로 변환한다
	 * @param data
	 * @return
	 */
	public static byte[] toByteArrays(float data) {
		return toByteArrays(Float.floatToRawIntBits(data));
	}

	/**
	 * double을 byte array로 변환한다
	 * @param data
	 * @return
	 */
	public static byte[] toByteArrays(double data) {
		return toByteArrays(Double.doubleToRawLongBits(data));
	}

	// byte array to primitive type

	/**
	 * byte array를 byte로 변환한다. 넘겨받은 byte array가 null이거나, length가 1 미만일 경우 IllegalArgumentException이 발생한다
	 * @param data
	 * @return
	 * 전달받은 배열의 0번째 값을 반환한다
	 */
	public static byte toByte(byte[] data) {
		if(data == null || data.length < 1) throw new IllegalArgumentException("given byte array is null or length less than 1");

		return data[0];
	}

	/**
	 * byte array를 short로 변환한다. 넘겨받은 byte array가 null이거나, length가 2 미만일 경우 IllegalArgumentException이 발생한다
	 * @param data
	 * @return
	 */
	public static short toShort(byte[] data) {
		if(data == null || data.length < 2) throw new IllegalArgumentException("given byte array is null or length less than 2");

		return (short)(
				(0xff & data[0]) << 8 |
				(0xff & data[1]) << 0
		);
	}

	/**
	 * 2개의 byte를 전달받아 short로 변환한다
	 * @param value1
	 * @param value2
	 * @return
	 */
	public static short toShort(byte value1, byte value2) {
		return (short)(
				(0xff & value1) << 8 |
				(0xff & value2) << 0
		);
	}

	/**
	 * byte array를 char로 변환한다. 넘겨받은 byte array가 null이거나, length가 2 미만일 경우 IllegalArgumentException이 발생한다
	 * @param data
	 * @return
	 */
	public static char toChar(byte[] data) {
		if(data == null || data.length < 2) throw new IllegalArgumentException("given byte array is null or length less then 2");

		return (char)(
				(0xff & data[0]) << 8 |
				(0xff & data[1]) << 0
		);
	}

	/**
	 * 2개의 byte를 전달받아, char로 변환한다
	 * @param value1
	 * @param value2
	 * @return
	 */
	public static char toChar(byte value1, byte value2) {
		return (char)(
				(0xff & value1) << 8 |
				(0xff & value2) << 0
		);
	}

	/**
	 * byte array를 int로 변환한다. 넘겨받은 byte array가 null이거나, length가 4 미만일 경우 IllegalArgumentException이 발생한다
	 * @param data
	 * @return
	 */
	public static int toInt(byte[] data) {
		if(data == null || data.length < 4) throw new IllegalArgumentException("given byte array is null or length less than 4");

		return (int)(
				(0xff & data[0]) << 24 |
				(0xff & data[1]) << 16 |
				(0xff & data[2]) << 8 |
				(0xff & data[3]) << 0
		);
	}

	/**
	 * 4개의 byte를 전달받아 int로 변환한다
	 * @param value1
	 * @param value2
	 * @param value3
	 * @param value4
	 * @return
	 */
	public static int toInt(byte value1, byte value2, byte value3, byte value4) {
		return (int)(
				(0xff & value1) << 24 |
				(0xff & value2) << 16 |
				(0xff & value3) << 8 |
				(0xff & value4)
		);
	}

	/**
	 * byte array를 long으로 변환한다. 넘겨받은 byte array가 null이거나, length가 8 미만이라면 IllegalArgumentException이 발생한다
	 * @param data
	 * @return
	 */
	public static long toLong(byte[] data) {
		if(data == null || data.length < 8) throw new IllegalArgumentException("given byte array is null or length less then 8");

		return (long)(
				(long)(0xff & data[0]) << 56 |
				(long)(0xff & data[1]) << 48 |
				(long)(0xff & data[2]) << 40 |
				(long)(0xff & data[3]) << 32 |
				(long)(0xff & data[4]) << 24 |
				(long)(0xff & data[5]) << 16 |
				(long)(0xff & data[6]) << 8 |
				(long)(0xff & data[7])
		);
	}

	/**
	 * 8개의 byte를 전달받아 long으로 변환한다
	 * @param value1
	 * @param value2
	 * @param value3
	 * @param value4
	 * @param value5
	 * @param value6
	 * @param value7
	 * @param value8
	 * @return
	 */
	public static long toLong(byte value1, byte value2, byte value3, byte value4, byte value5, byte value6, byte value7, byte value8) {
		return (long)(
				(long)(0xff & value1) << 56 |
				(long)(0xff & value2) << 48 |
				(long)(0xff & value3) << 40 |
				(long)(0xff & value4) << 32 |
				(long)(0xff & value5) << 24 |
				(long)(0xff & value6) << 16 |
				(long)(0xff & value7) << 8 |
				(long)(0xff & value8)
		);
	}

	/**
	 * byte array를 float으로 변환한다. 넘겨받은 byte array가 null이거나 length가 8 미만이라면 IllegalArgumentException이 발생한다
	 * @param data
	 * @return
	 */
	public static float toFloat(byte[] data) {
		if(data == null || data.length < 4) throw new IllegalArgumentException("given byte array is null or length less then 4");
		return Float.intBitsToFloat(toInt(data));
	}

	/**
	 * 4개의 byte를 전달받아 float로 변환한다
	 * @param value1
	 * @param value2
	 * @param value3
	 * @param value4
	 * @return
	 */
	public static float toFloat(byte value1, byte value2, byte value3, byte value4) {
		return Float.intBitsToFloat(toInt(value1, value2, value3, value4));
	}

	/**
	 * byte array를 전달받아 double로 변환한다. 넘겨받은 byte array가 null이거나 length가 8 미만이라면 IllegalArgumentException이 발생한다
	 * @param data
	 * @return
	 */
	public static double toDouble(byte[] data) {
		if(data == null || data.length < 8) throw new IllegalArgumentException("given byte array is null or length less then 8");
		return Double.longBitsToDouble(toLong(data));
	}

	/**
	 * 8개의 byte를 전달받아 double로 변환한다
	 * @param value1
	 * @param value2
	 * @param value3
	 * @param value4
	 * @param value5
	 * @param value6
	 * @param value7
	 * @param value8
	 * @return
	 */
	public static double toDouble(byte value1, byte value2, byte value3, byte value4,
								  byte value5, byte value6, byte value7, byte value8) {
		return Double.longBitsToDouble(toLong(value1, value2, value3, value4, value5, value6, value7, value8));
	}
}
