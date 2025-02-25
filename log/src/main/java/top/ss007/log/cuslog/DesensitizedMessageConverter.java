package top.ss007.log.cuslog;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.slf4j.Marker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static top.ss007.log.cuslog.HandlePolicy.DROP;
import static top.ss007.log.cuslog.HandlePolicy.REPLACE;

/**
 *
 */
public class DesensitizedMessageConverter extends ClassicConverter {

    protected int maxLength = 10240;
    protected String regex;
    protected HandlePolicy policy = REPLACE;
    protected int depth = 100;

    //the log with these marker will not be masked
    protected List<String> plainMarkers = new ArrayList<>();
    private ReplaceMatcher replaceMatcher = null;

    @Override
    public void start() {
        List<String> options = getOptionList();
        if (options == null || options.isEmpty()) {
            super.start();
            return;
        }

        //read config from option list.
        try {
            final int size = options.size();
            if (size >= 1) {
                maxLength = Integer.valueOf(options.get(0));
            }
            if (size >= 2) {
                regex = options.get(1);
            }
            if (size >= 3) {
                policy = HandlePolicy.fromName(options.get(2));
            }
            if (size >= 4) {
                Integer input = Integer.valueOf(options.get(3));
                if (input > 0) {
                    depth = input;
                }
            }
            if (size >= 5) {
                String markerStr = options.get(4);
                if (Objects.nonNull(markerStr)) {
                    plainMarkers.addAll(Arrays.stream(markerStr.split(",")).toList());
                }
            }
        } catch (Exception e) {
            addError("invalid parameters", e);
        }

        if (Objects.nonNull(regex) && !"NA".equalsIgnoreCase(regex)) {
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

    public static class ReplaceMatcher {
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
            for (int i = 0; i < times; i++) {
                r[i] = markSign;
            }
            return new String(r);
        }

    }


}
