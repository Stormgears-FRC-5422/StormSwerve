package frc.utils.SwerveDriveUtils;

import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import frc.utils.motorcontrol.StormSpark;
import frc.utils.motorcontrol.StormTalon;

public class SwerveModule implements ISwerveModule {
    private StormSparkDriveController drive;
    private StormSparkSwivelController swivel;

    public SwerveModule(int driveMotorID, int swivelMotorID, int stormTalonID, int offsetTicks) {
        //initialize driveController
        StormSpark driveMotor = new StormSpark(driveMotorID, CANSparkMaxLowLevel.MotorType.kBrushless, StormSpark.MotorKind.kNeo);
        drive = new StormSparkDriveController(driveMotor);
        //initializer swivelController
        StormSpark swivelMotor = new StormSpark(swivelMotorID, CANSparkMaxLowLevel.MotorType.kBrushless, StormSpark.MotorKind.kNeo);
        StormTalon talon = new StormTalon(stormTalonID);
        talon.setOffset(offsetTicks);
        SRXMagAbsoluteEncoder absoluteEncoder = new SRXMagAbsoluteEncoder(talon);
        swivel = new StormSparkSwivelController(swivelMotor, absoluteEncoder);
    }


    @Override
    public double getDriveVel() {
        return drive.getVelocity();
    }

    @Override
    public double getSteerAngle() {
        return swivel.getCurrentAngle();
    }

    @Override
    public void set(SwerveModuleState state) {
        //TODO: drive speed based on m/s
        drive.setSpeed(state.speedMetersPerSecond);
        swivel.setAngle(state.angle.getDegrees());
    }
}
