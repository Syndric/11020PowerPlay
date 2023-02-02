package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public class ArmSetPowerCommand extends CommandBase {
    private ArmSubsystem armSubsystem;
    private double power;
    private Telemetry telemetry;

    public ArmSetPowerCommand(ArmSubsystem armSubsystem, double power) {
        this.armSubsystem = armSubsystem;
        this.power = power;
        addRequirements(armSubsystem);
    }

    @Override
    public void execute() {
        armSubsystem.setPower(power);
    }
}
