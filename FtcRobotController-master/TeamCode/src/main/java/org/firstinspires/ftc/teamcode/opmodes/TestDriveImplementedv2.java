package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.command.CommandOpMode;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.commands.ServoChangeByPosConstantCommand;
import org.firstinspires.ftc.teamcode.commands.ArmOperateCommand;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TelemetryUpdateSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.GrabberSubsystem;

import org.firstinspires.ftc.teamcode.commands.MecanumDeclareCommand;
import org.firstinspires.ftc.teamcode.commands.MecanumDriveCommand;
import org.firstinspires.ftc.teamcode.commands.ArmSetPowerCommand;

public class TestDriveImplementedv2 extends CommandOpMode {
    GamepadEx driver, operator;
    MecanumDriveSubsystem mecanumDriveSubsystem;
    ArmSubsystem armSubsystem;
    TelemetryUpdateSubsystem telemetryUpdateSubsystem;
    GrabberSubsystem grabberSubsystem;

    @Override
    public void initialize() {
        driver = new GamepadEx(gamepad1);
        operator = new GamepadEx(gamepad2);
        mecanumDriveSubsystem = new MecanumDriveSubsystem(hardwareMap, telemetry);
        armSubsystem = new ArmSubsystem(hardwareMap, telemetry);
        telemetryUpdateSubsystem = new TelemetryUpdateSubsystem(telemetry);
        grabberSubsystem = new GrabberSubsystem(hardwareMap, telemetry);

        //mecanumDriveCommand is used to run in background
        //mecanumDeclareCommand is used when command are declared explicitly

        /*
        //D1 Joystick comamnds for robot centric
        MecanumDriveCommand driveMecanumCommand = new MecanumDriveCommand(
                mecanumDriveSubsystem,
                () -> -driver.getLeftY(),
                () -> driver.getRightX(),
                () -> driver.getLeftX(),
                telemetry
        );

        //D1 DPAD cardinal directions for drive
        driver.getGamepadButton(GamepadKeys.Button.DPAD_LEFT)
                .whenActive(new MecanumDeclareCommand(mecanumDriveSubsystem, 0.0, 0.0, -1.0))
                .whenInactive(driveMecanumCommand);

        driver.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT)
                .whenActive(new MecanumDeclareCommand(mecanumDriveSubsystem, 0.0, 0.0, 1.0))
                .whenInactive(driveMecanumCommand);

        driver.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whenActive(new MecanumDeclareCommand(mecanumDriveSubsystem, 1.0, 0.0, 0.0))
                .whenInactive(driveMecanumCommand);

        driver.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenActive(new MecanumDeclareCommand(mecanumDriveSubsystem, -1.0, 0.0, -1.0))
                .whenInactive(driveMecanumCommand);

        //D2 DPAD Arm control (basic)
        operator.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whenActive(new ArmSetPowerCommand(armSubsystem, 1.0))
                .whenInactive(new ArmSetPowerCommand(armSubsystem, 0));

        operator.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenActive(new ArmSetPowerCommand(armSubsystem, -1.0))
                .whenInactive(new ArmSetPowerCommand(armSubsystem, 0));

        register(telemetryUpdateSubsystem);
        CommandScheduler.getInstance().run();
         */

        //D1 Joystick comamnds for robot centric
        MecanumDriveCommand driveMecanumCommand = new MecanumDriveCommand(
                mecanumDriveSubsystem,
                () -> -driver.getLeftY(),
                () -> driver.getRightX(),
                () -> driver.getLeftX(),
                telemetry
        );
        mecanumDriveSubsystem.setDefaultCommand(driveMecanumCommand); //When subsystem is not being used by DPAD, use joystick

        //D1 DPAD cardinal directions for drive
        driver.getGamepadButton(GamepadKeys.Button.DPAD_LEFT)
                .whenActive(new MecanumDeclareCommand(mecanumDriveSubsystem, 0.0, 0.0, -1.0))
                .whenInactive(driveMecanumCommand);

        driver.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT)
                .whenActive(new MecanumDeclareCommand(mecanumDriveSubsystem, 0.0, 0.0, 1.0))
                .whenInactive(driveMecanumCommand);

        driver.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whenActive(new MecanumDeclareCommand(mecanumDriveSubsystem, 1.0, 0.0, 0.0))
                .whenInactive(driveMecanumCommand);

        driver.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenActive(new MecanumDeclareCommand(mecanumDriveSubsystem, -1.0, 0.0, -1.0))
                .whenInactive(driveMecanumCommand);


        //D2 DPAD Arm control (basic)
        //Try set++ or -- aim position?
        operator.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .and(driver.getGamepadButton(GamepadKeys.Button.B)).negate()
                .whenActive(new ArmSetPowerCommand(armSubsystem, 0.3))
                .whenInactive(new ArmSetPowerCommand(armSubsystem, 0));

        operator.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .and(driver.getGamepadButton(GamepadKeys.Button.B)).negate()
                .whenActive(new ArmSetPowerCommand(armSubsystem, -0.3))
                .whenInactive(new ArmSetPowerCommand(armSubsystem, 0));

        operator.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .and(driver.getGamepadButton(GamepadKeys.Button.B))
                .whenActive(new ArmSetPowerCommand(armSubsystem, 1.0))
                .whenInactive(new ArmSetPowerCommand(armSubsystem, 0));

        operator.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .and(driver.getGamepadButton(GamepadKeys.Button.B))
                .whenActive(new ArmSetPowerCommand(armSubsystem, -1.0))
                .whenInactive(new ArmSetPowerCommand(armSubsystem, 0));


        /*
        //D2 State Control Arm Control (advanced, WIP)
        ArmOperateCommand armOperateCommand = new ArmOperateCommand(armSubsystem, telemetry);
        //armSubsystem.setDefaultCommand(armOperateCommand);

        operator.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whenHeld(new ArmSetPowerCommand(armSubsystem, 0.5))
                .whenReleased(armOperateCommand);

        operator.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenHeld(new ArmSetPowerCommand(armSubsystem, -0.5))
                .whenReleased(armOperateCommand);

        operator.getGamepadButton(GamepadKeys.Button.DPAD_LEFT)
                .whenRele;
*/

        //D2 Servo Control (basic)
        operator.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whenHeld(new ServoChangeByPosConstantCommand(grabberSubsystem, -0.01, telemetry));

        operator.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whenHeld(new ServoChangeByPosConstantCommand(grabberSubsystem, 0.01, telemetry));

        operator.getGamepadButton(GamepadKeys.Button.X)
                .whenPressed(new InstantCommand(grabberSubsystem::closeServo));

        operator.getGamepadButton(GamepadKeys.Button.Y)
                .whenPressed(new InstantCommand(grabberSubsystem::openServo));

        //Servo Control
        schedule(driveMecanumCommand);
        //schedule(armOperateCommand)
        //register(telemetryUpdateSubsystem);
        CommandScheduler.getInstance().run();


    }
}
