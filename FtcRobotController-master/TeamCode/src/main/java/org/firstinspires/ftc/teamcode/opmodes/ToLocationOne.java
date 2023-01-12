//Stock
package org.firstinspires.ftc.teamcode.opmodes;


import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


import org.firstinspires.ftc.teamcode.commands.DriveDistanceCommand;
import org.firstinspires.ftc.teamcode.commands.DriveStrafeDistanceCommand;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TelemetryUpdateSubsystem;

@Autonomous(name = "ToLocationOne", group = "1")
public class ToLocationOne extends CommandOpMode {

    TelemetryUpdateSubsystem telemetryUpdateSubsystem;


    @Override
    public void initialize() {
        telemetryUpdateSubsystem = new TelemetryUpdateSubsystem(telemetry);
        MecanumDriveSubsystem mecanumDriveSubsystem = new MecanumDriveSubsystem(hardwareMap, telemetry);
        DriveDistanceCommand driveForwardCommand =
                new DriveDistanceCommand(mecanumDriveSubsystem,
                        DriveDistanceCommand.DriveDirection.FORWARDS,24);
        DriveStrafeDistanceCommand driveLeftStrafeCommand =
                new DriveStrafeDistanceCommand(mecanumDriveSubsystem,
                        DriveStrafeDistanceCommand.Direction.LEFT,26,telemetry);
        schedule(new SequentialCommandGroup(driveForwardCommand));
        schedule(new SequentialCommandGroup(driveLeftStrafeCommand));
        register(telemetryUpdateSubsystem);
    }
}
