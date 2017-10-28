package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.vuforia.HINT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Axis;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by Tristan on 10/7/2017.
 */
@Autonomous(name="RedGem Find :D", group ="Test")
public class VuforiaOp extends LinearOpMode {

    public void runOpMode() throws InterruptedException {
        VuforiaLocalizer.Parameters param = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
        param.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        param.vuforiaLicenseKey = "AcfullL/////AAAAGSOSZA30iEJ6lhWTCbftAasmcUshL/HUebUI6EDhrrnupgA15NCxOkPJDBMD46rE8MlgnGyDIEy3MAYNsykv0eP8Yf/tjV080ZMEAiNBpr2APCddbWLQfBtbN7N1gvCg7ytNJ59sDca1P8g8nsByYb7SXzuTq11DMQfDih3Fz+BR3qW+HBM/4vpa7F4PGkXmbdCF8qFFeD9tkZwjvzgPOiVM0psczS/BMPKwVbsdr3bmVsDYf/0lCfqE1Rzupdtwx9MvtVWxyPvl9EmNExdyLC+NRWW+zTg2bYGVtA/KnWvEXe6m/dLcsUlpdcavc40vAssFruJ+qv2TidEjtLGnKNwkSmUF22GH2Ngk1RiUY1wd";
        param.cameraMonitorFeedback = VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES;

        VuforiaLocalizer vuforia = ClassFactory.createVuforiaLocalizer(param);

        VuforiaTrackables redBall = vuforia.loadTrackablesFromAsset("TristanVuforia_OT");

        VuforiaTrackable redGemTrack = redBall.get(0);
        redGemTrack.setName("redGem");

        telemetry.addData(">", "Press Play to start");
        telemetry.update();
        waitForStart();

        redBall.activate();

        while (opModeIsActive()) {

            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(redGemTrack);
            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {

                /* Found an instance of the template. In the actual game, you will probably
                 * loop until this condition occurs, then move on to act accordingly depending
                 * on which VuMark was visible. */
                telemetry.addData("VuMark", "%s visible", vuMark);

                /* For fun, we also exhibit the navigational pose. In the Relic Recovery game,
                 * it is perhaps unlikely that you will actually need to act on this pose information, but
                 * we illustrate it nevertheless, for completeness. */
                OpenGLMatrix pose = ((VuforiaTrackableDefaultListener)redGemTrack.getListener()).getPose();
                telemetry.addData("pose", format(pose));

                /* We further illustrate how to decompose the pose into useful rotational and
                 * translational components */
                if (pose != null) {
                    VectorF trans = pose.getTranslation();
                    Orientation rot = Orientation.getOrientation(pose, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);

                    // Extract the X, Y, and Z components of the offset of the target relative to the robot
                    double tX = trans.get(0);
                    double tY = trans.get(1);
                    double tZ = trans.get(2);

                    // Extract the rotational components of the target relative to the robot
                    double rX = rot.firstAngle;
                    double rY = rot.secondAngle;
                    double rZ = rot.thirdAngle;

                }
            }
            else {
                telemetry.addData("VuMark", "not visible");
            }

            telemetry.update();
        }

    }
    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }
}
