//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           TextManipulator
// Files:           OneFish.txt, OneFishDict.txt, TestOutout.txt
// Course:          CS200, fall 2017
//
// Author:          Junheng Wang
// Email:           jwang922@wisc.edu
// Lecturer's Name: Jim Williams
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.  If you received no outside help from either type of 
// source, then please explicitly indicate NONE.
//
// Persons:         None
// Online Sources:  None
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.io.IOException;
import java.util.ArrayList;
public class TestTextManipulator {

    public static void main(String[] args) {
       
        
        try {          
            testMatchCase();           
            testTranslate();
            testReverse();
            testReverseArrayList();
            testPigLatin();
            testManipulate();
            
            
        }
        catch (IOException e) {
            System.out.println("Manipulate: Input/Output Exception");
        }
        catch (Exception e) {
            System.out.println("Error");
        }
        finally {
            
        }
        

    }
    
    private static void testMatchCase() {
        String testWord1 = "aAAaaaaaa";
        String testWord2 = "WISCONSIN";
        testWord2 = TextManipulator.matchCase(testWord1, testWord2);
        String testWord3 = "HAha";
        String testWord4 = "ha";
        testWord4 = TextManipulator.matchCase(testWord3, testWord4);
        String testWord5 = "U";
        String testWord6 = "qWERTY";
        testWord6 = TextManipulator.matchCase(testWord5, testWord6);
        String testWord7 = "HaHaha";
        String testWord8 = "haHa";
        testWord8 = TextManipulator.matchCase(testWord7, testWord8);
        if (testWord2.equals("wISconsin") && testWord4.equals("HA") && testWord6.equals("Qwerty")
            && testWord8.equals("HaHa")) {
            System.out.println("TestMatchCase: passed");
        }
        else {
            System.out.print("TestMatchCase: failed, ");
            if(!testWord2.equals("wISconsin")) {
                System.out.println("Test 1 failed: Expected: wISconsin, result is " + testWord2);
            }
            if(!testWord4.equals("HA")) {
                System.out.println("Test 2 failed: Expected: HA, result is " + testWord4);
            }
            if(!testWord6.equals("Qwerty")) {
                System.out.println("Test 3 failed: Expected: Qwerty, result is " + testWord6);
            }
            if(!testWord8.equals("HaHa")) {
                System.out.println("Test 4 failed: Expected: HaHa, result is " + testWord8);
            }
        }
    }

