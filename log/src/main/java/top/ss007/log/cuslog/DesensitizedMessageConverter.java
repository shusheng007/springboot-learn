package top.ss007.log.cuslog;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static top.ss007.log.cuslog.HandlePolicy.DROP;
import static top.ss007.log.cuslog.HandlePolicy.REPLACE;

public class DesensitizedMessageConverter extends ClassicConverter {

    protected String regex;
    protected int depth = 100;
    protected String policy = "REPLACE";
    protected int maxLength = 10240;
    private ReplaceMatcher replaceMatcher = null;

    @Override
    public void start() {
        List<String> options = getOptionList();
        //read config from option list.
        if (options != null) {
            try {
                final Integer targetMaxLength = Integer.valueOf(options.get(0));
                if (targetMaxLength > 125) {
                    maxLength = targetMaxLength;
                }
                regex = options.get(1);
                policy = options.get(2);
                depth = Integer.valueOf(options.get(3));
            } catch (Exception e) {
                e.printStackTrace();
            }

            if ((!"NA".equalsIgnoreCase(regex))
                    && (HandlePolicy.fromName(policy) != HandlePolicy.UNKNOWN)
                    && depth > 0) {
                replaceMatcher = new ReplaceMatcher(regex, depth);
            }
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
        if (isOutLengthLimit || replaceMatcher != null) {
            StringBuilder sb = new StringBuilder(isOutLengthLimit ? maxLength + 6 : length);
            //cut to length limit
            if (isOutLengthLimit) {
                sb.append(source, 0, maxLength)
                        .append("<<<");
            } else {
                sb.append(source);
            }

            if (replaceMatcher != null) {
                return replaceMatcher.execute(sb, HandlePolicy.fromName(policy));
            }

            return sb.toString();
        }

        return source;
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
