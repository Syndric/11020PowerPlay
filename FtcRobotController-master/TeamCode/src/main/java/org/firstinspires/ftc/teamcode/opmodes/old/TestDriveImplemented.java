package org.firstinspires.ftc.teamcode.opmodes.old;

import com.arcrobotics.ftclib.command.CommandOpMode;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TelemetryUpdateSubsystem;

public class TestDriveImplemented extends CommandOpMode {
    GamepadEx driver, operator;
    MecanumDriveSubsystem mecanumDriveSubsystem;
    TelemetryUpdateSubsystem telemetryUpdateSubsystem;

    @Override
    public void initialize() {
        driver = new GamepadEx(gamepad1);
        operator = new GamepadEx(gamepad2);
        mecanumDriveSubsystem = new MecanumDriveSubsystem(hardwareMap, telemetry);
        telemetryUpdateSubsystem = new TelemetryUpdateSubsystem(telemetry);

        while (opModeIsActive()) {
            double max;

            double axial = -gamepad1.left_stick_y;
            double lateral = gamepad1.left_stick_x;
            double yaw = gamepad1.right_stick_x;
            double lifty = -gamepad2.left_stick_y;

            max = Math.max(Math.abs(axial), Math.abs(lateral));
            max = Math.max(max, Math.abs(yaw));

            if(max > 1.0) {
                axial /= max;
                lateral /= max;
                yaw /= max;
            }

            while(opModeIsActive()) {
                mecanumDriveSubsystem.Drive(axial, yaw, lateral);
                telemetry.addData("Axial:", "%4.2f", axial);
                telemetry.addData("Lateral:", "%4.2f", lateral);
                telemetry.addData("Yaw:", "%4.2f", yaw);
                telemetry.update();
            }
        }

    }
}
