package utils;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class StringUtilsTest {
    @Test
    public void testItalics() throws Exception {
        String input = "blep";
        String expectedOutput = "*" + input + "*";
        assertEquals(expectedOutput, StringUtils.italics(input));
    }

    @Test
    public void testBold() throws Exception {
        String input = "blep";
        String expectedOutput = "**" + input + "**";
        assertEquals(expectedOutput, StringUtils.bold(input));
    }

    @Test
    public void testBoldItalics() throws Exception {
        String input = "blep";
        String expectedOutput = "***" + input + "***";
        assertEquals(expectedOutput, StringUtils.boldItalics(input));
    }

}