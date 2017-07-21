package com.vaa25;

import java.util.ArrayList;
import java.util.List;

public final class Strings {

    public static List<String> merge(final List<String> first, final List<String> second){
        final List<String> result = new ArrayList<>(first);
        result.removeAll(second);
        result.addAll(second);
        return result;
    }

    public static List<String> remove(final List<String> first, final List<String> second){
        final List<String> result = new ArrayList<>(first);
        result.removeAll(second);
        return result;
    }
}
