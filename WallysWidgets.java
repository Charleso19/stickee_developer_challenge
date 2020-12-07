import java.util.Arrays;

/**
 * The WallysWidgets class is a Java solution for the Wally's Widgets problem as part of the Stickee Developer
 * Challenge. The solution is a 'standalone' class and can be run via the main method within this class.
 * 
 * The solution is consistent with the following logic:
 *  
 *  1. Only whole packs can be sent. Packs cannot be broken open.
 *  
 *	2. Within the constraints of Rule 1 above, send out no more widgets than necessary to fulfil
 *	   the order.
 *
 *	3. Within the constraints of Rules 1 and 2 above, send out as few packs as possible to fulfil each
 *	   order.
 * 
 * 
 * Precondition 1: The int array 'PACKAGES' MUST be sorted in descending order.
 * Precondition 2: WIDGETS_ORDERED MUST be above 0. That is to say, one cannot order 0 or a negative number of widgets.
 * 
 * 
 * A note on flexibility:
 * 
 * The programmer need only change the PACKAGES variable (at the top of the program) to meet the flexibility
 * requirements of the challenge; one can add/delete packages and/or adjust package sizes. The amount of widgets that
 * the programmer/user wishes to order can be altered via modifying the WIDGETS_ORDERED variable within the main method.
 * 
 * 
 * Evaluation:
 * 
 * The procedural-style of the class allows for simplicity and minimal alterations if one wishes to change how many
 * packages there are available to order or their size. However, in order to aid extensibility, I would transform the
 * program from a Procedural paradigm to an Object-Oriented one. For example, the WallysWidgets class could be composed
 * of (has-a) a List of Package instances. This would allow for extra details to be added to the packages, such as
 * price for example.
 * 
 * @author Owen Charles
 * @version 2020/12/06
 */
public class WallysWidgets {
	
	/* The static variable PACKAGES can be easily modified here and represents the available packages.
	 * N.B. It MUST be in descending order.
	 */
	public static final int[] PACKAGES 		    = { 5000, 2000, 1000, 500, 250 };
	public static final int   PACKAGES_LENGTH   =   PACKAGES.length			    ;
	
	/**
	 * The orderPacks() method iterates through the PACKAGES array and selects packages for delivery based on logical
	 * rules 1 and 2, mainly ensuring no more widgets than necessary are used to fulfil the order.
	 * 
	 * The orderPacks() method is a helper method for the orderWidgets() method, and is therefore private as a
	 * class/object calling this method instead of orderWidgets() may not receive the most optimal and correct
	 * solution.  
	 * 
	 * @param  WIDGETS_TO_BE_ORDERED is of type int and represents the amount of widgets ordered by the customer.
	 * 
	 * @return An int array of preliminary packages that should be delivered in order to fulfil the widget order. The
	 * 		   array of packages must be accepted via the orderWidgets() method.
	 */
	private static int[] orderPacks(final int WIDGETS_TO_BE_ORDERED) {
		
		/* PACKAGES_TO_ORDER is initialised as an int array with length equal to the length of PACKAGES and contains at
		 * first only 0s. Additionally, each index of PACKAGES_TO_ORDER is matched with the index of PACKAGES.
		 * 
		 * 
		 * Example One: 
		 * 
		 * Index			     0     1     2    3    4
		 * PACKAGES          = {5000, 2000, 1000, 500, 250};
		 * PACKAGES_TO_ORDER = {   0,    0,    0,   0,   0};
		 * 
		 * 
		 * Therefore, PACKAGES_TO_ORDER[i] represents the amount of packages ordered of type PACKAGE[i].
		 * 
		 * 
		 * Example Two:
		 *
		 * Index                 0     1     2    3    4
		 * PACKAGES          = {5000, 2000, 1000, 500, 250};
		 * PACKAGES_TO_ORDER = {   2,    1,    0,   0,   1};
		 * 
		 * 
		 * Example Two represents 5000 x 2, 2000 x 1, and 250 x 1 packages, which results in a total of 12_250 widgets.
		 */
		final int[] PACKAGES_TO_ORDER = new int[PACKAGES_LENGTH];
		
		int totalWidgetsOrdered = 0;
		int pointer 			= 0;
		
		/* The use of a while loop allows us to iterate through the PACKAGES array from largest pack to the smallest
		 * pack. A while loop has been utilised with a pointer variable instead of a for loop as the pointer should
		 * not be increased for each iteration. Although possible to negate the effects of an automatically incrementing
		 * pointer within a for loop, it decreases readability and can be overly confusing. The while loop here allows
		 * for both greater control of the pointer and greater readability. 
		 */
		while (true) {
			
			/* If the total widgets ordered plus the widgets from PACKAGES[pointer] exceeds the initial order, and it is
			 * NOT the minimal package, then try again with the next package down in size.
			 */
			if (totalWidgetsOrdered + PACKAGES[pointer] > WIDGETS_TO_BE_ORDERED
				&& pointer < PACKAGES_LENGTH - 1) {
				
				pointer++;
				continue;
			
			/* Else, if the total widgets ordered plus the widgets from PACKAGES[pointer] has exceeded the initial order
			 * and we have arrived at the minimal package, then add the package to be ordered and break as we cannot
			 * get a smaller package.
			 */
			} else if (totalWidgetsOrdered + PACKAGES[pointer] > WIDGETS_TO_BE_ORDERED
					   && pointer == PACKAGES_LENGTH - 1) {
				
				PACKAGES_TO_ORDER[pointer]++;
				break;
			
			/* Else, if the total widgets ordered plus the widgets from PACKAGES[pointer] equal the widgets to order,
			 * then add this package for ordering and break.
			 */
			} else if (totalWidgetsOrdered + PACKAGES[pointer] == WIDGETS_TO_BE_ORDERED) {
				
				PACKAGES_TO_ORDER[pointer]++;
				break;
				
			/* Else, the total widgets ordered plus the widgets from PACKAGES[pointer] will not exceed the initial
			 * order and we therefore select this package to be ordered as part of the widget order.
			 */
			} else {
				
				PACKAGES_TO_ORDER[pointer]++            ;
				totalWidgetsOrdered += PACKAGES[pointer];
				
			}
		}
		
		return PACKAGES_TO_ORDER;
	}
	
