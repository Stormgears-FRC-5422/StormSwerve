package frc.utils.SwerveDriveUtils;

import com.ctre.phoenix.sensors.AbsoluteSensorRange;
import com.ctre.phoenix.sensors.CANCoder;

public class CANCoderAbsoluteEncoder implements IAbsoluteEncoder {

    private CANCoder encoder;

    public CANCoderAbsoluteEncoder(CANCoder encoder) {
        this.encoder = encoder;
        encoder.configAbsoluteSensorRange(AbsoluteSensorRange.Unsigned_0_to_360);
    }

    @Override
    public double getAbsoluteAngle() {
        return encoder.getPosition(); // i think this will work because I set the config like that
    }
}
