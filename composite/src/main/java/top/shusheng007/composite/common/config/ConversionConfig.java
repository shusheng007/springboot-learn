package top.shusheng007.composite.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.unit.DataSize;

import java.time.Duration;

/**
 * Created by Ben.Wang
 * <p>
 * author     : Ben.Wang
 * date       : 2021/5/13 13:12
 * description:
 */
@ConfigurationProperties(prefix = "conversion")
public class ConversionConfig {

    private Duration timeInDefaultUnit;
    private Duration timeInHour;

    private DataSize sizeInDefaultUnit;
    private DataSize sizeInGB;
    private DataSize sizeInTB;

    private Developer developer;

    public void setTimeInDefaultUnit(Duration timeInDefaultUnit) {
        this.timeInDefaultUnit = timeInDefaultUnit;
    }

    public void setTimeInHour(Duration timeInHour) {
        this.timeInHour = timeInHour;
    }

    public void setSizeInDefaultUnit(DataSize sizeInDefaultUnit) {
        this.sizeInDefaultUnit = sizeInDefaultUnit;
    }

    public void setSizeInGB(DataSize sizeInGB) {
        this.sizeInGB = sizeInGB;
    }

    public void setSizeInTB(DataSize sizeInTB) {
        this.sizeInTB = sizeInTB;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    @Override
    public String toString() {
        return "ConversionConfig{" +
                "timeInDefaultUnit=" + timeInDefaultUnit +
                ", timeInHour=" + timeInHour +
                ", sizeInDefaultUnit=" + sizeInDefaultUnit +
                ", sizeInGB=" + sizeInGB +
                ", sizeInTB=" + sizeInTB +
                ", developer=" + developer +
                '}';
    }
}
