package Junit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Demo03_Parameterized_Test {

    @ParameterizedTest
    @ValueSource(strings = {"amma", "mom", "nitin"})
    void testPalindromePass(String candidate) {
        assertTrue(Demo03_StringUtils.isPalindrome(candidate));
    }
    //fail
    @Test
    void testPalindromeFail() {
        // "hello" is NOT a palindrome, so this will fail
        assertTrue(Demo03_StringUtils.isPalindrome("hello"), "Expected true but got false");
    }

    @ParameterizedTest
    @ValueSource(strings = {"java", "spring", "bank"})
    void testNotPalindrome(String candidate) {
        assertFalse(Demo03_StringUtils.isPalindrome(candidate));
    }
}