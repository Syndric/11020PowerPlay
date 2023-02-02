package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.robocol.Command;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;

import java.util.function.DoubleSupplier;

public class MecanumDriveCommand extends CommandBase {
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    private DoubleSupplier axial;
    private DoubleSupplier yaw;
    private DoubleSupplier lateral;
    private Telemetry telemetry;

    public MecanumDriveCommand(MecanumDriveSubsystem mecanumDriveSubsystem,
                   DoubleSupplier axial,
                   DoubleSupplier yaw,
                   DoubleSupplier latral,
                   Telemetry telemetry) {

        this.mecanumDriveSubsystem = mecanumDriveSubsystem;
        this.axial = axial;
        this.yaw = yaw;
        this.lateral = lateral;
        this.telemetry = telemetry;
        addRequirements(mecanumDriveSubsystem);
    }

    @Override
    public void execute() {
        mecanumDriveSubsystem.Drive(
                axial.getAsDouble(),
                yaw.getAsDouble(),
                lateral.getAsDouble()
        );
    }

}