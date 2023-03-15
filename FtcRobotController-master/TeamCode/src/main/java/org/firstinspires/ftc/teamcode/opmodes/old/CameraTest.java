package org.firstinspires.ftc.teamcode.opmodes.old;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.old.old_CameraSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TelemetryUpdateSubsystem;

@TeleOp(name = "Camera Test", group = "2")
public class CameraTest extends CommandOpMode {
    @Override
    public void initialize() {
        old_CameraSubsystem cameraSubsystem = new old_CameraSubsystem(hardwareMap,telemetry);
        TelemetryUpdateSubsystem telemetryUpdateSubsystem = new TelemetryUpdateSubsystem(telemetry);
        register(telemetryUpdateSubsystem, cameraSubsystem);

    }
}
