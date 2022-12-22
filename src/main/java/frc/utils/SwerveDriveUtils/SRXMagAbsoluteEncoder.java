package frc.utils.SwerveDriveUtils;

import frc.utils.motorcontrol.StormTalon;

public class SRXMagAbsoluteEncoder implements IAbsoluteEncoder {

    StormTalon talon;

    public SRXMagAbsoluteEncoder(StormTalon talon) {
        this.talon = talon;
    }

    @Override
    public double getAbsoluteAngle() {
        double absolutePos = talon.getPosition() % 4096;
        return (absolutePos < 0)? absolutePos + 4096 : absolutePos;
        
    }
}
