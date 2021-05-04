package prob06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) ) ;
	static TicketingSystem ts = new TicketingSystem();
	
	public static void main(String[] args) {
		while(true)	{
			try	{
				System.out.print(
				"\n== Ticketing System ==\n"+
				"1. Reserve\n"+
				"2. Cancel\n"+
				"3. Count\n"+
				"4. exit\n"+
				"> " );
				
				String kind = br.readLine();
				
				switch( Integer.parseInt( kind ) ) {
					case 1: 
						doReserve(); 
						break;
					case 2: 
						doCancel(); 
						break;
					case 3: 
						doCount(); 
						break;
					case 4:
						System.exit(0);
						break;
					default:
						System.out.println("\n Wrong.\n\n");
				}
			} catch( IOException ie ) {
				System.out.println("Incorrect input.");
			}
		}
	}

	static void printMovieListMenu(String title) {
		System.out.print(
				"\n== "+title+"==\n"+
				"1. The Lord of the Rings\n"+
				"2. The Matrix\n"+
				"3. Pride & Prejudice\n"+
				"> " );
	}
	
	static void doReserve() throws IOException {
		printMovieListMenu("Reservation");
		String id = br.readLine() ;
		if(Integer.parseInt(id) <= 3) {
			System.out.println("Your name: ");
			String name = br.readLine();
			ts.addReservation( Integer.parseInt(id), name );
		}
	}
	
	static void doCancel() throws IOException {
		/* 예약 취소 기능 구현 코드를 작성합니다 */
		System.out.print("Your name: ");
		String name = br.readLine();
		
		ts.cancelReservation(name);
		
	}
	
	static void doCount() throws IOException {
		printMovieListMenu( "Count" );
		String id = br.readLine() ;
		if(Integer.parseInt(id) <= 3) {
			System.out.println( "Reserved count: " + ts.reservedCount( Integer.parseInt( id ) ) );
		} else {
			System.out.println( "Invalid movie ID." );
		}
	}
}
