package top.ss007.log;

import ch.qos.logback.classic.encoder.PatternLayoutEncoder;

import java.text.MessageFormat;
import java.util.Optional;

public class DesensitizedPatternLayoutEncoder extends PatternLayoutEncoder {

    protected static final String DOMAIN_DELIMITER = "|--|";
    protected static final String FIELD_DELIMITER = "|";
    protected static final String PATTERN_D1 = "%d'{'yyyy-MM-dd HH:mm:ss.SSS'}'|{0}|%X'{'RequestId:-'}'";
    protected static final String PATTERN_D2_S1 = "{0}:%property'{'{1}'}'";
    protected static final String PATTERN_D2_S2 = "{0}:%X'{'{1}:--'}'";
    protected static final String PATTERN_D3_S1 = "[%t] %-5level %logger{50} %line - ";
    //0:message最大长度（超出则截取），1:正则表达式，2:policy，3:查找深度（超过深度后停止正则匹配）
    protected static final String PATTERN_D3_S2 = "%m'{'{0},{1},{2},{3}'}'%n";
    public static final int MAX_LENGTH_LIMIT = 10240;

    protected String mdcKeys;//来自MDC的key,多个key用逗号分隔。

    protected String regex = "-";//匹配的正则表达式，如果此值为null或者"-",那么policy、deep参数都将无效

    protected int maxLength = 2048;//单条消息的最大长度，主要是message

    protected String policy = "replace";//如果匹配成功，字符串的策略。

    protected int depth = 128;

    protected boolean useDefaultRegex = true;

    protected static final String DEFAULT_REGEX = "'((?<\\d)1[3-9]\\d{9}(?!\\d))'";//手机号，11位数字，并且前后位不再是数字。
    //系统参数，如果未指定，则使用default;
    protected String systemProperties;

    protected static final String DEFAULT_SYSTEM_PROPERTIES = "project,profiles,cloudPlatform,clusterName";

    @Override
    public void start() {
        if (getPattern() == null) {
            StringBuilder sb = new StringBuilder();
            String d1 = MessageFormat.format(PATTERN_D1, Utils.getHostName());
            sb.append(d1)
                    .append(DOMAIN_DELIMITER);
            //拼装系统参数，如果当前数据视图不存在，则先set一个默认值
            if (systemProperties == null || systemProperties.isEmpty()) {
                systemProperties = DEFAULT_SYSTEM_PROPERTIES;
            }

            String[] properties = systemProperties.split(",");
            for (String property : properties) {
                String value = Optional.ofNullable(Utils.getSystemProperty(property)).orElse("-");
                sb.append(MessageFormat.format(PATTERN_D2_S1, property, value))
                        .append(FIELD_DELIMITER);
            }

            //拼接MDC参数
            if (mdcKeys != null) {
                String[] keys = mdcKeys.split(",");
                for (String key : keys) {
                    sb.append(MessageFormat.format(PATTERN_D2_S2, key, key));
                    sb.append(FIELD_DELIMITER);
                }
                sb.append(DOMAIN_DELIMITER)
                        .append(FIELD_DELIMITER);
            }
            sb.append(PATTERN_D3_S1);

            if (PolicyEnum.fromName(policy) == PolicyEnum.UNKNOWN) {
                policy = "-";
            }

            if (maxLength < 0 || maxLength > MAX_LENGTH_LIMIT) {
                maxLength = 2048;
            }

            //如果用户设置了自己的正则则使用，否则使用默认的
            if (!"-".equalsIgnoreCase(regex)) {
                useDefaultRegex = false;
            }
            if (useDefaultRegex) {
                regex = DEFAULT_REGEX;
            }

            sb.append(MessageFormat.format(PATTERN_D3_S2, String.valueOf(maxLength), regex, policy, String.valueOf(depth)));
            setPattern(sb.toString());
        }
        super.start();
    }

    public String getMdcKeys() {
        return mdcKeys;
    }

    public void setMdcKeys(String mdcKeys) {
        this.mdcKeys = mdcKeys;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public Boolean getUseDefaultRegex() {
        return useDefaultRegex;
    }

    public boolean isUseDefaultRegex() {
        return useDefaultRegex;
    }

    public void setUseDefaultRegex(boolean useDefaultRegex) {
        this.useDefaultRegex = useDefaultRegex;
    }

    @Override
    public String getPattern() {
        return super.getPattern();
    }

    @Override
    public void setPattern(String pattern) {
        super.setPattern(pattern);
    }

    public String getSystemProperties() {
        return systemProperties;
    }

    public void setSystemProperties(String systemProperties) {
        this.systemProperties = systemProperties;
    }
}
