//Preset 1/10/23
package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;

import java.util.function.DoubleSupplier;


public class DriveDistanceCommand extends CommandBase {
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    public enum DriveDirection {
        FORWARD,
        BACKWARD,
        LEFT,
        RIGHT,
        STOP
    }
    public DriveDistanceCommand.DriveDirection driveDirection;
    private double distanceInches;
    private double startPosition;
    static double countsPerRevolution = 7;
    static double gearRatio = 40;
    static double positionsPerRevolution = countsPerRevolution * gearRatio;
    static double wheelDiameterInches = 4;
    static double wheelCircInches = wheelDiameterInches * Math.PI;
    private Telemetry telemetry;

    public DriveDistanceCommand(MecanumDriveSubsystem mecanumDriveSubsystem, DriveDistanceCommand.DriveDirection driveDirection, double distanceInches, Telemetry telemetry) {
        this.mecanumDriveSubsystem = mecanumDriveSubsystem;
        this.driveDirection = driveDirection;
        this.telemetry = telemetry;
        this.distanceInches = distanceInches;
    }

    public double distanceTraveled(){
        return Math.abs((mecanumDriveSubsystem.getLeftBackDrivePosition() - startPosition))/ positionsPerRevolution * wheelCircInches;
    }

    @Override
    public void initialize() {
        startPosition = mecanumDriveSubsystem.getLeftBackDrivePosition();
    }

    @Override
    public void execute() {
        telemetry.addData("Distance Traveled", distanceTraveled());
        telemetry.addData("LB", mecanumDriveSubsystem.getLeftBackDrivePosition());
        telemetry.addData("Start", startPosition);
        switch(driveDirection){
            case LEFT:
                mecanumDriveSubsystem.Drive(0,0,-0.25);
                break;
            case RIGHT:
                mecanumDriveSubsystem.Drive(0,0,0.25);
                break;
            case FORWARD:
                mecanumDriveSubsystem.Drive(0.5,0,0);
                break;
            case BACKWARD:
                mecanumDriveSubsystem.Drive(-0.5,0,0);
                break;
        }
    }
    //it works
    @Override
    public boolean isFinished() {
        return distanceTraveled() >= distanceInches;
    }
    @Override
    public void end(boolean interrupted) {
        mecanumDriveSubsystem.Drive(0,0,0);
    }
}