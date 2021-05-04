package prob06;

import java.util.ArrayList;
import java.util.List;

public class TicketingSystem {
	
	List<Reservation> listReservation = new ArrayList<Reservation>();
	
	public void addReservation( int movie_id, String customer_name ) {
		Reservation r = new Reservation( movie_id, customer_name );
		listReservation.add( r ) ;
	}
	
	public boolean cancelReservation( String customer_name ) {
		/* 예약 취소 기능 구현 코드를 작성합니다. */
		int count = listReservation.size();
		for(int i =0; i<count; i++){
			if(customer_name.equals((listReservation.get(i)).getCustomerName())){
				System.out.println("Cancelled.");
				listReservation.remove(i);
				return true;
			}
		}
		
		System.out.println("Not Found");
		return false;
	}
		
	public int reservedCount( int movie_id ) {
		int count=0;
		for( Reservation item: listReservation ) {
			if ( item.getMovieID() == movie_id) {
				count++;
			}
		}	
			
		return count;
	}
}
