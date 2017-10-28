package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

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
    public MecanumDrive(HardwareMap hardwareMap)
    {
        motorRF = hardwareMap.dcMotor.get("motorRF"); //sets DcMotors to type of motor
        motorLF = hardwareMap.dcMotor.get("motorLF");
        motorRB = hardwareMap.dcMotor.get("motorRB");
        motorLB = hardwareMap.dcMotor.get("motorLB");

        motorRB.setDirection(DcMotor.Direction.REVERSE);
        motorRF.setDirection(DcMotor.Direction.REVERSE);
    }

    public void move(double x, double y, double turn) {

        double magnitude = Math.sqrt(y * y + x * x);
        double radians = Math.atan2(y, x) - (Math.PI / 4);

        wheels(radians, magnitude, turn);
    }

    public void mecMove(double degrees, double magnitude, double turn)
    {
        wheels(Math.toRadians(degrees), magnitude, turn);
    }

    private void wheels(double radians, double magnitude, double turn)
    {
        if (Math.abs(magnitude) + Math.abs(turn) > 1) {
            magnitude = magnitude / (Math.abs(magnitude) + Math.abs(turn));
            turn = Math.signum(turn) * (1 - Math.abs(magnitude));
        }

        motorLF.setPower(magnitude * Math.cos(radians) + turn);
        motorLB.setPower(magnitude * Math.sin(radians) + turn);
        motorRF.setPower(magnitude * Math.sin(radians) - turn);
        motorRB.setPower(magnitude * Math.cos(radians) - turn);
    }

    public void tankDrive(double powerL, double powerR)
    {
        motorLF.setPower(powerL);
        motorLB.setPower(powerL);
        motorRF.setPower(powerR);
        motorRB.setPower(powerR);
    }
}
