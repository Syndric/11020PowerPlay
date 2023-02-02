package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.GrabberSubsystem;

public class ServoChangeByPosConstantCommand extends CommandBase {
    private GrabberSubsystem grabberSubsystem;
    private double posdelta;
    private Telemetry telemetry;

    public ServoChangeByPosConstantCommand(GrabberSubsystem grabberSubsystem, double posdelta, Telemetry telemetry) {
        this.grabberSubsystem = grabberSubsystem;
        this.posdelta = posdelta;
        this.telemetry = telemetry;
        addRequirements(grabberSubsystem);
    }

    @Override
    public void execute() {
        grabberSubsystem.posChangeServo(posdelta);
    }
}
