
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;



@TeleOp(name="Teleop", group="ibot")
//@Disabled
public class teleoppract extends LinearOpMode {

    /* Declare OpMode members. */
    hardwareIbot robot = new hardwareIbot(DcMotor.RunMode.RUN_WITHOUT_ENCODER);




    @Override
    public void runOpMode() {
        double left;
        double right;

        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.initialize(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
        telemetry.update();


        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)

            right = gamepad1.right_stick_y;
            left = gamepad1.left_stick_y;
            robot.leftm.setPower(left);
            robot.rightm.setPower(right);




            if(gamepad2.a){

                robot.arml.setPosition(.35);
                robot.armr.setPosition(.7);
            }
            if(gamepad2.x){
                robot.arml.setPosition(.75);
                robot.armr.setPosition(.4);
            }
            if(gamepad2.b){
                robot.arml.setPosition(.55);
                robot.armr.setPosition(.5);
            }
            if(gamepad2.dpad_down)
            {

            }
            if (gamepad1.y) {
                robot.lifting.setPower(.45);
            }
            if(gamepad1.a){
                robot.lifting.setPower(-.017);
            }
            if(gamepad1.x){
                robot.lifting.setPower(.1);
            }
            if(gamepad1.b){
                robot.lifting.setPower(0);
            }




        }
    }
}













