package top.ss007.safelog.logback;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.slf4j.Marker;
import top.ss007.safelog.core.HandlePolicy;
import top.ss007.safelog.core.ReplaceMatcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static top.ss007.safelog.core.HandlePolicy.REPLACE;

/**
 *
 */
public class DesensitizeConverter extends ClassicConverter {

    protected String regex;
    protected int maxLength = 100 * 1024;
    protected HandlePolicy policy = REPLACE;
    //the log with these marker will not be masked
    protected List<String> plainMarkers = new ArrayList<>();
    protected int depth = 100;

    private ReplaceMatcher replaceMatcher = null;

    @Override
    public void start() {
        List<String> options = getOptionList();
        if (options == null || options.isEmpty()) {
            super.start();
            return;
        }

        final int size = options.size();
        //read config from option list.
        try {
            regex = options.get(0);

            if (size >= 2) {
                maxLength = Integer.parseInt(options.get(1));
            }
        } catch (Exception e) {
            addError("invalid parameters", e);
        }

        try {
            if (size >= 3) {
                policy = HandlePolicy.fromName(options.get(2));
            }

            if (size >= 4) {
                String markerStr = options.get(3);
                if (Objects.nonNull(markerStr)) {
                    plainMarkers.addAll(Arrays.stream(markerStr.split(",")).toList());
                }
            }

            if (size >= 5) {
                int input = Integer.parseInt(options.get(4));
                if (input > 0) {
                    depth = input;
                }
            }

        } catch (Exception e) {
            addError("invalid parameters", e);
        }

        if (!"NA".equalsIgnoreCase(regex)) {
            replaceMatcher = new ReplaceMatcher(regex, depth);
        }

        super.start();
    }

    @Override
    public String convert(ILoggingEvent event) {
        String source = event.getFormattedMessage();
        if (source == null || source.isEmpty()) {
            return source;
        }

        int length = source.length();
        boolean isOutLengthLimit = length > maxLength;
        if (replaceMatcher != null || isOutLengthLimit) {
            StringBuilder sb = new StringBuilder(isOutLengthLimit ? maxLength + 6 : length);
            //cut to length limit
            if (isOutLengthLimit) {
                sb.append(source, 0, maxLength)
                        .append("<<<");
            } else {
                sb.append(source);
            }

            if (replaceMatcher != null && !isMarkAsPlainLog(event.getMarkerList())) {
                return replaceMatcher.execute(sb, policy);
            }

            return sb.toString();
        }

        return source;
    }

    private boolean isMarkAsPlainLog(List<Marker> markerList) {
        if (markerList == null || markerList.isEmpty()) {
            return false;
        }
        return plainMarkers.contains(markerList.get(0).getName());
    }

}
