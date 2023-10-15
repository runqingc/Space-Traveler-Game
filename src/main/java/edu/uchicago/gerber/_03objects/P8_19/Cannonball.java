package edu.uchicago.gerber._03objects.P8_19;

public class Cannonball {

    double xpos;

    double ypos;

    double xvelocity;

    double yvelocity;

    private static final double GRAVITY = -9.81;

    public Cannonball(double xpos) {
        this.xpos = xpos;
        this.ypos = 0;
    }

    public double getXpos() {
        return xpos;
    }

    public double getYpos() {
        return ypos;
    }

    // move method
    public void move(double sec) {
        // Compute the new position
        xpos += xvelocity * sec;                 // Update x-position
        ypos += yvelocity * sec;                 // Update y-position

        // Update y-velocity (taking into account gravitational acceleration)
        yvelocity += GRAVITY * sec;              // Update y-velocity
    }

    // shoot method
    public void shoot(double alpha, double v) {
        // Convert angle from degrees to radians
        double alphaRad = Math.toRadians(alpha);

        // Compute x and y velocities using trigonometry
        xvelocity = v * Math.cos(alphaRad);
        yvelocity = v * Math.sin(alphaRad);

        // Move the cannonball until it hits the ground
        while (ypos >= 0) {
            move(0.1);  // 0.1 seconds interval
            System.out.printf("x: %.2f, y: %.2f%n", getXpos(), getYpos()); // Display the position after each move
        }
    }


}
