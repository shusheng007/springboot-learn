package top.shusheng007.safelog.core;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static top.shusheng007.safelog.core.HandlePolicy.DROP;
import static top.shusheng007.safelog.core.HandlePolicy.REPLACE;

public class ReplaceMatcher {
    private final Pattern pattern;
    private final int depth;

    public ReplaceMatcher(String regex, int depth) {
        this.pattern = Pattern.compile(regex);
        this.depth = depth;
    }

    public String execute(StringBuilder source, HandlePolicy policy) {
        Matcher matcher = pattern.matcher(source.toString());

        int depthCounter = 0;
        while (matcher.find() && (depthCounter < depth)) {
            depthCounter++;
            int start = matcher.start();
            int end = matcher.end();
            if (start < 0 || end < 0) {
                break;
            }
            source.replace(start, end, facade(matcher.group(), policy));
        }
        return source.toString();
    }


    private String facade(String source, HandlePolicy policy) {
        final int length = source.length();
        StringBuilder sb = new StringBuilder(source);

        if (policy == DROP) {
            return ">" + length + "<";
        }
        if (policy == REPLACE) {
            if (length > 128) {
                return sb.replace(3, length - 3, String.format("[%s]", length - 6)).toString();
            }
            if (length > 10) {
                return sb.replace(3, length - 3, repeat('*', length - 6)).toString();
            }
        }
        return sb.replace(0, length, repeat('*', length)).toString();
    }

    private String repeat(char markSign, int times) {
        char[] r = new char[times];
        Arrays.fill(r, markSign);
        return new String(r);
    }
}
