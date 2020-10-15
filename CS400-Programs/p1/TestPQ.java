/**
 * Filename:   TestPQ.java
 * Project:    p1TestPQ
 * Authors:    Debra Deppeler, Junheng Wang
 *
 * Semester:   Fall 2018
 * Course:     CS400
 * Lecture:    002
 * 
 * Note: Warnings are suppressed on methods that construct new instances of 
 * generic PriorityQueue types.  The exceptions that occur are caught and 
 * such types are not able to be tested with this program.
 * 
 * Due Date:   Before 10pm on September 17, 2018
 * Version:    2.0
 * 
 * Credits:    None
 * 
 * Bugs:       Some tests return NullPointerException since basic function of PQ is not complete
 */


import java.sql.Array;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Runs black-box unit tests on the priority queue implementations passed in as command-line
 * arguments (CLA).
 * 
 * If a class with the specified class name does not exist or does not implement the
 * PriorityQueueADT interface, the class name is reported as unable to test.
 * 
 * If the class exists, but does not have a default constructor, it will also be reported as unable
 * to test.
 * 
 * If the class exists and implements PriorityQueueADT, and has a default constructor, the tests
 * will be run.
 * 
 * Successful tests will be reported as passed.
 * 
 * Unsuccessful tests will include: input, expected output, and actual output
 * 
 * Example Output: Testing priority queue class: PQ01 5 PASSED 0 FAILED 5 TOTAL TESTS RUN Testing
 * priority queue class: PQ02 FAILED test00isEmpty: unexpectedly threw
 * java.lang.NullPointerException FAILED test04insertRemoveMany: unexpectedly threw
 * java.lang.ArrayIndexOutOfBoundsException 3 PASSED 2 FAILED 5 TOTAL TESTS RUN
 * 
 * ... more test results here
 * 
 * @author deppeler
 */
public class TestPQ {

    // set to true to see call stack trace for exceptions
    private static final boolean DEBUG = false;

