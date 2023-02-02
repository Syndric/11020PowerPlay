package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

import java.util.function.BooleanSupplier;

public class ArmOperateCommand extends CommandBase {
    private ArmSubsystem armSubsystem;
    private Telemetry telemetry;
    public ArmOperateCommand(ArmSubsystem armSubsystem, Telemetry telemetry) {
        this.armSubsystem = armSubsystem;
        this.telemetry = telemetry;
        addRequirements(armSubsystem);
    }

    @Override
    public void execute() {
        if(!(armSubsystem.getState() == 0)) {
            armSubsystem.approachTargetPosition();
        }

    }
}
