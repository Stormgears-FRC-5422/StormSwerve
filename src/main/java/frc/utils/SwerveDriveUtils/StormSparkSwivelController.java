package frc.utils.SwerveDriveUtils;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import frc.utils.motorcontrol.StormSpark;

public class StormSparkSwivelController implements ISwivelController {

    private final StormSpark motor;
    private final RelativeEncoder motorEncoder;
    private SparkMaxPIDController controller;
    private final IAbsoluteEncoder encoder;
    private double referenceAngle;

    public StormSparkSwivelController(StormSpark motor, IAbsoluteEncoder encoder) {
        this.motor = motor;
        this.motorEncoder = motor.getEncoder();
        this.controller = motor.getPIDController();
        this.encoder = encoder;
    }

    public void resetEncoder() {
        motorEncoder.setPosition(encoder.getAbsoluteAngle());
    }

    @Override
    public double getReferenceAngle() {
        return referenceAngle;
    }

    @Override
    public void setAngle(double referenceAngle) {
        this.referenceAngle = referenceAngle;
        //TODO: do we add a ff?????
        controller.setReference(referenceAngle, CANSparkMax.ControlType.kPosition);
    }

    @Override
    public double getCurrentAngle() {
        // get current encoder position and convert to (0, 360)
        double modAngle = motorEncoder.getPosition() % 360;
        return (modAngle < 0.)? modAngle + 360 : modAngle;
    }
}
