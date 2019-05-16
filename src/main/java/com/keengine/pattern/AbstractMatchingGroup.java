package com.keengine.pattern;

import java.util.List;
import java.util.regex.Matcher;

public abstract class AbstractMatchingGroup {

    protected abstract void match(Matcher matcher, List<String> matchList);

}
