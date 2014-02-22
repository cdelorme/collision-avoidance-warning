
public class beacon {

    Lats = new double[5];
    Longs = new double[5];
    double Sum_X, Sum_Y, Sum_sqx, Sum_pr, Mean_X, Mean_Y, Slope, Y_intercept = 0;

void init_Lat_Long(){
    Lats = {0, 0, 0, 0, 0};
    Longs = {0, 0, 0, 0, 0};
    }

void Calc_Best_Fit(double[] Lats, double[] Longs){
    for(int i=0; i<Lats.length; i++){
        Sum_X += Lats[i];
        Sum_Y += Longs[i];
        Sum_sqx += Lats[i]*Lats[i];
        Sum_pr += Lats[i]*Longs[i];
    }
    Mean_X = Sum_Y/Longs.length;
    Mean_Y = Sum_X/Lats.length;
    Slope += Sum_pr-((Sum_X*Sum_Y)/Lats.length);
    Slope /= Sum_sqx-(Sum_sqx/Longs.length);
    Y_intercept = Mean_Y-Slope*Mean_X;
    }

bool Compare_Collision(beacon other){
        if(Slope==other.Slope&&Y_intercept==other.Y_intercept){
            return true;
        }
        if(Slope == (-1/other.Slope){
            return true;
        }
    }
}
