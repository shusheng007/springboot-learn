package top.ss007.log.cuslog;

public enum PolicyEnum {
    REPLACE, DROP, ERASE, UNKNOWN;


    public static PolicyEnum fromName(String inputName) {
        for (PolicyEnum value : PolicyEnum.values()) {
            if (value.name().equalsIgnoreCase(inputName)) {
                return value;
            }
        }
        return PolicyEnum.UNKNOWN;
    }
}
