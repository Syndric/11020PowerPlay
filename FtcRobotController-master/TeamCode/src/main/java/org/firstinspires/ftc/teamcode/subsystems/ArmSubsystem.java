package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ArmSubsystem extends SubsystemBase {
    private MotorEx armMotor;
    private Telemetry telemetry;
    private int state;
    /*
    Lift States:
    0 - User Override
    1 - Floor/Rest
    2 - Ground
    3 - Low
    4 - Medium
    5 - High
     */

    //Positions given in ???
    private int[] positions = { //in ticks
            1, // state 1
            2, // state 2
            3, // state 3
            4, // state 4
            5}; // state 5

    public ArmSubsystem(HardwareMap hardwaremap, Telemetry telemetry) {
        this.telemetry = telemetry;
        armMotor = new MotorEx(hardwaremap, "LIFT_MOTOR"); // get motor
        armMotor.setRunMode(Motor.RunMode.PositionControl); // set run mode
        armMotor.setPositionCoefficient(0.05); //set PositionCoefficient
        double kP = armMotor.getPositionCoefficient();
        armMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE); //set lift to brake when power is set to 0
        armMotor.setPositionTolerance(20); //allowed maximum error
    }

    //Motor basic control
    public void setPower(double power) {
        armMotor.motor.setPower(power);
    } //set power of the lift motor
    public void setTargetPosition(int ticks) {
        armMotor.setTargetPosition(ticks);
    } //set target position of the lift motor

    //State Basic Func
    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }

    //State Control
    /**
     * find closest state based on position
     * @return closest state
     */
    public int findClosestState() {
        int[] differences = {};
        int currentPosition = armMotor.getCurrentPosition();
        for(int i = 0; i < positions.length; i++) {
            differences[i] = (int)Math.abs(currentPosition - positions[i]);
        }
        int closestState = 0;
        for(int i = 1; i < positions.length; i++) {
            if(differences[closestState] < differences[i]) {
                closestState = i;
            }
        }
        return closestState + 1; //+1 because arrays index at 0, while ours starts at index 0 = state 1

    }

    /**
     * changes state of arm
     * @param upDownAsInt -1 for down 1 for up
     */
    public void changeState (int upDownAsInt) {
        int state = getState(); //Assign state to a variable we can manipulate

        //If user is controlling, find the closest state. Otherwise, move state in specified direction.
        if(state == 0) {
            state = findClosestState();
        } else {
            state += upDownAsInt;
        }

        //Bounds check - can't manually change state to 0 or 6
        if(state == 0) {
            state = 1;
        } else if(state == 6) {
            state = 5;
        }

        if(state != 0) {
            armMotor.setTargetPosition(positions[state + 1]);
        }
        setState(state); //Pass state back
    }

    /**
     * approaches target position
     */
    public void approachTargetPosition() {
        if(!armMotor.atTargetPosition()) {
            armMotor.set(0.5);
        } else {
            armMotor.stopMotor();
        }

    }

    public void periodic() {

    }
}
