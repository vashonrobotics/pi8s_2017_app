package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.ArrayList;

/**
 * Created by Tristan on 9/26/2016.
 */
public class MecanumDrive
{
    DcMotor motorRF;            //R = Right, L = Left
    DcMotor motorLF;            //F = Front, B = Back
    DcMotor motorRB;            //Gamepad 1: right stick and left stick
    DcMotor motorLB;            //Gamepad 1: right stick and left stick
    double off = 1;

    ArrayList<DcMotor> motors = new ArrayList<DcMotor>();

    public MecanumDrive(HardwareMap hardwareMap)
    {
        motorRF = hardwareMap.dcMotor.get("motorRF"); //sets DcMotors to type of motor
        motorRB = hardwareMap.dcMotor.get("motorRB");
        motorLF = hardwareMap.dcMotor.get("motorLF");
        motorLB = hardwareMap.dcMotor.get("motorLB");

        motors.add(motorRF);
        motors.add(motorRB);
        motors.add(motorLF);
        motors.add(motorLB);

        motorRB.setDirection(DcMotor.Direction.REVERSE);
        motorRF.setDirection(DcMotor.Direction.REVERSE);

        for(int i = 0; motors.size() > i; i++)
        {
            motors.get(i).setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motors.get(i).setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void move(double x, double y, double turn) {

        double magnitude = Math.sqrt(y * y + x * x);
        double radians = Math.atan2(y, x) - (Math.PI / 4);

        wheels(radians, magnitude,  turn);
    }

    public void mecMove(double degrees, double magnitude, double turn)
    {
        wheels(Math.toRadians(degrees+45), magnitude, turn);
    }

    private void wheels(double radians, double magnitude, double turn)
    {
        if (Math.abs(magnitude) + Math.abs(turn) > 1) {
            magnitude = magnitude / (Math.abs(magnitude) + Math.abs(turn));
            turn = Math.signum(turn) * (1 - Math.abs(magnitude));
        }

        motorLF.setPower((magnitude * Math.cos(radians) + turn)*(1));
        motorLB.setPower((magnitude * Math.sin(radians) + turn)*(1));
        motorRF.setPower((magnitude * Math.sin(radians) - turn)*(1));
        motorRB.setPower((magnitude * Math.cos(radians) - turn)*(1));
    }

    public void setPosistion(int mlfPos, int mlbPos, int mrfPos, int mrbPos)
    {
        motorLF.setTargetPosition(mlfPos);
        motorLB.setTargetPosition(mlbPos);
        motorRF.setTargetPosition(mrfPos);
        motorRB.setTargetPosition(mrbPos);
    }

    public void tankDrive(double powerL, double powerR)
    {
        motorLF.setPower(powerL);
        motorLB.setPower(powerL);
        motorRF.setPower(powerR);
        motorRB.setPower(powerR);

    }

    public ArrayList<Integer> getMotorPosistions()
    {
        ArrayList<Integer> motorPosistions = new ArrayList();
        for(int i = 0; motors.size() > i; i++)
        {
            motorPosistions.add(motors.get(i).getCurrentPosition());
        }
        return motorPosistions;
    }

    public void setModePosistion()
    {
        for(int i = 0; motors.size() > i; i++)
        {
            motors.get(i).setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
    }
}
