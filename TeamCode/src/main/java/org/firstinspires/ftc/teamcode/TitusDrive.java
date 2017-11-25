package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Tristan on 1/14/2017.
 */
@TeleOp(name = "TitusOp for Tests", group = "Test")
public class TitusDrive extends OpMode {

    DcMotor leftWheel;
    DcMotor rightWheel;

    DcMotor leftLift;
    DcMotor rightLift;

    public void init()
    {
        leftWheel = hardwareMap.dcMotor.get("leftWheel");
        rightWheel = hardwareMap.dcMotor.get("rightWheel");

        leftLift = hardwareMap.dcMotor.get("leftLift");
        rightLift = hardwareMap.dcMotor.get("rightLift");

        leftWheel.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void loop()
    {
        leftWheel.setPower(-gamepad1.left_stick_y);
        rightWheel.setPower(-gamepad1.right_stick_y);

        leftLift.setPower(gamepad1.right_trigger-gamepad1.left_trigger);
        rightLift.setPower(gamepad1.right_trigger-gamepad1.left_trigger);

    }
}
