package top.ss007.log.cuslog;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DesensitizedMessageConverter extends MessageConverter {

    protected String regex = "-";
    protected int depth = 100;
    protected String policy = "replace";
    protected int maxLength = 10240;
    private ReplaceMatcher replaceMatcher = null;

    @Override
    public void start() {
        List<String> options = getOptionList();
        //如果存在参数选项，则提取
        if (options != null) {
            try {
                maxLength = Integer.valueOf(options.get(0));
                regex = options.get(1);
                policy = options.get(2);
                depth = Integer.valueOf(options.get(3));
            } catch (Exception e) {
                e.printStackTrace();
            }

            if ((!"-".equals(regex))
                    && (PolicyEnum.fromName(policy) != PolicyEnum.UNKNOWN)
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
            StringBuilder sb = new StringBuilder(isOutLengthLimit ? maxLength + 6 : length + 6);
            //超长截取
            if (isOutLengthLimit) {
                sb.append(source, 0, maxLength)
                        .append("<<<");
            } else {
                sb.append(source);
            }

            if (replaceMatcher != null) {
                return replaceMatcher.execute(sb, PolicyEnum.fromName(policy));
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

        public String execute(StringBuilder source, PolicyEnum policy) {

            Matcher matcher = pattern.matcher(source);

            int depthCounter = 0;
            while (matcher.find() && (depthCounter < depth)) {
                depthCounter++;
                int start = matcher.start();
                int end = matcher.end();
                if (start < 0 || end < 0) {
                    break;
                }
                //匹配到的数据
                String group = matcher.group();
                source.replace(start, end, facade(group, policy));
            }
            return source.toString();
        }

        /**
         * 混淆，但是不改变字符串的长度
         */
        private String facade(String source, PolicyEnum policy) {
            final int length = source.length();
            StringBuilder sb = new StringBuilder(source);

            switch (policy) {
                case DROP:
                    return ">" + length + "<";
                case REPLACE:
                    if (length > 10) {
                        return sb.replace(3, length - 3, repeat('*', length - 6)).toString();
                    } else {
                        return sb.replace(0, length, repeat('*', length)).toString();
                    }
                case ERASE:
                default:
                    return sb.replace(0, length, repeat('*', length)).toString();
            }
        }

        private String repeat(char t, int times) {
            char[] r = new char[times];
            for (int i = 0; i < times; i++) {
                r[i] = t;
            }
            return new String(r);
        }

    }


}
