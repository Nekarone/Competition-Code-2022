/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

public final class Constants {

    // === SWERVEDRIVE === //

    // SwerveDrive MOTOR Ports
  
    public static final int MOTOR_PORT_1 = 1;
    public static final int MOTOR_PORT_2 = 3;
    public static final int MOTOR_PORT_3 = 5;
    public static final int MOTOR_PORT_4 = 7; 
    public static final int ROTATOR_PORT_1 = 2;
    public static final int ROTATOR_PORT_2 = 4;
    public static final int ROTATOR_PORT_3 = 6;
    public static final int ROTATOR_PORT_4 = 8;

    // Intake Ports
    //Needs calibrating
    public static final int ROLLER_INTAKE_PORT = 10;
    //Not sure if intake needs pistons or not
    //public static final int INTAKE_PISTON_PORT_1 = 10;
    //public static final int INTAKE_PISTON_PORT_2 = 11;

    // Shooter Ports
    //Needs calibrating
    public static final int SHOOTER_MOTOR_PORT_1 = 1;
    public static final int SHOOTER_MOTOR_PORT_2 = 2;
    public static final int SHOOTER_PISTON_PORT_1 = 9;
    public static final int SHOOTER_PISTON_PORT_2 = 10;

    //Gyro
    public static final int GYRO_PORT = 9;

    //Controller Constants
    public static final int DRIVER_CONTROLLER = 1;
    public static final int OPERATOR_CONTROLLER = 0;
    public static final int TRANSLATIONAL_HORIZONTAL_AXIS = 0;
    public static final int TRANSLATIONAL_VERTICAL_AXIS = 1;
    public static final int ROTATIONAL_HORIZONTAL_AXIS = 4;
    public static final double CONTROLLER_SENSITIVITY = 0.1; // The value from the center of an axis to where it is, after which the code will actually run for the rotaters
    public static final int DRIVESWITCHBUTTON = 9; // Check this
    public static final int INTAKE_BUTTON = 3;
    public static final int OUTTAKE_BUTTON = 4;
    public static final int SHOOT_BUTTON = 5;
    public static final int LOWERCATAPULT_BUTTON = 6;
    public static final int RELEASECATAPULT_BUTTON = 7;

    // Swervedrive Constants
    public static final double ANGLE_RANGE = 2;
    public static final double P_CONSTANT = 1.0/360.0;
    public static final double GEAR_RATIO = 12.8;
    public static final double GEAR_RATIO_ROTATER = 6.86;
    public static final double UNITS_PER_ROTATION = 2048;
    public static final double L = 24.174;
    public static final double W = 24.183;
    public static final double CATAPULT_SPEED = 0.4;
    
    //Speed constants
    public static final double ROLLER_INTAKE_SPEED = 0.5;
    public static final double SHOOTER_PISTON_SPEED = 0.5;

    //Swervedrive PID Stuff
    public static final int kSlotIdx = 0;
	public static final int kPIDLoopIdx = 0;
    public static final int kTimeoutMs = 30;
    public static final Gains kGains = new Gains(0.2, 0.0, 0.0, 0.0, 0, 0.0); //coamnds 
    public static final Gains jGains = new Gains(0.1,0.0,0.0,0.0,0, 0.0); // spinners
    public static final double ERROR_TOLERANCE = 25;
    public static final double ROTATOR_ERROR_TOLERANCE = 25;
}