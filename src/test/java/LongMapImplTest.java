import de.comparus.opensource.longmap.LongMap;
import de.comparus.opensource.longmap.LongMapImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class LongMapImplTest {

    @Test
    public void testMethodPut() {
        LongMap<String> map = new LongMapImpl<>();
        assertEquals("one", map.put(1L, "one"));
        assertEquals("two", map.put(2L, "two"));
        assertEquals("three", map.put(3L, "three"));
        assertEquals("forth", map.put(4L, "forth"));
        assertEquals("fifth", map.put(5L, "fifth"));
    }

    @Test
    public void testMethodPutForSameKey() {
        LongMap<String> map = new LongMapImpl<>();
        assertEquals("one", map.put(1L, "one"));
        assertEquals("two", map.put(1L, "two"));
        assertEquals("three", map.put(2L, "three"));
        assertNull(map.put(2L, null));
    }

    @Test
    public void testMethodGet() {
        LongMap<String> map = new LongMapImpl<>();
        assertEquals(map.put(1L, "one"), map.get(1L));
        assertEquals(map.put(2L, "two"), map.get(2L));
        assertEquals(map.put(3L, "three"), map.get(3L));
        assertEquals(map.put(4L, null), map.get(4L));
        assertNull(map.get(-5L));
    }

    @Test
    public void testMethodRemove() {
        LongMap<String> map = new LongMapImpl<>();
        assertEquals(map.put(1L, "one"), map.remove(1L));
        assertEquals(map.put(2L, "two"), map.remove(2L));
        assertEquals(map.put(3L, "three"), map.remove(3L));
        assertNull(map.remove(4L));
        assertEquals(map.put(5L, null), map.remove(5L));
        assertNull(map.remove(6L));
        map.put(7L, "one");
        map.put(8L, "two");
        assertEquals(map.put(9L, "three"), map.remove(9L));
    }

    @Test
    public void testMethodIsEmpty() {
        LongMap<String> map = new LongMapImpl<>();
        assertTrue(map.isEmpty());
        map.put(1L, "one");
        assertFalse(map.isEmpty());
    }

    @Test
    public void testMethodClear() {
        LongMap<Integer> map = new LongMapImpl<>();
        map.clear();
        assertEquals(0, map.size());
        map.put(1L, 1);
        map.put(2L, 2);
        assertNotEquals(0, map.size());
        map.clear();
        assertEquals(0, map.size());
    }

    @Test
    public void testMethodContainKey() {
        LongMap<Integer> map = new LongMapImpl<>();
        map.put(1L, 0);
        assertTrue(map.containsKey(1L));
        assertFalse(map.containsKey(2L));
    }

    @Test
    public void testMethodContainValue() {
        LongMap<String> map = new LongMapImpl<>();
        map.put(1L, "one");
        assertTrue(map.containsValue("one"));
        assertFalse(map.containsValue("three"));
    }

    @Test
    public void testMethodKeys() {
        LongMap<String> map = new LongMapImpl<>();
        map.put(1L, "one");
        map.put(2L, "one");
        map.put(3L, "one");
        map.put(4L, "two");
        map.put(5L, "three");
        map.put(6L, null);
        long[] expected = new long[]{1L, 2L, 3L, 4L, 5L, 6L};
        long[] actual = map.keys();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testMethodValues() {
        LongMap<Integer> map = new LongMapImpl<>();
        Integer[] expected = new Integer[10];
        for (int i = 0; i < 10; i++) {
            map.put(i, i);
            expected[i] = i;
        }
        assertArrayEquals(expected, map.values());
    }

    @Test
    public void testMethodSize() {
        LongMap<String> map = new LongMapImpl<>();
        map.put(1L, "one");
        assertEquals(1, map.size());
        map.put(2L, "two");
        assertEquals(2, map.size());
        map.put(3L, "three");
        assertEquals(3, map.size());
        map.put(4L, "four");
        assertEquals(4, map.size());
    }
}
