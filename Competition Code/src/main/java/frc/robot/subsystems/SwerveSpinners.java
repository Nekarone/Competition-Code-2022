/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import static frc.robot.Constants.*;

import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;

// import org.graalvm.compiler.asm.sparc.SPARCAssembler.Br;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SwerveSpinners extends SubsystemBase {

  /** These are the variables for the SwerveSpinners subsytem. */
  public static final double MM_TO_IN = 0.0393701;
  public static final double WHEEL_TO_WHEEL_DIAMETER_INCHES = 320 * MM_TO_IN;
  public static final double WHEEL_DIAMETER_INCHES = 4;
  // It may be more logical to use no SPEED MULTIPLIER and rather just depend on the controller input(investigate)
  // public static final double ROTTRANSCUT = 0;
  public static final double SPEED_MULTIPLIER = 0.65;
  public static final double ROTATION_COEFFICIENT = 0.35;
  private WPI_TalonFX bRMotor, bLMotor, fRMotor, fLMotor;
  private SpeedControllerGroup bR, bL, fR, fL;
  public static boolean swerveSwitch; // tank drive go brrrrrrr
  
  //This is the constructor for this subsytem.
  public SwerveSpinners() {
    swerveSwitch = false;

    bRMotor = new WPI_TalonFX(MOTOR_PORT_4);
    bLMotor = new WPI_TalonFX(MOTOR_PORT_3);
    fRMotor = new WPI_TalonFX(MOTOR_PORT_1);
    fLMotor = new WPI_TalonFX(MOTOR_PORT_2);
// test
    fLMotor.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, 12,13,0.1));
    fLMotor.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 12,13,0.1));

    fRMotor.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, 12,13,0.1));
    fRMotor.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 12,13,0.1));

    bRMotor.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, 12,13,0.1));
    bRMotor.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 12,13,0.1));
    
    bLMotor.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, 12,13,0.1));
    bLMotor.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 12,13,0.1));


    bR = new SpeedControllerGroup(bRMotor);
    bL = new SpeedControllerGroup(bLMotor);
    fR = new SpeedControllerGroup(fRMotor);
    fL = new SpeedControllerGroup(fLMotor);

  }
  public void configPID(){
    
    fLMotor.configFactoryDefault();
    fLMotor.set(ControlMode.Velocity,0);
    fLMotor.config_kP(0, jGains.kP);
    fLMotor.config_kI(0, jGains.kI);
    fLMotor.config_kD(0, jGains.kD);
    fLMotor.config_kF(0, jGains.kF); 
    fLMotor.setSensorPhase(true);

    fRMotor.configFactoryDefault();
    fRMotor.set(ControlMode.Velocity,0);
    fRMotor.config_kP(0, jGains.kP);
    fRMotor.config_kI(0, jGains.kI);
    fRMotor.config_kD(0, jGains.kD);
    fRMotor.config_kF(0, jGains.kF); 
    fRMotor.setSensorPhase(true);

    bLMotor.configFactoryDefault();
    bLMotor.set(ControlMode.Velocity,0);
    bLMotor.config_kP(0, jGains.kP);
    bLMotor.config_kI(0, jGains.kI);
    bLMotor.config_kD(0, jGains.kD);
    bLMotor.config_kF(0, jGains.kF); 
    bLMotor.setSensorPhase(true);
    
    bRMotor.configFactoryDefault();
    bRMotor.set(ControlMode.Velocity,0);
    bRMotor.config_kP(0, jGains.kP);
    bRMotor.config_kI(0, jGains.kI);
    bRMotor.config_kD(0, jGains.kD);
    bRMotor.config_kF(0, jGains.kF); 
    bRMotor.setSensorPhase(true);
    
  }


  public void resetEncoders(){
    fRMotor.setSelectedSensorPosition(0);
    fLMotor.setSelectedSensorPosition(0);
    bLMotor.setSelectedSensorPosition(0);
    bRMotor.setSelectedSensorPosition(0);
  }
  

  public boolean getSwitch() {return swerveSwitch;}

  public void toggleSwitch(){
    if(swerveSwitch==true) swerveSwitch = false;
    else swerveSwitch = true;
  }

  //This function is the default command for the swervedrive motor spinners.
  public void spinMotors(double horizontal, double vertical, double rotationHorizontal, double angle){
    //This -1 is due to how the vertical axis works on the controller. 
    vertical *= -1;

    if(swerveSwitch == false){
      double r = (Math.pow(Math.sqrt(horizontal*horizontal + vertical*vertical),1)*SPEED_MULTIPLIER);

      //Here the initial speeds are set to the value r - calculated above -
      double backRightSpeed = 0;
      double backLeftSpeed = 0;
      double frontRightSpeed = 0;
      double frontLeftSpeed = 0;
      boolean isRotating = Math.abs(rotationHorizontal)>=CONTROLLER_SENSITIVITY;
      boolean isTranslating = (Math.sqrt((Math.pow(vertical, 2) + Math.pow(horizontal, 2))) >= CONTROLLER_SENSITIVITY);

      if (!isRotating&&isTranslating){
        frontRightSpeed = r;
        backLeftSpeed = r;
        backRightSpeed = r;
        frontLeftSpeed = r;
      }

      else if(isRotating && !isTranslating){
        backRightSpeed = -rotationHorizontal*ROTATION_COEFFICIENT;
        frontRightSpeed = -rotationHorizontal*ROTATION_COEFFICIENT;
        backLeftSpeed = -rotationHorizontal*ROTATION_COEFFICIENT;
        frontLeftSpeed = -rotationHorizontal*ROTATION_COEFFICIENT;
      }

      else if (isRotating && isTranslating){
        frontRightSpeed = r;
        backLeftSpeed = r;
        backRightSpeed = r;
        frontLeftSpeed = r;
      }

      bR.set(backRightSpeed);
      bL.set(backLeftSpeed);
      fR.set(frontRightSpeed);
      fL.set(frontLeftSpeed);
    }

    else{
      double y;
      double x;
      if(Math.abs(vertical) < 0.02) y = 0;
      if(Math.abs(rotationHorizontal) < 0.02) x = 0;        
      y = Math.abs(vertical) * SPEED_MULTIPLIER;
      x = Math.abs(rotationHorizontal) * SPEED_MULTIPLIER; 
      bR.set(-(y-x));
      bL.set(y+x);
      fR.set(-(y-x));
      fL.set(y+x);
    }
  }


  public void autoTranslational(double x, double y, double totalDistance){
    double initialPosition = bRMotor.getSelectedSensorPosition();
    while((Math.PI*WHEEL_DIAMETER_INCHES*360*(bRMotor.getSelectedSensorPosition()-initialPosition)/2048)<totalDistance){
      spinMotors(x, -y, 0, 0);
    }
  }


  public double pulsesToCm(double p){
      // 2048 pulses to a rotation
      return p*WHEEL_DIAMETER_INCHES*2.54*Math.PI/UNITS_PER_ROTATION;
  }


  //takes distance (cm) divides by cm per rotation and then multiplies by pulses per rotation
  public double cmToPulses(double cm){
    return GEAR_RATIO_ROTATER*UNITS_PER_ROTATION*cm/(WHEEL_DIAMETER_INCHES*2.54*Math.PI);
  }


  public void driveDistance(double a, double b, double c, double d){
    resetEncoders();
    bRMotor.set(ControlMode.Position, cmToPulses(a));
    bLMotor.set(ControlMode.Position, cmToPulses(b));
    fRMotor.set(ControlMode.Position, cmToPulses(c));
    fLMotor.set(ControlMode.Position, cmToPulses(d));
  }


  public boolean reachedPosition(double a, double b, double c, double d){
    if (checkError(bRMotor, a) && checkError(bLMotor, b) && checkError(fRMotor, c) && checkError(fLMotor, d)) {
      return true;
    }
    return false;
  }


  private boolean checkError(WPI_TalonFX motor, double d){
    return motor.getSelectedSensorPosition() < d + ERROR_TOLERANCE && motor.getSelectedSensorPosition() > d - ERROR_TOLERANCE;
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}