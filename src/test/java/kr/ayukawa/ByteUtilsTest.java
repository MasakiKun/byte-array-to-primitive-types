package kr.ayukawa;

import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

import static org.junit.jupiter.api.Assertions.*;

class ByteUtilsTest {

	@Test
	void byte를_바이트배열로_변환() {
		byte data = 0b00001111;

		byte[] result = ByteUtils.toByteArrays(data);

		assertEquals(result.length, 1);
		assertEquals(result[0], data);
	}

	@Test
	void short를_바이트배열로_변환() {
		short data = 0b0000_1000_0000_1111;

		byte[] result = ByteUtils.toByteArrays(data);

		assertEquals(result.length, 2);
		assertEquals(result[0], 0b00001000);
		assertEquals(result[1], 0b00001111);
	}

	@Test
	void char를_바이트배열로_변환() {
		// 'a' == 97 == 0b_0000_0000_0110_0001
		char data = 'a';

		byte[] result = ByteUtils.toByteArrays(data);

		assertEquals(result.length, 2);
		assertEquals(result[0], 0b00000000);
		assertEquals(result[1], 0b01100001);
	}

	@Test
	void int를_바이트배열로_변환() {
		int data = 0b0111_1111_0011_0111_0001_0011_0111_1111;

		byte[] result = ByteUtils.toByteArrays(data);

		assertEquals(result.length, 4);
		assertEquals(result[0], 0b01111111);
		assertEquals(result[1], 0b00110111);
		assertEquals(result[2], 0b00010011);
		assertEquals(result[3], 0b01111111);
	}

	@Test
	void long을_바이트배열로_변환() {
		long data = 0b0111_1111___0011_1111___0001_0111___0011_1111___0111_0101___0011_1001___0111_1100____0001_0001L;

		byte[] result = ByteUtils.toByteArrays(data);

		assertEquals(result.length, 8);
		assertEquals(result[0], 0b01111111);
		assertEquals(result[1], 0b00111111);
		assertEquals(result[2], 0b00010111);
		assertEquals(result[3], 0b00111111);
		assertEquals(result[4], 0b01110101);
		assertEquals(result[5], 0b00111001);
		assertEquals(result[6], 0b01111100);
		assertEquals(result[7], 0b00010001);
	}

	@Test
	void float를_바이트배열로_변환() {
		float data = Float.intBitsToFloat(0b0111_1111___0011_1111___0001_1111___0000_1111);
// ___0000_0111___0000_0011___0000_0001___0000_0000L
		byte[] result = ByteUtils.toByteArrays(data);

		assertEquals(result.length, 4);
		assertEquals(result[0], 0b01111111);
		assertEquals(result[1], 0b00111111);
		assertEquals(result[2], 0b00011111);
		assertEquals(result[3], 0b00001111);
	}

	@Test
	void double을_바이트배열로_변환() {
		double data = Double.longBitsToDouble(0b0111_1111___0011_1111___0001_1111___0000_1111___0000_0111___0000_0011___0000_0001___0000_0000L);

		byte[] result = ByteUtils.toByteArrays(data);

		assertEquals(result.length, 8);
		assertEquals(result[0], 0b01111111);
		assertEquals(result[1], 0b00111111);
		assertEquals(result[2], 0b00011111);
		assertEquals(result[3], 0b00001111);
		assertEquals(result[4], 0b00000111);
		assertEquals(result[5], 0b00000011);
		assertEquals(result[6], 0b00000001);
		assertEquals(result[7], 0b00000000);
	}

	@Test
	void byte배열을_byte로_변환시_null이_전달되면_예외() {
		try {
			ByteUtils.toByte(null);
			fail();
		} catch(IllegalArgumentException e) {}
	}

	@Test
	void byte배열을_byte로_변환시_length가_1미만이면_예외() {
		try {
			byte[] data = new byte[] {};
			ByteUtils.toByte(data);
			fail();
		} catch(IllegalArgumentException e) {}
	}

	@Test
	void byte배열을_byte로_변환() {
		byte[] data = new byte[] { 0x50, 0x51, 0x52, 0x53 };

		byte result = ByteUtils.toByte(data);

		assertEquals(result, 0x50);
	}

	@Test
	void byte배열을_short로_변환시_null이_전달되면_예외() {
		try {
			ByteUtils.toShort(null);
			fail();
		} catch(IllegalArgumentException e) {}
	}

	@Test
	void byte배열을_short로_변환시_length가_2미만이면_예외() {
		try {
			byte[] data = new byte[]{};
			ByteUtils.toShort(data);
			fail();
		} catch(IllegalArgumentException e) {}
	}

	@Test
	void byte배열을_short로_변환() {
		byte[] data = new byte[] { (byte)0b0111_1111, (byte)0b1111_1111 };
		short result = ByteUtils.toShort(data);

		assertEquals(result, Short.MAX_VALUE);
	}

	@Test
	void byte_열을_short로_변환() {
		assertEquals(ByteUtils.toShort((byte)0b01111111, (byte)0xff), Short.MAX_VALUE);
	}