	/**
	 * The orderWidget method takes the amount of widgets to be ordered as a single argument and returns the packages
	 * that must be delivered to fulfil the order. The method utilises the orderPacks() method to calculate and refine
	 * the minimum packs and widgets needed. 
	 * 
	 * @param  ORIGINAL_WIDGETS_TO_BE_ORDERED is of type int and represents the amount of widgets the customer has
	 * 		   ordered.
	 * 
	 * @return An int array indicating the which packages of widgets are needed to fulfil the order within the
	 * 		   constraints of the three logical rules.
	 */
	public static int[] orderWidgets(final int ORIGINAL_WIDGETS_TO_BE_ORDERED) {
		
		// Defensive programming.
		if (ORIGINAL_WIDGETS_TO_BE_ORDERED <= 0) {
			
			throw new IllegalArgumentException("You cannot order 0 or below widgets.");
		}
		
		/* A is at first the original amount of widgets ordered, such as 12_001, or 251 etc. Later, it is updated to the
		 * minimum amount of widgets needed to fulfil the order (based on the pack sizes) for the refinement process. 
		 */
		int A = ORIGINAL_WIDGETS_TO_BE_ORDERED;
		
		/* In order to ensure Rule 3 is not violated, a refinement procedure is carried out whereby the number of packs
		 * are minimised. For example, an initial run of ordering packs for 251 widgets generates incorrectly 2 x 250
		 * packs. The widgets from these 2 packs are then summed (500 in total) and ran again. This results in a 1 x 500
		 * pack. This refinement process leads to the same amount of widgets (2 x 250 == 1 x 500), but only a single
		 * larger pack. The refinement procedure is proceeded until two runs produce an identical result, which
		 * indicates the result is complete.
		 * 
		 * A while loop is used and is only exited once two runs are identical, indicating that the packs are fully
		 * refined. Although most test cases may be fully refined within exactly two steps, the use of the while loop
		 * allows for test cases that require more than two stages of refinement. The program is therefore more flexible
		 * and can handle different pack sizes.
		 */
		while (true) {
			
			/* packagesToOrder represents those packages from PACKAGES that fulfil the widget order. A more detailed
			 * explanation to its representation is detailed within the the orderPacks() method.  
			 * 
			 * packagesToOrder may not be optimised upon the initial run: packs may not be optimised, for example, there
			 * may contain 2 x 250 packs instead of a 1 x 500 pack.
			 */
			int[] packagesToOrder = orderPacks(A);
			
			/* Here, we sum all the widgets within the packagesToOrder array. This will often be more than the original
			 * widget order but will NOT violate rule 2, it will always be no more than necessary than to fulfil the
			 * order.
			 * 
			 * We sum up the widgets here so that we can run it through the program again in order to refine the amount
			 * of packs ordered. For example, we sum the total widgets of 2 packs of 250 widgets (2 x 250), which equals
			 * 500. We then run this through the program to achieve a single a pack of 500 (1 x 500). We therefore have
			 * the same number of widgets, with less packs.
			 */
			int widgetsNecessaryForOrder = sum(packagesToOrder);
			
			/* The process is then run again with the minimum widgets needed to fulfil the order as determined by the
			 * first run. On this run, the number of packs needed to make up the widgets are refined. For example,
			 * 500 widgets are no longer represented by 2 x 250 packs; instead it is represented by a single 1 x 500
			 * pack.
			 */
			int[] refinedPackagesToOrder = orderPacks(widgetsNecessaryForOrder);
			
			
			// If the two runs are equal, then we break from the while loop by returning the array of packs.
			if (Arrays.equals(refinedPackagesToOrder, packagesToOrder)) {
				
				// As both are equal, it does not matter which array is returned.
				return refinedPackagesToOrder;
			
			// Else, we continue to refine until both runs through are equal.
			} else {
				
				/* Here, we update A with the new amount of packages that were the result of the refinement process
				 * before we continue with the next iteration.
				 */
				A = sum(refinedPackagesToOrder);
			}
		}
	}
	
	/**
	 * Helper method that sums up all the widgets in the packs selected for delivery.
	 * 
	 * @param  packagesForDeliver is the int array representing which and how many packs are for delivery.
	 * 
	 * @return An int value representing the sum of all the widgets in the packages that have been selected for
	 *         delivery.
	 */
	private static int sum(int[] packagesForDelivery) {
		
		int widgets = 0;
		
		for (int i = 0; i < packagesForDelivery.length; i++) {
		
			widgets += packagesForDelivery[i] * PACKAGES[i]; 
		}
		
		return widgets;
		
	}


	public static void main(String[] args) {
		
		// This variable represents the amount of orders the customer/programmer wants to order.
		final int WIDGETS_ORDERED = 251;
		
		final int[] PACKAGES_TO_ORDER = orderWidgets(WIDGETS_ORDERED);
		
		// Prints the amount of packages we need to fulfil the order to the console.
		System.out.println("User ordered: " + WIDGETS_ORDERED + " widgets.");
		for (int i = 0; i < PACKAGES_TO_ORDER.length; i++) {
			
			System.out.printf("%4d x %d%n", PACKAGES[i], PACKAGES_TO_ORDER[i]);
			
		}
	}
}
