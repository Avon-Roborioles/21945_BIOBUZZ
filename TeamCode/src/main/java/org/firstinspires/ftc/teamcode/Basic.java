
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.controls.DriveControls;

@TeleOp(name = "Basic", group = "BioBuzz")

public class Basic extends LinearOpMode {


    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;

    @Override
    public void runOpMode() {


        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        // Set motor directions
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);

        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        // Set braking behavior
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Initialize driving controls
        DriveControls driveControls = new DriveControls(
                frontLeft,
                frontRight,
                backLeft,
                backRight
        );


        telemetry.addData("Robot", "BioBuzz");
        telemetry.addData("Status", "Initialized");
        telemetry.addData("Controls", "Gamepad 2");
        telemetry.update();

        waitForStart();

        if (isStopRequested()) {
            return;
        }


        while (opModeIsActive()) {

            driveControls.drive(gamepad2);


           // telemetry.addData("Forward/Backward",
                   // "%.2f", -gamepad2.left_stick_y);

         // telemetry.addData("Strafing",
                    //"%.2f", gamepad2.left_stick_x);

           // telemetry.addData("Turning",
                    //"%.2f", gamepad2.right_stick_x);

            //telemetry.addData("Slow Mode",
                   // gamepad2.left_bumper);

           // telemetry.addData("Status", "Running");
           // telemetry.update();
        }


        driveControls.stop();

        telemetry.addData("Status", "Stopped");
        telemetry.update();
    }
}