    private static void testTranslate() throws Exception{
        String testWord1 = "black";
        String testWord2 = "a";
        String testWord3 = "!";
        String testWord4 = "of";
        ArrayList<String[]> wordList = new ArrayList<String[]>();
        TextManipulator.readDictFile("OneFishDict.txt", wordList);
        testWord1 = TextManipulator.translate(testWord1, wordList);
        testWord2 = TextManipulator.translate(testWord2, wordList);
        testWord3 = TextManipulator.translate(testWord3, wordList);
        testWord4 = TextManipulator.translate(testWord4, wordList);
        if (testWord1.equals("noir(e)") && testWord2.equals("un(e)") &&
            testWord3.equals("!") && testWord4.equals("de")) {
            System.out.println("TestTranslate: passed");
        }
        else {
            System.out.print("TestTranslate: failed, ");
            if(!testWord1.equals("noir(e)")) {
                System.out.println("Test 1 failed: Expected: noir(e), result is " + testWord1);
            }
            if(!testWord2.equals("un(e)")) {
                System.out.println("Test 2 failed: Expected: un(e), result is " + testWord2);
            }
            if(!testWord3.equals("!")) {
                System.out.println("Test 3 failed: Expected: !, result is " + testWord3);
            }
            if(!testWord4.equals("de")) {
                System.out.println("Test 4 failed: Expected: de, result is " + testWord4);
            }
        }

        
    }
    private static void testManipulate() throws IOException{
        ArrayList<ArrayList<String>> fileByLine = new ArrayList<ArrayList<String>>();//original
        ArrayList<ArrayList<String>> modFileByLine = new ArrayList<ArrayList<String>>();//do revision on
        ArrayList<String[]> dictList = new ArrayList<String[]>();
        //Scanner scnr = new Scanner(System.in);
        boolean[] modFlags = new boolean[Config.NUM_MODS];
        modFlags[Config.MOD_TRANS] = true;
        modFlags[Config.MOD_REV_LINE] = true;
        modFlags[Config.MOD_PIG] = true;
        modFlags[Config.MOD_REV_WORD] = true;
        
        TextManipulator.readInputFile("OneFish.txt", modFileByLine);
        TextManipulator.readInputFile("OneFish.txt", fileByLine);
        TextManipulator.readDictFile("OneFishDict.txt", dictList);
        
        modFileByLine = TextManipulator.manipulate(modFileByLine, dictList, modFlags );
     
        if (modFileByLine.get(2).get(1).equals("Yareguo") && 
            modFileByLine.get(4).get(0).equals("yapnossio") &&
            modFileByLine.get(3).get(0).equals(".") &&
            modFileByLine.get(10).get(0).equals("---")) {
            System.out.println("TestManipulate: passed");
        }
        else {
            System.out.print("TestManipulate: failed, ");
            if (!modFileByLine.get(2).get(1).equals("Yareguo")) {
                System.out.println("Test 1 failed: Expected: Yareguo, result is: " + modFileByLine.get(2).get(1));
            }
            if (!modFileByLine.get(4).get(0).equals("yapnossio")) {
                System.out.println("Test 2 failed: Expected: yapnossio, result is: " + modFileByLine.get(4).get(0));
            }
            if (!modFileByLine.get(3).get(0).equals(".")) {
                System.out.println("Test 3 failed: Expected: ., result is: " + modFileByLine.get(3).get(0));
            }
            if (!modFileByLine.get(10).get(0).equals("---")) {
                System.out.println("Test 4 failed: Expected: ---, result is: " + modFileByLine.get(10).get(0));
            }
        }
        
    }
    private static void testReverse() {
        String testWord1 = "Abcde";
        String testWord2 = "QWERTy";
        String testWord3 = "WAteR";
        String testWord4 = "Ab";
        testWord1 = TextManipulator.reverse(testWord1);
        testWord2 = TextManipulator.reverse(testWord2);
        testWord3 = TextManipulator.reverse(testWord3);
        testWord4 = TextManipulator.reverse(testWord4);

        if (testWord1.equals("Edcba") && testWord2.equals("YTREWq") &&
            testWord3.equals("REtaW") && testWord4.equals("Ba")) {
            System.out.println("TestTranslate: passed");
        }
        else {
            System.out.print("TestTranslate: failed, ");
            if (!testWord1.equals("Edcba")) {
                System.out.println("Test 1 failed: Expected: Edcba, result is " + testWord1);
            }
            if (!testWord2.equals("YTREWq")) {
                System.out.println("Test 2 failed: Expected: YTREWq, result is " + testWord2);
            }
            if (!testWord3.equals("REtaW")) {
                System.out.println("Test 3 failed: Expected: REtaW, result is " + testWord3);
            }
            if (!testWord1.equals("Ba")) {
                System.out.println("Test 4 failed: Expected: Ba, result is " + testWord4);
            }
        }

    }
    private static void testReverseArrayList() {
        ArrayList<String> testArrL1 = new ArrayList<String>();
        ArrayList<String> testArrL2 = new ArrayList<String>();
        ArrayList<String> testArrL3 = new ArrayList<String>();
        ArrayList<String> testArrL4 = new ArrayList<String>();

        

        
        testArrL1.add("you");
        testArrL1.add("require");
        testArrL1.add("my");
        testArrL1.add("assistance");
        
        testArrL2.add("A");
        testArrL2.add("B");
        testArrL2.add("C");
        testArrL2.add("D");
        testArrL2.add("E");

        testArrL3.add("I");
        testArrL3.add("love");
        testArrL3.add("you");
        
        testArrL4.add("Why");
        testArrL4.add("4");
        testArrL4.add("tests???");
        
        testArrL1 = TextManipulator.reverse(testArrL1);
        testArrL2 = TextManipulator.reverse(testArrL2);
        testArrL3 = TextManipulator.reverse(testArrL3);
        testArrL4 = TextManipulator.reverse(testArrL4);
        
        if (testArrL1.get(1).equals("my") && testArrL2.get(0).equals("E") &&
            testArrL3.get(2).equals("I") && testArrL4.get(0).equals("tests???")) {
            System.out.println("TestReverseArray: passed");
        }
        else {
            System.out.println("TestReverseArray: failed, ");
            if(!testArrL1.get(1).equals("my")) {
                System.out.println("Test 1 failed: Expected: my, result is: " + testArrL1.get(1));
            }
            if(!testArrL2.get(0).equals("E")) {
                System.out.println("Test 2 failed: Expected: E, result is: " + testArrL2.get(0));
            }
            if(!testArrL3.get(2).equals("I")) {
                System.out.println("Test 3 failed: Expected: I, result is: " + testArrL3.get(2));
            }
            if(!testArrL4.get(0).equals("tests???")) {
                System.out.println("Test 4 failed: Expected: tests???, result is: " + testArrL4.get(0));
            }
        }

        
    }
    private static void testPigLatin() {
        String test1 = "tggYHH";
        String test2 = "yes please";
        String test3 = "Pig";
        String test4 = "Latin";
        test1 = TextManipulator.pigLatin(test1);
        test2 = TextManipulator.pigLatin(test2);
        test3 = TextManipulator.pigLatin(test3);
        test4 = TextManipulator.pigLatin(test4);
        
        if (test1.equals("yhhTGGay") && test2.equals("yes pleaseway") &&
            test3.equals("Igpay") && test4.equals("Atinlay")) {
            System.out.println("TestPigLatin : passed");
        }
        else {
            System.out.print("TestPigLatin: failed, ");
            if (!test1.equals("yhhTGGay")) {
                System.out.println("Test 1 failed: Expected: yhhTGGay, result is: " + test1);
            }
            if (!test2.equals("yes pleaseway")) {
                System.out.println("Test 2 failed: Expected: yes pleaseway, result is: " + test2);
            }
            if (!test3.equals("Igpay")) {
                System.out.println("Test 3 failed: Expected: Igpay, result is: " + test3);
            }
            if (!test4.equals("Atinlay")) {
                System.out.println("Test 4 failed: Expected: Atinlay, result is: " + test4);
            }
        }
        
    }
}

