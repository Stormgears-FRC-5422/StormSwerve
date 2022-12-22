package frc.utils.SwerveDriveUtils;

public interface ISwivelController {

    double getReferenceAngle();

    void setAngle(double referenceAngle);

    double getCurrentAngle();

    void resetEncoder();
}
