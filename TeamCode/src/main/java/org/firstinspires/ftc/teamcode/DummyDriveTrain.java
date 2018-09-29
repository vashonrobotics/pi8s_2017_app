package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;


class DummyDriveTrain implements DriveTrain {
    private Navigation navigation;

    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;

    public DummyDriveTrain(HardwareMap hardwareMap) {

    }

    public void init() {
        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
    }

    @Override
    public void driveTo(double x, double y) {

    }

    @Override
    public void turnAbsolute(double theta) {
        double thetaToTurn = theta - navigation.getTheta();
        turnRelative(thetaToTurn);
    }

    private void turnRelative(double thetaToTurn) {
    }

    @Override
    public void lookAt(int i, int i1) {
       double relativeX = x - navigation.getX();
       double relativeY = y - navigation.getY();
       double theta = Math.atan2(y, x);
       turnAbsolute(theta);
    }
}
