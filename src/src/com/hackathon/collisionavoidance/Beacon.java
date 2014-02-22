package com.hackathon.collisionavoidance;

public class Beacon {

    double[] Lats;
    double[] Longs;
    double[] Velos;
    double[] Times;
    double ratio;
    double Sum_X, Sum_Y, Sum_sqx, Sum_pr, Mean_X, Mean_Y, Slope, Y_intercept = 0;

public static void main(String[] args){
    double[] x = {8, 2, 11, 6, 5, 4, 12, 9, 6, 1};
    double[] y = {3, 10, 3, 6, 8, 12, 1, 4, 9, 14};
    Beacon bacon = new Beacon();
    bacon.Calc_Best_Fit(x, y);
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

void init_Lat_Long(){
	double[] temp = {0, 0, 0, 0, 0};
    this.Lats = temp;
	this.Longs = temp;
    }

void Calc_Best_Fit(double[] one, double[] two){
    this.Lats = one;
    this.Longs = two;
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

double dist_between_this_and_that(Beacon bacon, Beacon other){
    bacon.set_Ratio();
    return ratio * Math.sqrt(((bacon.Lats[0]-other.Lats[0])*(bacon.Lats[0]-other.Lats[0]))+((bacon.Longs[0]-other.Longs[0])*(bacon.Longs[0]-other.Longs[0])));
    }

void set_Ratio(){
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
