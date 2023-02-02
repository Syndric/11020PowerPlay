package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;

public class MecanumDeclareCommand extends CommandBase {
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    private double axial, yaw, lateral;
    private Telemetry telemetry;

    public MecanumDeclareCommand(MecanumDriveSubsystem mecanumDriveSubsystem,
                               double axial, double yaw, double lateral) {
        this.mecanumDriveSubsystem = mecanumDriveSubsystem;
        this.axial = axial;
        this.yaw = yaw;
        this.lateral = lateral;
        addRequirements(mecanumDriveSubsystem);
    }

    @Override
    public void execute () {
        mecanumDriveSubsystem.Drive(axial, yaw, lateral);
    }
}
