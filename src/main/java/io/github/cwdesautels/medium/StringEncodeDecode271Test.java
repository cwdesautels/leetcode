package io.github.cwdesautels.medium;

import java.util.LinkedList;
import java.util.List;

/**
 * Design an algorithm to encode a list of strings to a single string. The encoded string is then decoded back to the original list of strings.
 * <p>
 * Please implement encode and decode
 * <p>
 * Example 1:
 * <p>
 * Input: ["neet","code","love","you"]
 * <p>
 * Output:["neet","code","love","you"]
 * <p>
 * Example 2:
 * <p>
 * Input: ["we","say",":","yes"]
 * <p>
 * Output: ["we","say",":","yes"]
 * <p>
 * Constraints:
 * <p>
 * 0 <= strs.length < 100
 * 0 <= strs[i].length < 200
 * strs[i] contains only UTF-8 characters.
 */
public class StringEncodeDecode271Test {
    private final int DELIMITER_CODE_POINT = Character.MIN_CODE_POINT;
    private final String DELIMITER_STRING = Character.toString(DELIMITER_CODE_POINT);

    public String encode(List<String> strs) {
        final var buffer = new StringBuilder(256);
        final var itr = strs.iterator();

        if (itr.hasNext()) {
            buffer.append(itr.next());
        }

        while (itr.hasNext()) {
            buffer.append(DELIMITER_STRING).append(itr.next());
        }

        return buffer.toString();
    }

    public List<String> decode(String str) {
        final var list = new LinkedList<String>();

        str.codePoints()
                .filter(codePoint -> codePoint != DELIMITER_CODE_POINT)
                .mapToObj(Character::toString)
                .forEach(list::add);

        return list;
    }

}