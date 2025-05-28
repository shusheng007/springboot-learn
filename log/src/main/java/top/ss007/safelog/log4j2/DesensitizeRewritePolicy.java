package top.ss007.safelog.log4j2;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.rewrite.RewritePolicy;
import org.apache.logging.log4j.core.config.Node;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.impl.Log4jLogEvent;
import org.apache.logging.log4j.message.SimpleMessage;
import top.ss007.safelog.core.HandlePolicy;
import top.ss007.safelog.core.ReplaceMatcher;

import java.util.Arrays;
import java.util.List;


@Plugin(
        name = "DesensitizeRewritePolicy",
        category = Node.CATEGORY,
        elementType = "rewritePolicy",
        printObject = true
)
public class DesensitizeRewritePolicy implements RewritePolicy {

    private final String regex;
    private final int depth;
    private final String policy;
    private final int maxLength;
    private final ReplaceMatcher replaceMatcher;
    private final List<String> skipLoggers;

    public DesensitizeRewritePolicy(String regex, int depth, String policy, int maxLength, ReplaceMatcher replaceMatcher, List<String> skipLoggers) {
        this.regex = regex;
        this.depth = depth;
        this.policy = policy;
        this.maxLength = maxLength;
        this.replaceMatcher = replaceMatcher;
        this.skipLoggers = skipLoggers;
    }

    @PluginFactory
    public static DesensitizeRewritePolicy createPolicy(
            @PluginAttribute("regex") String regex,
            @PluginAttribute("depth") Integer depth,
            @PluginAttribute("policy") String policy,
            @PluginAttribute("maxLength") Integer maxLength,
            @PluginAttribute("skipLoggers") String skipLoggers) {

        ReplaceMatcher replaceMatcher = null;
        if (!"NA".equalsIgnoreCase(regex)) {
            replaceMatcher = new ReplaceMatcher(regex, depth);
        }
        if (skipLoggers == null) {
            skipLoggers = "";
        }
        return new DesensitizeRewritePolicy(regex, depth, policy, maxLength, replaceMatcher, Arrays.asList(skipLoggers.split(" ")));
    }


    @Override
    public LogEvent rewrite(LogEvent event) {
        String source = event.getMessage().getFormattedMessage();
        if (source == null || source.isEmpty()) {
            return event;
        }

        int length = source.length();
        boolean isOutLengthLimit = length > maxLength;
        if (isOutLengthLimit || replaceMatcher != null) {
            Log4jLogEvent.Builder logEventBuilder = new Log4jLogEvent.Builder(event);
            StringBuilder sb = new StringBuilder(isOutLengthLimit ? maxLength + 6 : length);
            if (isOutLengthLimit) {
                sb.append(source, 0, maxLength)
                        .append("<<<");
            } else {
                sb.append(source);
            }

            if (replaceMatcher != null && !skipLoggers.contains(event.getLoggerName())) {
                return logEventBuilder.setMessage(new SimpleMessage(replaceMatcher.execute(sb, HandlePolicy.fromName(policy)))).build();
            }
            return logEventBuilder.setMessage(new SimpleMessage(sb.toString())).build();
        }

        return event;
    }
}
