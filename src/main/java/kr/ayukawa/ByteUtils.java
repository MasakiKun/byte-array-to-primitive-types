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
	 * byte array를 float으로 변환한다. 넘겨받은 byte array가 null이거나 length가 8 미만이라면 IllegalArgumentException이 발생한다
	 * @param data
	 * @return
	 */
	public static float toFloat(byte[] data) {
		if(data == null || data.length < 4) throw new IllegalArgumentException("given byte array is null or length less then 4");
		return Float.intBitsToFloat(toInt(data));
	}

	public static double toDouble(byte[] data) {
		if(data == null || data.length < 8) throw new IllegalArgumentException("given byte array is null or length less then 8");
		return Double.longBitsToDouble(toLong(data));
	}
}
