package top.shusheng007.safelog.log4j2;

import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.rewrite.RewritePolicy;
import org.apache.logging.log4j.core.config.Node;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.impl.Log4jLogEvent;
import org.apache.logging.log4j.message.SimpleMessage;
import top.shusheng007.safelog.core.HandlePolicy;
import top.shusheng007.safelog.core.ReplaceMatcher;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Plugin(
        name = "DesensitizeRewritePolicy",
        category = Node.CATEGORY,
        elementType = "rewritePolicy",
        printObject = true
)
public class DesensitizeRewritePolicy implements RewritePolicy {

    private final String policy;
    private final int maxLength;
    private final List<String> plainMarkers;
    private ReplaceMatcher replaceMatcher;

    public DesensitizeRewritePolicy(String regex, int depth, String policy, int maxLength, List<String> plainMarkers) {
        this.policy = policy;
        this.maxLength = maxLength;
        this.plainMarkers = plainMarkers;
        if (!"NA".equalsIgnoreCase(regex)) {
            this.replaceMatcher = new ReplaceMatcher(regex, depth);
        }
    }

    @PluginFactory
    public static DesensitizeRewritePolicy createPolicy(
            @PluginAttribute("regex") String regex,
            @PluginAttribute("depth") Integer depth,
            @PluginAttribute("policy") String policy,
            @PluginAttribute("maxLength") Integer maxLength,
            @PluginAttribute("plainMarkers") String plainMarkers) {

        List<String> plainMarkerList = Arrays.stream(Optional.ofNullable(plainMarkers).orElse("").split(",")).toList();
        return new DesensitizeRewritePolicy(regex, depth, policy, maxLength, plainMarkerList);
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

            if (replaceMatcher != null && !isMarkAsPlainLog(event.getMarker())) {
                return logEventBuilder.setMessage(new SimpleMessage(replaceMatcher.execute(sb, HandlePolicy.fromName(policy)))).build();
            }
            return logEventBuilder.setMessage(new SimpleMessage(sb.toString())).build();
        }

        return event;
    }

    private boolean isMarkAsPlainLog(Marker marker) {
        if (marker == null) {
            return false;
        }
        return plainMarkers.contains(marker.getName());
    }
}
