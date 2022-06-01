import java.util.*;

public class SneakyKnights
{
	//power function because eustis doesn't like Math.pow()
	public static int Power(int base, int power)
	{
		int answer = 1;
		if (power == 0)
		{
			return 1;
		}

		while (power != 0)
		{
			answer *= base;
			--power;
		}

		return answer;
	}

	public static int getLinearCoordinate(int boardSize, int col, int row)
	{
		return boardSize * row + col;
	}

	public static boolean allTheKnightsAreSafe(ArrayList<String> coordinateStrings, int boardSize)
	{
		//initialize arrays needed and fill them with 0s
		int coordStringLength = coordinateStrings.size();

		HashSet<Integer> unsafe = new HashSet<Integer>();

		//split the coordinate string into 2 parts
		//1 for the letters and 1 for the numbers
		for (int i = 0; i < coordStringLength; i++)
		{
			String tempString = coordinateStrings.get(i);
			String coordinatesTemp[] = tempString.split("(?<=[a-z])(?=[0-9])");
			String columnTemp = coordinatesTemp[0];
			char columnTemp2[] = columnTemp.toCharArray();
			int colTempLength = columnTemp2.length;

			int conversionFinal = 0;
			int colTempLengthHelper = colTempLength;
			//convert the letters to 26 base number system
			//without a 0 value at the beginning
			for (int j = 0; j < colTempLength; j++)
			{
				int x = columnTemp2[j] - 96;
				int base = 26;

				int position = colTempLengthHelper - 1;
				int conversionTemp = (x * Power(base, position));
				conversionFinal += conversionTemp;
				colTempLengthHelper--;
			}

			int colCoord = conversionFinal - 1;
			int rowCoord = Integer.parseInt(coordinatesTemp[1]) - 1;
		
			unsafe.add(getLinearCoordinate(boardSize, colCoord, rowCoord));
			//tests for all 8 possible attack moves for each knight.
			//Does this by checking to see if the hashset contains
			//a knight in attack range based on hashing function.
			if (colCoord > 1 && rowCoord < boardSize - 1)
			{
				if (unsafe.contains(getLinearCoordinate(boardSize, colCoord - 2, rowCoord + 1)))
				{
					return false;
				}
			}
			if (colCoord > 0 && rowCoord < boardSize - 2)
			{
				if (unsafe.contains(getLinearCoordinate(boardSize, colCoord - 1, rowCoord + 2)))
				{
					return false;
				}
			}
			if (colCoord < boardSize - 1 && rowCoord < boardSize - 2)
			{
				if (unsafe.contains(getLinearCoordinate(boardSize, colCoord + 1, rowCoord + 2)))
				{
					return false;
				}
			}
			if (colCoord < boardSize - 2 && rowCoord < boardSize - 1)
			{
				if (unsafe.contains(getLinearCoordinate(boardSize, colCoord + 2, rowCoord + 1)))
				{
					return false;
				}
			}
			if (colCoord < boardSize - 2 && rowCoord > 0)
			{
				if (unsafe.contains(getLinearCoordinate(boardSize, colCoord + 2, rowCoord - 1)))
				{
					return false;
				}
			}
			if (colCoord < boardSize - 1 && rowCoord > 1)
			{
				if (unsafe.contains(getLinearCoordinate(boardSize, colCoord + 1, rowCoord - 2)))
				{
					return false;
				}
			}
			if (colCoord > 0 && rowCoord > 1)
			{
				if (unsafe.contains(getLinearCoordinate(boardSize, colCoord - 1, rowCoord - 2)))
				{
					return false;
				}
			}
			if (colCoord > 1 && rowCoord > 0)
			{
				if (unsafe.contains(getLinearCoordinate(boardSize, colCoord - 2, rowCoord - 1)))
				{
					return false;
				}
			}
			
		}

		return true;
	}
	
}
