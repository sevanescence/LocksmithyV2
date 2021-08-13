package com.makotomiyamoto.locksmithyv2.lib.util;

import java.text.ParseException;
import java.util.regex.Matcher;

/**
 * Because Java's regex library is terrible.
 */
public abstract class MatcherUtils {
    public static class MatcherFindException extends Exception {
        public MatcherFindException(String message) {
            super(message);
        }
    }

    public static String getNextFind(Matcher matcher, int group) throws MatcherFindException {
        if (matcher.find()) {
            return matcher.group(group);
        } else {
            throw new MatcherFindException("Match was not found. Only use this if you know something will match.");
        }
    }
}
