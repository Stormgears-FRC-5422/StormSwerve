package frc.utils.SwerveDriveUtils;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.sensors.AbsoluteSensorRange;
import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenix.sensors.CANCoderConfiguration;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import frc.robot.Constants;
import frc.utils.motorcontrol.StormSpark;

public class NeoCanCoderSwerveModule implements ISwerveModule {

    private StormSparkDriveController drive;
    private StormSparkSwivelController swivel;

    public NeoCanCoderSwerveModule(int driveMotorID, int swivelMotorID, int CANCoderID, int offsetDeg) {
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
        //make cancoder with config
        CANCoder canCoder = new CANCoder(CANCoderID);
        CANCoderConfiguration config = new CANCoderConfiguration();
        config.magnetOffsetDegrees = offsetDeg;
        config.absoluteSensorRange = AbsoluteSensorRange.Unsigned_0_to_360;
//        config.sensorDirection = true; //clockwise or counterlclockwise read api
        canCoder.configAllSettings(config);
        while (canCoder.getLastError() != ErrorCode.OK) {
            canCoder.getPosition();
        }
        CANCoderAbsoluteEncoder absoluteEncoder = new CANCoderAbsoluteEncoder(canCoder);
        //make swivel
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
        drive.setSpeed(state.speedMetersPerSecond);
        swivel.setAngle(state.angle.getDegrees());
    }
}
