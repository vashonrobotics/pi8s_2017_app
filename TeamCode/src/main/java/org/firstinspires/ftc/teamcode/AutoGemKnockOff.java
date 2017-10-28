package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;
/**
 * Created by Tristan on 9/26/2016.
 */
public class AutoGemKnockOff {
    DcMotor motorRF;
    DcMotor motorLF;
    DcMotor motorRB;
    DcMotor motorLB;

    Servo colourServo;

    ColorSensor colourSensor;

    boolean finished = false;

    public AutoGemKnockOff(HardwareMap hardwareMap) {
        motorRF = hardwareMap.dcMotor.get("motorRF"); //sets DcMotors to type of motor
        motorLF = hardwareMap.dcMotor.get("motorLF");
        motorRB = hardwareMap.dcMotor.get("motorRB");
        motorLB = hardwareMap.dcMotor.get("motorLB");



        motorRB.setDirection(DcMotor.Direction.REVERSE);
        motorRF.setDirection(DcMotor.Direction.REVERSE);
    }

    public boolean finished()
    {
        return finished;
    }

    public boolean lookForBall()
    {
        colourServo.setPosition(1);

        return false;
    }
}
