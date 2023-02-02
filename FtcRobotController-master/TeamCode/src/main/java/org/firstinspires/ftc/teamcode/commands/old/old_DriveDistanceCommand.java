//Preset 1/10/23
package org.firstinspires.ftc.teamcode.commands.old;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;

public class old_DriveDistanceCommand extends CommandBase {
    public enum DriveDirection {
        FORWARDS,
        BACKWARDS,
        STOP
    }
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    private old_DriveDistanceCommand.DriveDirection DriveDirection;
    private double DistanceInches;
    private double startPosition;
    static double countsPerRevolution = 7;
    static double gearRatio = 40;
    static double positionsPerRevolution = countsPerRevolution * gearRatio;
    static double wheelDiameterInches = 4;
    static double wheelCircInches = wheelDiameterInches * Math.PI;

    public old_DriveDistanceCommand(MecanumDriveSubsystem mecanumDriveSubsystem, old_DriveDistanceCommand.DriveDirection driveDirection, double distanceInches) {
        this.mecanumDriveSubsystem = mecanumDriveSubsystem;
        this.DriveDirection = driveDirection;
        DistanceInches = distanceInches;
    }
    private double distanceTraveled(){
        return Math.abs((mecanumDriveSubsystem.getLeftBackDrivePosition() - startPosition))/ positionsPerRevolution * wheelCircInches;
    }

    @Override
    public void initialize() {
        startPosition = mecanumDriveSubsystem.getLeftBackDrivePosition();
    }

    @Override
    public void execute() {
        switch(DriveDirection){
            case FORWARDS:
                mecanumDriveSubsystem.Drive(-0.5,0,0);
                break;
            case BACKWARDS:
                mecanumDriveSubsystem.Drive(0.5,0,0);
                break;
            case STOP:
                mecanumDriveSubsystem.Drive(0,0,0);
                break;
        }
    }

    @Override
    public boolean isFinished() {
        return distanceTraveled() >= DistanceInches;
    }

    @Override
    public void end(boolean interrupted) {
        mecanumDriveSubsystem.Drive(0,0,0);

    }
}
