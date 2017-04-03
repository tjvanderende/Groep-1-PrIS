package model;

import java.util.ArrayList;

public abstract class PrISService {
	abstract Klas loadKlas(String klasCode, String klasNaam);
	abstract ArrayList<Les> loadLessen();

	/**
	 * In een eventuele implementatie zou je de objecten opslaan
	 */
	abstract void saveStudent(Student student);
}
