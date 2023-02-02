package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public class ArmSetStateCommand extends CommandBase {
    private ArmSubsystem armSubsystem;
    private Telemetry telemetry;
    int stateModifier;
    boolean directSet;

    /**
     * Main command for setting arm state
     * @param armSubsystem arm subsystem
     * @param stateModifier If directset, choose state. If changeset, -1 or 1
     * @param directSet True if directset, false if changeset
     * @param telemetry telemetry
     */
    public ArmSetStateCommand(ArmSubsystem armSubsystem, int stateModifier, boolean directSet, Telemetry telemetry) {
        this.armSubsystem = armSubsystem;
        this.telemetry = telemetry;
        this.stateModifier = stateModifier;
        this.directSet = directSet;
        addRequirements(armSubsystem);
    }

    @Override
    public void initialize() {
        if(directSet) {
            armSubsystem.setState(stateModifier);
        } else {
            armSubsystem.changeState(stateModifier);
        }
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
