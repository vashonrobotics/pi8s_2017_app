package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.LiftAndServos;

/**
 * Created by Tristan on 1/14/2017.
 */
@TeleOp(name = "Just Drive & Servo", group = "Test")
public class JustDrive extends OpMode {

    MecanumDrive drive;
    LiftAndServos LAS;

    public void init()
    {
        drive = new MecanumDrive(hardwareMap);
        LAS = new LiftAndServos(hardwareMap);
    }

    public void loop()
    {
        drive.move(gamepad1.left_stick_x, -gamepad1.left_stick_y, -gamepad1.right_stick_x);
        if(gamepad1.left_bumper)
        {
            drive.move(gamepad1.left_stick_x/5, -gamepad1.left_stick_y/5, -gamepad1.right_stick_x/5);
        }

        if(gamepad2.dpad_left) {
            drive.move(90, 0.35, 0);
        }
        else if(gamepad2.dpad_right) {
            drive.move(270, 0.35, 0);
        }

        if(gamepad2.a)
        {
            LAS.moveServos(-1);
        }
        else if(gamepad2.b)
        {
            LAS.moveServos(1);
        }

        LAS.lift((gamepad2.left_trigger-gamepad2.right_trigger)/2);
    }
}
