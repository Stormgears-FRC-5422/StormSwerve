package frc.utils.SwerveDriveUtils;

import edu.wpi.first.math.controller.PIDController;
import frc.robot.Constants;
import frc.utils.motorcontrol.StormSpark;

public class StormSparkSwivelController implements ISwivelController {
    private final StormSpark motor;
    private PIDController controller;
    private final IAbsoluteEncoder encoder;
    private double referenceAngle;
    private double angle;
    public StormSparkSwivelController(StormSpark motor, IAbsoluteEncoder encoder) {
        this.motor = motor;
        controller.setP(Constants.kSwivelP);
        controller.setI(Constants.kSwivelI);
        controller.setD(Constants.kSwivelD);
        this.encoder = encoder;
    }


    @Override
    public double getReferenceAngle() {
        return referenceAngle;
    }

    @Override
    public void setAngle(double referenceAngle) {
        this.referenceAngle = referenceAngle;
        //TODO: do we add a ff?????
        double pidout = controller.calculate(getCurrentAngle(), referenceAngle);
        motor.set(pidout);
    }

    @Override
    public double getCurrentAngle() {
        return encoder.getAbsoluteAngle();
    }
}
