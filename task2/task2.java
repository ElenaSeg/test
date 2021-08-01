import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class task2 {
    public static void main(String[] args) throws IOException {
        String fileCircle = args[0];
        String filePoint = args[1];
        List<String> paramsCircle = Files.readAllLines(Paths.get(fileCircle));
        String strXY[] = paramsCircle.get(0).split(" "); 
        float circleX = Float.parseFloat(strXY[0]);
        float circleY = Float.parseFloat(strXY[1]);
        float r = Float.parseFloat(paramsCircle.get(1));
        List<String> points = Files.readAllLines(Paths.get(filePoint));
        for (String point: points){
            String pointXY[] = point.split(" ");
            float pointX = Float.parseFloat(pointXY[0]);
            float pointY = Float.parseFloat(pointXY[1]);
            System.out.println(checkPointPlace(circleX,
                            circleY, pointX, pointY, r));
        } 
    }

    static int checkPointPlace (float circleX, float circleY,
                                float pointX, float pointY, float r){
        float x = Math.abs(circleX - pointX);
        float y = Math.abs(circleY - pointY);
        float hypotenuseSquare = (x*x + y*y);
        if(hypotenuseSquare == r*r)
            return 0;
        else if (hypotenuseSquare < r*r)
            return 1;
        else 
            return 2;
    }
}
