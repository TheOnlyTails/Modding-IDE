package io.github.railroad.config;

import io.github.railroad.Railroad;
import io.github.railroad.debugger.syntax.EnumSyntaxType;
import io.github.railroad.debugger.syntax.SyntaxObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.file.Files.newDirectoryStream;
import static java.nio.file.Paths.get;

public class SyntaxConfig {

    public final List<SyntaxObject> languages = new ArrayList<>();

    public static final SyntaxObject EMPTY = new SyntaxObject("null", new HashMap<>() {{
        put("(?s).*", EnumSyntaxType.ELSE);
    }});

    public SyntaxConfig() {
        final Path folder = get("src/main/resources/assets/syntax");
        try (final DirectoryStream<Path> stream = newDirectoryStream(folder)) {
            for (final Path path : stream) {
                final String extension = path.getFileName().toString().split("\\.")[1];
                if (extension.equals("json")) {
                    final JSONTokener tokenizer = new JSONTokener(
                            Railroad.class.getResourceAsStream("/assets/syntax/" + path.getFileName()));

                    final JSONObject obj = new JSONObject(tokenizer);
                    final JSONArray rules = obj.getJSONArray("rules");

                    final Map<String, EnumSyntaxType> ruleMap = new HashMap<>();
                    for (int j = 0; j < rules.length(); j++) {
                        final JSONObject rule = rules.getJSONObject(j);
                        ruleMap.put(rule.getString("regex"), EnumSyntaxType.valueOf(rule.getString("type")));
                    }

                    languages.add(new SyntaxObject(obj.getString("extension"), ruleMap));
                }
            }
        } catch (IOException reason) {
            throw new RuntimeException(reason);
        }
    }

    public SyntaxObject getByExt(String ext) {
        for (final SyntaxObject o : languages) {
            if (o.ext.equals(ext))
                return o;
        }
        return EMPTY;
    }
}
