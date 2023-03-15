//Edited a little bit
package org.firstinspires.ftc.teamcode.opmodes;


import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


import org.firstinspires.ftc.teamcode.commands.DriveDistanceCommand;
import org.firstinspires.ftc.teamcode.commands.old.old_DriveStrafeDistanceCommand;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TelemetryUpdateSubsystem;

@Autonomous(name = "TestAuto", group = "2")
public class TestAuto extends CommandOpMode {

    TelemetryUpdateSubsystem telemetryUpdateSubsystem;


    @Override
    public void initialize() {
        telemetryUpdateSubsystem = new TelemetryUpdateSubsystem(telemetry);
//            DriveDistance driveDistance = new DriveDistance(mecanumDriveSubsystem,
//                    0, 0, -0.5, 24);
//            schedule(new SequentialCommandGroup(driveDistance.withTimeout(1500)));
        MecanumDriveSubsystem mecanumDriveSubsystem = new MecanumDriveSubsystem(hardwareMap, telemetry);
        SequentialCommandGroup move = new SequentialCommandGroup(
                new DriveDistanceCommand(mecanumDriveSubsystem, DriveDistanceCommand.DriveDirection.FORWARD, 36, telemetry),
                new DriveDistanceCommand(mecanumDriveSubsystem, DriveDistanceCommand.DriveDirection.LEFT, 36, telemetry),
                new DriveDistanceCommand(mecanumDriveSubsystem, DriveDistanceCommand.DriveDirection.BACKWARD, 36, telemetry),
                new DriveDistanceCommand(mecanumDriveSubsystem, DriveDistanceCommand.DriveDirection.RIGHT, 36, telemetry)
        );

        schedule(move);
        register(telemetryUpdateSubsystem);
    }
}
