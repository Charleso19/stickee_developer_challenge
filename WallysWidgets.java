import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
 * Precondition 2: The values inside the int array 'PACKAGES' MUST NOT be 0 or a negative number.
 * Precondition 3: ORIGINAL_WIDGET_ORDER MUST be above 0. That is to say, one cannot order 0 or a negative number of
 * 				   widgets.
 * 
 * 
 * A note on flexibility:
 * 
 * The programmer need only change the PACKAGES variable (at the top of the program) to meet the flexibility
 * requirements of the challenge; one can add/delete packages and/or adjust package sizes. The amount of widgets that
 * the programmer/user wishes to order can be altered via modifying the ORIGINAL_WIDGET_ORDER variable (also at the top
 * of the program).
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
 * @version 2020/12/09
 */
public class WallysWidgets {
	
	// PACKAGES MUST BE IN DESCENDING ORDER.
	private static final int[] PACKAGES = { 5000, 2000, 1000, 500, 251, 250 };
	private static final int ORIGINAL_WIDGET_ORDER = 502;


	/**
	 * Responsible for rule 2: Returns the minimum amount of widgets necessary that will fulfil the order based on the
	 * pack sizes.
	 * 
	 * @param  ORIGINAL_WIDGET_ORDER
	 * @return Returns the minimum amount of widgets necessary that will fulfil the order based on the pack sizes.
	 */
	public static int[] collectWidgets(final int WIDGET_ORDER) {
		
		final int[] PACKAGES_TO_ORDER = new int [PACKAGES.length]       ;
		List<Integer> differences     = new ArrayList<>(PACKAGES.length);		
		
		int widgetsLeftToOrder = WIDGET_ORDER;
		
		// We check whether the widgets that remain to be ordered can be fulfiled wholly by a pack.
		for (int i = 0; i < PACKAGES.length; i++) {
			
			// If the widgets order can be fulfilled without breaking a pack (rule 1), we add it and return.
			if ((widgetsLeftToOrder % PACKAGES[i]) == 0) {
				
				PACKAGES_TO_ORDER[i] += widgetsLeftToOrder / PACKAGES[i];
				
				return PACKAGES_TO_ORDER;
			}
		}
		
		// While there are still widgets left to order.
		while (widgetsLeftToOrder > 0) {
			
			/* If the amount of widgets still left to order is larger than the amount of widgets in the largest pack,
			 * then add the largest pack and subtract the widgets in the largest pack from the widgets left to order.
			 */
			if (widgetsLeftToOrder >= PACKAGES[0]) {
				
				PACKAGES_TO_ORDER[0]++;
				widgetsLeftToOrder -= PACKAGES[0];
				continue;
			
			/* Else, we select the pack of widgets that has the smallest absolute difference compared to the widgets
			 * left to order.
			 */
			} else {
			
				for (int pack : PACKAGES) {
					
					differences.add(Math.abs(widgetsLeftToOrder - pack));
					
				}
				
				int smallestPackPosition = differences.indexOf(Collections.min(differences));
				PACKAGES_TO_ORDER[smallestPackPosition]++;
				widgetsLeftToOrder -= PACKAGES[smallestPackPosition];
				
				// The List is cleared for the next iteration.
				differences.clear();
			}
		}
		
		return PACKAGES_TO_ORDER;
		
	}

	/**
	 * Responsible for rule 3: Refines the number of packs used (if possible).
	 * 
	 * @param ORIGINAL_WIDGET_ORDER
	 * @return Returns the minimum amount of widgets necessary that will fulfil the order based on the pack sizes.
	 */
	public static int[] collectWidgetsRefined(final int WIDGET_ORDER) {
		
		final int[] PACKAGES_TO_ORDER = new int [PACKAGES.length]       ;
		List<Integer> differences     = new ArrayList<>(PACKAGES.length);		
		
		int widgetsLeftToOrder = WIDGET_ORDER;
		
		// While there are still widgets left to order.
		while (widgetsLeftToOrder > 0) {
			
			/* If the amount of widgets still left to order is larger than the amount of widgets in the largest pack,
			 * then add the largest pack and subtract the widgets in the largest pack from the widgets left to order.
			 */
			if (widgetsLeftToOrder >= PACKAGES[0]) {
				
				PACKAGES_TO_ORDER[0]++;
				widgetsLeftToOrder -= PACKAGES[0];
				continue;
			
			/* Else, we select the pack of widgets that has the smallest absolute difference compared to the widgets
			 * left to order.
			 */
			} else {
			
				for (int pack : PACKAGES) {
					
					differences.add(Math.abs(widgetsLeftToOrder - pack));
					
				}
				
				int smallestPackPosition = differences.indexOf(Collections.min(differences));
				PACKAGES_TO_ORDER[smallestPackPosition]++;
				widgetsLeftToOrder -= PACKAGES[smallestPackPosition];
				
				// The List is cleared for the next iteration.
				differences.clear();
			}
		}
		
		return PACKAGES_TO_ORDER;
		
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
	
	/**
	 * A helper method that sums up the amount of pacckages in an order.
	 * 
	 * @param packagesForDelivery
	 * @return Returns the amount of packages in an order.
	 */
	private static int sumPacks(int[] packagesForDelivery) {
		
		int packs = 0;
		
		for (int i = 0; i < packagesForDelivery.length; i++) {
			
			packs += packagesForDelivery[i];
			
		}
		
		return packs; 
		
	}
	
	/**
	 * Prints the amount of packages we need to fulfil the order to the console.
	 * 
	 * @param PACKAGES_TO_ORDER
	 */
	public static void printOrder(final int[] PACKAGES_TO_ORDER) {
		
		System.out.println("User ordered: " + ORIGINAL_WIDGET_ORDER + " widgets.");
		for (int i = 0; i < PACKAGES_TO_ORDER.length; i++) {

			System.out.printf("%4d x %d%n", PACKAGES[i], PACKAGES_TO_ORDER[i]);

		}
	}
	

	// The main method that runs the program.
	public static void main(String[] args) {
		
		
		// Rule 2
		int[] minimumWidgetsOrder = collectWidgets(ORIGINAL_WIDGET_ORDER);
		
		// Rule 3
		int totalWidgetsForMinimumOrder = sum(minimumWidgetsOrder);
		int[] refinedOrder = collectWidgetsRefined(totalWidgetsForMinimumOrder);
		 
		int totalWidgetsRefinedOrder = sum(refinedOrder);
		
		// If the refinement order has broken rule 2, then go with minimum.
		if (totalWidgetsForMinimumOrder < totalWidgetsRefinedOrder) {
			
			printOrder(minimumWidgetsOrder);
		
		 /* Else, if the original and refinement procedure both have the same number of widgets; choose the one with the
		  * less packs (rule 3).
		  */
		} else if (totalWidgetsForMinimumOrder == totalWidgetsRefinedOrder) {
			
			int sumOfPacksForMinimumWidgetsOrder = sumPacks(minimumWidgetsOrder);
			
			int sumOfPacksForRefinedOrder = sumPacks(refinedOrder);
			
			if (sumOfPacksForMinimumWidgetsOrder < sumOfPacksForRefinedOrder) {
				
				printOrder(minimumWidgetsOrder);
			
			} else {
				
				printOrder(refinedOrder);
				
			}
		
		// Else, the refined version has not violated any rules, and can be printed.
		} else {
			printOrder(refinedOrder);
		}
	}
}
