package frc.utils.SwerveDriveUtils;

import edu.wpi.first.math.kinematics.SwerveModuleState;

public interface ISwerveModule {

    double getDriveVel();

    double getSteerAngle();

    void set(SwerveModuleState state);
}
