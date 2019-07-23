public class Body {
    /** Define instance variable */
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double G = 6.67e-11; 
    /** The order of static and final is interchangeable
        see:https://stackoverflow.com/questions/11219556/difference-between-final-static-and-static-final
    */

    /** Define constructors */

    /** The first constructor */
    public Body(double xP, double yP, double xV,
                double yV, double m, String img){
                    xxPos = xP;
                    yyPos = yP;
                    xxVel = xV;
                    yyVel = yV;
                    mass = m;
                    imgFileName = img;
                }

    /** The second constructor */
    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    /**
     * Compute the distance between this and b.
     * @param b    a body
     * @return     distance
     */
    public double calcDistance(Body b) {
        double r;
        double xDiff = xxPos - b.xxPos;
        double yDiff = yyPos - b.yyPos;
        r = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
        return r;
    }

    /**
     * Compute force exerted by b
     * @param b    a body
     * @return     force exerted by b
     */

    public double calcForceExertedBy(Body b) {
        double r = calcDistance(b);
        double force = G * mass * b.mass / (r * r);
        return force;
    }


    /**
     * Compute x force exerted by b
     * @param b    a body 
     * @return     x force exerted by b
     */
    public double calcForceExertedByX(Body b) {
        double force = calcForceExertedBy(b);
        double dx = b.xxPos - xxPos;
        double r = calcDistance(b);
        double forceX = force * dx / r;
        return forceX;
    }

    /**
     *  Compute y force exerted by b
     * @param b    a body
     * @return     y force exerted by b
     */
    public double calcForceExertedByY(Body b) {
        double force = calcForceExertedBy(b);
        double dy = b.yyPos - yyPos;
        double r = calcDistance(b);
        double forceY = force * dy / r;
        return forceY;
    }

    /**
     * Compute x netforce exerted by all bodys in allBodys
     * @param allBodys    an array of bodys
     * @return            x netforce
     */
    public double calcNetForceExertedByX(Body[] allBodys) {
        double netForceX = 0; 
        for (Body b : allBodys) {
            if (this.equals(b)) {
                continue;
            }
            netForceX = netForceX + calcForceExertedByX(b);
        }
        return netForceX;
    }

    /**
     * Compute y netforce exerted by all bodys in allBodys
     * @param allBodys    an array of bodys
     * @return            y netforce
     */
    public double calcNetForceExertedByY(Body[] allBodys) {
        double netForceY = 0;
        for (Body b : allBodys) {
            if (this.equals(b)) {
                continue;
            }
            netForceY = netForceY + calcForceExertedByY(b);
        }
        return netForceY;
    }

    /**
     * Update velocity and position of current body.
     * @param dt    Time difference
     * @param fX    X-force 
     * @param fY    Y-force
     */
    public void update(double dt, double fX, double fY) {
        double aX = fX / mass;
        double aY = fY / mass;
        xxVel = xxVel + dt * aX;
        yyVel = yyVel + dt * aY;
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }

    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
    }
}