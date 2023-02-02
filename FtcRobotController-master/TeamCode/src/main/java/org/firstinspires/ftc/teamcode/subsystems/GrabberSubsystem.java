package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class GrabberSubsystem extends SubsystemBase {
    private ServoEx grabberServo;
    private Telemetry telemetry;

    public GrabberSubsystem(HardwareMap hardwareMap, Telemetry telemetry){
        this.telemetry = telemetry;
        grabberServo = new SimpleServo(hardwareMap, "grabberServo", 0, 180);
        openServo();
    }

     public void closeServo() {
        setServo(0.0);
     }

     public void openServo() {
        setServo(1.0);
     }

     public void setServo(double position) {
        grabberServo.setPosition(position);
     }

     public void posChangeServo(double position) {
        grabberServo.rotateBy(position);
     }

}
