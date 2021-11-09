package lapr.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    String sdate = "1/10/2020 23:16";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm");
    LocalDateTime date = LocalDateTime.from(formatter.parse(sdate));
    Position position1 = new Position(date, 1, 48, 4, 5, 5);

    String sdate2 = "3/10/2020 23:16";
    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    LocalDateTime date2 = LocalDateTime.from(formatter.parse(sdate2));
    Position position2 = new Position(date2, 2, 48, 4, 5, 5);

    String sdate3 = "4/10/2020 23:16";
    DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    LocalDateTime date3 = LocalDateTime.from(formatter.parse(sdate3));
    Position position3 = new Position(date3, 3, 48, 4, 5, 5);

    String sdate4 = "5/10/2020 23:16";
    DateTimeFormatter formatter4 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    LocalDateTime date4 = LocalDateTime.from(formatter.parse(sdate4));
    Position position4 = new Position(date4, 4, 48, 4, 5, 5);

    String sdate5 = "6/10/2020 23:16";
    DateTimeFormatter formatter5 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    LocalDateTime date5 = LocalDateTime.from(formatter.parse(sdate5));
    Position position5 = new Position(date5, 5, 48, 4, 5, 5);

    String sdate6 = "7/10/2020 23:16";
    DateTimeFormatter formatter6 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    LocalDateTime date6 = LocalDateTime.from(formatter.parse(sdate6));
    Position position6 = new Position(date6, 6, 48, 4, 5, 5);


    String sdate7 = "10/10/2020 23:16";
    DateTimeFormatter formatter7 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    LocalDateTime date7 = LocalDateTime.from(formatter.parse(sdate7));
    Position position7 = new Position(date7, 7, 48, 4, 5, 5);

    String sdate8 = "15/10/2020 23:16";
    DateTimeFormatter formatter8 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    LocalDateTime date8 = LocalDateTime.from(formatter.parse(sdate8));
    Position position8 = new Position(date8, 8, 48, 4, 5, 5);

    String sdate9 = "16/10/2020 23:16";
    DateTimeFormatter formatter9 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    LocalDateTime date9 = LocalDateTime.from(formatter.parse(sdate9));
    Position position9 = new Position(date9, 9, 48, 4, 5, 5);

    String sdate10 = "30/10/2020 23:16";
    DateTimeFormatter formatter10 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    LocalDateTime date10 = LocalDateTime.from(formatter.parse(sdate10));
    Position position10 = new Position(date10, 10, 48, 4, 5, 5);


    Position[] arr = {position6, position4, position1, position3, position2, position5, position10, position9, position7, position8};
    Position[] inorderT = {position1, position2, position3, position4, position5, position6, position7, position8, position9, position10};
    Position[] preorderT = {position6, position4, position1, position3, position2, position5, position10, position9, position7, position8};
    Position[] posorderT = {position2, position3, position1, position5, position4, position8, position7, position9, position10, position6};
    int[] height = {0, 1, 2, 3, 4, 4, 4, 4, 4, 4};
    BinarySearchTree instance;

    public BinarySearchTreeTest() {
    }

    @BeforeEach
    void setUp() {
        instance = new BinarySearchTree<Position>();
        for (Position i : arr) {
            instance.insert(i);
        }
    }


    @Test
    void testSize() {
        assertEquals(instance.size(), arr.length);

        BinarySearchTree sInstance = new BinarySearchTree();
        assertEquals(0,sInstance.size());
        sInstance.insert(position1);
        assertEquals(1,sInstance.size());
        sInstance.insert(position2);
        assertEquals(2,sInstance.size());
        sInstance.insert(position2);
        assertEquals(2,sInstance.size());
    }

    @Test
    void testInsert() {
        int[] arr = {20, 15, 10, 13, 8, 17, 40, 50, 30, 20, 15, 10};
        BinarySearchTree<Integer> instance = new BinarySearchTree();
        for (int i = 0; i < 9; i++) {            //new elements
            instance.insert(arr[i]);
            assertEquals(i + 1,instance.size());
        }
        for (int i = 9; i < arr.length; i++) {    //duplicated elements => same size
            instance.insert(arr[i]);
            assertEquals(9, instance.size());
        }
    }


    @Test
    void testRemove() {

        String sdate = "1/10/2020 14:16";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm");
        LocalDateTime date = LocalDateTime.from(formatter.parse(sdate));
        Position positionNotExistent = new Position(date, 64, 48, 4, 5, 5);

        int qtd = arr.length;
        instance.remove(positionNotExistent);

        assertEquals(instance.size(), qtd);
        for (int i = 0; i < arr.length; i++) {
            instance.remove(arr[i]);
            qtd--;
            assertEquals(qtd, instance.size());
        }

        instance.remove(999);
        assertEquals(0, instance.size());
    }


    @Test
    void testIsEmpty() {

        assertFalse(instance.isEmpty());
        instance = new BinarySearchTree();
        assertTrue(instance.isEmpty());

        instance.insert(11);
        assertFalse(instance.isEmpty());

        instance.remove(11);
        assertTrue(instance.isEmpty());
    }


    @Test
    void testHeight() {
        instance = new BinarySearchTree();
        assertEquals(-1,instance.height());
        for (int idx = 0; idx < arr.length; idx++) {
            instance.insert(arr[idx]);
            assertEquals(instance.height(), height[idx]);
        }
        instance = new BinarySearchTree();
        assertEquals(-1,instance.height());

        instance = new BinarySearchTree();
        instance.insert(position6);
        assertEquals(0,instance.height());
        instance.insert(position10);
        assertEquals(1,instance.height());
        instance.insert(position4);
        assertEquals(1,instance.height());
        instance.insert(position9);
        assertEquals(2,instance.height());
        instance.insert(position3);
        assertEquals(2,instance.height());
        instance.insert(position1);
        assertEquals(3,instance.height());

    }


    @Test
    void find() {
        assertNull(instance.find(null, position1));
        assertEquals(position7, instance.find(position7));
    }

    @Test
    void testSmallestElement() {
        assertEquals(position1, instance.smallestElement());
        instance.remove(position1);
        assertEquals(position2, instance.smallestElement());
        instance.remove(position2);
        assertEquals(position3, instance.smallestElement());
    }


    @Test
    void testProcessBstByLevel() {
        BinarySearchTree<Position> instance1 = new BinarySearchTree();
        assertEquals(new HashMap<>(), instance1.nodesByLevel());

        Map<Integer, List<Position>> expResult2 = new HashMap();
        expResult2.put(0, Collections.singletonList(position1));

        BinarySearchTree<Position> instance2 = new BinarySearchTree();
        instance2.insert(position1);
        assertEquals(expResult2, instance2.nodesByLevel());

        Map<Integer, List<Position>> expResult = new HashMap();
        expResult.put(0, Collections.singletonList(position6));
        expResult.put(1, Arrays.asList(position4, position10));
        expResult.put(2, Arrays.asList(position1, position5, position9));
        expResult.put(3, Arrays.asList(position3, position7));
        expResult.put(4, Arrays.asList(position2, position8));

        Map<Integer, List<Position>> result = instance.nodesByLevel();

        for (Map.Entry<Integer, List<Position>> e : result.entrySet())
            assertEquals(expResult.get(e.getKey()), e.getValue());
    }


    @Test
    void testInOrder() {
        List<Position> lExpected = Arrays.asList(inorderT);
        assertEquals(lExpected, instance.inOrder());
    }


    @Test
    void testpreOrder() {
        List<Position> lExpected = Arrays.asList(preorderT);
        assertEquals(lExpected, instance.preOrder());
    }

    @Test
    void testposOrder() {
        List<Position> lExpected = Arrays.asList(posorderT);
        assertEquals(lExpected, instance.posOrder());
    }

    @Test
    void toStringTest(){
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("|-------Position{latitude=10.0, longitude=48.0, heading=4.0, sog=5.0, cog=5.0}\n");
        stringBuilder.append("|\t|-------Position{latitude=9.0, longitude=48.0, heading=4.0, sog=5.0, cog=5.0}\n");
        stringBuilder.append("|\t|\t|\t|-------Position{latitude=8.0, longitude=48.0, heading=4.0, sog=5.0, cog=5.0}\n");
        stringBuilder.append("|\t|\t|-------Position{latitude=7.0, longitude=48.0, heading=4.0, sog=5.0, cog=5.0}\n");
        stringBuilder.append("Position{latitude=6.0, longitude=48.0, heading=4.0, sog=5.0, cog=5.0}\n");
        stringBuilder.append("|\t|-------Position{latitude=5.0, longitude=48.0, heading=4.0, sog=5.0, cog=5.0}\n");
        stringBuilder.append("|-------Position{latitude=4.0, longitude=48.0, heading=4.0, sog=5.0, cog=5.0}\n");
        stringBuilder.append("|\t|\t|-------Position{latitude=3.0, longitude=48.0, heading=4.0, sog=5.0, cog=5.0}\n");
        stringBuilder.append("|\t|\t|\t|-------Position{latitude=2.0, longitude=48.0, heading=4.0, sog=5.0, cog=5.0}\n");
        stringBuilder.append("|\t|-------Position{latitude=1.0, longitude=48.0, heading=4.0, sog=5.0, cog=5.0}\n");
        assertEquals(stringBuilder.toString(),instance.toString());
    }
}