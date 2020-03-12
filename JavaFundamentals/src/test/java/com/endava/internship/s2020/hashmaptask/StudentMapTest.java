package com.endava.internship.s2020.hashmaptask;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StudentMapTest {

    private Map<Integer, Integer> map = new StudentMap<>(2);

    @Test
    void shouldAddOnlyUniqueElements() {
        map.put(1, 1);
        map.put(1, null);
        map.put(3, 2);
        map.put(null, null);
        assertThat(map).containsKeys(1, 3, null);
        assertThat(map).containsValues(null, 2, null);
        assertThat(map).size().isEqualTo(3);

    }

    @Test
    void shouldReturnTheNumberOfUniqueElements() {
        map.put(1, 1);
        map.put(1, 2);
        map.put(3, 2);
        assertThat(map).size().isEqualTo(2);
    }

    @Test
    void shouldReturnTrueIfCollectionIsEmpty() {
        map.put(12, 13);
        map.clear();
        assertThat(map).isEmpty();
        map.put(11, 23);
        assertThat(map.isEmpty()).isFalse();
    }

    @Test
    void shouldReturnTrueWhenCollectionContainsKey() {
        map.put(null, 1);
        map.put(11, 12);
        assertThat(map.keySet()).contains(null, 11);
        assertThat(map.containsKey(987)).isFalse();
    }

    @Test
    void shouldReturnFalseForValueWhichDoesntExists() {
        map.put(null, 1);
        map.put(1, null);
        assertThat(map).containsValues(null, 1);
        assertThat(map.containsValue(111)).isEqualTo(false);
    }

    @Test
    void shouldReturnValueForExistingKey() {
        map.put(1, 111);
        assertThat(map.get(1)).isEqualTo(111);
    }

    @Test
    void shouldReturnRemovedValue() {
        map.put(22, 8);
        assertThat(map.remove(22)).isEqualTo(8);
        assertThat(map.containsKey(22)).isFalse();
    }

    @Test
    void shouldTestPutAllMethod() {
        map.put(1, 2);
        map.put(null, 0);
        Map<Integer, Integer> tempMap = new StudentMap<>();
        tempMap.put(1, 3);
        tempMap.put(33, 34);
        tempMap.put(null, null);
        map.putAll(tempMap);
        assertThat(map.keySet()).containsOnlyOnce(1, 33, null);
    }

    @Test
    void shouldMakeCollectionEmpty() {
        map.put(1, 1);
        map.put(2, 2);
        map.clear();
        assertThat(map).isEmpty();
    }

    @Test
    void shouldReturnListOfValues() {
        map.put(null, null);
        map.put(1, null);
        map.put(7, 8);
        assertThat(map).containsValues(8, null);
    }

    @Test
    void shouldReturnString() {
        map.put(99, 98);
        map.put(null, 100);
        map.put(45, null);
        assertThat(map.toString()).isEqualTo("{null = 100,99 = 98,45 = null,}");
    }

    @Test
    void shouldReturnNullForKeyWhichDoesntExists() {
        assertThat(map.get(999)).isNull();
    }

    @Test
    void shouldRemoveTheElementWhichIsFirstInList() {
        Map<String, Integer> tempMap = new StudentMap<>(1);
        tempMap.put("a", 22);
        tempMap.put("aa", 222);
        tempMap.put("aaa", 2222);
        assertThat(tempMap.remove("a")).isEqualTo(22);
        assertThat(tempMap.containsKey("a")).isFalse();
    }

    @Test
    void shouldRemoveTheElementInTheMiddleOfList() {
        Map<String, Integer> tempMap = new StudentMap<>(1);
        tempMap.put("a", 2);
        tempMap.put("aa", 22);
        tempMap.put("aaa", 222);
        tempMap.put("aaaa", 2223);
        assertThat(tempMap.remove("aaa")).isEqualTo(222);
        assertThat(tempMap.containsKey("aaa")).isFalse();
    }

    @Test
    void shouldReturnNullValue() {
        assertThat(map.remove(7894)).isNull();
    }

    @Test
    void shouldThrowConstructorException() {
        assertThatThrownBy(() -> new StudentMap<>(-555))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Argument must be non-negative and greater than 0!");
    }

    @Test
    void shouldThrowUnsupportedOperationException() {
        assertThatThrownBy(() -> map.entrySet())
                .isInstanceOf(UnsupportedOperationException.class);
    }
}