package frc.utils.SwerveDriveUtils;

import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import frc.robot.Constants;
import frc.utils.motorcontrol.StormSpark;
import frc.utils.motorcontrol.StormTalon;

public class NeoMagEncoderSwerveModule implements ISwerveModule {

    private StormSparkDriveController drive;
    private StormSparkSwivelController swivel;


    public NeoMagEncoderSwerveModule(int driveMotorID, int swivelMotorID, int stormTalonID, int offsetTicks) {
        //initialize driveController
        StormSpark driveMotor = new StormSpark(driveMotorID, CANSparkMaxLowLevel.MotorType.kBrushless, StormSpark.MotorKind.kNeo);
        drive = new StormSparkDriveController(driveMotor);
        //initializer swivelController
        //make swivel motor with encoder set up and controller set up
        StormSpark swivelMotor = new StormSpark(swivelMotorID, CANSparkMaxLowLevel.MotorType.kBrushless, StormSpark.MotorKind.kNeo);
        RelativeEncoder motorEncoder = swivelMotor.getEncoder();
        motorEncoder.setPositionConversionFactor(360. * Constants.kSwivelMotorGearRatio);
        motorEncoder.setVelocityConversionFactor(360. * Constants.kSwivelMotorGearRatio / 60.);
        //make pid controller
        SparkMaxPIDController motorController = swivelMotor.getPIDController();
        motorController.setP(Constants.kSwivelP);
        motorController.setI(Constants.kSwivelI);
        motorController.setD(Constants.kSwivelD);
        motorController.setFeedbackDevice(motorEncoder);
        //Get the absolute encoder
        StormTalon talon = new StormTalon(stormTalonID);
        talon.setOffset(offsetTicks);
        SRXMagAbsoluteEncoder absoluteEncoder = new SRXMagAbsoluteEncoder(talon);
        //create swivel with motor and encoder
        swivel = new StormSparkSwivelController(swivelMotor, absoluteEncoder);
        swivel.resetEncoder();
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
