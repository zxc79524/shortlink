package idv.blake.shortlink.util;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class ConvertUtilTest {

	@Test
	void testHex10To62() {
		String result = ConvertUtil.hex10To62(0);
		assertEquals("0", result);

		result = ConvertUtil.hex10To62(1);
		assertEquals("1", result);

		result = ConvertUtil.hex10To62(10);
		assertEquals("A", result);
		result = ConvertUtil.hex10To62(35);
		assertEquals("Z", result);

		result = ConvertUtil.hex10To62(36);
		assertEquals("a", result);
		result = ConvertUtil.hex10To62(61);
		assertEquals("z", result);

		result = ConvertUtil.hex10To62(62);
		assertEquals("10", result);

		result = ConvertUtil.hex10To62(63);
		assertEquals("11", result);

		result = ConvertUtil.hex10To62(72);
		assertEquals("1A", result);

	}

}