    /**
     * Run tests to determine if each Priority Queue implementation works as specified. User names
     * the Priority Queue types to test. If there are no command-line arguments, nothing will be
     * tested.
     * 
     * @param args names of PriorityQueueADT implementation class types to be tested.
     */
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++)
            test(args[i]);

        if (args.length < 1)
            print("no PQs to test");
    }

    /**
     * Run all tests on each priority queue type that is passed as a classname.
     * 
     * If constructing the priority queue in the first test causes exceptions, then no other tests
     * are run.
     * 
     * @param className the name of the class that contains the priority queue implementation.
     */
    private static void test(String className) {
        print("Testing priority queue class: " + className);
        int passCount = 0;
        int failCount = 0;
        try {

//            if (test00isEmpty(className))
//                passCount++;
//            else
//                failCount++;
//            if (test01getMaxEXCEPTION(className))
//                passCount++;
//            else
//                failCount++;
//
//            if (test02removeMaxEXCEPTION(className))
//                passCount++;
//            else
//                failCount++;
//            if (test03insertRemoveOne(className))
//                passCount++;
//            else
//                failCount++;
//            if (test04insertRemoveMany(className))
//                passCount++;
//            else
//                failCount++;
            if (test05duplicatesAllowed(className))
                passCount++;
            else
                failCount++;
//            if (test06manyDataItems(className))
//                passCount++;
//            else
//                failCount++;
//            if (test07insertNull(className))
//                passCount++;
//            else
//                failCount++;
//            if (test08getMaxRemoves(className))
//                passCount++;
//            else
//                failCount++;
//            if (test09getMaxRemoveMaxSame(className))
//                passCount++;
//            else
//                failCount++;



            String passMsg = String.format("%4d PASSED", passCount);
            String failMsg = String.format("%4d FAILED", failCount);
            print(passMsg);
            print(failMsg);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            if (DEBUG)
                e.printStackTrace();
            print(className + " FAIL: Unable to construct instance of " + className);
        } finally {
            String msg = String.format("%4d TOTAL TESTS RUN", passCount + failCount);
            print(msg);
        }

    }

    /**
     * This test examines if getMax and removeMax can return the same value that has the highest 
     * priority among inserted elements.
     * 
     * @param className name of the priority queue implementation to test.
     * @return true if returned values match and no exception is thrown
     * @throws NullPointerException
     * @throws InputMismatchException
     */
    private static boolean test09getMaxRemoveMaxSame(String className)
        throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        PriorityQueueADT[] pqs = new PriorityQueueADT[3];
        Comparable[][] tester = {{new Integer(3), new Integer(4), new Integer(5), new Integer(3)},
            {new Double(2.0), new Double(3.0), new Double(4.0), new Double(3.0)},
            {new String("A"), new String("B"), new String("C"), new String("C")}};
        Comparable[][] tester2 = {{new Integer(5), new Integer(4), new Integer(3), new Integer(3)},
            {new Double(4.0), new Double(3.0), new Double(3.0), new Double(2.0)},
            {new String("C"), new String("C"), new String("B"), new String("A")}};
        try {
            PriorityQueueADT<Integer> pqInt = newIntegerPQ(className);
            PriorityQueueADT<Double> pqDou = newDoublePQ(className);
            PriorityQueueADT<String> pqStr = newStringPQ(className);
            pqs[0] = pqInt;
            pqs[1] = pqDou;
            pqs[2] = pqStr;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < tester[i].length; j++) {
                    pqs[i].insert(tester[i][j]);
                }
            } // Adding to PQ
            Integer tempInt = (Integer) pqs[0].getMax();
            Double tempDou = (Double) pqs[1].getMax();
            String tempStr = (String) pqs[2].getMax();
            Integer tempInt2 = (Integer) pqs[0].removeMax();
            Double tempDou2 = (Double) pqs[1].removeMax();
            String tempStr2 = (String) pqs[2].removeMax();
            // Comparing if removeMax and getMax returned the same element.
            if (!tempInt.equals(tempInt2)) {
                throw new InputMismatchException(
                    "FAILED test09getMaxRemoveMaxSame: removeMax returned " + tempInt2
                        + " while getMax returned " + tempInt + ".");
            }
            if (!tempDou.equals(tempDou2)) {
                throw new InputMismatchException(
                    "FAILED test09getMaxRemoveMaxSame: removeMax returned " + tempDou2
                        + " while getMax returned " + tempDou + ".");
            }
            if (!tempStr.equals(tempStr2)) {
                throw new InputMismatchException(
                    "FAILED test09getMaxRemoveMaxSame: removeMax returned " + tempStr2
                        + " while getMax returned " + tempStr + ".");
            }
            return true;
        } catch (InputMismatchException e) {
            print(e.getMessage());
            return false;
        } catch (NullPointerException e) {
            if (DEBUG)
                e.printStackTrace();
            print(
                "FAILED test09getMaxRemoveMaxSame: PriorityQueue unexpectedly threw NullPointer Exception "
                    + "while there should be no null in the queue, or insert is incomplete");
            return false;
        } catch (Exception e) {
            if (DEBUG)
                e.printStackTrace();
            print("FAILED test09getMaxRemoveMaxSame: unexpectedly threw " + e.getClass().getName());
            return false;
        }


    }

    /**
     * This test inserts one element to PQ to make sure the queue is not empty, then examines
     * if getMax will remove any item from the queue by testing if the queue is empty.
     * 
     * @param className name of the priority queue implementation to test.
     * @return true if no element is removed and no exception thrown
     * @throws IllegalArgumentException
     * @throws NullPointerException
     */
    private static boolean test08getMaxRemoves(String className)
        throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        PriorityQueueADT[] pqs = new PriorityQueueADT[3];
        Comparable[] tester = {new Integer(1), new Double(2.0), new String("test")};
        try {
            PriorityQueueADT<Integer> pqInt = newIntegerPQ(className);
            PriorityQueueADT<Double> pqDou = newDoublePQ(className);
            PriorityQueueADT<String> pqStr = newStringPQ(className);
            pqs[0] = pqInt;
            pqs[1] = pqDou;
            pqs[2] = pqStr;

            // Similar to test07, but testing if getMax removes the items in the PQ by testing
            // isEmpty.

            for (int i = 0; i < 3; i++) {
                pqs[i].insert(tester[i]);
            }
            for (int j = 0; j < 3; j++) {
                if (pqs[j].getMax() == null) {
                    throw new NullPointerException();
                }

                if (pqs[j].isEmpty()) {
                    throw new IllegalArgumentException((String) tester[j]);
                }
            }
            return true;
        } catch (IllegalArgumentException e) {
            print("FAILED test08getMaxRemoves: getMax unexpectedly removed element from PQ while "
                + e.getMessage() + " was the input.");
            return false;
        } catch (NullPointerException e) {
            print(
                "FAILED test08getMaxRemoves: getMax unexpectedly returned null where input didn't include null");
            return false;
        } catch (Exception e) {
            if (DEBUG)
                e.printStackTrace();
            print("FAILED test08getMaxRemoves: unexpectedly threw " + e.getClass().getName());
            return false;
        }

    }

    /**
     * This test tries to inserts 3 null values into PQs and see if the PQs are empty, which
     * shows that PQs can deal with illegally inserted null values
     * 
     * @param className name of the priority queue implementation to test.
     * @return true if NoSuchElementException or IllegalArgumentException is thrown
     * @throws NoSuchElementException
     * @throws IllegalArgumentException
     * @throws NullPointerException
     */
    private static boolean test07insertNull(String className)
        throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        PriorityQueueADT[] pqs = new PriorityQueueADT[3];
        try {
            PriorityQueueADT<Integer> pqInt = newIntegerPQ(className);
            PriorityQueueADT<Double> pqDou = newDoublePQ(className);
            PriorityQueueADT<String> pqStr = newStringPQ(className);
            pqs[0] = pqInt;
            pqs[1] = pqDou;
            pqs[2] = pqStr;
            // For each PQ, inserting 3 null values, then try getMax to see if each PQ is empty.
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    pqs[i].insert(null);
                }
            }
            for (int k = 0; k < 3; k++) {
                pqs[k].getMax();
            }
            print("FAILED test07insertNull: PriorityQueue failed to stop inserting null.");
            return false;
        } catch (NoSuchElementException e) {
            return true;
        } catch (NullPointerException e) {
            print("FAILED test07insertNull: PriorityQueue failed to stop inserting null.");
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        } catch (Exception e) {
            if (DEBUG)
                e.printStackTrace();
            print("FAILED test07insertNull: unexpectedly threw " + e.getClass().getName());
            return false;
        }

    }

    /**
     * This test inserts a very large amount of items and removes them all to see if PQ can handle
     * large amount of items and sort them into correct order
     * 
     * @param className name of the priority queue implementation to test.
     * @return true if no exception is thrown and PQs correctly inserted and removed items
     * @throws NoSuchElementException
     * @throws ArrayIndexOutOfBoundsException
     * @throws NullPointerException
     */
    private static boolean test06manyDataItems(String className)
        throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        PriorityQueueADT[] pqs = new PriorityQueueADT[3];
        Comparable[][] tester = new Comparable[3][70000];
        Random randGen = new Random();
        Integer temp = new Integer(0);
        for (int i = 0; i < 70000; i++) {
            tester[0][i] = randGen.nextInt();
        }
        for (int j = 0; j < 70000; j++) {
            tester[1][j] = randGen.nextDouble();
        }
        for (int k = 0; k < 70000; k++) {
            temp = randGen.nextInt();
            tester[2][k] = temp.toString();
        }

        try {
            PriorityQueueADT<Integer> pqInt = newIntegerPQ(className);
            PriorityQueueADT<Double> pqDou = newDoublePQ(className);
            PriorityQueueADT<String> pqStr = newStringPQ(className);
            Integer testInt = new Integer(0);
            Double testDou = new Double(0.0);
            String testStr = new String("");

            // Below are first adding many items and then remove them while comparing if they were
            // removed
            // in correct order.
            for (int m = 0; m < 70000; m++) {
                pqInt.insert(randGen.nextInt());
            }
            testInt = pqInt.getMax();
            for (int m = 0; m < 70000; m++) {
                pqInt.removeMax();
                if (testInt.compareTo(pqInt.getMax()) < 0) {
                    throw new InputMismatchException();
                }
                testInt = pqInt.getMax();
            }

            for (int n = 0; n < 70000; n++) {
                pqDou.insert(randGen.nextInt() + randGen.nextDouble());
            }
            testDou = pqDou.getMax();
            for (int n = 0; n < 70000; n++) {
                pqDou.removeMax();
                if (testDou.compareTo(pqDou.getMax()) < 0) {
                    throw new InputMismatchException();
                }
                testDou = pqDou.getMax();
            }

            for (int l = 0; l < 70000; l++) {
                pqStr.insert(new Integer(randGen.nextInt()).toString());
            }
            testStr = pqStr.getMax();
            for (int l = 0; l < 70000; l++) {
                pqStr.removeMax();
                if (testStr.compareTo(pqStr.getMax()) < 0) {
                    throw new InputMismatchException();
                }
                testStr = pqStr.getMax();
            }

            return true;
        } catch (NoSuchElementException e) {
            print("FAILED test06manyDataItems: PriorityQueue failed to handle 1 million elements");
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            print("FAILED test06manyDataItems: PriorityQueue failed to handle 1 million elements");
        } catch (Exception e) {
            if (DEBUG)
                e.printStackTrace();
            print("FAILED test06manyDataItems: unexpectedly threw " + e.getClass().getName());
            return false;
        }
        return false;
    }

    /**
     * This test inserts items that contain duplicates and removes them all to see if duplicates 
     * are allowed
     * 
     * @param className name of the priority queue implementation to test.
     * @return true if duplicate are allowed to insert
     * @throws NoSuchElementException
     * @throws NullPointerException
     */
    private static boolean test05duplicatesAllowed(String className)
        throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        PriorityQueueADT[] pqs = new PriorityQueueADT[3];
        Comparable[][] tester = {{new Integer(3), new Integer(4), new Integer(5), new Integer(3)},
            {new Double(2.0), new Double(3.0), new Double(4.0), new Double(3.0)},
            {new String("A"), new String("B"), new String("C"), new String("C")}};
        Comparable[][] tester2 = {{new Integer(5), new Integer(4), new Integer(3), new Integer(3)},
            {new Double(4.0), new Double(3.0), new Double(3.0), new Double(2.0)},
            {new String("C"), new String("C"), new String("B"), new String("A")}};
        // Adding variables for testing. From test05 I started to use array to collect different
        // types of
        // PriorityQueues.
        System.out.println(pqs[1] instanceof Object);

        try {
            PriorityQueueADT<Integer> pqInt = newIntegerPQ(className);
            PriorityQueueADT<Double> pqDou = newDoublePQ(className);
            PriorityQueueADT<String> pqStr = newStringPQ(className);
            pqs[0] = pqInt;
            pqs[1] = pqDou;
            pqs[2] = pqStr;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < tester[i].length; j++) {
                    pqs[i].insert(tester[i][j]);
                }
            } // Adding to PQ, then check if duplicate are allowed.
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < tester[i].length; j++) {
                    if (!pqs[i].removeMax().equals(tester2[i][j])) {
                        throw new InputMismatchException(
                            "FAILED test05duplicatesAllowed: duplicate elements are "
                                + "not supported by this PriorityQueue while " + tester2[i][j]
                                + " was supplied duplicately");
                    }
                }
            }
            return true;
        } catch (NoSuchElementException e) {
            print(e.getMessage());
            return false;
        } catch (NullPointerException e) {
            print(
                "FAILED test05duplicatesAllowed: failed to insert duplicate elements that can be caused by incomplete implementation.");
            return false;
        } catch (Exception e) {
            if (DEBUG)
                e.printStackTrace();
            print("FAILED test05duplicatesAllowed: unexpectedly threw " + e.getClass().getName());
            return false;
        }
    }

    /**
     * This test inserts many elements into PQ and see if remove can return the max value
     * 
     * @param className name of the priority queue implementation to test.
     * @return true if maximum value is returned correctly
     * @throws InputMismatchException
     * @throws NullPointerException
     */
    private static boolean test04insertRemoveMany(String className)
        throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        PriorityQueueADT<Integer> pqInt = newIntegerPQ(className);
        PriorityQueueADT<Double> pqDou = newDoublePQ(className);
        PriorityQueueADT<String> pqStr = newStringPQ(className);
        Integer[] tester1 = new Integer[] {2, 10, -1};
        Double[] tester2 = new Double[] {1.5, 11.3, -1.5};
        String[] tester3 = new String[] {"Apple", "strawberry", "mango"};
        // Adding variables for testing.
        try {
            Integer compare1 = new Integer(1);
            Double compare2 = new Double(2.0);
            String compare3 = new String("test");
            for (int i = 0; i < 3; i++) {
                pqInt.insert(tester1[i]);
                pqDou.insert(tester2[i]);
                pqStr.insert(tester3[i]);
            } // Adding each element to their individual PQs
            compare1 = pqInt.removeMax();
            compare2 = pqDou.removeMax();
            compare3 = pqStr.removeMax();
            // Test if removed item is the max value among elements inserted
            if (!compare1.equals(10)) {
                throw new InputMismatchException("FAILED test04insertRemoveMany: output " + compare1
                    + " didn't match expected highest input value " + 10);
            }
            if (!compare2.equals(11.3)) {
                throw new InputMismatchException("FAILED test04insertRemoveMany: output " + compare2
                    + " didn't match expected highest input value " + 11.3);
            }
            if (!compare3.equals("strawberry")) {
                throw new InputMismatchException("FAILED test04insertRemoveMany: output " + compare1
                    + " didn't match expected highest input value Strawberry");
            }
            return true;
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            print(
                "FAILED test04insertRemoveMany: function is not complete that a NullPointer Exception is thrown");
        } catch (Exception e) {
            if (DEBUG)
                e.printStackTrace();
            print("FAILED test04insertRemoveMany: unexpectedly threw " + e.getClass().getName());
            return false;
        }
        return false;
    }

    /**
     * This test inserts one element into PQ and see if remove can correctly remove the value inserted
     * 
     * @param className name of the priority queue implementation to test.
     * @return true if item removed is the same as the one inserted.
     * @throws InputMismatchException
     * @throws NullPointerException
     */
    private static boolean test03insertRemoveOne(String className)
        throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        PriorityQueueADT<Integer> pqInt = newIntegerPQ(className);
        PriorityQueueADT<Double> pqDou = newDoublePQ(className);
        PriorityQueueADT<String> pqStr = newStringPQ(className);
        Integer tester1 = new Integer(1);
        Double tester2 = new Double(2.0);
        String tester3 = new String("test");
        Integer compare1 = new Integer(1);
        Double compare2 = new Double(2.0);
        String compare3 = new String("test");
        // Adding variables for testing.
        try {
            pqInt.insert(tester1);
            pqDou.insert(tester2);
            pqStr.insert(tester3);
            compare1 = pqInt.removeMax();
            compare2 = pqDou.removeMax();
            compare3 = pqStr.removeMax();
            // Test if item removed is the same as the one inserted.
            if (!compare1.equals(tester1)) {
                throw new InputMismatchException("FAILED test03insertRemoveOne: output " + compare1
                    + " didn't match input value " + tester1);
            }
            if (!compare2.equals(tester2)) {
                throw new InputMismatchException("FAILED test03insertRemoveOne: output " + compare2
                    + " didn't match input value " + tester2);
            }
            if (!compare3.equals(tester3)) {
                throw new InputMismatchException("FAILED test03insertRemoveOne: output " + compare1
                    + " didn't match input value " + tester3);
            }
            return true;
        } catch (InputMismatchException e) {
            print(e.getMessage());
        } catch (NullPointerException e) {
            print(
                "FAILED test03insertRemoveOne: function is not complete that a NullPointer Exception is thrown");
        } catch (Exception e) {
            if (DEBUG)
                e.printStackTrace();
            print("FAILED test03insertRemoveOne: unexpectedly threw " + e.getClass().getName());
            return false;
        }

        return false;
    }

    /**
     * Confirm that getMax throws NoSuchElementException if
     * called on an empty priority queue. Any other exception indicates a fail.
     * 
     * @param className name of the priority queue implementation to test.
     * @return true if removeMax on empty priority queue throws NoSuchElementException
     * @throws NoSuchElementException
     */
    private static boolean test02removeMaxEXCEPTION(String className)
        throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        PriorityQueueADT<Integer> pq = newIntegerPQ(className);
        try {
            pq.removeMax();
        } catch (NoSuchElementException e) {
            return true;
        } catch (Exception e) {
            if (DEBUG)
                e.printStackTrace();
            print("FAILED test02removeMaxEXCEPTION: unexpectedly threw " + e.getClass().getName());
            return false;
        }
        print(
            "FAILED test02removeMaxEXCEPTION: removeMax did not throw NoSuchElement exception on newly constructed PQ");
        return false;
    }

    /**
     * DO NOT EDIT -- provided as an example Confirm that getMax throws NoSuchElementException if
     * called on an empty priority queue. Any other exception indicates a fail.
     * 
     * @param className name of the priority queue implementation to test.
     * @return true if getMax on empty priority queue throws NoSuchElementException
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private static boolean test01getMaxEXCEPTION(String className)
        throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        PriorityQueueADT<Integer> pq = newIntegerPQ(className);
        try {
            pq.getMax();
        } catch (NoSuchElementException e) {
            return true;
        } catch (Exception e) {
            if (DEBUG)
                e.printStackTrace();
            print("FAILED test01getMaxEXCEPTION: unexpectedly threw " + e.getClass().getName());
            return false;
        }
        print(
            "FAILED test01getMaxEXCEPTION: getMax did not throw NoSuchElement exception on newly constructed PQ");
        return false;
    }

    /**
     * DO NOT EDIT THIS METHOD
     * 
     * @return true if able to construct Integer priority queue and the instance isEmpty.
     * 
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private static boolean test00isEmpty(String className)
        throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        PriorityQueueADT<Integer> pq = newIntegerPQ(className);
        try {
            if (pq.isEmpty())
                return true;
        } catch (Exception e) {
            if (DEBUG)
                e.printStackTrace();
            print("FAILED test00isEmpty: unexpectedly threw " + e.getClass().getName());
            return false;
        }
        print("FAILED test00isEmpty: isEmpty returned false on newly constructed PQ");
        return false;
    }

    /**
     * DO NOT EDIT THIS METHOD Constructs a max Priority Queue of Integer using the class that is
     * name.
     * 
     * @param className The specific Priority Queue to construct.
     * @return a PriorityQueue
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    @SuppressWarnings({"unchecked"})
    public static final PriorityQueueADT<Integer> newIntegerPQ(String className)
        throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        Class<?> pqClass = Class.forName(className);
        Object obj = pqClass.newInstance();
        if (obj instanceof PriorityQueueADT) {
            return (PriorityQueueADT<Integer>) obj;
        }
        return null;
    }

    /**
     * DO NOT EDIT THIS METHOD Constructs a max Priority Queue of Double using the class that is
     * named.
     * 
     * @param className The specific Priority Queue to construct.
     * @return a PriorityQueue
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    @SuppressWarnings({"unchecked"})
    public static final PriorityQueueADT<Double> newDoublePQ(final String className)
        throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        Class<?> pqClass = Class.forName(className);
        Object obj = pqClass.newInstance();
        if (obj instanceof PriorityQueueADT) {
            return (PriorityQueueADT<Double>) obj;
        }
        return null;
    }

    /**
     * DO NOT EDIT THIS METHOD Constructs a max Priority Queue of String using the class that is
     * named.
     * 
     * @param className The specific Priority Queue to construct.
     * @return a PriorityQueue
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    @SuppressWarnings({"unchecked"})
    public static final PriorityQueueADT<String> newStringPQ(final String className)
        throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        Class<?> pqClass = Class.forName(className);
        Object obj = pqClass.newInstance();
        if (obj instanceof PriorityQueueADT) {
            return (PriorityQueueADT<String>) obj;
        }
        return null;
    }


    /**
     * DO NOT EDIT THIS METHOD Write the message to the standard output stream. Always adds a new
     * line to ensure each message is on its own line.
     * 
     * @param message Text string to be output to screen or other.
     */
    private static void print(String message) {
        System.out.println(message);
    }

}
