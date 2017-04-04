package model;

import java.util.ArrayList;

public interface PrISService {
	public Klas loadKlas(String klasCode, String klasNaam);
	public ArrayList<Les> loadLessen();

	/**
	 * In een eventuele implementatie zou je de objecten opslaan
	 */
	public void saveStudentPresentie(StudentPresentie presentie);
}
