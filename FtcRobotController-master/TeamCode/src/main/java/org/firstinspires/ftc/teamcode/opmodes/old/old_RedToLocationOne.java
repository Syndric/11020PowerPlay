//Edited a little bit
package org.firstinspires.ftc.teamcode.opmodes.old;


import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


import org.firstinspires.ftc.teamcode.commands.old.old_DriveDistanceCommand;
import org.firstinspires.ftc.teamcode.commands.old.old_DriveStrafeDistanceCommand;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TelemetryUpdateSubsystem;

@Autonomous(name = "Red To Location One", group = "2")
public class old_RedToLocationOne extends CommandOpMode {

    TelemetryUpdateSubsystem telemetryUpdateSubsystem;


    @Override
    public void initialize() {
        telemetryUpdateSubsystem = new TelemetryUpdateSubsystem(telemetry);
//            DriveDistance driveDistance = new DriveDistance(mecanumDriveSubsystem,
//                    0, 0, -0.5, 24);
//            schedule(new SequentialCommandGroup(driveDistance.withTimeout(1500)));
        MecanumDriveSubsystem mecanumDriveSubsystem = new MecanumDriveSubsystem(hardwareMap, telemetry);

        old_DriveStrafeDistanceCommand driveRightStrafeCommand =
                new old_DriveStrafeDistanceCommand(mecanumDriveSubsystem,
                        old_DriveStrafeDistanceCommand.Direction.RIGHT,36, telemetry);
        old_DriveStrafeDistanceCommand driveLeftStrafeCommand =
                new old_DriveStrafeDistanceCommand(mecanumDriveSubsystem,
                        old_DriveStrafeDistanceCommand.Direction.LEFT,36, telemetry);
        old_DriveDistanceCommand driveForwardsCommand =
                new old_DriveDistanceCommand(mecanumDriveSubsystem,
                        old_DriveDistanceCommand.DriveDirection.FORWARDS,36);
        old_DriveDistanceCommand driveBackwardsCommand =
                new old_DriveDistanceCommand(mecanumDriveSubsystem,
                        old_DriveDistanceCommand.DriveDirection.BACKWARDS,36);
        old_DriveDistanceCommand driveStopCommand =
                new old_DriveDistanceCommand(mecanumDriveSubsystem,
                        old_DriveDistanceCommand.DriveDirection.STOP,0);
        // schedule(new SequentialCommandGroup(driveLeftStrafeCommand, driveBackwardsCommand, driveRightStrafeCommand, driveForwardsCommand));
        schedule(new SequentialCommandGroup(driveForwardsCommand));
        schedule(new SequentialCommandGroup(driveBackwardsCommand));
        schedule(new SequentialCommandGroup(driveLeftStrafeCommand));
        schedule(new SequentialCommandGroup(driveRightStrafeCommand));
        register(telemetryUpdateSubsystem);
    }
}
