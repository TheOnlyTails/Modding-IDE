package io.github.railroad.debugger.syntax;

import java.util.Map;
import java.util.regex.Pattern;

public class SyntaxObject {

    public String ext;
    public Map<String, EnumSyntaxType> regex;
    public Pattern compiled;

    public SyntaxObject(String ext, Map<String, EnumSyntaxType> regex) {
        this.ext = ext;
        this.regex = regex;
        compile();
    }

    public void compile() {
        final StringBuilder builder = new StringBuilder();
        for (final Map.Entry<String, EnumSyntaxType> e : regex.entrySet()) {
            builder.append("|(?<").append(e.getValue().name()).append(">").append(e.getKey()).append(")");
        }
        compiled = Pattern.compile(builder.substring(1)); // Remove first "|"
    }
}
