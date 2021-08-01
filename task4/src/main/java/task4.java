import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.apache.commons.math3.stat.descriptive.rank.Median;

public class task4
{
	public static void main( String[] args ) throws IOException
	{
		String fileName = args[0];
		List <String> params  = Files.readAllLines(Paths.get(fileName));
		double[] array = new double[params.size()];
		for(int i = 0; i < array.length; i++)
			array[i] = Double.parseDouble(params.get(i));

		Median median = new Median();
		int mdn = (int)median.evaluate(array);
		int result = 0;
		for(int i = 0; i < array.length; i++){
			result += Math.abs((int)array[i] - mdn);
		}
		System.out.println(result);
	}
}
