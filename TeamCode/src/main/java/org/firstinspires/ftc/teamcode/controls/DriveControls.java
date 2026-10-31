
package org.firstinspires.ftc.teamcode.controls;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class DriveControls {

    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;

    private static final double DEADZONE = 0.10;
    private static final double TURN_SENSITIVITY = 0.70;
    private static final double SLOW_SPEED = 0.40;

    public DriveControls(
            DcMotor frontLeft,
            DcMotor frontRight,
            DcMotor backLeft,
            DcMotor backRight) {

        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.backLeft = backLeft;
        this.backRight = backRight;
    }

    public void drive(Gamepad gamepad) {

        // Read joystick inputs
        double drive = -gamepad.left_stick_y;
        double strafe = gamepad.left_stick_x;
        double turn = gamepad.right_stick_x;

        // Apply deadzone
        if (Math.abs(drive) < DEADZONE) {
            drive = 0;
        }

        if (Math.abs(strafe) < DEADZONE) {
            strafe = 0;
        }

        if (Math.abs(turn) < DEADZONE) {
            turn = 0;
        }

        // Reduce turning sensitivity
        turn *= TURN_SENSITIVITY;

        // Mecanum wheel calculations
        double frontLeftPower = drive + strafe + turn;
        double frontRightPower = drive - strafe - turn;
        double backLeftPower = drive - strafe + turn;
        double backRightPower = drive + strafe - turn;

        // Normalize motor powers
        double maxPower = Math.max(1.0,
                Math.max(
                        Math.max(Math.abs(frontLeftPower),
                                Math.abs(frontRightPower)),
                        Math.max(Math.abs(backLeftPower),
                                Math.abs(backRightPower))
                ));

        frontLeftPower /= maxPower;
        frontRightPower /= maxPower;
        backLeftPower /= maxPower;
        backRightPower /= maxPower;

        // Slow mode
        if (gamepad.left_bumper) {
            frontLeftPower *= SLOW_SPEED;
            frontRightPower *= SLOW_SPEED;
            backLeftPower *= SLOW_SPEED;
            backRightPower *= SLOW_SPEED;
        }

        // Apply motor power
        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);
    }

    public void stop() {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
}
