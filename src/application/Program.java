package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.dtos.ProductsDTO;

public class Program {

	public static void main(String[] args) {
		 
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the file path: ");
		String strPath = sc.nextLine();

		List<ProductsDTO> products = new ArrayList<>();	

		// READ FILE
		try (BufferedReader file = new BufferedReader(new FileReader(strPath))) {
			String line = file.readLine();

			for (int i = 0; line != null; i++) {
				if (i != 0) {
					String[] objLine = line.split(",");

					String name = objLine[0];
					Double price = Double.parseDouble(objLine[1]);
					Integer amount = Integer.parseInt(objLine[2]);

					products.add(new ProductsDTO(name, price, amount));
				}
				line = file.readLine();
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		File rootPath = new File(strPath);

		// CREATE FOLDER
		String newStrPath = rootPath.getParent() + "\\out";
		boolean successCreateFolder = new File (newStrPath).mkdir();
		System.out.println("folder created with success? " + successCreateFolder);

		// CREATE NEW FILE
		try (BufferedWriter file = new BufferedWriter(new FileWriter(newStrPath+"\\newFile.csv"))) {
			file.write("Name,Total Price\n");
			for (ProductsDTO prod : products) {
				file.write(prod.getName() + "," + prod.getPrice() * prod.getAmount()+"\n");
			}
		}
		catch(IOException e) {
			System.out.println("Error: "+e.getMessage());
		}
		catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		sc.close();
	}

}