	@Test
	void byte배열을_char로_변환시_null이_전달되면_예외() {
		try {
			ByteUtils.toChar(null);
			fail();
		} catch(IllegalArgumentException e) {}
	}

	@Test
	void byte배열을_char로_변환시_length가_2미만이면_예외() {
		try {
			ByteUtils.toChar(new byte[] {});
			fail();
		} catch(IllegalArgumentException e) {}
	}

	@Test
	void byte배열을_char로_변환() {
		byte[] data = new byte[] {
				0b0000_0000, 0b0100_0001
		};
		char result = ByteUtils.toChar(data);
		assertEquals(result, 'A');
	}

	@Test
	void byte_열을_char로_변환() {
		assertEquals(ByteUtils.toChar((byte)0b0000_0000, (byte)0b0100_0001), 'A');
	}

	@Test
	void byte배열을_int로_변환시_null이_전달되면_예외() {
		try {
			ByteUtils.toInt(null);
			fail();
		} catch(IllegalArgumentException e) {}
	}

	@Test
	void byte배열을_int로_변환시_length가_4미만이면_예외() {
		try {
			ByteUtils.toInt(new byte[] {});
			fail();
		} catch(IllegalArgumentException e) {}
	}

	@Test
	void byte배열을_int로_변환() {
		byte[] data = new byte[] { (byte)0b0111_1111, (byte)0xff, (byte)0xff, (byte)0xff};
		int result = ByteUtils.toInt(data);
		assertEquals(result, Integer.MAX_VALUE);
	}

	@Test
	void byte_열을_int로_변환() {
		assertEquals(ByteUtils.toInt((byte)0b0111_1111, (byte)0xff, (byte)0xff, (byte)0xff), Integer.MAX_VALUE);
	}

	@Test
	void byte배열을_long으로_변환시_null이_전달되면_예외() {
		try {
			ByteUtils.toLong(null);
			fail();
		} catch(IllegalArgumentException e) {}
	}

	@Test
	void byte배열을_long_으로_변환시_length가_8미만이면_예외() {
		try {
			ByteUtils.toLong(new byte[] {});
			fail();
		} catch(IllegalArgumentException e) {}
	}

	@Test
	void byte배열을_long으로_변환() {
		byte[] data = new byte[] {
				0b0111_1111, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff
		};

		long result = ByteUtils.toLong(data);

		assertEquals(result, Long.MAX_VALUE);
	}

	@Test
	void byte_열을_long으로_변환() {
		assertEquals(
				ByteUtils.toLong(
						(byte)0b0111_1111,
						(byte)0xff,
						(byte)0xff,
						(byte)0xff,
						(byte)0xff,
						(byte)0xff,
						(byte)0xff,
						(byte)0xff
				),
				Long.MAX_VALUE
		);
	}

	@Test
	void byte배열을_float으로_변환시_null이_전달되면_예외() {
		try {
			ByteUtils.toFloat(null);
			fail();
		} catch(IllegalArgumentException e) {}
	}

	@Test
	void byte배열을_float으로_변환시_length가_4미만이면_예외() {
		try {
			ByteUtils.toFloat(new byte[] {});
			fail();
		} catch(IllegalArgumentException e) {}
	}

	@Test
	void byte배열을_float으로_변환() {
		byte[] data = new byte[] {
			0b0111_1111, (byte)0xff, (byte)0xff, (byte)0xff
		};
		float result = ByteUtils.toFloat(data);
		assertEquals(result, Float.intBitsToFloat(Integer.MAX_VALUE));
	}

	@Test
	void byte_열을_float으로_변환() {
		assertEquals(
				ByteUtils.toFloat(
						(byte)0b0111_1111,
						(byte)0xff,
						(byte)0xff,
						(byte)0xff
				),
				Float.intBitsToFloat(Integer.MAX_VALUE)
		);
	}

	@Test
	void byte배열을_double로_변환시_null이_전달되면_예외() {
		try {
			ByteUtils.toDouble(null);
			fail();
		} catch(IllegalArgumentException e) {}
	}

	@Test
	void byte배열을_double로_변환시_length가_8미만이면_예외() {
		try {
			ByteUtils.toDouble(new byte[] {});
			fail();
		} catch(IllegalArgumentException e) {}
	}

	@Test
	void byte배열을_double로_변환() {
		byte[] data = new byte[] {
				0b0111_1111, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff
		};
		double result = ByteUtils.toDouble(data);
		assertEquals(result, Double.longBitsToDouble(Long.MAX_VALUE));
	}

	@Test
	void byte_열을_double로_변환() {
		assertEquals(
				ByteUtils.toDouble(
						(byte)0b0111_1111,
						(byte)0xff,
						(byte)0xff,
						(byte)0xff,
						(byte)0xff,
						(byte)0xff,
						(byte)0xff,
						(byte)0xff
				),
				Double.longBitsToDouble(Long.MAX_VALUE)
		);
	}
}