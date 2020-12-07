import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

/**
 * JUnit testing for the WallysWidgets Java solution that contains the original test cases plus some additional ones.
 * 
 * @author Owen Charles
 * @version 2020/12/07
 */
public class WallysWidgetsTest {

	@Test
	public void testOrderWidgets() {
		
		// Test case -1
		assertThrows(IllegalArgumentException.class, () -> {WallysWidgets.orderWidgets(-1);});
		
		
		// Test case = 0
		assertThrows(IllegalArgumentException.class, () -> {WallysWidgets.orderWidgets(0);});

		
		// Test case = 1
		final int[] expectedOne = {0, 0, 0, 0, 1};
		assertArrayEquals(expectedOne, WallysWidgets.orderWidgets(1));
		
		// Test case = 2 
		final int[] expectedTwo = {0, 0, 0, 0, 1};
		assertArrayEquals(expectedTwo, WallysWidgets.orderWidgets(2));
		
		// Test case = 249
		final int[] expectedThree = {0, 0, 0, 0, 1};
		assertArrayEquals(expectedThree, WallysWidgets.orderWidgets(249));		
		
		// Test case = 250
		final int[] expectedFour = {0, 0, 0, 0, 1};
		assertArrayEquals(expectedFour, WallysWidgets.orderWidgets(250));

		// Test case = 251
		final int[] expectedFive = {0, 0, 0, 1, 0};
		assertArrayEquals(expectedFive, WallysWidgets.orderWidgets(251));
		
		// Test case = 499
		final int[] expectedSix = {0, 0, 0, 1, 0};
		assertArrayEquals(expectedSix, WallysWidgets.orderWidgets(499));
		
		// Test case = 500
		final int[] expectedSeven = {0, 0, 0, 1, 0};
		assertArrayEquals(expectedSeven, WallysWidgets.orderWidgets(500));
		
		//Test case = 501
		final int[] expectedEight = {0, 0, 0, 1, 1};
		assertArrayEquals(expectedEight, WallysWidgets.orderWidgets(501));
		
		// Test case = 11_999
		final int[] expectedNine = {2, 1, 0, 0, 0};
		assertArrayEquals(expectedNine, WallysWidgets.orderWidgets(11_999));
		
		// Test case = 12_000
		final int[] expectedTen = {2, 1, 0, 0, 0};
		assertArrayEquals(expectedNine, WallysWidgets.orderWidgets(12_000));
		
		//Test case = 12_001
		final int[] expectedEleven = {2, 1, 0, 0, 1};
		assertArrayEquals(expectedEleven, WallysWidgets.orderWidgets(12_001));
		
	}
}
