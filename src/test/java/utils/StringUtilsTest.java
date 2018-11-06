package utils;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class StringUtilsTest {
    @Test
    public void testItalics() {
        String input = "blep";
        String expectedOutput = "*" + input + "*";
        assertEquals(expectedOutput, StringUtils.italics(input));
    }

    @Test
    public void testBold() {
        String input = "blep";
        String expectedOutput = "**" + input + "**";
        assertEquals(expectedOutput, StringUtils.bold(input));
    }

    @Test
    public void testBoldItalics() {
        String input = "blep";
        String expectedOutput = "***" + input + "***";
        assertEquals(expectedOutput, StringUtils.boldItalics(input));
    }

    @Test(dataProvider = "stringLists")
    public void testListify(List<String> list, String expected) {
        assertEquals(StringUtils.listify(list), expected);
    }

    @DataProvider
    public Object[][] stringLists() {
        return new Object[][] {
                {Arrays.asList("north", "west", "east", "south"), "north, west, east, and south"},
                {Arrays.asList("north", "west", "east"), "north, west, and east"},
                {Arrays.asList("north", "west"), "north and west"},
                {Collections.singletonList("north"), "north"}
        };
    }

}