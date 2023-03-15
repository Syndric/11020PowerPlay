//Preset 1/10/23
package org.firstinspires.ftc.teamcode.commands.old;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;

import java.util.function.DoubleSupplier;


public class old_DriveStrafeDistanceCommand extends CommandBase {
    public enum Direction {
        RIGHT,
        LEFT
    }
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    private Direction strafeDirection;
    private double DistanceInches;
    private double startPosition;
    static double countsPerRevolution = 7;
    static double gearRatio = 40;
    static double positionsPerRevolution = countsPerRevolution * gearRatio;
    static double wheelDiameterInches = 4;
    static double wheelCircInches = wheelDiameterInches * Math.PI;
    private Telemetry telemetry;

    public old_DriveStrafeDistanceCommand(MecanumDriveSubsystem mecanumDriveSubsystem, Direction strafeDirection, double distanceInches, Telemetry telemetry) {
        this.mecanumDriveSubsystem = mecanumDriveSubsystem;
        this.strafeDirection = strafeDirection;
        this.telemetry = telemetry;
        DistanceInches = distanceInches;
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
//        telemetry.addData("Distance Traveled", distanceTraveled());
        switch(strafeDirection){
            case LEFT:
                mecanumDriveSubsystem.Drive(0,0,-0.25);
                break;
            case RIGHT:
                mecanumDriveSubsystem.Drive(0,0,0.25);
                break;
        }
    }


    //it works

    @Override
    public boolean isFinished() {
        return distanceTraveled() >= DistanceInches;
    }
    @Override
    public void end(boolean interrupted) {
        mecanumDriveSubsystem.Drive(0,0,0);
    }
}