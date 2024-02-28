package ule.edi.travel;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ule.edi.model.*;

public class TravelArrayImpl implements Travel {
	
	private static final Double DEFAULT_PRICE = 100.0;
	private static final Byte DEFAULT_DISCOUNT = 25;
	private static final Byte CHILDREN_EXMAX_AGE = 18;
	private Date travelDate;
	private int nSeats;
	
	private Double price;    // precio de entradas 
	private Byte discountAdvanceSale;   // descuento en venta anticipada (0..100)
   	
	private Seat[] seats;
		
	
   public TravelArrayImpl(Date date, int nSeats){
	   //TODO 
	   // utiliza los precios por defecto: DEFAULT_PRICE y DEFAULT_DISCOUNT definidos en esta clase
	   //debe crear el array de asientos
	   this.price = DEFAULT_PRICE;
	   this.discountAdvanceSale = DEFAULT_DISCOUNT;
	   this.nSeats = nSeats;
	   this.travelDate = date;
	   this.seats = new Seat[nSeats];
   }
   
   
   public TravelArrayImpl(Date date, int nSeats, Double price, Byte discount){
	   //TODO 
	   // Debe crear el array de asientos
	   this.price = price;
	   this.discountAdvanceSale = discount;
	   this.nSeats = nSeats;
	   this.travelDate = date;
	   this.seats = new Seat[nSeats];

   }


@Override
public Byte getDiscountAdvanceSale() {
	// TODO Auto-generated method stub
	return discountAdvanceSale;
}


@Override
public int getNumberOfSoldSeats() {
	// TODO Auto-generated method stub
	int soldSteats = 0;
	for(int i = 0; i < this.nSeats; i++){
		if(this.seats[i] != null){
			soldSteats++;
		}
	}
	return soldSteats;
}


@Override
public int getNumberOfNormalSaleSeats() {
	// TODO Auto-generated method stub
	int normalSales = 0;
	for(int i = 0; i < this.nSeats; i++){
		if(this.seats[i] != null){
			if(!this.seats[i].getAdvanceSale()){
				normalSales++;
			}
		}
	}
	return normalSales;
}


@Override
public int getNumberOfAdvanceSaleSeats() {
	// TODO Auto-generated method stub
	int advancedSales = 0;
	for(int i = 0; i < this.nSeats; i++){
		if(this.seats[i] != null){
			if(this.seats[i].getAdvanceSale()){
				advancedSales++;
			}
		}
	}
	return advancedSales;
}


@Override
public int getNumberOfSeats() {
	// TODO Auto-generated method stub
	return this.nSeats;
}


@Override
public int getNumberOfAvailableSeats() {
	// TODO Auto-generated method stub
	int avaliableSeats = 0;
	for(int i = 0; i < this.nSeats; i++){
		if(this.seats[i] == null){
			avaliableSeats++;
		}
	}
	return avaliableSeats;

}


@Override
public Seat getSeat(int pos) {
	// TODO Auto-generated method stub
	if (pos > 0 && pos <= this.nSeats) {
		if(this.seats[pos -1] != null){
			return this.seats[pos - 1];
		}
	}
	return null;
}


@Override
public Person refundSeat(int pos) {
	// TODO Auto-generated method stub
	Person person0;
	if(pos <= 0 || pos > nSeats || this.seats[pos - 1] == null){
		return null;
	}
	person0 = this.seats[pos - 1].getHolder();
	this.seats[pos - 1] = null;
	return person0;
}


public boolean isChildren(int age) {
	// TODO Auto-generated method stub
	boolean isChildren = false;
	if(age < CHILDREN_EXMAX_AGE){
		isChildren = true;
	}
	return isChildren;
}

private boolean isAdult(int age) {
	// TODO Auto-generated method stub
	boolean isAdult = false;
	if(age >= CHILDREN_EXMAX_AGE){
		isAdult = true;
	}
	return isAdult;
}


@Override
public List<Integer> getAvailableSeatsList() {
	// TODO Auto-generated method stub
	List<Integer> lista = new ArrayList<Integer>(nSeats);
	for(int i = 0; i < this.nSeats; i++){
		if(this.seats[i] == null){
			lista.add(i + 1);
		}
	}
	return lista;
}


@Override
public List<Integer> getAdvanceSaleSeatsList() {
	// TODO Auto-generated method stub
	List<Integer> lista = new ArrayList<Integer>(nSeats);
	for(int i = 0; i < this.nSeats; i++){
		if(this.seats[i] != null){
			if(this.seats[i].getAdvanceSale() == true){
				lista.add(i + 1);
			}
		}
	}
	
	return lista;
}


@Override
public int getMaxNumberConsecutiveSeats() {
	// TODO Auto-generated method stub
	int consecutiveSeats = 0;
	int maxConsecutiveSeats = 0;
	for(int i = 0; i < this.nSeats; i++){
		if(this.seats[i] == null){
			consecutiveSeats++;
			if(consecutiveSeats > maxConsecutiveSeats){
				maxConsecutiveSeats = consecutiveSeats;
			}
		}else{
			consecutiveSeats = 0;
		}
	}
	return maxConsecutiveSeats;
}


@Override
public boolean isAdvanceSale(Person p) {
	// TODO Auto-generated method stub
	boolean isAdvancedSale = false;
	for(int i = 0; i < this.nSeats; i++){
		if(this.seats[i] != null){
			if(this.seats[i].getHolder().equals(p)){
				if(this.seats[i].getAdvanceSale()){
					isAdvancedSale = true;
				}
			}
		}
	}
	return isAdvancedSale;
}


@Override
public Date getTravelDate() {
	// TODO Auto-generated method stub
	return this.travelDate;
}


@Override
public boolean sellSeatPos(int pos, String nif, String name, int edad, boolean isAdvanceSale) {
	// TODO Auto-generated method stub
	boolean seatCanSell = false;
	Person person1;
	Seat seat1;
	if(pos <= 0 || pos > this.nSeats || this.getPosPerson(nif) != -1){
		return seatCanSell;
	}
	if(this.seats[pos - 1] == null){
		seatCanSell = true;
		person1 = new Person(nif, name, edad);
		seat1 = new Seat(isAdvanceSale, person1);
		this.seats[pos - 1] = seat1;
	}
	return seatCanSell;
}


@Override
public int getNumberOfChildren() {
	// TODO Auto-generated method stub
	int numberOfChildren = 0;
	for(int i = 0; i < this.nSeats; i++){
		if(this.seats[i] != null){
			if(isChildren(this.seats[i].getHolder().getAge())){
				numberOfChildren++;
			}
		}
	}
	return numberOfChildren;
}


@Override
public int getNumberOfAdults() {
	// TODO Auto-generated method stub
	int numberOfAdult = 0;
	for(int i = 0; i < this.nSeats; i++){
		if(this.seats[i] != null){
			if(isAdult(this.seats[i].getHolder().getAge())){
				numberOfAdult++;
			}
		}
	}
	return numberOfAdult;
}


@Override
public Double getCollectionTravel() {
	// TODO Auto-generated method stub
	double collectionTravel = 0.0;
	for(int i = 0; i < this.nSeats; i++){
		if(this.seats[i] != null){
			collectionTravel += getSeatPrice(this.seats[i]);
		}
	}
	return collectionTravel;
}


@Override
public int getPosPerson(String nif) {
	// TODO Auto-generated method stub
	int posPerson = - 1;
	for(int i = 0; i < this.nSeats; i++){
		if(this.seats[i] != null){
			if(this.seats[i].getHolder().getNif().equals(nif)){
				posPerson = i + 1;
			}
		}
	}
	return posPerson;	
}


@Override
public int sellSeatFrontPos(String nif, String name, int edad, boolean isAdvanceSale) {
	// TODO Auto-generated method stub
	int seatFrontPos = -1;
	Seat seat2;
	Person person2;
	if (this.getPosPerson(nif) == -1) {
		for(int i = 0; i < this.nSeats; i++){
			if(this.seats[i] == null){
				seatFrontPos = i + 1;
				person2 = new Person(nif, name, edad);
				seat2 = new Seat(isAdvanceSale, person2);
				this.seats[i] = seat2;
				return seatFrontPos;
			}
		}
	}
	return seatFrontPos;
}


@Override
public int sellSeatRearPos(String nif, String name, int edad, boolean isAdvanceSale) {
	// TODO Auto-generated method stub
	int seatRearPos = -1;
	Seat seat3;
	Person person3;
	if (this.getPosPerson(nif) == -1) {
		for(int i = nSeats; i > 0; i--){
			if(this.seats[i - 1] == null){
				seatRearPos = i;
				person3 = new Person(nif, name, edad);
				seat3 = new Seat(isAdvanceSale, person3);
				this.seats[i - 1] = seat3;
				return seatRearPos;
			}
		}
	}
	return seatRearPos;
}


@Override
public Double getSeatPrice(Seat seat) {
	// TODO Auto-generated method stub
	double seatPrice = 0.0;
	if(seat.getAdvanceSale() == true){
		seatPrice = this.price - ((this.price * discountAdvanceSale)/100.0);
	}else{
		seatPrice = this.price;
	}
	return seatPrice;
}


@Override
public double getPrice() {
	return this.price;
}

}	