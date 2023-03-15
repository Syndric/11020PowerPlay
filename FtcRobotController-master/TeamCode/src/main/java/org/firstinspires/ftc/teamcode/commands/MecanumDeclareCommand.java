package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;

public class MecanumDeclareCommand extends CommandBase {
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    private double forward, turn, lateral;
    private Telemetry telemetry;

    public MecanumDeclareCommand(MecanumDriveSubsystem mecanumDriveSubsystem,
                               double forward, double turn, double lateral) {
        this.mecanumDriveSubsystem = mecanumDriveSubsystem;
        this.forward = forward;
        this.turn = turn;
        this.lateral = lateral;
        addRequirements(mecanumDriveSubsystem);
    }

    @Override
    public void execute () {
        mecanumDriveSubsystem.Drive(forward, turn, lateral);
    }
}
