package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Tristan on 9/26/2016.
 */
public class LiftAndServos
{
    Servo servoLeft;
    Servo servoRight;
    DcMotor liftMotor;
    double off = 1;
    public LiftAndServos(HardwareMap hardwareMap)
    {
        liftMotor = hardwareMap.dcMotor.get("liftMotor"); //sets DcMotors to type of motor

        servoLeft = hardwareMap.servo.get("servoL");
        servoRight = hardwareMap.servo.get("servoR");

    }

    public void lift(double power)
    {
        liftMotor.setPower(power);
    }
    public void moveServos(double posistion)
    {
        servoLeft.setPosition(posistion);
        servoRight.setPosition(-posistion);
    }

}
