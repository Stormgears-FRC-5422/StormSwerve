package frc.utils.stormSwerveLib;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.sensors.CANCoderConfiguration;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import frc.utils.motorcontrol.StormTalon;

import static frc.robot.Constants.kTicksPerRotation;

public class StormMagCoder {
    private final int id;
    private final StormTalon talon;

    public StormMagCoder(int id) {
        this.id = id;
        this.talon = new StormTalon(id);
        // TODO - where should this really be done? See also StormMagCoderFactoryBuilder
        this.talon.setOffset(0); // This should at least get the mag and rel encoders aligned
    }

    public ErrorCode configAllSettings(CANCoderConfiguration config, int i) {
        // TODO - save config settings
        return ErrorCode.OK;
    }

    public ErrorCode setStatusFramePeriod(StatusFrame sensorData, int periodMilliseconds, int i) {
        // TODO - check that this is getting the right frame
        talon.setStatusFramePeriod(sensorData, periodMilliseconds, i);
        return ErrorCode.OK;
    }

    public double getAbsolutePositionDegrees() {
        return 360. * ((double)talon.getPositionTicks() / kTicksPerRotation);
    }

}
