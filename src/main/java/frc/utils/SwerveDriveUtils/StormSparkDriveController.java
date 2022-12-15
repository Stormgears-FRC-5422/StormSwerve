package frc.utils.SwerveDriveUtils;

import frc.robot.Constants;
import frc.utils.motorcontrol.StormSpark;

public class StormSparkDriveController implements IDriveController {
    private final StormSpark motor;
    public StormSparkDriveController(StormSpark motor) {
        this.motor = motor;
        //to get actual velocity and position
        double conversionFactor = Math.PI * Constants.kWheelDiameter *
                (1/Constants.kDriveMotorGearRatio);
        motor.getEncoder().setPositionConversionFactor(conversionFactor);
        //gets RPM * conversion then divides by 60 to get m/s
        motor.getEncoder().setVelocityConversionFactor(conversionFactor / 60.0);
    }

    @Override
    public void setSpeed(double speedMetersPerSecond) {
        motor.set((speedMetersPerSecond/Constants.kMaxVelocity));
    }

    @Override
    public double getVelocity() {
        return motor.getEncoder().getVelocity();
    }
}
