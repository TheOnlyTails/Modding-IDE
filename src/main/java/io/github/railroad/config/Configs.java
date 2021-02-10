package io.github.railroad.config;

public final class Configs {

    public final LanguageConfig lang;
    public final SyntaxConfig syntax;

    public Configs() {
        lang = new LanguageConfig("/assets/lang/en_us.json");
        syntax = new SyntaxConfig();
    }
}
