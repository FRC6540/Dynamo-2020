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
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class ControlPanel extends Subsystem {

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	private PWMVictorSPX motor;

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	public ControlPanel() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
		motor = new PWMVictorSPX(6);
		addChild("Motor", motor);
		motor.setInverted(false);

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

	}

	@Override
	public void initDefaultCommand() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	@Override
	public void periodic() {
		// Put code here to be run every loop
		String colorString;
		Color detectedColor = m_colorSensor.getColor();
		ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

		if (match.color == kBlueTarget) {
			colorString = "Blue";
		} else if (match.color == kRedTarget) {
			colorString = "Red";
		} else if (match.color == kGreenTarget) {
			colorString = "Green";
		} else if (match.color == kYellowTarget) {
			colorString = "Yellow";
		} else {
			colorString = "Unknown";
		}
		SmartDashboard.putNumber("Red", detectedColor.red);
		SmartDashboard.putNumber("Green", detectedColor.green);
		SmartDashboard.putNumber("Blue", detectedColor.blue);
		SmartDashboard.putNumber("Confidence", match.confidence);
		SmartDashboard.putString("Detected Color", colorString);
	}

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	I2C.Port i2cPort = I2C.Port.kOnboard;
	ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
	ColorMatch m_colorMatcher = new ColorMatch();
	Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
	Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
	Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
	Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);
	public boolean enabled;

	void spinUntil(String color) {
		
	}

	void enable() {
		enabled = true;
	}

	void disable() {
		enabled = false;
	}

	public boolean getState() {
		return enabled;
	}
}
