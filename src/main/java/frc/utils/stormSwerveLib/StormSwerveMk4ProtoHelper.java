package frc.utils.stormSwerveLib;

import com.swervedrivespecialties.swervelib.*;
import com.swervedrivespecialties.swervelib.ctre.*;
import com.swervedrivespecialties.swervelib.rev.NeoDriveControllerFactoryBuilder;
import com.swervedrivespecialties.swervelib.rev.NeoSteerConfiguration;
import com.swervedrivespecialties.swervelib.rev.NeoSteerControllerFactoryBuilder;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;

public final class StormSwerveMk4ProtoHelper {
    private StormSwerveMk4ProtoHelper() {
    }

    private static DriveControllerFactory<?, Integer> getFalcon500DriveFactory(Mk4ModuleConfiguration configuration) {
        return new Falcon500DriveControllerFactoryBuilder()
                .withVoltageCompensation(configuration.getNominalVoltage())
                .withCurrentLimit(configuration.getDriveCurrentLimit())
                .build();
    }

    private static SteerControllerFactory<?, Falcon500SteerConfiguration<CanCoderAbsoluteConfiguration>> getFalcon500SteerFactory(Mk4ModuleConfiguration configuration) {
        return new Falcon500SteerControllerFactoryBuilder()
                .withVoltageCompensation(configuration.getNominalVoltage())
                .withPidConstants(0.2, 0.0, 0.1)
                .withCurrentLimit(configuration.getSteerCurrentLimit())
                .build(new CanCoderFactoryBuilder()
                        .withReadingUpdatePeriod(100)
                        .build());
    }

    private static DriveControllerFactory<?, Integer> getNeoDriveFactory(Mk4ModuleConfiguration configuration) {
        return new NeoDriveControllerFactoryBuilder()
                .withVoltageCompensation(configuration.getNominalVoltage())
                .withCurrentLimit(configuration.getDriveCurrentLimit())
                .build();
    }

    private static SteerControllerFactory<?, NeoSteerConfiguration<CanCoderAbsoluteConfiguration>> getNeoSteerFactory(Mk4ModuleConfiguration configuration) {
        return new NeoSteerControllerFactoryBuilder()
                .withVoltageCompensation(configuration.getNominalVoltage())
                .withPidConstants(1.0, 0.0, 0.1)
                .withCurrentLimit(configuration.getSteerCurrentLimit())
                .build(new CanCoderFactoryBuilder()
                        .withReadingUpdatePeriod(100)
                        .build());
    }


    /**
     * Creates a Mk4i swerve module that uses NEOs for driving and steering.
     * Module information is displayed in the specified ShuffleBoard container.
     *
     * @param container        The container to display module information in.
     * @param configuration    Module configuration parameters to use.
     * @param gearRatio        The gearing configuration the module is in.
     * @param driveMotorPort   The CAN ID of the drive NEO.
     * @param steerMotorPort   The CAN ID of the steer NEO.
     * @param steerEncoderPort The CAN ID of the steer CANCoder.
     * @param steerOffset      The offset of the CANCoder in radians.
     * @return The configured swerve module.
     */
    public static SwerveModule createNeo(
            ShuffleboardLayout container,
            Mk4ModuleConfiguration configuration,
            StormSwerveMk4ProtoHelper.GearRatio gearRatio,
            int driveMotorPort,
            int steerMotorPort,
            int steerEncoderPort,
            double steerOffset
    ) {
        return new SwerveModuleFactory<>(
                gearRatio.getConfiguration(),
                getNeoDriveFactory(configuration),
                getNeoSteerFactory(configuration)
        ).create(
                container,
                driveMotorPort,
                new NeoSteerConfiguration<>(
                        steerMotorPort,
                        new CanCoderAbsoluteConfiguration(steerEncoderPort, steerOffset)
                )
        );
    }

    /**
     * Creates a Mk4i swerve module that uses NEOs for driving and steering.
     * Module information is displayed in the specified ShuffleBoard container.
     *
     * @param container        The container to display module information in.
     * @param gearRatio        The gearing configuration the module is in.
     * @param driveMotorPort   The CAN ID of the drive NEO.
     * @param steerMotorPort   The CAN ID of the steer NEO.
     * @param steerEncoderPort The CAN ID of the steer CANCoder.
     * @param steerOffset      The offset of the CANCoder in radians.
     * @return The configured swerve module.
     */
    public static SwerveModule createNeo(
            ShuffleboardLayout container,
            StormSwerveMk4ProtoHelper.GearRatio gearRatio,
            int driveMotorPort,
            int steerMotorPort,
            int steerEncoderPort,
            double steerOffset
    ) {
        return createNeo(container, new Mk4ModuleConfiguration(), gearRatio, driveMotorPort, steerMotorPort, steerEncoderPort, steerOffset);
    }

    /**
     * Creates a Mk4i swerve module that uses NEOs for driving and steering.
     *
     * @param configuration    Module configuration parameters to use.
     * @param gearRatio        The gearing configuration the module is in.
     * @param driveMotorPort   The CAN ID of the drive NEO.
     * @param steerMotorPort   The CAN ID of the steer NEO.
     * @param steerEncoderPort The CAN ID of the steer CANCoder.
     * @param steerOffset      The offset of the CANCoder in radians.
     * @return The configured swerve module.
     */
    public static SwerveModule createNeo(
            Mk4ModuleConfiguration configuration,
            StormSwerveMk4ProtoHelper.GearRatio gearRatio,
            int driveMotorPort,
            int steerMotorPort,
            int steerEncoderPort,
            double steerOffset
    ) {
        return new SwerveModuleFactory<>(
                gearRatio.getConfiguration(),
                getNeoDriveFactory(configuration),
                getNeoSteerFactory(configuration)
        ).create(
                driveMotorPort,
                new NeoSteerConfiguration<>(
                        steerMotorPort,
                        new CanCoderAbsoluteConfiguration(steerEncoderPort, steerOffset)
                )
        );
    }

    /**
     * Creates a Mk4i swerve module that uses NEOs for driving and steering.
     *
     * @param gearRatio        The gearing configuration the module is in.
     * @param driveMotorPort   The CAN ID of the drive NEO.
     * @param steerMotorPort   The CAN ID of the steer NEO.
     * @param steerEncoderPort The CAN ID of the steer CANCoder.
     * @param steerOffset      The offset of the CANCoder in radians.
     * @return The configured swerve module.
     */
    public static SwerveModule createNeo(
            StormSwerveMk4ProtoHelper.GearRatio gearRatio,
            int driveMotorPort,
            int steerMotorPort,
            int steerEncoderPort,
            double steerOffset
    ) {
        return createNeo(new Mk4ModuleConfiguration(), gearRatio, driveMotorPort, steerMotorPort, steerEncoderPort, steerOffset);
    }


    public enum GearRatio {
        L1(SdsModuleConfigurations.MK4I_L1),
        L2(SdsModuleConfigurations.MK4I_L2),
        L3(SdsModuleConfigurations.MK4I_L3);

        private final ModuleConfiguration configuration;

        GearRatio(ModuleConfiguration configuration) {
            this.configuration = configuration;
        }

        public ModuleConfiguration getConfiguration() {
            return configuration;
        }
    }
}
