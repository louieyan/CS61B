public class NBody {

    /**
     * Read radius from given file
     * @param fileName
     * @return readius
     */
    public static double readRadius(String fileName) {
        In in = new In(fileName);
        in.readInt(); /* First element in object in is the number of bodies(N) */
        double readius = in.readDouble();
        return readius;
    }

    /**
     * Read body data from give file
     * @param fileName
     * @return an array of bodies 
     */
    public static Body[] readBodies(String fileName) {
        In in = new In(fileName);
        int N = in.readInt();
        in.readDouble(); /* Read readius*/
        Body[] bodies = new Body[N];
        for (int i = 0; i < N; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgString = in.readString();
            bodies[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, imgString);
        }
        return bodies;
    }

    public static void main(String[] args) {
        /** Collect all needed input */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String fileName = args[2];
        double radius = readRadius(fileName);
        Body[] bodies = readBodies(fileName);

        double tt = 0;
        int N = bodies.length;
        StdAudio.play("audio/2001.mid");
        while (tt <= T) {
            double[] xForces = new double[N];
            double[] yForces = new double[N];
            for (int i = 0; i < N; i++) {
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            for (int i = 0; i < N; i++) {
                bodies[i].update(dt, xForces[i], yForces[i]);
            }
            /** Draw the background */
            StdDraw.enableDoubleBuffering();
            /** Sets up the universe so it goes from
		    * -radius, -radius up to radius, radius */
            StdDraw.setScale(-radius, radius);
            /** Clears the drawing window. */
            StdDraw.clear();
            /** Draw picture centered at the origin. */
            StdDraw.picture(0, 0, "images/starfield.jpg");
            /** Shows the picture */
            for (Body b : bodies) {
                b.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            tt = tt + dt;
        }

        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (Body b : bodies) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                         b.xxPos, b.yyPos, b.xxVel, b.yyVel, b.mass, b.imgFileName);
        }
        
    }
}