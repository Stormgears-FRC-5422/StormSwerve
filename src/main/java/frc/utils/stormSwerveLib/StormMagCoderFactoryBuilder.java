package frc.utils.stormSwerveLib;

import com.ctre.phoenix.sensors.AbsoluteSensorRange;
import com.ctre.phoenix.sensors.CANCoderConfiguration;
import com.swervedrivespecialties.swervelib.AbsoluteEncoder;
import com.swervedrivespecialties.swervelib.AbsoluteEncoderFactory;
import com.swervedrivespecialties.swervelib.ctre.CanCoderAbsoluteConfiguration;
import com.swervedrivespecialties.swervelib.ctre.CanCoderFactoryBuilder;
import com.swervedrivespecialties.swervelib.ctre.CtreUtils;

import com.ctre.phoenix.motorcontrol.StatusFrame;

// TODO - Annoying that I needed to reimplement the entire class since there are no accessors for the private members.
// Was this really necessary?
public class StormMagCoderFactoryBuilder extends CanCoderFactoryBuilder {
    private StormMagCoderFactoryBuilder.Direction direction;
    private int periodMilliseconds;

    public StormMagCoderFactoryBuilder() {
        this.direction = StormMagCoderFactoryBuilder.Direction.COUNTER_CLOCKWISE;
        this.periodMilliseconds = 10;
    }

    public StormMagCoderFactoryBuilder withReadingUpdatePeriod(int periodMilliseconds) {
        this.periodMilliseconds = periodMilliseconds;
        return this;
    }

    public StormMagCoderFactoryBuilder withDirection(StormMagCoderFactoryBuilder.Direction direction) {
        this.direction = direction;
        return this;
    }

    public AbsoluteEncoderFactory<CanCoderAbsoluteConfiguration> build() {
        return (configuration) -> {
            CANCoderConfiguration config = new CANCoderConfiguration();
            config.absoluteSensorRange = AbsoluteSensorRange.Unsigned_0_to_360;
            config.magnetOffsetDegrees = Math.toDegrees(configuration.getOffset());
            config.sensorDirection = this.direction == StormMagCoderFactoryBuilder.Direction.CLOCKWISE;
            StormMagCoder encoder = new StormMagCoder(configuration.getId());
            CtreUtils.checkCtreError(encoder.configAllSettings(config, 250), "Failed to configure CANCoder");
            CtreUtils.checkCtreError(encoder.setStatusFramePeriod(StatusFrame.Status_2_Feedback0, this.periodMilliseconds, 250), "Failed to configure CANCoder update rate");
            return new StormMagCoderFactoryBuilder.EncoderImplementation(encoder);
        };
    }

    public enum Direction {
        CLOCKWISE,
        COUNTER_CLOCKWISE;

        Direction() {
        }
    }

    private static class EncoderImplementation implements AbsoluteEncoder {
        private final StormMagCoder encoder;
        private EncoderImplementation(StormMagCoder encoder) {
            this.encoder = encoder;
        }

        public double getAbsoluteAngle() {
            double angle = Math.toRadians(this.encoder.getAbsolutePositionDegrees());
            angle %= 6.283185307179586;
            if (angle < 0.0) {
                angle += 6.283185307179586;
            }

            return angle;
        }
    }
}
