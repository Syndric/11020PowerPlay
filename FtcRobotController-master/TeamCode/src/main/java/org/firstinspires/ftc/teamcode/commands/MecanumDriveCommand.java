package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;

import java.util.function.DoubleSupplier;

public class MecanumDriveCommand extends CommandBase {
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    private DoubleSupplier forward;
    private DoubleSupplier turn;
    private DoubleSupplier lateral;
    private Telemetry telemetry;

    public MecanumDriveCommand(MecanumDriveSubsystem mecanumDriveSubsystem,
                   DoubleSupplier forward,
                   DoubleSupplier turn,
                   DoubleSupplier lateral,
                   Telemetry telemetry) {

        this.mecanumDriveSubsystem = mecanumDriveSubsystem;
        this.forward = forward;
        this.turn = turn;
        this.lateral = lateral;
        this.telemetry = telemetry;
        addRequirements(mecanumDriveSubsystem);
    }

    @Override
    public void execute() {
        mecanumDriveSubsystem.Drive(
                forward.getAsDouble(),
                turn.getAsDouble(),
                lateral.getAsDouble()
        );
        telemetry.addData("Left x, y", "%4.2f, %4.2f", lateral.getAsDouble(), forward.getAsDouble());
        telemetry.addData("Back x", "%4.2f", turn.getAsDouble());
    }

}