// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package frc.robot.subsystems;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import frc.robot.Robot;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class Wheels extends Subsystem {

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	private PWMVictorSPX frontRight;
	private PWMVictorSPX bottomRight;
	private SpeedControllerGroup rightMotorGroup;
	private PWMVictorSPX frontLeft;
	private PWMVictorSPX bottomLeft;
	private SpeedControllerGroup leftMotorGroup;
	private DifferentialDrive wheels;

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	public Wheels() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
		frontRight = new PWMVictorSPX(1);
		addChild("Front Right", frontRight);
		frontRight.setInverted(false);

		bottomRight = new PWMVictorSPX(3);
		addChild("Bottom Right", bottomRight);
		bottomRight.setInverted(false);

		rightMotorGroup = new SpeedControllerGroup(frontRight, bottomRight);
		addChild("Right Motor Group", rightMotorGroup);

		frontLeft = new PWMVictorSPX(0);
		addChild("Front Left", frontLeft);
		frontLeft.setInverted(false);

		bottomLeft = new PWMVictorSPX(2);
		addChild("Bottom Left", bottomLeft);
		bottomLeft.setInverted(false);

		leftMotorGroup = new SpeedControllerGroup(frontLeft, bottomLeft);
		addChild("Left Motor Group", leftMotorGroup);

		wheels = new DifferentialDrive(rightMotorGroup, frontRight);
		addChild("wheels", wheels);
		wheels.setSafetyEnabled(true);
		wheels.setExpiration(0.1);
		wheels.setMaxOutput(1.0);

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
	}

	@Override
	public void initDefaultCommand() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		setDefaultCommand(new Drive());

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	@Override
	public void periodic() {
		// Put code here to be run every loop

	}

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public String mode = "arcade";
	private boolean full_speed = true;
	public double mod = 1.0;
	double R_trigger;
	double L_trigger;
	double trigger_speedBF;
	double speedLR;

	public void Drive(Joystick ctrl) {
		R_trigger = ctrl.getRawAxis(3);
		L_trigger = -ctrl.getRawAxis(2);
		trigger_speedBF = R_trigger + L_trigger;
		speedLR = ctrl.getRawAxis(0);
		if (full_speed) {
			mod = 1.0;
		} else {
			mod = 0.5;
		}
		double straight = mod * trigger_speedBF;
		double turn = speedLR;
		SmartDashboard.putNumber("Acceleration", straight);
		SmartDashboard.putNumber("Turn", turn);
		switch (mode) {
		case "arcade":
			wheels.arcadeDrive(straight, turn);
			break;
		case "tank":
			wheels.tankDrive(R_trigger, L_trigger);
			break;
		case "curvature":
			wheels.curvatureDrive(straight, turn, true);
			break;
		default:
			wheels.arcadeDrive(straight, turn);
			break;
		}
		// wheels.arcadeDrive(straight, turn);
	}

	public void set_half() {
		full_speed = false;
	}

	public void set_full() {
		full_speed = true;
	}
	public boolean isFull(){
		return full_speed;
	}

	public void reset() {
		wheels.arcadeDrive(0.0, 0.0);
	}
}
