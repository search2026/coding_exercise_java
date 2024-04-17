package coding_exercise;


import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class coding_exercise_1 {

    public class Robot {
        public int faceDirection; //1,2,3,4 n e s w
        public int x;
        public int y;

        Robot(int faceDirection, int x, int y) {
            this.faceDirection = faceDirection;
            this.x = x;
            this.y = y;
        }
    }

    public class Robotgame {
        Robot robot;

        // turn the face to left/right/
        private void rollover(String direction) {
            switch (direction) {
                case "left":
                    robot.faceDirection -= 1;
                    break;
                case "right":
                    robot.faceDirection += 1;
                    break;
                default:
                    return;
            }

            if (robot.faceDirection <= 0) {
                robot.faceDirection = 4;
            }

            if (robot.faceDirection > 4) {
                robot.faceDirection = 1;
            }
        }

        // move forward one step
        private void move() {
            switch (robot.faceDirection) {
                case 1:
                    robot.y += 1;
                    return;
                case 2:
                    robot.x += 1;
                    break;
                case 3:
                    robot.y -= 1;
                    break;
                case 4:
                    robot.x -= 1;
                    break;
                default:
                    return;
            }
        }

        public void command(String input) {
//            String[] tokens = input.split(input);
            // r, l, m
            switch (input) {
                case "r":
                    rollover("right");
                    break;
                case "l":
                    rollover("left");
                    break;
                case "m":
                    move();
                    break;
                default:
                    return;
            }
        }

        public String toString() {
            return "face direction " + getDirection(robot.faceDirection) + ". " + "robot is at (" + robot.x + ", " + robot.y + ")";
        }

        Robotgame(Robot robot) {
            this.robot = new Robot(robot.faceDirection, robot.x, robot.y);
        }

        // translate direction from integer into English letters
        private String getDirection(int direction) {
            switch (direction) {
                case 1:
                    return "North";
                case 2:
                    return "East";
                case 3:
                    return "South";
                case 4:
                    return "West";
                default:
                    return "Unknown";
            }
        }
    }

    public void main(String[] args) {

        Robotgame game = new Robotgame(new Robot(1, 0, 0));
        Scanner keyboard = new Scanner(System.in);

        System.out.println("enter your command");
        String userInput = keyboard.next();
        game.command(userInput);

//        System.out.println("Hello Java");
    }

//    @Nested
//    class UnitTest {
//        @Test
//        public void test1() {
//            //coding_exercise_1.Solution sol = new coding_exercise_1().new Solution();
//            Robotgame game = new Robotgame(new Robot(1, 0, 0));
//            System.out.println(game);
//            System.out.println("--------");
//
//            Scanner keyboard = new Scanner(System.in);
//
//
//
//            for (int i = 0; i < 6; i++) {
//                game.command("l");
//                System.out.println(game);
//                game.command("m");
//                System.out.println(game);
//            }
//            System.out.println("--------");
//            for (int i = 0; i < 9; i++) {
//                game.command("r");
//                System.out.println(game);
//                game.command("m");
//                System.out.println(game);
//            }
//            System.out.println("--------");
//
//            assertEquals(1, 1);
//        }
//    }
}
