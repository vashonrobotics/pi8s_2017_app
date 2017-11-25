package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.internal.android.dx.dex.file.StringDataItem;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.LiftAndServos;
import org.firstinspires.ftc.teamcode.RelicArm;

import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.ArrayList;

/**
 * Created by Tristan on 1/14/2017.
 */
@TeleOp(name = "Main TeleOp", group = "Test")
public class JustDrive extends OpMode {


    MecanumDrive drive;
    LiftAndServos LAS;
    RelicArm Jones;

    public void init()
    {
        drive = new MecanumDrive(hardwareMap);
        LAS = new LiftAndServos(hardwareMap);
        Jones = new RelicArm(hardwareMap);
    }

    public void loop()
    {

        if(gamepad1.left_bumper)
        {
            drive.move(gamepad1.left_stick_x/2.5, -gamepad1.left_stick_y/2.5, -gamepad1.right_stick_x/2.5);
        }
        else
        {
            drive.move(gamepad1.left_stick_x, -gamepad1.left_stick_y, -gamepad1.right_stick_x);
        }

        ArrayList<Integer> motorPos = drive.getMotorPosistions();
        if(motorPos.size() == 4){
            telemetry.addData(motorPos.get(0).toString(), "MotorRF Posistion");
            telemetry.addData(motorPos.get(1).toString(), "MotorRB Posistion");
            telemetry.addData(motorPos.get(2).toString(), "MotorLF Posistion");
            telemetry.addData(motorPos.get(3).toString(), "MotorLB Posistion");
        }

        if(gamepad2.a)//This should be blocked (by blocks)
        {
            LAS.servoPosistion(-0.04);
        }
        else if(gamepad2.b)
        {
            LAS.servoPosistion(0.04);
        }
        LAS.moveServos();

        LAS.lift((gamepad2.left_trigger-gamepad2.right_trigger));
        int liftPosistion = LAS.liftPos();

        Jones.lift((gamepad2.right_stick_y));

        if(gamepad2.x)
        {
            Jones.clawPosistion(0.03);//To grab your attention
        }
        if(gamepad2.y)
        {
            Jones.clawPosistion(-0.03);
        }
        Jones.clawPosistion(0.0);

        Jones.armPosistion(gamepad2.left_stick_y*0.01);//Careful, we're armed

        telemetry.update();
    }
}
