//Settings changed, not setup fully
package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class MecanumDriveSubsystem extends SubsystemBase {
    private GamepadEx driverOp;
    private Telemetry telemetry;
    // private IMUSystem imuSystem;
    private MecanumDrive mecanumDrive;
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    private Motor leftFrontDrive, leftBackDrive, rightFrontDrive, rightBackDrive;

    public MecanumDriveSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        leftFrontDrive = new Motor(hardwareMap, "LEFT_FRONT");
        leftBackDrive = new Motor(hardwareMap, "LEFT_BACK");
        rightFrontDrive = new Motor(hardwareMap, "RIGHT_FRONT");
        rightBackDrive = new Motor(hardwareMap, "RIGHT_BACK");

        leftFrontDrive.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBackDrive.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontDrive.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackDrive.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        mecanumDrive = new MecanumDrive(leftFrontDrive, rightFrontDrive, leftBackDrive, rightBackDrive);
    }
    public void Drive(double forward, double turn, double lateral) {
        mecanumDrive.driveRobotCentric(lateral, forward, turn, true);
    }

    public double getLeftBackDrivePosition() {
        return leftBackDrive.encoder.getPosition();
    }

    public double getEnconderPosition() {
        return leftBackDrive.encoder.getPosition();
    }

    @Override
    public void periodic() {
        telemetry.addData("Front left/Right", "%4.2f, %4.2f", leftFrontDrive.motor.getPower(), rightFrontDrive.motor.getPower());
        telemetry.addData("Back  left/Right", "%4.2f, %4.2f", leftBackDrive.motor.getPower(), rightBackDrive.motor.getPower());
    }
}
