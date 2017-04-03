package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Een test Service geschreven die de data in het PrIS laadt
 * De data komt op dit moment vanuit Array files.
 * Het PoC moet werken met de CSV files.
 * Bij een eventuele implementatie kan de data uit een databse worden ingeladen.
 * @author tjvan
 *
 */
public class PrISMockService implements PrISService {
	private String CSVfileLocation = "CSV/";
	@Override
	public ArrayList<Les> loadLessen() {
		//2017-02-06,10:00,12:30,TICT-V1OODC-15_2016,peter.schuler@hu.nl,DL200-3.91 (52),TICT-SIE-V1D
		//2017-02-06,11:30,14:00,TICT-V1OODC-15_2016,bart.vaneijkelenburg@hu.nl,DL200-6.84 (32),TICT-SIE-V1B
		ArrayList<Les> rooster = new ArrayList<Les>();
		Scanner scanner;
		try {
			File file = new File(CSVfileLocation+"/rooster.csv");
			scanner = new Scanner(file);
			while(scanner.hasNextLine()){
				String line = scanner.nextLine();
				String[] values = line.split(",");
				String[] getKlasNaam = values[6].split("-");
				Docent docent = new Docent("test", "test", "test", values[4]);
				rooster.add(new Les(values[0], values[1], values[2], values[3], this.loadKlas(values[6], getKlasNaam[2]), docent));
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rooster;
	}

	@Override
	public Klas loadKlas(String klasCode, String klasNaam) {
		Klas klas = new Klas(klasCode, klasNaam);

		File file = new File(CSVfileLocation+"/"+klasNaam+".csv");
		try {
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()){
				String line = scanner.nextLine();
				String[] values = line.split(",");
				klas.addStudent(new Student(Integer.parseInt(values[0]), values[1], values[1]+"123", values[1], values[2], klasNaam, "", "jos.reenen@hu.nl"));
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		return klas;
	}

	@Override
	public void saveStudentPresentie(StudentPresentie presentie) {

	}

}
