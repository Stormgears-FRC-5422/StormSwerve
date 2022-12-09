package frc.utils.motorcontrol;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.math.MathUtil;

public class StormTalon extends WPI_TalonSRX {
  private double scale = 1.0;
  private int offset = 0;
  private int timeoutMs = 15;
  public boolean atHome = false;

  public StormTalon(int deviceID) {
    super(deviceID);
    this.getSensorCollection().setQuadraturePosition(getPWMPosition(), timeoutMs);
  }

  public void setOffset(int offset) {
    this.offset = offset;
  }

  public int getPWMPosition() {
    return this.getSensorCollection().getPulseWidthPosition() - offset;
  }

  public int getPosition() {
    return this.getSensorCollection().getQuadraturePosition() - offset;
  }

  @Override
  public void set(double speed) {
    // Could put safety features - e.g. temperature control - here.
    super.set(scale * speed);
  }

  // Should be between 0.0 and 1.0 - to account for oddities in the drive train
  // e.g. two different gear ratios
  public void setSpeedScale(double scale) {
    this.scale = MathUtil.clamp(scale, 0, 1.0);
  }

}
