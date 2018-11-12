package com.energyxxer.commodore.util;

import com.energyxxer.commodore.CommandUtils;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberRange<T extends Number> implements Cloneable {
    private final T min;
    private final T max;

    public NumberRange(T min, T max) {
        this.min = min;
        this.max = max;
    }

    public NumberRange(T value) {
        this.min = value;
        this.max = value;
    }

    @Override
    public String toString() {
        if(min != null && min.equals(max)) {
            return CommandUtils.numberToPlainString(min.doubleValue());
        } else {
            return ((min != null) ? CommandUtils.numberToPlainString(min.doubleValue()) : "") + ".." + ((max != null) ? "" + CommandUtils.numberToPlainString(max.doubleValue()) : "");
        }
    }

    @Override
    public NumberRange<T> clone() {
        return new NumberRange<>(min, max);
    }

    public static RangeParseResult<Double> parseDouble(String str) {
        Matcher range = Pattern.compile("(-?\\d+(?:\\.\\d+)?)?\\.\\.(-?\\d+(?:\\.\\d+)?)?").matcher(str);
        if(range.lookingAt()) {
            MatchResult matchResult = range.toMatchResult();

            String rawMin = matchResult.group(1);
            String rawMax = matchResult.group(2);

            Double min = (rawMin != null) ? Double.parseDouble(rawMin) : null;
            Double max = (rawMax != null) ? Double.parseDouble(rawMax) : null;

            return new RangeParseResult<>(range.group(), new NumberRange<>(min, max));
        }
        Matcher exact = Pattern.compile("-?\\d+(?:\\.\\d+)?").matcher(str);
        if(exact.lookingAt()) {
            return new RangeParseResult<>(exact.group(), new NumberRange<>(Double.parseDouble(exact.group())));
        }
        throw new IllegalArgumentException("'" + str + "' is not a double or range");
    }

    public static RangeParseResult<Integer> parseInt(String str) {
        Matcher range = Pattern.compile("(-?\\d+)?\\.\\.(-?\\d+)?").matcher(str);
        if(range.lookingAt()) {
            MatchResult matchResult = range.toMatchResult();

            String rawMin = matchResult.group(1);
            String rawMax = matchResult.group(2);

            Integer min = (rawMin != null) ? Integer.parseInt(rawMin) : null;
            Integer max = (rawMax != null) ? Integer.parseInt(rawMax) : null;

            return new RangeParseResult<>(range.group(), new NumberRange<>(min, max));
        }
        Matcher exact = Pattern.compile("-?\\d+").matcher(str);
        if(exact.lookingAt()) {
            return new RangeParseResult<>(exact.group(), new NumberRange<>(Integer.parseInt(exact.group())));
        }
        throw new IllegalArgumentException("'" + str + "' is not an integer or range");
    }

    public T getMin() {
        return min;
    }

    public T getMax() {
        return max;
    }
}

class RangeParseResult<T extends Number> {
    final String raw;
    final NumberRange<T> result;

    public RangeParseResult(String raw, NumberRange<T> result) {
        this.raw = raw;
        this.result = result;
    }
}