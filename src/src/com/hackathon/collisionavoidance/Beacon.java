package com.hackathon.collisionavoidance;


/* The Beacon class will serve to house the information of the last five
 * blips of latitude, longitude, velocity, and time.
 * Using that information, it will calculate the trajectory of the 
 * vehicle, cyclist, or pedestrian currently using the app using a 
 * line of best fit.
 *
 */
public class Beacon {

    double[] Lats;
    double[] Longs; //These arrays will be 5 slots long to store
    double[] Velos; // the information of the last five blips
    double[] Times;
    double ratio;   //These are calculated in the functions below using the data above.
    double Sum_X, Sum_Y, Sum_sqx, Sum_pr, Mean_X, Mean_Y, Slope, Y_intercept = 0;

    /*
     * Used for testing the functions currently written, the main method
     * calculates a line of best fit off of the Beacon data and prints 
     * the results
     */
public static void main(String[] args){
    double[] x = {8, 2, 11, 6, 5, 4, 12, 9, 6, 1};
    double[] y = {3, 10, 3, 6, 8, 12, 1, 4, 9, 14};
    Beacon bacon = new Beacon();
    bacon.Set_Lat_Long(x, y);
    bacon.Calc_Best_Fit();
    System.out.println("Beacon Info");
    String lat_long = "Lat & Long: ";
    for(int i=0; i<bacon.Lats.length; i++){
        lat_long += bacon.Lats[i] + ", " + bacon.Longs[i] + "\n";
    }
    System.out.println(lat_long);
    System.out.println("Sum_X: " + bacon.Sum_X + " Sum_Y: " + bacon.Sum_Y + " Sum_pr: " + bacon.Sum_pr + " Sum_sqx: " + bacon.Sum_sqx);
    System.out.println("Slope: " + bacon.Slope);
    System.out.println("Y_intercept: " + bacon.Y_intercept);
}

    // Just a method to initialize empty arrays
void init_Lat_Long(){
	double[] temp = {0, 0, 0, 0, 0};
    this.Lats = temp;
	this.Longs = temp;
    this.Velos = temp;
    this.Times = temp;
    }

    /* A setting function to enter data points into the arrays
     * Parameters:
     *      double[] one: what will become the latitude array
     *      double[] two: what will become the longitude array
     */
void Set_Lat_Long(double[] one, double[] two){
    this.Lats = one;
    this.Longs = two;
}

    /* A function to calculate the line of best fit using the data
     * already stored in the latitude & longitude arrays
     */
void Calc_Best_Fit(){
    for(int i=0; i<this.Lats.length; i++){
    	this.Sum_X += this.Lats[i];
    	this.Sum_Y += this.Longs[i];
    	this.Sum_sqx += this.Lats[i]*this.Lats[i];
    	this.Sum_pr += this.Lats[i]*this.Longs[i];
    }
    this.Mean_X = this.Sum_Y/this.Longs.length;
    this.Mean_Y = this.Sum_X/this.Lats.length;
    this.Slope = this.Sum_pr-((this.Sum_X*this.Sum_Y)/this.Lats.length);
    this.Slope = this.Slope/(this.Sum_sqx-((this.Sum_X*this.Sum_X)/this.Longs.length));
    this.Y_intercept = this.Mean_Y-this.Slope*this.Mean_X;
    }

    /*
     * This function, meant to be called in a loop, returns the distance
     * from the current line of best fit/vector of the vehicle to a point.
     *
     * Can be used for a possible point of intersection or with GPS 
     * beacon points to find an average distance from the line
     *
     * Parameters:
     *      Beacon bacon: will probably be replaced by "this," but is 
     *      currently a beacon to be compared to a point
     *
     *      double Lat: the x value of the point to be distanced
     *      double Long: the y value of the point to be distanced
     */ 
double dist_between_pt_line(Beacon bacon, double Lat, double Long){
    double inter_diff = Long - ((-1/bacon.Slope)*Lat);
    double coeff_diff = 0;
    double X;
    double Y;
    inter_diff = bacon.Y_intercept-inter_diff;
    coeff_diff = (-1/bacon.Slope)-bacon.Slope;
    X = inter_diff/coeff_diff;
    Y = bacon.Slope*X;
    Y += bacon.Y_intercept;
    return java.lang.Math.sqrt(((bacon.Lats[0]-X)*(bacon.Lats[0]-X))+((bacon.Longs[0]-Y)*(bacon.Longs[0]-Y)));
}

    /*
     * Using the Calc_Ratio function below, this function returns the
     * value for the distance between two beacons
     *
     * Parameters:
     *      beacon bacon: will probably be replaced by "this," but is 
     *      currently a beacon to be compared to another one
     *
     *      Beacon other: the other Beacon to which the first one 
     *      will be compared.
     */
double dist_between_this_and_that(Beacon bacon, Beacon other){
    bacon.Calc_Ratio();
    return ratio * Math.sqrt(((bacon.Lats[0]-other.Lats[0])*(bacon.Lats[0]-other.Lats[0]))+((bacon.Longs[0]-other.Longs[0])*(bacon.Longs[0]-other.Longs[0])));
    }

    /*
     * Here we can the calculate the ratio variable. It is the average
     * distance from the velocities divided by the distance traversed
     * accross the actual latitude and longitude values
     */
void Calc_Ratio(){
    double Mean_velo = 0;
    double Tot_time;
    for(int i=0;i<Velos.length; i++){
        Mean_velo += Velos[i];
        }
    Mean_velo = Mean_velo/Velos.length;
    Tot_time = Times[0]-Times[-1];
    ratio = Mean_velo*Tot_time;
    ratio = ratio/java.lang.Math.sqrt(((this.Lats[0]-this.Lats[-1])*(this.Lats[0]-this.Lats[-1]))+((this.Longs[0]-this.Longs[-1])*(this.Longs[0]-this.Longs[-1])));
    }

    /*
     * An unfinished attempt to determine which type of collision could
     * occur. Slopes and other aspects of geometric lines would be 
     * compared to real-life road intersections and driving situations.
     */
boolean Compare_Collision(Beacon other){
        if(this.Slope==other.Slope&&this.Y_intercept==other.Y_intercept){
            return true;
        }
        if(this.Slope == (-1/other.Slope)){
            return true;
        }
	return false;
	}
}
