public class Body {
    /** Define instance variable */
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

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

    public double calcDistance(Body b) {
        double r;
        double xDiff = this.xxPos - b.xxPos;
        double yDiff = this.yyPos - b.yyPos;
        r = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
        return r;
    }


}