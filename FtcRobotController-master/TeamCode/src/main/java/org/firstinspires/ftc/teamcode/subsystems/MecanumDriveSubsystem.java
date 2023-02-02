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

    private MecanumDrive drive;
    private GamepadEx driverOp;
    private Telemetry telemetry;
    // private IMUSystem imuSystem;
    private MecanumDrive mecanumDrive;
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    private Motor leftBackDrive;
    private Motor leftFrontDrive;
    private Motor rightBackDrive;
    private Motor rightFrontDrive;

    public MecanumDriveSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        leftFrontDrive = new Motor(hardwareMap, "LEFT_FRONT");
        leftBackDrive = new Motor(hardwareMap, "LEFT_BACK");
        rightFrontDrive = new Motor(hardwareMap, "RIGHT_FRONT");
        rightBackDrive = new Motor(hardwareMap, "RIGHT_BACK");

        leftFrontDrive.motor.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.motor.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.motor.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.motor.setDirection(DcMotor.Direction.FORWARD);

        //leftFrontDrive.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //leftBackDrive.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //rightFrontDrive.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //rightBackDrive.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        mecanumDrive = new MecanumDrive(leftFrontDrive, rightFrontDrive, leftBackDrive, rightBackDrive);
    }
    //axial: forward
    //turn: yaw
    //strafe: side
    public void Drive(double forward, double turn, double strafe) {
        mecanumDrive.driveRobotCentric(-strafe, forward, -turn, false);
    }

    public double getLeftBackDrivePosition() {
        return leftBackDrive.getCurrentPosition();
    }

    public double getEnconderPosition() {
        return leftBackDrive.getCurrentPosition();
    }
}
